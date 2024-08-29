package br.edu.ifsuldeminas.mch.trabalhocinemapdi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.edu.ifsuldeminas.mch.trabalhocinemapdi.model.Film;
import br.edu.ifsuldeminas.mch.trabalhocinemapdi.model.FilmResponse;
import br.edu.ifsuldeminas.mch.trabalhocinemapdi.model.FilmService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilmsActivity extends AppCompatActivity {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "24ba5b0c26143b808033dc756bca6c1d";

    private ListView filmsListView;

    List<Film> filmsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films);

        filmsListView = findViewById(R.id.listFilms);

        // Configura o Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FilmService service = retrofit.create(FilmService.class);

        // Faz a chamada à API
        Call<FilmResponse> call = service.getPopularFilms(API_KEY);

        // Executa a requisição de forma assíncrona
        call.enqueue(new Callback<FilmResponse>() {
            @Override
            public void onResponse(Call<FilmResponse> call, Response<FilmResponse> response) {
                if (response.isSuccessful()) {
                    filmsList = response.body().getResults();
                    updateFilms();

                } else {
                    Log.e("Retrofit", "Erro na resposta: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<FilmResponse> call, Throwable t) {
                Log.e("Retrofit", "Erro na requisição: " + t.getMessage());
            }
        });
    }

    private void updateFilms() {
        ArrayAdapter<Film> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filmsList);
        filmsListView.setAdapter(arrayAdapter);
    }
}
