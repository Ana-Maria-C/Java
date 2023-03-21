import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String args[]) throws IOException, InvalidCatalogException {
        Document document1 = new Document("1", "Document 1", "document1.docx", Map.of("author", "John Doe", "year", "2021"));
        Document document2 = new Document("2", "Document 2", "document2.docx", Map.of("author", "Jane Smith", "year", "2022"));

        List<Document> doclist= new ArrayList<>();
        doclist.add(document1);
        doclist.add(document2);

        Catalog catalog = new Catalog("catalog1",doclist);

        CatalogUtil catalogManager = new CatalogUtil(catalog,"catalog.json");
        catalogManager.save(catalog, "catalog.json");

        Catalog loadedCatalog = CatalogUtil.load("catalog.json");
        System.out.println(loadedCatalog);
    }
}