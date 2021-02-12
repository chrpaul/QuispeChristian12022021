package com.example.quispechristian12022021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

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
}
