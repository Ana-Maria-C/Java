Clasa AlbumDAO:

-> oferă acces la date pentru un album;
-> oferă metode pentru a crea, găsi după nume, găsi după ID și a imprima albume dintr-o bază de date utilizând JDBC
-> are o metodă de instanță numită "create" care primește ca parametri anul lansării, titlul, artistul și genurile albumului și inserează aceste informații în baza de date;
-> metoda găsește ID-ul artistului și inserează legătura dintre album și genuri în tabelele "albums" și "album_genre";
-<  contine metoda de căutare, "findByName", care caută un album după nume și "findById", care caută un album după ID in baze de date;
-> metoda "Print" care afișează toate albumele din baza de date;
-> aceste metode utilizează conexiunea la baza de date prin intermediul clasei "Database";


Clasa  GenreDAO:

-> obiect de acces la date pentru un de gen muzical
-> Metoda "create" primește numele genului și îl introduce în baza de date prin intermediul unui obiect PreparedStatement;
-> Metoda "findByName" primește numele genului și caută în baza de date ID-ul corespunzător folosind obiectul Statement și ResultSet;
-> Metoda "findById" primește ID-ul genului și caută în baza de date numele corespunzător;
-> aceste metode utilizează conexiunea la baza de date prin intermediul clasei "Database";



Clasa Database:

-> este responsabilă de crearea și gestionarea conexiunii la baza de date Oracle;
->  conține o metodă statică numită getConnection() care returnează conexiunea curentă la baza de date;
-> dacă conexiunea nu există încă, metoda va apela o altă metodă statică createConnection() pentru a crea conexiunea;
-> metoda createConnection() folosește clasa DriverManager pentru a încărca driverul Oracle JDBC și pentru a crea conexiunea la baza de date;
->  conține și două metode suplimentare, closeConnection() și rollback(), pentru a închide conexiunea și pentru a efectua rollback în cazul în care este necesar;


Clasa  ArtistDAO:

-> oferă acces la date pentru un artist;
-> Metoda create foloseste un PreparedStatement si este utilizată pentru a insera un nou artist în baza de date;
-> metoda findByName este utilizată pentru a căuta un artist după nume;
-> metoda findById este utilizată pentru a căuta un artist după ID;\
-> r metodele findByName și findById utilizează Statement și ResultSet pentru a căuta artiștii în baza de date;


Clasa App:
-> creează obiecte ale clasei AlbumDAO și se adaugă înregistrări noi în baza de date utilizând metoda create
-> se afișează toate albumele din baza de date utilizând metoda Print()
-> se închide conexiunea la baza de date utilizând getConnection().close()


