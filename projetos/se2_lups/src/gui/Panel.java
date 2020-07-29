/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

import lups.Controller;

public class Panel extends javax.swing.JPanel {
    MainFrame frame;
    GroupLayout layout;
    String select;

    /**
     * Panel(MainFrame) Konstruktor der Klasse Panel.
     * @param frame
     */
    public Panel(MainFrame frame) {
        this.frame=frame;
        initComponents();
    }

    /**
     * initComponents() Initialisiert die Komponenten vom Main Panel.
     */
    private void initComponents() {

        menuePanel = new javax.swing.JPanel();
        menueLabel = new javax.swing.JLabel();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        subMainPanel = new javax.swing.JPanel();
        mainlabel = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(700, 500));

        menuePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        menuePanel.setPreferredSize(new java.awt.Dimension(181, 500));

        menueLabel.setText("jLabel1");

        btn1.setText("Btn1");
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        btn2.setText("jButton2");
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });

        btn3.setText("jButton3");
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });

        btn4.setText("jButton4");
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });

        btn5.setText("jButton5");
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });

        btn6.setText("jButton6");
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuePanelLayout = new javax.swing.GroupLayout(menuePanel);
        menuePanel.setLayout(menuePanelLayout);
        menuePanelLayout.setHorizontalGroup(
            menuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(menuePanelLayout.createSequentialGroup()
                        .addComponent(menueLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btn1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(btn3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        menuePanelLayout.setVerticalGroup(
            menuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menueLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn6)
                .addContainerGap(164, Short.MAX_VALUE))
        );

        mainlabel.setText("jLabel1");

        javax.swing.GroupLayout subMainPanelLayout = new javax.swing.GroupLayout(subMainPanel);
        subMainPanel.setLayout(subMainPanelLayout);
        subMainPanelLayout.setHorizontalGroup(
            subMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subMainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainlabel)
                .addContainerGap(344, Short.MAX_VALUE))
        );
        subMainPanelLayout.setVerticalGroup(
            subMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subMainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainlabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
            .addComponent(subMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn6ActionPerformed
    
    /**
     * setSubPanel(int) Ruft ein neues Panel auf.
     * @param id - Panel
     */
    public void setSubPanel(int id){

        switch(id){
        case 0:ctrlVorlDetailPanel(id);
        		break;
        case 3:ctrlStudentZulassenAbmelden(id,false);
        	  	break;
        case 4:ctrlStudentZulassenAbmelden(id,true);
        		break;
        case 5:ctrlErstelleVorlPanel();
        		break;
        case 6:ctrlBenutzerdatenPanel();
        		break;
        case 7:ctrlNutzerVerwaltenPanel();
        		break;
        case 8:ctrlZeigeLogPanel();
        		break;
        case 9:ctrlDruckeBenutzerDokumentePanel();
        		break;
        case 10:ctrlStudBelegteVeranstaltPanel();
        		break;
        case 11:ctrlStudLeistungenPanel();
        		break;
        case 12:ctrlVeranstaltungslistePanel();
        		break;
        case 13:ctrlVerwaltePruefendenPanel();
        		break;
        case 14:ctrlVerwalteVorlesungenPanel();
        		break;
        default:ctrlLogout();  
        }
    }
    
    /**
     * ctrlVorlDetailPanel(int)
     * @param id 
     */
    private void ctrlVorlDetailPanel(int id){
    	if (frame.getController().isStudent()){
  			id++;
  			if (frame.getController().isAngemeldet(select)){
  				id++;
  			}
  		  }
      	VorlesungsDetailPanel tmpPanel=new VorlesungsDetailPanel(this,id,select);
      	tmpPanel.setPanelData(frame.getController().getVeranstaltung(select));

      	layout.replace(subMainPanel,subMainPanel=tmpPanel);
    }
    
    /**
     * ctrlStudentZulassenAbmelden(int,boolean) Öffnet das Panel "Student Zulassen/Abmelden"
     * @param id - Id der Lehrveranstaltung
     * @param bool - true= zugelassen, false= abmelden.
     */
    private void ctrlStudentZulassenAbmelden(int id,Boolean bool){
    	StudentZulassenAbmelden tmpPanel=new StudentZulassenAbmelden(this,id,select);
  	  	tmpPanel.setData(frame.getController().getTeilnehmerliste(select,bool));
  	  	layout.replace(subMainPanel,subMainPanel=tmpPanel);
    }
    
    /**
     * ctrlErstelleVorlPanel() Öffnet das Panel "Erstelle Vorlesung"
     */
    private void ctrlErstelleVorlPanel(){
    	ErstelleVorlesungsPanel tmpPanel=new ErstelleVorlesungsPanel(this);
        layout.replace(subMainPanel,subMainPanel=tmpPanel);
    }
    
    /**
     * ctrlBenutzerdatenPanel() Öffnet das Panel "Benutzerdaten"
     */
    private void ctrlBenutzerdatenPanel(){
    	BenutzerdatenPanel tmpPanel=new BenutzerdatenPanel(this);
        tmpPanel.setData(frame.getController().getPerson());
        layout.replace(subMainPanel,subMainPanel=tmpPanel);
    }
    
    /**
     * ctrlNutzerVerwaltenPanel() Öffnet das Panel "Nutzer Verwalten"
     */
    private void ctrlNutzerVerwaltenPanel(){
    	AdminNutzerVerwaltenPanel tmpPanel=new AdminNutzerVerwaltenPanel(this);
        tmpPanel.setData(frame.getController().getMailList());
        layout.replace(subMainPanel,subMainPanel=tmpPanel); 
    }
    
    /**
     * ctrlZeigeLogPanel() Öffnet das Panel "Zeige Log"
     */
    private void ctrlZeigeLogPanel(){
    	ZeigeLogPanel tmpPanel=new ZeigeLogPanel(this);
        tmpPanel.fillText(frame.getController().logAuslesen());
        layout.replace(subMainPanel,subMainPanel=tmpPanel);
    }
    
    /**
     * ctrlDruckeBenutzerDokumentePanel() Öffnet das Panel "Drucke Benutzer Dokumente"
     */
    private void ctrlDruckeBenutzerDokumentePanel(){
    	DruckeDokumentePanel tmpPanel=new DruckeDokumentePanel(this);
        layout.replace(subMainPanel,subMainPanel=tmpPanel);
    }
    
    /**
     * ctrlStudBelegteVeranstaltPanel() Öffnet das Panel "Studenten Belegte Veranstaltung"
     */
    private void ctrlStudBelegteVeranstaltPanel(){
    	StudBelegteVeranstaltungenPanel tmpPanel=new StudBelegteVeranstaltungenPanel(this);
        tmpPanel.setData(frame.getController().kurslisteGUI());
    	layout.replace(subMainPanel,subMainPanel=tmpPanel);   
    }
    
    /**
     * ctrlStudLeistungenPanel() Öffnet das Panel "Student Leistungen"
     */
    private void ctrlStudLeistungenPanel(){
    	StudLeistungenPanel tmpPanel=new StudLeistungenPanel(this);
    	tmpPanel.setData(frame.getController().leistungsUebersichtGUI());
        layout.replace(subMainPanel,subMainPanel=tmpPanel);
    }
    
    /**
     * ctrlVeranstaltungslistePanel() Öffnet das Panel "Veranstaltungsliste"
     */
    private void ctrlVeranstaltungslistePanel(){
    	VeranstaltungslistePanel tmpPanel=new VeranstaltungslistePanel(this);
    	tmpPanel.setData(frame.getController().getVorlesungsverzeichnis());
        layout.replace(subMainPanel,subMainPanel=tmpPanel); 
    }
    
    /**
     * ctrlVerwaltePruefendenPanel() Öffnet das Panel "Verwalte Pruefenden"
     */
    private void ctrlVerwaltePruefendenPanel(){
    	VerwaltePruefendenPanel tmpPanel=new VerwaltePruefendenPanel(this);
    	tmpPanel.setData(frame.getController().pruefenderListe());
        layout.replace(subMainPanel,subMainPanel=tmpPanel); 
    }
    
    /**
     * ctrlVerwalteVorlesungenPanel() Öffnet das Panel "Verwalte Vorlesungen"
     */
    private void ctrlVerwalteVorlesungenPanel(){
    	VerwalteVorlesungPanel tmpPanel=new VerwalteVorlesungPanel(this);
    	tmpPanel.setData(frame.getController().getLehrenderliste());
        layout.replace(subMainPanel,subMainPanel=tmpPanel); 
    }

    /**
     * ctrlLogout()
     */
    private void ctrlLogout(){
    	frame.getController().logOut();
        frame.setGUI(7);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btn1;
    javax.swing.JButton btn2;
    javax.swing.JButton btn3;
    javax.swing.JButton btn4;
    javax.swing.JButton btn5;
    javax.swing.JButton btn6;
    javax.swing.JLabel mainlabel;
    javax.swing.JLabel menueLabel;
    javax.swing.JPanel menuePanel;
    javax.swing.JPanel subMainPanel;

}
