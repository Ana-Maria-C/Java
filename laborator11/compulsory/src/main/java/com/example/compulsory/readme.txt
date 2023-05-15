Clasa Player
    -> reprezintă o entitate în baza de date;
    -> numele tabelei în care se va face maparea este "player"
    -> câmpurile id și name din clasa Player sunt mapate către coloanele "id" și "name" din tabela "player" prin intermediul adnotărilor @Id și @Column.

Clasa PlayerRepository:
    -> se foloseste  pentru a accesa și interacționa cu baza de date;

Clasa PlayerController
    -> clasa va gestiona cererile HTTP și va returna răspunsuri HTTP;
    -> metoda getAllPlayers() este marcată cu adnotarea @GetMapping("/players"), ceea ce înseamnă că va răspunde la cererile HTTP GET la ruta "/api/players";
        -> această metodă utilizează playerRepository pentru a obține toți jucătorii din baza de date prin apelul metodei findAll() și returnează rezultatul sub forma unei liste de obiecte Player;