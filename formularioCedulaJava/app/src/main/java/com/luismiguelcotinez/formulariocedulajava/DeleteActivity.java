package com.luismiguelcotinez.formulariocedulajava;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DeleteActivity extends AppCompatActivity {

    private EditText etDocumento,etNombreCatedra;
    private Button btnEliminarEstudiante,btnEliminarCatedra,btnVolver;
    private DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delete);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        dbHandler = ((BDHelper)getApplicationContext()).getDBHandler();
        btnEliminarEstudiante = findViewById(R.id.btnEliminarEstudiante);
        btnEliminarCatedra = findViewById(R.id.btnEliminarCatedra);
        btnVolver = findViewById(R.id.btnVolver);
        etDocumento = findViewById(R.id.etDocumento);
        etNombreCatedra = findViewById(R.id.etNombreCatedra);
        btnEliminarEstudiante.setOnClickListener(v->eliminarE());
        btnEliminarCatedra.setOnClickListener(v->eliminarC());
        btnVolver.setOnClickListener(v->Volver());
    }
    private void eliminarE(){

        String iddocumento = etDocumento.getText().toString();
        if (iddocumento.isEmpty())
        {
            Message.message(this, "Campo Vacio");
            return;
        }
        dbHandler.eliminarE(iddocumento);
        Message.message(this, "Eliminar Exitoso");

    }
    private void eliminarC(){

        String nombre = etNombreCatedra.getText().toString();
        if (nombre.isEmpty())
        {
            Message.message(this, "Campo Vacio");
            return;
        }
        dbHandler.eliminarC(nombre);
        Message.message(this, "Eliminar Exitoso");
    }
    private void Volver()
    {
        finish();
    }
}