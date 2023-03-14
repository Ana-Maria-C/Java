Am creat clasa abstracta Person, din care vor mosteni Programmer si Designer
Programmer se diferentiaza de Designer prin faptul ca acesta scrie cod si lucreaza cu limbaje de
programare, pe cand Designerul foloseste tehnologii si/sau ionstrumente pentru a crea interfete
In lasa abstracta am definit o pereche(map) de felul :<Obiect, String> ce va reprezenta
legaturile pe care o persoana le poate avea cu un obiect( o alta persoana sau companie)
iar conexiunea cu aceasta va fi descrisa de un atribut de tipul string(valoarea perechii)
Clasa Network include o lista de noduri(persoane/companii), iar un alt aspect important legat de
aceasta este faptul ca, contine o metoda get_importance, ce returneaza gradul fiecarui nod;
daca o persoana x are o relatie de prietenie cu o persoana y si lucreaza la compania z
, unde x,y si z sunt din aceeasi retea, atunci get_importance(x)=2, deoarece este conectat
cu alte doua noduri.
Pentru a face o sortare descrescatoare in functie de importanta a nodurilor retelei,
am definit o functie lambda, compareByImportance, ce compara doua obiecte node in functie
de importanta lor.

---> Pentru a calcula nodurile din retea ce constituie puncte de articulatie(adica prin eliminarea lor din graf, acesta este deconectat) am creat matricea
de adiacenta a retelei in clasa graph si am folosit metoda find_cutpoints, care verifica daca un nod este punct de articulatie, avand la baza un
algoritm de parcurgere a grafului in adancime(implementat in metoda dfs()).