package hw7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CampusPaths
{
	/**
	 * Overview:
	 * view the list of names of all buildings.
	 * 
	 * @param g a CampusMapGraph object
	 */
	public static void listBuilding(CampusMapGraph g)
	{
		System.out.print(g.listAllBuildings());
	}
	/**
	 * Overview:
	 * main function
	 * the control component and handles the user input
	 */
	public static void main(String[] argv)
	{
		CampusMapGraph g = new CampusMapGraph();
		try
		{
			g.createNewGraph("hw7/data/RPI_map_data_Nodes.csv", "hw7/data/RPI_map_data_Edges.csv");
		}
		catch (IOException i)
		{
			return;
		}
		String menu = "Menu:\n"
				+ "b: lists all buildings\n"
				+ "r: prints directions for the shortest route between two buildings or their IDs\n"
				+ "q: quits the program\n"
				+ "m: prints a menu of all commands\n";
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while(!input.equals("q"))
		{
			try
			{
				input = reader.readLine();
			}
			catch (IOException e)
			{
				break;
			}
			if(input.equals("b"))
				listBuilding(g);
			else if(input.equals("r"))
			{
				String start;
				String end;
				System.out.print("First building id/name, followed by Enter: ");
				try
				{
					start = reader.readLine();
				}
				catch (IOException badinput)
				{
					break;
				}
				System.out.print("Second building id/name, followed by Enter: ");
				try
				{
					end = reader.readLine();
				}
				catch (IOException badinput)
				{
					break;
				}
				System.out.print(g.findPath(start, end));
			}
			else if(input.equals("q"))
				break;
			else if(input.equals("m"))
				System.out.print(menu);
			else
				System.out.print("Unknown option\n");
		}
		return;
	}
}
