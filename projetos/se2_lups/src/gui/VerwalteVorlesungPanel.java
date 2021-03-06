/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VerwalteVorlesungPanel extends javax.swing.JPanel {
    String select="";
    Vector<Vector> gesamt=new Vector();
    Panel p;

    /**
     * VerwalteVorlesungPanel(Panel) Erstellt Panel "Verwalte Vorlesung"
     * @param p
     */
    public VerwalteVorlesungPanel(Panel p) {
    	this.p=p;
    	initComponents();
    }

    /**
     * initComponents() Initialisiert die Komponenten.
     */
    private void initComponents() {
        vorlVerwGroup = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        vorlTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        vorlErstellRadio = new javax.swing.JRadioButton();
        studZuRadio = new javax.swing.JRadioButton();
        studAbRadio = new javax.swing.JRadioButton();
        vorlDelRadio = new javax.swing.JRadioButton();
        vorlVerwBtn = new javax.swing.JButton();
        selectLabel = new javax.swing.JLabel();
        printTeilnRadio = new javax.swing.JRadioButton();

        vorlVerwGroup.add(vorlErstellRadio);
        vorlVerwGroup.add(studZuRadio);
        vorlVerwGroup.add(studAbRadio);
        vorlVerwGroup.add(vorlDelRadio);
        vorlVerwGroup.add(printTeilnRadio);
        setPreferredSize(new java.awt.Dimension(700, 500));

        jLabel1.setText("Vorlesungverwaltung:");

        vorlTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "VorlesungsNr", "Titel"
            }
        ));
        vorlTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vorlTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(vorlTable);
        vorlTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        vorlTable.getColumnModel().getColumn(0).setMaxWidth(1000);

        jLabel2.setText("Ihre Vorlesungen:");

        jLabel3.setText("gewählte Vorlesung:");

        vorlErstellRadio.setSelected(true);
        vorlErstellRadio.setText("Vorlesung erstellen");

        studZuRadio.setText("Angemeldete Studenten zulassen");

        studAbRadio.setText("Zugelassene Studenten auslöschen");

        vorlDelRadio.setText("Vorlesung entfernen");

        vorlVerwBtn.setText("Durchführen");
        vorlVerwBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vorlVerwBtnActionPerformed(evt);
            }
        });

        printTeilnRadio.setText("Teilnehmerliste drucken");
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(vorlErstellRadio)
                            .addComponent(studZuRadio)
                            .addComponent(studAbRadio)
                            .addComponent(vorlDelRadio)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(selectLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(printTeilnRadio)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(vorlVerwBtn)
                                    .addComponent(jLabel2))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(selectLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vorlErstellRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(studZuRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(studAbRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vorlDelRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(printTeilnRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vorlVerwBtn)
                .addContainerGap(94, Short.MAX_VALUE))
        );
    }

    /**
     * vorlVerwBtnActionPerformed(ActionEvent)
     * @param evt
     */
    private void vorlVerwBtnActionPerformed(java.awt.event.ActionEvent evt) {
    	if (select!=""){
    		if (studAbRadio.isSelected()){
    			p.setSubPanel(4);  
    		}
    		else if (studZuRadio.isSelected()){
    			p.setSubPanel(3);
    		}
    		else if (vorlErstellRadio.isSelected()){
    			p.setSubPanel(5);
    		}
    		else if (vorlDelRadio.isSelected()==true)p.frame.getController().deleteLehrveranstaltung(select);
    		else if (printTeilnRadio.isSelected()==true)p.frame.getController().printTeilnehmerliste(select);
    
    	}
    }
    
    /**
     * vorlTableMouseClicke(MouseEvent)
     * @param evt
     */
    private void vorlTableMouseClicked(java.awt.event.MouseEvent evt) {
    	JTable tmpTable=(JTable)evt.getSource();
        select=(String)gesamt.get(tmpTable.getSelectedRow()).get(0);
        p.select=this.select;
        selectLabel.setText(select);  
        if (evt.getClickCount()==2){
            p.setSubPanel(0);
        }
    }
    
    /**
     * setData(Vector)
     * @param data
     */
    public void setData(Vector data){
    	this.gesamt=data;
    	Vector v=new Vector();
    	v.add("VorlesungsNr");
    	v.add("Titel");
    	vorlTable.setModel(new DefaultTableModel(data,v){
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                   return false;
                }
    	    });
    }
    
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JRadioButton printTeilnRadio;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel selectLabel;
    private javax.swing.JRadioButton studAbRadio;
    private javax.swing.JRadioButton studZuRadio;
    private javax.swing.JRadioButton vorlDelRadio;
    private javax.swing.JRadioButton vorlErstellRadio;
    private javax.swing.JTable vorlTable;
    private javax.swing.JButton vorlVerwBtn;
    private javax.swing.ButtonGroup vorlVerwGroup;
    
}
