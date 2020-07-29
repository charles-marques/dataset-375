package comm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import model.User;

public class ClientConnection {
	private static ClientConnection instance;
	//TODO: move constants to a properties file
	private static final String SERVER_HOSTNAME = "localhost";
	private static final int SERVER_PORT = 9876;
	private SSLSocket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private User user = null;
	
	/**
	 * Private constructor enforces the use of getInstance()
	 */
	private ClientConnection() {
		try {
			socket = (SSLSocket) SSLSocketFactory.getDefault().createSocket(SERVER_HOSTNAME, SERVER_PORT);
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(socket.getInputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Get a ClientConnection instance. At most one instance exists at any given time.
	 * @return a ClientConnection instance
	 */
	public static ClientConnection getInstance() {
		if (instance == null)
			instance = new ClientConnection();
		return instance;
	}
	
	/**
	 * Send an APICall to the server
	 * @param call an APICall
	 */
	public void send(APICall call) {
		try {
			out.writeObject(call);
		} catch (IOException e) {
			System.err.println("Could not send the API call: " + e.getMessage());
		}
	}
	
	/**
	 * Check if an object is available to be read from the server. If it is, return
	 * the object as an instance of the specified type T. If not, return {@code null}
	 * @return an instance of T (or {@code null})
	 */
	@SuppressWarnings("unchecked")
	public <T> T receiveObject() {
		Object result = null;
		try {
			Object input = in.readObject();
			if (input == ServerReply.NEXT_IS_OBJECT)
				result = in.readObject();
		} catch (ClassCastException e) {
			System.err.println("Could not cast the object to the specified type: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("There is an error with the input stream: " + e.getMessage());;
		} catch (ClassNotFoundException e) {
			System.err.println("The received object doesn't match a known class: " + e.getMessage());
		}
		return (T)result;
	}
	
	/**
	 * Get a {@code ServerReply} from the server. If the object available from the server
	 * isn't a {@code ServerReply}, return {@code null}
	 * @return a {@code ServerReply} (or {@code null})
	 */
	public ServerReply receiveReply() {
		ServerReply result = null;
		try {
			Object input = in.readObject();
			if (input instanceof ServerReply)
				result = (ServerReply) input;
		} catch (IOException e) {
			System.err.println("There is an error with the input stream: " + e.getMessage());;
		} catch (ClassNotFoundException e) {
			System.err.println("The received object doesn't match a known class: " + e.getMessage());
		}
		return result;
	}
	
	/**
	 * Set the user associated with this connection
	 * @param user a User object
	 */
	public void setUser(User user) {
		if (this.user == null)
			this.user = user;
	}
	
	/**
	 * Get the user associated with this connection
	 * @return a User object
	 */
	public User getUser() {
		return this.user;
	}
	
}
