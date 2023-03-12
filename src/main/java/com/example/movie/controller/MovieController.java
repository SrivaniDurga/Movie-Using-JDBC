
package com.example.movie.controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.example.movie.model.Movie;
import com.example.movie.service.MovieH2Service;
import org.springframework.beans.factory.annotation.Autowired;
@RestController
public class MovieController{
    @Autowired
    public MovieH2Service service;
    @GetMapping("/movies")
    public ArrayList<Movie> getMovie(){
        return service.getMovies();
    }
    @GetMapping("/movies/{movieId}")
    public Movie getMovieById(@PathVariable ("movieId") int movieId){
        return service.getMovieById(movieId);
    }
    @PostMapping("/movies")
    public Movie addMovepost(@RequestBody Movie movie ){
        return service.addMovie(movie);
    }
    @PutMapping("/movies/{movieId}")
    public Movie updateMovie(@PathVariable ("movieId") int movieId , @RequestBody Movie movie){
        return service.updateMovie(movieId , movie);
    }
    @DeleteMapping("/movies/{movieId}")
    public void deleteMovie(@PathVariable ("movieId") int movieId){
        service.deleteMovie(movieId);
    }

}