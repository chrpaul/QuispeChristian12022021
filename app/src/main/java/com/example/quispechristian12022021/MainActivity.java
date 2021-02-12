package com.example.quispechristian12022021;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView tv_pedido, tv_nombre, tv_precio, tv_sub_ice, tv_sub_iva, tv_sub;
    private EditText edtproducto, edtcliente,edtcantidad;
    private Button btnnuevo, btncargar, btnañadir, btnquitar;
    private CheckBox cb_ice, cb_iva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    //Quitar
    public void QuitarProducto(View view) {
        int codigo = Integer.valueOf(et_codigo.getText().toString());
        for (int i = 0; i < pedido.getDetalles().size(); i++) {
            if (codigo == pedido.getDetalles().get(i).producto.id_producto) {
                pedido.getDetalles().remove(i);
                break;
            }
        }

        for (int i = 1; i < linea.size(); i++) {
            int cod = Integer.valueOf(linea.get(i).substring(0, 1).trim());
            if (cod == codigo) {
                linea.remove(i);
                LimpiarCampos();
                adapter.notifyDataSetChanged();
                break;
            }
        }
        CalcularValores();

    }

    //Cargar
    public void CargarProducto(View view) {

        try {
            SQLiteDatabase base = admin.getReadableDatabase();

            String codigo = et_codigo.getText().toString();

            if (!codigo.isEmpty()) {
                Cursor fila = base.rawQuery("SELECT nombre,precio FROM productos WHERE id_producto =" + codigo, null);
                if (fila.moveToFirst()) {
                    tv_nombre.setText(fila.getString(0));
                    tv_precio.setText(fila.getString(1));
                    base.close();
                } else {
                    Toast.makeText(this, "Producto no existe", Toast.LENGTH_SHORT).show();
                    base.close();
                }
            } else {
                Toast.makeText(this, "Ingrese el codigo del producto", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            String exepcion = e.getMessage();
        }


    }

    //Añadir
    public void AñadirProductoLista(View view) {

        if (et_cantidad.getText().toString().isEmpty() || et_codigo.getText().toString().isEmpty() || tv_precio.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingrese todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            int id = Integer.parseInt(et_codigo.getText().toString());
            int cantidad = Integer.parseInt(et_cantidad.getText().toString());
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
