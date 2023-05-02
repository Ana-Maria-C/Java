package com.example.compulsory.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@NamedQuery(
        name = "Genre.findByName",
        query = "SELECT a FROM Artist a WHERE a.name LIKE :name"
)
@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "artist")
    private List<Album> albums = new ArrayList<>();

    public Artist(Long id, String name, List<Album> albums) {
        this.id = id;
        this.name = name;
        this.albums = albums;
    }
    public Artist( String name) {

        this.name = name;
    }

    public Artist() {

    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
