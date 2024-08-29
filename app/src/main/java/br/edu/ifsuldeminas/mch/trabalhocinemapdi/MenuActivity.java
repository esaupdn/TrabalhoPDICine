package br.edu.ifsuldeminas.mch.trabalhocinemapdi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_page);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageButton btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(v -> {
            SharedPreferences preferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();

            Intent intent = new Intent(MenuActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        Button btnAddMovie = findViewById(R.id.btnAddMovies);
        btnAddMovie.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity.this, AddActivity.class);
            startActivity(intent);
        });

        Button btnListMovie = findViewById(R.id.btnListMovies);
        btnListMovie.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity.this, ListActivity.class);
            startActivity(intent);
        });

        Button btnSuggestion = findViewById(R.id.btnSuggestionView);
        btnSuggestion.setOnClickListener(view -> {


            Intent intent = new Intent(MenuActivity.this, SuggestionActivity.class);
            startActivity(intent);
        });

        Button btnFirebaseView = findViewById(R.id.btnFirebaseView);
        btnFirebaseView.setOnClickListener(view -> {


            Intent intent = new Intent(MenuActivity.this, ListCinemaBranchesActivity.class);
            startActivity(intent);
        });

        Button btnApiView = findViewById(R.id.btnApiView);
        btnApiView.setOnClickListener(view -> {


            Intent intent = new Intent(MenuActivity.this, FilmsActivity.class);
            startActivity(intent);
        });


        if (getIntent().hasExtra("USERNAME")) {
            String username = getIntent().getStringExtra("USERNAME");
            Toast.makeText(this, "Bem-vindo, " + username + "!", Toast.LENGTH_SHORT).show();
        }
    }

}
