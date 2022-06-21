package com.moviefetcher.infrastructure.tmdb;

import com.moviefetcher.infrastructure.tmdb.json.TmdbMovie;
import com.moviefetcher.infrastructure.tmdb.json.TmdbTrending;

import java.util.List;

public interface TmdbClient {

    List<TmdbMovie> fetchTrendingByWeek();

}
