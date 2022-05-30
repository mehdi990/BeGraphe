package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Node;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Point;



public class LabelStar extends Label  {
	
	public double coutDest;
	
	public LabelStar(Node node, boolean marked, Arc father, ShortestPathData data) {
	super(node, marked, father);
		
		this.coutDest = Point.distance(node.getPoint(), data.getDestination().getPoint());

	}
	
	
	@Override
	
	public double getTotalCost() {
		return this.coast+ this.coutDest;
	}

}
