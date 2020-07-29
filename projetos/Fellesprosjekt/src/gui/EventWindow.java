package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import comm.APICall;
import comm.ClientConnection;

import model.Event;
import model.Room;
import model.Status;
import model.User;
import net.miginfocom.swing.MigLayout;

public abstract class EventWindow extends JPanel {
	//fields
	private JLabel whatLabel, infoLabel,timeLabel,dash,dateLabel,roomLabel,participantsLabel,emptyLabel,locationLabel,jlLeader;
	private ListModel listModel;
	private JTextField what, info, location, jtlLeader;
	private JSpinner start, end, date;
	private JComboBox room;
	private JRadioButton reserveRoom, noRoom;
	private ButtonGroup radioButtonGroup;
	private JButton addParticipants;
	private JList participants;
	private JScrollPane participantScrollPane;
	private SpinnerDateModel endSpinnerModel;
	private SpinnerDateModel startSpinnerModel;
	private SpinnerDateModel dateModel;
	protected JButton avslagButton, lukkButton, godtaButton, slettButton, avbrytButton, okButton, opprettButton;
	
	private ParticipantList pList;
	private Event event;
	private DefaultComboBoxModel roomModel;
	
	
	public EventWindow(Event event) {
		this.event = event;
		initialize();
		
		pList = new ParticipantList(event, this);
		
		if(event != null) {
			what.setText(event.getName());
			info.setText(event.getInfo());
			jtlLeader.setText(event.getLeader().getName());
			endSpinnerModel.setValue(event.getEnd().getTime());
			startSpinnerModel.setValue(event.getStart().getTime());
			dateModel.setValue(event.getStart().getTime());
			
			if(event.getRoom() != null) {
				((DefaultComboBoxModel) room.getModel()).addElement(event.getRoom());
				room.setSelectedItem(event.getRoom());
			} else {
				location.setText(event.getInfo());
				room.setVisible(false);
				location.setVisible(true);
				noRoom.setSelected(true);
			}
			
			genListModel();
		}
		
	}
	
	protected void genListModel() {
		listModel.clear();
		Iterator<Entry<User, Status>> it = event.getUserStatuses().entrySet().iterator();
		while(it.hasNext()) {
			Entry<User, Status> pairs = it.next();
			listModel.addElement(pairs);
		}
	}
	
	//constructor
	public EventWindow(){
		this.event = new Event();
		pList = new ParticipantList(event, this);
		initialize();
	}
	
	public void initialize() {
		listModel = new ListModel();
		
		whatLabel = new JLabel("Hva");
		infoLabel = new JLabel("Info");
		timeLabel = new JLabel("Tid");
		dash  = new JLabel("-");
		dateLabel = new JLabel("Dato");
		roomLabel = new JLabel("Rom");
		participantsLabel = new JLabel("Andre deltakere");
		emptyLabel = new JLabel();
		locationLabel = new JLabel("Sted");
		
		what = new JTextField();
		info = new JTextField();
		location = new JTextField();
		
		jlLeader = new JLabel("Møteleder");
		jtlLeader = new JTextField();
		jtlLeader.setEditable(false);
		
		endSpinnerModel = new SpinnerDateModel();
		endSpinnerModel.setCalendarField(Calendar.MINUTE);
		startSpinnerModel = new SpinnerDateModel();
		startSpinnerModel.setCalendarField(Calendar.MINUTE);
		
		end = new JSpinner(endSpinnerModel);
		end.setEditor(new JSpinner.DateEditor(end, "HH:mm"));
		start = new JSpinner(startSpinnerModel);
		start.setEditor(new JSpinner.DateEditor(start, "HH:mm"));
		
		dateModel = new SpinnerDateModel();
		dateModel.setCalendarField(Calendar.DAY_OF_MONTH);
		
		date = new JSpinner(dateModel);
		date.setEditor(new JSpinner.DateEditor(date, "dd.MM.yyyy"));
		
		roomModel = new DefaultComboBoxModel();
		roomModel.addElement("Velg rom…");
		roomModel.addElement("");
		roomModel.addElement("");
		roomModel.addElement("");
		
		room = new JComboBox(roomModel);
		room.addFocusListener(new onRoomFocus());
		
		radioButtonGroup = new ButtonGroup();
		reserveRoom = new JRadioButton("Møterom");
		noRoom = new JRadioButton("Annet…");
		
		addParticipants = new JButton("Legg til deltakere…");
		addParticipants.addActionListener(new spawnListView());
		
		participants = new JList();
		participants.setModel(listModel);
		participants.setCellRenderer(new UserStatusRenderer());
		participantScrollPane = new JScrollPane(participants);
		participantScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		//adding buttons for specific classes
		lukkButton = new JButton("Lukk");
		opprettButton = new JButton("Opprett");
		avbrytButton = new JButton("Avbryt");
		godtaButton = new JButton("Godta");
		avslagButton = new JButton("Avslag");
		okButton = new JButton("OK");
		slettButton = new JButton("Slett");

		
		lukkButton.addActionListener(new lukkAction());
		avbrytButton.addActionListener(new cancelAction());
		okButton.addActionListener(new okAction());
		
		avslagButton.addActionListener(new declineAction());
		godtaButton.addActionListener(new acceptAction());
		
		opprettButton.addActionListener(new createAction());
		slettButton.addActionListener(new deleteAction());
		
		
		setLayout(new MigLayout(
				"wrap 3, fill",								//Layout constraints
				"",											//Column constraints
				""));										//row constraints
		
		
		add(whatLabel);
		add(what,"growx,span, wrap");
		
		add(infoLabel);
		add(info, "growx,span, wrap");
		
		add(timeLabel);
		add(start, "split 3, align left,growx, span");
		add(dash);
		add(end, "split 3, align left,growx");
		
		add(dateLabel);
		add(date, "growx, span, wrap");
		
		add(emptyLabel);
		radioButtonGroup.add(reserveRoom);
		radioButtonGroup.add(noRoom);
		reserveRoom.setSelected(true);
		add(reserveRoom,"align center");
		add(noRoom);
		reserveRoom.addActionListener(new ChoosePlace(0));
		noRoom.addActionListener(new ChoosePlace(1));
		
		add(locationLabel,"split 2, hidemode 2");
		add(roomLabel, "align left, split 2, hidemode 2");
		locationLabel.setVisible(false);
		roomLabel.setVisible(true);
		
		add(room,"span, growx, wrap, split 2, hidemode 3");	
		add(location, "span, growx, wrap, split 2, hidemode 3");
		
		location.setVisible(false);
		room.setVisible(true);
		
		add(jlLeader);
		add(jtlLeader, "growx, span");
		
		add(participantsLabel);
		add(addParticipants,"align right, span");
		
		add(participantScrollPane, "h 300%, span, grow");
	    setVisible(true);
	}
	
	public void makeRoomModel() {
		ClientConnection clientConnection = ClientConnection.getInstance();
		
		Calendar at = Calendar.getInstance();
		at.setTime(startSpinnerModel.getDate());
		Calendar ka = Calendar.getInstance();
		ka.setTime(endSpinnerModel.getDate());
		
		clientConnection.send(new APICall.GetAvailableRooms(at, ka));
		
		List<Room> rooms = new ArrayList<Room>();
		
		rooms = clientConnection.receiveObject();
		
		roomModel.removeAllElements();
		roomModel.addElement("Velg rom…");
		
		for(int i = 0; i < rooms.size(); i++) {
			roomModel.addElement(rooms.get(i));
		}
	}
	
	public ListModel getModel(){
		return listModel;
	}
	
	public void setModel(ListModel lModel){
		this.listModel =  lModel;
	}
	
	public void removeLeader(){
		remove(jlLeader);
		remove(jtlLeader);
	}
	
	public void removeParticipants(){
		remove(participantScrollPane);
		remove(participantsLabel);
		remove(addParticipants);
		remove(participants);
	}
	
	public Event getEvent() {
		return event;
	}
	
	public void setNotEditable(){
		what.setEditable(false);
		info.setEditable(false);
		location.setEditable(false);
		room.setEnabled(false);
		start.setEnabled(false);
		end.setEnabled(false);
		date.setEnabled(false);
		addParticipants.setEnabled(false);
		participantScrollPane.setEnabled(false);
		jtlLeader.setEditable(false);
		reserveRoom.setEnabled(false);
		noRoom.setEnabled(false);
	}
	
	class onRoomFocus implements FocusListener {
		public void focusGained(FocusEvent e) {
			makeRoomModel();
		}
		public void focusLost(FocusEvent e) {}
	}
	
	class spawnListView implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ParticipantList.createListWindow(pList);
		}
	}
	
	class ChoosePlace implements ActionListener{
		int place;
		
		ChoosePlace(int place){
			this.place = place;
		}
		
		public void actionPerformed(ActionEvent e){
			if(place == 0){
				location.setVisible(false);
				locationLabel.setVisible(false);
				room.setVisible(true);
				roomLabel.setVisible(true);
			}else{
				room.setVisible(false);
				roomLabel.setVisible(false);
				location.setVisible(true);
				locationLabel.setVisible(true);
			}
		}
	}
	
	class cancelAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Container frame = avbrytButton.getParent();
			do frame = frame.getParent();
			while (!(frame instanceof JFrame));
			((JFrame) frame).dispose();
		}
	}
	
	class lukkAction implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Container frame = lukkButton.getParent();
			do frame = frame.getParent();
			while (!(frame instanceof JFrame));
			((JFrame) frame).dispose();
		}
	}
	
	private void addToDatabase(Event event) {
		ClientConnection clientConnection = ClientConnection.getInstance();
		clientConnection.send(new APICall.AddEvent(event));
		clientConnection.receiveReply();
	}
	
	class createAction implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			Event event = getEvent();
			
			setValues(event);
			addToDatabase(event);
			
//			HomeScreen parent = HomeScreen.getInstance();
//			parent.reDraw();
			
			Container frame = opprettButton.getParent();
			do frame = frame.getParent();
			while (!(frame instanceof JFrame));
			((JFrame) frame).dispose();
		}
		
	}
	
	private void setValues(Event event) {
		event.setName(what.getText());
		event.setInfo(info.getText());
		if(room.getSelectedIndex() > 0) {
			event.setRoom((Room)room.getSelectedItem());
		} else {
			event.setLocation(location.getText());
		}
		
		Calendar c = Calendar.getInstance();
		Calendar k = Calendar.getInstance();
		
		// Get date for event
		c.setTime(dateModel.getDate());
		int sYear = c.get(Calendar.YEAR);
		int sMonth = c.get(Calendar.MONTH);
		int sday = c.get(Calendar.DAY_OF_MONTH);

		// Get start time for event
		c.setTimeInMillis(startSpinnerModel.getDate().getTime());
		int sHour = c.get(Calendar.HOUR_OF_DAY);
		int sMinute = c.get(Calendar.MINUTE);
		
		// Get end time for event
		k.setTimeInMillis(endSpinnerModel.getDate().getTime());
		int eHour = k.get(Calendar.HOUR_OF_DAY);
		int eMinute = k.get(Calendar.MINUTE);
		
		c.clear();
		k.clear();
		c.set(sYear, sMonth, sday, sHour, sMinute, 00);
		k.set(sYear, sMonth, sday, eHour, eMinute, 00);
		
		// Compose dates
		event.setStart(c);
		event.setEnd(k);
		
	}
	
	class okAction implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Event event = getEvent();
			
			setValues(event);
			
			ClientConnection clientConnection = ClientConnection.getInstance();
			if(event.getId() != 0) {
				clientConnection.send(new APICall.UpdateEvent(event));
				clientConnection.receiveReply();
			} else {
				clientConnection.send(new APICall.AddEvent(event));
				clientConnection.receiveReply();
			}
			
			HomeScreen parent = HomeScreen.getInstance();
			parent.reDraw();
			
			Container frame = okButton.getParent();
			do frame = frame.getParent();
			while (!(frame instanceof JFrame));
			((JFrame) frame).dispose();
		}
	}
	
	class declineAction implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			int eventId = getEvent().getId();
			
			ClientConnection clientConnection = ClientConnection.getInstance();
			
			clientConnection.send(new APICall.DeclineEvent(eventId));
			
			HomeScreen parent = HomeScreen.getInstance();
			parent.reDraw();
			
			Container frame = avslagButton.getParent();
			do frame = frame.getParent();
			while (!(frame instanceof JFrame));
			((JFrame) frame).dispose();
		}
		
	}
	
	class acceptAction implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			int eventId = getEvent().getId();
			
			ClientConnection clientConnection = ClientConnection.getInstance();
			
			clientConnection.send(new APICall.AcceptEvent(eventId));
			clientConnection.receiveReply();
			
			HomeScreen parent = HomeScreen.getInstance();
			parent.reDraw();
			
			Container frame = godtaButton.getParent();
			do frame = frame.getParent();
			while (!(frame instanceof JFrame));
			((JFrame) frame).dispose();
		}
		
	}
	
	class deleteAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int eventId = getEvent().getId();
			
			ClientConnection clientConnection = ClientConnection.getInstance();
			
			clientConnection.send(new APICall.DeleteEvent(eventId));
			
			Container frame = slettButton.getParent();
			do frame = frame.getParent();
			while (!(frame instanceof JFrame));
			((JFrame) frame).dispose();
		}
		
	}
	
}


class NewApointment extends EventWindow{
	
	public NewApointment(){
		super();
		removeLeader();
		add(new JLabel(""));
		
		add(avbrytButton, "align left, growx");
		add(opprettButton, "align right, growx");
	}
}

class EditSharedApointment extends EventWindow{
		
	public EditSharedApointment(Event event){
		super(event);
		add(slettButton, "growx");
		add(avbrytButton, "align right, growx");
		add(okButton, "growx");
	}
}

class ConsiderApointment extends EventWindow{
	public  ConsiderApointment(Event event){
		super(event);
		setNotEditable();
		
		add(avslagButton, "growx");
		add(godtaButton, "growx");
		add(lukkButton, "align right, growx");
	}
}
