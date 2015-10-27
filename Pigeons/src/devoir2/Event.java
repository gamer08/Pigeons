package devoir2;



public  class Event 
{
	public enum Type
	{
		FOOD,
		FOOD_EXPIRED,
		PANIC
	}
	
	// En lecture seulement
	
	public final Vector _position;
	public final Type _type;
	
	private Event()
	{
		_position = new Vector(0, 0);
		_type = Type.FOOD;
	}
	
	public Event (Type type, Vector position)
	{
		_type = type;
		_position = position;
		
	}
	
}
