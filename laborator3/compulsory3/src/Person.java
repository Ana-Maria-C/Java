public class Person implements Comparable<Person>, Node{
 private String name;

    public Person(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Person other) {
        return this.getName().compareTo(other.getName());
    }

    @Override
    public String getname() {
        return this.name;
    }
}
