package hw4.test;

import hw4.Graph;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

@SuppressWarnings("unused")
public class GraphTest<T1, T2>
{
	private Graph<String, String> test = new Graph<String, String>();
	@Test
	public void testChild()
	{
		test.addNode("Arya");
		test.addNode("Jaqen");
		test.addNode("Snow");
		test.addEdge("Arya", "Snow", "Stark");
		test.addEdge("Arya", "Jaqen", "Faceless Men");
		assertEquals("Jaqen<Faceless Men>,Snow<Stark>", test.listChildren("Arya"));
		//assertEquals("A, B, C", test.listNodes().toString().substring(test.listNodes().toString().indexOf("[", 0) + 1, test.listNodes().toString().indexOf("]", 0)));
	}
	/*
	@Test //test addNode with null string
	public void testNullNode()
	{
		boolean failed = false;
		try
		{
			test.addNode(null);
		}
		catch (Exception e)
		{
			failed = true;
		}
		assertTrue(failed);
	}
	@Test //test node constructor with non-null string
	public void testNodeConstructor1()
	{
		boolean failed = false;
		try
		{
			test.addNode("A");
		}
		catch (Exception e)
		{
			failed = true;
		}
		assertFalse(failed);
	}
	@Test //test node constructor with non-null string
	public void testNodeConstructor2()
	{
		boolean failed = false;
		try
		{
			test.addNode("A");
			test.addNode("B");
		}
		catch (Exception e)
		{
			failed = true;
		}
		assertFalse(failed);
	}
	@Test //test node constructor with non-null string
	public void testNodeConstructor3()
	{
		boolean failed = false;
		try
		{
			test.addNode("A");
			test.addNode("B");
			test.addNode("C");
		}
		catch (Exception e)
		{
			failed = true;
		}
		assertFalse(failed);
	}
	@Test //test node constructor with non-null string
	public void testNodeConstructor4()
	{
		boolean failed = false;
		try
		{
			test.addNode("A");
			test.addNode("B");
			test.addNode("C");
			test.addNode("D");
		}
		catch (Exception e)
		{
			failed = true;
		}
		assertFalse(failed);
	}
	@Test //test node constructor with non-null string
	public void testNodeConstructor5()
	{
		boolean failed = false;
		try
		{
			test.addNode("A");
			test.addNode("B");
			test.addNode("C");
			test.addNode("D");
			test.addNode("E");
		}
		catch (Exception e)
		{
			failed = true;
		}
		assertFalse(failed);
	}
	@Test //test node constructor with same string
	public void testSameNodeConstructor()
	{
		boolean failed = false;
		try
		{
			test.addNode("A");
			test.addNode("A");
		}
		catch (Exception e)
		{
			failed = true;
		}
		assertTrue(failed);
	}
	@Test //test addEdge with null args
	public void testEdgeConstructorNull1()
	{
		test.addNode("A");
		test.addNode("B");
		boolean failed = false;
		try
		{
			test.addEdge(null, null, null);
		}
		catch (Exception e)
		{
			failed = true;
		}
		assertTrue(failed);
	}
	@Test //test addEdge with null args
	public void testEdgeConstructorNull2()
	{
		test.addNode("A");
		test.addNode("B");
		boolean failed = false;
		try
		{
			test.addEdge("A", null, null);
		}
		catch (Exception e)
		{
			failed = true;
		}
		assertTrue(failed);
	}
	@Test //test addEdge with null args
	public void testEdgeConstructorNull3()
	{
		test.addNode("A");
		test.addNode("B");
		boolean failed = false;
		try
		{
			test.addEdge(null, "B", null);
		}
		catch (Exception e)
		{
			failed = true;
		}
		assertTrue(failed);
	}
	@Test //test addEdge with null args
	public void testEdgeConstructorNull4()
	{
		test.addNode("A");
		test.addNode("B");
		boolean failed = false;
		try
		{
			test.addEdge(null, null, "Label");
		}
		catch (Exception e)
		{
			failed = true;
		}
		assertTrue(failed);
	}
	@Test //test addEdge with null args
	public void testEdgeConstructorNull5()
	{
		test.addNode("A");
		test.addNode("B");
		boolean failed = false;
		try
		{
			test.addEdge("A", "B", null);
		}
		catch (Exception e)
		{
			failed = true;
		}
		assertTrue(failed);
	}
	@Test //test addEdge with null args
	public void testEdgeConstructorNull6()
	{
		test.addNode("A");
		test.addNode("B");
		boolean failed = false;
		try
		{
			test.addEdge("A", null, "Label");
		}
		catch (Exception e)
		{
			failed = true;
		}
		assertTrue(failed);
	}
	@Test //test addEdge with null args
	public void testEdgeConstructorNull7()
	{
		test.addNode("A");
		test.addNode("B");
		boolean failed = false;
		try
		{
			test.addEdge(null, "B", "Label");
		}
		catch (Exception e)
		{
			failed = true;
		}
		assertTrue(failed);
	}
	@Test //test addEdge with valid args
	public void testEdgeConstructor1()
	{
		test.addNode("A");
		test.addNode("B");
		boolean failed = false;
		try
		{
			test.addEdge("A", "B", "Label1");
		}
		catch (Exception e)
		{
			failed = true;
		}
		assertFalse(failed);
	}
	@Test //test addEdge with valid args
	public void testEdgeConstructor2()
	{
		test.addNode("A");
		test.addNode("B");
		boolean failed = false;
		try
		{
			test.addEdge("A", "B", "Label1");
			test.addEdge("A", "B", "Label2");
		}
		catch (Exception e)
		{
			failed = true;
		}
		assertFalse(failed);
	}
	@Test //test addEdge with valid args
	public void testEdgeConstructor3()
	{
		test.addNode("A");
		test.addNode("B");
		boolean failed = false;
		try
		{
			test.addEdge("A", "B", "Label1");
			test.addEdge("A", "B", "Label2");
			test.addEdge("A", "B", "Label3");
		}
		catch (Exception e)
		{
			failed = true;
		}
		assertFalse(failed);
	}
	@Test //test addEdge with valid args
	public void testEdgeConstructor4()
	{
		test.addNode("A");
		test.addNode("B");
		boolean failed = false;
		try
		{
			test.addEdge("A", "B", "Label1");
			test.addEdge("A", "B", "Label2");
			test.addEdge("A", "B", "Label3");
			test.addEdge("A", "B", "Label4");
		}
		catch (Exception e)
		{
			failed = true;
		}
		assertFalse(failed);
	}
	@Test //test addEdge with valid args
	public void testEdgeConstructor5()
	{
		test.addNode("A");
		test.addNode("B");
		boolean failed = false;
		try
		{
			test.addEdge("A", "B", "Label1");
			test.addEdge("A", "B", "Label2");
			test.addEdge("A", "B", "Label3");
			test.addEdge("A", "B", "Label4");
			test.addEdge("A", "B", "Label5");
		}
		catch (Exception e)
		{
			failed = true;
		}
		assertFalse(failed);
	}
	@Test //test addEdge with exceeding args
	public void testEdgeConstructor6()
	{
		test.addNode("A");
		test.addNode("B");
		boolean failed = false;
		try
		{
			test.addEdge("A", "B", "Label1");
			test.addEdge("A", "B", "Label2");
			test.addEdge("A", "B", "Label3");
			test.addEdge("A", "B", "Label4");
			test.addEdge("A", "B", "Label5");
			test.addEdge("A", "B", "Label6");
		}
		catch (Exception e)
		{
			failed = true;
		}
		assertFalse(failed);
	}
	
	@Test //test reflective edges
	public void testReflective()
	{
		test.addNode("A");
		test.addEdge("A", "A", "Label1");
		//assertTrue(test.checkRep());
	}
	/*
	@Test //test Rep Invariant holds
	public void testCheckRep()
	{
		assertTrue(test.checkRep());
		test.addNode("B");
		assertTrue(test.checkRep());
		test.addEdge("B", "B", "Label1");
		assertTrue(test.checkRep());
		test.addNode("A");
		test.addEdge("A", "B", "Label2");
		assertTrue(test.checkRep());
	}
	*/
}
