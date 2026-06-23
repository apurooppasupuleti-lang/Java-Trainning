package com.northernarc.springbootdemo.dao;

import com.northernarc.springbootdemo.Model.Movie;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.Map;

@Component
public class MovieDaoImpl implements MovieDao {

    private final Map<Integer, Movie> movieStore = new HashMap<>();

    @Override
    public void save(Movie movie) {
        movieStore.put(movie.getId(), movie);
    }

    @Override
    public Movie getMovieById(int id) {
        return movieStore.get(id);
    }

    @Override
    public void update(Movie movie) {
        if (movieStore.containsKey(movie.getId())) {
            movieStore.put(movie.getId(), movie);
        } else {
            throw new IllegalArgumentException("Movie with id " + movie.getId() + " not found");
        }
    }

    @Override
    public void delete(int id) {
        movieStore.remove(id);
    }
}