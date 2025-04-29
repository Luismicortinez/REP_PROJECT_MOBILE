package com.luismiguelcortinez.apilocal;

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
        ApiService apiService = retrofit.create(ApiService.class);

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Llamada a la API cuando el botón es presionado
                    Call<ResponseApi> call = apiService.getApod();

                    call.enqueue(new Callback<ResponseApi>() {
                        @Override
                        public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                ResponseApi responseApi = response.body();

                                // Mostrar el título y la explicación
                                titleTextView.setText(responseApi.getTitle());
                                explanationTextView.setText(responseApi.getExplanation());

                                // Cargar la imagen usando Picasso
                                Picasso.get().load(responseApi.getUrl()).into(imageView);
                            } else {
                                Toast.makeText(MainActivity.this, "Respuesta inválida del servidor.", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseApi> call, Throwable t) {
                            // Manejar error de conexión o de servidor
                            Toast.makeText(MainActivity.this, "Error al conectar con la API. Intenta nuevamente.", Toast.LENGTH_LONG).show();
                        }
                    });

                } catch (Exception e) {  // <- Aquí indicamos Exception
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Ha ocurrido un error inesperado.", Toast.LENGTH_LONG).show();
                }
            }

        });
    }
}