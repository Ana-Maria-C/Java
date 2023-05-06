package com.example.compulsory.entity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@NamedQuery(
        name = "Album.findByName",
        query = "SELECT a FROM Album a WHERE a.title LIKE :name"
)
@Entity
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "release_year")
    private Integer releaseYear;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToMany
    @JoinTable(name = "album_genre",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new HashSet<>();

    public Album(Long id, int releaseYear, String title, Artist artist, Set<Genre> genres) {
        this.id = id;
        this.releaseYear = releaseYear;
        this.title = title;
        this.artist = artist;
        this.genres = genres;
    }
    public Album (String title, Artist artist, int releaseYear)
    {
        this.title=title;
        this.artist=artist;
        this.releaseYear=releaseYear;
    }
    public Album()
    {

    }

    public Long getId() {
        return id;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public Artist getArtist() {
        return artist;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public void addGenre(Genre rock) {

    }
}
