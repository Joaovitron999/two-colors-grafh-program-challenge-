import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String finalOut = "\n";
        Scanner scanner = new Scanner(System.in);
        int qntNodes = scanner.nextInt();
        while (qntNodes != 0) {
            Graph graph = new Graph();  // cria um novo grafo
            for (int i = 0; i < qntNodes; i++) {    // adiciona os nós ao grafo
                graph.addNode(new Node(i));
            }
            int qntEdges = scanner.nextInt();
            for (int i = 0; i < qntEdges; i++) {   // adiciona as arestas ao grafo
                int node1 = scanner.nextInt();
                int node2 = scanner.nextInt();
                graph.addEdge(graph.getNodes().get(node1), graph.getNodes().get(node2));
                graph.getNodes().get(node1).getNeighbors().add(graph.getNodes().get(node2));
                graph.getNodes().get(node2).getNeighbors().add(graph.getNodes().get(node1));

            }

            paintGraph(graph);
            //printGraph(graph);
            if(testGraph(graph)) {
                finalOut += "BICOLORABLE.\n";
            } else {
                finalOut += "NOT BICOLORABLE.\n";
            }
            qntNodes = scanner.nextInt();
        }
        System.out.println(finalOut);
    }

    private static void paintGraph(Graph graph) {
        List<Node> nodes = graph.getNodes();
        //pinta o primeiro nó
        nodes.get(0).setColor(1);

        for(Node node : nodes) {
            if(node.getColor() == 1) {
                for(Node neighbor : node.getNeighbors()) {
                    if(neighbor.getColor() == 0) {
                        neighbor.setColor(2);
                    }
                }
            } else if(node.getColor() == 2) {
                for(Node neighbor : node.getNeighbors()) {
                    if(neighbor.getColor() == 0) {
                        neighbor.setColor(1);
                    }
                }  
            }
        }        
    }

    // testa se não há arestas entre nós de mesma cor
    private static boolean testGraph(Graph graph) {
        List<Node> nodes = graph.getNodes();
        for(Node node : nodes) {
            for(Node neighbor : node.getNeighbors()) {
                if(node.getColor() == neighbor.getColor()) {
                    return false;
                }
            }
        }
        return true;
    }


    //teste
    private static void printGraph(Graph graph) {
        List<Node> nodes = graph.getNodes();
        for(Node node : nodes) {
            System.out.println("Nó: " + node.getId() + " Cor: " + node.getColor());
        }
    }
}

// graph

class Graph {
    private List<Node> nodes = new ArrayList<>();

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void addEdge(Node node1, Node node2) {
        Edge edge = new Edge(node1, node2);
        node1.getEdges().add(edge);
        node2.getEdges().add(edge);
    }

    public List<Node> getNodes() {
        return nodes;
    }
}

class Node {
    private int id;
    private int color;  // 0 = não pintado, 1 = cor 1, 2 = cor 2
    private List<Edge> edges = new ArrayList<>();
    private List<Node> neighbors = new ArrayList<>();

    public Node(int id) {
        this.id = id;
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<Node> neighbors) {
        this.neighbors = neighbors;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public int getId() {
        return id;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
    
}

class Edge {
    private Node node1;
    private Node node2;

    // atalho para criar um construtor automaticamente é: alt + insert
    public Edge(Node node1, Node node2) {
        this.node1 = node1;
        this.node2 = node2;
    }

    public Node getNode1() {
        return node1;
    }

    public Node getNode2() {
        return node2;
    }
}

// gh