
public class Location
{
    private String name;
    private Type_location  type_location;
    private double x;
    private double y;

    public Location(String name, Type_location  type_location, double x, double y  ) {
        this.name = name;
        this.type_location=type_location;
        this.x=x;
        this.y=y;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setY(double y) {
        this.y = y;
    }
    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }
    public double getX() {
        return x;
    }

    public void setType_location(Type_location type_location) {
        this.type_location = type_location;
    }

    public Type_location getType_location() {
        return type_location;
    }
    @Override
    public String toString ()
    {

        return("Locatia "+ getName()+ " este de tipul "+ getType_location() + " si se afla la coordonatele: x="+getX() + " si "+ " y="+getY());

    }
}
