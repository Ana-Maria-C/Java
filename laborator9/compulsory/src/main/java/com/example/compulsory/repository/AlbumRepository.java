package com.example.compulsory.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import com.example.compulsory.entity.Album;

public class AlbumRepository {
    private static EntityManagerFactory entityManagerFactory;
    private static AlbumRepository instance;
    private EntityManagerFactory emf;

    public AlbumRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public AlbumRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
    }

    public static AlbumRepository getInstance() {
        if (instance == null) {
            instance = new AlbumRepository();
        }
        return instance;
    }

    public void create(Album album) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(album);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Album findById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Album album = entityManager.find(Album.class, id);
        entityManager.close();
        if (album == null) {
            throw new EntityNotFoundException("Album with id " + id + " not found.");
        }
        return album;
    }

    public List<Album> findByName(String namePattern) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Album> albums = entityManager.createNamedQuery("Album.findByName", Album.class)
                .setParameter("namePattern", "%" + namePattern + "%")
                .getResultList();
        entityManager.close();
        return albums;
    }
}
