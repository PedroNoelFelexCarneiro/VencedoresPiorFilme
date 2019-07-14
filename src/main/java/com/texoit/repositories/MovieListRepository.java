package com.texoit.repositories;

import com.texoit.models.MovieList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Pedro
 */
@Repository
public interface MovieListRepository extends JpaRepository<MovieList, Integer> {}
