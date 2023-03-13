package org.example;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        var students = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student("S" + i) )
                .toArray(Student[]::new);

        var projects = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Project("P" + i) )
                .toArray(Project[]::new);


        /***
         * lista de studenti pe care o si initializam cu studentii declarati mai sus
         */
        LinkedList<Student> studentsList = new LinkedList<>(Arrays.asList(students));
        /***
         * folosind metoda suprascrisa compareTo din clasa student, vom afisa toti studentii in ordine lexicografica
         */
        studentsList.sort(Student::compareTo);
        for (Student student : studentsList) {
            System.out.println(student.getName());
        }
        /***
         * creem treeset-ul cu proiecte si specificam metoda folosita pentru ordonarea elementelor
         */
        TreeSet<Project> projectsTreeset=new TreeSet<>(Project::compareTo);
        projectsTreeset.addAll(Arrays.asList(projects));
        for (Project project : projectsTreeset) {
            System.out.println(project.getName());
        }
    }

}