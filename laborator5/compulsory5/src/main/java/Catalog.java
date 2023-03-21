import java.io.Serializable;
import java.util.*;
public class Catalog implements Serializable {
    private String name;
    private List<Document> docs = new ArrayList<>();

    public Catalog() {
        // default constructor for deserialization
    }

    public Catalog(String name, List<Document> docs) {
        this.name = name;
        this.docs = docs;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDocs(List<Document> docs) {
        this.docs = docs;
    }

    public String getName() {
        return name;
    }

    public List<Document> getDocs() {
        return docs;
    }

    public void addDoc(Document doc) {
        docs.add(doc);
    }

    public Document findById(String id) {
        return docs.stream()
                .filter(d -> d.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "name='" + name + '\'' +
                ", docs=" + docs +
                '}';
    }
}
