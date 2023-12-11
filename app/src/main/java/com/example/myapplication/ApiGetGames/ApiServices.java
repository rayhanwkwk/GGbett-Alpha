package com.example.myapplication.ApiGetGames;

import com.example.myapplication.Models.ApiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("games")
    Call<List<GamesHeadlines>> getGames();

    @GET("game")
    Call<GameItemDetail> getGameDetail(@Query("id") int gameId);

    @GET("search")
    Call<ApiResponse> search(@Query("query") String query);
}
