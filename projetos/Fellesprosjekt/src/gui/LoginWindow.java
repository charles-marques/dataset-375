package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;

import comm.APICall;
import comm.ClientConnection;
import comm.ServerReply;

import model.User;
import net.miginfocom.swing.MigLayout;

/**
 * Class to handle LoginScreen for the Calendarsystem
 * TODO - implement DB connection and listeners with real functions 
 */

public class LoginWindow extends JPanel{
	private ClientConnection conn;
	private JLabel userName, passWord, Warning;
	private JTextField inputName;
	private JPasswordField inputPW;
	private JButton logInButton;
	
	//Test frame
	
	public static void createLoginWindow(){
		JFrame frame = new JFrame("Innlogging");
		LoginWindow loginWindow = new LoginWindow();
		
		frame.setContentPane(loginWindow);
		frame.pack();

		//sets boundaries for the window
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		frame.setSize(screenWidth/5,screenHeight/5);
		frame.setMinimumSize(new Dimension(screenWidth/10,screenHeight/6));
		frame.setLocation(screenWidth/3, screenHeight/3);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Replace with DISPOSE at some point
	}
	
	//Constructor 
	public LoginWindow(){
		conn = ClientConnection.getInstance();
		setLayout(new MigLayout("fill"));
		userName = new JLabel("Brukernavn: ");
		passWord = new JLabel("Passord: ");
		Warning = new JLabel("Feil brukernavn eller Passord!");
		Warning.setForeground(Color.RED);
		Warning.setVisible(false);
		
		
		inputName = new JTextField();
		inputName.setName("inputName");
		inputName.addMouseListener(new ClearError());
		inputName.addKeyListener(new keyAction());
		
		inputPW = new JPasswordField();
		inputPW.setEchoChar('*');
		inputPW.setName("inputName");
		inputPW.addMouseListener(new ClearError());
		inputPW.addKeyListener(new keyAction());
		
		logInButton = new JButton("Logg inn");
		logInButton.addActionListener(new ButtonAction());
		logInButton.addKeyListener(new keyAction());
		
		//add to Panel
		add(Warning, "span, align center");
		add(userName);
		add(inputName, "w 75%, growx, wrap");
		add(passWord);
		add(inputPW, "w 75%, growx, wrap");
		add(logInButton, "span, align right,wrap");	
		setSize(new Dimension(200, 200));
	}
	
	public void tryLogin(){
		String username = inputName.getText();
		String password = "";
		char[] temp = inputPW.getPassword();
		for(int i = 0; i<temp.length;i++){
			password = password + temp[i];
			temp[i] = 0;
		}
		conn.send(new APICall.Login(username, password));
		if(conn.receiveReply() == ServerReply.LOGIN_OK) {
			User user = conn.receiveObject();
			conn.setUser(user);
			MainFrame.createMainWindow();
			Container frame = logInButton.getParent();
			do frame = frame.getParent();
			while (!(frame instanceof JFrame));
			((JFrame) frame).dispose();
		}
		else{
			Warning.setVisible(true);
		}
	}
	
	class keyAction implements KeyListener{
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_ENTER){
				tryLogin();
			}
		}
		public void keyReleased(KeyEvent e) {	
		}
		public void keyTyped(KeyEvent e) {
		}
		
	}
	
	
	class ButtonAction implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			tryLogin();
		}
	}
	
	class ClearError implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent arg0) {
			Warning.setVisible(false);
		}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0){} 
	}
}
