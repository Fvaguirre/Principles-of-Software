package hw7;

import java.io.*;
import java.util.*;

public class CampusParsing
{
	public CampusParsing()
	{
		
	}
	/** 
	@param: filename The path to the "CSV" file that contains the <name, id, x, y> pairs                                                                                                
    @param: name_id The Map that stores parsed <name, id> pairs
    @param: id_coordinate The Map that stores parsed <id, coordinate> pairs
    @effects: adds parsed <name, id> pairs to Map name_id;
    		  adds parsed <id, coordinate> to Map id_coordinate;
    @throws: IOException if file cannot be read of file not a CSV file                                                                                   
	*/
	@SuppressWarnings("resource")
	public static void readNodeData(String filename, HashMap<String, String> name_id, HashMap<String, Coordinate> id_coordinate) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(filename));
	    String line = null;
	    while ((line = reader.readLine()) != null)
	    {
	         String[] sample = line.split(",");
	         if (sample.length != 4)
	        	 throw new IOException("File "+filename+" not a CSV file.");
	         String name = sample[0];
	         if(name.equals(""))
	        	 name = "Intersection";
	         String id = sample[1];
	         name_id.putIfAbsent(name, id);
	         double x = Double.parseDouble(sample[2]);
	         double y = Double.parseDouble(sample[3]);
	         id_coordinate.putIfAbsent(id, new Coordinate(name, id, x, y));
	    }
	    reader.close();
	}
	/** 
	@param: filename The path to the "CSV" file that contains the <id, id> pairs                                                                                                
    @param: id_id The Map that stores parsed <id, id> pairs
    @effects: adds parsed <id, id> pairs to Map id_id;
    @throws: IOException if file cannot be read of file not a CSV file                                                                                   
	*/
	@SuppressWarnings("resource")
	public static void readEdgeData(String filename, HashMap<String, String> id_id) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(filename));
	    String line = null;
	    while ((line = reader.readLine()) != null)
	    {
	         String[] sample = line.split(",");
	         if (sample.length != 2)
	        	 throw new IOException("File "+filename+" not a CSV file.");
	         String id1 = sample[0];
	         String id2 = sample[1];
	         id_id.putIfAbsent(id1, id2);
	    }
	    reader.close();
	}
}
