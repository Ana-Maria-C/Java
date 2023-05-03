Entity:

    Album:
        -> este o entitate persistență și este mapată către tabela "albums" din baza de date
        -> are o serie de proprietăți (id, releaseYear, title, artist și genres) și un constructor care primește valorile acestora
        -> definește o relație ManyToOne cu clasa Artist și o relație ManyToMany cu clasa Genre, utilizând o tabelă de legătură "album_genre"
        -> conține și o interogare numită "Genre.findByName", care va fi utilizată pentru a căuta albume după numele lor

    Artist:
        -> reprezintă o entitate persistentă pentru un artist muzical și va fi utilizată pentru a stoca și accesa informații despre artiști din baza de date;
        -> este mapată către tabela "artists" din baza de date și are o serie de proprietăți (id, name, albums) și un constructor care primește valorile acestora
        -> definește o relație OneToMany cu clasa Album, indicând că un artist poate avea mai multe albume,această relație este mapată pe baza faptului că în clasa Album, există o proprietate artist, care indică artistul ce a creat acel album
        -> conține și o interogare numită "Genre.findByName", care va fi utilizată pentru a căuta artiști după numele lor.

    Genre:
        -> este o clasă de entități, care reprezintă genuri muzicale și este folosită pentru a gestiona datele despre genurile muzicale în aplicație.
        -> conține:un câmp id pentru identificarea unică a fiecărui gen muzical în baza de date, un câmp name pentru numele genului muzical, un set de albume (albums) care conțin acest gen muzical
        -> conține și o interogare numită (NamedQuery) pentru a găsi genul muzical după nume (Genre.findByName).


Repository:

    Album:
        -> este responsabilă pentru efectuarea operațiilor de bază pe entitatea Album în baza de date.
        -> conține metode pentru crearea unui album, găsirea unui album după id și găsirea unei liste de albume după nume
        -> clasa utilizează un EntityManagerFactory pentru a crea și a gestiona conexiunea la baza de date; aceasta este o implementare a șablonului Singleton, astfel încât să nu fie creat mai mult de un obiect AlbumRepository.

    Artist:
        -> reprezintă un repository (un fel de serviciu) pentru a interacționa cu tabela "artists" din baza de date
        -> are următoarele metode: create - inserează un nou artist în baza de date; findById - caută un artist în baza de date după un ID specific și îl întoarce;
              findByName - caută artiști în baza de date după  nume și întoarce o listă cu artiștii găsiți.
    Genre:
        -> metoda create din clasa GenreRepository primește ca argument un obiect de tip Genre, acest obiect trebuie să reprezinte o instanță a entității Genre pe care dorim să o stocăm în baza de date.
        -> create utilizează un obiect EntityManager pentru a începe o tranzacție cu baza de date: in interiorul acestei tranzacții, metoda persist a obiectului EntityManager este folosită pentru a stoca obiectul de tip Genre în baza de date.
        ->  metoda findById primește ca argument un id de tip Long, caută în baza de date obiectul de tip Genre cu acest id și îl returnează.
        ->  metoda findByName primește ca argument un nume și returnează o listă de obiecte de tip Genre care conțin acel nume; pentru a realiza căutarea, se folosește o interogare numită (NamedQuery) definită în clasa Genre.