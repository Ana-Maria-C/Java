Am creat un Maven project.
Am creat clasele Student si Project, fiecare dintre ele are un atribut name si suprascrie metoda compareTo, pentru a face compararea obiectelor de tipul
Project, respectiv Student.
Am creat 3 obicete de tipul student si 3 obiecte de tipul project cu ajutorul stream-urilor.
Am pus studentii intr-o lista inlantuita si i-am afisat apoi in ordine alfabetica, folosind metoda de comparare, Student::compareTo.
Am creeat apoi un treeset, in care am adaugat proiectele si le-am afisat in ordine alfabetica, utilizand metoda Project::compareTo .

HOMEWORK:

Am creat clasa Problem care primeste ca input o lista de studenti si o lista de studenti
Am adaugat un Map de forma: <Student, listaPreferinte> pentru a retine lista de proiecte preferate a fiecarui student(daca are)

Folosind faker.name(), am generat nume false atat pentru studenti cat si pentru proiecte.
Accesul la aceasta s-a facut modificand fisierul pom.xml si adaugand dependintele:
<dependency>
            <groupId>com.github.javafaker</groupId>
            <artifactId>javafaker</artifactId>
            <version>1.0.2</version>
            <scope>test</scope>
        </dependency>

Am creat metoda getAvailableProjects, care va asigna fiecarui student o lista de proiecte disponibile, din care poate alege; aceasta metoda
va fi utilizata in algoritmul greedy pentru asignarea unui proiect fiecarui student
In metoda getStudentsWithFewPreferences se gaseste un query prin care vom afisa toti studenti cu un numar de preferinte mai mic decat media
Prima linie, folosind stream-uri,obtine o listă cu toate preferințele și se numără aceste preferințe, iar apoi acest număr este împărțit
la numărul de studenți pentru a se obține media preferințelor.
Urmatoarea linie, parcurge harta prefMap utilizand stream-uri si se aplica o operatie de filtrare pentru a obtine doar intrarile care au
un numar de preferinte mai mic decat media; aceste intrari sunt transformate in obiecte Student folosind metoda map() si apoi se
afiseaza numele studentilor folosind metoda forEach().
Metoda matchAlgorithm, foloseste un algoritm Greedy prin care atribuie fiecarui student primul proiect din lista sa de obtiuni, ce a fost
creata astfel incat doi studenti sa nu poata fi asignati cu acelasi proiect.