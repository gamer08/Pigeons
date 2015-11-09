package devoir2;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import devoir2.Event.Type;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class PanelSettings extends JPanel implements MouseListener{
	
	public static JButton _buttonGame;
	
	public PanelSettings(int w, int h)
	{
		setSize(new Dimension(800, 50));
		_buttonGame = new JButton("Effrayer les pigeons");
		
		/**
		 * Le bouton va envoyer un message "PANIC"
		 */
		_buttonGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MessageBroker.GetInstance().Publish(new Event(Type.PANIC));
			}
		});
        add(_buttonGame);
        
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Bouton cliqué");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
