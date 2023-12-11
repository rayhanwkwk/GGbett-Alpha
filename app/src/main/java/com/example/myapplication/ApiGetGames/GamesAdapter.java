package com.example.myapplication.ApiGetGames;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import java.util.List;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolder> {
    private List<GamesHeadlines> gamesList;
    private Context context;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(GamesHeadlines game);
    }

    public GamesAdapter(Context context, List<GamesHeadlines> gamesList) {
        this.context = context;
        this.gamesList = gamesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.headline_list_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GamesHeadlines game = gamesList.get(position);

        holder.textTitle.setText(game.getTitle());
        holder.textShortDescription.setText(game.getShort_description());

        // Menggunakan Glide untuk memuat gambar dari URL
        Glide.with(context)
                .load(game.getThumbnail())
                .placeholder(R.drawable.not_available)
                .into(holder.imgHeadline);

        // Setel listener klik
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(game);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return Math.min(20, gamesList.size());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle;
        TextView textShortDescription;
        ImageView imgHeadline;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.text_title);
            textShortDescription = itemView.findViewById(R.id.text_short_description);
            imgHeadline = itemView.findViewById(R.id.img_headline);
        }

    }
}
