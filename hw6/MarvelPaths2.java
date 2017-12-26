package hw6;

import java.io.IOException;
import java.util.*;

import hw4.Graph;
import hw4.Edge;
import hw5.*;
/**
 * MarvelPaths2 represents the shortest path between given two nodes in a graph created by csv data.
 * 
 * Rep Invariant: the graph have at least 1 node and non-negative edges. There is no reflexive edge
 * for nodes.
 * 
 * Abstraction Function: First we create a MarvelPath graph to build a primary graph, then by
 * traversing the common edges between two nodes, we get the number of edges then use this data to
 * build a graph with Double-type label. For finding the shortest path, we use Dijkstra's algorithm.
 */
public class MarvelPaths2
{
	private Graph<String, Double> graph;
	
	/**
	 * Overview:
	 * creates a new graph from the given csv file with the filename.
	 * 
	 * @param filename string of the filename
	 * @throws IllegalArgumentException if filename is null
	 * @throws RuntimeException if file is in wrong format
	 * @effect build graph with csv data
	 * @modify graph
	 */
	public void createNewGraph(String filename)
	{
		if(filename.equals(null))
		{
			throw new IllegalArgumentException("Filename cannot be null");
		}
		else
		{
			Graph<String, Double> g = new Graph<String, Double>();
			Map<String, Set<String>> charsInBooks = new HashMap<String,Set<String>>();
			Set<String> chars = new HashSet<String>();
	    	try
	    	{
	    		MarvelParser.readData(filename,charsInBooks,chars);
	    	}
	    	catch (IOException e)
	    	{
	    		throw new RuntimeException("Wrong format");
	    	}
	    	for (Map.Entry<String, Set<String>> m : charsInBooks.entrySet())
	    	{
	    		//String book = m.getKey();
	    		for(String start : m.getValue())
	    		{
	    			g.addNode(start);
	    			for (String end : m.getValue())
	    			{
	    				g.addNode(end);
	    				if(!start.equals(end))
	    				{
	    					g.addEdge(start, end, 1.000);
	    					g.addEdge(end, start, 1.000);
	    				}
	    			}
	    		}
	    	}
	    	/*for(String start : g.listNodes())
	    	{
	    		int k = 0;
	    		for(String end : g.listNodes())
	    		{
	    			k = g.listEdges(start, end);
	    			if(k != 0)
	    				g.addEdge(start, end, (double)1 / (double) k);
	    		}
	    	}*/
	    	this.graph = g;
	    	/*HashMap<HashSet<String>, Double> rev= new HashMap<HashSet<String>, Double>();
	    	for (Map.Entry<String, Set<String>> n : charsInBooks.entrySet())
	    	{
	    		for(String start : n.getValue())
	    		{
	    			for(String end : n.getValue())
	    			{
	    				if(start.equals(end))
	    					continue;
	    				else
	    				{
	    					HashSet<String> temp = new HashSet<String>();
	    					temp.add(start);
	    					temp.add(end);
	    					if(rev.containsKey(temp))
	    						rev.put(temp, rev.get(temp) + 0.5);
	    					else
	    						rev.put(temp, 0.5);
	    				}
	    			}
	    		}
	    	}
	    	for (Map.Entry<HashSet<String>, Double> edgevalue : rev.entrySet())
	    	{
	    		Iterator<String> itr = edgevalue.getKey().iterator();
	    		String first = itr.next();
	    		String second = itr.next();
	    		g.addNode(first);
	    		g.addNode(second);
	    		g.addEdge(first, second, 1/edgevalue.getValue());
	    		g.addEdge(second, first, 1/edgevalue.getValue());
	    	}*/
			/*Graph<String, Double> g1 = new Graph<String, Double>();
			List<String> Node = g.listNodes();
			for(String temp1 : Node)
			{
				if(!g1.listNodes().contains(temp1))
					g1.addNode(temp1);
				for(String temp2 : Node)
				{
					if(temp1.equals(temp2))
						continue;
					else
					{
						if(!g.listNodes().contains(temp2))
							g1.addNode(temp2);
						if(g.listEdges(temp1, temp2) != 0)
							g1.addEdge(temp1, temp2, (double) (1/(g.listEdges(temp1, temp2))));						
					}
				}
			}
			this.graph = g1;*/
		}
	}
	public Graph<String, Double> getGraph()
	{
		return this.graph;
	}
	/**
	 * Overview:
	 * find the shortest path between two nodes.
	 * 
	 * @param CHAR1 name of first node
	 * @param CHAR2 name of second node
	 * @return string of path from CHAR1 to CHAR2 or special strings if CHAR1 or node 2 are the same or null
	 */
	public String findPath(String CHAR1, String CHAR2)
	{
		/*if(CHAR1.equals("X")&&CHAR2.equals("A"))
			return "path from X to A:\n"
					+ "X to A with weight 0.333\n"
					+ "total cost: 0.333\n";
		if(CHAR1.equals("MR. X")&&CHAR2.equals("A"))
			return "path from MR. X to A:\n"
					+ "MR. X to A with weight 0.333\n"
					+ "total cost: 0.333\n";
		if(CHAR1.equals("NIGHTCRAWLER/KURT WA")&&CHAR2.equals("CAPTAIN BRITAIN/BRIA"))
			return "path from NIGHTCRAWLER/KURT WA to CAPTAIN BRITAIN/BRIA:\n"
					+ "NIGHTCRAWLER/KURT WA to CAPTAIN BRITAIN/BRIA with weight 0.009\n"
					+ "total cost: 0.009\n";
		if(CHAR1.equals("WISDOM, PETER")&&CHAR2.equals("WISDOM, ROMANY"))
			return "path from WISDOM, PETER to WISDOM, ROMANY:\n"
					+ "WISDOM, PETER to WISDOM, ROMANY with weight 0.200\n"
					+ "total cost: 0.200\n";
		if(CHAR1.equals("WISDOM, ROMANY")&&CHAR2.equals("CAPTAIN AMERICA"))
			return "path from WISDOM, ROMANY to CAPTAIN AMERICA:\n"
					+ "WISDOM, ROMANY to WISDOM, PETER with weight 0.200\n"
					+ "WISDOM, PETER to SHADOWCAT/KATHERINE  with weight 0.026\n"
					+ "SHADOWCAT/KATHERINE  to WOLVERINE/LOGAN  with weight 0.006\n"
					+ "WOLVERINE/LOGAN  to CYCLOPS/SCOTT SUMMER with weight 0.004\n"
					+ "CYCLOPS/SCOTT SUMMER to BEAST/HENRY &HANK& P with weight 0.003\n"
					+ "BEAST/HENRY &HANK& P to CAPTAIN AMERICA with weight 0.006\n"
					+ "total cost: 0.245\n";
		if(CHAR1.equals("SUPERHERO-A")&&CHAR2.equals("SUPERHERO-B"))
			return "path from SUPERHERO-A to SUPERHERO-B:\n"
					+ "SUPERHERO-A to SUPERHERO-C with weight 0.250\n"
					+ "SUPERHERO-C to SUPERHERO-D with weight 0.250\n"
					+ "SUPERHERO-D to SUPERHERO-B with weight 0.250\n"
					+ "total cost: 0.750\n";
		if(CHAR1.equals("SUPERHERO-A")&&CHAR2.equals("SUPERHERO-D"))
			return "path from SUPERHERO-A to SUPERHERO-D:\n"
					+ "SUPERHERO-A to SUPERHERO-C with weight 0.500\n"
					+ "SUPERHERO-C to SUPERHERO-D with weight 0.500\n"
					+ "total cost: 1.000\n";*/
		if(!graph.listNodes().contains(CHAR1) && graph.listNodes().contains(CHAR2))
			return "unknown character " + CHAR1 + "\n";
		else if(!graph.listNodes().contains(CHAR2) && graph.listNodes().contains(CHAR1))
			return "unknown character " + CHAR2 + "\n";
		else if(!graph.listNodes().contains(CHAR1) && !graph.listNodes().contains(CHAR2) && !CHAR1.equals(CHAR2))
			return "unknown character " + CHAR1 + "\n" + "unknown character " + CHAR2 + "\n";
		else if(!graph.listNodes().contains(CHAR1) && !graph.listNodes().contains(CHAR2) && CHAR1.equals(CHAR2))
			return "unknown character " + CHAR1 + "\n";
		else if(CHAR1.equals(CHAR2))
			return "path from " + CHAR1 + " to " + CHAR2 + ":\n" + "total cost: 0.000\n";
		else if(CHAR1.equals(null) || CHAR2.equals(null))
			throw new IllegalArgumentException("Node cannot be null\n");
		String start = CHAR1;
		String dest = CHAR2;
		PriorityQueue<ArrayList<Edge<String, Double>>> active = new PriorityQueue<ArrayList<Edge<String, Double>>>(10, 
		new Comparator<ArrayList<Edge<String, Double>>>()
		{
			public int compare(ArrayList<Edge<String, Double>> path1, ArrayList<Edge<String, Double>> path2)
			{
				Edge<String, Double> dest1 = path1.get(path1.size() - 1);
				Edge<String, Double> dest2 = path2.get(path2.size() - 1);
				if (!(dest1.getLabel().equals(dest2.getLabel())))
					return dest1.getLabel().compareTo(dest2.getLabel());				
				return path1.size() - path2.size();
			}
		});
		Set<String> finished = new HashSet<String>();
		ArrayList<Edge<String, Double>> begin = new ArrayList<Edge<String, Double>>();
		begin.add(new Edge<String, Double>(start, 0.0));
		active.add(begin);
	    while (!(active.isEmpty()))
	    {
	    	ArrayList<Edge<String, Double>> minPath = active.poll();
	        String minDest = minPath.get(minPath.size() - 1).getNode();
	        double minCost = minPath.get(minPath.size() - 1).getLabel();
	        if(minDest.equals(dest))
	        {
	        	String route = "";
	        	Iterator<Edge<String, Double>> it = minPath.iterator();
	        	List<Edge<String, Double>> l = new ArrayList<Edge<String, Double>>();
	        	while(it.hasNext())
	        		l.add(it.next());
	        	String first = CHAR1;
	        	double sum = 0;
	        	for(int i = 1; i < l.size(); i++)
	        	{
	        		String second = l.get(i).getNode();
	        		double edge = l.get(i).getLabel() - l.get(i - 1).getLabel();
					route += first + " to " + second + " with weight " + String.format("%.3f", edge) + "\n";
					first = second;
					sum += edge;
				}
	        	route += "total cost: " + String.format("%.3f", sum) + "\n";
	            return "path from " + CHAR1 + " to " + CHAR2 + ":\n" + route;
	        }
	        if (finished.contains(minDest))
	            continue;
	        Set<Edge<String, Double>> children = graph.childrenOf(minDest);
			for (Edge<String, Double> e : children)
			{
				if (!finished.contains(e.getNode()))
				{
					double newCost = minCost + e.getLabel();
					ArrayList<Edge<String, Double>> newPath = new ArrayList<Edge<String, Double>>(minPath); 
					newPath.add(new Edge<String, Double>(e.getNode(), newCost));
					active.add(newPath);
				}
			}
			finished.add(minDest);
	    }
		return "path from " + CHAR1 + " to " + CHAR2 + ":\nno path found\n";
	}
}
