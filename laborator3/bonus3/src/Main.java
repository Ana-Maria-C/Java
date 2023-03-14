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

    /***
     * vom modela reteaua de persoane si companii ca un graf, astfel incat fiacre persoana/companie va reprezenta un nod, iar daca doua persoane au o relatie de prietenie/etc
     * sau o persona lucreaza intr-o companie, va exista muchie intre nodurile corespunzatoare acestora.
     * numerotarea nmodurilor se va face cu ajutorul functiei getId din clasa network, iar listele de adiacenta ale nodurilor se vor completa cu ajutorul metodei
     * relationships din clasa Person.
     */
    int n=n1.getNumbersOfNodes(); // number of nodes
    int[][] graphParametre=new int[n][n];
    Graph graph=new Graph(n,graphParametre); // adjacency list of graph
    List<Node> graphNodes=n1.getNodes();
    for(Node node :graphNodes)
    {
        //System.out.println(node.getName()+' '+n1.getId(node));
        int i= n1.getId(node); // identificam id-ul nodului ce va corespunde numarului sau in grap
        int j;
        /***
         * initializam lista de adiacenta a nodului (relationships) si pe baza acesteia completam matricea de adiacenta 'graph';
         */
        if(node instanceof Person)
        {
            Iterator<Map.Entry<Node, String>> it = ((Person) node).relationships.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Node, String> entry = it.next();
                Node key = entry.getKey();
                j=n1.getId(key);// luam id-ul prietenului
                /**
                 * verificam daca nodul se afla in retea si completam graful conform relatiei cu nodul curent
                 */
                if(n1.getId(key)>=0)
                {
                    graph.setElement(i,j,1);
                    graph.setElement(j,i,1);
                }
            }
        }
    }

    /**
     * matricea de adiacenta a grafului:
     */
    for(int i=0;i<n;i++)
    {
        for(int j=0;j<n;j++)
        {
            System.out.print(graph.getElement(i,j));
        }
        System.out.println();
    }
    graph.find_cutpoints();
    /**
     * afisam punctele de articulatie
     */
    for(int i=0;i<graph.getIsCutPoinLength();i++)
    {
        for(Node nod:graphNodes)
        {
            if(graph.getIsCutPointElement(n1.getId(nod))==1)
            {
                System.out.println(nod.getName()+" este punct de articulatie in retea");
            }
        }
    }
}
}