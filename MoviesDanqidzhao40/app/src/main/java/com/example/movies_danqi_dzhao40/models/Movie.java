package com.example.movies_danqi_dzhao40.models;

import com.google.gson.annotations.SerializedName;

public class Movie {
    /*{
"adult":false,
"backdrop_path":"/p1F51Lvj3sMopG948F5HsBbl43C.jpg",
"genre_ids":[],
"id":616037,
"original_language":"en",
"original_title":"Thor: Love and Thunder",
"overview":"After his retirement is interrupted by Gorr the God Butcher, a galactic killer who seeks the extinction of the gods, Thor enlists the help of King Valkyrie, Korg, and ex-girlfriend Jane Foster, who now inexplicably wields Mjolnir as the Mighty Thor. Together they embark upon a harrowing cosmic adventure to uncover the mystery of the God Butcher’s vengeance and stop him before it’s too late.",
"popularity":12294.551,
"poster_path":"/pIkRyD18kl4FhoCNQuWxWu5cBLM.jpg",
"release_date":"2022-07-08",
"title":"Thor: Love and Thunder",
"video":false,
"vote_average":6.7,
"vote_count":1574
}*/

    @SerializedName("backdrop_path")
    private String imageUrl;
    @SerializedName("title")
    private String name;
    @SerializedName("vote_average")
    private double rating;
    @SerializedName("release_date")
    private String releaseDate;
    private String overview;
    private int id;

    public String getImageUrl() {
        return "https://image.tmdb.org/t/p/w500"+imageUrl;
    }

    public String getName() {
        return name;
    }

    public double getRating() {
        return rating * 10;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "imageUrl='" + imageUrl + '\'' +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", releaseDate='" + releaseDate + '\'' +
                ", overview='" + overview + '\'' +
                '}';
    }
}
