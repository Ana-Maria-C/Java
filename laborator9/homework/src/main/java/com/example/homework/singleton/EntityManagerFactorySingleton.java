package com.example.homework.singleton;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactorySingleton {
    private static final String PERSISTENCE_UNIT_NAME = "MusicAlbumsPU";
    private static EntityManagerFactory emf;

    private EntityManagerFactorySingleton() {}

    public static EntityManagerFactory getInstance() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return emf;
    }
    private static void createEntityManagerFactory ()
    {
        emf = Persistence.createEntityManagerFactory("MusicAlbumsPU");
    }

    public static void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
