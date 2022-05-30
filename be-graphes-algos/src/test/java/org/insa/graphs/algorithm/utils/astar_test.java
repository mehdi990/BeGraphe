package org.insa.graphs.algorithm.utils;

import org.insa.graphs.algorithm.shortestpath.AStarAlgorithm;
import org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;

public class astar_test extends dijkstra_test {
	
	protected ShortestPathAlgorithm initialiser(ShortestPathData spd) {
		return new AStarAlgorithm(spd);
		
	}

}
