package com.moviefetcher.infrastructure.tmdb;

import com.moviefetcher.infrastructure.tmdb.json.TmdbMovie;
import com.moviefetcher.infrastructure.tmdb.json.TmdbTrending;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class TmdbClientImpl implements TmdbClient {

    private static final String BASE_URL = "https://api.themoviedb.org/3";
    private static final String TRENDING_WEEK_URL = "/trending/movie/day";
    private static final String GENRES_URL = "/genre/movie/list";

    private static final String API_KEY_PLACEHOLDER = "?api_key=";
    private final String apiKeyQueryParam;
    private static final String PAGE_PLACEHOLDER = "&page=";
    private static final int NUMBER_OF_REQUESTS = 5;

    private HttpHeaders headers;

    @Autowired
    public TmdbClientImpl(@Value("${tmdb.api.key}") String apiKey) {
        this.apiKeyQueryParam = API_KEY_PLACEHOLDER + apiKey;
    }


    @Override
    public List<TmdbMovie> fetchTrendingByWeek() {

        RestTemplate restTemplate = new RestTemplate();

        List<TmdbMovie> results = new ArrayList<>();

        for (int i = 1; i <= NUMBER_OF_REQUESTS; i++) {
            results.addAll(restTemplate.getForEntity(BASE_URL + TRENDING_WEEK_URL + apiKeyQueryParam + PAGE_PLACEHOLDER + i, TmdbTrending.class)
                    .getBody().getResults());

        }
        return results;
    }
}
