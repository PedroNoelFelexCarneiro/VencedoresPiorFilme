package com.texoit.controllers;

import com.texoit.models.MovieList;
import com.texoit.repositories.MovieListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Pedro
 */
@RestController
@RequestMapping("/lista")
public class MovieListResource {

    @Autowired
    private MovieListRepository movieListRepository;

    @GetMapping(produces = "application/json")
    public @ResponseBody
    Iterable<MovieList> getListaDeFilmes() {
        Iterable<MovieList> lista = movieListRepository.findAll();
        return lista;
    }

    @PostMapping(produces = "application/json",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void cadastrarListaDeFilmes(MovieList movieList) {
        movieListRepository.save(movieList);
    }
}
