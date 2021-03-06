/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FinestraAfegirProductesATicket.java
 *
 * Created on 09/11/2009, 02:27:24
 */

package tpv;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 *
 * @author joan
 */
public class FinestraAfegirProductesATicket extends javax.swing.JFrame {

    private Register tpv;
    /** Creates new form FinestraAfegirProductesATicket */
    public FinestraAfegirProductesATicket() {
        initComponents();
    }
    public FinestraAfegirProductesATicket(Register unTpv) {
                initComponents();

        tpv=unTpv;
        LinkedHashMap productes = tpv.getStore().getItems();

        Collection col = productes.values();
        Iterator cIt=col.iterator();

        while(cIt.hasNext())
        {
            Item unItem = (Item)cIt.next();
            String p = unItem.getDescription().getItemID()+"\t"+unItem.getDescription().getDescription()+"\t"+unItem.getQuantity()+"\n";
            areaProductes.append(p);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LabelAccio = new javax.swing.JLabel();
        textIDProducte = new javax.swing.JTextField();
        textQuantitat = new javax.swing.JTextField();
        LIdProducte = new javax.swing.JLabel();
        LQuantitat = new javax.swing.JLabel();
        BtnIntroduir = new javax.swing.JButton();
        BtnFinalitzar = new javax.swing.JButton();
        LIntroduir = new javax.swing.JLabel();
        LFinalitzar = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaProductes = new javax.swing.JTextArea();
        LProducts = new javax.swing.JLabel();
        LId = new javax.swing.JLabel();
        LDesc = new javax.swing.JLabel();
        LAmount = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Introduir productes");

        LabelAccio.setText("Introdueix productes que vols comprar:");

        LIdProducte.setText("idProducte:");

        LQuantitat.setText("Quantitat:");

        BtnIntroduir.setText("Introduir");
        BtnIntroduir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnIntroduirActionPerformed(evt);
            }
        });

        BtnFinalitzar.setText("Finalitzar");
        BtnFinalitzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnFinalitzarActionPerformed(evt);
            }
        });

        LIntroduir.setText("Utilitza 'Introduir' per agregar un altre producte.");

        LFinalitzar.setText("Utilitza 'Finalitzar' per passar a fer el pagament.");

        areaProductes.setColumns(1);
        areaProductes.setEditable(false);
        areaProductes.setRows(20);
        areaProductes.setTabSize(4);
        jScrollPane1.setViewportView(areaProductes);

        LProducts.setText("Productes Disponibles:");

        LId.setText("id");

        LDesc.setText("Descripció");

        LAmount.setText("Quantitat");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(LIdProducte)
                            .addComponent(LQuantitat))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textQuantitat, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textIDProducte, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LIntroduir)
                            .addComponent(LabelAccio)
                            .addComponent(LFinalitzar)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(BtnIntroduir, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnFinalitzar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LId)
                        .addGap(18, 18, 18)
                        .addComponent(LDesc)
                        .addGap(18, 18, 18)
                        .addComponent(LAmount))
                    .addComponent(LProducts, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(LabelAccio)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LIdProducte)
                            .addComponent(textIDProducte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textQuantitat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LQuantitat))
                        .addGap(18, 18, 18)
                        .addComponent(LIntroduir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LFinalitzar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtnFinalitzar)
                            .addComponent(BtnIntroduir)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(LProducts)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LId)
                            .addComponent(LDesc)
                            .addComponent(LAmount))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnIntroduirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnIntroduirActionPerformed
      
        int id=Integer.parseInt(textIDProducte.getText());
        int q=Integer.parseInt(textQuantitat.getText());
        if((tpv.getStore().getItem(id)==null))
        {
            ErrID eri = new ErrID(this,true);
            eri.show();
        }
        else if(q>(tpv.getStore().getItem(id).getQuantity()))
        {
            ErrQuantitat er=new ErrQuantitat(this,true);
            er.show();
        }else
        {
            tpv.introduirProducte(tpv.getStore().getItem(id), q);
            this.textQuantitat.setText("");
            this.textIDProducte.setText("");
        }

    }//GEN-LAST:event_BtnIntroduirActionPerformed

    private void BtnFinalitzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnFinalitzarActionPerformed
        
        tpv.finalitzaVenda();
        FinestraPagament f4=new FinestraPagament(tpv);
        f4.show();
        dispose();
        setVisible(false);
    }//GEN-LAST:event_BtnFinalitzarActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FinestraAfegirProductesATicket().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnFinalitzar;
    private javax.swing.JButton BtnIntroduir;
    private javax.swing.JLabel LAmount;
    private javax.swing.JLabel LDesc;
    private javax.swing.JLabel LFinalitzar;
    private javax.swing.JLabel LId;
    private javax.swing.JLabel LIdProducte;
    private javax.swing.JLabel LIntroduir;
    private javax.swing.JLabel LProducts;
    private javax.swing.JLabel LQuantitat;
    private javax.swing.JLabel LabelAccio;
    private javax.swing.JTextArea areaProductes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField textIDProducte;
    private javax.swing.JTextField textQuantitat;
    // End of variables declaration//GEN-END:variables

}
