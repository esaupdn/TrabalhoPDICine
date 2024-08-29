package br.edu.ifsuldeminas.mch.trabalhocinemapdi;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.edu.ifsuldeminas.mch.trabalhocinemapdi.model.Movie;
import br.edu.ifsuldeminas.mch.trabalhocinemapdi.model.db.MovieDAO;

public class ListActivity extends AppCompatActivity {

    ListView moviesList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        moviesList = findViewById(R.id.moviesList);
        registerForContextMenu(moviesList);

        Button btnAdd = findViewById(R.id.listMoviesBtnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, AddActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });


        moviesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Movie movie = (Movie) moviesList.getItemAtPosition(position);

                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
                intent.putExtra("contaDetalhada", movie);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateMovies();
    }

    private void updateMovies() {
        MovieDAO dao = new MovieDAO(this);
        List<Movie> movieList = dao.loadMovies();

        ArrayAdapter<Movie> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, movieList);
        moviesList.setAdapter(arrayAdapter);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem itemDelete = menu.add("Apagar Filme");
        itemDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                AdapterView.AdapterContextMenuInfo itemClicado = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Movie movie = (Movie) moviesList.getItemAtPosition(itemClicado.position);

                MovieDAO dao = new MovieDAO(ListActivity.this);

                dao.delete(movie);
                updateMovies();

                Toast toast = Toast.makeText(ListActivity.this, "Filme apagado com sucesso!", Toast.LENGTH_SHORT);
                toast.show();

                return true;
            }
        });

        MenuItem itemDetail = menu.add("Editar Conta");
        itemDetail.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                AdapterView.AdapterContextMenuInfo itemClicado = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Movie movie = (Movie) moviesList.getItemAtPosition(itemClicado.position);

                Intent intent = new Intent(ListActivity.this, AddActivity.class);
                intent.putExtra("contaEdicao", movie);
                startActivity(intent);

                return true;
            }
        });
    }
}
