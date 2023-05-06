package com.example.compulsory.repository;

import com.example.compulsory.entity.Artist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ArtistRepository extends AbstractRepository<Artist, Integer> {
    public ArtistRepository(EntityManagerFactory emf) {
        super(emf, Artist.class);
    }
}