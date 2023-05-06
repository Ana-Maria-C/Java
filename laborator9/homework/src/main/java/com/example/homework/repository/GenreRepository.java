package com.example.homework.repository;

import com.example.homework.entity.Genre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class GenreRepository extends AbstractRepository<Genre, Integer> {
    public GenreRepository(EntityManagerFactory emf) {
        super(emf, Genre.class);
    }
}