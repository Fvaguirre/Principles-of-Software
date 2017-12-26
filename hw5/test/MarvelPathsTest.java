package hw5.test;

import hw5.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import java.util.*;

import org.junit.Test;

@SuppressWarnings("unused")

public class MarvelPathsTest
{
	@Test//null filename
	public void testNullBuilder()
	{
		boolean flag = false;
		MarvelPaths mp = new MarvelPaths();
		try
		{
			mp.createNewGraph(null);
		}
		catch(Exception e)
		{
			flag = true;
		}
		assertTrue(flag);
	}
	@Test//illegal filename
	public void testIllegalBuilder()
	{
		boolean flag = false;
		MarvelPaths mp = new MarvelPaths();
		try
		{
			mp.createNewGraph("hw5/data/notexist.csv");
		}
		catch(Exception e)
		{
			flag = true;
		}
		assertTrue(flag);
	}
	@Test//wrong format file
	public void testWrongFormatFile()
	{
		boolean flag = false;
		MarvelPaths mp = new MarvelPaths();
		try
		{
			mp.createNewGraph("hw5/data/WrongFormat.csv");
		}
		catch(Exception e)
		{
			flag = true;
		}
		assertTrue(flag);
	}
	@Test//unknown character 1
	public void testUnknownCharacter1()
	{
		MarvelPaths mp = new MarvelPaths();
		mp.createNewGraph("hw5/data/test1.csv");
		String path = mp.findPath("Jaime", "Arya");
		assertEquals(path, "unknown character Jaime\n");
	}
	@Test//unknown character 2
	public void testUnknownCharacter2()
	{
		MarvelPaths mp = new MarvelPaths();
		mp.createNewGraph("hw5/data/test1.csv");
		String path = mp.findPath("Ramsy", "Cersei");
		assertEquals(path, "unknown character Cersei\n");
	}
	@Test//unknown character 1 & 2
	public void testUnknownCharacter1And2()
	{
		MarvelPaths mp = new MarvelPaths();
		mp.createNewGraph("hw5/data/test1.csv");
		String path = mp.findPath("Jaime", "Cersei");
		assertEquals(path, "unknown character Jaime\nunknown character Cersei\n");
	}
	@Test//unknown character 1 & 1 equals 2
	public void testUnknownSameCharacter()
	{
		MarvelPaths mp = new MarvelPaths();
		mp.createNewGraph("hw5/data/test1.csv");
		String path = mp.findPath("Jaime", "Jaime");
		assertEquals(path, "unknown character Jaime\n");
	}
	@Test//same character
	public void testSameCharacter()
	{
		MarvelPaths mp = new MarvelPaths();
		mp.createNewGraph("hw5/data/test1.csv");
		String path = mp.findPath("Ramsy", "Ramsy");
		assertEquals(path, "path from Ramsy to Ramsy:\n");
	}
	@Test//null character 1
	public void testNullCharacter1()
	{
		boolean flag = false;
		MarvelPaths mp = new MarvelPaths();
		mp.createNewGraph("hw5/data/test1.csv");
		try
		{
			String path = mp.findPath(null, "Ramsy");
		}
		catch(Exception e)
		{
			flag = true;
		}
		assertFalse(flag);
	}
	@Test//null character 2
	public void testNullCharacter2()
	{
		boolean flag = false;
		MarvelPaths mp = new MarvelPaths();
		mp.createNewGraph("hw5/data/test1.csv");
		try
		{
			String path = mp.findPath("Ramsy", null);
		}
		catch(Exception e)
		{
			flag = true;
		}
		assertFalse(flag);
	}
	@Test//2 distant characters without connection
	public void testFindMarvelPath()
	{
		MarvelPaths mp = new MarvelPaths();
		mp.createNewGraph("hw5/data/test1.csv");
		String path = mp.findPath("Ramsy", "Arya");
		assertEquals(path, "path from Ramsy to Arya:\nno path found\n");
	}
	@Test//2 distant characters without connection
	public void testFindNonePath()
	{
		MarvelPaths mp = new MarvelPaths();
		mp.createNewGraph("hw5/data/test1.csv");
		String path = mp.findPath("Ramsy", "Arya");
		assertEquals(path, "path from Ramsy to Arya:\nno path found\n");
	}
	@Test//2 distant characters via single book
	public void testFindSinglePath()
	{
		MarvelPaths mp = new MarvelPaths();
		mp.createNewGraph("hw5/data/test1.csv");
		String path = mp.findPath("John Snow", "Arya");
		assertEquals(path, "path from John Snow to Arya:\nJohn Snow to Arya via Stark\n");
	}
	@Test//2 distant characters via multiple books
	public void testFindMultiplePath()
	{
		MarvelPaths mp = new MarvelPaths();
		mp.createNewGraph("hw5/data/test1.csv");
		String path = mp.findPath("John Snow", "Jaqen");
		assertEquals(path, "path from John Snow to Jaqen:\nJohn Snow to Arya via Stark\nArya to Jaqen via FacelessMen\n");
	}
}
