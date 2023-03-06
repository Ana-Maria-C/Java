Am creat clasele Person si Company, fiecare obiect de unul dintre aceste tipuri avaand cate un nume.
Pentru fiecare clasa am implementat metoda compareTo din interfata Comparable pentru a putea ordona obiecte in functie de numele acestora;
Am creat intefata Node, ce contine metoda "getname", pe care am implementat-o in fiecare clasa;
In main am declarat obiecte de tipul Person si obiecte de tipul Company, iar apoi am creat o lista Node in care am adaugat obiecte de cele douia tipuri.
Suplimentar: Pentru a face ordonarea listei nodes(<Node>) am creat o functie lambda ce poate fi data ca parametru in momentul sortarii, pentru a face posibila compararea a doua obiecte de tipuri diferite.