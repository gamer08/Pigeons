package devoir2;

import javax.swing.Timer;

public class Game implements Runnable
{
	private MainWindow _ui;
	private Thread _thread;
	private boolean _isRunning;
	private int _fps;
	private double _timePerTickInNanoSecond, _deltaTime;
	private long _now, _last;
	
	public Game()
	{
		_isRunning = false;
	}
	
	private void Init()
	{
		System.out.println("4 - In Game.init");
		_ui = new MainWindow();
		_fps =30;
		_timePerTickInNanoSecond = 1000000000/_fps;
	}
	
	public synchronized void Start() 
	{
		System.out.println("2 - In Game.Start");
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
		System.out.println("3 - In Game.run");
		Init();
		
		_deltaTime = 0;
		_last = System.nanoTime();
		
		while (_isRunning)
		{
			_now = System.nanoTime();
			_deltaTime+= (_now -_last) /_timePerTickInNanoSecond;
			_last = _now;
			
			if (_deltaTime >=1)
			{
				Tick(0.0f);
				Render();
				_deltaTime--;
			}
		}
		
		Stop();
		
	}
	
	
	private void Tick(float deltaTime)
	{
		
	}
	
	private void Render()
	{
		System.out.println("In Game.render");
		//_ui._panelGame.repaint();
	}

	
	
	
}
