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

import devoir2.Event.Type;
import devoir2.Vector;

public class Pigeon implements Runnable, SubscriberInterface
{
	static String _symbol = "pigeon.png";
	
	//private final Image _image;
	private Vector _position;
	private Thread _thread;
	private volatile boolean _gameTerminated, _isUpdating;
	private int _fps;
	private double _timePerTickInNanoSecond, _deltaTime;
	private long _now, _last;
	private PanelGame _owner;
	private float _fixedUpdateDeltaTime;
	public Boolean _isScared; // bool qui sert a gere le fait qu'un pigeon ne mange as quand il est effrayé
	
	//Steering behavior data
	
	private SteeringBehavior _steeringBehavior;
	private boolean _onSeek;
	private Vector _defaultVelocity ,_velocity;
	private float _speed;
	
	
	public Pigeon(int width, int height, PanelGame panel)
	{
		Random rand = new Random();
		
		int xRandom;
		int yRandom;
		xRandom = rand.nextInt(width +1);
		yRandom = rand.nextInt(height +1);
		
		//_position = new Vector(width, height);
		
		_position = new Vector(xRandom, yRandom);
		
		_gameTerminated = false;
		_isUpdating = false;
		
		_steeringBehavior = new SteeringBehavior(this);
		_onSeek = false;
		_velocity = new Vector();
		_defaultVelocity = new Vector(1,0);
		_speed = 20.0f;
		
		_fixedUpdateDeltaTime = 0.5f;
		
		_owner = panel;
		
		_isScared = false; // par défaut, un pigeon n'est pas effrayé
		
		MessageBroker.GetInstance().AddSubscriber(Type.FOOD, this);
		MessageBroker.GetInstance().AddSubscriber(Type.PANIC, this);
		MessageBroker.GetInstance().AddSubscriber(Type.FOOD_EXPIRED, this);
		
		Start();
	}
	
	private void Init()
	{
		_fps =30;
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
					Update(_fixedUpdateDeltaTime);
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
	
	public synchronized Vector getPosition()
	{
		return _position;
	}
	
	public synchronized boolean IsUpdating()
	{
		return _isUpdating;
	}
	
	public Vector GetVelocity()
	{
		return _velocity;
	}
	
	public float GetSpeed()
	{
		return _speed;
	}
	
	private void Update(float deltaTime)
	{

		_isUpdating = true;
		
		Vector steeringForce = new Vector();
		
		steeringForce = _steeringBehavior.Calculate();
		steeringForce = Vector.ScalarMultiplication(steeringForce, deltaTime);
			
		//_velocity = Vector.Add(_velocity, steeringForce);
		
		_velocity = steeringForce;
		
		_velocity = Vector.ScalarMultiplication(_velocity, deltaTime);
		
		_position = Vector.Add(_position, _velocity);
		
		_isUpdating = false;
		
		
	}
	
	public void HandleMessage(Event event)
	{


		if (event._type == Type.FOOD)
		{
			_steeringBehavior.OnSeek(true);
			_steeringBehavior.SetTarget(event._position);
			

		}
		
		/**
		 * Cas où l'évènement est un effraiement
		 * Le calcul de la "target" doit être différent pour chaque pigeon
		 * TO DO: éviter que le pigeon arrive sur la nourriture
		 */
		else if (event._type == Type.PANIC)
		{
			_isScared = true; // pigeon effrayé
			_steeringBehavior.OnSeek(false);
			_steeringBehavior.IsScared(true);
			Random rand = new Random();
			int xRandom;
			int yRandom;
			xRandom = rand.nextInt(_owner._dimension.width +1);
			yRandom = rand.nextInt(_owner._dimension.height +1);
			_steeringBehavior.SetTarget(new Vector(xRandom, yRandom));
		}
		
		else if (event._type == Type.FOOD_EXPIRED)
		{
			_steeringBehavior.OnSeek(false);
		}
		
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
}
