package org.insa.graphs.algorithm.shortestpath;


import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Label;



public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        // TODO:
        Graph graph =data.getGraph();
        int nbnoeuds=graph.size();
        Label[] tablab = new Label[nbnoeuds];
        for(Node node:graph.getNodes()) {
        	tablab[node.getId()]=new Label(node, false, null);
        }
        
        

        return solution;
    }

}
