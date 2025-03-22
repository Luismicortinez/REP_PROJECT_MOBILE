package com.luismiguelcotinez.formulariocedulajava;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.TextView;
import android.widget.Button;

public class ContactActivity extends AppCompatActivity {

    private TextView TxvInformcaionContacto;
    private Button BtnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TxvInformcaionContacto = findViewById(R.id.idViewContact);
        TxvInformcaionContacto.setText("Nombre: Luis Miguel \n Documento: 1037640956");
        BtnVolver = findViewById(R.id.idBtnVolver);

        BtnVolver.setOnClickListener(c -> Volver());
    }

    private void  Volver(){
        finish();
    }
}