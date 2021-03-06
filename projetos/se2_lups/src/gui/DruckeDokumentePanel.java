/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author nvettera
 */
public class DruckeDokumentePanel extends javax.swing.JPanel {
	Panel p;
    
	/**
	 * DruckeDokumentePanel(Panel) Erstellt Panel "Drucken"
	 * @param p
	 */
    public DruckeDokumentePanel(Panel p) {
        this.p=p;
    	initComponents();
    }

    /**
     * initComponents() Initialisiert die Komponenten vom Panel "Nutzer Verwalten".
     */
    private void initComponents() {

        printBtnGroup = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        printLeistungsuebersichtRadio = new javax.swing.JRadioButton();
        printStudienbescheinigungRadio = new javax.swing.JRadioButton();
        printStundenplanRadio = new javax.swing.JRadioButton();
        printStudienverlaufsbescheinigungRadio = new javax.swing.JRadioButton();
        printBtn = new javax.swing.JButton();

        jLabel1.setText("Druckmenü:");

        printLeistungsuebersichtRadio.setText("Leistungsübersicht drucken");

        printStudienbescheinigungRadio.setText("Studienbescheinigung drucken");

        printStundenplanRadio.setText("Stundenplan drucken");

        printStudienverlaufsbescheinigungRadio.setText("Studienverlaufsbescheinigung drucken");

        printBtn.setText("Drucken");
        printBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printBtnActionPerformed(evt);
            }
        });
        printBtnGroup.add(printLeistungsuebersichtRadio);
        printBtnGroup.add(printStudienbescheinigungRadio);
        printBtnGroup.add(printStundenplanRadio);
        printBtnGroup.add(printStudienverlaufsbescheinigungRadio);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(printLeistungsuebersichtRadio)
                    .addComponent(printStudienbescheinigungRadio)
                    .addComponent(printStundenplanRadio)
                    .addComponent(printStudienverlaufsbescheinigungRadio)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(printBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(288, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(printLeistungsuebersichtRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(printStudienbescheinigungRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(printStundenplanRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(printStudienverlaufsbescheinigungRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(printBtn)
                .addContainerGap(265, Short.MAX_VALUE))
        );
    }


    /**
     * printBtnActionPerformed(java.awt.event.ActionEvent)
     * @param evt
     */
    private void printBtnActionPerformed(java.awt.event.ActionEvent evt) {
    	if (printLeistungsuebersichtRadio.isSelected()==true)p.frame.getController().print(1);
    	else if (printStudienbescheinigungRadio.isSelected()==true)p.frame.getController().print(2);
    	else if (printStundenplanRadio.isSelected()==true)p.frame.getController().print(3);
    	else if (printStudienverlaufsbescheinigungRadio.isSelected()==true)p.frame.getController().print(4);
    	
    }

    // Variables declaration
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton printBtn;
    private javax.swing.ButtonGroup printBtnGroup;
    private javax.swing.JRadioButton printLeistungsuebersichtRadio;
    private javax.swing.JRadioButton printStudienbescheinigungRadio;
    private javax.swing.JRadioButton printStudienverlaufsbescheinigungRadio;
    private javax.swing.JRadioButton printStundenplanRadio;
}
