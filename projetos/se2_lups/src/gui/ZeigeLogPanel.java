/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;



import java.util.List;

import javax.swing.JLabel;
	

public class ZeigeLogPanel extends javax.swing.JPanel {
    Panel p;
    
    /**
     * ZeigeLogPanel(Panel) Erstellt Panel "Zeige Logs"
     * @param p
     */
    public ZeigeLogPanel(Panel p) {
        this.p=p;
    	initComponents();
    }
    
    /**
     * fillText(List<String>) 
     * @param text
     */
    public void fillText(List<String> text){
        logTxtArea.setText("");
        for (int i=0;i<text.size();i++)
        	logTxtArea.append(text.get(i)+"\n");
    }

    /**
     * initComponents() Initialisiert die Komponenten.
     */
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        logTxtArea = new javax.swing.JTextArea();
        logCombo = new javax.swing.JComboBox();

        jLabel1.setText("Logs:");

        logTxtArea.setColumns(20);
        logTxtArea.setRows(5);
        jScrollPane1.setViewportView(logTxtArea);

        logCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        logCombo.setVisible(false);
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(logCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 121, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(logCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                .addContainerGap())
        );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox logCombo;
    private javax.swing.JTextArea logTxtArea;
    
}
