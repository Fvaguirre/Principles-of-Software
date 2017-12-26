package hw4.test;

import hw4.GraphWrapper;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class GraphWrapperTest
{
	private GraphWrapper test = new GraphWrapper();
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
		assertTrue(failed);
	}
	@Test //test label after addEdge
	public void testLabel1()
	{
		test.addNode("A");
		test.addNode("B");
		test.addEdge("A", "B", "Label");
		assertNotEquals("B(Label2)", test.listChildren("A").next());
	}
	@Test //test label after addEdge
	public void testLabel2()
	{
		test.addNode("A");
		test.addNode("B");
		test.addEdge("A", "B", "Label");
		assertEquals("B(Label)", test.listChildren("A").next());
	}
	@Test //test reflective edges
	public void testReflective()
	{
		test.addNode("A");
		test.addEdge("A", "A", "Label1");
		assertEquals("A(Label1)", test.listChildren("A").next());
	}
	@Test //test multiple edges
	public void testAddEdge()
	{
		test.addNode("A");
		test.addNode("B");
		test.addNode("C");
		test.addEdge("A", "A", "Label1");
		test.addEdge("B", "A", "Label2");
		test.addEdge("C", "A", "Label3");
		assertEquals("A(Label1)", test.listChildren("A").next());
		assertEquals("A(Label2)", test.listChildren("B").next());
		assertEquals("A(Label3)", test.listChildren("C").next());
	}
	@Test //test listNodes with valid args
	public void testlistNodes()
	{
		test.addNode("A");
		assertEquals("A", test.listNodes().next());
	}
	@Test //test listNodes with invalid args
	public void testlistNodes1()
	{
		test.addNode("A");
		assertNotEquals("B", test.listNodes().next());
	}@Test //test listNodes with invalid args
	public void testlistNodes2()
	{
		test.addNode("A");
		test.addNode("B");
		assertNotEquals("C", test.listNodes().next());
	}
	@Test //test listNodes with invalid args
	public void testlistNodes3()
	{
		test.addNode("A");
		test.addNode("B");
		test.addNode("C");
		assertNotEquals("D", test.listNodes().next());
	}
	@Test //test listChildren with valid args
	public void testlistChildren()
	{
		test.addNode("A");
		test.addNode("B");
		test.addEdge("A", "B", "Label");		
		assertEquals("B(Label)", test.listChildren("A").next());
	}
	@Test //test listChildren with valid args
	public void testlistChildren1()
	{
		test.addNode("A");
		test.addNode("B");
		test.addEdge("A", "B", "Label");
		test.addEdge("A", "B", "Label");
		assertEquals("B(Label)", test.listChildren("A").next());
	}
	@Test //test listChildren with valid args
	public void testlistChildren2()
	{
		test.addNode("A");
		test.addNode("B");
		test.addEdge("A", "B", "Label1");
		test.addEdge("B", "A", "Label2");
		assertEquals("B(Label1)", test.listChildren("A").next());
		assertEquals("A(Label2)", test.listChildren("B").next());
	}
	@Test //test listChildren with valid args
	public void testlistChildren3()
	{
		test.addNode("A");
		assertEquals(false, test.listChildren("A").hasNext());
	}
	@Test //test listChildren with invalid args
	public void testlistChildren0()
	{
		test.addNode("A");
		test.addNode("B");
		test.addEdge("A", "B", "Label");		
		assertNotEquals("C(Label)", test.listChildren("A").next());
	}
}
