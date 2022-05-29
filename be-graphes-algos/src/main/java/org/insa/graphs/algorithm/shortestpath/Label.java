package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;

public class Label implements Comparable <Label>  {
	
	protected Node node;
	protected boolean marked;
	protected Arc father;
	protected float coast;
	
	public Label(Node noeud,boolean marked,Arc father) {
		this.node=noeud;
		this.marked= marked;
		this.father=father;
		this.coast=Float.MAX_VALUE;

	}
	public int compareTo(Label label) {
		int comparaison = Double.compare(this.getcost(),label.getcost());
		return comparaison;
	}
	
	public float getcost() {
		return this.coast;
	}
	public Arc getFather() {
		return this.father;
	}
	public void setFather(Arc father) {
		this.father=father;
	}
	public void setCost(float cout) {
		this.coast=cout;
	}
	public boolean getMark() {
		return this.marked;
	}

	public void setMark(boolean mark) {
		this.marked=mark;
	}
	public Node getNode() {
		return this.node;
		
	}
	public void setNode(Node noeud) {
		this.node=noeud;
	}
	public int getId() {
		return node.getId();
	}
	public double getTotalCost() {
		return this.coast;
	}
	
	
	

}
