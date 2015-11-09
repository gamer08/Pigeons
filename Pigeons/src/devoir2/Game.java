package devoir2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import devoir2.Event.Type;

public class Game implements Runnable
{
	private MainWindow _ui;
	private Thread _thread;
	private boolean _isRunning;
	private int _fps;
	private double _timePerTickInNanoSecond, _deltaTime;
	private long _now, _last;
	private boolean _isRefreshNeeded;
	private Timer _timer; // timer utilisé pour l'evenemnt d'effraiement des pigeons
	private ActionListener _frightenPigeons;
	private int _timeRandom;
	private int _nbPigeonsMoving;
	
	
	public Game()
	{
		_isRunning = _isRefreshNeeded = false;
		
	}
	
	private void Init()
	{
		_ui = new MainWindow();
		_fps =30;
		_timePerTickInNanoSecond = 1000000000/_fps;
	}
	
	public synchronized void Start() 
	{
		if (_isRunning)
			return;
		
		_isRunning = true;
		_thread = new Thread(this);
		_thread.start();
	}
	
	public synchronized void Stop() 
	{
		if (!_isRunning)
			return;
		
		try 
		{
			_isRunning = false;
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
		
		_timeRandom = (5 + new Random().nextInt(5)) * 1000;
			
		
		_deltaTime = 0;
		_last = System.nanoTime();
		
		while (_isRunning)
		{
			_now = System.nanoTime();
			
			if (!_isRefreshNeeded)
			{
				
				_deltaTime+= (_now -_last) /_timePerTickInNanoSecond;
				
					
			}
				
			
			_last = _now;
			
			if (_deltaTime >=1)
			{	
				
				_isRefreshNeeded = true;
				_ui._panelGame.SetGameRefreshNeeded(true);
				
				for (Pigeon p:_ui._panelGame._pigeons)
				{
					int timer = 0;
					if (p.GetVelocity()._x == 0.0f && p.GetVelocity()._y == 0.0f)
					{
						_nbPigeonsMoving+=1;
						
					}
					
				}
				
				System.out.println(_nbPigeonsMoving);
				if (_nbPigeonsMoving == _ui._panelGame._nbPigeons)
				{
				
				 _nbPigeonsMoving = 0;
				 
				 if (Math.random() < 0.0001) 
				 {
					_ui._panelSettings._labelFrightening.setText("Effraiement");
					MessageBroker.GetInstance().Publish(new Event(Type.PANIC));
				 }
				 
				}
				
				_nbPigeonsMoving = 0;
				_ui._panelSettings._labelFrightening.setText("Rien");

				
				if (_ui._panelGame.CanRefreshGame())
				{
					Render();
					_deltaTime = 0;
					_isRefreshNeeded = false;
					_ui._panelGame.SetGameRefreshNeeded(false);
				}
			}
		}
		
		Stop();
		
	}
	
	
	private void Render()
	{
		_ui._panelGame.repaint();
	}

	public synchronized boolean IsRefreshNeeded()
	{
		return _isRefreshNeeded;
	}
	
	
}
