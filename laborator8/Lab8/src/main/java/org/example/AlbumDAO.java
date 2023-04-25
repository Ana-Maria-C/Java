package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AlbumDAO {

    public void create(int releaseYear, String title, String artist, String genres) throws SQLException {
        Connection con = Database.getConnection();
        int idArtist = new ArtistDAO().findByName(artist);
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into albums (release_year,title,artist_id) values (?,?,?)")) {
            pstmt.setInt(1, releaseYear);
            pstmt.setString(2, title);
            pstmt.setInt(3, idArtist);

            pstmt.executeUpdate();
        }

        int idAlbum = new AlbumDAO().findByName(title);

        String[] genresVec = genres.split(",");
        for (String g : genresVec) {
            int idGenres = new GenreDAO().findByName(g);
            try (PreparedStatement pstmt = con.prepareStatement(
                    "insert into album_genre (album_id,genre_id) values (?,?)")) {
                pstmt.setInt(1, idAlbum);
                pstmt.setInt(2, idGenres);
                pstmt.executeUpdate();
            }
        }
    }

    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from albums where title='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    public String findById(int id) throws SQLException {
        // TODO
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select name from albums where id='" + id + "'")) {
            return rs.next() ? rs.getString(1) : null;
        }
    }

    public void Print() throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from albums")) {
            while (rs.next()) {
                System.out.println(
                        rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
            }
        }
    }
}
