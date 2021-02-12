package com.example.quispechristian12022021;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView tv_pedido, tv_nombre, tv_precio, tv_sub_ice, tv_sub_iva, tv_sub;
    private EditText edtproducto, edtcantidad;
    private Button btnnuevo, btncargar, btnañadir, btnquitar;
    private CheckBox cb_ice, cb_iva;
    List<String> linea = new ArrayList<>();
    RequestQueue requestQueue;
    private ListView lv_datos;
    private Pedido pedido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    private void ejecutarServicio(String URL){
        StringRequest stringRequest= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),"OPERACION EXITOSA",Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros=new HashMap<String, String>();
                parametros.put("id_Detalle",edtproducto.getText().toString());
                parametros.put("cantidad",edtcantidad.getText().toString());
                return parametros;
            }
        };

        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void AñadirProductoLista(View view) {

        if (edtcantidad.getText().toString().isEmpty() || edtproducto.getText().toString().isEmpty() || tv_precio.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingrese todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            int id = Integer.parseInt(edtproducto.getText().toString());
            int cantidad = Integer.parseInt(edtcantidad.getText().toString());
            double precio = Double.parseDouble(tv_precio.getText().toString());
            String nombre = tv_nombre.getText().toString();

            Producto producto = new Producto();
            producto.id_producto = id;
            producto.nombre = tv_nombre.getText().toString();
            producto.precio = Double.parseDouble(tv_precio.getText().toString());

            double iva = 0.12;
            double ice = 0.10;
            double subtotalp = 0;
            double subtotal = 0;
            double subtotal_iva = 0;
            double subtotal_ice = 0;

            //Calculo de valores

            subtotalp = cantidad * precio;

            if (cb_iva.isChecked()) {
                subtotal_iva = subtotalp * iva;
            }
            if (cb_ice.isChecked()) {
                subtotal_ice = subtotalp * ice;
            }
            subtotal = subtotalp + subtotal_ice + subtotal_iva;


            Detalle detalle = new Detalle();
            detalle.producto = producto;
            detalle.cantidad = cantidad;
            detalle.subtotal_ice = subtotal_ice;
            detalle.subtotal_iva = subtotal_iva;
            detalle.subtotal_producto = subtotal;
            boolean existe = false;
            for (int i = 1; i < linea.size(); i++) {
                int cod = Integer.valueOf(linea.get(i).substring(0, 1).trim());
                if (cod == id) {
                    existe = true;
                    Toast.makeText(this, "El producto ya se encuentra en el pedido", Toast.LENGTH_SHORT).show();
                }
            }
            if (!existe) {
                pedido.getDetalles().add(detalle);

                //mostrar lista

                DecimalFormat formato = new DecimalFormat("#.##");
                //6 15 11 6 5 5

                linea.add(id + "     " + nombre + "        " + cantidad + "         " + precio + "  " + formato.format(subtotal_ice) + " " + formato.format(subtotal_iva) + "  " + formato.format(subtotal));
                adapter.notifyDataSetChanged();
                CalcularValores();
                LimpiarCampos();
            }
        }

    }

    private void CalcularValores() {
        double total_iva = 0;
        double total_ice = 0;
        double total = 0;
        for (int i = 0; i < pedido.getDetalles().size(); i++) {
            total_ice += pedido.getDetalles().get(i).subtotal_ice;
            total_iva += pedido.getDetalles().get(i).subtotal_iva;
            total += pedido.getDetalles().get(i).subtotal_producto;
        }
        DecimalFormat formato = new DecimalFormat("#.##");
        tv_sub_iva.setText(formato.format(total_iva));
        tv_sub_ice.setText(formato.format(total_ice));
        tv_sub.setText(formato.format(total));

    }

    private void LimpiarCampos() {
        edtproducto.setText("");
        edtcliente.setText("");
        edtcantidad.setText("");
        tv_precio.setText("");
        tv_nombre.setText("");

    }
}
