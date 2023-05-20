package com.example.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
       RestTemplate restTemplate = new RestTemplate();

        // Add a new player
        Player newPlayer = new Player();
        newPlayer.setName("Maria");
        Player addedPlayer = restTemplate.postForObject("http://localhost:8090/api/players", newPlayer, Player.class);
        System.out.println("Added Player: " + addedPlayer);

        // Update player's name
        Long playerId = 1L;
        String updatedName = "Constantin";
        Player updatedPlayer = new Player();
        updatedPlayer.setName(updatedName);
        restTemplate.put("http://localhost:8090/api/players/" + playerId, updatedPlayer);

        // Delete a player
        Long playerIdToDelete = 2L;
        restTemplate.delete("http://localhost:8090/api/players/" + playerIdToDelete);


    }

}
