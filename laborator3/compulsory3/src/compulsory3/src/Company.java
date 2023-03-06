public class Company implements Comparable<Company>, Node {
    private String name;

    public Company(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Company other_company) {
        return this.getName().compareTo(other_company.getName());
    }

    @Override
    public String getname() {
        return this.name;
    }
}
