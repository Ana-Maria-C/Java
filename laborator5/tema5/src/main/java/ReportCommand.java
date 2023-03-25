import java.awt.*;
import java.io.*;

import freemarker.template.Configuration;
import java.util.HashMap;
import java.util.Map;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class ReportCommand implements Command {
    private Catalog catalog;
    private String templateFileName;

    public ReportCommand(Catalog catalog, String templateFileName) {
        this.catalog = catalog;
        this.templateFileName = templateFileName;
    }

    @Override
    public void execute() throws IOException, TemplateException {
        // Load the FreeMarker template from the file system
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        cfg.setDirectoryForTemplateLoading(new File("C:\\Users\\Ana-Maria\\IdeaProjects\\laborator5\\compulsory5\\src\\main\\resources"));
        Template template = cfg.getTemplate("template.ftl");

        // Create a data model to pass to the template
        Map<String, Object> data = new HashMap<>();
        data.put("documents", catalog.getDocs());

        // Generate the HTML report using the template and data model
        File reportFile = new File("report.html");
        Writer out = new FileWriter(reportFile);
        template.process(data, out);
        out.flush();
        out.close();

        if(reportFile.exists() && Desktop.isDesktopSupported()){
            // Open the HTML report in the default browser
            Desktop.getDesktop().browse(reportFile.toURI());
        }
        else{
            System.out.println("Failed to generate report or open in default browser.");
        }
    }

}