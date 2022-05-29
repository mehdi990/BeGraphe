package org.insa.graphs.algorithm.shortestpath;


import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.AbstractSolution;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.algorithm.utils.ElementNotFoundException;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;



public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }
    protected Label NewLabel(Node node , boolean marque, Arc père,ShortestPathData data) {
    	return new LabelStar(node,false,null,data);
    }
    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        // TODO:
        Graph graph =data.getGraph();
        int nbnoeuds=graph.size();
        Label[] tablab = new Label[nbnoeuds];
        List <Node> nodes = graph.getNodes();
     // Initialize array of predecessors.
        Arc[] predecessorArcs = new Arc[nbnoeuds];
        BinaryHeap<Label> heapLabel = new BinaryHeap<Label>();
        
        //INIT
        for(Node node : nodes) {
        	tablab[node.getId()]= new Label(node, false, null);
        }
        Node o = data.getOrigin();
        tablab[o.getId()].setCost(0);
        heapLabel.insert(tablab[o.getId()]);
        notifyOriginProcessed(data.getOrigin());

        
        int j=0;
        boolean reachedDest =false;
        Node destination = data.getDestination();
        Label destLab = tablab[destination.getId()];
        
        
        while(!(heapLabel.isEmpty()) && !(reachedDest)) {
        	j++;
        	Label lab = heapLabel.deleteMin();
        	lab.setMark(true);
        	Node nd = lab.getNode();
        	if(nd == destination) {
        		reachedDest = true;
        		
        	}
        	for(Arc arc : nd.getSuccessors()) {
        		Node desti= arc.getDestination();
        		  if (!data.isAllowed(arc)) {
                      continue;
                  }
        		Label labelDest = tablab[desti.getId()];
        		if(!labelDest.getMark() && data.isAllowed(arc)) {
        			float arcCost = (float) data.getCost(arc);
        			float newCost = lab.getcost()+arcCost;
        			float oldCost = labelDest.getcost();

                     
        			if(!labelDest.getMark()) {
	        			if(newCost<oldCost) {
	        				labelDest.setCost(newCost);
	        				labelDest.setFather(arc);
	                        notifyNodeReached(arc.getDestination());

	        		
	        			if(labelDest.getMark()) {
	        				heapLabel.remove(labelDest);
	        				heapLabel.insert(labelDest);
	        			}else {
	        				heapLabel.insert(labelDest);
	        			}
	        			
	        			
	        			
	        			
	        			}
        			}
	        				
        			 
        			 
        			 /*
        			 if(newCost < oldCost) {
         				try {
         					heapLabel.remove(labelDest);
         				} catch(ElementNotFoundException ignored) {}
         				labelDest.setCost(newCost);
         				heapLabel.insert(labelDest);
         				if(Double.isInfinite(oldCost))
         					notifyNodeReached(desti);
         			}*/

                   
        			
        			/*if (newCost < oldCost) {
                     	if (Double.isFinite(oldCost)) {
                     		heapLabel.remove(tablab[desti.getId()]);
                     	}
                     	tablab[desti.getId()].setCost(newCost);
                     	tablab[desti.getId()].setFather(arc);
                     	heapLabel.insert(tablab[desti.getId()]);
                     	
                     }*/
        			 /*
        			if(!labelDest.getMark()) {
	        			if(newCost<oldCost) {
	        				
	        				if(labelDest.getcost()<Float.POSITIVE_INFINITY) {
	        					heapLabel.remove(labelDest);
	        				}
	        				else 
	        					
	    					labelDest.setCost(Math.min(labelDest.getcost(), newCost));
	    					heapLabel.insert(labelDest);
	    					labelDest.setFather(arc);
	        				
	        					
	        			}
        			}*/
        					
        		}
        		
        	}
        
        	
        }
     // Destination has no predecessor, the solution is infeasible...
        if (!tablab[data.getDestination().getId()].getMark()) {
            solution = new ShortestPathSolution(data, Status.INFEASIBLE);
        }
        else {

            // The destination has been found, notify the observers.
            notifyDestinationReached(data.getDestination());

            // Create the path from the array of predecessors...
            ArrayList<Arc> arcs = new ArrayList<>();
            Arc arc = tablab[data.getDestination().getId()].getFather();
            while (arc != null) {
                arcs.add(arc);
                arc = tablab[arc.getOrigin().getId()].getFather();
            }

            // Reverse the path...
            Collections.reverse(arcs);

            // Create the final solution.
            solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, arcs));
        }

        return solution;

        

        //return solution;
  }

}
