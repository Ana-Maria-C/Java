import java.util.Map;

public class AddCommand implements Command{
    /**
     * catalogul in care se adauga un nopu document
     */
    private final Catalog catalog;
    /**
     * atributele documentului ce va fi adaugat in catalog
     */
    private final String id;
    private final String title;
    private final String path;
    private final Map<String, String> tags;

    public AddCommand(Catalog catalog, String id, String title, String path, Map<String, String> tags) {
        this.catalog = catalog;
        this.id = id;
        this.title = title;
        this.path = path;
        this.tags = tags;
    }

    @Override
    public void execute() {
        /**
         * creem un document cu datele de mai sus si il adaugam in catalog
         */
        Document document =new Document(id, title, path, tags);
        catalog.getDocs().add(document);
    }
}
