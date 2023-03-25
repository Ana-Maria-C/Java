import java.util.List;

public class ListCommand implements Command{
    private Catalog catalog;

    public ListCommand(Catalog catalog) {
        this.catalog = catalog;
    }
    @Override
    public void execute() {
        System.out.println("Documents from "+ catalog.getName()+": ");
        List<Document> documents = catalog.getDocs();
        if (documents.isEmpty()) {
            System.out.println("Catalog is empty.");
        } else {
            for (Document document : documents) {
                System.out.println(document);
            }
        }
    }
}
