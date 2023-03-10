import java.util.Objects;

public class Company implements Node{
    private String name;
    private int start_year;
    private int  nr_of_employee;

    public Company(String name, int strat_year, int nr_of_employee) {
        this.name = name;
        this.start_year=strat_year;
        this.nr_of_employee=nr_of_employee;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNr_of_employee(int nr_of_employee) {
        this.nr_of_employee = nr_of_employee;
    }

    public void setStart_year(int start_year) {
        this.start_year = start_year;
    }

    public int getNr_of_employee() {
        return nr_of_employee;
    }

    public int getStart_year() {
        return start_year;
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return start_year == company.start_year && nr_of_employee == company.nr_of_employee && Objects.equals(name, company.name);
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", start_year=" + start_year +
                ", nr_of_employee=" + nr_of_employee +
                '}';
    }
}
