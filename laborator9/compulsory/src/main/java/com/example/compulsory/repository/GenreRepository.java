package com.example.compulsory.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import com.example.compulsory.entity.Genre;

public class GenreRepository {
    private EntityManager entityManager;

    public GenreRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Genre genre) {
        entityManager.getTransaction().begin();
        entityManager.persist(genre);
        entityManager.getTransaction().commit();
    }

    public Genre findById(Long id) {
        return entityManager.find(Genre.class, id);
    }

    public List<Genre> findByName(String namePattern) {
        TypedQuery<Genre> query = entityManager.createNamedQuery("Genre.findByName", Genre.class);
        query.setParameter("name", "%" + namePattern + "%");
        return query.getResultList();
    }
}
