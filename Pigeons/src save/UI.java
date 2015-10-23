package devoir2;
import javax.swing.JPanel;

import devoir2.Event.Type;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class UI extends JPanel implements MouseListener
{
	private Dimension _dimension;
	
	private Pigeon p;
	
	public UI()
	{
		this._dimension = new Dimension(800,600);
		this.setBackground(Color.white);
		this.addMouseListener(this);
		p = new Pigeon();
	}
	
	private static void CreateAndShowGUI() 
    {
        //Create and set up the window.
        JFrame frame = new JFrame("Pigeons");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Add contents to the window.
        frame.add(new UI());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	
	@Override
    public Dimension getPreferredSize() 
  	{
        return this._dimension;
    }
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		MessageBroker.GetInstance().Publish(new Event(Type.FOOD, new Position(0, 0)));
		
		/*int x = e.getX();
		int y = e.getY();
		
		System.out.print("Clique à la position" + System.lineSeparator());
		System.out.print("X :" + x + System.lineSeparator());
		System.out.print("Y :" + y + System.lineSeparator());*/
	}


	@Override
	public void mouseEntered(MouseEvent e) 
	{
		
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{

	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{

	}
	
	public static void main (String[] args)
	{
		CreateAndShowGUI();
	}

}
