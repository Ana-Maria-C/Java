public class Problem {
    private Location[] locations;
    private Road[] roads;

    public Problem(Location[] locations, Road[] roads) {
        // verificam sa nu apara aceeasi loactie de mai multe ori;
        for (int i = 0; i < locations.length - 1; i++) {
            for (int j = i + 1; j < locations.length; j++) {
                if (locations[i].equals(locations[j])) {
                    for (int k = j; k < locations.length - 1; k++) {
                        locations[k] = locations[k + 1];
                    }
                    locations[locations.length - 1] = null;
                }
            }
        }
        // verificm sa nu apara aceeasi strada de mai multe ori

        for (int i = 0; i < roads.length - 1; i++) {
            for (int j = i + 1; j < roads.length; j++) {
                if (roads[i].equals(roads[j])) {
                    for (int k = j; k < roads.length - 1; k++) {
                        roads[k] = roads[k + 1];
                    }
                    roads[roads.length - 1] = null;
                }
            }
        }

        this.locations = locations;
        this.roads = roads;
    }
    // pentru ca problema sa fie valida trebuie sa contina cel putin 2 locatii diferite si o strada
    public boolean is_valid()
    {
       if(locations.length>=2 && roads.length>=1)
            {
                return true;
            }
        return false;
    }
    public String from_to(Location L1, Location L2)
    {
        int distance;
        distance= (int)Math.sqrt(Math.pow((L2.x-L1.x),2)+Math.pow((L2.y-L1.y),2));
        for(int i=0;i< roads.length;i++)
        {
            if((int)roads[i].length==distance)
            {
                return "Se poate ajunge din locatia: "+L1.name+" in locatia: "+L2.name ;
            }
        }
        return "Nu se poate ajunge din locatia: "+L1.name+" in locatia: "+L2.name ;
    }
}
