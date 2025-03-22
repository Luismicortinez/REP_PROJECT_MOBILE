package com.luismiguelcotinez.formulariocedulajava;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;



public class MainActivity extends AppCompatActivity {

    private EditText EdtDocumento;
    private TextView TxvData;
    private Button BtnGuardar;
    private Button BtnMostrar;
    protected  DBHandler dbhandler;
    private Button BtnContacto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EdtDocumento = findViewById(R.id.idEdtDocumento);
        TxvData = findViewById(R.id.idTxvData);
        BtnGuardar = findViewById(R.id.idBtnGuardar);
        BtnMostrar = findViewById(R.id.idBtnMostrar);
        BtnContacto = findViewById(R.id.idBtnContacto);


        dbhandler = new DBHandler(this);
        BtnGuardar.setOnClickListener(v -> Guardar());
        BtnMostrar.setOnClickListener(v -> Mostrar());
        BtnContacto.setOnClickListener(v -> Contacto());

    }

    private void Contacto(){
        Intent intent = new Intent(MainActivity.this, ContactActivity.class);
        startActivity(intent);
    }

    private void Guardar(){
        String documento = EdtDocumento.getText().toString();

        if (documento.isEmpty())
        {
            Message.message(this, "Campo Vacio");
            return;
        }
        dbhandler.ingresar(documento);

        Message.message(this, "Ingreso Exitoso");
    }

    private void Mostrar(){
        String resultado = dbhandler.consultar();
        TxvData.setText(resultado);
        Message.message(this,resultado);
    }

}