package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.Models.ApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {
    Context context;

    Retrofit retrofit = new  Retrofit.Builder()
            .baseUrl("https://www.freetogame.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void getGamesHeadlines(OnFetchDataListener listener, String category, String query)
    {
        CallApiResponse callApiResponse = retrofit.create(CallApiResponse.class);
        Call<ApiResponse> call = callApiResponse.callHeadlines("pc", category, query, context.getString(R.string.api_key));

        try {
            call.enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    if (!response.isSuccessful()){
                        Toast.makeText(context, "Error!!", Toast.LENGTH_SHORT).show();
                    }
                    //listener.onFetchData(response.body().getId(), response.message());
                    Log.d("TAG", response.body().getId() + "");
                }
                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    listener.onError("Request Failed!");
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public RequestManager(Context context) {
        this.context = context;
    }
    public interface CallApiResponse {
        @GET("games")
        Call<ApiResponse> callHeadlines(
                @Query("platform") String  platform,
                @Query("category") String category,
                @Query("a") String query,
                @Query("apiKey") String api_key
        );
    }
}
