/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

public class StudentZulassenAbmelden extends javax.swing.JPanel {
    int id;
    Panel p;
    JCheckBox check=new JCheckBox();
    String select;

    /**
     * StudentZulassenAbmelden(Panel,int,String) Erstellt Panel "Student Zulassen Abmelden"
     * @param p - Panel
     * @param id - id der Lehrveranstaltung
     * @param select - auswahl...
     */
    public StudentZulassenAbmelden(Panel p,int id,String select) {
        this.p=p;
    	this.id=id;
    	this.select=select;
        initComponents();
    }

    /**
     * initComponents() Initialisiert die Komponenten.
     */
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        saveBtn = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(700, 500));

        jLabel1.setText("Übersicht über angemeldete Studenten:");
        if (id==4){
            jLabel1.setText("Übersicht über zugelassene Studenten:");
        }

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Name", false}
            },
            new String [] {
                "Name", "Zulassen"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        saveBtn.setText("Zulassen");
        if (id==4)saveBtn.setText("Abmelden");
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1)
                    .addComponent(saveBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveBtn)
                .addContainerGap(36, Short.MAX_VALUE))
        );
    }

    /**
     * saveBtnActionPerformed(java.awt.event.ActionEvent) Speichert Button event.
     * @param evt
     */
    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
        p.frame.getController().teilnehmerZulassen(select,model.getDataVector());        
        p.setSubPanel(14);
    }
        
    /**
     * setData(Vector)
     * @param data
     */
    public void setData(Vector data){
    	Vector v=new Vector();
    	v.add("Name");
    	v.add("Zulassen");
    	jTable1.setModel(new DefaultTableModel(data,v){
	        public boolean isCellEditable(int rowIndex, int columnIndex) {
	            if (columnIndex==1)return true;
	            return false;
	        }
	    });
    	jTable1.getColumn("Zulassen").setCellEditor(new DefaultCellEditor(check));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton saveBtn;
  
}
