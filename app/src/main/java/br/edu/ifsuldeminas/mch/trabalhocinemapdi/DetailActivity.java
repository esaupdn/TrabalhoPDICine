package br.edu.ifsuldeminas.mch.trabalhocinemapdi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.edu.ifsuldeminas.mch.trabalhocinemapdi.model.Movie;

public class DetailActivity extends AppCompatActivity {

    private TextView detailTxtName, detailTxtGenre, detailTxtDirector;
    private ImageButton btnShare, btnBackToList;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        // Referências das TextViews
        detailTxtName = findViewById(R.id.detailMovieName);
        detailTxtDirector = findViewById(R.id.detailTxtDirector);
        detailTxtGenre= findViewById(R.id.detailTxtGenre);

        // Recebe a Bill passada pela ListActivity
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("contaDetalhada")) {
            movie = (Movie) intent.getSerializableExtra("contaDetalhada");
            populateDetails();
        }

        btnBackToList = findViewById(R.id.btnBackToList);
        btnBackToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // Implementação do botão de compartilhar
        btnShare = findViewById(R.id.detailBtnShare);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareBillDetails();
            }
        });
    }

    // Método para popular os TextViews com os detalhes da conta
    private void populateDetails() {
        if (movie != null) {
            detailTxtName.setText(movie.getName());
            detailTxtGenre.setText(movie.getGenre());
            detailTxtDirector.setText(movie.getDirector());
        }
    }


    // Método para compartilhar os detalhes da conta
    private void shareBillDetails() {
        if (movie != null) {
            String message = String.format(
                    "-------- CINEMA -------\n" +
                            "Filme: %s\n" +
                            "Gênero: %s\n" +
                            "Diretor: %s\n",
                    movie.getName(), movie.getGenre(), movie.getDirector());


            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, message);
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        }
    }
}
