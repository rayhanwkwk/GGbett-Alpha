package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.ApiGetGames.ApiClient;
import com.example.myapplication.ApiGetGames.ApiServices;
import com.example.myapplication.ApiGetGames.GamesAdapter;
import com.example.myapplication.ApiGetGames.GamesHeadlines;
import com.example.myapplication.Models.Detail_Headliness;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {

    private TextView textTitle;
    private TextView textShortDescription;
    private ImageView imgHeadline;

    private String TAG = "MainActivity";

    RecyclerView recyclerView;

    GamesAdapter gamesAdapter;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        FetchDataApiGames();

    }

    private void FetchDataApiGames() {
        ApiServices apiService = ApiClient.getRetrofitInstance().create(ApiServices.class);
        Call<List<GamesHeadlines>> call = apiService.getGames();

        call.enqueue(new Callback<List<GamesHeadlines>>() {
            @Override
            public void onResponse(Call<List<GamesHeadlines>> call, Response<List<GamesHeadlines>> response) {
                if (response.isSuccessful()) {
                    List<GamesHeadlines> gamesHeadlines = response.body();

                    gamesAdapter = new GamesAdapter(getActivity(), gamesHeadlines);
                    recyclerView.setAdapter(gamesAdapter);

                    gamesAdapter.setOnItemClickListener(new GamesAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(GamesHeadlines game) {
                            openDetailActivity(game.getId());
                        }
                    });

                } else {
                    Log.d(TAG, "Error" + response.toString());
                }
            }

            @Override
            public void onFailure(Call<List<GamesHeadlines>> call, Throwable t) {
                Log.d(TAG, "Error Fastal" + t.getMessage().toString());
            }
        });
    }

    private void openDetailActivity(int gameId) {
        // Membuka DetailActivity dan meneruskan ID game yang dipilih
        Intent intent = new Intent(getActivity(), Detail_Headliness.class);
        intent.putExtra("id", gameId);
        startActivity(intent);
    }
}