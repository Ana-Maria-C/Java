import java.util.*;

public class Main {
public static void main(String[] args)
{
    String[] tip1={"computer hardware programme"};
    String[] l1={"java","c++"};
    Person ana=new Programmer("Ana","02.08.2002",tip1,l1,2);
    Person ioan=new Programmer("Ioan","21.05.2001",tip1,l1,4);
    Person matei =new Designer("Matei","19.01.2005",tip1,l1,1);
    Company amazon=new Company("Amazon",2005,289);
    Company endava= new Company("Endava",2012,147);

    //PriorityQueue<Integer> pQueue = new PriorityQueue<>();
    List<Node> nodes =new ArrayList<>();

    nodes.add(ioan);
    nodes.add(ana);
    nodes.add(matei);
    nodes.add(amazon);
    Network n1=new Network(nodes);
    Comparator<Node> compareByImportance = (o1, o2) -> Integer.compare(n1.get_importance(o2), n1.get_importance(o1));
    ana.addRelationship(matei,"bestfriends");
    matei.addRelationship(ioan,"bestfriends");
    ana.addRelationship(endava,"boss");
    matei.addRelationship(amazon,"boss");
    ioan.addRelationship(ana,"friends");
    System.out.println(n1.get_importance(amazon));
    System.out.println(n1.get_importance(ana));
    System.out.println(n1.get_importance(matei));
    System.out.println(n1.get_importance(ioan));
    nodes.sort(compareByImportance);
    System.out.println(n1.toString());
}
}