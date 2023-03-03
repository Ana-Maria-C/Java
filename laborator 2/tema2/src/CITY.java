public class CITY extends Location {
    int population;
    public CITY(String name, double x, double y, int population ) {
        this.name=name;
        this.x=x;
        this.y=y;
        this.type_location="CITY";
        this.population=population;
    }
}
