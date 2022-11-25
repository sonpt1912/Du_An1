/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.raven.form;

import com.mycompany.customModel.HoaDonChiTietResponse;
import com.mycompany.domainModel.ComBo;
import com.mycompany.domainModel.HoaDon;
import com.mycompany.domainModel.HoaDonChiTiet;
import com.mycompany.domainModel.MonAn;
import com.mycompany.service.ICommonService;
import com.mycompany.service.IHoaDonChiTietResponseService;
import com.mycompany.service.IHoaDonChiTietService;
import com.mycompany.service.IHoaDonService;
import com.mycompany.service.impl.ComBoService;
import com.mycompany.service.impl.HoaDonChiTietResponseService;
import com.mycompany.service.impl.HoaDonChiTietService;
import com.mycompany.service.impl.HoaDonService;
import com.mycompany.service.impl.MonAnService;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class JDialogGopHoaDon extends javax.swing.JDialog {

    private DefaultTableModel dtmHDCTCu = new DefaultTableModel();
    private DefaultTableModel dtmHDCTMoi = new DefaultTableModel();
    private DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
    private List<HoaDonChiTietResponse> lstDonChiTietResponsesCu;
    private List<HoaDonChiTietResponse> lstDonChiTietResponsesMoi;
    private IHoaDonChiTietResponseService hdctResponseService = new HoaDonChiTietResponseService();
    private IHoaDonChiTietService hdctService = new HoaDonChiTietService();
    private ICommonService monAnService = new MonAnService();
    private ICommonService comBoService = new ComBoService();
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
        lstDonChiTietResponsesCu = hdctResponseService.getAll(hoaDon);
        showDataHDCTCu(lstDonChiTietResponsesCu);
        loadCBB();
        HoaDon hdMoi = (HoaDon) hds1.getOne(cbbMaHoaDonMoi.getSelectedItem().toString());
        lstDonChiTietResponsesMoi = hdctResponseService.getAll(hdMoi);
        showDataHDCTMoi(lstDonChiTietResponsesMoi);
    }

    private void loadCBB() {
        lstHoaDons = hds2.getHDByTrangThai(0);
        for (int i = 0; i < lstHoaDons.size(); i++) {
            if (lstHoaDons.get(i).getMaHoaDon().equals(lbMaHDCu.getText())) {
                lstHoaDons.remove(i);
            }
        }
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
        btnXacNhan = new javax.swing.JButton();

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
        tbHDCTCu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHDCTCuMouseClicked(evt);
            }
        });
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

        btnXacNhan.setText("Xác nhận");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(601, Short.MAX_VALUE)
                .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(lbMaHDCu, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbMaHDCu))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbMaHoaDonMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btnXacNhan))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbbMaHoaDonMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMaHoaDonMoiActionPerformed
        // TODO add your handling code here:
        HoaDon hd = (HoaDon) hds1.getOne(cbbMaHoaDonMoi.getSelectedItem().toString());
        lstDonChiTietResponsesMoi = hdctResponseService.getAll(hd);
        showDataHDCTMoi(lstDonChiTietResponsesMoi);
    }//GEN-LAST:event_cbbMaHoaDonMoiActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        // TODO add your handling code here:
        String xoaHDCTCu = (String) hdctService.remove(hoaDon);
        HoaDon hdMoi = (HoaDon) hds1.getOne(cbbMaHoaDonMoi.getSelectedItem().toString());
        String xoaHDCTMoi = (String) hdctService.remove(hdMoi);
        for (HoaDonChiTietResponse hoaDonChiTietResponse : lstDonChiTietResponsesCu) {
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setHoaDon(hoaDon);
            if (hoaDonChiTietResponse.getMaMonAn() != null) {
                MonAn monAn = (MonAn) monAnService.getOne(hoaDonChiTietResponse.getMaMonAn());
                hoaDonChiTiet.setMonAn(monAn);
                hoaDonChiTiet.setDonGiaMonAn(hoaDonChiTietResponse.getDonGiaMonAn());
                hoaDonChiTiet.setSoLuongMonAn(hoaDonChiTietResponse.getSoLuongMonAn());
                hoaDonChiTiet.setDonGiaCombo(BigDecimal.valueOf(0));
                hoaDonChiTiet.setSoLuongCombo(0);
                String themHDCT = (String) hdctService.add(hoaDonChiTiet);
            } else {
                ComBo comBo = (ComBo) comBoService.getOne(hoaDonChiTietResponse.getMaCombo());
                hoaDonChiTiet.setComBo(comBo);
                hoaDonChiTiet.setDonGiaCombo(hoaDonChiTietResponse.getDonGiaCombo());
                hoaDonChiTiet.setSoLuongCombo(hoaDonChiTietResponse.getSoLuongCombo());
                hoaDonChiTiet.setDonGiaMonAn(BigDecimal.valueOf(0));
                hoaDonChiTiet.setSoLuongMonAn(0);
                String themHDCT = (String) hdctService.add(hoaDonChiTiet);
            }
        }
        for (HoaDonChiTietResponse hoaDonChiTietResponse : lstDonChiTietResponsesMoi) {
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setHoaDon(hdMoi);
            if (hoaDonChiTietResponse.getMaMonAn() != null) {
                MonAn monAn = (MonAn) monAnService.getOne(hoaDonChiTietResponse.getMaMonAn());
                hoaDonChiTiet.setMonAn(monAn);
                hoaDonChiTiet.setDonGiaMonAn(hoaDonChiTietResponse.getDonGiaMonAn());
                hoaDonChiTiet.setSoLuongMonAn(hoaDonChiTietResponse.getSoLuongMonAn());
                hoaDonChiTiet.setDonGiaCombo(BigDecimal.valueOf(0));
                hoaDonChiTiet.setSoLuongCombo(0);
                String themHDCT = (String) hdctService.add(hoaDonChiTiet);
            } else {
                ComBo comBo = (ComBo) comBoService.getOne(hoaDonChiTietResponse.getMaCombo());
                hoaDonChiTiet.setComBo(comBo);
                hoaDonChiTiet.setDonGiaCombo(hoaDonChiTietResponse.getDonGiaCombo());
                hoaDonChiTiet.setSoLuongCombo(hoaDonChiTietResponse.getSoLuongCombo());
                hoaDonChiTiet.setDonGiaMonAn(BigDecimal.valueOf(0));
                hoaDonChiTiet.setSoLuongMonAn(0);
                String themHDCT = (String) hdctService.add(hoaDonChiTiet);

            }
        }

        hoaDon.setTrangThai(2);
        String upDateTrangThaiHDCu = (String) hds1.update(hoaDon, hoaDon.getMaHoaDon());
        JOptionPane.showMessageDialog(this, "Gộp thành công");
        this.dispose();
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void tbHDCTCuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHDCTCuMouseClicked
        // TODO add your handling code here:
        int index = tbHDCTCu.getSelectedRow();
        HoaDonChiTietResponse hdctrCu = lstDonChiTietResponsesCu.get(index);
//        // con vớt thằng cần tách vào thằng mới
//        
//        HoaDonChiTietResponse hdctrMoi = hdctrCu;
        // lấy số lượng của thằng cũ
        Integer soLuongMonAnCu = hdctrCu.getSoLuongMonAn();
        Integer soLuongComboCu = hdctrCu.getSoLuongCombo();

        String soLuong = JOptionPane.showInputDialog("Mời nhập số lượng");
        if (soLuong == null) {
            return;
        } else if (soLuong.equals("")) {
            return;
        } else {
            if (hdctrCu.getMaMonAn() == null) {
                HoaDonChiTietResponse hdctrMoi = new HoaDonChiTietResponse();
                //tính số lượng còn lại của thằng cũ
                Integer soLuongConLai = soLuongComboCu - Integer.valueOf(soLuong);
                if (soLuongConLai < 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng còn lại không được âm");
                    return;
                }
                // sét  số lượng cho thằng mới
                hdctrMoi.setDonGiaCombo(hdctrCu.getDonGiaCombo());
                hdctrMoi.setGhiChu(hdctrCu.getGhiChu());
                hdctrMoi.setMaCombo(hdctrCu.getMaCombo());
                hdctrMoi.setSoLuongCombo(Integer.valueOf(soLuong));
                hdctrMoi.setTenCombo(hdctrCu.getTenCombo());
                System.out.println(hdctrMoi.getTenCombo());
                int indexDeUpDate = 0;
                int dem = 0;
                for (int i = 0; i < lstDonChiTietResponsesMoi.size(); i++) {
//                    System.out.println(lstDonChiTietResponsesMoi.get(i).getTenCombo());
                    if (null == lstDonChiTietResponsesMoi.get(i).getTenCombo()) {
                        continue;
                    }
                    if (lstDonChiTietResponsesMoi.get(i).getTenCombo().equals(hdctrCu.getTenCombo())) {
                        indexDeUpDate = i;
                        dem++;
                        lstDonChiTietResponsesMoi.get(indexDeUpDate).setSoLuongCombo(lstDonChiTietResponsesMoi.get(indexDeUpDate).getSoLuongCombo() + Integer.valueOf(soLuong));
                    }
                }
                if (dem == 0) {
                    lstDonChiTietResponsesMoi.add(hdctrMoi);
                }
                showDataHDCTMoi(lstDonChiTietResponsesMoi);
                //sét số lượng còn lại cho thằng cũ
                hdctrCu.setSoLuongCombo(soLuongConLai);
                showDataHDCTCu(lstDonChiTietResponsesCu);
                return;
            } else {
                //tạo hdct mới (tạm thời)
                HoaDonChiTietResponse hdctrMoi = new HoaDonChiTietResponse();
                hdctrMoi.setDonGiaMonAn(hdctrCu.getDonGiaMonAn());
                hdctrMoi.setGhiChu(hdctrCu.getGhiChu());
                hdctrMoi.setMaMonAn(hdctrCu.getMaMonAn());
                hdctrMoi.setTenMonAn(hdctrCu.getTenMonAn());
                Integer soLuongConLai = soLuongMonAnCu - Integer.valueOf(soLuong);
                if (soLuongConLai < 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng còn lại không được âm");
                    return;
                }
                // sét  số lượng cho thằng mới
                hdctrMoi.setSoLuongMonAn(Integer.valueOf(soLuong));
                //add vào list tạm thời
                int indexDeUpDate = 0;
                int dem = 0;
                for (int i = 0; i < lstDonChiTietResponsesMoi.size(); i++) {
                    if (null == lstDonChiTietResponsesMoi.get(i).getTenMonAn()) {
                        continue;
                    }
                    if (lstDonChiTietResponsesMoi.get(i).getTenMonAn().equals(hdctrCu.getTenMonAn())) {
                        indexDeUpDate = i;
                        dem++;
                        lstDonChiTietResponsesMoi.get(indexDeUpDate).setSoLuongMonAn(lstDonChiTietResponsesMoi.get(indexDeUpDate).getSoLuongMonAn() + Integer.valueOf(soLuong));
                    }
                }
                if (dem == 0) {
                    lstDonChiTietResponsesMoi.add(hdctrMoi);
                }
                showDataHDCTMoi(lstDonChiTietResponsesMoi);
                //sét số lượng còn lại cho thằng cũ
                hdctrCu.setSoLuongMonAn(soLuongConLai);
                showDataHDCTCu(lstDonChiTietResponsesCu);
//                if (soLuongConLai == 0) {
//                    dtmHDCu.removeRow(index);
//                }
//                for (int i = 0; i < lstDonChiTietResponsesCu.size(); i++) {
//                    if (lstDonChiTietResponsesCu.get(i).getSoLuongMonAn() == 0 && lstDonChiTietResponsesCu.get(i).getSoLuongCombo() > 0) {
//                        dtmHDCu.removeRow(i);
//                    }
//                }
                for (HoaDonChiTietResponse hoaDonChiTietResponse : lstDonChiTietResponsesMoi) {
                    System.out.println(hoaDonChiTietResponse);
                }
                return;
            }
        }
    }//GEN-LAST:event_tbHDCTCuMouseClicked

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
    private javax.swing.JButton btnXacNhan;
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
