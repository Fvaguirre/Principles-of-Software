package hw4;

import hw4.Graph;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class GraphWrapper
{
	private Graph<String, String> test = new Graph<String, String>();
	private String[] name = new String[5];
	private String[][][] Matrix = new String[5][5][5];
	@SuppressWarnings("rawtypes")
	public GraphWrapper()
	{
		@SuppressWarnings("unused")
		Graph test1 = new Graph();
		for(int h = 0; h < 5; h++)
			name[h] = "";
		for(int i = 0; i < 5; i++)
			for(int j = 0; j < 5; j++)
				for(int k = 0; k < 5; k++)
					Matrix[i][j][k] = "infinity";
	}
	public void addNode(String nodeData)
	{
		test.addNode(nodeData);
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
	public void addEdge(String parentNode, String childNode, String edgeLabel)
	{
		test.addEdge(parentNode, childNode, edgeLabel);
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
	@SuppressWarnings({ })
	public Iterator<String> listNodes()
	{
		ArrayList<String> temp = new ArrayList<String>();
		for(int i = 0; i < 5; i++)
		{
			if(!name[i].equals(""))
				temp.add(name[i]);
		}
		Collections.sort(temp);
		Iterator<String> list = temp.iterator();
		return list;
	}
	@SuppressWarnings({ })
	public Iterator<String> listChildren(String parentNode)
	{
		ArrayList<String> temp = new ArrayList<String>();
		for(int i = 0; i < 5; i++)
		{
			if(name[i].equals(parentNode))
			{
				for(int j = 0; j < 5; j++)
				{
					for(int k = 0; k < 5; k++)
					{
						if(!Matrix[i][j][k].equals("infinity"))
							temp.add(name[j] + "(" + Matrix[i][j][k] + ")");
					}
				}
			}
		}
		Collections.sort(temp);
		Iterator<String> children = temp.iterator();
		return children;
	}
}
