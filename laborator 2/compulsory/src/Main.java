public class Main {
    public static void main(String[] args)
    {
        System.out.println("Hello world!");
        Location L1= new Location("Botosani",  Type_location.CITIES, 3.5, 4.8);
        Location L2= new Location ("Iasi International Aeroport", Type_location.AIRPORTS,8.2,10.9);
        Road R1= new Road("Locotenent Stoicescu", 100, 80,Type_road.EXPRESS);
        Road R2= new Road("A4",60, 130, Type_road.HIGHWAY);
        System.out.println(L1.toString());
        System.out.println(L2.toString());
        System.out.println(R1.toString());
        System.out.println(R2.toString());
    }
}