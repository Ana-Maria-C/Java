import java.util.Arrays;
import java.util.Objects;

public abstract class Location {
    Location[] locations;
    int nr_of_locations=0;
protected String name;
protected double x;
protected double y;
protected String type_location;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return nr_of_locations == location.nr_of_locations && Double.compare(location.x, x) == 0 && Double.compare(location.y, y) == 0 && Arrays.equals(locations, location.locations) && Objects.equals(name, location.name) && Objects.equals(type_location, location.type_location);
    }
}