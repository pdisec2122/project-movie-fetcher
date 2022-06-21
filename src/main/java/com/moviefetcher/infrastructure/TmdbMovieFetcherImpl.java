package com.moviefetcher.infrastructure;

import com.moviefetcher.application.Movie;
import com.moviefetcher.application.MovieFetcher;
import com.moviefetcher.infrastructure.tmdb.TmdbClient;
import com.moviefetcher.infrastructure.tmdb.json.TmdbMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TmdbMovieFetcherImpl implements MovieFetcher {

    private final TmdbClient tmdbClient;

    @Autowired
    public TmdbMovieFetcherImpl(TmdbClient tmdbClient) {
        this.tmdbClient = tmdbClient;
    }

    @Override
    public List<Movie> fetchMoviesByWeek() {
        return tmdbClient.fetchTrendingByWeek().stream().map(this::toMovie).collect(Collectors.toList());
    }

    private Movie toMovie(TmdbMovie tmdbMovie) {
        Movie.Builder builder = Movie.Builder.with();

        builder.id(tmdbMovie.getId());
        builder.backDropPath(tmdbMovie.getBackDropPath());
        builder.posterPath(tmdbMovie.getPosterPath());
        builder.overview(tmdbMovie.getOverview());
        builder.genres(tmdbMovie.getGenres());
        builder.originalTitle(tmdbMovie.getOriginalTitle());
        builder.name(tmdbMovie.getName());
        builder.originalName(tmdbMovie.getOriginalName());
        builder.title(tmdbMovie.getTitle());
        builder.voteCount(tmdbMovie.getVoteCount());
        builder.voteAverage(tmdbMovie.getVoteAverage());

        return builder.build();
    }

}
