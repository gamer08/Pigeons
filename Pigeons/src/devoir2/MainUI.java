package devoir2;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.util.Random;

/**
 * Classe en charge l'affichage de toute l'interface graphique.
 * Elle comprend deux panels (pour le jeu et un panel de reglages)
 *
 */
public class MainUI  {
	
	static JFrame _frame;
	public static JButton _buttonGame;
	static JPanel _panelButton;
	static GameUI _panelGame;

	
	public MainUI()
	{
		System.out.println("Constructeur de MainUI");
		
		  setFrame();
	}
	
	/**
	 * Mise en place de la frame (fenetre principale)
	 */
	public static void setFrame()
	{
		
		System.out.println("setFrame");
		_frame = new JFrame();
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setVisible(true);
        _frame.setSize(800, 400);
        
        setPanels();
        _frame.getContentPane().add(_panelGame, BorderLayout.CENTER);
        _frame.getContentPane().add(_panelButton, BorderLayout.SOUTH);
        
        _frame.pack();
        _frame.setVisible(true);
	}
	
	/**
	 * Mise en place des deux panels (jeu et reglges)
	 */
	public static void setPanels()
	{
		System.out.println("setPanels");
		_panelGame = new GameUI();
		_panelButton = new JPanel();
		_buttonGame = new JButton("Jouer");
		_buttonGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Bouton appuyé");
				_panelGame.setPigeons(_panelGame.getGraphics());
				//_buttonGame.setEnabled(false);
			}
		});
		_panelButton.setSize(new Dimension(800, 50));
        _panelButton.add(_buttonGame);
             
	}

	
	
	


}
