package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Models.GamesHeadlines;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private Context context;
    private List<GamesHeadlines> headlines;

    public CustomAdapter(Context context, List<GamesHeadlines> headlines) {
        this.context = context;
        this.headlines = headlines;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.headline_list_items, parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        holder.text_title.setText(headlines.get(position).getTitle());
        holder.text_source.setText(headlines.get(position).getSource().getId());

        if (headlines.get(position).getThumbnail()!=null){
            Picasso.get().load(headlines.get(position).getThumbnail()).into(holder.img_headline);
        }
    }
    @Override
    public int getItemCount() {
        return headlines.size();
    }
}
