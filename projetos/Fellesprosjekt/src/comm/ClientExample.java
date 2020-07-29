package comm;

import java.util.Calendar;
import java.util.List;

import model.Event;
import model.User;

public class ClientExample {
	public static void main(String[] args) {
		ClientConnection clientConnection = ClientConnection.getInstance();
		clientConnection.send(new APICall.Login("test", "test"));
		if (clientConnection.receiveReply() == ServerReply.LOGIN_OK) {
			User u = clientConnection.receiveObject();
			clientConnection.setUser(u);
			System.out.println("ID: " + u.getID() + ", name: " + u.getName());
			
			Calendar start = Calendar.getInstance();
			Calendar end = Calendar.getInstance();
			start.clear();
			end.clear();
			start.set(2012, 0, 1, 8, 0); // 1. januar 2012 kl 08:00
			end.set(2012, 4, 1, 8, 0); // 1. mai 2012 kl 08:00
			clientConnection.send(new APICall.GetEventsInPeriod(start, end));
			List<Event> events = clientConnection.receiveObject();
			System.out.println(events);
			clientConnection.send(new APICall.Logout());
		}
	}
}
