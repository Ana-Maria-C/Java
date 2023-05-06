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

    AbstractRepository:
        -> este implementată utilizând API-ul JPA și oferă operațiuni de bază CRUD (create, read, update și delete) pentru obiectele de tip T (care este tipul entității stocate în baza de date) și ID (care este tipul cheii primare a entității).
        -> metoda create(T entity) - adaugă o entitate de tip T în baza de date.
        -> metoda findById(ID id) - caută și returnează entitatea de tip T identificată de cheia primară specificată de parametrul ID.
        -> metoda findAll() - caută și returnează toate entitățile de tip T stocate în baza de date.
        -> metoda findByName(String name) - caută și returnează toate entitățile de tip T care au numele specificat în parametrul name.
        -> metoda update(T entity) - actualizează o entitate de tip T existentă în baza de date.
        -> metoda delete(ID id) - șterge entitatea de tip T identificată de cheia primară specificată de parametrul ID.
        -> clasa utilizează un obiect EntityManager pentru a interacționa cu baza de date.
        -> acesta este creat folosind un obiect EntityManagerFactory, care este pasat ca parametru constructorului AbstractRepository.
        -> clasa folosește API-ul CriteriaQuery pentru a defini criteriile de căutare pentru metodele findAll și findByName.


    Album Artist si Genre repository-classes extind clasa 'AbstractRepository' ce ofera toate funcționalitățile descrise mai sus