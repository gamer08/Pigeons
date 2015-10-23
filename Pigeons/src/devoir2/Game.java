package devoir2;

public class Game implements Runnable
{
	private MainUI _ui;
	private Thread _thread;
	private boolean _isRunning;
	
	public Game()
	{
		_ui = new MainUI();
		_isRunning = false;
	}
	
	private void Init()
	{
		_ui = new MainUI();
		_isRunning = false;
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
		
		while (_isRunning)
		{
			Tick(0.0f);
			Render();
		}
		
		Stop();
		
	}
	
	
	private void Tick(float deltaTime)
	{
		
	}
	
	private void Render()
	{
		_ui._panelGame.repaint();
	}

	
	
	
}
