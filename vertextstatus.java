import java.awt.Color;
import java.util.ArrayList;

public class vertextstatus extends status
{
	private Boolean required;
	private String operation;
	
	public ArrayList<Integer> relatev;
	public ArrayList<Integer> head;
	public ArrayList<Integer> tail;
	
	vertextstatus()
	{
		relatev = new ArrayList<Integer>();
		head = new ArrayList<Integer>();
		tail = new ArrayList<Integer>();
		this.required = false;
		this.operation = "thinking";
	}
	
	public Boolean isrequired()
	{
		return required;
	}
	
	public void setrequire(Boolean b)
	{
		this.required = b;
	}
	
	public void setoper(String oper)
	{
		this.operation = oper;
	}
	
	public String getoper()
	{
		return this.operation;
	}
	
	public void updatestatus()
	{
		if(this.operation == "thinking")
		{
			this.c = Color.BLUE;
		}
		else if(this.operation == "hungry")
		{
			this.c = Color.ORANGE;
		}
		else if(this.operation == "eating")
		{
			this.c = Color.GREEN;
		}

	}
	
	public Color getColor()
	{
		if(this.operation == "thinking")
		{
			return Color.BLUE;
		}
		else if(this.operation == "hungry")
		{
			return Color.ORANGE;
		}
		else
		{
			return Color.GREEN;
		}
	}
	
}
