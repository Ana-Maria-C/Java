import java.io.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class CatalogUtil {
    private Catalog catalog;
    private String fileName;

        public CatalogUtil(Catalog catalog, String fileName) {
        this.catalog = catalog;
        this.fileName = fileName;
    }

    public static void save(Catalog catalog, String path)
            throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(
                new File(path),
                catalog);
    }
    public static Catalog load(String path)
            throws InvalidCatalogException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Catalog catalog = objectMapper.readValue(
                new File(path),
                Catalog.class);
        return catalog;
    }
}
