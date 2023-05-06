package com.example.compulsory.repository;

import java.util.List;
import jakarta.persistence.*;

import com.example.compulsory.entity.Album;

public class AlbumRepository extends AbstractRepository<Album, Integer> {
    public AlbumRepository(EntityManagerFactory emf) {
        super(emf, Album.class);
    }
}