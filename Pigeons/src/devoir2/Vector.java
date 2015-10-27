package devoir2;

public class Vector 
{
	public float _x;
	public float _y;
	
	public Vector()
	{
		_x = _y = 0.0f;
	}
	
	public Vector(float x, float y)
	{
		_x = x;
		_y = y;
	}
	
	public Vector(Position position)
	{
		_x = position._x;
		_y = position._y;
	}
	
	public static Vector Add(Vector vector1, Vector vector2)
	{
		Vector result = new Vector();
		result._x = vector1._x + vector2._x;
		result._y = vector1._y + vector2._y;
		
		return result;
	}
	
	public static Vector Substract(Vector vector1, Vector vector2)
	{
		Vector result = new Vector();
		result._x = vector1._x - vector2._x;
		result._y = vector1._y - vector2._y;
		
		return result;
	}
	
	public static Vector ScalarMultiplication(Vector vector, float value)
	{
		Vector result = new Vector();
		result._x = vector._x * value;
		result._y = vector._y * value;
		
		return result;
	}
	
	public static Vector Normalize(Vector vector)
	{
		Vector result = new Vector();
		
		float normeSquared = (vector._x * vector._x) + (vector._y * vector._y);
		
		if (normeSquared == 0)
			result._x = result._y = 0.0f;
		else
		{
			result._x = (float) (vector._x / Math.sqrt(normeSquared));
			result._y = (float) (vector._y / Math.sqrt(normeSquared));
		}
		return result;
	}	
}
