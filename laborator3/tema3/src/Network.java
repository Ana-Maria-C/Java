import java.util.*;
public class Network{
    private List<Node> nodes = new ArrayList<>();
    public void addNode(Node node) {
        nodes.add(node);
    }

    public Network(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Node> getNodes() {
        return nodes;
    }
    public int get_importance(Node node)
    {
        int importance=0;
        if(node instanceof Person)
            importance= ((Person) node).relationships.size();
        for(Node each:nodes)
            if(each instanceof Person) {
                Iterator<Map.Entry<Node, String>> it = ((Person) each).relationships.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<Node, String> entry = it.next();
                    Node key = entry.getKey();
                    //String value = entry.getValue();
                    //System.out.print(key.getName()+"\n"+ value+"\n");
                    if (key.getName().equals(node.getName())) {
                        importance++;
                    }
                }
            }
        return importance;
    }

    @Override
    public String toString() {
        return "Network{" +
                "nodes=" + nodes +
                '}';
    }

}
