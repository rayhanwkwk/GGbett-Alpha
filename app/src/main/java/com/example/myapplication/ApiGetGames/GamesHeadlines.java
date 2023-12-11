package com.example.myapplication.ApiGetGames;

import com.google.gson.annotations.SerializedName;

public class GamesHeadlines {
    @SerializedName("id")
    public int id;

    @SerializedName("title")
    public String title;
    @SerializedName("thumbnail")
    public String thumbnail;
    @SerializedName("short_description")
    public String short_description;
    @SerializedName("game_url")
    public String game_url;
    @SerializedName("genre")
    public String genre;
    @SerializedName("platfrom")
    public String platfrom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getGame_url() {
        return game_url;
    }

    public void setGame_url(String game_url) {
        this.game_url = game_url;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPlatfrom() {
        return platfrom;
    }

    public void setPlatfrom(String platfrom) {
        this.platfrom = platfrom;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getFreetogame_profile_url() {
        return freetogame_profile_url;
    }

    public void setFreetogame_profile_url(String freetogame_profile_url) {
        this.freetogame_profile_url = freetogame_profile_url;
    }

    @SerializedName("publisher")
    public String publisher;
    @SerializedName("developer")
    public String developer;
    @SerializedName("release_date")
    public String release_date;
    @SerializedName("freetogame_profile_url")
    public String freetogame_profile_url;
}
