package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

import comm.APICall;
import comm.ClientConnection;

import model.Event;
import model.Status;
import model.User;
import net.miginfocom.swing.MigLayout;

/**
 * Adding participants to the event or the view others calendar method.
 *
 */

public class ParticipantList extends JPanel{
	
	private JLabel people, participants, search;
	private JTextField searchField;
	private JList peopleList, participantList;
	private JButton cancelButton, okButton;
	private JScrollPane peopleScrollPane, participantScrollPane;
	private DefaultListSelectionModel selectionModel1;
	private DefaultListSelectionModel selectionModel2;
	private ListModel peopleListModel;
	private ListModel participantListModel;
	private ArrayList<User> revertToPreviousState;
	private Event event;
	private EventWindow eWindow;
	
	public static void createListWindow(ParticipantList participantList){
		JFrame frame = new JFrame("Legg til deltakere");
		//ParticipantList pList = new ParticipantList();
		
		//pList = participantList;
		frame.setContentPane(participantList);
		frame.pack();
		
		//sets boundaries for the window
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		frame.setSize(screenWidth/4, screenHeight/2);
		frame.setMinimumSize(new Dimension(screenWidth/8, screenHeight/3));
		frame.setLocation(screenWidth/3, screenHeight/3);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public ListModel getModel(){
		return participantListModel;
	}
	
	protected EventWindow getEventWindow() {
		return eWindow;
	}
	
	public void setModel(ListModel listModel){
		emptyReverteState();
		this.participantListModel = listModel;
		copyOldModel(listModel);
	}
	
	public void copyOldModel(ListModel listModel){
		for(int i = 0; i < listModel.size(); i++) {
			revertToPreviousState.add((User) listModel.get(i));
		}
	}
	
	public ListModel getOldModel(){
		participantListModel.clear();
		for(int i = 0; i < revertToPreviousState.size(); i++) {
			participantListModel.addElement(revertToPreviousState.get(i));
		}
		
		return participantListModel;
	}
	
	public void emptyReverteState() {
		revertToPreviousState.clear();
	}
	
	public ParticipantList(Event event, EventWindow eWindow) {
		this.event = event;
		this.eWindow = eWindow;
		
		peopleListModel = new ListModel();
		participantListModel = new ListModel();
		
		Map<User, Status> ustatus = event.getUserStatuses();
		
		if(ustatus != null) {
			Iterator<Entry<User, Status>> it = event.getUserStatuses().entrySet().iterator();
			while(it.hasNext()) {
				Entry<User, Status> pairs = it.next();
				participantListModel.addElement(pairs.getKey());
			}			
		}
		
		participantListModel = getModel();
		revertToPreviousState = new ArrayList<User>();
		copyOldModel(participantListModel);
		
		initialize();
		fetchUsers();
	}
	
	//Constructor
	public ParticipantList(ListModel lModel){

		
		
		
		//LEGGER DUMMIES TIL LISTEN
		peopleListModel = new ListModel();
		participantListModel = lModel;
		participantListModel = getModel();
		revertToPreviousState = new ArrayList<User>();
		copyOldModel(lModel);
		initialize();
		fetchUsers();
	}
	
	private void initialize() {
		setLayout(new MigLayout("fill"));
		people = new JLabel("Personer");
		participants = new JLabel("Inviterte deltakere");
		search = new JLabel("Søk");
		
		searchField = new JTextField();
		searchField.setName("SearchField");
		searchField.addMouseListener(new searchAction());
		searchField.addActionListener(new FilterUsers());
		
		peopleList = new JList();
		peopleList.addMouseListener(new addParticipant());
		participantList = new JList();
		participantList.addMouseListener(new removeParticipant());
		
		peopleList.setModel(peopleListModel);
		participantList.setModel(participantListModel);
		
		//peopleList scrollPane
		peopleScrollPane = new JScrollPane(peopleList);
		peopleScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		//participantList scrollPane
		participantScrollPane = new JScrollPane(participantList);
		participantScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		selectionModel1 = new DefaultListSelectionModel();
		selectionModel2 = new DefaultListSelectionModel();
		selectionModel1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		selectionModel2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		peopleList.setSelectionModel(selectionModel1);
		participantList.setSelectionModel(selectionModel2);
		
		cancelButton = new JButton("Avbryt");
		okButton = new JButton("OK");
		
		cancelButton.addActionListener(new cancelAction());
		okButton.addActionListener(new addAction());
		
		//add to window
		add(search, "wrap");
		add(searchField, "growx, wrap");
		add(people, "wrap");
		add(peopleScrollPane, "h 300%, grow, wrap");
		add(participants, "wrap");
		add(participantScrollPane, "h 300%, grow, span, wrap");
		add(cancelButton, "split 2, span, align right");
		add(okButton);
	}
	
	protected Event getEvent() {
		return event;
	}
	
	protected void handleAdd() {
		if(getEvent() != null) {
			Event event = getEvent();
			ListModel model = getModel();
			
			Iterator<Entry<User, Status>> it = event.getUserStatuses().entrySet().iterator();
			while(it.hasNext()) {
				Entry<User, Status> pairs = it.next();
				User user = pairs.getKey();
				
				if(!model.contains(user)) {
					event.getUserStatuses().remove(user);
				} else if(model.contains(user)) {
					model.removeElement(user);
				}
			}
			
			for(int i = 0; i < model.size(); ++i) {
				event.getUserStatuses().put((User) model.get(i), Status.WAITING);
			}
			
			getEventWindow().genListModel();
		}
	}
	
	class searchAction implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent arg0) {
			searchField.selectAll();
		}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
	}
	
	class cancelAction implements ActionListener{
		public void actionPerformed(ActionEvent e){
			setModel(getOldModel());
			emptyReverteState();
			Container frame = cancelButton.getParent();
			do frame = frame.getParent();
			while (!(frame instanceof JFrame));
			((JFrame) frame).dispose();
		}
		
	}
	
	class addAction implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//TODO add the model changing to the underlying event/calendar show other calendar.
			handleAdd();
			
			emptyReverteState();
			Container frame = okButton.getParent();
			do frame = frame.getParent();
			while (!(frame instanceof JFrame));
			((JFrame) frame).dispose();
		}
	}
	
	class addParticipant implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			JList plist = (JList) e.getSource();
			int index = plist.locationToIndex(e.getPoint());
			if(index>=0){
				User p = (User) plist.getModel().getElementAt(index);
				peopleListModel.removeElement(p);
				participantListModel.addElement(p);
			}
		}
		
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}
	
	class removeParticipant implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			JList plist = (JList) e.getSource();
			int index = plist.locationToIndex(e.getPoint());
			if(index>=0){
				User p = (User) plist.getModel().getElementAt(index);
				participantListModel.removeElement(p);
				peopleListModel.addElement(p);
			}
		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}
	
	//This method is unfinished
	class removeParticipants implements MouseListener {
		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}
	
	public void fetchUsers(){
		ClientConnection clientConnection = ClientConnection.getInstance();
		clientConnection.send(new APICall.FindUsers(this.searchField.getText()));
		List<User> users = clientConnection.receiveObject();
		peopleListModel.clear();
		for(int i = 0; i<users.size();i++){
			peopleListModel.addElement(users.get(i));
		}
		peopleListModel.removeElement(clientConnection.getUser());
	}
	
	class FilterUsers implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			fetchUsers();
		}
	}
}
