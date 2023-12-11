package com.example.myapplication.Models;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.ApiGetGames.ApiClient;
import com.example.myapplication.ApiGetGames.ApiServices;
import com.example.myapplication.ApiGetGames.GameItemDetail;
import com.example.myapplication.ApiGetGames.GamesAdapter;
import com.example.myapplication.ApiGetGames.GamesHeadlines;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail_Headliness extends AppCompatActivity {

    private String TAG = "Tag LogCat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_headliness);

        // Inisialisasi elemen-elemen dari layout
        ImageView thumbnailImageView = findViewById(R.id.imageThumbnail);
        TextView titleTextView = findViewById(R.id.textTitle);
        TextView shortDescriptionTextView = findViewById(R.id.textShortDescription);
        TextView descriptionTextView = findViewById(R.id.textDescription);
        TextView textDeveloper = findViewById(R.id.textAuthor);

        // Mengambil ID game yang dipilih
        int gameId = getIntent().getIntExtra("id", -1);

        if (gameId != -1) {
            fetchGameDetail(gameId);
        } else {
            finish();
        }
    }

    private void fetchGameDetail(int gameId) {
        ApiServices apiService = ApiClient.getRetrofitInstance().create(ApiServices.class);
        Call<GameItemDetail> call = apiService.getGameDetail(gameId);

        call.enqueue(new Callback<GameItemDetail>() {
            @Override
            public void onResponse(Call<GameItemDetail> call, Response<GameItemDetail> response) {
                if (response.isSuccessful()) {
                    GameItemDetail gameDetail = response.body();

                    // Setel data ke tampilan detail
                    setDetailData(gameDetail);
                } else {
                    Log.d(TAG, "Error" + response.toString());
                }
            }

            @Override
            public void onFailure(Call<GameItemDetail> call, Throwable t) {
                Log.d(TAG, "Error Fatal" + t.getMessage().toString());
            }
        });
    }

    private void setDetailData(GameItemDetail gameDetail) {
        // Dapatkan referensi ke elemen-elemen tampilan
        ImageView imageThumbnail = findViewById(R.id.imageThumbnail);
        TextView textTitle = findViewById(R.id.textTitle);
        TextView textShortDescription = findViewById(R.id.textShortDescription);
        TextView textDescription = findViewById(R.id.textDescription);
        TextView textDeveloper = findViewById(R.id.textAuthor);

        MaterialButton buttonback = findViewById(R.id.buttonselesai);

        // Setel data ke elemen-elemen tampilan
        Glide.with(this)
                .load(gameDetail.getThumbnail())
                .placeholder(R.drawable.not_available)
                .into(imageThumbnail);

        textTitle.setText(gameDetail.getTitle());
        textShortDescription.setText(gameDetail.getShort_description());
        textDescription.setText(gameDetail.getDescription());
        textDeveloper.setText(gameDetail.getDeveloper());

        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}