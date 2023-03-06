import java.util.*;

public class Main {
    public static Comparator<Node> compareByName = (Node n1, Node n2) -> n1.getname().compareTo(n2.getname());
    public static void main(String[] args)
    {
        Person ana=new Person("Ana");
        Person ioan=new Person("Ioan");
        Person matei =new Person("Matei");
        Company amazon=new Company("Amazon");
        Company endava= new Company("Endava");

        List<Node> nodes =new ArrayList<>();

        nodes.add(ana);
        nodes.add(ioan);
        nodes.add(matei);
        nodes.add(amazon);
        nodes.add(endava);
        Collections.sort(nodes,compareByName);
        for(int i=0;i<nodes.size();i++)
        {
            System.out.println(nodes.get(i).getname());
        }
    }
}