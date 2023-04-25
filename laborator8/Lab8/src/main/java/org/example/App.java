package org.example;

import java.sql.SQLException;

/**
 * Hello world!
 */
public class App {
    public static void main(String args[]) throws SQLException {
        try {

            ArtistDAO artists = new ArtistDAO();
            artists.create("Pink Floyd");
            artists.create("Michael Jackson");
            GenreDAO genres = new GenreDAO();
            genres.create("Rock");
            genres.create("Funk");
            genres.create("Soul");
            genres.create("Pop");

            Database.getConnection().commit();
            AlbumDAO albums = new AlbumDAO();
            albums.create(1979, "The Wall", "Pink Floyd", "Rock");
            // findByName
            albums.create(1982, "Thriller", "Michael Jackson", "Funk,Soul,Pop");
            Database.getConnection().commit();

            // TODO: print all the albums in the database

            albums.Print();
            Database.getConnection().close();
        } catch (SQLException e) {
            System.err.println(e);

            Database.rollback();
        }
    }
}
