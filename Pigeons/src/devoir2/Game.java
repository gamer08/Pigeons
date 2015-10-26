package devoir2;

import java.util.Vector;

import javax.swing.Timer;

public class Game implements Runnable
{
	private MainWindow _ui;
	private Thread _thread;
	private boolean _isRunning;
	private int _fps;
	private double _timePerTickInNanoSecond, _deltaTime;
	private long _now, _last;
	private boolean _isRefreshNeeded;
	
	
	public Game()
	{
		_isRunning = _isRefreshNeeded = false;
		
	}
	
	private void Init()
	{
		_ui = new MainWindow();
		_fps =1;
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
		
		_deltaTime = 0;
		_last = System.nanoTime();
		
		while (_isRunning)
		{
			_now = System.nanoTime();
			
			if (!_isRefreshNeeded)
				_deltaTime+= (_now -_last) /_timePerTickInNanoSecond;
			
			_last = _now;
			
			if (_deltaTime >=1)
			{	
				_isRefreshNeeded = true;
				_ui._panelGame.SetGameRefreshNeeded(true);
				
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
	
	/*private void Tick(float deltaTime)
	{
		_ui._panelGame.UpdatePigeon(deltaTime);
	}*/
	
	private void Render()
	{
		_ui._panelGame.repaint();
	}

	public synchronized boolean IsRefreshNeeded()
	{
		return _isRefreshNeeded;
	}
	
//	public synchronized void UpdatePigeon(float deltaTime)
//	{
//		for (Pigeon p : _pigeons)
//		{	
//			p.Update(deltaTime);
//		}
//	}
//	
//	private synchronized void DisableUpdatePigeons()
//	{
//		for (Pigeon p : _pigeons)
//		{
//			p.DisableRun();
//		}
//	}
//	
//	public synchronized boolean TryRefreshGame()
//	{
//		/*for (Pigeon p : _pigeons)
//		{
//			if (p.IsUpdating())
//				return false;
//		}*/
//		
//		DisableUpdatePigeons();
//		
//		return true;
//	}
//	
}
