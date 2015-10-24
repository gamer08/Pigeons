package devoir2;

import java.awt.Image;

public class Food {
	
	Boolean _isFresh;
	static String _symbol = "food.png";
	
	public Food()
	{
		
	}
	
	public static String getSymbol()
	{
		return _symbol;
	}
	
	public void setSymbol (String s)
	{
		_symbol = s;
	}

}
