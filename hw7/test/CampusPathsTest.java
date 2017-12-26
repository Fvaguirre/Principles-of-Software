package hw7.test;

import java.io.*;
import java.util.HashMap;

import hw7.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class CampusPathsTest { // Rename to the name of your "main" class

	/**
	 * @param file1 
	 * @param file2
	 * @return true if file1 and file2 have the same content, false otherwise
	 * @throws IOException
	 */	
	/* compares two text files, line by line */
	private static boolean compare(String file1, String file2) throws IOException {
		BufferedReader is1 = new BufferedReader(new FileReader(file1)); // Decorator design pattern!
		BufferedReader is2 = new BufferedReader(new FileReader(file2));
		String line1, line2;
		boolean result = true;
		while ((line1=is1.readLine()) != null) {
			// System.out.println(line1);
			line2 = is2.readLine();
			if (line2 == null) {
				System.out.println(file1+" longer than "+file2);
				result = false;
				break;
			}
			if (!line1.equals(line2)) {
				System.out.println("Lines: "+line1+" and "+line2+" differ.");
				result = false;
				break;
			}
		}
		if (result == true && is2.readLine() != null) {
			System.out.println(file1+" shorter than "+file2);
			result = false;
		}
		is1.close();
		is2.close();
		return result;		
	}
	
	private void runTest(String filename) throws IOException {
		InputStream in = System.in; 
		PrintStream out = System.out;				
		String inFilename = "hw7/data/"+filename+".test"; // Input filename: [filename].test here  
		String expectedFilename = "hw7/data/"+filename+".expected"; // Expected result filename: [filename].expected
		String outFilename = "hw7/data/"+filename+".out"; // Output filename: [filename].out
		BufferedInputStream is = new BufferedInputStream(new FileInputStream(inFilename));
		System.setIn(is); // redirects standard input to a file, [filename].test 
		PrintStream os = new PrintStream(new FileOutputStream(outFilename));
		System.setOut(os); // redirects standard output to a file, [filename].out 
		CampusPaths.main(null); // Call to YOUR main. May have to rename.
		System.setIn(in); // restores standard input
		System.setOut(out); // restores standard output
		assertTrue(compare(expectedFilename,outFilename)); 
		// TODO: More informative file comparison will be nice.
		
	}
	
	@Test
	public void testListBuildings() throws IOException {
		runTest("test1");
	}
	@Test//test campus node parsing
	public void testNodeWrongFormat()
	{
		new CampusParsing();
		boolean flag = true;
		HashMap<String, String> name_id = new HashMap<String, String>();
		HashMap<String, Coordinate> id_coordinate = new HashMap<String, Coordinate>();
		try
		{
			CampusParsing.readNodeData("hw7/data/WrongFormat.csv", name_id, id_coordinate);
		}
		catch (IOException e)
		{
			flag = false;
		}
		assertFalse(flag);
	}
	@Test//test campus edge parsing
	public void testEdgeWrongFormat()
	{
		boolean flag = true;
		HashMap<String, String> id_id = new HashMap<String, String>();
		try
		{
			CampusParsing.readEdgeData("hw7/data/WrongFormat.csv", id_id);
		}
		catch (IOException e)
		{
			flag = false;
		}
		assertFalse(flag);
	}
	@Test//test null node
	public void testUnknownNode1() throws IOException
	{
		CampusMapGraph a = new CampusMapGraph();
		a.createNewGraph("hw7/data/RPI_map_data_Nodes.csv", "hw7/data/RPI_map_data_Edges.csv");
		assertEquals(a.findPath("EMPAC", "Lally"), "Unknown building: [Lally]\n");
	}
	@Test//test null node
	public void testUnknownNode2() throws IOException
	{
		CampusMapGraph a = new CampusMapGraph();
		a.createNewGraph("hw7/data/RPI_map_data_Nodes.csv", "hw7/data/RPI_map_data_Edges.csv");
		assertEquals(a.findPath("Lally", "EMPAC"), "Unknown building: [Lally]\n");
	}
	@Test//test null node
	public void testUnknownNode3() throws IOException
	{
		CampusMapGraph a = new CampusMapGraph();
		a.createNewGraph("hw7/data/RPI_map_data_Nodes.csv", "hw7/data/RPI_map_data_Edges.csv");
		assertEquals(a.findPath("Lally", "Poly"), "Unknown building: [Lally]\n"
				+ "Unknown building: [Poly]\n");
	}
	@Test//test null node
	public void testUnknownNode4() throws IOException
	{
		CampusMapGraph a = new CampusMapGraph();
		a.createNewGraph("hw7/data/RPI_map_data_Nodes.csv", "hw7/data/RPI_map_data_Edges.csv");
		assertEquals(a.findPath("Lally", "Lally"), "Unknown building: [Lally]\n");
	}
	@Test//test intersection node
	public void testIntersection1() throws IOException
	{
		CampusMapGraph a = new CampusMapGraph();
		a.createNewGraph("hw7/data/RPI_map_data_Nodes.csv", "hw7/data/RPI_map_data_Edges.csv");
		assertEquals(a.findPath("EMPAC", "108"), "Unknown building: [108]\n");
	}
	@Test//test intersection node
	public void testIntersection2() throws IOException
	{
		CampusMapGraph a = new CampusMapGraph();
		a.createNewGraph("hw7/data/RPI_map_data_Nodes.csv", "hw7/data/RPI_map_data_Edges.csv");
		assertEquals(a.findPath("108", "EMPAC"), "Unknown building: [108]\n");
	}
	@Test//test intersection node
	public void testIntersection3() throws IOException
	{
		CampusMapGraph a = new CampusMapGraph();
		a.createNewGraph("hw7/data/RPI_map_data_Nodes.csv", "hw7/data/RPI_map_data_Edges.csv");
		assertEquals(a.findPath("108", "108"), "Unknown building: [108]\n");
	}
	@Test//test intersection node
	public void testIntersection4() throws IOException
	{
		CampusMapGraph a = new CampusMapGraph();
		a.createNewGraph("hw7/data/RPI_map_data_Nodes.csv", "hw7/data/RPI_map_data_Edges.csv");
		assertEquals(a.findPath("108", "109"), "Unknown building: [108]\n"
				+ "Unknown building: [109]\n");
	}
	@Test//test same node
	public void testSameNode() throws IOException
	{
		CampusMapGraph a = new CampusMapGraph();
		a.createNewGraph("hw7/data/RPI_map_data_Nodes.csv", "hw7/data/RPI_map_data_Edges.csv");
		assertEquals(a.findPath("EMPAC", "EMPAC"), "Path from EMPAC to EMPAC:\n" + 
				"Total distance: 0.000 pixel units.\n");
	}
	@Test//test the name and id conversion
	public void testNameId1() throws IOException
	{
		CampusMapGraph a = new CampusMapGraph();
		a.createNewGraph("hw7/data/RPI_map_data_Nodes.csv", "hw7/data/RPI_map_data_Edges.csv");
		assertEquals(a.findPath("EMPAC", "76"), "Path from EMPAC to EMPAC:\n" + 
				"Total distance: 0.000 pixel units.\n");
	}
	@Test//test the name and id conversion
	public void testNameId2() throws IOException
	{
		CampusMapGraph a = new CampusMapGraph();
		a.createNewGraph("hw7/data/RPI_map_data_Nodes.csv", "hw7/data/RPI_map_data_Edges.csv");
		assertEquals(a.findPath("76", "EMPAC"), "Path from EMPAC to EMPAC:\n" + 
				"Total distance: 0.000 pixel units.\n");
	}
	@Test//test nodes with no path
	public void testNoPath() throws IOException
	{
		CampusMapGraph a = new CampusMapGraph();
		a.createNewGraph("hw7/data/RPI_map_data_Nodes.csv", "hw7/data/RPI_map_data_Edges.csv");
		assertEquals(a.findPath("1", "EMPAC"), "There is no path from Pittsburgh Building to EMPAC.\n");
	}
	@Test//Test two nodes with one path
	public void testSimpleNode1() throws IOException
	{
		CampusMapGraph a = new CampusMapGraph();
		a.createNewGraph("hw7/data/RPI_map_data_Nodes.csv", "hw7/data/RPI_map_data_Edges.csv");
		assertEquals(a.findPath("1", "3"), "Path from Pittsburgh Building to Carnegie Building:\n" +
				"     Walk North to (Carnegie Building)\n" +
				"Total distance: 78.588 pixel units.\n");
	}
	@Test//Test two nodes with one path
	public void testSimpleNode2() throws IOException
	{
		CampusMapGraph a = new CampusMapGraph();
		a.createNewGraph("hw7/data/RPI_map_data_Nodes.csv", "hw7/data/RPI_map_data_Edges.csv");
		assertEquals(a.findPath("Polytechnic Residence Commons", "Blitman Residence Commons"), "There is no path from Polytechnic Residence Commons to Blitman Residence Commons.\n");
	}
}
