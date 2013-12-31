import java.awt.Color;
public class status {
	protected Color c;
	protected Boolean clean;
	
	public status(){
		this.c = Color.orange;
		this.clean = false;
	}
	
//	public status(Color cc){
		//c =cc;
//	}

	public Color getColor()
	{
		return this.c;
	}

	public void setColor(Color color)
	{
		this.c = color;
	}
	
}
