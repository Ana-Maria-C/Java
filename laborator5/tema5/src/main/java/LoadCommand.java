import java.io.*;
import java.io.IOException;

public class LoadCommand implements Command{
    private final String fileName;

    public LoadCommand(String fileName) {
        this.fileName = fileName;
    }
    @Override
    public void execute() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Catalog catalog = (Catalog) ois.readObject();
        ois.close();
        fis.close();
        catalog.toString();
    }
}
