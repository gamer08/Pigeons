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

public class Pigeon extends Thread implements Observer
{
	static String _symbol = "pigeon.png";
	
	
	public Pigeon()
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

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	
}
