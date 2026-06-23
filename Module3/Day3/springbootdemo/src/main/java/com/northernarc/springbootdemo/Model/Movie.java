package com.northernarc.springbootdemo.Model;

public class Movie {
    private String title;
    private String director;
    private int releaseYear;
    private String Hero;
    private int id;
    public Movie () {

    }
    public Movie(String title,int id,int releaseYear,String director,String Hero) {
        this.title = title;
        this.id=id;
        this.director = director;
        this.releaseYear = releaseYear;
        this.Hero = Hero;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getHero() {
        return Hero;
    }

    public void setHero(String hero) {
        Hero = hero;
    }

}
