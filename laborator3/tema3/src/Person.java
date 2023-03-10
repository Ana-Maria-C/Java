import java.util.*;
public abstract class Person implements Node{
protected String name;
protected String date_birth;
    /***
     * pentru a retine personale/ companiile(obiectele de tip node) vom crea o coada de prioritate in care sortarea se va face
     * in functie de importanta nodurilor, adica numarul de noduri cu care acestea sunt conectate
     * de exemplu: un node de tip persoana este conectat cu un alt node de tip persoana daca acestea sunt prietene,
     * iar un nod de tip companie este conectat cu un node de tip persoana daca persoana este angajata la compania respectiva.
     */
protected Map<Node, String> relationships= new HashMap<>();

    public String getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(String date_birth) {
        this.date_birth = date_birth;
    }

    public void setName(String name) {
        if(name.length()>0)
             this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public Map<Node, String> getRelationships() {
        return relationships;
    }

    public void addRelationship(Node node, String value) {
         this.relationships.put(node, value);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", date_birth='" + date_birth + '\'' +
                '}';
    }
}
