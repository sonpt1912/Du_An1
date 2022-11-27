/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.raven.form;

import com.mycompany.customModel.BanResponse;
import com.mycompany.domainModel.Ban;
import com.mycompany.domainModel.ChiTietBanHoaDon;
import com.mycompany.domainModel.HoaDon;
import com.mycompany.repository.impl.BanRepository;
import com.mycompany.service.IBanResponseService;
import com.mycompany.service.IChiTietBanHoaDonService;
import com.mycompany.service.ICommonResponseService;
import com.mycompany.service.ICommonService;
import com.mycompany.service.impl.BanResponseService;
import com.mycompany.service.impl.BanService;
import com.mycompany.service.impl.ChiTietBanHoaDonService;
import com.mycompany.service.impl.HoaDonService;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class JDialogChuyenBan extends javax.swing.JDialog {

    private List<BanResponse> lstBan = new ArrayList<>();
    private HoaDonService hoaDonService = new HoaDonService();
    private IBanResponseService banResponseService = new BanResponseService();
    private List<BanResponse> lstMaBan = new ArrayList<>();// list để lấy mã bàn
    private DefaultTableModel dtmBan = new DefaultTableModel();
    private IChiTietBanHoaDonService chiTietBanHoaDonService = new ChiTietBanHoaDonService();
    private List<BanResponse> lstBanResponses = new ArrayList<>();
    List<ChiTietBanHoaDon> lstChiTietBanHoaDons = new ArrayList<>();
    private ICommonService banService = new BanService();
    private HoaDon hoaDon;

    public JDialogChuyenBan(java.awt.Frame parent, boolean modal, HoaDon hd) {
        super(parent, modal);
        initComponents();
        hoaDon = hd;
        String headerBan[] = {"STT", "Mã Bàn", "Số lượng chỗ ngồi", "Khu vực", "Trạng thái"};
        tbBanMoi.setModel(dtmBan);
        dtmBan.setColumnIdentifiers(headerBan);
        lstBanResponses = banResponseService.getByTrangThai(0);
        showDataBan(lstBanResponses);
        fillBanCu();
    }

    private void showDataBan(List<BanResponse> banResponses) {
        dtmBan.setRowCount(0);
        int stt = 0;
        for (BanResponse banResponse : banResponses) {
            stt++;
            dtmBan.addRow(banResponse.toDataRow(stt));
        }
    }

    private void fillBanCu() {
        lstMaBan.clear();
        lstChiTietBanHoaDons = chiTietBanHoaDonService.getByHoaDon(hoaDon);
        String maBan = lstChiTietBanHoaDons.get(0).getBan().getMaBan().toString();
        for (int i = 0; i < lstChiTietBanHoaDons.size(); i++) {
            if (lstChiTietBanHoaDons.size() > 1) {
                if (i == 0) {
                    continue;
                }
                maBan += ", " + lstChiTietBanHoaDons.get(i).getBan().getMaBan().toString();
//                lbSoBan.setText(maBan + ", " + lstChiTietBanHoaDon.getBan().getMaBan());
                lbBanCu.setText(maBan);
            } else {
                lbBanCu.setText(lstChiTietBanHoaDons.get(i).getBan().getMaBan().toString());
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbBanMoi = new javax.swing.JTable();
        lbBanCu = new javax.swing.JLabel();
        lbBanMoi = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        btnXacNhan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Bàn cũ:");

        jLabel2.setText("Bàn mới:");

        tbBanMoi.setModel(new javax.swing.table.DefaultTableModel(
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
        tbBanMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBanMoiMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbBanMoi);

        lbBanCu.setText("Mã bàn cũ");

        lbBanMoi.setText("                                                   ");

        btnExit.setText("Quay lại");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
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
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnXacNhan)
                        .addGap(66, 66, 66)
                        .addComponent(btnExit))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbBanMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbBanCu, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(64, 64, 64))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbBanCu))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lbBanMoi))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExit)
                    .addComponent(btnXacNhan))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tbBanMoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBanMoiMouseClicked
        // TODO add your handling code here:
        // lấy ra bàn đang chọn và fill mã bàn lên label
        int index = tbBanMoi.getSelectedRow();
        String maBan = lbBanMoi.getText();
        BanResponse banResponse = lstBanResponses.get(index);
        for (BanResponse banResponse1 : lstMaBan) {
            if (banResponse1.getMaBan() == banResponse.getMaBan()) {
                JOptionPane.showMessageDialog(this, "Đã có bàn rồi");
                return;
            }
        }
        // add bàn click vào lstMaBan
        lstMaBan.add(banResponse);
        for (BanResponse banResponse1 : lstMaBan) {
            if (lstMaBan.size() > 1) {
                lbBanMoi.setText(maBan + ", " + banResponse1.getMaBan());
            } else {
                lbBanMoi.setText(banResponse1.getMaBan().toString());
            }
        }
    }//GEN-LAST:event_tbBanMoiMouseClicked

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        // TODO add your handling code here:
        String xoaChiTIetBan = (String) chiTietBanHoaDonService.remove(hoaDon);
        for (BanResponse banResponse : lstMaBan) {
            Ban ban = (Ban) banService.getOne(banResponse.getMaBan().toString());
            ChiTietBanHoaDon chiTietBanHoaDon = new ChiTietBanHoaDon(null, hoaDon, ban);
            String addChiTietBanHoaDon = (String) chiTietBanHoaDonService.add(chiTietBanHoaDon);
            List<HoaDon> listHDChuaTT = hoaDonService.getHDChoByMaBan(ban.getMaBan());
            if (listHDChuaTT.size() <= 0) {
                // set trạng thái = 0 cho bàn đã getone về
                ban.setTrangThai(0);
                String setTrangThaiBan = (String) banService.update(ban, ban.getMaBan().toString());
            } else {
                ban.setTrangThai(1);
                String setTrangThaiBan = (String) banService.update(ban, ban.getMaBan().toString());
            }
        }
        for (ChiTietBanHoaDon lstChiTietBanHoaDon : lstChiTietBanHoaDons) {
            Ban ban = (Ban) banService.getOne(lstChiTietBanHoaDon.getBan().getMaBan().toString());
            List<ChiTietBanHoaDon> chiTietBanHoaDons = chiTietBanHoaDonService.getByBan(ban);
            if (chiTietBanHoaDons.size() <= 0) {
                ban.setTrangThai(0);
                String setTrangThaiBan = (String) banService.update(ban, ban.getMaBan().toString());
                JOptionPane.showMessageDialog(this, "OK");
            } else {
                ban.setTrangThai(1);
                String setTrangThaiBan = (String) banService.update(ban, ban.getMaBan().toString());
                JOptionPane.showMessageDialog(this, "KO");
            }
        }
        JOptionPane.showMessageDialog(this, "Chuyển thành công");
        this.dispose();
    }//GEN-LAST:event_btnXacNhanActionPerformed

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
//            java.util.logging.Logger.getLogger(JDialogChuyenBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(JDialogChuyenBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(JDialogChuyenBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(JDialogChuyenBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                JDialogChuyenBan dialog = new JDialogChuyenBan(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lbBanCu;
    private javax.swing.JLabel lbBanMoi;
    private javax.swing.JTable tbBanMoi;
    // End of variables declaration//GEN-END:variables
}
