package devoir2;

import devoir2.Event.Type;

public class Food implements SubscriberInterface{
	
	Boolean _isFresh;
	 String _symbol = "food.png";
	Position _position;
	Boolean _isEaten;
	
	public Food(int x, int y)
	{
		_position = new Position(x,y);
		MessageBroker.GetInstance().AddSubscriber(Type.FOOD, this); // pour s'abonner à l'évènement, il y a une nouvelle nourriture
		MessageBroker.GetInstance().AddSubscriber(Type.FOOD_EXPIRED, this);
		_isFresh = true;
		_isEaten = false;
	}
	
	public  String getSymbol()
	{
		return _symbol;
	}
	
	public void setSymbol(String s)
	{
		
		_symbol = s;
	}

	@Override
	public void HandleMessage(Event event) {
		
		if (event._type == Type.FOOD)
		{
			
			
			// Toutes les autres nourriture sauf la dernière deviennent "pourries"
			setSymbol("food2.png");
			_isFresh = false;
		}
		
		else if (event._type == Type.FOOD_EXPIRED)
		{
			
			
			if (_isFresh == true)
			{
				_isEaten = true;
			}
			
		}

		
	}
	
	
	public Boolean isEaten()
	{
		return _isEaten;
	}

}
