package com.moviefetcher.api;

import com.moviefetcher.application.Movie;
import com.moviefetcher.application.MovieFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/trending")
public class MovieController {

    private final MovieFetcher fetcher;

    @Autowired
    public MovieController(MovieFetcher fetcher) {
        this.fetcher = fetcher;
    }

    @GetMapping("/movie/day")
    public List<Movie> getTrendingByWeek() {
        return fetcher.fetchMoviesByWeek();
    }
}
