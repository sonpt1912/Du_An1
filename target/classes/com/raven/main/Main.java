/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.main;

import com.mycompany.domainModel.NhanVien;
import com.raven.component.ClockThread;
import com.raven.event.EventMenuSelected;
import com.raven.form.FormBan;
import com.raven.form.FormCombo;
import com.raven.form.FormHoaDon;
import com.raven.form.FormNhanVien;
import com.raven.form.FormKhachHang;
import com.raven.form.FormHome;
import com.raven.form.FormKhuyenMai;
import com.raven.form.FormSanPham;
import com.raven.form.FormTaiKhoan;
import com.raven.form.FormThongKe;
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author RAVEN
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    private FormHome home;
    private FormHoaDon form1;
    private FormNhanVien form2;
    private FormKhachHang form3;
    private FormBan form4;
    private FormKhuyenMai form5;
    private FormSanPham form6;
    private FormCombo form7;
    private FormThongKe form8;
    private FormTaiKhoan form9;

    public Main(NhanVien nv) {
        initComponents();
        lbTenMaNhanVien.setText(nv.getMa() + " - " + nv.getChucVu().getTen() + " - " + nv.getHo() + " " + nv.getTenDem() + " " + nv.getTen());
        setBackground(new Color(0, 0, 0, 0));
        home = new FormHome(nv);
        form1 = new FormHoaDon(nv);
        form2 = new FormNhanVien(nv);
        form3 = new FormKhachHang(nv);
        form4 = new FormBan(nv);
        form5 = new FormKhuyenMai(nv);
        form6 = new FormSanPham(nv);
        form7 = new FormCombo(nv);
        form8 = new FormThongKe(nv);
        form9 = new FormTaiKhoan(nv);
        menu.initMoving(Main.this);
        menu.addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void selected(int index) {
                if (nv.getChucVu().getMa().equalsIgnoreCase("CV2")) {
                    if (index == 0) {
                        setForm(home);
                    } else if (index == 1) {
                        setForm(form1);
                    } else if (index == 2) {
                        setForm(form2);
                    } else if (index == 3) {
                        setForm(form3);
                    } else if (index == 4) {
                        setForm(form4);
                    } else if (index == 5) {
                        setForm(form5);
                    } else if (index == 6) {
                        setForm(form6);
                    } else if (index == 7) {
                        setForm(form7);
                    } else if (index == 8) {
                        setForm(form8);
                    } else if (index == 9) {
                        setForm(form9);
                    } else if (index == 10) {
                        int checkOut = JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất không");
                        if (checkOut == JOptionPane.NO_OPTION) {
                            return;
                        } else if (checkOut == JOptionPane.CLOSED_OPTION) {
                            return;
                        } else if (checkOut == JOptionPane.CANCEL_OPTION) {
                            return;
                        } else {
                            System.exit(0);
                        }
                    }
                } else {
                    if (index == 0) {
                        setForm(home);
                    } else if (index == 1) {
                        setForm(form1);
                    } else if (index == 2) {
                        JOptionPane.showMessageDialog(rootPane, "vui lòng sử dụng quyền khách hàng");
                    } else if (index == 3) {
                        setForm(form3);
                    } else if (index == 4) {
                        setForm(form4);
                    } else if (index == 5) {
                        JOptionPane.showMessageDialog(rootPane, "vui lòng sử dụng quyền quản lý");
                    } else if (index == 6) {
                        JOptionPane.showMessageDialog(rootPane, "vui lòng sử dụng quyền quản lý");
                    } else if (index == 7) {
                        JOptionPane.showMessageDialog(rootPane, "vui lòng sử dụng quyền quản lý");
                    } else if (index == 8) {
                        JOptionPane.showMessageDialog(rootPane, "vui lòng sử dụng quyền quản lý");
                    } else if (index == 9) {
                        setForm(form9);
                    } else if (index == 10) {
                        int checkOut = JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất không");
                        if (checkOut == JOptionPane.NO_OPTION) {
                            return;
                        } else if (checkOut == JOptionPane.CLOSED_OPTION) {
                            return;
                        } else if (checkOut == JOptionPane.CANCEL_OPTION) {
                            return;
                        } else {
                            System.exit(0);
                        }
                    }
                }
            }
        });
        //  set when system open start with home form
        setForm(new FormHome(nv));
    }

    private void setForm(JComponent com) {
        mainPanel.removeAll();
        mainPanel.add(com);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.raven.swing.PanelBorder();
        menu = new com.raven.component.Menu();
        mainPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lbTenMaNhanVien = new javax.swing.JLabel();
        lbClock = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        btnThuNho = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        mainPanel.setOpaque(false);
        mainPanel.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbTenMaNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbTenMaNhanVien.setText("Nguyễn Đức Dụng - Nhân Viên");

        lbClock.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbClock.setText("2022-11-17 12:42:20");
        lbClock.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                lbClockAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        btnLogout.setBackground(new java.awt.Color(204, 204, 255));
        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 0, 51));
        btnLogout.setText("X");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnThuNho.setBackground(new java.awt.Color(204, 204, 255));
        btnThuNho.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThuNho.setForeground(new java.awt.Color(255, 51, 51));
        btnThuNho.setText("-");
        btnThuNho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThuNhoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lbTenMaNhanVien)
                .addGap(30, 30, 30)
                .addComponent(lbClock)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThuNho)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLogout)
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbClock)
                    .addComponent(lbTenMaNhanVien)
                    .addComponent(btnLogout)
                    .addComponent(btnThuNho))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1264, Short.MAX_VALUE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lbClockAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_lbClockAncestorAdded
        // TODO add your handling code here:
        ClockThread clock = new ClockThread(lbClock);
        clock.start();
    }//GEN-LAST:event_lbClockAncestorAdded

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        int checkOut = JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất không");
        if (checkOut == JOptionPane.NO_OPTION) {
            return;
        } else if (checkOut == JOptionPane.CLOSED_OPTION) {
            return;
        } else if (checkOut == JOptionPane.CANCEL_OPTION) {
            return;
        } else {
            System.exit(0);
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnThuNhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThuNhoActionPerformed
        // TODO add your handling code here:
        this.setExtendedState(Main.ICONIFIED);
    }//GEN-LAST:event_btnThuNhoActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Main().setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Main().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnThuNho;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbClock;
    private javax.swing.JLabel lbTenMaNhanVien;
    private javax.swing.JPanel mainPanel;
    private com.raven.component.Menu menu;
    private com.raven.swing.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
