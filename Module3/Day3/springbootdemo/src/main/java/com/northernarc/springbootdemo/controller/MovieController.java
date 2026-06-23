package com.northernarc.springbootdemo.controller;

import com.northernarc.springbootdemo.dao.MovieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    @Autowired
    MovieDao movieDao;
    @RequestMapping("/getMovieById")
    public void getMovieById() {
    }

    @RequestMapping("/save")
    public String save() {
        return "Movie saved successfully!";
    }

    @RequestMapping("/update")
    public String update() {
        return "Movie updated successfully!";

    }
    @RequestMapping("/delete")
    public String delete() {
        return "Movie deleted successfully!";
    }
}
