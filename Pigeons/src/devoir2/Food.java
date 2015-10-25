package devoir2;

import java.awt.Image;

public class Food {
	
	Boolean _isFresh;
	static String _symbol = "food.png";
	Position _position;
	
	public Food(int x, int y)
	{
		_position = new Position(x,y);
	}
	
	public static String getSymbol()
	{
		return _symbol;
	}

}
