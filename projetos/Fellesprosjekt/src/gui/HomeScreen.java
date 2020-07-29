package gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import comm.APICall;
import comm.ClientConnection;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import net.miginfocom.swing.MigLayout;
import model.Event;
import model.Message;
import model.MessageType;
import model.User;


public class HomeScreen extends JPanel{
	private static HomeScreen instance;
	public JPanel [][]calendarFill = new JPanel[96][8];
	public String []time = new String[96];
	public String[] weekDays = {"Mandag", "Tirsdag", "Onsdag", "Torsdag", "Fredag", "Lørdag", "Søndag"};
	public Calendar cal = Calendar.getInstance();
	public int showWeek = cal.get(Calendar.WEEK_OF_YEAR);
	private ListModel listModel;
	private ClientConnection conn = ClientConnection.getInstance();
	private ListModel newEventModel;
	private List<Event> events;
	private List<User> users;
	
	//OPPRETTER SWING ELEMENTER
	private JPanel calendarMain;
	private JButton jbWeekBack = new JButton("<-");
	private JLabel jlWeek;
	private JButton jbWeekForward = new JButton("->");
	private JButton jbCreateNewEvent = new JButton("Opprett ny avtale");
	private JLabel jlLookAtOthersCalendars = new JLabel("Se andres kalender:");
	private JButton jbAddOthersCalendars = new JButton("+");
	private JList jliOthersCalendars, jliNewEvents;
	private JLabel jlViewNewEvents = new JLabel("Nye hendelser: ");
	private JButton jbLoggOff = new JButton("Logg ut");
	
	public static HomeScreen getInstance() {
		if (instance == null)
			instance = new HomeScreen();
		return instance;
	}
	public ListModel getModel(){
		return listModel;
	}
	
	public void setModel(ListModel lModel){
		this.listModel = lModel;
	}
	
	private HomeScreen(){
		/////////////////////////////////////
//		Calendar c1 = Calendar.getInstance();
//		Calendar c2 = Calendar.getInstance();
//		c1.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
//		c2.set(Calendar.DAY_OF_WEEK, 6);
		Calendar c1 = Calendar.getInstance();
		c1.setFirstDayOfWeek(Calendar.MONDAY);
		int thisWeek = c1.get(Calendar.WEEK_OF_YEAR);
		
		c1.add(Calendar.WEEK_OF_YEAR, showWeek-thisWeek);
		
		Calendar c2 = Calendar.getInstance();
		c2.setFirstDayOfWeek(Calendar.MONDAY);
		int thisWeek2 = c2.get(Calendar.WEEK_OF_YEAR);
		
		c2.add(Calendar.WEEK_OF_YEAR, showWeek-thisWeek2);
		
		conn.send(new APICall.GetEventsInPeriod(c1, c2));
		events = conn.receiveObject();
		/////////////////////////////////////
		//SETTER LAYOUTMANAGER (MIGLAYOUT)
		JPanel rightPanel = new JPanel(new MigLayout("wrap 3"));
		calendarMain = new JPanel(new MigLayout("wrap 8", "[grow]0[grow]0[grow]0[grow]0[grow]0[grow]0[grow]0[grow]", "[]0[]"));
		this.setLayout(new MigLayout());
		
		//ADDEER ACTION LISTENERS TIL KNAPPER
		jbWeekBack.addActionListener(new ChangeWeek(0));
		jbWeekForward.addActionListener(new ChangeWeek(1));
		jlWeek = new JLabel("   "+showWeek);
		jbCreateNewEvent.addActionListener(new ButtonListener("NewApointment"));
		jbAddOthersCalendars.addActionListener(new ButtonListener("ParticipantList"));
		
		//FYLLER LISTENE MED DUMMY SHIT
		listModel = new ListModel();
		users = new ArrayList<User>();		
		newEventModel = new ListModel();

		populateMessages();

		jliOthersCalendars = new JList(listModel);
		jliNewEvents = new JList(newEventModel);
		jliNewEvents.addMouseListener(new messageAction());
		
		jbLoggOff.addActionListener(new logoutAction());
		
		//LEGGER TIL BORDERS, SCROLLBARS OG SETTER VISIBLE ROWCOUNT TIL 5
		jliOthersCalendars.setBorder(BorderFactory.createLoweredBevelBorder());
		jliNewEvents.setBorder(BorderFactory.createLoweredBevelBorder());
		jliOthersCalendars.setVisibleRowCount(5);
		jliNewEvents.setVisibleRowCount(5);
		JScrollPane jliOthersCalendarsSP = new JScrollPane(jliOthersCalendars);
		JScrollPane jliNewEventsSP = new JScrollPane(jliNewEvents);
		
		//SETTER STØRRELSE, BAKGRUNN, BORDER OG SCROLL PÅ SELVE CALENDER PANELET
		calendarMain.setPreferredSize(new Dimension(1100, 3000));
		calendarMain.setBackground(Color.WHITE);
		calendarMain.setBorder(BorderFactory.createLoweredBevelBorder());
		JScrollPane calendarMainSP = new JScrollPane(calendarMain);
		
		//LEGGER TIL ELEMENTER I RIGHTPANEL
		rightPanel.add(jbWeekBack, "align left,span, split 3, sg 1, growx");
		rightPanel.add(jlWeek, "align right, sg 1");
		rightPanel.add(jbWeekForward, "align right,sg 1,growx");
		rightPanel.add(jbCreateNewEvent, "span, growx");
		rightPanel.add(jlLookAtOthersCalendars, "span 2");
		rightPanel.add(jbAddOthersCalendars,"align right");
		rightPanel.add(jliOthersCalendarsSP, "span, grow");
		rightPanel.add(jlViewNewEvents, "span");
		rightPanel.add(jliNewEventsSP, "span, grow");
		rightPanel.add(jbLoggOff, "span, align right");
		
		setDefaultCalendar();
		getEvents();
		fillCalendar();

		//LEGGER CALENDARPANEL OG RIGHTPANEL TIL HOVEDPANELET
		this.add(calendarMainSP);
		this.add(rightPanel, "top");
	}
	
	public void populateMessages() {
		ClientConnection clientConnection = ClientConnection.getInstance();
		clientConnection.send(new APICall.GetMessages(clientConnection.getUser().getUsername()));
		
		List<Message> messages = clientConnection.receiveObject();
		
		newEventModel.clear();
		
		for(int i = 0; i<messages.size();i++){
			newEventModel.addElement(messages.get(i));
		}
	}
	
	public void setDefaultCalendar(){
		
		//FYLL CALENDARMAIN MED UKEDAGER
		calendarMain.add(new JLabel(""));
		for (int i = 0; i < weekDays.length; i++){
			calendarMain.add(new JLabel(weekDays[i]));
		}
		
		calendarMain.add(new JLabel("Tid"));
		calendarMain.add(new JLabel(""+cal.get(Calendar.DAY_OF_MONTH)));
		calendarMain.add(new JLabel(""+(cal.get(Calendar.DAY_OF_MONTH))+1));
		calendarMain.add(new JLabel(""+(cal.get(Calendar.DAY_OF_MONTH))+1));
		calendarMain.add(new JLabel(""+(cal.get(Calendar.DAY_OF_MONTH))+1));
		calendarMain.add(new JLabel(""+(cal.get(Calendar.DAY_OF_MONTH))+1));
		calendarMain.add(new JLabel(""+(cal.get(Calendar.DAY_OF_MONTH))+1));
		calendarMain.add(new JLabel(""+(cal.get(Calendar.DAY_OF_MONTH))+1));
		
		//FYLL CALENDARMAIN MED TIDSPUNKTER
		for (int i = 0; i < 24; i++){
			for (int y = 0; y < 4; y++){
				if (i < 10){
					if (y == 0){
						time[(i*4)+y] = "0" + i + ":"+(15*y)+"0";
					}else{
						time[(i*4)+y] = "0" + i + ":"+(15*y);
					}
				}else{
					if (y == 0){
						time[(i*4)+y] = "" + i + ":"+(15*y)+"0";
					}else{
						time[(i*4)+y] = "" + i + ":"+(15*y);
					}
				}
			}
		}
		
		//FYLL CALENDARMAIN MED BLANKE ELEMENTER
		for (int y = 0; y < 96; y++){
			calendarMain.add(new JLabel(time[y]));
			for (int x = 1; x < 8; x++){
				calendarFill[y][x] = new FillApointment(Color.WHITE);
				calendarMain.add(calendarFill[y][x]); 
			}
		}
	}
	
	public void setApointment(int day, int startTime, int endTime, Event event){
		
		calendarFill[startTime][day-1] = new FillApointment(Color.RED, event);
		calendarFill[startTime][day-1].addMouseListener(new PanelClick());
		for (int x = startTime+1; x < endTime; x++){
				calendarFill[x][day-1] = new FillApointment(Color.RED);
				calendarFill[x][day-1].addMouseListener(new PanelClick());
		}
	}
	
	
	public void clearCalendarFill(){		
		for(int i = 0; i < calendarFill.length; i++){
			for (int j = 0; j <calendarFill[i].length; j++){
				calendarFill[i][j] = new FillApointment(Color.WHITE);
			}
		}
		
	}
	public void fillCalendar(){
		
		calendarMain.removeAll();
		calendarMain.add(new JLabel(""));
		for (int i = 0; i < weekDays.length; i++){
			calendarMain.add(new JLabel(weekDays[i]));
		}
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		int thisWeek = c.get(Calendar.WEEK_OF_YEAR);
		
		c.add(Calendar.WEEK_OF_YEAR, showWeek-thisWeek);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		
		calendarMain.add(new JLabel("Tid"));
		calendarMain.add(new JLabel(simpleDateFormat(c)));
		c.add(Calendar.DATE, 1);
		calendarMain.add(new JLabel(simpleDateFormat(c)));
		c.add(Calendar.DATE, 1);
		calendarMain.add(new JLabel(simpleDateFormat(c)));
		c.add(Calendar.DATE, 1);
		calendarMain.add(new JLabel(simpleDateFormat(c)));
		c.add(Calendar.DATE, 1);
		calendarMain.add(new JLabel(simpleDateFormat(c)));
		c.add(Calendar.DATE, 1);
		calendarMain.add(new JLabel(simpleDateFormat(c)));
		c.add(Calendar.DATE, 1);
		calendarMain.add(new JLabel(simpleDateFormat(c)));
		c.add(Calendar.DATE, 1);
		
		for (int y = 0; y < 96; y++){
			calendarMain.add(new JLabel(time[y]));
			for (int x = 1; x < 8; x++){
				calendarMain.add(calendarFill[y][x]); 
			}
		}
	}
	
	private static String simpleDateFormat(Calendar now) {
        // Get today's date
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM");
        return formatter.format(now.getTime());
    }
	
	private List<User> getUsers() {
		users.clear();
		for(int i = 0; i < listModel.size(); i++) {
			users.add((User) listModel.get(i));
		}
		
		return users;
	}
	
	private void getEvents(){
		
		events.clear();
		//Henter nye avtaler data.
		
		
		Calendar c1 = Calendar.getInstance();
		c1.setFirstDayOfWeek(Calendar.MONDAY);
		int thisWeek = c1.get(Calendar.WEEK_OF_YEAR);
		
		c1.add(Calendar.WEEK_OF_YEAR, showWeek-thisWeek);
		
		Calendar c2 = Calendar.getInstance();
		c2.setFirstDayOfWeek(Calendar.MONDAY);
		int thisWeek2 = c2.get(Calendar.WEEK_OF_YEAR);
		
		c2.add(Calendar.WEEK_OF_YEAR, showWeek-thisWeek2);
		
		c1.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		c2.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

		conn.send(new APICall.GetEventsInPeriod(c1, c2, getUsers()));
		events = conn.receiveObject();
		
		for (Event e : events) {
			Calendar startC = e.getStart();
			startC.setFirstDayOfWeek(Calendar.MONDAY);
			Calendar endC = e.getEnd();
			endC.setFirstDayOfWeek(Calendar.MONDAY);
			int dag = startC.get(Calendar.DAY_OF_WEEK);
			int startTime = (startC.get(Calendar.HOUR_OF_DAY)*4);
			startTime += (startC.get(Calendar.MINUTE)/15);
			int endTime = (endC.get(Calendar.HOUR_OF_DAY)*4);
			endTime += (endC.get(Calendar.MINUTE)/15);
			setApointment(dag, startTime, endTime, e);
		}
	}
	
	public void reDraw() {
		clearCalendarFill();
		getEvents();
		fillCalendar();
	}
	
	class ChangeWeek implements ActionListener{
		int backForward;
		
		ChangeWeek(int backForward){
			this.backForward = backForward;
			//setDefaultCalendar();
			
		}
		
		public void actionPerformed(ActionEvent e){
			if(backForward == 0){
				showWeek -= 1;
				reDraw();
				jlWeek.setText("   "+(showWeek));
			}else{
				showWeek += 1;
				reDraw();
				jlWeek.setText("   "+(showWeek));
			}
		}
	}
	
	public HomeScreen getHomeScreen() {
		return this;
	}
	
	class ButtonListener implements ActionListener{
		String what;
		
		ButtonListener(String what){
			this.what = what;
		}
		
		public void actionPerformed(ActionEvent e){
			EventFrame ef = new EventFrame(what,getModel());
			ef.showFrame();
		}
	}
	
	class messageAction implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			JList mlist = (JList) e.getSource();

			Message message = (Message) mlist.getSelectedValue();
			MessageType type = message.getType();
			
			ClientConnection clientConnection = ClientConnection.getInstance();
			clientConnection.send(new APICall.GetEvent(message.getEventID()));
			Event event = clientConnection.receiveObject();
			EventFrame ef;
			switch(type) {
				case ALL_ACCEPTED:
					ef = new EventFrame("EditSharedApointment", event);
					ef.showFrame();
					break;
				case CHANGE:
					ef = new EventFrame("ConsiderApointment", event);
					ef.showFrame();
					break;
				case INVITE:
					ef = new EventFrame("ConsiderApointment", event);
					ef.showFrame();
					break;
				case USER_DECLINED:
					ef = new EventFrame("EditSharedApointment", event);
					ef.showFrame();
					break;
			}
		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		
	}
	
	private void showWindow(MouseEvent arg0) {
		Event e = ((FillApointment) arg0.getSource()).getEvent();
		ClientConnection clientConnection = ClientConnection.getInstance();
		User u = clientConnection.getUser();
		EventFrame ef;

		if(u.equals(e.getLeader())) {
			ef = new EventFrame("EditSharedApointment", e);
			ef.showFrame();
		} else if(e.getUserStatuses().containsKey(u)) {
			ef = new EventFrame("ConsiderApointment", e);
			ef.showFrame();
		}
	}
	
	class PanelClick implements MouseListener{
		public void mouseReleased(MouseEvent arg0) {}
		
		public void mousePressed(MouseEvent arg0) {
			showWindow(arg0);
		}
		public void mouseExited(MouseEvent arg0) {}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseClicked(MouseEvent arg0) {}
	}
	
	public class FillApointment extends JPanel {
		private Event event;
		
		public FillApointment(Color c){
			JPanel jp = new JPanel();
			jp.setBorder(BorderFactory.createEmptyBorder());
			jp.setBackground(c);
			jp.setPreferredSize(new Dimension(135, 200));
			jp.setForeground(c);
			this.add(jp);
		}
		
		public FillApointment(Color c, Event event){
			this.event = event;
			JPanel jp = new JPanel();
			jp.setBorder(BorderFactory.createEmptyBorder());
			jp.setBackground(c);
			jp.setPreferredSize(new Dimension(135, 200));
			jp.setForeground(c);
			jp.add(new JLabel(event.getName()));
			this.add(jp);
		}
		
		public Event getEvent() {
			return event;
		}
	}
	
	class logoutAction implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			ClientConnection clientConnection = ClientConnection.getInstance();
			clientConnection.send(new APICall.Logout());
			System.exit(0);
		}
	}
}
