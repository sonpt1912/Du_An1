/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.raven.form;

import com.mycompany.customModel.HoaDonChiTietResponse;
import com.mycompany.domainModel.HoaDon;
import com.mycompany.service.ICommonService;
import com.mycompany.service.IHoaDonChiTietResponseService;
import com.mycompany.service.IHoaDonService;
import com.mycompany.service.impl.HoaDonChiTietResponseService;
import com.mycompany.service.impl.HoaDonService;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class JDialogGopHoaDon extends javax.swing.JDialog {

    private DefaultTableModel dtmHDCTCu = new DefaultTableModel();
    private DefaultTableModel dtmHDCTMoi = new DefaultTableModel();
    private DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
    private List<HoaDonChiTietResponse> lstdonChiTietResponsesCu;
    private List<HoaDonChiTietResponse> lstdonChiTietResponsesMoi;
    private IHoaDonChiTietResponseService hdctResponseService = new HoaDonChiTietResponseService();
    private List<HoaDon> lstHoaDons;
    private IHoaDonService hds2 = new HoaDonService();
    private ICommonService hds1 = new HoaDonService();
    //thực thể
    private HoaDon hoaDon;

    public JDialogGopHoaDon(java.awt.Frame parent, boolean modal, HoaDon hd) {
        super(parent, modal);
        initComponents();
        hoaDon = hd;
        String headerHoaDonCT[] = {"STT", "Tên món ăn", "Giá món ăn", "Số lượng món ăn", "Tên combo", "Giá combo", "Số lượng combo", "Ghi chú"};
        cbbMaHoaDonMoi.setModel(dcbm);
        tbHDCTCu.setModel(dtmHDCTCu);
        tbHDCTMoi.setModel(dtmHDCTMoi);
        dtmHDCTCu.setColumnIdentifiers(headerHoaDonCT);
        dtmHDCTMoi.setColumnIdentifiers(headerHoaDonCT);
        lbMaHDCu.setText(hd.getMaHoaDon());
        lstdonChiTietResponsesCu = hdctResponseService.getAll(hoaDon);
        showDataHDCTCu(lstdonChiTietResponsesCu);
        loadCBB();
        HoaDon hdMoi = (HoaDon) hds1.getOne(cbbMaHoaDonMoi.getSelectedItem().toString());
        lstdonChiTietResponsesMoi = hdctResponseService.getAll(hdMoi);
        showDataHDCTMoi(lstdonChiTietResponsesMoi);
    }

    private void loadCBB() {
        lstHoaDons = hds2.getHDByTrangThai(0);
        for (HoaDon lstHoaDon : lstHoaDons) {
            dcbm.addElement(lstHoaDon.getMaHoaDon());
        }
    }

    private void showDataHDCTCu(List<HoaDonChiTietResponse> donChiTietResponses) {
        dtmHDCTCu.setRowCount(0);
        int stt = 0;
        for (HoaDonChiTietResponse donChiTietResponse : donChiTietResponses) {
            stt++;
            dtmHDCTCu.addRow(donChiTietResponse.toDataRow(stt));
        }
    }

    private void showDataHDCTMoi(List<HoaDonChiTietResponse> donChiTietResponses) {
        dtmHDCTMoi.setRowCount(0);
        int stt = 0;
        for (HoaDonChiTietResponse donChiTietResponse : donChiTietResponses) {
            stt++;
            dtmHDCTMoi.addRow(donChiTietResponse.toDataRow(stt));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lbMaHDCu = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbHDCTCu = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        cbbMaHoaDonMoi = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHDCTMoi = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Mã hoá đơn cũ:");

        lbMaHDCu.setText("Mã hd");

        tbHDCTCu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tbHDCTCu);

        jLabel3.setText("Mã hoá mới:");

        cbbMaHoaDonMoi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbMaHoaDonMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMaHoaDonMoiActionPerformed(evt);
            }
        });

        tbHDCTMoi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbHDCTMoi);

        jButton1.setText("Exit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(lbMaHDCu, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(650, 650, 650)))
                .addGap(48, 48, 48))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 776, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(22, 22, 22)
                        .addComponent(cbbMaHoaDonMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 783, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbMaHDCu))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbMaHoaDonMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbbMaHoaDonMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMaHoaDonMoiActionPerformed
        // TODO add your handling code here:
        HoaDon hd = (HoaDon) hds1.getOne(cbbMaHoaDonMoi.getSelectedItem().toString());
        lstdonChiTietResponsesMoi = hdctResponseService.getAll(hd);
        showDataHDCTMoi(lstdonChiTietResponsesMoi);
    }//GEN-LAST:event_cbbMaHoaDonMoiActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(JDialogGopHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(JDialogGopHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(JDialogGopHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(JDialogGopHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                JDialogGopHoaDon dialog = new JDialogGopHoaDon(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbbMaHoaDonMoi;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbMaHDCu;
    private javax.swing.JTable tbHDCTCu;
    private javax.swing.JTable tbHDCTMoi;
    // End of variables declaration//GEN-END:variables
}
