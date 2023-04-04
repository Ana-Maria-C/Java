
->clasa Token: O clasă simplă care reprezintă un token cu o valoare numerică.

->clasa Robot: O clasă ce implementeaza Runable care reprezintă un robot care explorează harta. Fiecare robot are un nume, o referință la harta 
de explorare, o referință la memoria partajată și un număr de jetoane de extras din memorie atunci când vizitează o nouă celulă.
Metoda run() a robotului este bucla principală care selectează aleatoriu o celulă neexplorată, o vizitează, extrage jetoane din
 memoria partajată și le stochează în celulă.

-> clasa SharedMemory: O clasă care reprezintă memoria partajată care conține jetoanele. Memoria este inițializată cu o listă 
amestecată de jetoane, iar metoda extractTokens() permite roboților să extragă un anumit număr de jetoane din listă.

-> clasa ExplorationMap: O clasă care reprezintă harta de explorat. Harta este reprezentată ca o matrice bidimensională de celule,
 fiecare dintre ele conține o listă de jetoane. Metoda setExplored() marchează o celulă ca fiind explorată, în timp ce metodele 
getCellTokens() și setCellTokens() permit roboților să citească și să scrie jetoanele într-o celulă.

-> clasa Explorare: O clasă care gestionează procesul de explorare. Constructorul creează o hartă de explorare și o memorie
 partajată și creează un număr de fire robot. Metodele start(), pause() și resume() controlează execuția firelor de execuție 
a robotului.

->Am folosit încuietori pentru a sincroniza accesul la harta de explorare și la memoria partajată, asigurându-mă că 
doar un robot poate accesa o celulă sau poate extrage jetoane la un moment dat. Metoda `interrupt()` este folosită pentru a întrerupe 
firele robotului când este apelată metoda pause().