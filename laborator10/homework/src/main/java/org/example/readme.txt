
Clasa GameServer

    ->  este implementarea serverului de jocuri care poate accepta conexiuni de la clienți și poate permite clienților să creeze sau să se alăture unui joc;
    -> metoda start() deschide un serverSocket și așteaptă conexiuni noi; atunci când un client se conectează la server, aceasta creează un obiect de tipul ClientThread și îl adaugă la lista de clienți,apoi, aceasta pornește un fir de execuție pentru comunicarea cu acel client;
    -> metoda stop() este apelată atunci când vrem să oprim serverul de jocuri;
    -> metoda stop() este apelată atunci când dorim să oprim serverul;
    -> metoda isStopped() returnează valoarea variabilei stopped;
    -> metoda removeClient() este utilizată pentru a elimina un client din lista de clienți atunci când acesta se deconectează de la server.


Clasa ClientThread

    -> extinde din clasa Thread și gestionează conexiunile clientilor(creeaza un thread pentru fiecare client) pentru serverul GameServer;
    -> metoda run() a acestei clase gestionează comunicarea între client și server;se setează un timeout de 30 de secunde pentru socket-ul clientului și se deschid fluxurile de intrare și ieșire pentru comunicarea cu clientul
    -> dacă este o excepție de tipul SocketTimeoutException (timeout expirat),se afișează mesajul "GAME OVER:timeout expired!" și se închide conexiunea;
    -> se configurează un obiect PrintWriter pentru a trimite răspunsuri către client și un obiect BufferedReader pentru a citi cererile de la client;
    -> metoda close() este apelată atunci când clientul este deconectat de la server; aceasta închide socketul clientului și înlătură obiectul ClientThread din lista de clienți din GameServer.


Clasa GameClient

    -> reprezintă o implementare a clientului;
    -> fiecare client se conectează la un server de joc pe un anumit port și apoi primește comenzi de la utilizator prin intermediul comenzilor tastaturii;
    -> clientul are patru comenzi disponibile: "create", "join", "move" și "exit";
        -> create:-după intoducerea comenzii, clientul trebuie sa introducă un nume pentru joc, ce va fi folosit apoi ca o cheie unică de identificare(u pot exista doua jocuri cu acelasi nume)
                  -dacă numele intodus este deja atribuit altui joc, clientul va fi atentionat printr-un mesaj si i se va cere sa introducă alt nume
        -> join: -clientul trebuie să intoducă numele jocului la care vrea să se alăture, iar dacă acesta nu este corect, sau dacă jocul are deja 2 jucatori i se va cere un alt nume
        -> move: -clientul trebuie să introducă coordonatele jocului la cafe vrea să participe, iar dacă sunt greșite, sau locul pe tabla este ocupat i se cere sa introducă alte coordonate
    -> comanda "create" este utilizată pentru a crea un joc nou;
    -> comanda "join" este utilizată pentru a se alătura unui joc existent;
    -> comanda "move" este utilizată pentru a efectua o mutare în joc;
    -> omanda "exit" este utilizată pentru a ieși din joc;
    -> !!! toate aceste comenzi au un timp de 30 de secunde pentru a fi introduse,dacă timpul se scurge, jocul este pierdut și se finalizeaza
    -> !!! în plus, comenzile pot fi apelate după o anumită logică: un client trebuie să fie într-un joc pentru a putea face o mutare și dacă a dat join sau create , cu alte cuvinte
            se află deja în joc, nu poate da  create sau join din nou, deoarece face deja parte dintr-un joc în momentul curent.


Clasa Board

    -> este o reprezentare a tablei de joc pentru jocul de Gomoku;
    -> conține metode pentru a verifica starea tablei de joc, inclusiv dacă este plină sau dacă există o poziție câștigătoare pentru un anumit simbol (de exemplu, 'a' sau 'n');
    -> are metode pentru a verifica dacă există o poziție câștigătoare pe linie orizontală, verticală sau diagonală; aceste metode iau în considerare toate combinațiile posibile de 5 piese adiacente, pornind de la o poziție dată pe tablă;
    -> are o matrice bidimensională pentru a reprezenta tabla de joc și o mărime a tablei, care este setată implicit la 15, deoarece jocul Gomoku se joacă pe o tablă de 15x15.


Clasa Game

    -> reprezintă logica jocului; are o instanță a clasei "Board" și o listă de jucători;
    -> constructorul primește două parametri, numele jocului și limita de timp pentru fiecare jucător;
    -> există metode pentru a adăuga un jucător, a începe jocul, a schimba jucătorul curent și a verifica mișcarea jucătorului;
    -> metoda "submit_move" primește coordonatele "i" și "j" ale tablei de joc, verifică dacă locul este liber și adaugă simbolul jucătorului curent; apoi verifică dacă jucătorul curent a câștigat sau dacă tabla de joc este plină, caz în care jocul se termină; in caz contrar, metoda schimbă jucătorul curent și returnează "false".


Clasa Player

    -> reprezinta un jucator;
    -> are trei variabile membru: numele jucatorului, simbolul (care poate fi 'a' sau 'n') si timpul ramas pentru a-si face mutarea;
    -> clasa are un constructor care initializeaza cele trei variabile cu valorile primite ca parametri;
    -> are cateva metode de acces si modificare a acestor variabile.

