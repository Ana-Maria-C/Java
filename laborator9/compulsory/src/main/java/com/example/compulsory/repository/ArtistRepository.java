package com.example.compulsory.repository;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import com.example.compulsory.entity.Artist;
import com.example.compulsory.singleton.EntityManagerFactorySingleton;

public class ArtistRepository {

    private static EntityManagerFactory entityManagerFactory;
    private static ArtistRepository instance;

    private ArtistRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
    }

    public static ArtistRepository getInstance() {
        if (instance == null) {
            instance = new ArtistRepository();
        }
        return instance;
    }

    public void create(Artist artist) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(artist);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Artist findById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Artist artist = entityManager.find(Artist.class, id);
        entityManager.close();
        if (artist == null) {
            throw new EntityNotFoundException("Artist with id " + id + " not found.");
        }
        return artist;
    }

    public List<Artist> findByName(String namePattern) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Artist> artists = entityManager.createNamedQuery("Artist.findByName", Artist.class)
                .setParameter("namePattern", "%" + namePattern + "%")
                .getResultList();
        entityManager.close();
        return artists;
    }
}
