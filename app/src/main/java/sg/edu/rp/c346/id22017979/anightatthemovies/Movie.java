package sg.edu.rp.c346.id22017979.anightatthemovies;

import java.io.Serializable;

public class Movie implements Serializable {
    private int id;
    private String title;
    private String genre;
    private String year;
    private String rating;

    public Movie(int id, String title, String genre, String year, String rating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setMovie(String title,String genre,String year,String rating){
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rating = rating;

    }
}
