
Clasa GameServer

    ->  este implementarea serverului de jocuri care poate accepta conexiuni de la clienți și poate permite clienților să creeze sau să se alăture unui joc;
    -> metoda start() deschide un serverSocket și așteaptă conexiuni noi; atunci când un client se conectează la server, aceasta creează un obiect de tipul ClientThread și îl adaugă la lista de clienți,apoi, aceasta pornește un fir de execuție pentru comunicarea cu acel client;
    -> metoda stop() este apelată atunci când vrem să oprim serverul de jocuri;
    -> metoda stop() este apelată atunci când dorim să oprim serverul;
    -> metoda isStopped() returnează valoarea variabilei stopped;
    -> metoda removeClient() este utilizată pentru a elimina un client din lista de clienți atunci când acesta se deconectează de la server.


Clasa ClientThread

    -> extinde din clasa Thread și gestionează conexiunile clientului pentru serverul GameServer;
    -> metoda run() a acestei clase gestionează comunicarea între client și server;
    -> se configurează un obiect PrintWriter pentru a trimite răspunsuri către client și un obiect BufferedReader pentru a citi cererile de la client;
    -> metoda close() este apelată atunci când clientul este deconectat de la server; aceasta închide socketul clientului și înlătură obiectul ClientThread din lista de clienți din GameServer.


Clasa GameClient

    -> reprezintă un client de joc;
    -> clientul se conectează la un server de joc pe un anumit port și apoi primește comenzi de la utilizator prin intermediul comenzilor tastaturii;
    -> client de joc are patru comenzi disponibile: "create", "join", "move" și "exit";
    -> comanda "create" este utilizată pentru a crea un joc nou;
    -> comanda "join" este utilizată pentru a se alătura unui joc existent;
    -> comanda "move" este utilizată pentru a efectua o mutare în joc;
    -> omanda "exit" este utilizată pentru a ieși din joc;
    -> !!! în plus, comenzile pot fi apelate după o anumită logică: un client trebuie să fie într-un joc pentru a putea face o mutare și dacă a dat join sau create , cu alte cuvinte
            se află deja în joc, nu poate da  create sau join din nou, deoarece face deja parte dintr-un joc în momentul curent.


Clasele MainServer și MainClient sunt folosite pentru a rula aplicția: MainServer pornește serverul, iar MainClient pornește clientul prin: server.start(), respectiv client.start().