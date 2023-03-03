import java.util.Arrays;
import java.util.Objects;

public abstract class Road {
    Road[] roads;
    int nr_of_roads=0;
    protected String name;
    protected double length;
    protected int speed_limit;
    protected String type_road;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Road road = (Road) o;
        return nr_of_roads == road.nr_of_roads && Double.compare(road.length, length) == 0 && speed_limit == road.speed_limit && Arrays.equals(roads, road.roads) && Objects.equals(name, road.name) && Objects.equals(type_road, road.type_road);
    }
}
