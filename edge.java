import java.awt.Color;
public class edge {
	private int start;
	private int end;
	public status stat;
	
	edge()
	{
		this.stat = new status();
		this.start = -1;
		this.end = -1;
	}
	
	public void setstart(int v1)
	{
		this.start = v1;
	}
	
	public void setend(int v2)
	{
		this.end = v2;
	}
	
	public int getstart()
	{
		return this.start;
	}
	
	public int getend()
	{
		return this.end;
	}
	
	public void updateedgestatus()
	{
		if(!stat.clean)
			stat.c = Color.orange;
		else
			stat.c = Color.green;
	}
}
