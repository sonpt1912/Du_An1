/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.raven.form;

import com.mycompany.domainModel.NhanVien;
import com.mycompany.service.impl.NhanVienService;
import com.raven.main.Main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List; 
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author Admin
 */
public class ViewLogin extends javax.swing.JFrame {

    private NhanVienService nhanVienService = new NhanVienService();
    //
    private List<NhanVien> listNhanVien = new ArrayList<>();

    /**
     * Creates new form Login
     */
    public ViewLogin() {
        initComponents();

        setSize(1000, 650);
        setVisible(true);

        setLayout(new BorderLayout());

        JLabel background = new JLabel(new ImageIcon("src\\main\\java\\com\\raven\\icon\\1.jpg"));
        ImageIcon iconLogo = new ImageIcon("src\\main\\java\\com\\raven\\icon\\user.png");
        add(background);
        iconUser.setIcon(iconLogo);
        background.setLayout(new FlowLayout());
//        jPanel2.setBackground(new Color(0, 0, 0, 190));
        jPanel2.addMouseListener(new MouseAdapter() {
        });
        jPanel2.addMouseMotionListener(new MouseAdapter() {
        });

        jPanel2.setFocusable(true);
        jPanel2.setBackground(new Color(0, 0, 0, 200));
        txtUser.setBackground(new Color(0, 0, 0, 200));
        txtMatKhau.setBackground(new Color(0, 0, 0, 200));
        btnDangNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDangNhap.setBackground(Color.DARK_GRAY);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDangNhap.setBackground(UIManager.getColor("control"));
            }
        });
        lbQuenPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbQuenPass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                lbQuenPass.setBackground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbQuenPass.setBackground(UIManager.getColor("control"));
            }
        });
        txtUser.setHorizontalAlignment(JTextField.LEFT);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtUser = new com.raven.swing.textfield.TextField();
        txtMatKhau = new com.raven.swing.textfield.PasswordField();
        btnDangNhap = new javax.swing.JButton();
        iconUser = new javax.swing.JLabel();
        lbLogin = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbQuenPass = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("LOGIN");
        setAlwaysOnTop(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, -1, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));

        txtUser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtUser.setForeground(new java.awt.Color(255, 255, 255));
        txtUser.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtUser.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtUser.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        txtMatKhau.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtMatKhau.setForeground(new java.awt.Color(51, 51, 51));
        txtMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtMatKhau.setShowAndHide(true);
        txtMatKhau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMatKhauKeyPressed(evt);
            }
        });

        btnDangNhap.setBackground(new java.awt.Color(204, 204, 255));
        btnDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDangNhap.setText("LOGIN");
        btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });

        lbLogin.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        lbLogin.setForeground(new java.awt.Color(255, 255, 255));
        lbLogin.setText("LOGIN");

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("If you forgot password ");

        lbQuenPass.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lbQuenPass.setForeground(new java.awt.Color(255, 255, 204));
        lbQuenPass.setText("CLICK HERE !");
        lbQuenPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbQuenPassMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(93, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lbLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(iconUser, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(199, 199, 199))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(3, 3, 3)
                                    .addComponent(lbQuenPass))))
                        .addGap(88, 88, 88))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iconUser, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbQuenPass))
                .addGap(18, 18, 18)
                .addComponent(btnDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 500, 470));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangNhapActionPerformed
        // TODO add your handling code here:
        String pass = new String(txtMatKhau.getPassword());
        String user = txtUser.getText();
        if (user.isEmpty()) {
            JOptionPane.showMessageDialog(this, "không được để trống mật khẩu hoặc tài khoản");
        } else if (pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "không được để trống mật khẩu hoặc tài khoản");
        } else {
            NhanVien login = nhanVienService.getUserAndPass(user, pass);
            if (login != null) {
                Main trangChu = new Main(login);
                this.dispose();
                trangChu.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "tài khoản hoặc mật khẩu không chính xác");
            }
        }

    }//GEN-LAST:event_btnDangNhapActionPerformed

    private void lbQuenPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbQuenPassMouseClicked
        // TODO add your handling code here:

        JDialogQuenMatKhau viewQuenMatKhau = new JDialogQuenMatKhau(this, true);
        this.dispose();
        viewQuenMatKhau.setVisible(true);
    }//GEN-LAST:event_lbQuenPassMouseClicked

    private void txtMatKhauKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMatKhauKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String pass = new String(txtMatKhau.getPassword());
            String user = txtUser.getText();
            NhanVien login = nhanVienService.getUserAndPass(user, pass);
            if (login != null) {
                Main trangChu = new Main(login);
                this.dispose();
                trangChu.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "tài khoản hoặc mật khẩu không chính xác");
            }
        }
    }//GEN-LAST:event_txtMatKhauKeyPressed

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
            java.util.logging.Logger.getLogger(ViewLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangNhap;
    private javax.swing.JLabel iconUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbLogin;
    private javax.swing.JLabel lbQuenPass;
    private com.raven.swing.textfield.PasswordField txtMatKhau;
    private com.raven.swing.textfield.TextField txtUser;
    // End of variables declaration//GEN-END:variables
}
