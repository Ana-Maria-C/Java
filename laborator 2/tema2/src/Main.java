
public class Main {
    public static void main(String[] args) {
        Location L1= new AIRPORT("Aeroport 1", 1,2,5);
        Location L2=  new AIRPORT("Aeroport 1", 3.5,4.7,5);
        Location L3 = new CITY("Botosani",2,0,200000);
        Location L4= new CITY("Iasi",5,8,1234534567);
        Location L5= new STATION("Universitate",5,12,6.8);
        Road R1= new EXPRESS("Mihai Viteazul", 2,60);
        Road R2= new EXPRESS("Mihai Eminescu", 3.4,60);
        Road R3= new HIGHWAY("Altceva", 80,130);
        Road R4=new COUNTRY("Eternitate",37,90);

        Location[] locatii={L1,L3,L5};
        Road[] strazi={R1,R3};
        Problem P1= new Problem(locatii,strazi);
        System.out.println(P1.is_valid());
        System.out.println( P1.from_to(L1,L3));
        System.out.println( P1.from_to(L1,L5));
    }
}