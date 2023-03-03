public class STATION extends Location{
    double gas_price;
    public STATION(String name, double x, double y, double gas_price) {
        this.name=name;
        this.x=x;
        this.y=y;
        this.type_location="STATION";
        this.gas_price=gas_price;
    }
}
