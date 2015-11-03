package devoir2;

import devoir2.Event.Type;

public class SteeringBehavior 
{
	private Pigeon _owner;
	private Vector _steeringForce;
	private Vector _target;
	
	private boolean _onSeek;
	
	private boolean _isScared;
	
	
	public SteeringBehavior(Pigeon pigeon)
	{
		_owner = pigeon;
		_steeringForce = new Vector();
		_target = new Vector(-1,-1);
		_onSeek = false;
		_isScared = false;
	}
	
	public void OnSeek(boolean value)
	{
		_onSeek = value;
	}
	
	public void IsScared(boolean value)
	{
		_isScared = value;
	}
	
	public void SetTarget(Vector position)
	{
		_target = position;
	}
	
	public Vector Calculate()
	{

		_steeringForce = new Vector();
		
		if (_onSeek)
			_steeringForce = Vector.Add(_steeringForce, Seek());
		
		if (_isScared)
		{
			_steeringForce = Vector.Add(_steeringForce, Seek());
		}
		
		
		
		return _steeringForce;
	}
	
	public Vector Seek()
	{
		Vector toTarget = Vector.Substract(_target, _owner.getPosition());

		
		if (Vector.getNorm(toTarget)<2 && !_isScared) // le pigeon n'est pas effrayé
		{
			OnSeek(false);
			MessageBroker.GetInstance().Publish(new Event(Type.FOOD_EXPIRED));
		}
		
		else if (Vector.getNorm(toTarget)<2 && _isScared)
		{
			IsScared(false);
		}
		
		toTarget = Vector.Normalize(toTarget);	
		
		Vector desiredVelocity = Vector.ScalarMultiplication(toTarget, _owner.GetSpeed());
		
		Vector r = Vector.Substract(desiredVelocity, _owner.GetVelocity());
		
		
		
		return r;
	}
	
	public Vector getTarget()
	{
		return _target;
	}
}
