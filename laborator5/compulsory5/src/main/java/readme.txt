Am creat clasele Catalog, Document, InvalidCatalogException, ce extinde Exception, pentru a genera o exceptie ce trateaza problema unui catalog invalid.
Clasa CatalogUtil este responsabila cu operatiile externe ce se executa asupra catalogului, precum save si load.
In clasa Catalog se afla:
 -metoda addDoc, ce adauga un document nou la lista de documnete ce se gaseste in clasa Catalog;
 - metoda toString ce genereaza o afisare a catalogului: cu documentele din acesta si detalii despre fiecare document;
In clasa CatalogUtil:
    - metoda save: ce utilizeaza ObjectMapper pt a serializa obiectul de tip catalog intr-un sir JSON.(Transforma obiectul java, catalog, in json)
    - metoda load : ce utilizeaza ObjectMapper pt a deserializa obiectul JSON intr-un obiect java, de tipul catalog.