package br.edu.ifsuldeminas.mch.trabalhocinemapdi.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsuldeminas.mch.trabalhocinemapdi.model.Movie;

public class MovieDAO extends DAO {

    public MovieDAO(Context context) {
        super(context);
    }

    public boolean save(Movie movie) {
        SQLiteDatabase dataBase = openToWrite();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", movie.getName());
        contentValues.put("director", movie.getDirector());
        contentValues.put("genre", movie.getGenre());

        dataBase.insert("movies", null, contentValues);

        dataBase.close();

        return true;
    }

    public List<Movie> loadMovies() {
        SQLiteDatabase dataBase = openToRead();
        List<Movie> movies = new ArrayList<Movie>();
        String sql = "SELECT * FROM movies;";
        Cursor cursor = dataBase.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(
                    cursor.getColumnIndexOrThrow("name"));
            String genre = cursor.getString(cursor.getColumnIndexOrThrow("genre"));
            String director = cursor.getString(cursor.getColumnIndexOrThrow("director"));
            Movie movie = new Movie(id, name, genre, director);
            movies.add(movie);
        }
        cursor.close();
        dataBase.close();
        return movies;
    }

    public void delete(Movie movie) {
        SQLiteDatabase dataBase = openToWrite();

        String[] params = {movie.getId().toString()};
        dataBase.delete("movies", "id = ?", params);

        dataBase.close();
    }

    public void update(Movie movie) {
        SQLiteDatabase dataBase = openToWrite();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", movie.getName());
        contentValues.put("genre", movie.getGenre());
        contentValues.put("director", movie.getDirector());

        String[] params = {movie.getId().toString()};
        dataBase.update("movies", contentValues, "id = ?", params);

        dataBase.close();
    }
}
