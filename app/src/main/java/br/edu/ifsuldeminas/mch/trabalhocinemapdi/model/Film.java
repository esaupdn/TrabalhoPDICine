package br.edu.ifsuldeminas.mch.trabalhocinemapdi.model;

import androidx.annotation.NonNull;

public class Film {
    private String title;
    private String release_date;
    private double vote_average;

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public double getVoteAverage() {
        return vote_average;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("%s (%s) - Rating: %.1f", title, release_date, vote_average);
    }
}