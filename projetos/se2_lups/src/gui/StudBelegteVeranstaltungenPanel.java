/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
    
public class StudBelegteVeranstaltungenPanel extends javax.swing.JPanel {
    private String select;
    private Vector<Vector> tableVector=new Vector();
    Panel p;
    
    /**
     * StudBelegteVeranstaltungenPanel(Panel) Erstellt Panel "Studenten Belegte Veranstaltung" 
     * @param p
     */
    public StudBelegteVeranstaltungenPanel(Panel p) {
        this.p=p;
    	initComponents();
    }

    /**
     * initComponents() Initialisiert die Komponenten.
     */
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        eigVorlTable = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(700, 500));

        jLabel1.setText("Hier sehen Sie ihre momentan belegten Veranstaltungen:");

        eigVorlTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "VorlesungsNr", "Titel", "Lehrender", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        eigVorlTable.setColumnSelectionAllowed(true);
        eigVorlTable.getTableHeader().setReorderingAllowed(false);
        eigVorlTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eigVorlTableMouseClicked(evt);
            }
        });
        
        jScrollPane1.setViewportView(eigVorlTable);
        eigVorlTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        eigVorlTable.getColumnModel().getColumn(0).setResizable(false);
        eigVorlTable.getColumnModel().getColumn(1).setResizable(false);
        eigVorlTable.getColumnModel().getColumn(2).setResizable(false);
        eigVorlTable.getColumnModel().getColumn(3).setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE))
        );
    }

    /**
     * eigVorlTableMouseClicked(jawa.awt.event.MouseEvent)
     * @param evt
     */
    private void eigVorlTableMouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            JTable tmpTable=(JTable)evt.getSource();
            this.select=(String)tableVector.get(tmpTable.getSelectedRow()).get(0);
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
    	v.add("VorlesungsNr");
    	v.add("Titel");
    	v.add("Lehrender");
    	v.add("Status");
    	eigVorlTable.setModel(new DefaultTableModel(data,v){
	        public boolean isCellEditable(int rowIndex, int columnIndex) {
	            return false;
	        }
	    });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable eigVorlTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;

}
