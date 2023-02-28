public class Road {
    private float length;
    private String name;
    private int speed_limit;
    private Type_road type_road;

    public Road( String name,float length, int speed_limit, Type_road type_road) {
        this.length = length;
        this.name=name;
        this.speed_limit=speed_limit;
        this.type_road=type_road;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpeed_limit() {
        return speed_limit;
    }

    public void setSpeed_limit(int speed_limit) {
        this.speed_limit = speed_limit;
    }

    public Type_road getType_road() {
        return type_road;
    }

    public void setType_road(Type_road type_road) {
        this.type_road = type_road;
    }

    @Override
    public String toString ()
    {

        String info="Strada "+ getName()+ " este de tipul "+ getType_road() + " are limita de viteza "+ getSpeed_limit() + " si are o lungime de "+getLength();
        return info;
    }
}
