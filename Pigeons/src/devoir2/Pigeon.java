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
	private volatile boolean _gameTerminated, _isUpdating;
	private int _fps;
	private double _timePerTickInNanoSecond, _deltaTime;
	private long _now, _last;
	private PanelGame _owner;
	
	public Pigeon(int width, int height, PanelGame panel)
	{
		Random rand = new Random();
		
		/*int xRandom;
		int yRandom;
		xRandom = rand.nextInt(width +1);
		yRandom = rand.nextInt(height +1);*/
		
		_position = new Position(width, height);
		
		_gameTerminated = false;
		_isUpdating = false;
		
		_owner = panel;
		
		Start();
	}
	
	private void Init()
	{
		_fps =1;
		_timePerTickInNanoSecond = 1000000000/_fps;
	}
	
	private void Start() 
	{
		_thread = new Thread(this);
		_thread.start();
	}
	
	public synchronized void Stop() 
	{	
		try 
		{
			_thread.join();
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public void run()
	{
		Init();
		
		_deltaTime = 0;
		_last = System.nanoTime();
		
		while(!_gameTerminated)
		{
			_now = System.nanoTime();
			if (!_owner.GetGameRefreshNeeded())
			{
				_deltaTime+= (_now -_last) /_timePerTickInNanoSecond;
				_last = _now;
				
				if (_deltaTime >=1)
				{
					Update(0.0f);
					_deltaTime = 0;
				}
				
			}
			else
				_last =_now;
			
		}
	}
	
	/*public boolean IsWainting()
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
	}*/
	
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
	
	/*private synchronized void EnableRun()
	{
		if (_canUpdate || !_hasRunAtLeastOnce)
			return;
		
		_canUpdate = true;
		

		
		synchronized (this) 
		{
			this.notify();
		}
	}*/
	
	/*public synchronized void DisableRun()
	{
		if (_canUpdate)
		{
			_canUpdate = false;
			
		}
	}*/
	
	/*private synchronized void UpdateLogic(float deltaTime)
	{
		_isUpdating = true;
		
		//_position._x+=1;
		
		System.out.println("Update " + _position._x +" " + System.nanoTime());
		
		_isUpdating = false;
	}*/
	
	
	private void Update(float deltaTime)
	{
		_isUpdating = true;
		_position._x +=5;
		_isUpdating = false;
	}
	
	
	
	public synchronized boolean IsUpdating()
	{
		return _isUpdating;
	}
	
}
