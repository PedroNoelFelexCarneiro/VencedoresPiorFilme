package com.texoit.controllers;

import com.texoit.models.MovieList;
import com.texoit.models.RetornoJson;
import com.texoit.models.Winner;
import com.texoit.repositories.MovieListRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Pedro
 */
@RestController
@RequestMapping("/intervalo_premios")
public class WinnerResource {

    @Autowired
    private MovieListRepository movieListRepository;

    @GetMapping(produces = "application/json")
    public @ResponseBody
    RetornoJson getListaDeFilmes() {
        List<MovieList> lista1 = movieListRepository.findAll().stream().filter(p -> "yes".equals(p.getWinner())).collect(Collectors.toList());
        List<MovieList> lista = lista1.stream().filter(p -> "yes".equals(p.getWinner())).collect(Collectors.toList());
        lista.sort((a1, a2) -> a1.getYear().compareTo(a2.getYear()));
        List<Winner> listaVencedores = new ArrayList<>();
        for (MovieList movieList : lista) {
            Winner winner;
            String[] producers = movieList.getProducers().split(",|and");
            for (final String producer : producers) {
                winner = listaVencedores.stream().filter(p -> p.getProducer().equals(producer.trim())).findFirst().orElse(new Winner());
                winner.setProducer(producer.trim());
                if (winner.getPreviousWin() == null) {
                    winner.setPreviousWin(Integer.valueOf(movieList.getYear()));
                    listaVencedores.add(winner);
                } else {
                    winner.setFollowingWin(Integer.valueOf(movieList.getYear()));
                }
            }
        }
        listaVencedores.sort((a1, a2) -> a1.getProducer().compareTo(a2.getProducer()));
        
        RetornoJson retorno = new RetornoJson(listaVencedores.stream().filter(p -> p.getInterval() != null).collect(Collectors.toList()));
        return retorno;
    }
}
