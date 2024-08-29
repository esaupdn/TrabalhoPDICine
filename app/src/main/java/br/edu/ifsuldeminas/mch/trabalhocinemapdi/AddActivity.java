package br.edu.ifsuldeminas.mch.trabalhocinemapdi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.edu.ifsuldeminas.mch.trabalhocinemapdi.model.Movie;
import br.edu.ifsuldeminas.mch.trabalhocinemapdi.model.db.MovieDAO;

public class AddActivity extends AppCompatActivity {

    private Movie movie;

    Button btnAdd;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        Intent chooserIntent = getIntent();
        movie = (Movie) chooserIntent.getSerializableExtra("contaEdicao");

        Button btnList = findViewById(R.id.addMovieBtnList);
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, ListActivity.class);
                Toast.makeText(AddActivity.this, "Indo para tela de Listagem.", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
            }
        });

        EditText MovieNameInput = findViewById(R.id.textMovieName);
        EditText MovieGenreInput = findViewById(R.id.textMovieGenre);
        EditText MovieDirectorInput = findViewById(R.id.textMovieDirector);



        if (movie != null) {
            MovieNameInput.setText(movie.getName());
            MovieGenreInput.setText(movie.getGenre());
            MovieDirectorInput.setText(movie.getDirector());
        }

        btnAdd = findViewById(R.id.btnAddMovie);
        btnAdd.setOnClickListener(view -> {

            String name = MovieNameInput.getText().toString();
            String genre = MovieGenreInput.getText().toString();
            String director = MovieDirectorInput.getText().toString();

            if (name.isEmpty() || genre.isEmpty() || director.isEmpty()) {
                Toast toast = Toast.makeText(AddActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                MovieDAO dao = new MovieDAO(this);
                if (this.movie == null) {
                    movie = new Movie();
                    movie.setName(name);
                    movie.setGenre(genre);
                    movie.setDirector(director);

                    dao.save(movie);
                    Toast toast = Toast.makeText(this, "Filme adicionado a lista com sucesso!", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    movie.setName(name);
                    movie.setGenre(genre);
                    movie.setDirector(director);

                    dao.update(movie);
                    Toast toast = Toast.makeText(this, "Filme atualizado com sucesso!", Toast.LENGTH_LONG);
                    toast.show();
                }
                finish();
            }
        });

    }
}
