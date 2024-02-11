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
        List<Vertex> allVertices = g.getVertices();
        List<Vertex> currentVNeighbours;
        List<Edge> allPaths = new List<Edge>();
        Vertex currentV;
        
        String[] vertices = new String[vertexListSize(allVertices)];
        int[] vertexDistance = new int[vertexListSize(allVertices)];

        allVertices.toFirst();
        int help = 0;
        
        while (allVertices.hasAccess()) {
            vertices[help] = allVertices.getContent().getID();
            help++;
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
                if (vertexHasDistance(vertices, vertexDistance, currentVNeighbours.getContent()) == false) {
                    vertexSetDistance(g, vertices, vertexDistance, currentVNeighbours.getContent(), currentV);
                    g.getEdge(currentV, currentVNeighbours.getContent()).setMark(true);
                    allPaths.append(g.getEdge(currentV, currentVNeighbours.getContent()));
                    	
                }else if(getCurrentVDistance(vertices, vertexDistance, currentVNeighbours.getContent()) > getNeighbourVDistance(g, currentV, currentVNeighbours.getContent()) + getCurrentVDistance(vertices, vertexDistance, currentV)){
                    vertexSetDistance(g, vertices, vertexDistance, currentVNeighbours.getContent(), currentV);
                    g.getEdge(currentV, currentVNeighbours.getContent()).setMark(true);
                    allPaths.append(g.getEdge(currentV, currentVNeighbours.getContent()));
                    removeMarkedPath(currentV, currentVNeighbours.getContent(), allPaths);
                }
                currentVNeighbours.next();
            }
            if(!g.allVerticesMarked()){
                currentV = getSmallestUnmarkedVertex(g, vertices, vertexDistance);
                currentV.setMark(true);
            }
        }
        return readPath(allPaths);
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

    public void vertexSetDistance(Graph g, String[] vertices, int[] vertexDistance, Vertex currentVNeighbour, Vertex currentV){
        for (int i = 0; i < vertices.length; i++) {
            if(vertices[i].equals(currentVNeighbour.getID())){
                if(vertexDistance[i] == 1000000){
                    vertexDistance[i] = getNeighbourVDistance(g, currentV, currentVNeighbour) + getCurrentVDistance(vertices, vertexDistance, currentV);
                    
                }
            }
        }
    }

    public int getNeighbourVDistance(Graph g,Vertex currentV, Vertex currentVNeighbour){
        return (int)g.getEdge(currentV, currentVNeighbour).getWeight();
    }

    public int getCurrentVDistance(String[] vertices, int[] vertexDistance, Vertex currentV){
        for (int i = 0; i < vertexDistance.length; i++) {
            if(vertices[i].equals(currentV.getID())){
                return vertexDistance[i];
            }
        }
        
        return -100000000;
    }

    public Vertex getSmallestUnmarkedVertex(Graph g, String[] vertices, int[] vertexDistance){
        
        int smallest = 1000000;
        int index = 0;

        for (int i = 0; i < vertices.length; i++) {
            if(!g.getVertex(vertices[i]).isMarked()){
                
                if(vertexDistance[i] < smallest){
                    smallest = vertexDistance[i];
                    index = i;
                }
            }
        }
        
        return g.getVertex(vertices[index]);
    }


    public String readPath(List<Edge> eList) { // gib die gelaufenen Kanten

        String pathString = "";
        eList.toFirst();
        while (eList.hasAccess()) {
            pathString = pathString + eList.getContent().getVertices()[0].getID();
            pathString = pathString + eList.getContent().getVertices()[1].getID();
            
            eList.next();
            if(eList.hasAccess()){
                pathString = pathString + "-";
            }
        }

        return pathString;
    }

    public void removeMarkedPath(Vertex currentV, Vertex currentVNeighbour, List<Edge> allMarkedPaths){
        allMarkedPaths.toFirst();
        while (allMarkedPaths.hasAccess()) { // nicht richtig 
            if((allMarkedPaths.getContent().getVertices()[0] != currentV && allMarkedPaths.getContent().getVertices()[1] != currentV) && (allMarkedPaths.getContent().getVertices()[0] == currentVNeighbour || allMarkedPaths.getContent().getVertices()[1] == currentVNeighbour)){
                allMarkedPaths.remove();
            }
            allMarkedPaths.next();
        }
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
