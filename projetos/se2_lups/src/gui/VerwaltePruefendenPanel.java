/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VerwaltePruefendenPanel extends javax.swing.JPanel {

    private Vector<Vector> tableVector=new Vector();
    private String select;
    Panel p;

    /**
     * VerwaltePruefendenPanel(Panel) Erstellt Panel "Verwalte Pruefenden"
     * @param p
     */
    public VerwaltePruefendenPanel(Panel p) {
        this.p=p;
    	initComponents();
    	}

    /**
     * initComponents() Initialisiert die Komponenten.
     */
    private void initComponents() {

        verwNotenGroup = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pruefTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        auswahlTxt = new javax.swing.JLabel();
        zuPruefAnmRadio = new javax.swing.JRadioButton();
        vonPruefAbmRadio = new javax.swing.JRadioButton();
        notenVerwRadio = new javax.swing.JRadioButton();
        durchBtn = new javax.swing.JButton();
        auswahlSubTxt = new javax.swing.JLabel();
        notePanel = new javax.swing.JPanel();
        studentCombo = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        noteTxt = new javax.swing.JTextField();

        verwNotenGroup.add(zuPruefAnmRadio);
        verwNotenGroup.add(vonPruefAbmRadio);
        verwNotenGroup.add(notenVerwRadio);

        setPreferredSize(new java.awt.Dimension(700, 500));

        jLabel1.setText("Prüfungsverwaltung:");

        pruefTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "VolesungsNr", "Titel", "Prüfender"
            }
        ));
        pruefTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pruefTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(pruefTable);

        jLabel2.setText("Verfügbare Vorlesungen:");

        auswahlTxt.setText("Gewählte Vorlesung:");

        zuPruefAnmRadio.setText("Zur Prüfung anmelden");

        vonPruefAbmRadio.setText("Von Prüfung abmelden");

        notenVerwRadio.setText("Note Speichern");
        notenVerwRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notenVerwRadioActionPerformed(evt);
            }
        });

        durchBtn.setText("Durchführen");
        durchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                durchBtnActionPerformed(evt);
            }
        });

        studentCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { ""}));
        studentCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	studentComboActionPerformed(evt);
            }
        });
        jLabel3.setText("Student:");

        jLabel4.setText("Note:");

        javax.swing.GroupLayout notePanelLayout = new javax.swing.GroupLayout(notePanel);
        notePanel.setLayout(notePanelLayout);
        notePanelLayout.setHorizontalGroup(
            notePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(notePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(notePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(studentCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(noteTxt))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        notePanelLayout.setVerticalGroup(
            notePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(notePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(studentCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(notePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(noteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(durchBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(notenVerwRadio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(zuPruefAnmRadio)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(auswahlTxt)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(auswahlSubTxt))
                                    .addComponent(vonPruefAbmRadio))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(notePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(auswahlTxt)
                            .addComponent(auswahlSubTxt))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zuPruefAnmRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vonPruefAbmRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(notenVerwRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(durchBtn))
                    .addComponent(notePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(99, Short.MAX_VALUE))
        );
    }

    /**
     * durchBtnActionPerformed(java.awt.event.ActionEvent)
     * @param evt
     */
    private void durchBtnActionPerformed(java.awt.event.ActionEvent evt) {
    	if (zuPruefAnmRadio.isSelected()==true){
    		p.frame.getController().prueferAnmelden(select);
    		zuPruefAnmRadio.setSelected(false);
    	
    	}
    	else if	(vonPruefAbmRadio.isSelected()==true){
    		p.frame.getController().prueferAbmelden(select);
    		vonPruefAbmRadio.setSelected(false);
    	}
    	else if (notenVerwRadio.isSelected()==true){
    		p.frame.getController().setBewertung((String)studentCombo.getSelectedItem(), select, noteTxt.getText());
    }
    	p.setSubPanel(13);
    }
    
    /**
     * studentComboActionPerformed(ActionEvent)
     * @param evt
     */
    private void studentComboActionPerformed(java.awt.event.ActionEvent evt) {
    	String student=(String)studentCombo.getSelectedItem();
    	noteTxt.setText(p.frame.getController().getBewertung(student, select));
    }
    
    /**
     * pruefTableMouseClicked(MouseEvent)
     * @param evt
     */
    private void pruefTableMouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 1) {
            JTable tmpTable=(JTable)evt.getSource();
            this.select=(String)tableVector.get(tmpTable.getSelectedRow()).get(0);
            auswahlSubTxt.setText(select);
            p.select=this.select;  
            studentCombo.setModel(new DefaultComboBoxModel(p.frame.getController().listeZugelasseneTeilnehmer(select)));
        }
    }
    
    /**
     * setData(Vector)
     * @param dataTable
     */
    public void setData(Vector dataTable){
    	this.tableVector=dataTable;
    	Vector v=new Vector();
    	v.add("VorlesungsNr");
    	v.add("Titel");
    	v.add("Prüfender");
    	pruefTable.setModel(new DefaultTableModel(dataTable,v){
	        public boolean isCellEditable(int rowIndex, int columnIndex) {
	            return false;
	        }
	    });
    	
    }
    
    /**
     * notenVerwRadioActionPerformed(ActionEvent)
     * @param evt
     */
    private void notenVerwRadioActionPerformed(java.awt.event.ActionEvent evt) {
        if (notenVerwRadio.isSelected()==true){
            notePanel.setVisible(true);
        }
        else notePanel.setVisible(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel auswahlSubTxt;
    private javax.swing.JLabel auswahlTxt;
    private javax.swing.JButton durchBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel notePanel;
    private javax.swing.JTextField noteTxt;
    private javax.swing.JRadioButton notenVerwRadio;
    private javax.swing.JTable pruefTable;
    private javax.swing.JComboBox studentCombo;
    private javax.swing.ButtonGroup verwNotenGroup;
    private javax.swing.JRadioButton vonPruefAbmRadio;
    private javax.swing.JRadioButton zuPruefAnmRadio;
    
}
