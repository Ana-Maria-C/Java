import com.example.homework.entity.Album;
import com.example.homework.entity.Artist;
import com.example.homework.repository.AlbumRepository;
import com.example.homework.repository.ArtistRepository;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        /**
         * create EntityManagerFactory
         */
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MusicAlbumsPU");

        /**
        *   create repositories
        */

        ArtistRepository artistRepository = new ArtistRepository(emf);
        AlbumRepository albumRepository = new AlbumRepository(emf);

        /**
         generate and insert 1000 artists and albums
         */
        long startTime = System.nanoTime();
        int releaseYear=1019;

        for (int i = 0; i < 1000; i++) {
            // create fake artist
            Artist artist = new Artist("Artist " + i);

            // persist artist
            artistRepository.create(artist); // persist artist to database

            // create fake album
            Album album = new Album("Album " + i, artist, releaseYear);

            // persist album
            albumRepository.create(album);
            releaseYear++;
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);

        /**
         *
         execution time
          */
        System.out.println("Execution time: " + duration + " nanoseconds");

        /**
        *  close EntityManagerFactory
        */
        emf.close();

    }
}
