/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.raven.form;

import com.mycompany.domainModel.Ban;
import com.mycompany.domainModel.ChiTietBanHoaDon;
import com.mycompany.domainModel.HoaDon;
import com.mycompany.domainModel.NhanVien;
import com.mycompany.service.impl.BanService;
import com.mycompany.service.impl.ChiTietBanHoaDonService;
import com.mycompany.service.impl.HoaDonService;
import com.mycompany.service.impl.NhanVienService;
import com.mycompany.util.HoaDonUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Duongntt
 */
public class JDialogBan_ThemHD extends javax.swing.JDialog {

    private DefaultTableModel dtmBan = new DefaultTableModel();
    private DefaultTableModel dtmBanDangChon = new DefaultTableModel();
    private BanService banService = new BanService();
    private List<Ban> listBan = new ArrayList<>();
    private List<Ban> listBanDangChon = new ArrayList<>();
    private List<HoaDon> listHD = new ArrayList<>();
    private HoaDonService hoaDonService = new HoaDonService();
    private HoaDonUtil hoaDonUtil = new HoaDonUtil();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private java.util.Date today = new java.util.Date();
    private NhanVienService nhanVienService = new NhanVienService();
    private List<ChiTietBanHoaDon> listCTBan_HD = new ArrayList<>();
    private ChiTietBanHoaDonService chiTietBanHoaDonService = new ChiTietBanHoaDonService();
    private NhanVien nhanVien;

    public JDialogBan_ThemHD(java.awt.Frame parent, boolean modal, NhanVien nv) {
        super(parent, modal);
        initComponents();
        this.nhanVien = nv;
        tbBan.setModel(dtmBan);
        tblBanDangChon.setModel(dtmBanDangChon);
        String headersBan[] = {"Mã bàn", "Khu vực", "Số lượng chỗ ngồi", "Trạng thái"};
        dtmBan.setColumnIdentifiers(headersBan);
        dtmBanDangChon.setColumnIdentifiers(headersBan);
        listBan = banService.getFull();
        showDataBan(listBan);
    }

    private void showDataBan(List<Ban> listBan) {
        dtmBan.setRowCount(0);
        for (Ban ban : listBan) {
            dtmBan.addRow(ban.toDataRow());
        }
    }

    private void showDataBanDangChon(List<Ban> listBan) {
        dtmBanDangChon.setRowCount(0);
        for (Ban ban : listBan) {
            dtmBanDangChon.addRow(ban.toDataRow());

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbBan = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBanDangChon = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnXacNhan = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tbBan.setModel(new javax.swing.table.DefaultTableModel(
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
        tbBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbBan);

        tblBanDangChon.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblBanDangChon);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Bàn đang chọn:");

        btnXacNhan.setText("Xác nhận");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(btnXacNhan)
                        .addGap(42, 42, 42)
                        .addComponent(btnRefresh)
                        .addGap(46, 46, 46)
                        .addComponent(btnExit)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXacNhan)
                    .addComponent(btnRefresh)
                    .addComponent(btnExit))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tbBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBanMouseClicked
        int index = tbBan.getSelectedRow();
        Ban ban = listBan.get(index);
        if (ban.getTrangThai() == 1) {
            JOptionPane.showMessageDialog(this, "Bàn đã có khách!");
        } else {
            listBanDangChon.add(ban);
            ban.setTrangThai(1);
            showDataBanDangChon(listBanDangChon);
        }

    }//GEN-LAST:event_tbBanMouseClicked

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        if (listBanDangChon.size() <= 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn bàn!");
        } else {
            //chạy for list bàn đang chọn để tính số ghế:
            int soGhe = 0;
            for (Ban ban : listBanDangChon) {
                soGhe += ban.getSoLuongChoNgoi();
            }
            //nhập số khách:
            String soLuongKH = "";
            try {
                do {
                    soLuongKH = JOptionPane.showInputDialog("Nhập số lượng khách hàng");
                } while (!hoaDonUtil.checkSoLuongKhach(soLuongKH, soGhe));
            } catch (Exception e) {
                soLuongKH = "";
            }
            if (!soLuongKH.equals("")) {
                try {
                    listHD = hoaDonService.getAll();
                    //tạo hoá đơn mới
                    String maHD = hoaDonUtil.zenMaThuyDuong(listHD);
                    java.util.Date ngayTao = (today);
                    HoaDon hoaDon = new HoaDon();
                    hoaDon.setMaHoaDon(maHD);
                    hoaDon.setNgayTao(ngayTao);
                    hoaDon.setTrangThai(0);
                    hoaDon.setNhanVien(nhanVien);
                    hoaDon.setSoLuongKhach(Integer.valueOf(soLuongKH));
                    //thêm hd vào db
                    String themHD = hoaDonService.add(hoaDon);
                    //khai báo ChiTietBan_HoaDon, thêm bàn
                    HoaDon hoaDonMoiThem = hoaDonService.getOne(maHD);
                    for (Ban ban : listBanDangChon) {
                        ChiTietBanHoaDon chiTietBanHoaDon = new ChiTietBanHoaDon();
                        chiTietBanHoaDon.setHd(hoaDonMoiThem);
                        chiTietBanHoaDon.setBan(ban);
                        chiTietBanHoaDonService.add(chiTietBanHoaDon);
                        //set trạng thái cho bàn
                        ban.setTrangThai(1);
                        String upDateBan = banService.update(ban, ban.getMaBan().toString());
                    }
                    //thêm xong clear list bàn đang chọn, clear drtm bàn đang chọn
                    listBanDangChon.removeAll(listBanDangChon);
                    dtmBanDangChon.setRowCount(0);
                    int checkConfirm = JOptionPane.showConfirmDialog(null, "Xác nhận thêm!");
                    if (checkConfirm == 0) {
                        JOptionPane.showMessageDialog(this, themHD);
                        JOptionPane.showMessageDialog(new Form_HoaDon(nhanVien), "Vui lòng refresh để kiểm tra thay đổi!");
                        //thoát form
                        this.dispose();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Không thành công");
                    if (listBanDangChon.size() > 0) {
                        for (Ban ban : listBanDangChon) {
                            ban.setTrangThai(0);
                            String upDateBan = banService.update(ban, ban.getMaBan().toString());
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        if (listBanDangChon.size() > 0) {
            for (Ban ban : listBanDangChon) {
                ban.setTrangThai(0);
                String upDateBan = banService.update(ban, ban.getMaBan().toString());
            }
        }
        listBan = banService.getFull();
        showDataBan(listBan);
        listBanDangChon.removeAll(listBanDangChon);
        showDataBanDangChon(listBanDangChon);
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        if (listBanDangChon.size() > 0) {
            for (Ban ban : listBanDangChon) {
                ban.setTrangThai(0);
                String upDateBan = banService.update(ban, ban.getMaBan().toString());
            }
        }
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

//    /**
//     * @param args the command line arguments
//     */
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
//            java.util.logging.Logger.getLogger(JDialogBan_ThemHD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(JDialogBan_ThemHD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(JDialogBan_ThemHD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(JDialogBan_ThemHD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                JDialogBan_ThemHD dialog = new JDialogBan_ThemHD(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbBan;
    private javax.swing.JTable tblBanDangChon;
    // End of variables declaration//GEN-END:variables
}
