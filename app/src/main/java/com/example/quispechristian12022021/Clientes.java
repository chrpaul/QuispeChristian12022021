package com.example.quispechristian12022021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;

public class Clientes extends AppCompatActivity {

    EditText edtIdcliente,edtProducto,edtDireccion;

    Button btnBuscar, btnInsertar,btnSiguiente;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        edtIdcliente= (EditText)findViewById(R.id.edtIdCliente);
        edtProducto= (EditText)findViewById(R.id.edtNombreCliente);
        edtDireccion= (EditText)findViewById(R.id.edtDireccion);

        btnInsertar= (Button) findViewById(R.id.btnInsertar);
        btnBuscar= (Button) findViewById(R.id.btnBuscar);
        btnSiguiente=(Button) findViewById(R.id.btnSiguiente);
    }
}
