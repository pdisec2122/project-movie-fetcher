package com.moviefetcher.infrastructure;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TmdbMovieFetcherImplTest {

    @Autowired
    private TmdbMovieFetcherImpl fetcher;

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Test
    public void canGetTrendingMovies() {

        System.out.println(fetcher.fetchMoviesByWeek());


    }

}