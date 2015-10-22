package devoir2;

public  class Event 
{
	public enum Type
	{
		FOOD,
		PANIC
	}
	
	// En lecture seulement
	
	public final Position _position;
	public final Type _type;
	
	private Event()
	{
		_position = new Position(0, 0);
		_type = Type.FOOD;
	}
	
	public Event (Type type, Position position)
	{
		_type = type;
		_position = position;
		
	}
	
}
