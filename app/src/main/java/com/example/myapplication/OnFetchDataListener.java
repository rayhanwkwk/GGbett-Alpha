package com.example.myapplication;

import com.example.myapplication.Models.GamesHeadlines;
import com.google.android.gms.common.util.Strings;

import java.util.List;

public interface OnFetchDataListener<ApiResponse>{
    void onFetchData(List<GamesHeadlines> gamesHeadlines, String message);
    void onError(Strings message);
    void onError(String error);
}
