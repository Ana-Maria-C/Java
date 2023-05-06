package com.example.homework.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@NamedQuery(
        name = "Genre.findByName",
        query = "SELECT g FROM Genre g WHERE g.name LIKE :name"
)
@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "genres")
    private Set<Album> albums = new HashSet<>();

    public Genre(Long id, String name, Set<Album> albums) {
        this.id = id;
        this.name = name;
        this.albums = albums;
    }
 public Genre(String name){
        this.name=name;
 }
    public Genre()
    {

    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }
}
