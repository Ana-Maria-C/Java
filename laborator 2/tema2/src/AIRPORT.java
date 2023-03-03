public class AIRPORT extends Location {
    int number_of_terminals;
    public AIRPORT(String name, double x, double y, int number_of_terminals) {
        this.name=name;
        this.x=x;
        this.y=y;
        this.type_location="AIRPORTS";
        this.number_of_terminals=number_of_terminals;
    }
}
