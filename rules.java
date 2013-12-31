//import java.awt.Color;
import java.util.Iterator;

public class rules {
	public static void applyonerule(phis g)
	{
		
		//for(int i=0;i<g.vertex.length;i++)
		//{
		//System.out.println("test");
		int i = g.step % g.vertex.length;
		vertextstatus v = g.vertex[i];
		if(v.getoper() == "thinking")
		{
			int ishungry = gethungry();
			if(ishungry == 1)
			{
				v.setoper("hungry");
				g.debugtext += i + " is hungry.\n";
			}
		}
		
		if(v.getoper() == "eating")
		{
			v.setoper("thinking");
			g.debugtext += i + " is thinking from eating.\n";
		}
		
		//p.h ∧ fork(p, q) = q −→ req(p, q) := q;
		if(v.getoper()=="hungry" && !v.tail.isEmpty())
		{
			Iterator<Integer> tail = v.tail.iterator();
			while(tail.hasNext())
			{
				int tailnum = tail.next();
				g.vertex[g.edges[tailnum].getend()].setrequire(true);
				g.debugtext += i + " sends request to " + g.edges[tailnum].getend() + ".\n";
			}
		}
		
		
		//p.h ∧ (∀q : E (p, q) : fork(p, q) = p ∧ (clean(p, q) ∨ req(p, q) = q))
		//−→ p.state := eating; clean(p, q) := false;
		Iterator<Integer> head = v.head.iterator();
		Boolean tmp = true;
		while(head.hasNext())
		{
			int num = head.next();
			tmp = tmp && g.edges[num].stat.clean;
		}
		tmp = tmp || !v.isrequired();
		if(v.getoper()=="hungry" && v.tail.isEmpty() && tmp)
		{
			v.setoper("eating");
			g.debugtext += i + " is eating.\n";
			head = v.head.iterator();
			while(head.hasNext())
			{
				int edgenum = head.next();
				g.edges[edgenum].stat.clean = false;
				g.debugtext += "edge " + edgenum + " is dirty.\n";
			}
		}

		//req(p, q) = p ∧ fork(p, q) = p ∧ ¬clean(p, q) ∧ ¬p.e
		//−→ fork(p,q) := q; clean(p, q) := true;
		if(v.isrequired())
		{
			head = v.head.iterator();
			tmp = true;
			while(head.hasNext())
			{
				int edgetmp = head.next();
				if(g.vertex[g.edges[edgetmp].getstart()].getoper()=="hungry" && !g.edges[edgetmp].stat.clean && v.getoper()!="eating")
				{
					int currenthead = g.edges[edgetmp].getstart();
					int currenttail = g.edges[edgetmp].getend();
					g.edges[edgetmp].stat.clean = true;
					v.head.remove(new Integer(edgetmp));
					v.tail.add(edgetmp);
					g.edges[edgetmp].setstart(currenttail);
					g.edges[edgetmp].setend(currenthead);
					
					//Besides remove the exchange the head and tail for the current vertex, the another endpoint
					//of the edge also need to exchange its head and tail.
					//This direction of the edge has already been converted.
					g.vertex[g.edges[edgetmp].getend()].head.add(edgetmp);
					g.vertex[g.edges[edgetmp].getend()].tail.remove(new Integer(edgetmp));
					
					v.setrequire(false);
					
					g.debugtext += i + " sends folk to " + g.edges[edgetmp].getend() + "\n";
					g.debugtext += "edge " + edgetmp + " is clean.\n";
					break;
				}
			}
			
		}
		g.step++;
		/*
			if(g.vertex[i].getoper() == "hungry" && g.edges[i].getstart() != i)
			{
				g.vertex[g.edges[i].getend()].setrequire(true);
				g.debugtext += "philosopher " + i + " send request to philosppher " +  g.edges[i].getend() + "<br/>";
			}
			
			if(g.vertex[i].getoper() == "hungry")
			{
				for(int m=0;m<g.vertex[i].relatev.size();m++)
				{
				    if(g.edges[g.vertex[i].relatev.get(m)-1].getend() == i)
				    {
				    	//System.out.println(g.vertex[i].relatev.get(m)-1);
				    	if(g.edges[g.vertex[i].relatev.get(m)-1].stat.getColor() == Color.GREEN || !g.vertex[g.vertex[i].relatev.get(m)-1].isrequired())
				    	{
				    		g.vertex[i].setoper("eating");
				    		g.edges[g.vertex[i].relatev.get(m)-1].stat.setColor(Color.BLACK);
				    	}
				    }
				}
				g.debugtext += "philosopher " + i + " is eating<br/>";
			}
			
			for(int n=0;n<g.vertex[i].relatev.size();n++)
			{
				if(g.vertex[i].isrequired())
				{
					if(g.edges[g.vertex[i].relatev.get(n)-1].getend() == i && g.edges[g.vertex[i].relatev.get(n)-1].stat.getColor() == Color.BLACK && g.vertex[i].getoper() != "eating")
					{
						g.edges[g.vertex[i].relatev.get(n)-1].setend(g.vertex[i].relatev.get(n)); 
						g.edges[g.vertex[i].relatev.get(n)-1].setstart(i);
					}
					g.debugtext += "philosppher " + i + "send folk to philosopher " +  g.vertex[i].relatev.get(n) + "<br/>";
				}
			}
			*/
		//}
		/*
		int color = (int)(Math.random() * 3);
		if(color == 1)
			s.setvertexColor(Color.blue);
		if(color == 2)
			s.setvertexColor(Color.yellow);
		if(color == 3)
			s.setvertexColor(Color.red);
			*/
	}
	
	public static int gethungry()
	{
		if(Math.random() > 0.5)
			return 1;
		else
			return 0;
	}
}
