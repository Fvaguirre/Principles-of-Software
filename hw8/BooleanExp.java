package hw8;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * BooleanExp is an immutable class. It represents boolean constants, boolean variables,
 * Or boolean expressions and And boolean expressions.
 *
 */
interface Visitor
{
	public boolean visit(BooleanExp1 b);
	public boolean visit(Constant e);
	public boolean visit(VarExp e);
	public boolean visit(NotExp e);
	public boolean visit(AndExp e);
	public boolean visit(OrExp e); 
}
class EvaluateVisitor implements Visitor
{
	Context c;	
	EvaluateVisitor(Context c)
	{
		this.c = c;
	}	
	public boolean visit(BooleanExp1 b)
	{
		return b.getValue();
	}	
	public boolean visit(Constant e)
	{ 
		return e.getValue();
	}	
	public boolean visit(VarExp e)
	{
		return c.lookup(e.getName());
	}	
	public boolean visit(NotExp e)
	{
		return ! e.getValue();
	}
	public boolean visit(AndExp e)
	{
		return e.getLeft() && e.getRight();
	}
	public boolean visit(OrExp e)
	{
		return e.getLeft() || e.getRight();
	}
}
class Context1
{
	private static Map<String, Boolean> context;
	Context1()
	{
		if (context == null)
		{
			context = new HashMap<String, Boolean>();
		}
	}
	void setValue(String var, boolean val)
	{
		context.put(var, val);
	}
	boolean lookup(String var)
	{
		return context.get(var);
	}
}
abstract class BooleanExp1
{
	boolean val;	
	abstract boolean accept(Visitor v);	
	boolean getValue()
	{
		return val;
	}
}
class Constant extends BooleanExp1
{
	Constant(boolean c)
	{ 
		val = c; 
	}
	boolean accept(Visitor v)
	{ 
		return v.visit(this);
	}
}
class VarExp extends BooleanExp1
{
	String varname;
	VarExp(String var)
	{ 
		varname = var; 
	}	
	String getName()
	{
		return varname;
	}	
	boolean accept(Visitor v)
	{ 
		return v.visit(this);
	}
}
class AndExp extends BooleanExp1
{
	private BooleanExp1 leftExp;
	private BooleanExp1 rightExp;
	AndExp(BooleanExp1 left, BooleanExp1 right)
	{
		leftExp = left;
		rightExp = right;
	}	
	boolean getLeft()
	{
		return leftExp.getValue();
	}	
	boolean getRight()
	{
		return rightExp.getValue();
	}
	boolean accept(Visitor v)
	{ 
		leftExp.val = leftExp.accept(v);
		rightExp.val = rightExp.accept(v);
		return v.visit(this);
	}
} 
class OrExp extends BooleanExp1
{
	private BooleanExp1 leftExp;
	private BooleanExp1 rightExp;
	OrExp(BooleanExp1 left, BooleanExp1 right)
	{
		leftExp = left;
		rightExp = right;
	}
	boolean getLeft()
	{
		return leftExp.getValue();
	}	
	boolean getRight()
	{
		return rightExp.getValue();
	}
	boolean accept(Visitor v)
	{ 
		leftExp.val = leftExp.accept(v);
		rightExp.val = rightExp.accept(v);
		return v.visit(this);
	}
} 
class NotExp extends BooleanExp1
{
	private BooleanExp1 exp;
	NotExp(BooleanExp1 exp)
	{
		this.exp = exp;
	}
	boolean accept(Visitor v)
	{ 
		val = exp.accept(v);
		return v.visit(this);
	}
}
public class BooleanExp
{
	final static int CONST = 0;
	final static int VAR = 1;
	final static int AND = 2;
	final static int OR = 3;
	final static int NOT = 4;
	// Rep invariant:
	// exprCode is AND || OR || CONST || VAR
	// if exprCode == AND || exprCode == OR then left != null and right != null and value = null and varString = null
	// else if exprCode == CONST then left == null and right == null and value != null and varString == null
	// else if exprCode == VAR then left == null and right == null and value == null and varString != null
	private BooleanExp left;
	private BooleanExp right;
	private int exprCode;
	private Boolean value;
	private String varString;
	public BooleanExp getLeft()
	{
		return left;
	}
	public BooleanExp getRight()
	{
		return right;
	}
	public int getExpressionCode()
	{
		return exprCode;
	}
	public boolean getValue()
	{
		return value.booleanValue();
	}
	public String getVarString()
	{
		return varString;
	}	
	public BooleanExp(int exprCode, BooleanExp left, BooleanExp right, Boolean value, String varString)
	{
		this.left = left;
		this.right = right;
		this.value = value;
		this.varString = varString;
		this.exprCode = exprCode; 
	}
	/**
	 * @return: String corresponding to Preorder of this BooleanExp
	 * E.g., if BooleanExp is CONST with value true, result is "true"
	 * If BooleanExp is AND with left VAR "x" and right VAR "x", 
	 * result is "AND x y"  
	 */
	public String toString()
	{
		StringBuffer result = new StringBuffer();	
		switch (getExpressionCode())
		{
			case BooleanExp.AND: 
				result.append("AND ");
				result.append(getLeft().toString());
				result.append(" ");
				result.append(getRight().toString());
				break;
			case BooleanExp.OR:
				result.append("OR ");
				result.append(getLeft().toString());
				result.append(" ");
				result.append(getRight().toString());
				break;
			case BooleanExp.NOT:
				result.append("NOT ");
				result.append(getLeft().toString());
				result.append(" ");
				result.append(getRight().toString());
				break;
			case BooleanExp.CONST:
				result.append(getValue());
				break;
			case BooleanExp.VAR:
				result.append(getVarString());
				break;
		}
		return result.toString();
	}			
}
