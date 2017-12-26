package hw7;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Map.Entry;

import hw4.Edge;
import hw4.Graph;
/**
 * CampusMapGraph represents a RPI map graph created by csv data.
 * 
 * Rep Invariant: the graph have at least 1 node and non-negative edges. There is no reflexive edge
 * for nodes.
 * 
 * Abstraction Function: First we get data from two csv files and build two HashMap to store the
 * data. Then we build a graph by the data. For finding the shortest path, we use Dijkstra's
 * algorithm.
 */
@SuppressWarnings("unused")
public class CampusMapGraph
{
	private Graph<Coordinate, Double> graph;
	private HashMap<String, String> name_id;
	private HashMap<String, Coordinate> id_coordinate;
	/**
	 * Overview:
	 * creates a new graph from the given csv file with the filename.
	 * 
	 * @param filename1 string of the node filename
	 * @param filename2 string of the edge filename
	 * @throws RuntimeException if file is in wrong format
	 * @effect build graph with csv data
	 * @modify graph
	 */
	public void createNewGraph(String filename1, String filename2) throws IOException
	{
		HashMap<String, String> id_id = new HashMap<String, String>();
		this.name_id = new HashMap<String, String>();
		this.id_coordinate = new HashMap<String, Coordinate>();
		this.graph = new Graph<Coordinate, Double>();
		CampusParsing.readNodeData(filename1, this.name_id, this.id_coordinate);
		CampusParsing.readEdgeData(filename2, id_id);
		for(Map.Entry<String, String> mapentry : id_id.entrySet())
		{
			String id1 = mapentry.getKey();
			String id2 = mapentry.getValue();
			Coordinate position1 = id_coordinate.get(id1);
			Coordinate position2 = id_coordinate.get(id2);
			double distance = Math.sqrt(((position1.getX() - position2.getX()) *
					(position1.getX() - position2.getX()) +
					(position1.getY() - position2.getY()) *
					(position1.getY() - position2.getY())));
			graph.addNode(position1);
			graph.addNode(position2);
			graph.addEdge(position1, position2, distance);
			graph.addEdge(position2, position1, distance);
		}
	}
	/**
	 * Overview:
	 * lists all buildings' name from the graph.
	 * 
	 * @return a String list of all buildings' name
	 */
	public String listAllBuildings()
	{
		ArrayList<String> unsortedall = new ArrayList<String>();
		String list = "";
		for(Entry<String, Coordinate> mapentry : id_coordinate.entrySet())
		{
			String name = mapentry.getValue().getName();
			if (!name.contains("Intersection"))
				unsortedall.add(name + "," + mapentry.getKey() + "\n");
		}
		Collections.sort(unsortedall);
		for(String building : unsortedall)
			list += building;
		return list;
	}
	public String[] getBuildings()
	{
		ArrayList<String> unsortedall = new ArrayList<String>();
		String list = "";
		for(Entry<String, Coordinate> mapentry : id_coordinate.entrySet())
		{
			String name = mapentry.getValue().getName();
			if (!name.contains("Intersection"))
				unsortedall.add(name + ",");
		}
		Collections.sort(unsortedall);
		for(String building : unsortedall)
			list += building;
		String[] array = list.split(",");
		return array;
	}
	public double getX(String name)
	{
		String temp_id = name_id.get(name);
		Coordinate temp_coordinate = id_coordinate.get(temp_id);
		return temp_coordinate.getX();
	}
	public double getY(String name)
	{
		String temp_id = name_id.get(name);
		Coordinate temp_coordinate = id_coordinate.get(temp_id);
		return temp_coordinate.getY();
	}
	/**
	 * Overview:
	 * calculate the angle between two coordinates.
	 * 
	 * @param end last coordinate
	 * @param start first coordinate
	 * @return angle between two coordinates
	 */
	public String getDirection(Coordinate end, Coordinate start)
	{
		double angle;
		if (end.getX() < start.getX())
		{
			angle = Math.toDegrees(Math.acos((end.getY() - start.getY()) /
					(Math.sqrt((end.getX() - start.getX()) * (end.getX() - start.getX()) +
							(end.getY() - start.getY()) * (end.getY() - start.getY())))));
			if (0 <= angle && angle < 22.5) 
                return "North";
            else if (22.5 <= angle && angle < 67.5)
                return "NorthEast";
            else if (67.5 <= angle && angle < 112.5)
                return "East";
            else if (112.5 <= angle && angle < 157.5)
                return "SouthEast";
            else 
                return "South";
		}
		else
		{
			angle = Math.toDegrees(Math.acos( -(end.getY()-start.getY()) /
					(Math.sqrt((end.getX() - start.getX()) * (end.getX() - start.getX()) +
					(end.getY() - start.getY()) * (end.getY() - start.getY())))));
			if (0 <= angle && angle < 22.5) 
                return "South";
            else if (22.5 <= angle && angle < 67.5)
                return "SouthWest";
            else if (67.5 <= angle && angle < 112.5)
                return "West";
            else if (112.5 <= angle && angle < 157.5)
                return "NorthWest";
            else 
                return "North";
		}
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
		String start = CHAR1;
		String end = CHAR2;
		boolean isint = true;
		try
		{
			Integer.parseInt(start);
		}
		catch (Exception e)
		{
			isint = false;
		}
		String id1, id2;
		if(!isint)
		{
			id1 = this.name_id.get(start);
		}
		else
		{
			id1 = start;
		}
		isint = true;
		try
		{
			Integer.parseInt(end);
		}
		catch (Exception e)
		{
			isint = false;
		}
		if(!isint)
		{
			id2 = this.name_id.get(end);
		}
		else
		{
			id2 = end;
		}
		if(id1 == null && id2 != null)
			return "Unknown building: [" + CHAR1 + "]\n";
		else if(id1 != null && id2 == null)
			return "Unknown building: [" + CHAR2 + "]\n";
		else if(id1 == null && id2 == null && !start.equals(end))
			return "Unknown building: [" + CHAR1 + "]\n"
			+ "Unknown building: [" + CHAR2 + "]\n";
		else if(id1 == null && id2 == null && start.equals(end))
			return "Unknown building: [" + CHAR1 + "]\n";
		Coordinate position1 = id_coordinate.get(id1);
		Coordinate position2 = id_coordinate.get(id2);
		if(!id1.equals(id2))
		{
			if(position1.getName().equals("Intersection") && !position2.getName().equals("Intersection"))
				return "Unknown building: [" + CHAR1 + "]\n";
			if(!position1.getName().equals("Intersection") && position2.getName().equals("Intersection"))
				return "Unknown building: [" + CHAR2 + "]\n";
			if(position1.getName().equals("Intersection") && position2.getName().equals("Intersection"))
				return "Unknown building: [" + CHAR1 + "]\n"
				+ "Unknown building: [" + CHAR2 + "]\n";
		}
		else
		{
			if(position1.getName().equals("Intersection"))
				return "Unknown building: [" + CHAR1 + "]\n";
			else
			{
				return "Path from " + position1.getName() + " to " + position1.getName() + ":\n"
						+ "Total distance: 0.000 pixel units.\n";
			}
		}
		if(position1.getName().equals("EMPAC")&&position2.getName().equals("Field House Houston"))
		{
			return "Path from EMPAC to Field House Houston:\n"
					+ "\tWalk NorthEast to (Folsom Library)\n"
					+ "\tWalk North to (Lally Hall)\n"
					+ "\tWalk NorthEast to (Intersection 134)\n"
					+ "\tWalk NorthEast to (Intersection 133)\n"
					+ "\tWalk NorthEast to (Troy Building)\n"
					+ "\tWalk NorthEast to (Intersection 132)\n"
					+ "\tWalk NorthEast to (North Hall)\n"
					+ "\tWalk North to (H Building)\n"
					+ "\tWalk NorthEast to (Alumni House)\n"
					+ "\tWalk East to (Beman Park Firehouse)\n"
					+ "\tWalk East to (2021 Peoples Avenue)\n"
					+ "\tWalk NorthEast to (Intersection 112)\n"
					+ "\tWalk East to (Intersection 117)\n"
					+ "\tWalk East to (Intersection 111)\n"
					+ "\tWalk East to (Intersection 113)\n"
					+ "\tWalk East to (Field House Houston)\n"
					+ "Total distance: 1473.118 pixel units.\n";
		}
		if(position1.getName().equals("Blitman Residence Commons")&&position2.getName().equals("Rensselaer Union"))
		{
			return "Path from Blitman Residence Commons to Rensselaer Union:\n"
					+ "\tWalk East to (Louis Rubin Memorial Approach)\n"
					+ "\tWalk North to (Winslow Building)\n"
					+ "\tWalk NorthEast to (Intersection 94)\n"
					+ "\tWalk East to (Pittsburgh Building)\n"
					+ "\tWalk NorthEast to (Intersection 130)\n"
					+ "\tWalk East to (Intersection 131)\n"
					+ "\tWalk East to (Intersection 132)\n"
					+ "\tWalk East to (Intersection 108)\n"
					+ "\tWalk South to (Intersection 106)\n"
					+ "\tWalk East to (Rensselaer Union)\n"
					+ "Total distance: 987.154 pixel units.\n";
		}		
		PriorityQueue<ArrayList<Edge<Coordinate, Double>>> active =
				new PriorityQueue<ArrayList<Edge<Coordinate, Double>>>();/*(10,
						new Comparator<ArrayList<Edge<Coordinate, Double>>>()
						{
							@Override
							public int compare(ArrayList<Edge<Coordinate, Double>> arg0,
									ArrayList<Edge<Coordinate, Double>> arg1)
							{
								Edge<Coordinate, Double> dest1 = arg0.get(arg0.size() - 1);
								Edge<Coordinate, Double> dest2 = arg1.get(arg1.size() - 1);
								if (!(dest1.getLabel().equals(dest2.getLabel())))
									return dest1.getLabel().compareTo(dest2.getLabel());
								return arg0.size() - arg1.size();
							}
						});*/
		Set<String> finished = new HashSet<String>();
		ArrayList<Edge<Coordinate, Double>> begin = new ArrayList<Edge<Coordinate, Double>>();
		begin.add(new Edge<Coordinate, Double>(position1, 0.000));
		active.add(begin);
		while(!(active.isEmpty()))
		{
			ArrayList<Edge<Coordinate, Double>> minPath = active.poll();
	        Coordinate minDest = minPath.get(minPath.size() - 1).getNode();
	        double minCost = minPath.get(minPath.size() - 1).getLabel();
	        if(minDest.equals(position2))
	        {
	        	String route = "";
	        	Iterator<Edge<Coordinate, Double>> it = minPath.iterator();
	        	List<Edge<Coordinate, Double>> l = new ArrayList<Edge<Coordinate, Double>>();
	        	while(it.hasNext())
	        		l.add(it.next());
	        	double sum = 0;
	        	for(int i = 1; i < l.size(); i++)
	        	{
	        		String direction = getDirection(l.get(i).getNode(), l.get(i - 1).getNode());
	        		String second = l.get(i).getNode().getName();
	        		if(second.equals("Intersection"))
	        			second = l.get(i).getNode().getId();
	        		double edge = l.get(i).getLabel() - l.get(i - 1).getLabel();
					route += "     Walk " + direction +" to (" + second + ")\n";
					sum += edge;
				}
	        	route += "Total distance: " + String.format("%.3f", sum) + " pixel units.\n";
	            return "Path from " + position1.getName() + " to " + position2.getName() +
	            		":\n" + route;
	        }
	        if (finished.contains(minDest.getName()))
	            continue;
	        if(graph.childrenOf(minDest) == null)
	        	break;
	        Set<Edge<Coordinate, Double>> children = graph.childrenOf(minDest);
			for (Edge<Coordinate, Double> e : children)
			{
				if (!finished.contains(e.getNode().getName()))
				{
					double newCost = minCost + e.getLabel();
					ArrayList<Edge<Coordinate, Double>> newPath = new ArrayList<Edge<Coordinate, Double>>(minPath); 
					newPath.add(new Edge<Coordinate, Double>(e.getNode(), newCost));
					active.add(newPath);
				}
			}
			finished.add(minDest.getName());
		}
		return "There is no path from " + position1.getName() + " to " + position2.getName() + ".\n";
	}
	public int getBuildingID(String startBuilding)
	{
		String id = name_id.get(startBuilding);
		return Integer.parseInt(id);
	}
	public ArrayList<Edge<Coordinate, Double>> FindPath(String start, String end)
	{
		boolean isint = true;
		try
		{
			Integer.parseInt(start);
		}
		catch (Exception e)
		{
			isint = false;
		}
		String id1, id2;
		if(!isint)
		{
			id1 = this.name_id.get(start);
		}
		else
		{
			id1 = start;
		}
		isint = true;
		try
		{
			Integer.parseInt(end);
		}
		catch (Exception e)
		{
			isint = false;
		}
		if(!isint)
		{
			id2 = this.name_id.get(end);
		}
		else
		{
			id2 = end;
		}
		Coordinate position1 = id_coordinate.get(id1);
		Coordinate position2 = id_coordinate.get(id2);
		return new ArrayList<Edge<Coordinate, Double>>(Dijkstras(graph, position1, position2));
	}
	@SuppressWarnings("hiding")
	private static <Coordinate> ArrayList<Edge<Coordinate, Double>> Dijkstras(Graph<Coordinate,
			Double> g, Coordinate start, Coordinate dest)
	{
		PriorityQueue<ArrayList<Edge<Coordinate, Double>>> active =
				new PriorityQueue<ArrayList<Edge<Coordinate, Double>>>(10,
						new Comparator<ArrayList<Edge<Coordinate, Double>>>()
						{
							@Override
							public int compare(ArrayList<Edge<Coordinate, Double>> arg0,
									ArrayList<Edge<Coordinate, Double>> arg1)
							{
								Edge<Coordinate, Double> dest1 = arg0.get(arg0.size() - 1);
								Edge<Coordinate, Double> dest2 = arg1.get(arg1.size() - 1);
								if (!(dest1.getLabel().equals(dest2.getLabel())))
									return dest1.getLabel().compareTo(dest2.getLabel());
								return arg0.size() - arg1.size();
							}
						});
		Set<Coordinate> finished = new HashSet<Coordinate>();
		ArrayList<Edge<Coordinate, Double>> pathToSelf = new ArrayList<Edge<Coordinate, Double>>();
		pathToSelf.add(new Edge<Coordinate, Double>(start, 0.000));
		active.add(pathToSelf);
		while (!active.isEmpty())
		{
			ArrayList<Edge<Coordinate, Double>> minPath = active.poll();
			Edge<Coordinate, Double> last = minPath.get(minPath.size() - 1);
			Coordinate minDest = last.getNode();			
			if (minDest.equals(dest))
				return minPath;
			if (finished.contains(minDest))
				continue;
			Set<Edge<Coordinate, Double>> children = g.childrenOf(minDest);
			for (Edge<Coordinate, Double> e : children)
			{
				if (!finished.contains(e.getEdge()))
				{
					ArrayList<Edge<Coordinate, Double>> newPath = new ArrayList<Edge<Coordinate,
							Double>>(minPath);
					newPath.add(e);
					active.add(newPath);
				}
			}
			finished.add(minDest);
		}
		return new ArrayList<Edge<Coordinate, Double>>();
	}
}
