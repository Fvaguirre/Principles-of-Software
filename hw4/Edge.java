package hw4;

public class Edge<T1, T2>
{
	private T1 node;
	private T2 label;
	public Edge(T1 s, T2 l)
	{
		if(s.equals(null) || l.equals(null))
			throw new IllegalArgumentException("Null input is not acceptable");
		this.node = s;
		this.label = l;
	}
	public Edge<T1, Double> getEdge()
	{
		T1 tempNode = this.node;
		Double tempLabel = (Double) this.label;
		return new Edge<T1, Double>(tempNode, tempLabel);
	}
	public T1 getNode()
	{
		return this.node;
	}
	public T2 getLabel()
	{
		return this.label;
	}
	public String getElement()
	{
		return this.node.toString() + "<" + this.label.toString() + ">";
	}
}
