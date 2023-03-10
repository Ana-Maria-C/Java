import java.util.*;
public class Programmer extends Person{
    /***
     * tipul de programator: computer hardware programmer, web developers, software developer sau database developers
     * Un programator isi poate atribui mai multe roluri din cele enumerate mai sus, motiv pentru care type-ul este un vector de tip string;
     */
    private String[] type;
    private int years_of_experience;
    /***
     * program_languages este un vector de stringuri ce va retine limbajele de programare cunoscute de programator;
     */
    private String[] program_languages;

    public Programmer(String name,String date_birth,String[] type, String[] program_languages, int years_of_experience) {
        this.name=name;
        this.date_birth = date_birth;
        this.type=type;
        this.program_languages=program_languages;
        this.years_of_experience=years_of_experience;
    }

    public void setProgram_languages(String[] program_languages) {
        this.program_languages = program_languages;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public void setYears_of_experience(int years_of_experience) {
        this.years_of_experience = years_of_experience;
    }

    public String[] getProgram_languages() {
        return program_languages;
    }

    public String[] getType() {
        return type;
    }

    public int getYears_of_experience() {
        return years_of_experience;
    }

}
