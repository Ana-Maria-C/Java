package com.example.homework.repository;

import com.example.homework.entity.Artist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ArtistRepository extends AbstractRepository<Artist, Integer> {
    public ArtistRepository(EntityManagerFactory emf) {
        super(emf, Artist.class);
    }
}