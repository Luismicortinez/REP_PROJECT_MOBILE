package com.luismiguelcotinez.formulariocedulajava;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ReadActivity extends AppCompatActivity {
    private EditText etDocumento,etCatedra;
    private TextView tvEstudiantes, tvCatedras;
    private Button btnConsultarE,btnConsultarC,btnVolver;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_read);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        dbHandler = ((BDHelper)getApplicationContext()).getDBHandler();
        btnConsultarE = findViewById(R.id.btnConsultarE);
        btnConsultarC = findViewById(R.id.btnConsultarC);
        btnVolver = findViewById(R.id.btnVolver);
        tvCatedras = findViewById(R.id.tvCatedras);
        tvEstudiantes = findViewById(R.id.tvEstudiantes);
        etDocumento = findViewById(R.id.etDocumento);
        etCatedra = findViewById(R.id.etCatedras    );
        btnConsultarE.setOnClickListener(v->ConsultarE());
        btnConsultarC.setOnClickListener(v->ConsultarC());
        btnVolver.setOnClickListener(v->Volver());
    }
    private void ConsultarE()
    {
        String documento = etDocumento.getText().toString();
        if (documento.isEmpty())
        {
            Message.message(this, "Hay un campo Vacio o invalido");
            return;
        }
        String result = dbHandler.consultarE(documento);
        tvEstudiantes.setText(result);
        Message.message(this, "Consulta Exitosa");
    }
    private void ConsultarC()
    {
        String name = etCatedra.getText().toString();
        if (name.isEmpty())
        {
            Message.message(this, "Hay un campo Vacio o invalido");
            return;
        }
        String result = dbHandler.consultarC(name);
        tvCatedras.setText(result);
        Message.message(this, "Consulta Exitosa");
    }
    private void Volver()
    {
        finish();
    }


}