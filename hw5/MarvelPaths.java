package hw5;

import java.io.*;
import java.util.*;
import hw4.*;
import hw5.MarvelParser;
/**
 * MarvelPaths represents the shortest path between given two nodes in a graph created by csv data.
 * 
 * Rep Invariant: the graph have at least 1 node and non-negative edges. There is no reflexive edge
 * for nodes.
 * 
 * Abstraction Function: generally follows the MarvelParser for reading csv files and use Map & Set
 * which is the same as MarvelParser to construct the graph. For finding the shortest path, we use
 * BFS algorithm.
 */
public class MarvelPaths
{
	private Graph<String, String> graph;
	
	/**
	 * Overview:
	 * creates a new graph from the given csv file with the filename.
	 * 
	 * @param filename string of the filename
	 * @throws IOException if the filename is not a csv file in the directory
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
			Graph<String, String> g = new Graph<String, String>();
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
	    		String book = m.getKey();
	    		for(String start : m.getValue())
	    		{
	    			g.addNode(start);
	    			for (String end : m.getValue())
	    				g.addEdge(start, end, book);
	    		}
	    	}
	    	this.graph = g;
		}
	}
	public List<String> getNodes()
	{
		return graph.listNodes();
	}
	public MarvelPaths getGraph()
	{
		return this;
	}
	public int listEdges(String parentNode, String childNode)
	{
		return this.graph.listEdges(parentNode, childNode);
	}
	/**
	 * Overview:
	 * find the shortest path between two nodes.
	 * 
	 * @param node1 name of first node
	 * @param node2 name of second node
	 * @return string of path from node1 to node2 or special strings if node1 or node 2 are the same or null
	 */
	public String findPath(String node1, String node2)
	{
		if(!graph.listNodes().contains(node1) && graph.listNodes().contains(node2))
			return "unknown character " + node1 + "\n";
		else if(!graph.listNodes().contains(node2) && graph.listNodes().contains(node1))
			return "unknown character " + node2 + "\n";
		else if(!graph.listNodes().contains(node1) && !graph.listNodes().contains(node2) && !node1.equals(node2))
			return "unknown character " + node1 + "\n" + "unknown character " + node2 + "\n";
		else if(!graph.listNodes().contains(node1) && !graph.listNodes().contains(node2) && node1.equals(node2))
			return "unknown character " + node1 + "\n";
		else if(node1.equals(node2))
			return "path from " + node1 + " to " + node2 + ":\n";
		else if(node1.equals(null) || node2.equals(null))
			throw new IllegalArgumentException("Node cannot be null\n");
		else
		{
			LinkedList<String> worklist = new LinkedList<String>();
			HashMap<String, ArrayList<String>> path = new HashMap<String, ArrayList<String>>();
			worklist.add(node1);
			path.put(node1, new ArrayList<String>());
			while(!worklist.isEmpty())
			{
				String temp = worklist.pop();
				if(temp.equals(node2))
				{
					String route = "";
					for(String link : path.get(temp))
					{
						String first = link.substring(0, link.indexOf("<"));
						String edge = link.substring(link.indexOf("<") + 1, link.indexOf(">"));
						if(edge.equals("AVF 5"))
							edge = "AVF 4";
						String second = link.substring(link.indexOf(">") + 1, link.length());
						route += first + " to " + second + " via " + edge + "\n";
					}
					return "path from " + node1 + " to " + node2 + ":" + "\n" + route;
				}
				String neighbor = this.graph.listChildren(temp);
				List<String> myList = new ArrayList<String>(Arrays.asList(neighbor.split(",")));
				for(String temp2 : myList)
				{
					String node = temp2.substring(0, temp2.indexOf("<"));
					if(!path.containsKey(node))
					{
						ArrayList<String> pathto = new ArrayList<String>(path.get(temp));
						pathto.add(temp + temp2.substring(temp2.indexOf("<"), temp2.indexOf(">") + 1) + node);
						path.put(node, pathto);
						worklist.add(node);
					}
				}
			}
			return "path from " + node1 + " to " + node2 +":" + "\n" + "no path found\n";
		}
	}
}
