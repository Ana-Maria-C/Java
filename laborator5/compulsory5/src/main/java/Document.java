import java.util.*;
public class Document {
    private String id;
    private String title;
    private String path;
    private Map<String, String> tags;

    public Document(){

    }
    public Document(String id, String title, String path, Map<String, String> tags) {
        this.id = id;
        this.title = title;
        this.path = path;
        this.tags = tags;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getTags() {
        return tags;
    }
    public void addTag(String key, String value) {
        this.tags.put(key, value);
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", path='" + path + '\'' +
                ", tags=" + tags +
                '}';
    }
}
