package org.example;
import java.util.*;

public class Problem {
   List<Student> studentList=new ArrayList<>();
   List<Project> projectList=new ArrayList<>();
   Map<Student,List<Project>> prefMap =new HashMap<>();

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public Problem(List<Student> studentList, List<Project> projectList) {
        this.studentList = studentList;
        this.projectList = projectList;

    }
    public int getIndexStudent(Student student)
    {
        for(int i=0;i<studentList.size();i++)
        {
            if(studentList.get(i).getName().equals(student.getName()))
                return i;
        }
        return -1;
    }

    /***
     *
     * lista cu proiecte disponibile pentru fiecare student
     */
    public List<Project> getAvailableProjects(Student student)
    {
        int index=this.getIndexStudent(student);
        if(index==0)
        {
            return this.projectList;
        }
        else
            if(index>0)
            {
                List<Project> availableProject=new ArrayList<>();
                for(;index<projectList.size();index++)
                {
                    availableProject.add(projectList.get(index));
                }
                return availableProject;
            }
        return Collections.emptyList();
    }

    /**
     * lista cu preferintele pe care le au studentii
     */
    public void addStudPref(Student student, List<Project> projects) {
        prefMap.put(student,projects);
    }

    /**
     * pt afisarea preferintelor pe care le-a introdus un student
     */
    public List<Project> getStudPref(Student student)
    {
        Iterator<Map.Entry<Student, List<Project>>> it = prefMap.entrySet().iterator();
        while (it.hasNext()) {
            var entry = it.next();
            Student key = entry.getKey();
            var value = entry.getValue();
            if (key.getName().equals(student.getName()))
            {
                return value;
            }
        }
        return Collections.emptyList();
    }
    /**
     * afisam toti studentii care au un numar de preferinte mai mic decat media
     */
    public void getStudentsWithFewPreferences()
    {
        /**
         *calculam media de preferinte a studentilor
          */
        if(!prefMap.isEmpty()) {
            double avgPrefs = prefMap.values()
                    .stream()
                    .flatMap(Collection::stream)
                    .count() / (double) prefMap.size();

            System.out.println("Students with less than average preferences:");
            prefMap.entrySet()
                    .stream()
                    .filter(entry -> entry.getValue() != null)
                    .filter(entry -> entry.getValue().size() < avgPrefs)
                    .map(Map.Entry::getKey)
                    .forEach(student -> System.out.println(student.getName()));
        }
    }
    public Map<Student, Project> matchAlgorithm()
    {
      /**
       * fiecare student va primi primul proiect din lista sa proiecte disponibile
       * deoarece la creearea listelor de proiecte disponibile am folosit algoritmul:
       * -primul student poate alege din toate proiectele date ca input problemei;
       * - urmatorul student va avea lista celui dinaintea lui, din care am sters primul proiect
       * asadar primul proiect din lista unui student nu va aparea niciodata in lista celorlati
       */
      Map<Student, Project> solveAlgotithm=new HashMap<>();
      for(int i=0;i<studentList.size();i++)
      {
          solveAlgotithm.put(studentList.get(i),getAvailableProjects(studentList.get(i)).get(0));
      }
        return solveAlgotithm;
    }
    }

