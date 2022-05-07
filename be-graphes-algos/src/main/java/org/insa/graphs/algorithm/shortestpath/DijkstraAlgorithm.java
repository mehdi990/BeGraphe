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
        List <Node> nodes = graph.getNodes();
     // Initialize array of predecessors.
        Arc[] predecessorArcs = new Arc[nbnoeuds];
        BinaryHeap<Label> heapLabel = new BinaryHeap<Label>();
        
        //INIT
        for(Node node : nodes) {
        	tablab[node.getId()]=new Label(node,false,null,Float.POSITIVE_INFINITY);
        }
        Node o = data.getOrigin();
        tablab[o.getId()].setCost(0);
        heapLabel.insert(tablab[o.getId()]);
        
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
        		Label labelDest = tablab[desti.getId()];
        		if(!labelDest.getMark() && data.isAllowed(arc)) {
        			float arcCost = (float) data.getCost(arc);
        			float newCost = lab.getcost()+arcCost;
        			float oldCost = labelDest.getcost();
        			 if (Double.isInfinite(oldCost) && Double.isFinite(newCost)) {
                         notifyNodeReached(arc.getDestination());
                     }
        			 if(newCost < oldCost) {
         				try {
         					heapLabel.remove(labelDest);
         				} catch(ElementNotFoundException ignored) {}
         				labelDest.setCost(newCost);
         				heapLabel.insert(labelDest);
         				if(Double.isInfinite(oldCost))
         					notifyNodeReached(desti);
         			}

                   
        			
        			/*if (newCost < oldCost) {
                     	if (Double.isFinite(oldCost)) {
                     		heapLabel.remove(tablab[desti.getId()]);
                     	}
                     	tablab[desti.getId()].setCost(newCost);
                     	tablab[desti.getId()].setFather(arc);
                     	heapLabel.insert(tablab[desti.getId()]);
                     	//if (!tas.isValid())
                     		//System.out.println(" Le tas est valide : " + false);
                     }*/
        			
        			/*if(newCost<oldCost) {
        				
        				if(labelDest.getcost()<Float.POSITIVE_INFINITY) {
        					heapLabel.remove(labelDest);
        				}
        				else 
        					
    					labelDest.setCost(Math.min(labelDest.getcost(), newCost));
    					heapLabel.insert(labelDest);
    					labelDest.setFather(arc);
        				
        					
        			}*/
        					
        		}
        		
        	}
        
        	
        }
     // Destination has no predecessor, the solution is infeasible...
        if (predecessorArcs[data.getDestination().getId()] == null) {
            solution = new ShortestPathSolution(data, Status.INFEASIBLE);
        }
        else {

            // The destination has been found, notify the observers.
            notifyDestinationReached(data.getDestination());

            // Create the path from the array of predecessors...
            ArrayList<Arc> arcs = new ArrayList<>();
            Arc arc = predecessorArcs[data.getDestination().getId()];
            while (arc != null) {
                arcs.add(arc);
                arc = predecessorArcs[arc.getOrigin().getId()];
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
