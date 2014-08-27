/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mackittipat.adfly.skipper.ui;

import com.mackittipat.adfly.skipper.core.AdFlySkipper;

/**
 *
 * @author mac
 */
public class MainWindow extends javax.swing.JFrame {
    
    private AdFlySkipper adFlySkipper;

    /**
     * Creates new form frmMain
     */
    public MainWindow() {
        initComponents();
        adFlySkipper = new AdFlySkipper();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblAdFlyUrl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtTargetUrl = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAdFlyUrl = new javax.swing.JTextPane();
        btnSkip = new javax.swing.JButton();
        pgbSkip = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AdFly Skipper");

        lblAdFlyUrl.setText("AdFly URL");
        lblAdFlyUrl.setName(""); // NOI18N

        txtTargetUrl.setName(""); // NOI18N
        jScrollPane1.setViewportView(txtTargetUrl);

        txtAdFlyUrl.setName(""); // NOI18N
        jScrollPane2.setViewportView(txtAdFlyUrl);

        btnSkip.setText("Skip URL");
        btnSkip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSkipActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 550, Short.MAX_VALUE)
                        .addComponent(btnSkip))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAdFlyUrl)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pgbSkip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAdFlyUrl)
                .addGap(18, 18, 18)
                .addComponent(pgbSkip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSkip)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSkipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSkipActionPerformed
        // Clear text and progress bar.
        txtTargetUrl.setText("");
        pgbSkip.setValue(0);
        // Disable button for temporary.
        btnSkip.setEnabled(false);
        String[] adFlyUrls = txtAdFlyUrl.getText().split("\n");
        // Total number of adfly link.
        int numAdFlyUrl = adFlyUrls.length;
        Thread threadSkipAdFlyUrl = new Thread() {
            @Override
            public void run() {
                int countProgressBarPercent = 0;
                for(int i=0; i<numAdFlyUrl; i++) {
                    // Skip adfly url.
                    String targetUrl = adFlySkipper.skipAdFlyUrl(adFlyUrls[i]);
                    // Display target url.
                    txtTargetUrl.setText(txtTargetUrl.getText() + targetUrl + "\n");
                    // Update progress bar.
                    if(i < numAdFlyUrl-1) {
                        countProgressBarPercent += 100 / numAdFlyUrl;
                    } else {
                        countProgressBarPercent = 100;
                        // After skip all adfly url, enable button again.
                        btnSkip.setEnabled(true);
                    }
                    pgbSkip.setValue(countProgressBarPercent);
                }
            }
        };
        threadSkipAdFlyUrl.start();
    }//GEN-LAST:event_btnSkipActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSkip;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAdFlyUrl;
    private javax.swing.JProgressBar pgbSkip;
    private javax.swing.JTextPane txtAdFlyUrl;
    private javax.swing.JTextPane txtTargetUrl;
    // End of variables declaration//GEN-END:variables
}
