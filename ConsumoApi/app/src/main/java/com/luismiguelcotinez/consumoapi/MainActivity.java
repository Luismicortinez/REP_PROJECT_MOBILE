package com.luismiguelcotinez.consumoapi;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView titleTextView;
    private TextView explanationTextView;
    private Button btnConsultar;
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
        imageView = findViewById(R.id.imageView);
        titleTextView = findViewById(R.id.titleTextView);
        explanationTextView = findViewById(R.id.explanationTextView);
        btnConsultar = findViewById(R.id.btnConsultar);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nasa.gov/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NasaApiService nasaApiService = retrofit.create(NasaApiService.class);

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Llamada a la API cuando el botón es presionado
                Call<ApodResponse> call = nasaApiService.getApod("TPx0CUOownaH7IS1xKD6AyhJVW1eHBvA63rAITCh");

                call.enqueue(new Callback<ApodResponse>() {
                    @Override
                    public void onResponse(Call<ApodResponse> call, Response<ApodResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            ApodResponse apodResponse = response.body();

                            // Mostrar el título y la explicación
                            titleTextView.setText(apodResponse.getTitle());

                            explanationTextView.setText(apodResponse.getExplanation());

                            // Cargar la imagen usando Picasso
                            Picasso.get().load(apodResponse.getUrl()).into(imageView);
                        }
                    }

                    @Override
                    public void onFailure(Call<ApodResponse> call, Throwable t) {
                        // Manejar error
                        Toast.makeText(MainActivity.this, "Error al conectar con la API. Intenta nuevamente.", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}