package gui;

import lups.Controller;


public class StudentMainPanel extends Panel {

	/**
	 * StudentMainPanel(MainFrame) Erstellt Main Panel "Student"
	 * @param frame
	 */
    public StudentMainPanel(MainFrame frame) {
        super(frame);
    	initComponents();
    }
                           
    /**
     * initComponents() Initialisiert die Komponenten.
     */
    private void initComponents() {
        menueLabel.setText("Studentenmenü");
        btn1.setText("<html>Belegte<br>Veranstaltungen</html>");
        btn2.setText("<html>Meine<br>Leistungen</html>");        
        btn3.setText("<html>Veranstaltungs-<br>übersicht</html>");
        btn4.setText("Benutzerdaten");
        btn6.setText("Logout");
        btn5.setText("Dokumente");
        mainlabel.setText("<html>Willkommen,<br>"+
            "in diesem Menü können Sie ihre Daten einsehen und gegebenenfalls verändern.<br>"
            + "Viel Vergnügen! =)</html>");
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });

           }                       

    /**
     * btn2ActionPerformed(java.awt.event.ActionEvent) Öffnet das Panel "Student Leistungen"
     * @param evt - default Parameter
     */
    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {                                            
    	this.setSubPanel(11);
    }                                           

    /**
     * btn1ActionPerformed(java.awt.event.ActionEvent) Öffnet das Panel "Student Belegte Veranstaltung"
     * @param evt - default Parameter
     */
    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {                                              
    	this.setSubPanel(10);   
    }                                             

    /**
     * btn4ActionPerformed(java.awt.event.ActionEvent) Öffnet das Panel "Benutzerdaten"
     * @param evt - default Parameter
     */
    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {                                               
    	this.setSubPanel(6);
    }                                              

    /**
     * btn3ActionPerformed(java.awt.event.ActionEvent) Öffnet das Panel "Veranstaltungsliste"
     * @param evt - default Parameter
     */
    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {                                               
    	this.setSubPanel(12);
    }                                              

    /**
     * btn5ActionPerformed(java.awt.event.ActionEvent) Öffnet das Panel "Drucke Benutzer Dokumente"
     * @param evt - default Parameter
     */
    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {                                       
    	this.setSubPanel(9);
    }                                      

    /**
     * btn6ActionPerformed(java.awt.event.ActionEvent) Öffnet das Panel "default"
     * @param evt - default Parameter
     */
    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {                                          
    	this.setSubPanel(20);
    }                                                          
}
