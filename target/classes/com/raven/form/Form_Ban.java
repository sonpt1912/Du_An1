/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.form;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import com.mycompany.domainModel.Ban;
import com.mycompany.domainModel.KhuVuc;
import com.mycompany.domainModel.NhanVien;
import com.mycompany.service.impl.BanService;
import com.mycompany.service.impl.KhuVucService;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*


/**
 *
 * @author RAVEN
 */
public class Form_Ban extends javax.swing.JPanel {

    private DefaultTableModel dtmBan = new DefaultTableModel();
    private List<Ban> listBan = new ArrayList<>();
    private BanService banService = new BanService();
    private List<KhuVuc> listKV = new ArrayList<>();
    private DefaultComboBoxModel dcbmKhuVuc = new DefaultComboBoxModel();
    private KhuVucService khuVucService = new KhuVucService();
    private NhanVien nv;

    public Form_Ban() {
        initComponents();
        //nv = nhanVien;
        tbBan.setModel(dtmBan);
        String header[] = {"Mã bàn", "Khu vực", "Số lượng chỗ ngồi", "Trạng thái"};
        dtmBan.setColumnIdentifiers(header);
        cbbMaKhuVuc.setModel(dcbmKhuVuc);
        //trạng thái = 0 => còn trống, = 1 có người
        listBan = banService.getAll();
        //thêm toData row vào domain
        showData(listBan);
        listKV = khuVucService.getAll();
        loadCbb(listKV);
    }


    private void showData(List<Ban> listBan) {
        dtmBan.setRowCount(0);
        for (Ban ban : listBan) {
            dtmBan.addRow(ban.toDataRow());
        }
    }

    private void loadCbb(List<KhuVuc> listKV) {
        cbbMaKhuVuc.removeAllItems();
        for (KhuVuc khuVuc : listKV) {
            dcbmKhuVuc.addElement(khuVuc.getMaKV());
        }
    }

    private Ban banNew() {
        Ban ban = new Ban();
        KhuVuc khuVuc = khuVucService.getOne(cbbMaKhuVuc.getSelectedItem().toString());
        ban.setKv(khuVuc);
        ban.setMaBan(Integer.valueOf(txtMa.getText()));
        ban.setSoLuongChoNgoi(Integer.valueOf(txtSoLuong.getText()));
        ban.setTrangThai(Integer.valueOf(txtTrangThai.getText()));
        return ban;
    }

    private void fill(int index, List<Ban> listBan) {
        Ban ban = listBan.get(index);
        txtMa.setText(String.valueOf(ban.getMaBan()));
        txtMa.setEnabled(false);
        txtSoLuong.setText(String.valueOf(ban.getSoLuongChoNgoi()));
        txtTrangThai.setText(String.valueOf(ban.getTrangThai()));
        cbbMaKhuVuc.setSelectedItem(ban.getKv().getMaKV());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.raven.swing.PanelBorder();
        searchText1 = new com.raven.swing.SearchText();
        jLabel1 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTrangThai = new javax.swing.JTextField();
        txtMa = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtTenKhuVuc = new javax.swing.JTextField();
        cbbMaKhuVuc = new javax.swing.JComboBox<>();
        btnAddKhuVuc = new javax.swing.JButton();
        btnLamMoiKhuVuc = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbBan = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();

        panelBorder1.setBackground(new java.awt.Color(204, 204, 255));

        jButton9.setBackground(new java.awt.Color(204, 204, 204));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton9.setText("Search");

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Mã Bàn                  :");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Số lượng chỗ ngồi:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Trạng Thái             :");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Mã Khu Vực :");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Tên Khu Vực:");

        cbbMaKhuVuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbMaKhuVuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMaKhuVucActionPerformed(evt);
            }
        });

        btnAddKhuVuc.setText("+");
        btnAddKhuVuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddKhuVucActionPerformed(evt);
            }
        });

        btnLamMoiKhuVuc.setText("@");
        btnLamMoiKhuVuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiKhuVucActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(171, 171, 171)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(txtTenKhuVuc))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(cbbMaKhuVuc, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 20, Short.MAX_VALUE)
                .addComponent(btnAddKhuVuc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLamMoiKhuVuc)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel11)
                    .addComponent(cbbMaKhuVuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddKhuVuc)
                    .addComponent(btnLamMoiKhuVuc))
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtTenKhuVuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(88, 88, 88))
        );

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
        jScrollPane5.setViewportView(tbBan);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Thiết lập thông tin bàn");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Danh sách bàn");

        btnAdd.setBackground(new java.awt.Color(51, 255, 0));
        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnRemove.setBackground(new java.awt.Color(255, 0, 51));
        btnRemove.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRemove.setText("Remove");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnClear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchText1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jButton9)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAdd)
                                .addGap(18, 18, 18)
                                .addComponent(btnUpdate)
                                .addGap(18, 18, 18)
                                .addComponent(btnRemove)
                                .addGap(18, 18, 18)
                                .addComponent(btnClear)))
                        .addContainerGap())))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton9)))
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addGap(8, 8, 8)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAdd)
                        .addComponent(btnUpdate)
                        .addComponent(btnRemove)
                        .addComponent(btnClear)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        Ban ban = banNew();
        JOptionPane.showMessageDialog(this, banService.add(ban));
        listBan = banService.getAll();
        showData(listBan);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnLamMoiKhuVucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiKhuVucActionPerformed
        listKV = khuVucService.getAll();
        loadCbb(listKV);
    }//GEN-LAST:event_btnLamMoiKhuVucActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        txtMa.setText("");
        txtSoLuong.setText("0");
        txtTrangThai.setText("0");
        cbbMaKhuVuc.setSelectedIndex(0);
        txtMa.setEnabled(true);
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnAddKhuVucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddKhuVucActionPerformed
        JDialogKhuVuc viewKhuVuc = new JDialogKhuVuc(null, true);
        viewKhuVuc.setVisible(true);
    }//GEN-LAST:event_btnAddKhuVucActionPerformed

    private void cbbMaKhuVucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMaKhuVucActionPerformed
        KhuVuc khuVuc = khuVucService.getOne(cbbMaKhuVuc.getSelectedItem().toString());
        txtTenKhuVuc.setText(khuVuc.getTenKV());
    }//GEN-LAST:event_cbbMaKhuVucActionPerformed

    private void tbBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBanMouseClicked
        int index = tbBan.getSelectedRow();
        fill(index, listBan);
    }//GEN-LAST:event_tbBanMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int index = tbBan.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Chọn data cần sửa!");
        } else {
            Ban ban = banNew();
            JOptionPane.showMessageDialog(this, banService.update(ban, txtMa.getText()));
            listBan = banService.getAll();
            showData(listBan);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        int index = tbBan.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Chọn data cần xoá!");
        } else {
            JOptionPane.showMessageDialog(this, banService.remove(txtMa.getText()));
            listBan = banService.getAll();
            showData(listBan);
        }
    }//GEN-LAST:event_btnRemoveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddKhuVuc;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnLamMoiKhuVuc;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbbMaKhuVuc;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane5;
    private com.raven.swing.PanelBorder panelBorder1;
    private com.raven.swing.SearchText searchText1;
    private javax.swing.JTable tbBan;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenKhuVuc;
    private javax.swing.JTextField txtTrangThai;
    // End of variables declaration//GEN-END:variables
}
