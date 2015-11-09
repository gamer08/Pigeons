package devoir2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import devoir2.Event.Type;

public class MessageBroker 
{
	private Map<Event.Type, ArrayList<SubscriberInterface>> _subscriberDictionnary;
	
	private MessageBroker()
	{
		_subscriberDictionnary = new HashMap<Event.Type, ArrayList<SubscriberInterface>>();
		_subscriberDictionnary.put(Type.FOOD, new ArrayList<SubscriberInterface>());
		_subscriberDictionnary.put(Type.FOOD_EXPIRED, new ArrayList<SubscriberInterface>());
		_subscriberDictionnary.put(Type.PANIC, new ArrayList<SubscriberInterface>());
	}
	
	private static class Holder
	{
		private final static MessageBroker _instance = new MessageBroker();
	}
	
	public static MessageBroker GetInstance(){return Holder._instance;}
	
	public void Publish(Event message)
	{
		if (_subscriberDictionnary.containsKey(message._type))
		{
			ArrayList<SubscriberInterface> subs = _subscriberDictionnary.get(message._type);
			
			for (SubscriberInterface s : subs)
				s.HandleMessage(message);
		}
	}
	
	public void AddSubscriber(Event.Type type, SubscriberInterface subscriber)
	{
		ArrayList<SubscriberInterface> subs = _subscriberDictionnary.get(type);
		
		if (!subs.contains(subscriber))
			subs.add(subscriber);
	}
	
	public void RemoveSubscriber(Event.Type type, SubscriberInterface subscriber)
	{
		ArrayList<SubscriberInterface> subs = _subscriberDictionnary.get(type);
		
		if (subs.contains(subscriber))
			subs.remove(subscriber);
	}
}
