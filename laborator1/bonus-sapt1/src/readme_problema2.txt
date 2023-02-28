Problema: Create the adjacency matrix of a regular graph. The number of vertices and the vertex degree are given as arguments.

Pentru a forma matricea de adiacenta a unui graf k-regulat, ne folosim de urmatoarele:
-matricea de adiacenta este simetrica, adica: matrix[i][j]=matix[j][i];
-elementele de pe diagonala principala sunt nule: matrix[i][i]=0, pt orice i>=0, i<n;
-fiecare nod i, i>=0 si i<n are exact k vecini;
Pentru a ne asigura ca atribuim fiecarui nod exact k-vecini, adica pe fiecare linie si fiecare coloana sa se gaseasca exact k elemente 1 si restul 0, trebuie sa ne asiguram ca lista vecinilor nodului curent i este ciclica: j=(i+k)%n, unde j este vecinul nodului i, iar k reprezinta pozitia lui j in lista de adiacenta a vecinilor lui i.
Dupa ce l-am marcat in matrice pe j ca fiind vecinul lui i : matrix[i][j]=1, ne asiguram ca si i este marcat ca fiind pe lista de adiacenta a vecinilor lui j: matrix[j][i]=1.