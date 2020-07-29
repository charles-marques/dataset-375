
package gui;

import lups.Controller;

public class LehrenderMainPanel extends Panel {

	/**
	 * LehrenderMainPanel(MainFrame) Erstellt das Panel "Lehrender".
	 * @param frame
	 */
    public LehrenderMainPanel(MainFrame frame) {
        super(frame);
        initComponents();
    }

    /**
     * initComponents() Initialisiert die Komponenten vom Panel "Nutzer Verwalten".
     */
    private void initComponents() {
        menueLabel.setText("Lehrendermenü:");
        btn1.setText("Verwalte Vorlesung");
        btn2.setText("Benutzerdaten");
        btn3.setText("Vorlesungsübersicht");
        btn4.setText("Logout");
        btn5.setVisible(false);
        btn6.setVisible(false);
        mainlabel.setText("Willkommen Lehrender!");
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
    }                       

    /**
     * btn1ActionPerformed(java.awt.event.ActionEvent) Öffnet das Panel "Verwalte Vorlesung"
     * @param evt
     */
    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {                                                 
    	this.setSubPanel(14); 
    }                                                

    /**
     * btn2ActionPerformed(java.awt.event.ActionEvent) Öffnet das Panel "Benutzerdaten"
     * @param evt
     */
    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {                                          
    	this.setSubPanel(6);
    }
    
    /**
     * btn3ActionPerformed(java.awt.event.ActionEvent) Öffnet das Panel "Veranstaltungsliste"
     * @param evt
     */
    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {                                          
    	this.setSubPanel(12); 
    }
    
    /**
     * btn4ActionPerformed(java.awt.event.ActionEvent) Öffnet das Panel "default"
     * @param evt
     */
    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {                                          
        this.setSubPanel(20);
    }                   
}
