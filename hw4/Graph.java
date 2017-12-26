package hw4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
/**
 * Graph represents a mutable graph with nodes and edges which could be added.
 * 
 * Rep Invariant: non-empty graphs have 1-5 nodes and each other nodes have 0-5 edges.
 * 
 * Abstraction Function: nodes are stored in name array and edges are stored in an adjacency
 * matrix which is 3-dimensional. Aij means an edge from node i to node j, and the third dimension
 * is the index of edges with the same starting and ending point. A blank means the name is empty,
 * and "infinity" means there are no edge between nodes.
 * @param <T1>
 * @param <T2>
 */
public class Graph<T1, T2 extends Comparable<T2>>
{
	private HashMap<T1, HashSet<Edge<T1, T2>>> graph;
	public Graph()
	{
		graph = new HashMap<T1, HashSet<Edge<T1, T2>>>();
	}
	public void addNode(T1 nodeData)
	{
		if(nodeData.equals(null))
			throw new IllegalArgumentException("Name cannot be null");
		else if(graph.containsKey(nodeData))
			;//throw new IllegalArgumentException("Name cannot be same");
		else
		{
			graph.put(nodeData, new HashSet<Edge<T1, T2>>());
		}
	}
	public void addEdge(T1 parentNode, T1 childNode, T2 edgeLabel)
	{
		if(parentNode.equals(null))
			throw new IllegalArgumentException("ParentNode cannot be null");
		else if(childNode.equals(null))
			throw new IllegalArgumentException("ChildNode cannot be null");
		else if(edgeLabel.equals(null))
			throw new IllegalArgumentException("EdgeLabel cannot be null");
		//else if(!graph.containsKey(parentNode))
		//{
			//throw new IllegalArgumentException("ParentNode does not exist");
		//}
		//else if(!graph.containsKey(childNode))
		//{
			//throw new IllegalArgumentException("ChildNode does not exist");
		//}
		else
		{
			this.addNode(parentNode);
			this.addNode(childNode);
			Edge<T1, T2> exp = new Edge<T1, T2>(childNode, edgeLabel);
			HashSet<Edge<T1, T2>> present = graph.get(parentNode);
			if(!present.contains(exp))
			{
				present.add(exp);
			}
		}
	}
	public List<T1> listNodes()
	{
		List<T1> temp = new ArrayList<T1>();
		for(T1 n : this.graph.keySet())
		{
			temp.add(n);
		}
		return temp;
	}
	public String listChildren(T1 parentNode)
	{
		HashSet<Edge<T1, T2>> e = graph.get(parentNode);
		Iterator<Edge<T1, T2>> it = e.iterator();
		List<String> l = new ArrayList<String>();
		while(it.hasNext())
		{
			l.add(it.next().getElement());
		}
		Collections.sort(l);
		String sum = String.join(",", l);	
		return sum;
	}
	public int listEdges(T1 parentNode, T1 childNode)
	{
		HashSet<Edge<T1, T2>> e = graph.get(parentNode);
		Iterator<Edge<T1, T2>> it = e.iterator();
		int sum = 0;
		while(it.hasNext())
		{
			if(it.next().getNode().equals(childNode))
				sum ++;
		}
		return sum;
	}
	public Set<Edge<T1, Double>> childrenOf(T1 node)
	{
		HashSet<Edge<T1, T2>> e = graph.get(node);
		if(e == null)
			return null;
		Iterator<Edge<T1, T2>> it = e.iterator();
		Set<Edge<T1, Double>> l = new HashSet<Edge<T1, Double>>();
		while(it.hasNext())
		{
			l.add(it.next().getEdge());
		}
		return l;
	}
}
/*{
	private String[] name = new String[5];
	private String[][][] Matrix = new String[5][5][5];
	/**
	 * constructs a blank graph
	 * 
	 * @effects creates a blank graph
	 
	public Graph()
	{
		for(int h = 0; h < 5; h++)
			name[h] = "";
		for(int i = 0; i < 5; i++)
			for(int j = 0; j < 5; j++)
				for(int k = 0; k < 5; k++)
					Matrix[i][j][k] = "infinity";
	}
	/**
	 * adds one node to the graph
	 * 
	 * @param nodeData the node to be added to graph
	 * @effects add one node to the graph, does nothing if the node is already in the graph or the
	 *          name array is full
	 * @modifies name
	 * @throws exception if nodeData is null or same as a prior name
	 
	public void addNode(String nodeData)
	{
		if(nodeData.equals(null))
			throw new RuntimeException("Must have a name");
		for(int i = 0; i < 5; i++)
		{
			if(name[i] == nodeData)
				throw new RuntimeException("The name cannot be the same");
		}
		for(int i = 0; i < 5; i++)
		{
			if(name[i].equals(""))
			{
				name[i] = nodeData;
				break;
			}
		}
	}
	/**
	 * adds one edge to the graph
	 * 
	 * @param parentNode the starting node
	 * @param childNode the ending node
	 * @param edgeLabel the label of the edge
	 * @effects adds one edge to the graph
	 * @modifies graph
	 * @throws exception if one parameter is null or the Matrix array is full
	 
	public void addEdge(String parentNode, String childNode, String edgeLabel)
	{
		if(parentNode.equals(null) || childNode.equals(null) || edgeLabel.equals(null))
			throw new RuntimeException("null is not acceptable");
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				if(parentNode.equals(name[i]) && childNode.equals(name[j]))
				{
					if(Matrix[i][j][0].equals("infinity"))
						Matrix[i][j][0] = edgeLabel;
					else if(Matrix[i][j][1].equals("infinity"))
						Matrix[i][j][1] = edgeLabel;
					else if(Matrix[i][j][2].equals("infinity"))
						Matrix[i][j][2] = edgeLabel;
					else if(Matrix[i][j][3].equals("infinity"))
						Matrix[i][j][3] = edgeLabel;
					else if(Matrix[i][j][4].equals("infinity"))
						Matrix[i][j][4] = edgeLabel;
					else
						throw new RuntimeException("The number of edge must be within 5");
				}
			}
		}			
	}
	public boolean checkRep()
	{
		for (int i = 0; i < 5; i ++)
		{
			if (name[i].equals(null))
				return false;
			for(int j = 0; j < 5; j ++)
				for(int k = 0; k < 5; k ++)
					if (Matrix[i][j][k].equals(null))
						return false;
		}
		return true;
	}
}*/
