package com.northernarc.springbootdemo.dao;


import com.northernarc.springbootdemo.Model.Movie;

public interface MovieDao {
    public void save(Movie movie);
    public Movie getMovieById(int id);
    public void update(Movie movie);
    public void delete(int id);
}
