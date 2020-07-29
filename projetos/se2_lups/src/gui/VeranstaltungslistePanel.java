/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VeranstaltungslistePanel extends javax.swing.JPanel {
    private String select;
    Vector<Vector> tableVector=new Vector();
    Panel p;

    /**
     * VeranstaltungslistePanel(Panel) Erstellt Panel "Veranstaltungsliste"
     * @param p
     */
    public VeranstaltungslistePanel(Panel p) {
        this.p=p;
        initComponents();
    }

    /**
     * initComponents() Initialisiert die Komponenten.
     */
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jLabel1.setText("Veranstaltungsübersicht:");

        /*jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "VeranstaltungsNr", "Titel", "Lehrender"
            }
        ));*/
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 384, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE))
        );
    }

    /**
     * jTable1MouseClicked(java.awt.event.MouseEvent)
     * @param evt
     */
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            JTable tmpTable=(JTable)evt.getSource();
            select=(String)tableVector.get(tmpTable.getSelectedRow()).get(0);
            p.select=this.select;
            p.setSubPanel(0);
        }
    }
    
    /**
     * setData(Vector)
     * @param data
     */
    public void setData(Vector data){
    	this.tableVector=data;
    	Vector v=new Vector();
    	v.add("VeranstaltungNr");
    	v.add("Titel");
    	v.add("Lehrender");
    	jTable1.setModel(new DefaultTableModel(data,v){
	        public boolean isCellEditable(int rowIndex, int columnIndex) {
	            return false;
	        }
	    });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;

}
