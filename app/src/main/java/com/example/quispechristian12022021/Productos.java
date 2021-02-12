package com.example.quispechristian12022021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Productos extends AppCompatActivity {
    EditText edtIdproducto,edtNombre,edtPrecio;

    Button btnBuscar, btnInsertar,btnSiguiente;

    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        edtIdproducto= (EditText)findViewById(R.id.edtProducto);
        edtNombre= (EditText)findViewById(R.id.edtNombreProd);
        edtPrecio= (EditText)findViewById(R.id.edtPrecioUnitario);

        btnInsertar= (Button) findViewById(R.id.btnInsertarProducto);
        btnBuscar= (Button) findViewById(R.id.btnBuscarProducto);
        btnSiguiente=(Button) findViewById(R.id.btnSiguienteP);

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ejecutarServicio("http://192.168.1.7:8080/lacigarra/insertar_productos.php");

            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarProducto("http://192.168.1.7:8080/productoscq/buscar_producto.php?codigo="+edtIdproducto.getText()+"");
            }
        });
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
                parametros.put("id_producto",edtIdproducto.getText().toString());
                parametros.put("nombre",edtNombre.getText().toString());
                parametros.put("precio",edtPrecio.getText().toString());
                return parametros;
            }
        };

        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void buscarProducto(String URL){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        edtNombre.setText(jsonObject.getString("nombre"));
                        edtPrecio.setText(jsonObject.getString("precio"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"ERROR CONEXIÓN", Toast.LENGTH_SHORT).show();            }
        }
        );
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    private void limpiarFormulario(){
        edtIdproducto.setText("");
        edtNombre.setText("");
        edtPrecio.setText("");
    }
}
