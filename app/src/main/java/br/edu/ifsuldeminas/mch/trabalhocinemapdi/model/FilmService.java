package br.edu.ifsuldeminas.mch.trabalhocinemapdi.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FilmService {

    @GET("movie/popular")
    Call<FilmResponse> getPopularFilms(@Query("api_key") String apiKey);
}