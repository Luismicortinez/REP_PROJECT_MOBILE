package com.luismiguelcotinez.formulariocedulajava;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateActivity extends AppCompatActivity {

    private Button BtnGuardarEstudiante, BtnGuardarCatedras, BtnVolver;
    private EditText etNombreP, etApellidos, etDocumento, etEmail, etNombreC, etHorario;
    private DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        BtnGuardarEstudiante = findViewById(R.id.btnGuardarEstudiante);
        BtnGuardarCatedras = findViewById(R.id.btnGuardarCatedras);
        BtnVolver = findViewById(R.id.btnVolver);
        etNombreP =findViewById(R.id.etNombre);
        etApellidos =findViewById(R.id.etApellidos);
        etDocumento =findViewById(R.id.etCedula);
        etEmail = findViewById(R.id.etCorreo);
        etNombreC=findViewById(R.id.etNombreCatedra);
        etHorario =findViewById(R.id.etHorario);
        dbHandler = ((BDHelper)getApplicationContext()).getDBHandler();
        BtnGuardarEstudiante.setOnClickListener(v-> GuardarEstudiante());
        BtnGuardarCatedras.setOnClickListener(v-> GuardarCatedra());
        BtnVolver.setOnClickListener(v->Volver());
    }
    private void GuardarEstudiante(){
        String nombre = etNombreP.getText().toString();
        String apellidos = etApellidos.getText().toString();
        String documento = etDocumento.getText().toString();
        String email = etEmail.getText().toString();
        if (documento.isEmpty() || nombre.isEmpty() || apellidos.isEmpty() || email.isEmpty())
        {
            Message.message(this, "Hay un campo Vacio o invalido");
            return;
        }
        dbHandler.ingresarEstudiante(nombre,apellidos,documento,email);

        Message.message(this, "Ingreso Exitoso");
    }
    private void GuardarCatedra(){
        String nombre = etNombreC.getText().toString();
        String horario = etHorario.getText().toString();

        if (horario.isEmpty() || nombre.isEmpty())
        {
            Message.message(this, "Hay un campo Vacio o invalido");
            return;
        }
        dbHandler.ingresarCatedra(nombre,horario);

        Message.message(this, "Ingreso Exitoso");

    }
    private void Volver()
    {
        finish();
    }
}