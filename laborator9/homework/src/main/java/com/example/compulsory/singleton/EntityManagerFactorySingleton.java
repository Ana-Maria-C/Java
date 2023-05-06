package com.example.compulsory.singleton;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactorySingleton {
    private static final String PERSISTENCE_UNIT_NAME = "examplePU";
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
        emf=Persistence.createEntityManagerFactory("ExamplePU");
    }

    public static void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
