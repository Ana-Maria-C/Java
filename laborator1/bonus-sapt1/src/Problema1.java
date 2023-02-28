
// Create the adjacency matrix A of a cycle graph Cn and compute the powers A2, A3, .... An. Give an interpretation of the result.
public class Problema1 {
    public static void main(String[] args) {
        try {
            Integer.parseInt(args[0]);
            System.out.println(args[0] + " is a valid integer number");
        } catch (NumberFormatException e) {
            System.out.println(args[0] + " is not a valid integer number");
            System.exit(0);
        }
        int n = Integer.parseInt(args[0]);
        int[][] graph_matrix = new int[n][n]; //matricea grafului
        int[][] A_putere = new int[n][n];           // in acesta matrice vom calcula puterile matricei grafului ,
        int[][] B = new int[n][n];           //o matrice auxiliara ce va folosi pentru a calcula puterile lui A
        int i, j;                            // indecsi pentru a parcurge matricea
        // initializam prima linie si ultima a matricei, deoarece exista o exceptie de la regula de completare a matricei de adiacenta
        B[0][n - 1] = 1;
        graph_matrix[0][n - 1] = 1;
        B[0][1] = 1;
        graph_matrix[0][1] = 1;
        B[n - 1][0] = 1;
        graph_matrix[n - 1][0] = 1;
        B[n - 1][n - 2] = 1;
        graph_matrix[n - 1][n - 2] = 1;

        /* initializam si restul matricei astfel:
         fiecare nod mai putin primul si ultimul, este adiacent cu nodul dinaintea si de dupa el;
        */
        for (i = 1; i < n - 1; i++) {
            B[i][i - 1] = 1;
            graph_matrix[i][i - 1] = 1;
            B[i][i + 1] = 1;
            graph_matrix[i][i + 1] = 1;
        }
        System.out.println("Matricea grafului,A: ");
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                System.out.print(graph_matrix[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
        // calculam puterile matricei
        for (int p = 2; p <= n; p++) {
            for ( i = 0; i < n; i++) {
                for ( j = 0; j < n; j++) {
                    A_putere[i][j]=0;
                    for (int k = 0; k < n; k++) {
                        A_putere[i][j] += B[i][k] * graph_matrix[k][j];
                    }
                }
            }
            System.out.println("A la puterea "+p);
            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    System.out.print(A_putere[i][j] + " ");
                }
                System.out.print("\n");
            }
            System.out.print("\n");
            for(i=0;i<n;i++)
                for(j=0;j<n;j++)
                    B[i][j]=A_putere[i][j]; // retinem matricea ridicata la puterea anterioara in B
        }

    }
}