import edu.uci.ics.jung.visualization.picking.PickedInfo;
import edu.uci.ics.jung.visualization.renderers.Renderer; 
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;
import java.awt.Color;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import org.apache.commons.collections15.Transformer;
public class MyRenderer implements Renderer.Vertex<Integer, Integer> 
{
	private phis g;
	
	public MyRenderer(phis s)
	{
		g = s;
	}
    public void paintVertex(RenderContext<Integer, Integer> rc, Layout<Integer, Integer> layout, Integer vertex) {
      GraphicsDecorator graphicsContext = rc.getGraphicsContext();
      Point2D center = layout.transform(vertex);
      Shape shape = new Ellipse2D.Double(center.getX() - 10, center.getY() - 10, 20, 20);
       
      int index = vertex;
      //System.out.println(index);
      if(g.vertex[index].isrequired())
      {
        shape = new Rectangle((int) center.getX() - 10, (int) center.getY() - 10, 20, 20);
      } 
      
        //shape = new Rectangle((int) center.getX() - 10, (int) center.getY() - 20, 20, 40);
      Color color = g.vertex[index].getColor();
      
      graphicsContext.setPaint(color);
      graphicsContext.fill(shape);
      
		Transformer<Integer,Paint> edgeColor = new Transformer<Integer,Paint>() {
	        public Paint transform(Integer s) 
	        {
	        	//System.out.println(s);
	        	return g.edges[s].stat.c;
	        }
	    };
	    rc.setEdgeFillPaintTransformer(edgeColor);
	    rc.setEdgeLabelTransformer(new EdgeLabelTransformer(rc.getPickedVertexState()));
	    rc.setVertexLabelTransformer(new VertexLabelTransformer(rc.getPickedVertexState()));
    }

  }

class VertexLabelTransformer implements Transformer<Integer,String>{
    private final PickedInfo<Integer> pi;

    public VertexLabelTransformer( PickedInfo<Integer> pi ){
        this.pi = pi;
    }

    @Override
    public String transform(Integer t) {
        if (pi.isPicked(t))
            return t.toString();
        else
          return t.toString();
    }
}

class EdgeLabelTransformer implements Transformer<Integer,String>{
    private final PickedInfo<Integer> pi;

    public EdgeLabelTransformer( PickedInfo<Integer> pi ){
        this.pi = pi;
    }

    @Override
    public String transform(Integer t) {
        if (pi.isPicked(t))
            return t.toString();
        else
          return t.toString();
    }
}