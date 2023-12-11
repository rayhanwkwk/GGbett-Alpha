package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.ApiGetGames.ApiClient;
import com.example.myapplication.ApiGetGames.ApiServices;
import com.example.myapplication.ApiGetGames.GamesAdapter;
import com.example.myapplication.ApiGetGames.GamesHeadlines;
import com.example.myapplication.Models.ApiResponse;
import com.example.myapplication.Models.Detail_Headliness;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.android.gms.common.util.Strings;
import com.example.myapplication.CustomAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new MainFragment()).commit();
        bottomBar = findViewById(R.id.bottomBar);
        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment fragment = null;

                if (id == R.id.menuHome) {
                    fragment = new MainFragment();
                } else if (id == R.id.menuNews) {
                    fragment = new NewsFragment();
                } else if (id == R.id.menuProfile) {
                    fragment = new MaintanceFragment();
                }

                if (fragment != null) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frameLayout, fragment).commit();
                    return true;
                }

                return false;
            }
        });
    }
}