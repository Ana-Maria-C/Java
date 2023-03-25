Am creat clasele Catalog, Document, InvalidCatalogException, ce extinde Exception, pentru a genera o exceptie ce trateaza problema unui catalog invalid.
Clasa CatalogUtil este responsabila cu operatiile externe ce se executa asupra catalogului, precum save si load.
In clasa Catalog se afla:
 -metoda addDoc, ce adauga un document nou la lista de documnete ce se gaseste in clasa Catalog;
 - metoda toString ce genereaza o afisare a catalogului: cu documentele din acesta si detalii despre fiecare document;
In clasa CatalogUtil:
    - metoda save: ce utilizeaza ObjectMapper pt a serializa obiectul de tip catalog intr-un sir JSON.(Transforma obiectul java, catalog, in json)
    - metoda load : ce utilizeaza ObjectMapper pt a deserializa obiectul JSON intr-un obiect java, de tipul catalog.


    Homework
   Am creat interfata Command cu metoda execute()
   Am creat cate o clasa pentru fiecare comanda , ce implementeaza interfata execute:
     	-> AddCommand, ce adauga un document in catalog
	-> LoadCommand, ce incarca catalogul dintr o sursa exterioara 
	-> ListCommand, ce afiseaza toate documentele din catalog
	-> ViewCommand, primeste un document si verifica daca documentul este un link http sau fisier local si il deschide corespunzator
	-> ReportCommand, ce generează un raport HTML utilizand un sablon FreeMarker si date din catalogul de documente astfel:
		-incarca un sablon FreeMarker din sistemul de fisiere;
		-creeaza un model de date pentru a fi transmis sablonului;
		-genereaza un fisier HTML folosind sablonul si modelul de date;
		-incearca sa deschida fisierul HTML in browser, folosind clasa Desktop;