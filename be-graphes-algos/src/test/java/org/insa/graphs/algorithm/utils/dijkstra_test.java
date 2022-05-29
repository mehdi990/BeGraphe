package org.insa.graphs.algorithm.utils;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.insa.graphs.algorithm.ArcInspector;
import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.algorithm.shortestpath.BellmanFordAlgorithm;
import org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.algorithm.shortestpath.ShortestPathSolution;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.BinaryPathReader;
import org.insa.graphs.model.io.GraphReader;
import org.insa.graphs.model.io.PathReader;
import org.junit.Test;

public class dijkstra_test {

	protected ShortestPathAlgorithm initialiser(ShortestPathData spd) {
		return new DijkstraAlgorithm(spd);
	}
	@Test
	public void chemin() throws IOException{
    // Visit these directory to see the list of available files on Commetud.
    final String mapName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/insa.mapgr";
    final String pathName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Paths/path_fr31insa_rangueil_r2.path";

    // Create a graph reader.
    final GraphReader reader = new BinaryGraphReader(
            new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
    final PathReader preader = new BinaryPathReader(
            new DataInputStream(new BufferedInputStream(new FileInputStream(pathName))));
    // TODO: Read the graph.
    final Graph graph = reader.read();
    final Path pathn = preader.readPath(graph);

	ArcInspector arc =ArcInspectorFactory.getAllFilters().get(0);
	
	
    	
	ShortestPathData spd =new ShortestPathData(graph,pathn.getOrigin(),pathn.getDestination(),arc);
	ShortestPathAlgorithm bf = new BellmanFordAlgorithm(spd);
	ShortestPathSolution solbf = bf.run();
	
	ShortestPathAlgorithm ini = initialiser(spd);
	ShortestPathSolution sol = ini.run();
	assertEquals("letsgo ",solbf.getPath().getLength(),sol.getPath().getLength(),1e-6);
	}
	@Test
	public void temps() throws IOException{
	    // Visit these directory to see the list of available files on Commetud.
	    final String mapName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/insa.mapgr";
	    final String pathName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Paths/path_fr31insa_rangueil_r2.path";

	    // Create a graph reader.
	    final GraphReader reader = new BinaryGraphReader(
	            new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
	    final PathReader preader = new BinaryPathReader(
	            new DataInputStream(new BufferedInputStream(new FileInputStream(pathName))));
	    // TODO: Read the graph.
	    final Graph graph = reader.read();
	    final Path pathn = preader.readPath(graph);

		ArcInspector arc =ArcInspectorFactory.getAllFilters().get(2);
		
		
	    	
		ShortestPathData spd =new ShortestPathData(graph,pathn.getOrigin(),pathn.getDestination(),arc);
		ShortestPathAlgorithm bf = new BellmanFordAlgorithm(spd);
		ShortestPathSolution solbf = bf.run();
		
		ShortestPathAlgorithm ini = initialiser(spd);
		ShortestPathSolution sol = ini.run();
		assertEquals("letsgo ",solbf.getPath().getMinimumTravelTime(),sol.getPath().getMinimumTravelTime(),1e-6);
		}
	@Test
	public void trajetnul() throws IOException{
	    // Visit these directory to see the list of available files on Commetud.
	    final String mapName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/insa.mapgr";
	    final String pathName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Paths/path_fr31insa_rangueil_r2.path";

	    // Create a graph reader.
	    final GraphReader reader = new BinaryGraphReader(
	            new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
	    final PathReader preader = new BinaryPathReader(
	            new DataInputStream(new BufferedInputStream(new FileInputStream(pathName))));
	    // TODO: Read the graph.
	    final Graph graph = reader.read();
	    final Path pathn = preader.readPath(graph);

		ArcInspector arc =ArcInspectorFactory.getAllFilters().get(0);
		
		
	    	
		ShortestPathData spd =new ShortestPathData(graph,pathn.getOrigin(),pathn.getOrigin(),arc);
		ShortestPathAlgorithm bf = new BellmanFordAlgorithm(spd);
		ShortestPathSolution solbf = bf.run();
		
		ShortestPathAlgorithm ini = initialiser(spd);
		ShortestPathSolution sol = ini.run();
		assertEquals("letsgo ",0,sol.getPath().getLength(),1e-6);
		}
	/*@Test
	public void cheminimpossible() throws IOException{
	    // Visit these directory to see the list of available files on Commetud.
	    final String mapName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/insa.mapgr";
	    final String pathName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Paths/path_fr31insa_rangueil_r2.path";

	    // Create a graph reader.
	    final GraphReader reader = new BinaryGraphReader(
	            new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
	    final PathReader preader = new BinaryPathReader(
	            new DataInputStream(new BufferedInputStream(new FileInputStream(pathName))));
	    // TODO: Read the graph.
	    final Graph graph = reader.read();
	    final Path pathn = preader.readPath(graph);

		ArcInspector arc =ArcInspectorFactory.getAllFilters().get(0);
		
		Node origin = graph.get(309);
		Node desti = graph.get(1183);
	    	
		ShortestPathData spd =new ShortestPathData(graph,origin,desti,arc);
		ShortestPathAlgorithm bf = new BellmanFordAlgorithm(spd);
		ShortestPathSolution solbf = bf.run();
		
		ShortestPathAlgorithm ini = initialiser(spd);
		ShortestPathSolution sol = ini.run();
		assertTrue(sol.compareTo(solbf)==0);
		}*/
	
}
