package br.edu.ifsuldeminas.mch.trabalhocinemapdi.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Movie implements Serializable {

    private Integer id;
    private String name;
    private String genre;
    private String director;

    public Movie() {
    }

    public Movie(Integer id, String name, String genre, String director) {
        this.id = id;
        setName(name);
        setGenre(genre);
        setDirector(director);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @NonNull
    @Override
    public String toString() {
        return name + " - " + genre + " - Directed by: " + director;
    }
}