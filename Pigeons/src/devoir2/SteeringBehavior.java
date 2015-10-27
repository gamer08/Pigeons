package devoir2;

public class SteeringBehavior 
{
	private Pigeon _owner;
	private Vector _steeringForce;
	private Vector _target;
	
	private boolean _onSeek;
	
	
	public SteeringBehavior(Pigeon pigeon)
	{
		_owner = pigeon;
		_steeringForce = new Vector();
		_target = new Vector(-1,-1);
		_onSeek = false;
	}
	
	public void OnSeek(boolean value)
	{
		_onSeek = value;
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
		
		return _steeringForce;
	}
	
	public Vector Seek()
	{
		Vector toTarget = Vector.Substract(_target, _owner.getPosition());
		toTarget = Vector.Normalize(toTarget);
		Vector desiredVelocity = Vector.ScalarMultiplication(toTarget, _owner.GetSpeed());
		
		return Vector.Substract(desiredVelocity, _owner.GetVelocity());
	}
}
