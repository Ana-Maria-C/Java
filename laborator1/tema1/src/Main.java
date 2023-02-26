public class Main {
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();
        int n;
        String aux = args[0];
        if (args.length == 1)
        {
            System.out.println("Numarul introdus este: " + aux);
        }
        else
        {
            System.out.println("Trebuie sa introduceti un numar intrg!");
        }
        n=Integer.parseInt(aux);
       int[][] arr = new int[n][n];
       String[] linie = new String[n+1];
       String[] coloana = new String[n+1];
       int number_of_line=0;
       int number_of_column=0;
       int i,j,k=1;
       for(i=0;i<n;i++)
       {
           arr[0][i]=i+1;
           number_of_line=number_of_line*10+arr[0][i];
       }
        linie[0]=String.valueOf(number_of_line);

        for(j=0;j<n;j++)
        {
            arr[j][0]=j+1;
            number_of_column=number_of_column*10+arr[j][0];
        }
        coloana[0]=String.valueOf(number_of_column);

       for (i=1;i<n;i++)
       {
           number_of_line=0;
           number_of_column=0;
           for(j=0;j<n-1;j++)
           {
            arr[i][j]=arr[i-1][j+1];
            number_of_line=number_of_line*10+arr[i][j];
            number_of_column=number_of_column*10+arr[j][i];
           }
           arr[i][j]=arr[i-1][0];
           number_of_line=number_of_line*10+arr[i][j];
           number_of_column=number_of_column*10+arr[j][i];
           linie[k]=String.valueOf(number_of_line);
           coloana[k]=String.valueOf(number_of_column);
           k++;
       }
        System.out.println("Valorile pe linie sunt:");
       for(i=0;i<k;i++)
           System.out.println(linie[i]);
        System.out.println("Valorile pe coloana sunt:");
        for(i=0;i<k;i++)
            System.out.println(coloana[i]);
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        if(n>30.000)
        {
            System.out.println("Timpul de rulare al programului este: " + totalTime/1000 + " milisecunde");
        }
    }
}