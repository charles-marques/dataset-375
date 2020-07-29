package gui;

import java.util.HashMap;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;

public class AdminNutzerVerwaltenPanel extends javax.swing.JPanel {
	Panel p;
    
	/**
	 * AdminNutzerVerwaltenPanel(Panel) Erstellt das Panel "Nutzer Verwalten" (nur Admin!)
	 * @param p - Übergebenes Panel
	 */
    public AdminNutzerVerwaltenPanel(Panel p) {
        this.p=p;
    	initComponents();
    }

    /**
     * initComponents() Initialisiert die Komponenten vom Panel "Nutzer Verwalten".
     */
    private void initComponents() {

        adminBtnGroup = new javax.swing.ButtonGroup();
        genUserPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        nameTxt = new javax.swing.JTextField();
        matrTxt = new javax.swing.JTextField();
        rechteCombo = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        emailTxt = new javax.swing.JTextField();
        genUserRadio = new javax.swing.JRadioButton();
        delUserRadio = new javax.swing.JRadioButton();
        changePWRadio = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        userEmailCombo = new javax.swing.JComboBox();
        passwordPanel = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        newPasswordTxtSec = new javax.swing.JTextField();
        newPasswordTxt = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        saveBtn = new javax.swing.JButton();

        adminBtnGroup.add(genUserRadio);
        adminBtnGroup.add(delUserRadio);
        adminBtnGroup.add(changePWRadio);

        genUserPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        genUserPanel.setVisible(false);

        jLabel9.setText("Name:");

        jLabel12.setText("Matr.Nr:");

        jLabel13.setText("Rechte:");

        rechteCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Admin", "Prüfender", "Lehrender", "Student" }));

        jLabel2.setText("Email:");

        javax.swing.GroupLayout genUserPanelLayout = new javax.swing.GroupLayout(genUserPanel);
        genUserPanel.setLayout(genUserPanelLayout);
        genUserPanelLayout.setHorizontalGroup(
            genUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(genUserPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(genUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(genUserPanelLayout.createSequentialGroup()
                        .addGroup(genUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(genUserPanelLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(30, 30, 30))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, genUserPanelLayout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)))
                        .addGap(1, 1, 1)
                        .addGroup(genUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(emailTxt)
                            .addGroup(genUserPanelLayout.createSequentialGroup()
                                .addComponent(rechteCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(genUserPanelLayout.createSequentialGroup()
                        .addGroup(genUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(genUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(matrTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
        );
        genUserPanelLayout.setVerticalGroup(
            genUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(genUserPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(genUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(nameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(genUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(matrTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(genUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(emailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(genUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rechteCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        genUserRadio.setText("Benutzer erstellen");
        genUserRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genUserRadioActionPerformed(evt);
            }
        });

        delUserRadio.setText("Benutzer löschen");
        delUserRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delUserRadioActionPerformed(evt);
            }
        });

        changePWRadio.setText("Benutzerpasswort ändern");
        changePWRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePWRadioActionPerformed(evt);
            }
        });

        jLabel1.setText("Nutzer(Email):");
        jLabel1.setVisible(false);

        userEmailCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        userEmailCombo.setVisible(false);

        passwordPanel.setVisible(false);

        jLabel15.setText("Neues Passwort:");

        jLabel16.setText("Passwort wiederholen:");

        javax.swing.GroupLayout passwordPanelLayout = new javax.swing.GroupLayout(passwordPanel);
        passwordPanel.setLayout(passwordPanelLayout);
        passwordPanelLayout.setHorizontalGroup(
            passwordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(passwordPanelLayout.createSequentialGroup()
                .addGroup(passwordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(passwordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newPasswordTxtSec, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                    .addComponent(newPasswordTxt))
                .addContainerGap())
        );
        passwordPanelLayout.setVerticalGroup(
            passwordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(passwordPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(passwordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newPasswordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(passwordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newPasswordTxtSec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        saveBtn.setText("Speichern");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(genUserRadio)
                        .addGap(18, 18, 18)
                        .addComponent(delUserRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(changePWRadio))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(genUserPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(userEmailCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(genUserRadio)
                    .addComponent(delUserRadio)
                    .addComponent(changePWRadio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(genUserPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userEmailCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveBtn)
                .addContainerGap(171, Short.MAX_VALUE))
        );
    }

    /**
     * genUserRadioActionPerformed(java.awt.evetn.ActionEvent)
     * @param evt - default Parameter
     */
    private void genUserRadioActionPerformed(java.awt.event.ActionEvent evt) {
        if (genUserRadio.isSelected()==true){
            genUserPanel.setVisible(true);
        }
        else genUserPanel.setVisible(false);
    }

    /**
     * delUserRadioActionPerformed(java.awt.evetn.ActionEvent)
     * @param evt - default Parameter
     */
    private void delUserRadioActionPerformed(java.awt.event.ActionEvent evt) {
        if (delUserRadio.isSelected()==true){
            jLabel1.setVisible(true);
            userEmailCombo.setVisible(true);
        }
        else{
            jLabel1.setVisible(false);
            userEmailCombo.setVisible(false);
        }
    }

    /**
     * changePWRadioActionPerformed(java.awt.evetn.ActionEvent)
     * @param evt - default Parameter
     */
    private void changePWRadioActionPerformed(java.awt.event.ActionEvent evt) {
        if (changePWRadio.isSelected()==true){
            passwordPanel.setVisible(true);
        }
        else passwordPanel.setVisible(false);
    }

    /**
     * saveBtnActionPerformed(java.awt.evetn.ActionEvent)
     * @param evt - default Parameter
     */
    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {
    	if (genUserRadio.isSelected()==true){
        	HashMap data=new HashMap();
        	data.put("name", nameTxt.getText());
        	data.put("matrnr", matrTxt.getText());
        	data.put("email", emailTxt.getText());
        	Boolean tue=p.frame.getController().createUser(data,rechteCombo.getSelectedIndex());
        	if (!tue){emailTxt.setText("Existiert bereits");
        	}
        }
    	else if (delUserRadio.isSelected()==true){
    		String select=(String)userEmailCombo.getSelectedItem();
    		p.frame.getController().deleteUser(select);
    	}
    	else if (changePWRadio.isSelected()==true){
    		if (newPasswordTxtSec.getText().equals(newPasswordTxt.getText())){
    			String select=userEmailCombo.getSelectedItem().toString();
    			p.frame.getController().resetPasswd(select,newPasswordTxt.getText());
    		}
    		else newPasswordTxtSec.setText("Passwort unterschiedlich");
    	}
    	p.setSubPanel(7);
    }
    
    /**
     * setData(Vector) Setzt Daten.
     * @param data
     */
    public void setData(Vector data){
    	userEmailCombo.setModel(new DefaultComboBoxModel(data));
    }
    
    private javax.swing.ButtonGroup adminBtnGroup;
    private javax.swing.JRadioButton changePWRadio;
    private javax.swing.JRadioButton delUserRadio;
    private javax.swing.JTextField emailTxt;
    private javax.swing.JPanel genUserPanel;
    private javax.swing.JRadioButton genUserRadio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField matrTxt;
    private javax.swing.JTextField nameTxt;
    private javax.swing.JTextField newPasswordTxt;
    private javax.swing.JTextField newPasswordTxtSec;
    private javax.swing.JPanel passwordPanel;
    private javax.swing.JComboBox rechteCombo;
    private javax.swing.JButton saveBtn;
    private javax.swing.JComboBox userEmailCombo;
    
}
