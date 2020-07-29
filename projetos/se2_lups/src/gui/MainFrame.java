/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.JPanel;

import lups.Controller;

public class MainFrame extends javax.swing.JFrame {
	private javax.swing.GroupLayout layout;
	private Controller moon;
	private JPanel panel;

	/**
	 * MainFrame(Controller) Erstellt den MainFrame.
	 * @param moon
	 */
    public MainFrame(Controller moon) {
    	this.moon=moon;
    	initComponents();
    }
    
    /**
     * initComponents() Initialisiert die Komponenten vom Main Panel.
     */
    private void initComponents() {
    	panel=new LoginPanel(this);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel)
        );

        pack();
    }
    
    /**
     * getController() Gibt eine Objekt vom Typ Controller zurück.
     * @return moon - Typ Controller
     */
    public Controller getController(){
    	return moon;
    }
    
    /**
     * setGUI(int) Erstellt die GUI nach dem übergebenen Parameter.
     * @param id - Nutzerrechte
     */
    public void setGUI(int id){
        switch(id){
        case 0:layout.replace(panel,panel=new AdminMainPanel(this));break;
        case 1:layout.replace(panel,panel=new PruefenderMainPanel(this));break;
        case 2:layout.replace(panel,panel=new LehrenderMainPanel(this));break;
        case 3:layout.replace(panel,panel=new StudentMainPanel(this));break;
        default:layout.replace(panel,panel=new LoginPanel(this));
        }            
    }
    
}
