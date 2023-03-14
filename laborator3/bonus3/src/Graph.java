import java.util.Arrays;

public class Graph {
    private int n;
    private int[][] graph;
    private int[] visited;
    private int[] tin;
    private int[] low;
    private int timer = 0;
    private int[] isCutPoin;

    public Graph(int n, int graph[][]) {
        this.n = n;
        this.graph = graph;
        this.visited = new int[n];
        this.tin = new int[n];
        this.low = new int[n];
        this.isCutPoin = new int[n];
    }

    public void setGraph(int[][] graph) {
        this.graph = graph;
    }

    public int[][] getGraph() {
        return graph;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getIsCutPoinLength() {
        return this.isCutPoin.length;
    }

    public int getN() {
        return n;
    }

    public void setElement(int i, int j, int value) {
        this.graph[i][j] = value;
    }

    public int getElement(int i, int j) {
        return graph[i][j];
    }

    public void IS_CUTPOINT(int vertex) {

        this.isCutPoin[vertex] = 1;
    }

    public void dfs(int v, int p) // metoda va fi apelata mereu cu p=-1
    {
        visited[v] = 1;
        tin[v] = low[v] = timer++;
        int children = 0;
        for (int to : graph[v]) {
            if (to == p) continue;
            if (visited[to] == 1) {
                low[v] = Math.min(low[v], tin[to]);
            } else {
                dfs(to, v);
                low[v] = Math.min(low[v], low[to]);
                if (low[to] >= tin[v] && p != -1) {
                    IS_CUTPOINT(v);
                    //System.out.println("aici");
                }
                ++children;
            }
        }
        if (p == -1 && children > 1) {
            IS_CUTPOINT(v);
        }
    }

    public void find_cutpoints() {
        timer = 0;
        Arrays.fill(visited, 0);
        Arrays.fill(tin, -1);
        Arrays.fill(low, -1);
        for (int i = 0; i < graph.length; ++i) {
            if (visited[i] == 0) {
                dfs(i, -1);
            }
        }
    }

    public int getIsCutPointElement(int index) {
        return this.isCutPoin[index];
    }
}
