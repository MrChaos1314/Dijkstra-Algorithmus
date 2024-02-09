package schule;

public class Main {

    public static void main(String[] args) {
        Main v = new Main();
        v.run();


    }

    public void run(){
        System.out.println(dijkstra(addGraph(), addGraph().getVertex("A"), addGraph().getVertex("F")));
    }

    public String dijkstra(Graph g, Vertex start, Vertex end){
        List<Vertex> allVertices = new List<Vertex>();
        List<Vertex> currentVNeighbours;
        Vertex currentV;
        
        String[] vertices = new String[vertexListSize(allVertices)];
        int[] vertexDistance = new int[vertexListSize(allVertices)];

        allVertices.toFirst();
        int help = 0;
        while (allVertices.hasAccess()) {
            vertices[help] = allVertices.getContent().getID();
            allVertices.next();
        }

        for (int i = 0; i < vertexDistance.length; i++) {
            if(vertices[i].equals(start.getID())){
                vertexDistance[i] = 0;
            }else{
                vertexDistance[i] = 1000000;
            }
        }

        start.setMark(true);
        currentV = start;

        while (!g.allVerticesMarked()) {
            currentVNeighbours = g.getNeighbours(currentV);
            currentVNeighbours.toFirst();
            while (currentVNeighbours.hasAccess()) {
                if (vertexHasDistance(vertices, vertexDistance, currentV) == false) {
                    vertexSetDistance(vertices, vertexDistance, currentV);
                    currentV.setMark(true);
                }
            }
        }


        
        return null;
    }

    public int vertexListSize(List<Vertex> list){
        int size = 0;
        list.toFirst();
        while (list.hasAccess()) {
            size++;
            list.next();
        }
        
        
        return size;
    }

    public boolean vertexHasDistance(String[] vertices, int[] vertexDistance, Vertex currentV){

        for (int i = 0; i < vertices.length; i++) {
            if(vertices[i].equals(currentV.getID())){
                if(vertexDistance[i] == 1000000){
                    return false;
                }
            }
        }

        return true;
    }

    public boolean vertexSetDistance(String[] vertices, int[] vertexDistance, Vertex currentV){

        for (int i = 0; i < vertices.length; i++) {
            if(vertices[i].equals(currentV.getID())){
                if(vertexDistance[i] == 1000000){
                    return false;
                }
            }
        }

        return true;
    }

    public Graph addGraph(){
        Graph g = new Graph();

        g.addVertex(new Vertex("A"));
        g.addVertex(new Vertex("B"));
        g.addVertex(new Vertex("C"));
        g.addVertex(new Vertex("D"));
        g.addVertex(new Vertex("E"));
        g.addVertex(new Vertex("F"));
        g.addVertex(new Vertex("G"));
        g.addVertex(new Vertex("H"));

        g.addEdge(new Edge(g.getVertex("A"), g.getVertex("B"), 3));
        g.addEdge(new Edge(g.getVertex("A"), g.getVertex("D"), 6));
        g.addEdge(new Edge(g.getVertex("B"), g.getVertex("C"), 2));
        g.addEdge(new Edge(g.getVertex("C"), g.getVertex("D"), 6));
        g.addEdge(new Edge(g.getVertex("B"), g.getVertex("E"), 7));
        g.addEdge(new Edge(g.getVertex("C"), g.getVertex("E"), 4));
        g.addEdge(new Edge(g.getVertex("C"), g.getVertex("G"), 5));
        g.addEdge(new Edge(g.getVertex("C"), g.getVertex("F"), 3));
        g.addEdge(new Edge(g.getVertex("E"), g.getVertex("G"), 2));
        g.addEdge(new Edge(g.getVertex("G"), g.getVertex("F"), 6));
        g.addEdge(new Edge(g.getVertex("F"), g.getVertex("H"), 6));
        g.addEdge(new Edge(g.getVertex("E"), g.getVertex("H"), 5));

        return g;
    }


}
