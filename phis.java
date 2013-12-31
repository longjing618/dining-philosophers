import java.util.ArrayList;
import java.util.List;

import edu.uci.ics.jung.graph.DirectedSparseGraph;

public class phis 
{
	private DirectedSparseGraph<Integer, Integer> g;
	public edge[] edges;
	public vertextstatus[] vertex;
	public String debugtext;
	public int step;
	public phis(int number)
	{
		//number = 5;
		step = 0;
		debugtext = number + " philosophers\n";
		edges = new edge[number];
		vertex = new vertextstatus[number];
		g = new DirectedSparseGraph<Integer, Integer>();
		for(int i=0;i<number;i++)
		{	
			g.addVertex(i);
			vertex[i] = new vertextstatus(); // initialize vertex
			//edges[i] = new edge();
		}
		
		//To make a graph fully connected, the number of edges is the number of vertex minus one
		for(int i=0;i<number-1;i++)
		{
			edges[i] = new edge();
		}
		int[] edgestmp = new int[(number-1)*2];
		getedges(number,edgestmp);
		for(int i = 0;i < edgestmp.length;i++)
		{
			if(i % 2 == 0)
				vertex[edgestmp[i]].tail.add(i/2);
			else
				vertex[edgestmp[i]].head.add(i/2);
		}
		for(int i = 0;i < edgestmp.length;i=i+2)
		{
			g.addEdge(i/2, edgestmp[i],edgestmp[i+1]);
			edges[i/2].setstart(edgestmp[i]);
			edges[i/2].setend(edgestmp[i+1]);
		}
		/*
		 * This is for test
		 * We need to create the graph without loop (partial order)
		 
		vertex[0].head.add(0);
		vertex[0].head.add(1);
		vertex[0].head.add(2);
		vertex[0].head.add(3);
		vertex[1].tail.add(0);
		vertex[2].tail.add(1);
		vertex[3].tail.add(2);
		vertex[4].tail.add(3);
		g.addEdge(0, 1, 0);
		g.addEdge(1, 2, 0);
		g.addEdge(2, 3, 0);
		g.addEdge(3, 4, 0);
		edges[0].setstart(1);
		edges[0].setend(0);
		edges[1].setstart(2);
		edges[1].setend(0);
		edges[2].setstart(3);
		edges[2].setend(0);
		edges[3].setstart(4);
		edges[3].setend(0);
		*/
		/*
	    for(int i=0;i<number;i++) // initialize edges
	    {
	    	int tail = (int)(Math.random() * number);
	    	while(tail == i)
	    	{
	    		tail = (int)(Math.random() * number);
	    		//System.out.print("test");
	    	}
	    	edges[i] = new edge();
	    	edges[i].setstart(i);
	    	edges[i].setend(tail);
		    g.addEdge(i, edges[i].getstart(), edges[i].getend());		    
	    }
	    if(check(edges))
	    	System.out.print("OK");
	    else
	    	System.out.print("Hi");
	    	*/
	}
	
	/*
	private boolean check(edge[] edges)
	{
		for(int i=0;i<edges.length;i++)
		{
			edge tmp = edges[i];
			while(tmp.getend() != -1)
			{
				System.out.print("test");
				if(tmp.getend()==i)
					return false;
				else
				{
					tmp = edges[tmp.getend()];
				}
			}
		}
		return true;
	}
	*/
	private void getedges(int numofvertex, int[] Arr)
	{
		int num = numofvertex - 1; //number of edges to form the fully connected graph
		int rand = num * 2;
		int flag = 0;
		int tmp;
		List<Integer> result = new ArrayList<Integer>(rand);
		for(int i=0;i<rand;i++)
		{
			while((tmp = (int)(Math.random() * (num+1))) > -1)
			{
				if(i % 2 == 1)
				{
					if(tmp == result.get(i-1))
						continue;
					if(result.contains(new Integer(tmp)) && flag == 1)
					{
						boolean all = true;
						for(int m=0;m<num;m++)
						{
							if(result.contains(new Integer(num)))
								all = all && true;
							else
								all = all && false;
						}
						if(all)
						{
							i=-1;
							result = new ArrayList<Integer>(rand);
							break;
						}
						else
							continue;
					}
					else
					{
						result.add(tmp);
						break;
					}
				}
				else
				{
					if(result.contains(new Integer(tmp)))
						flag = 1;
					else
						flag = 0;
					result.add(tmp);
					break;
				}
			}
		}
		for(int i = 0;i<result.size();i++)
			Arr[i] = result.get(i).intValue();
		//System.out.print(result.toString());
	}
	
	public DirectedSparseGraph<Integer, Integer> getg()
	{
		return g;
	}
	
	public void update()
	{
		//System.out.print("test");
		//g.removeEdge(0);
		//g.removeEdge(1);
		//g.removeEdge(2);
		//g.removeEdge(3);
		g = new DirectedSparseGraph<Integer, Integer>();
		for(int i=0;i<vertex.length;i++)
		{
			vertex[i].updatestatus();
			//System.out.println(vertex.length);
			g.addVertex(i);
		}
		
		//for(int i=0;i<edges.length;i++)
		//please notice the edges.length is not the real the number of edges
		//since the the edge is instantiate with the vertex 
		//and the number of vertex is not same as the number of edges. 
		for(int i=0;i<vertex.length-1;i++)
		{
			edges[i].updateedgestatus();
			g.addEdge(i, edges[i].getstart(), edges[i].getend());
		}
	}
}
