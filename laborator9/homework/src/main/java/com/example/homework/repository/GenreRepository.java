package com.example.compulsory.repository;

import com.example.compulsory.entity.Genre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class GenreRepository extends AbstractRepository<Genre, Integer> {
    public GenreRepository(EntityManagerFactory emf) {
        super(emf, Genre.class);
    }
}