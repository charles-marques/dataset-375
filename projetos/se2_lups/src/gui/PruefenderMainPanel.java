package gui;

import lups.Controller;

public class PruefenderMainPanel extends Panel {  
    
	/**
	 * PruefenderMainPanel(MainFrame) Erstellt das Panel "Pruefender"
	 * @param frame
	 */
    public PruefenderMainPanel(MainFrame frame) {
	super(frame);
        initComponents();
    }
    
    /**
     * initComponents() Initialisiert die Komponenten.
     */
    private void initComponents() {
        menueLabel.setText("Prüfermenü:");
        btn1.setText("<html>Verwalte<br> Vorlesung</html>");
        btn2.setText("Verwalte Noten");
        btn3.setText("Benutzerdaten");
        btn4.setText("Vorlesungsübersicht");
        btn5.setText("Logout");
        btn6.setVisible(false);
        mainlabel.setText("Willkommen Prüfender!");
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
    }                        

    /**
     * btn1ActionPerformed(java.awt.event.ActionEvent) Öffnet das Panel "Verwalte Vorlesung"
     * @param evt - default Parameter
     */
    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {                                     
    	this.setSubPanel(14);
    }                                                

    /**
     * btn2ActionPerformed(java.awt.event.ActionEvent) Öffnet das Panel "Verwalte Pruefenden"
     * @param evt - default Parameter
     */
    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {                                             
    	this.setSubPanel(13); 
    }                                            

    /**
     * btn3ActionPerformed(java.awt.event.ActionEvent) Öffnet das Panel "Benutzerdaten"
     * @param evt - default Parameter
     */
    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {                                          
    	this.setSubPanel(6); 
    }  
    
    /**
     * btn4ActionPerformed(java.awt.event.ActionEvent) Öffnet das Panel "Veranstaltungsliste"
     * @param evt - default Parameter
     */
    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {                                          
    	this.setSubPanel(12); 
    }
    
    /**
     * btn5ActionPerformed(java.awt.event.ActionEvent) Öffnet das Panel "default"
     * @param evt - default Parameter
     */
    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {                                          
        this.setSubPanel(20);
    }                                                            
}
