package devoir2;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JButton;

/**
 * JPanel qui possède les piegons et la nourriture
 *
 */
public class PanelGame extends JPanel implements MouseListener
{
	static Dimension _dimension;
	public Vector<Pigeon> _pigeons;
	public int _nbPigeons=10;
	
	public PanelGame()
	{
		System.out.println("Constructeur de GameUI");
		this._dimension = new Dimension(400,500);
		this.setBackground(Color.green);
		this.addMouseListener(this);
		
		_pigeons = new Vector<Pigeon>();
		
		for (int i=0; i<_nbPigeons; i++)
		{
			Pigeon p = new Pigeon(_dimension.width, _dimension.height);
			_pigeons.add(p);
		}
		
	}
	
	public static void CreateAndShowGUI() 
    {
        //Create and set up the window.
       /* _frame = new JFrame("Pigeons");
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Instance de la classe UI pour pouvoir utiliser getGraphics
        
        GameUI ui = new GameUI();
        //Add contents to the window.
        _frame.getContentPane().add(ui);
        _frame.pack();
        _frame.setVisible(true);
        
        _g = ui.getGraphics(); // Graphics pour pouvoir dessiner*/
 
        //Display the window.
        
    }
	
	@Override
    public Dimension getPreferredSize() 
  	{
        return this._dimension;
    }
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		
		System.out.println("Panel appuyé");
		// TODO Auto-generated method stub
		
		int x = e.getX();
		int y = e.getY();
		
		System.out.print("Clic à la position" + System.lineSeparator());
		System.out.print("X :" + x + System.lineSeparator());
		System.out.print("Y :" + y + System.lineSeparator());
		drawFood(x,y,this.getGraphics());

		
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

        Toolkit.getDefaultToolkit().sync();
        
        
 	 }
	 
	 private void drawPigeons(Graphics g)
	 {
		 
		 //Graphics2D g2d = (Graphics2D) g;
	     for (Pigeon p : _pigeons)
	     {
	    	 //g2d.drawImage(p.getImage(),p.getPosition()._x,p.getPosition()._y,this);
	    	 Image imgPigeon = getToolkit().getImage(Pigeon.getSymbol());
	    	 g.drawImage(imgPigeon, p.getPosition()._x, p.getPosition()._y, null);
	     }
	 }
	 

	 /**
	  * Méthode permettant de dessiner de la nourriture 
	  * @param x Abscisse du clic
	  * @param y Ordonnées du clic
	  * @param g
	  */
	 public void drawFood(int x, int y,Graphics g)
	 {
		 Image imgFood = getToolkit().getImage(Food.getSymbol());
		 g.drawImage(imgFood, x, y, null);
	 }
	 
	 /**
		 * Méthode permettant de dessiner les pigeons au démarrage du jeu
		 */
		public void setPigeons(Graphics g)
		{
			
//			System.out.println("pigeons mis en place");
//			Random rand = new Random();
//			int xRandom = 0;
//			int yRandom = 0;
//			int width = 10;
//			int height = 50;
//			
//			/* Attente du chargement de l'image car il faut cliquer deux fois sur le bouton 
//			pour avoir affichage de l'image...*/
//			
//			
//			Image imgPigeon = getToolkit().getImage(Pigeon.getSymbol());
//			
//			// Create a MediaTracker instance,
//			// to montior loading of images
//			
//			MediaTracker tracker = new MediaTracker(this);
//			
//			if (tracker.checkID(1) == false)
//			{
//				System.out.println("attente");
//			}
//			else
//			{
//				for (int i=0; i<_nbPigeons; i++)
//				{
//					xRandom = rand.nextInt(_dimension.width +1);
//					yRandom = rand.nextInt(_dimension.height +1);
//					
//					System.out.println("xRandom: " + xRandom + " -- yRandom: " + yRandom);
//			        g.drawImage(imgPigeon, xRandom, yRandom, null);
//		
//				}
//			}
//			
		}
		
}
