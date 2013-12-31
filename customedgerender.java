import edu.uci.ics.jung.visualization.renderers.EdgeArrowRenderingSupport;
//import edu.uci.ics.jung.visualization.renderers.Renderer.Edge;
import edu.uci.ics.jung.visualization.renderers.Renderer; 
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Line2D;

public class customedgerender implements Renderer.Edge<String,String>
{
	public void paintEdge(RenderContext<String,String> rc, Layout<String,String> layout, String edge)
	{   
		System.out.println(edge);
		Shape shape = new Line2D.Float(10,52,130,540);
		Color color = Color.black;
		GraphicsDecorator graphicsContext = rc.getGraphicsContext();
	    graphicsContext.setPaint(color);
	    graphicsContext.fill(shape);
	}
	
	public void setEdgeArrowRenderingSupport(EdgeArrowRenderingSupport edgeArrowRenderingSupport)
	{
		;
	}
	public EdgeArrowRenderingSupport getEdgeArrowRenderingSupport()
	{
		return null;
	}
}
