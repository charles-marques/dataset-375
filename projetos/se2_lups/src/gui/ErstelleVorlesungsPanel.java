/*
 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.HashMap;

public class ErstelleVorlesungsPanel extends javax.swing.JPanel {

	Panel p;
    
	/**
	 * ErstelleVorlesungsPanel(Panel) 
	 * @param p
	 */
    public ErstelleVorlesungsPanel(Panel p) {
        this.p=p;
    	initComponents();
    }

    /**
     * initComponents() Initialisiert die Komponenten vom Panel "Nutzer Verwalten".                          
     */
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        titelTxt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        beschrTxt = new javax.swing.JTextArea();
        saveBtn = new javax.swing.JButton();
        tagCombo = new javax.swing.JComboBox();
        beginH = new javax.swing.JComboBox();
        beginM = new javax.swing.JComboBox();
        endH = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        endM = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(700, 500));

        jLabel1.setText("Erstelle Vorlesung:");

        jLabel2.setText("Titel:");

        jLabel3.setText("Zeit:");

        jLabel4.setText("Beschreibung:");

        beschrTxt.setColumns(20);
        beschrTxt.setRows(5);
        jScrollPane1.setViewportView(beschrTxt);

        saveBtn.setText("Speichern");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        tagCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Montag", "Dienstag", "Mittwoch",
            "Donnerstag", "Freitag", "Samstag","Sonntag" }));

beginH.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08", "09", "10", "11", "12",
    "13", "14", "15","16", "17", "18" }));

    beginM.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "15", "30", "45" }));

    endH.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08", "09", "10", "11", "12",
        "13", "14", "15","16", "17", "18", "19","20" }));

jLabel5.setText("-");

endM.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "15", "30", "45" }));

jLabel6.setText(":");

jLabel7.setText(":");

javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
this.setLayout(layout);
layout.setHorizontalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
    .addContainerGap()
    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane1)
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(titelTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(tagCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(53, 53, 53)
                    .addComponent(beginH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel6)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(beginM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel5)
                    .addGap(18, 18, 18)
                    .addComponent(endH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel7)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(endM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jLabel4))
            .addGap(0, 138, Short.MAX_VALUE))
        .addComponent(saveBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel1)
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2)
                .addComponent(titelTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel3)
                .addComponent(tagCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(beginH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(beginM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(endH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel5)
                .addComponent(endM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel6)
                .addComponent(jLabel7))
            .addGap(18, 18, 18)
            .addComponent(jLabel4)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(saveBtn)
            .addContainerGap(112, Short.MAX_VALUE))
    );
    }                       

    /**
     * saveBtnActionPerformed(java.awt.event.ActionEvent)
     * @param evt
     */
    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {                                        
        
    	String tag=(String)tagCombo.getSelectedItem();
        String beginStd=(String)beginH.getSelectedItem();
        String beginMin=(String)beginM.getSelectedItem();
        String endStd=(String)endH.getSelectedItem();
        String endMin=(String)endM.getSelectedItem();
    	
        HashMap data=new HashMap();
        data.put("beschreibung",beschrTxt.getText());
        data.put("titel",titelTxt.getText());
        data.put("zeit",tag+" "+beginStd+":"+beginMin+" - "+endStd+":"+endMin);
        Boolean tmp=p.frame.getController().createVeranstaltung(data);	
        p.setSubPanel(14);
    }                                       

    // Variables declaration - do not modify                     
    private javax.swing.JComboBox beginH;
    private javax.swing.JComboBox beginM;
    private javax.swing.JTextArea beschrTxt;
    private javax.swing.JComboBox endH;
    private javax.swing.JComboBox endM;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton saveBtn;
    private javax.swing.JComboBox tagCombo;
    private javax.swing.JTextField titelTxt;
}
