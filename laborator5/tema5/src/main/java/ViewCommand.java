import java.awt.*;
import java.io.File;
import java.net.URI;

public class ViewCommand implements Command {
    private final Catalog catalog;  // catalogul in care se afla documentul
    private final String id;        // id-ul documentului

    public ViewCommand(Catalog catalog, String id) {
        this.catalog = catalog;
        this.id = id;
    }

    @Override
    public void execute() throws Exception {
        Document document = catalog.findById(id);    // cautam documentul in catalog
        if (document == null) {
            System.out.println("Document not found");
        } else {
            try {
                if (document.getPath().startsWith("http")) {
                    Desktop.getDesktop().browse(new URI(document.getPath()));
                } else {
                    File file = new File(document.getPath());
                    Desktop.getDesktop().open(file);
                }
            } catch (Exception e) {
                System.out.println("Failed to open document: " + e.getMessage());
            }
        }
    }

}
