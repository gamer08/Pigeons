package devoir2;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

public class Pigeon implements Runnable
{
	static String _symbol = "pigeon.png";
	
	//private final Image _image;
	private Position _position;
	private Thread _thread;
	private volatile boolean _canUpdate, _hasRunAtLeastOnce, _gameTerminated, _isUpdating;
	private float _deltaTime;
	
	private boolean set;
	
	public Pigeon(int width, int height)
	{
		Random rand = new Random();
		
		int xRandom;
		int yRandom;
		xRandom = rand.nextInt(width +1);
		yRandom = rand.nextInt(height +1);
		
		_position = new Position(50, 50);
		
		_canUpdate = false;
		_gameTerminated = false;
		_isUpdating = false;
		
		_thread = new Thread(this);
		_thread.start();
	}
	
	@Override
	public void run()
	{
		while(!_gameTerminated)
		{
			_hasRunAtLeastOnce =true;	
			if (_canUpdate)
			{
				//if (!_owner.IsRefreshNeeded())
				//{
					UpdateLogic(_deltaTime);
				//}
			}
			else
			{
				synchronized(this)
				{
					try 
					{
						this.wait();
					}
					catch (InterruptedException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public boolean IsWainting()
	{
		boolean waiting = false;
		Thread.State state = _thread.getState();
		switch(state)
		{
			case TIMED_WAITING:
			case WAITING:
				waiting = true;
				break;
				
			default:
				break;
				
		}
		return waiting;
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
	
	public synchronized Position getPosition()
	{
		return _position;
	}
	
	private synchronized void EnableRun()
	{
		if (_canUpdate || !_hasRunAtLeastOnce)
			return;
		
		_canUpdate = true;
		

		
		synchronized (this) 
		{
			this.notify();
		}
	}
	
	public synchronized void DisableRun()
	{
		if (_canUpdate)
		{
			_canUpdate = false;
			
		}
	}
	
	public synchronized void Update(float deltaTime)
	{
		EnableRun();
		
		//_position._x+=1;
		
		//System.out.println("Update " + _position._x +" " + System.nanoTime());
		
		_deltaTime = deltaTime;
	}
	
	private synchronized void UpdateLogic(float deltaTime)
	{
		_isUpdating = true;
		
		//_position._x+=1;
		
		System.out.println("Update " + _position._x +" " + System.nanoTime());
		
		_isUpdating = false;
	}
	
	public synchronized boolean IsUpdating()
	{
		return _isUpdating;
	}
	
}
