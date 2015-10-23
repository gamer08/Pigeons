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
public class MainWindow extends JFrame{
	
	static PanelSettings _panelSettings;
	static PanelGame _panelGame;

	
	public MainWindow()
	{
		System.out.println("Constructeur de MainUI");
		
		  setFrame();
	}
	
	/**
	 * Mise en place de la frame (fenetre principale)
	 */
	public void setFrame()
	{
		
		System.out.println("setFrame");
		setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(800, 400);
        setLocationRelativeTo(null);
        
        setPanels();
        this.getContentPane().add(_panelGame, BorderLayout.CENTER);
        this.getContentPane().add(_panelSettings, BorderLayout.SOUTH);
        
        this.pack();
        this.setVisible(true);
	}
	
	/**
	 * Mise en place des deux panels (jeu et reglges)
	 */
	public static void setPanels()
	{
		System.out.println("setPanels");
		_panelGame = new PanelGame();
		_panelSettings = new PanelSettings();
             
	}

	
	
	


}
