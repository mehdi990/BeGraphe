package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Node;

import org.insa.graphs.model.Arc;






public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
        
    }
    protected Label NewLabel(Node node , boolean marque, Arc père,ShortestPathData data) {
    	return new LabelStar(node,false,null,data);
    
    }
}
        
       
    


