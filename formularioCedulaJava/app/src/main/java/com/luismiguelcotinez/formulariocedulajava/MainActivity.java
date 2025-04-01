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

    private TextView TxvData;
    private Button BtnGuardar, btnConsutarID, BtnMostrar, BtnContacto, BtnRetirar, BtnModificar;
    protected  DBHandler dbhandler;



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

        BtnGuardar = findViewById(R.id.idBtnGuardar);
        BtnMostrar = findViewById(R.id.idBtnConsult);
        BtnContacto = findViewById(R.id.idBtnContacto);
        btnConsutarID = findViewById(R.id.idBtnConsult);
        BtnRetirar = findViewById(R.id.idBtnEliminar);
        BtnModificar = findViewById(R.id.idBtnModificar);




        dbhandler = new DBHandler(this);
        BtnGuardar.setOnClickListener(v -> Guardar());
        BtnMostrar.setOnClickListener(v -> Mostrar());
        BtnContacto.setOnClickListener(v -> Contacto());
        btnConsutarID.setOnClickListener(v-> ConsultarID());
        BtnModificar.setOnClickListener(v-> Modificar());
        BtnRetirar.setOnClickListener(v-> Retirar());

    }

    private void Contacto(){
        Intent intent = new Intent(MainActivity.this, ContactActivity.class);
        startActivity(intent);
    }

    private void Guardar(){

        Intent intent = new Intent(MainActivity.this, CreateActivity.class);
        startActivity(intent);
    }
    private void ConsultarID(){
        Intent intent = new Intent(MainActivity.this, ReadActivity.class);
        startActivity(intent);
    }
    private void Mostrar(){
        String resultado = dbhandler.consultar();
        TxvData.setText(resultado);
        Message.message(this,resultado);
    }
    private void Modificar(){
        Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
        startActivity(intent);
    }
    private void Retirar(){
        Intent intent = new Intent(MainActivity.this, DeleteActivity.class);
        startActivity(intent);
    }
}