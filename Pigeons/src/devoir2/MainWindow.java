package devoir2;

import javax.swing.JFrame;
import java.awt.BorderLayout;

/**
 * Classe en charge l'affichage de toute l'interface graphique.
 * Elle comprend deux panels (pour le jeu et un panel de reglages)
 *
 */
public class MainWindow extends JFrame{
	
	static PanelSettings _panelSettings;
	static PanelGame _panelGame;
	static int _width = 800;
	static int _height = 400;
	
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
		_panelGame = new PanelGame(_width, _height);
		_panelSettings = new PanelSettings(_width, _height);
             
	}

	
	
	


}
