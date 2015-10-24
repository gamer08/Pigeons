package devoir2;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

public class Pigeon
{
	static String _symbol = "pigeon.png";
	
	//private final Image _image;
	private Position _position;
	
	
	public Pigeon(int width, int height)
	{
		System.out.println("9 - In Pigeon (constructor)");
		Random rand = new Random();
		
		int xRandom;
		int yRandom;
		xRandom = rand.nextInt(width +1);
		yRandom = rand.nextInt(height +1);
		
		_position = new Position(xRandom, yRandom);
		
		//_image = new ImageIcon(this.getClass().getResource(_symbol)).getImage();
		
		
		
	}
	
	public void moveTo(Position p)
	{
		
	}
	
	public static String getSymbol()
	{
		
		return _symbol;
	}
	

	/*public Image getImage()
	{
		//return _image;
	}*/
	
	public Position getPosition()
	{
		return _position;
	}
	
	
}
