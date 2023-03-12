package com.example.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import com.example.movie.repository.MovieRepository;
import com.example.movie.model.Movie;
import com.example.movie.model.MovieRowMapper;
@Service 
public class MovieH2Service implements MovieRepository{
    @Autowired
    private JdbcTemplate db;
    @Override
    public ArrayList<Movie> getMovies(){
        List<Movie> moviesall = db.query("select * from movieList" , new MovieRowMapper());
        ArrayList<Movie> movie = new ArrayList<>(moviesall);
        return movie;
    }
    @Override
    public Movie getMovieById(int movieId){
        try{
            Movie movie = db.queryForObject("select * from movieList where movieId = ?", new MovieRowMapper(),movieId);
            return movie;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public Movie addMovie(Movie movie){
        // db.update("insert into team(playerName , jerseyNumber,role) values(player.getPlayerName(),player.getJerseyNumber(),player.getRole())");
        // return db.queryForObject("select * from team where playerName = ? and jerseyNumber = ? and role = ?",new PlayerRowMapper(),player.getPlayerName(),player.getJerseyNumber(),player.getRole());
        String sql = "INSERT INTO movieList (movieName, leadActor) VALUES (?, ?)";  
        db.update(sql, movie.getMovieName(), movie.getLeadActor());
        Movie movieadd = db.queryForObject("select * from movieList where movieName = ? and leadActor = ?",new MovieRowMapper(),movie.getMovieName(),movie.getLeadActor());
        return movieadd;
        
    }
    @Override
    public Movie updateMovie(int movieId , Movie movie){
        if(movie.getMovieName()!=null){
            db.update("update movieList set movieName = ? where movieId = ?",movie.getMovieName() , movieId);
        }
        if(movie.getLeadActor()!=null){
            db.update("update movieList set leadActor = ? where movieId = ?",movie.getLeadActor() , movieId);
        }
        return getMovieById(movieId);
    }
    @Override
    public void deleteMovie(int movieId){
        db.update("delete from movieList where movieId = ?",movieId);
    }

}