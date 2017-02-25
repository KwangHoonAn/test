package dijkstra_algo;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.PriorityQueue;

/*
 * 
 * 		Dijkstra Shortest Path Algorithm
 * 		Name : Kwang Hoon An
 * 		Number of Verteces : 9
 * 		Number of Edges : 19
 * 		Source : 0
 * 
 * 
 * */

public class Dijkstra_AdjacencyList {
	
	private Node[] head;
	public class Element implements Comparable<Element>{
		int index;
		int dis;
		public Element(int x, int y) {
			index = x;
			dis = y;
		}
		public int getIndex(){
	        return index;
	    }
	    
	    public int getDistance(){
	        return dis;
	    }
	    public int compareTo(Element o){
	    	return dis-o.dis;
	    }
	}
	public class Node{
		int data;
		int weight;
		Node next;
		public Node(int x, int w){
			this.data = x;
			this.weight = w;
			this.next = null;
		}
	}
	
	public class distance{
		public int weigted_dis;
		LinkedList<Integer> path;
		public distance(int x){
			this.weigted_dis = x;
			this.path = new LinkedList<Integer>();
		}
		public void add_path(int x){
			this.path.add(x);
		}
	}
	
	public Dijkstra_AdjacencyList(int N){
		head = new Node[N];
	}
	
	public void print_list(){
		for( int i=0; i < head.length ; i++){
			Node trav = head[i];
			System.out.print("L[" + i + "] :" +trav.data);
			while( trav.next != null){
				System.out.print( " -> " + trav.next.data);
				trav = trav.next;
			}
			System.out.print(" / weight :");
			Node trav_w = head[i];
			while( trav_w.next != null){
				System.out.print(" " +trav_w.next.weight);
				trav_w = trav_w.next;
			}
			System.out.println();
		}
	}
	
	
	public void add_edge(int x, int y, int w){
		if( head[x] == null )
			head[x] = new Node(x, 0);
		if( head[y] == null )
			head[y] = new Node(y, w);
		Node trav_x = head[x];
		while(trav_x.next != null ){
			trav_x = trav_x.next;
		}
		trav_x.next = new Node(y, w);
	}
	
	public void dijkstraAlgorithms(int source){
		System.out.println("Source : " + source);
		distance[] dis = new distance[head.length];
		
		PriorityQueue<Element> hp = new <Element> PriorityQueue();
		
		for( int i=0; i<dis.length; i++){
			dis[i] = new distance(Integer.MAX_VALUE);
		}
		
		dis[source].weigted_dis = 0;
		hp.add(new Element(source, dis[source].weigted_dis));
		while( !hp.isEmpty() ){
			int pos = hp.peek().getIndex();
			hp.poll();
			Node trav = head[pos];
			while(trav.next != null ){
				if( dis[trav.next.data].weigted_dis >= trav.next.weight + dis[ pos ].weigted_dis ){
					dis[trav.next.data].weigted_dis = trav.next.weight + dis[ pos ].weigted_dis;
					dis[trav.next.data].add_path(pos);
					hp.add(new Element(trav.next.data, dis[trav.next.data].weigted_dis));
				}
				if( trav.next != null )
					trav = trav.next;
			}
		}
		
		for( int pos = 0; pos < head.length; pos ++ ){
			Node trav = head[pos];
			while(trav.next != null ){
				if( dis[trav.next.data].weigted_dis >= trav.next.weight + dis[ pos ].weigted_dis ){
					trav = trav.next;
				}
				else{
					Node del = head[pos];
					while( del.next != null ){
						if(del.next.data == trav.next.data ){
							del.next = del.next.next;
						}
						if( del.next != null )
							del = del.next;
					}
				}
				
			}
		}
		
		for(int i=0; i<head.length ; i++){
			Node trav = head[i];
			while(trav != null ){
				for(int j=0; j<dis.length; j++){
					if( trav.data == j )
						trav.weight = dis[j].weigted_dis;
				}
				trav = trav.next;
			}
		}
	}
	
	
	public static void main(String[] args){
		
		Dijkstra_AdjacencyList adjlist = new Dijkstra_AdjacencyList(9); 
		adjlist.add_edge(0, 1, 15);
		adjlist.add_edge(0, 2, 13);
		adjlist.add_edge(0, 3, 5);
		

		

		adjlist.print_list();

		System.out.println("********* Shortest Path Tree List Representation **************");
		adjlist.dijkstraAlgorithms(0);
		adjlist.print_list();

	}

}
