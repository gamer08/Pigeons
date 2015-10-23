package devoir2;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

public class Pigeon extends Thread
{
	static String _symbol = "pigeon.png";
	
	private final Image _image;
	private Position _position;
	
	
	public Pigeon(Position position)
	{
		AudioInputStream audioInputStream = null;
		//File f = "scared.mp3";
		/* try{
		 			//obtention d'un flux audio à partir d'un fichier (objet File)
		      audioInputStream = AudioSystem.getAudioInputStream(f);

		    } catch (UnsupportedAudioFileException e) {
		        	e.printStackTrace();
		          return;
		    } catch (IOException e) {
		            e.printStackTrace();
		            return;
		    }
		 
		 AudioFormat audioFormat = audioInputStream.getFormat();*/
		
		_position = position;
		
		_image = new ImageIcon(this.getClass().getResource("data/pigeon.png")).getImage();
		
		
		
	}
	
	public void moveTo(Position p)
	{
		
	}
	
	public static String getSymbol()
	{
		
		return _symbol;
	}
	
	public void run()
	{
		
	}

	public Image GetImage()
	{
		return _image;
	}
	
	public Position GetPosition()
	{
		return _position;
	}
	
	
}
