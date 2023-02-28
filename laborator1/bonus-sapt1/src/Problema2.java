
// Create the adjacency matrix of a regular graph. The number of vertices and the vertex degree are given as arguments.
import java.util.Scanner;
public class Problema2 {
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
       int n=input.nextInt();
       int degree=input.nextInt();
       int[][] matrix= new int[n][n];
       if(degree<=n)
       {
           int i,j,k;
           for(i=0;i<n;i++)
           {
               for(k=0;k<degree;k++)
               {
                       j=(i+k)%n;
                       if(j!=i)
                       {
                           matrix[i][j]=1;
                           matrix[j][i]=1;
                       }
               }
           }
           for(i=0;i<n;i++)
           {
               for(j=0;j<n;j++)
                   System.out.print(matrix[i][j]+" ");
               System.out.print("\n");
           }

       }
       else
       {
           System.out.println("Nu exista un graf"+ args[1] + "regulat");
       }
    }
}