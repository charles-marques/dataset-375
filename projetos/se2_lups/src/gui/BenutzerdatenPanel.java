/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.HashMap;

public class BenutzerdatenPanel extends javax.swing.JPanel {
	Panel p;
	
    /**
     * BenutzerdatenPanel(Panel) Erstellt Panel "Benutzerdaten"
     * @param p
     */
    public BenutzerdatenPanel(Panel p) {
        this.p=p;
    	initComponents();
    }

    /**
     * initComponents() Initialisiert die Komponenten vom Panel "Nutzer Verwalten".
     */
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        benDatNameTxt = new javax.swing.JTextField();
        benDatStraßeTxt = new javax.swing.JTextField();
        benDatPLZTxt = new javax.swing.JTextField();
        benDatMatrTxt = new javax.swing.JTextField();
        saveDatBtn = new javax.swing.JButton();
        changeDatCheckBox = new javax.swing.JCheckBox();
        changePassCheckBox = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        benEmailTxt = new javax.swing.JTextField();
        benRechteLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        benDatOrtTxt = new javax.swing.JTextField();
        passwordPanel = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        newPasswordTxtSec = new javax.swing.JTextField();
        newPasswordTxt = new javax.swing.JTextField();
        oldPasswordTxt = new javax.swing.JTextField();
        
        jLabel9.setText("Name:");

        jLabel10.setText("Straße:");

        jLabel11.setText("PLZ:");

        jLabel12.setText("Matr.Nr:");

        jLabel13.setText("Rechte:");

        benDatNameTxt.setEditable(false);

        benDatStraßeTxt.setEditable(false);

        benDatPLZTxt.setEditable(false);

        benDatMatrTxt.setEditable(false);
        benEmailTxt.setEditable(false);
        benDatOrtTxt.setEditable(false);
        
        saveDatBtn.setText("Speichern");
        saveDatBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveDatBtnActionPerformed(evt);
            }
        });

        changeDatCheckBox.setText("Benutzerdaten ändern");
        changeDatCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeDatCheckBoxActionPerformed(evt);
            }
        });

        changePassCheckBox.setText("Passwort ändern");
        changePassCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePassCheckBoxActionPerformed(evt);
            }
        });

        jLabel1.setText("Email:");

        jLabel2.setText("Ort:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel9)
                            .addComponent(jLabel12)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(benEmailTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                            .addComponent(benDatOrtTxt)
                            .addComponent(benDatPLZTxt)
                            .addComponent(benDatStraßeTxt)
                            .addComponent(benDatNameTxt)
                            .addComponent(benDatMatrTxt)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(benRechteLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(changePassCheckBox)
                                    .addComponent(changeDatCheckBox))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saveDatBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 59, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(benDatNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(benDatStraßeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(benDatPLZTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(benDatOrtTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(benDatMatrTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(benEmailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(benRechteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(changeDatCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(changePassCheckBox))
                    .addComponent(saveDatBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        passwordPanel.setVisible(false);

        jLabel14.setText("Altes Passwort:");

        jLabel15.setText("Neues Passwort:");

        jLabel16.setText("Passwort wiederholen:");

        javax.swing.GroupLayout passwordPanelLayout = new javax.swing.GroupLayout(passwordPanel);
        passwordPanel.setLayout(passwordPanelLayout);
        passwordPanelLayout.setHorizontalGroup(
            passwordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(passwordPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(passwordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(passwordPanelLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(58, 58, 58)
                        .addComponent(oldPasswordTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
                    .addGroup(passwordPanelLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(52, 52, 52)
                        .addComponent(newPasswordTxt))
                    .addGroup(passwordPanelLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newPasswordTxtSec, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)))
                .addContainerGap())
        );
        passwordPanelLayout.setVerticalGroup(
            passwordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(passwordPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(passwordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(oldPasswordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(passwordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newPasswordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(passwordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newPasswordTxtSec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(passwordPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    /**
     * changeDatCheckBoxActionPerformed(java.awt.event.ActionEvent)
     * @param evt
     */
    private void changeDatCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {
        if (changeDatCheckBox.isSelected()){
        	benDatOrtTxt.setEditable(true);
            benDatStraßeTxt.setEditable(true);
            benDatMatrTxt.setEditable(true);
            benDatNameTxt.setEditable(true);
            benDatPLZTxt.setEditable(true);
           
        }
        else{
        	benDatOrtTxt.setEditable(false);
            benDatStraßeTxt.setEditable(false);
            benDatMatrTxt.setEditable(false);
            benDatNameTxt.setEditable(false);
            benDatPLZTxt.setEditable(false);
            
        } 
                
    }
    
    /**
     * saveDatBtnActionPerformed(java.awt.event.ActionEvent)
     * @param evt
     */
    private void saveDatBtnActionPerformed(java.awt.event.ActionEvent evt) {
        if (changeDatCheckBox.isSelected()){
        	HashMap<String,String> data=new HashMap();
        	data.put("ort",benDatOrtTxt.getText());
        	data.put("straße",benDatStraßeTxt.getText());
        	data.put("matrnr",benDatMatrTxt.getText());
        	data.put("name",benDatNameTxt.getText());
        	data.put("plz",benDatPLZTxt.getText());
        	p.frame.getController().changeUserData(data);
        }
        else if (changePassCheckBox.isSelected()==true){
        	if (newPasswordTxt.getText().equals(newPasswordTxtSec.getText())){
        		Boolean tue =p.frame.getController().setPasswd(oldPasswordTxt.getText(),newPasswordTxt.getText());
        		if (tue==true){
        			oldPasswordTxt.setText("Passwort falsch");
        		}
        	}
        	else newPasswordTxtSec.setText("PW-Unterschiedlich");
        }
    }

    /**
     * changePassCheckBoxActionPerformed(java.awt.event.ActionEvent)
     * @param evt
     */
    private void changePassCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {
        if (changePassCheckBox.isSelected())passwordPanel.setVisible(true);
        else passwordPanel.setVisible(false);
    }
    
    /**
     * setData(java.awt.event.ActionEvent)
     * @param evt
     */
    public void setData(HashMap<String,String> data){
    	benDatOrtTxt.setText(data.get("ort"));
    	benDatStraßeTxt.setText(data.get("straße"));
        benDatMatrTxt.setText(data.get("matrnr"));
        benDatNameTxt.setText(data.get("name"));
        benDatPLZTxt.setText(data.get("plz"));
        benEmailTxt.setText(data.get("email"));
        benRechteLabel.setText(data.get("flag"));
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JTextField benDatMatrTxt;
    javax.swing.JTextField benDatNameTxt;
    javax.swing.JTextField benDatOrtTxt;
    javax.swing.JTextField benDatPLZTxt;
    javax.swing.JTextField benDatStraßeTxt;
    javax.swing.JTextField benEmailTxt;
    javax.swing.JLabel benRechteLabel;
    private javax.swing.JCheckBox changeDatCheckBox;
    private javax.swing.JCheckBox changePassCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField newPasswordTxt;
    private javax.swing.JTextField newPasswordTxtSec;
    private javax.swing.JTextField oldPasswordTxt;
    private javax.swing.JPanel passwordPanel;
    private javax.swing.JButton saveDatBtn;

}
