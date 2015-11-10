package devoir2;
import javax.swing.JPanel;

import devoir2.Event.Type;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * JPanel qui possède les piegons et la nourriture
 *
 */
public class PanelGame extends JPanel implements MouseListener
{
	static Dimension _dimension;
	public ArrayList<Pigeon> _pigeons;
	public int _nbPigeons=10;
	public ArrayList<Food> _food;
	private volatile boolean _isGameRefreshNeeded;


	public PanelGame(int w, int h)
	{	
		_isGameRefreshNeeded = false;
		
		
		this._dimension = new Dimension(w,h);
		this.setBackground(Color.green);
		this.addMouseListener(this);
		
		_pigeons = new ArrayList<Pigeon>();
		_food = new ArrayList<Food>();
		
		for (int i=0; i<_nbPigeons; i++)
		{
			Pigeon p = new Pigeon(_dimension.width, _dimension.height,this);
			_pigeons.add(p);
		}	
	}
	
	@Override
    public Dimension getPreferredSize() 
  	{
        return this._dimension;
    }
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		
		int x = e.getX();
		int y = e.getY();

		
		synchronized(_food)
		{
			Food f = new Food(x, y);
					
			MessageBroker.GetInstance().Publish(new Event(Type.FOOD,new Vector(x,y)));
			_food.add(f);
						
			// Caractéristiques de la toute dernière nourriture
			_food.get(_food.size()-1).setSymbol("food.png"); // la dernière food est commestible
			_food.get(_food.size()-1)._isFresh = true;

			
		}
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
	
	 @Override
	 public void paintComponent(Graphics g) 
 	 {
        super.paintComponent(g);

        drawPigeons(g);
        drawFood(g);

        Toolkit.getDefaultToolkit().sync();  
 	 }
	 
	 private void drawPigeons(Graphics g)
	 {
	     for (Pigeon p : _pigeons)
	     {
	    	 Image imgPigeon = getToolkit().getImage(p.getSymbol());
	    	 g.drawImage(imgPigeon, (int)p.getPosition()._x, (int)p.getPosition()._y, null);
	     }
	 }
	 

	 /**
	  * Méthode permettant de designer de la nourriture 
	  * @param x Abscisse du clic
	  * @param y Ordonnées du clic
	  * @param g
	  */
	 public void drawFood(Graphics g)
	 {
		 synchronized(_food)
		 {
			 
			 
			 for (Food f : _food)
				 
			 {
				 Image imgFood = getToolkit().getImage(f.getSymbol());
				 
				 if (f.isEaten() == false)
				 {
					 g.drawImage(imgFood, f._position._x, f._position._y, null); // on dessine seulement les nourritures non mangées
				 }
			 }
			 
		 }
		 		 
	 }
	 
		
		public synchronized boolean CanRefreshGame()
		{
			for (Pigeon p : _pigeons)
			{
				if (p.IsUpdating())
					return false;
			}
			
			return true;
		}
		
		public synchronized void SetGameRefreshNeeded(boolean value)
		{
			_isGameRefreshNeeded = value;
		}
		
		public synchronized boolean GetGameRefreshNeeded()
		{
			return _isGameRefreshNeeded;
		}
		
}
