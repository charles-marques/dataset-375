package comm;

import java.io.IOException;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class Server {
	public static final int PORT = 9876;
	
	public static void main(String[] args) {
		try {
			SSLServerSocketFactory socketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
			SSLServerSocket welcomeSocket = (SSLServerSocket) socketFactory.createServerSocket(PORT);
			while (true) {
				SSLSocket socket = (SSLSocket) welcomeSocket.accept();
				(new Thread(new ServerConnection(socket))).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
