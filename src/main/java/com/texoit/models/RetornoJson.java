/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.texoit.models;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Pedro
 */
public class RetornoJson implements Serializable {

    private List<Winner> listWinner;
    private List<Winner> min;
    private List<Winner> max;

    public RetornoJson() {
    }

    public RetornoJson(List<Winner> listWinner) {
        this.listWinner = listWinner;
        calcularMin();
        calcularMax();
    }

    public void setMin(List<Winner> min) {
        this.min = min;
    }

    public List<Winner> getMin() {
        return min;
    }

    public void setMax(List<Winner> max) {
        this.max = max;
    }

    public List<Winner> getMax() {
        return max;
    }

    private void calcularMin() {
        Integer menorIntervalo = listWinner
                .stream()
                .map(Winner::getInterval)
                .min((a1, a2) -> a1.compareTo(a2))
                .orElse(0);

        min = listWinner
                .stream()
                .filter(p -> menorIntervalo.equals(p.getInterval()))
                .collect(Collectors.toList());
    }

    private void calcularMax() {
        Integer maiorIntervalo = listWinner
                .stream()
                .map(Winner::getInterval)
                .max((a1, a2) -> a1.compareTo(a2))
                .orElse(0);

        max = listWinner
                .stream()
                .filter(p -> maiorIntervalo.equals(p.getInterval()))
                .collect(Collectors.toList());
    }

}
