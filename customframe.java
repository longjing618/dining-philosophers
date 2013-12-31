import java.awt.Container;
import java.awt.Dimension;
//import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
//import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
//import javax.swing.ScrollPaneConstants;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.visualization.VisualizationImageServer;

import java.util.*;

public class customframe extends JFrame implements ActionListener
{
	private Container content;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JLabel numberofphilabel;
	private JTextField numberofphi;
	private JTextArea debugtext;
	private JLabel debug;
	private JLabel position;
	//private JLabel legendlabel;
	private JTextArea legend;
	private JScrollPane scrollPane;
	private Timer t;
	private phis g;
	private GridBagConstraints c;
	VisualizationImageServer<Integer, Integer> vs;
	public customframe()
	{
		setTitle("Dining Philosophers Demo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//setSize(screenSize);
	    //content.setBackground(Color.white);
	    setLayout(new GridBagLayout());
	    c = new GridBagConstraints();
	    content = getContentPane();
 
	    //phis g = new phis(3);
	    //VisualizationImageServer<String, String> vs =
		//        new VisualizationImageServer<String, String>(new TreeLayout<String, String>(g.getg()), new Dimension(300, 300));
	    //vs = new VisualizationImageServer<String, String>(new CircleLayout<String, String>(g.getg()), new Dimension(900, 700));

	    //status defa = new status(Color.blue);
	    
	    //vs.getRenderer().setVertexRenderer(new MyRenderer(defa));
	    
	    numberofphilabel = new JLabel("Number of Philosophers");
	    position = new JLabel("   ");
	    //legendlabel = new JLabel("Legend");
	    numberofphi = new JTextField(8);
	    
	    debugtext = new JTextArea(15,15);
	    //debugtext.setSize(150, 120);
	    //debugtext.setEditable(false);
	    scrollPane = new JScrollPane(debugtext);
	    
	    button1 = new JButton("Start");
	    button2 = new JButton("Stop");
	    button3 = new JButton("Generate");
	    debug = new JLabel("");
	    legend = new JTextArea(10,15);
	    legend.setText("Edges:\nGreen mean clean.\nYellow means dirty.\nThe head of the arrow means the location of the folk.\nVertex:\nBlue means thinking\nGreen means eating\nYellow means hungry\nSquare means be required.");
	    legend.setEditable(false);
	    //legend.setSize(150, 120);
	    
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 0;
	    content.add(position,c);
	    
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 1;
	    c.gridy = 0;
	    //c.anchor = GridBagConstraints.FIRST_LINE_START;
	    content.add(numberofphilabel,c);
	    
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 1;
	    c.gridy = 1;
	    //c.anchor = GridBagConstraints.FIRST_LINE_START;
	    content.add(numberofphi,c);
	    
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 1;
	    c.gridy = 2;
	    //c.anchor = GridBagConstraints.PAGE_START;
	    content.add(button3,c);
	    
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 1;
	    c.gridy = 3;
	    //c.anchor = GridBagConstraints.PAGE_END;
	    content.add(button1,c);
	    
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 1;
	    c.gridy = 4;
	    content.add(button2,c);
	    
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 1;
	    c.gridy = 5;
	    c.gridheight = 2;
	    add(scrollPane,c);
	    
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 1;
	    c.gridy = 7;
	    c.gridheight = 0;
	    //c.weightx = 1.0;  
        //c.weighty = 1.0;
	    content.add(legend,c);
	    
	    //custom_listener handler = new custom_listener();
	    numberofphi.addActionListener(this);
	    button1.addActionListener(this);
	    button2.addActionListener(this);
	    button3.addActionListener(this);
	    
	    pack();
	    setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
		setVisible(true);
		//setResizable(false);
	}
	
	public void actionPerformed(ActionEvent event)
	{	
		if(event.getSource() == button3)
		{
			if(Arrays.asList(content.getComponents()).contains(vs))
			{
				//String s = event.getActionCommand();
				//JOptionPane.showMessageDialog(null,s);
				content.remove(vs);
			}
			
			String tmp = numberofphi.getText();
			int num = Integer.parseInt(tmp);
			g = new phis(num);
			vs = new VisualizationImageServer<Integer, Integer>(new CircleLayout<Integer, Integer>(g.getg()), new Dimension(900, 700));
			
			//status defa = new status();
			//defa.setvertexColor(Color.BLUE);
			vs.getRenderer().setVertexRenderer(new MyRenderer(g));
			
			//vs.getRenderer().setEdgeRenderer(new customedgerender());
			/*
			Transformer<String,Paint> edgeColor = new Transformer<String,Paint>() {
		        public Paint transform(String s) 
		        {
		        	if(s.equals("E1"))
		        	{
		        		return Color.BLUE;
		        	}
		        	else if(s.equals("E2"))
		        	{
		        		return Color.YELLOW;
		        	}
		        	else
		        		return Color.GREEN;          
		        }
		    };
		    vs.getRenderContext().setEdgeFillPaintTransformer(edgeColor);
		    */
			
			c = new GridBagConstraints();
			//c.fill = GridBagConstraints.HORIZONTAL;
		    c.gridx = 2;
		    c.gridy = 1;
		    c.gridheight = 8;
		    c.gridwidth = 8;
		    //c.anchor = GridBagConstraints.CENTER;
		    c.weightx = 0.2;  
	        c.weighty = 0.2;
	        c.anchor = GridBagConstraints.CENTER;
		    //c.ipadx = 40;
		    //c.ipady = 40;
			content.add(vs,c);
			content.validate();
			content.repaint();
			pack();
			setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
		}
		if(event.getSource() == button1)
		{
			content.validate();
			int dealy = 2000;
			t = new Timer();
			t.schedule(new TimerTask()
			{
	            @Override
	            public void run() {
	    			rules.applyonerule(g);
	    			//content.remove(debug);
	    			debug = new JLabel(g.debugtext);
	    			//content.add(debug);
	    			//debugtext.setBounds(10, 10, 10, 10);
	    			debugtext.setText(debug.getText());
	    			String s = debugtext.getText();
	    			int pos = s.length();
	    			debugtext.setCaretPosition(pos);
	    			//debugtext.setSize(50, 20);
	    			g.update();    			
	    			content.remove(vs);
	    			vs = new VisualizationImageServer<Integer, Integer>(new CircleLayout<Integer, Integer>(g.getg()), new Dimension(900, 700));
	    			vs.getRenderer().setVertexRenderer(new MyRenderer(g));
	    			
	    			GridBagConstraints c = new GridBagConstraints();
	    			//c.fill = GridBagConstraints.HORIZONTAL;
	    		    c.gridx = 2;
	    		    c.gridy = 1;
	    		    c.gridheight = 8;
	    		    c.gridwidth = 8;
	    		    //c.anchor = GridBagConstraints.CENTER;
	    		    c.weightx = 0.2;  
	    	        c.weighty = 0.2;
	    	        c.anchor = GridBagConstraints.CENTER;
	    		    //c.ipadx = 40;
	    		    //c.ipady = 40;
	    			
	    			content.add(vs,c);
	    			content.repaint();
	    			pack();
	    			setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
	            }
			}, 0,dealy);
		}
		if(event.getSource() == button2)
		{
			t.cancel();
			t.purge();
		}
	}
}