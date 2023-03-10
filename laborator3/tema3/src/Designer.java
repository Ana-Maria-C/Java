import java.util.*;
public class Designer extends Person{
    /***
     * type-ul este un vector de stringuri ce retine tipul/tipurile obiectului designer, de exemplu:
     * designer de interfata, de produs, de jocuri, de experiente, etc;
     */
    private String[] type;
    /***
     * used_tools retine tehnologiile si/sau instrumentele pe care le folosesc designerii, de exemplu:
     * Unity, Unreal Engine,Adobe Photoshop, Adobe Illustrator, Sketch, etc
     */
    private String[] used_tools;
    private int years_of_experience;
    public Designer(String name,String date_birth,String[] type,String[] used_tools, int years_of_experience) {
        this.name=name;
        this.date_birth = date_birth;
        this.type=type;
        this.used_tools=used_tools;
        this.years_of_experience=years_of_experience;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public void setUsed_tools(String[] used_tools) {
        this.used_tools = used_tools;
    }

    public void setYears_of_experience(int years_of_experience) {
        this.years_of_experience = years_of_experience;
    }

    public String[] getType() {
        return type;
    }

    public String[] getUsed_tools() {
        return used_tools;
    }

    public int getYears_of_experience() {
        return years_of_experience;
    }

}
