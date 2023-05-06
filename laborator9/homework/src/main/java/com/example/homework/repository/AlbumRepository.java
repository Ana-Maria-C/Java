package com.example.homework.repository;
import java.util.List;
import jakarta.persistence.*;

import com.example.homework.entity.Album;

public class AlbumRepository extends AbstractRepository<Album, Integer> {
    public AlbumRepository(EntityManagerFactory emf) {
        super(emf, Album.class);
    }
}