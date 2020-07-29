
package gui;

import lups.Controller;

public class AdminMainPanel extends Panel{
    
    /**
     * AdminMainPanel(MainFrame) Erstellt das Main Panel.
     * @param frame
     */
    public AdminMainPanel(MainFrame frame){
        super(frame);
        initComponents();
    }
    
    /**
     * initComponents() Initialisiert die Komponenten vom Main Panel.
     */
    private void initComponents(){
        this.menueLabel.setText("Adminmenü:");
        this.btn1.setText("Nutzer Verwalten");
        this.btn2.setText("Logs betrachten");
        this.btn3.setText("Benutzerdaten");
        this.btn4.setText("Logout");
        this.btn5.setVisible(false);
        this.btn6.setVisible(false);
        this.mainlabel.setText("<html>Willkommen!<br>Viel Spaß beim schalten, walten,<br>Kaffee trinken und Domuts essen.</html>");
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
     * btn1ActionPerformed(java.awt.event.ActionEvent) Öffnet das Panel "Nutzer Verwalten"
     * @param evt - default Parameter
     */
    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {
    	this.setSubPanel(7);
    }

    /**
     * btn2ActionPerformed(java.awt.event.ActionEvent) Öffnet das Panel "Zeige Log"
     * @param evt - default Parameter
     */
    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {
    	this.setSubPanel(8);
    }

    /**
     * btn3ActionPerformed(java.awt.event.ActionEvent) Öffnet das Panel "Benutzerdaten"
     * @param evt - default Parameter
     */
    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {
    	this.setSubPanel(6);
    }

    /**
     * btn4ActionPerformed(java.awt.event.ActionEvent) Öffnet das Panel "default"
     * @param evt - default Parameter
     */
    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {
        this.setSubPanel(20);
    }
}
