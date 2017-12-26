package hw7;
/**
 * Coordinate represents a coordinate on campus
 * has a name, id, x and y coordinates
 * equals and hashcode is determined by id only
 * 
 * Rep Invariant: name and id are strings or null, x and y are doubles of any value
 * Abstraction Function: name is the name of the coordinate, map coordinates are (x, y)
 */
public class Coordinate
{
	private String name;
	private String id;
	private double x;
	private double y;
	public Coordinate(String name0, String id0, double x0, double y0)
	{
		this.name = name0;
		this.id = id0;
		this.x = x0;
		this.y = y0;
	}
	public String getName()
	{
		return this.name;
	}
	public String getId()
	{
		return this.id;
	}
	public double getX()
	{
		return this.x;
	}
	public double getY()
	{
		return this.y;
	}
	@Override
	public String toString()
	{
		return "<" + name + ", " + id + ", " + Math.round(x) + ", "
				+ Math.round(y) + ">";
	}
	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof Coordinate))
			return false;
		Coordinate b = (Coordinate) obj;
		return b.getName().equals(this.getName()) && b.getId() == this.getId() && b.getX() ==
				this.getX() && b.getY() == this.getY();
	}
	@Override
	public int hashCode()
	{
		return getName().hashCode();
	}
	public int compareTo(Coordinate l)
	{
		double d1 = Math.sqrt(this.x * this.x + this.y * this.y);
		double d2 = Math.sqrt(l.x * l.x + l.y * l.y);
		double compare = d1 - d2;
		if (compare < 0)
			return -1;
		if (compare > 0)
			return 1;
		return 0;
	}
}
