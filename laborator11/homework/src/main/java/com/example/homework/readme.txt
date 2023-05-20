Clasa Player
    -> reprezintă o entitate în baza de date;
    -> numele tabelei în care se va face maparea este "player"
    -> câmpurile id și name din clasa Player sunt mapate către coloanele "id" și "name" din tabela "player" prin intermediul adnotărilor @Id și @Column.

Clasa Game
    -> reprezintă o entitate în baza de date;
    -> numele tabelei în care se va face maparea este "game"
    -> câmpurile id și name din clasa Game sunt mapate către coloanele "id" și "name" din tabela "game" prin intermediul adnotărilor @Id și @Column.



Clasa PlayerRepository + Clasa GameRepository
    -> se folosesc  pentru a accesa și interacționa cu baza de date;



Clasa PlayerController
    -> clasa va gestiona cererile HTTP și va returna răspunsuri HTTP;
    -> metoda getAllPlayers() este marcată cu adnotarea @GetMapping("/players"), ceea ce înseamnă că va răspunde la cererile HTTP GET la ruta "/api/players";
        -> această metodă utilizează playerRepository pentru a obține toți jucătorii din baza de date prin apelul metodei findAll() și returnează rezultatul sub forma unei liste de obiecte Player;
    ->metoda addPlayer() este mapată la ruta POST "/api/players" și primește un obiect Player ca parametru în corpul cererii (RequestBody);
        -> aceasta salvează noul jucător utilizând metoda save() a playerRepository și returnează jucătorul salvat;
    -> metoda updatePlayer() este mapată la ruta PUT "/api/players/{id}" și primește doi parametri: id în calea cererii și name ca parametru de interogare (RequestParam);
        -> aceasta caută jucătorul cu id-ul specificat utilizând metoda findById() a playerRepository, actualizează numele jucătorului cu name și salvează modificările utilizând metoda save();
        -> dacă jucătorul nu este găsit, se aruncă o excepție EntityNotFoundException.
    -> metoda deletePlayer() este mapată la ruta DELETE "/api/player/{id}" și primește id-ul jucătorului de șters în calea cererii;
        -> aceasta utilizează metoda deleteById() a playerRepository pentru a șterge jucătorul cu id-ul specificat.



Clasa GameController
    -> clasa va gestiona cererile HTTP și va returna răspunsuri HTTP;
    -> metoda getAllGames() este marcată cu adnotarea @GetMapping("/games"), ceea ce înseamnă că va răspunde la cererile HTTP GET la ruta "/api/games";
        -> această metodă utilizează gameRepository pentru a obține toate jocurile din baza de date prin apelul metodei findAll() și returnează rezultatul sub forma unei liste de obiecte Game;
