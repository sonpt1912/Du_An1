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

    public Form_Ban(NhanVien nv) {
        initComponents();
        //nv = nhanVien;
        tbBan.setModel(dtmBan);
        String header[] = {"Mã bàn", "Khu vực", "Số lượng chỗ ngồi", "Trạng thái"};
        dtmBan.setColumnIdentifiers(header);
        cbbMaKhuVuc.setModel(dcbmKhuVuc);
        //trạng thái = 0 => còn trống, = 1 có người
        listBan = banService.getFull();
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
        String soLuong = txtSoLuong.getText();
        if ("".equals(soLuong)) {
            soLuong = "0";
        }
        ban.setSoLuongChoNgoi(Integer.valueOf(soLuong));
        if (radioCoNguoi.isSelected()) {
            ban.setTrangThai(1);
        } else {
            ban.setTrangThai(0);
        }
        return ban;
    }

    private void fill(int index, List<Ban> listBan) {
        Ban ban = listBan.get(index);
        txtMa.setText(String.valueOf(ban.getMaBan()));
        txtMa.setEnabled(false);
        txtSoLuong.setText(String.valueOf(ban.getSoLuongChoNgoi()));
        if (ban.getTrangThai() == 0) {
            radioCoNguoi.setSelected(true);
        } else {
            radioControng.setSelected(true);
            cbbMaKhuVuc.setSelectedItem(ban.getKv().getMaKV());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelBorder1 = new com.raven.swing.PanelBorder();
        txtSearch = new com.raven.swing.SearchText();
        jLabel1 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtTenKhuVuc = new javax.swing.JTextField();
        cbbMaKhuVuc = new javax.swing.JComboBox<>();
        btnAddKhuVuc = new javax.swing.JButton();
        btnLamMoiKhuVuc = new javax.swing.JButton();
        radioControng = new javax.swing.JRadioButton();
        radioCoNguoi = new javax.swing.JRadioButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbBan = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();

        panelBorder1.setBackground(new java.awt.Color(204, 204, 255));

        txtSearch.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchCaretUpdate(evt);
            }
        });

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

        buttonGroup1.add(radioControng);
        radioControng.setText("Còn trống");

        buttonGroup1.add(radioCoNguoi);
        radioCoNguoi.setText("Có người");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(21, 21, 21)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(radioControng)
                                .addGap(56, 56, 56)
                                .addComponent(radioCoNguoi))
                            .addComponent(txtSoLuong)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 316, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(txtTenKhuVuc))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(cbbMaKhuVuc, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
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
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(radioControng)
                    .addComponent(radioCoNguoi))
                .addGap(86, 86, 86))
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
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        if ("".equals(txtMa.getText())) {
            JOptionPane.showMessageDialog(this, "Mã không được trống");
            return;
        }
        if (!txtMa.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Mã phải là số");
            return;
        }
        if (null != banService.getOne(txtMa.getText())) {
            JOptionPane.showMessageDialog(this, "Mã trùng");
            return;
        }
        if ("".equals(txtSoLuong.getText())) {
            JOptionPane.showMessageDialog(this, "Số lượng không được trống");
            return;
        }
        if (!txtSoLuong.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số");
            return;
        }
        if (Integer.valueOf(txtSoLuong.getText()) < 1) {
            JOptionPane.showMessageDialog(this, "Số lượng chỗ ngồi không được dưới 1");
            return;
        }
        if ("".equals(txtTenKhuVuc.getText())) {
            JOptionPane.showMessageDialog(this, "Tên khu vực không được trống");
            return;
        }
        int check = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không?");
        if (JOptionPane.NO_OPTION == check) {
            return;
        } else if (check == JOptionPane.CLOSED_OPTION) {
            return;
        } else if (check == JOptionPane.CANCEL_OPTION) {
            return;
        } else {
            Ban ban = new Ban();
            KhuVuc khuVuc = khuVucService.getOne(cbbMaKhuVuc.getSelectedItem().toString());
            ban.setKv(khuVuc);
            ban.setMaBan(Integer.valueOf(txtMa.getText()));
            ban.setSoLuongChoNgoi(Integer.valueOf(txtSoLuong.getText()));
            if (radioCoNguoi.isSelected()) {
                ban.setTrangThai(1);
            } else {
                ban.setTrangThai(0);
            }
            JOptionPane.showMessageDialog(this, banService.add(ban));
            listBan = banService.getAll();
            showData(listBan);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnLamMoiKhuVucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiKhuVucActionPerformed
        listKV = khuVucService.getAll();
        loadCbb(listKV);
    }//GEN-LAST:event_btnLamMoiKhuVucActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        txtMa.setText("");
        txtSoLuong.setText("0");
        buttonGroup1.clearSelection();
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
            if (null != banService.getOne(txtMa.getText())) {
                JOptionPane.showMessageDialog(this, "Mã trùng");
                return;
            }
            if ("".equals(txtSoLuong.getText())) {
                JOptionPane.showMessageDialog(this, "Số lượng không được trống");
                return;
            }
            if (!txtSoLuong.getText().matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Số lượng phải là số");
                return;
            }
            if (Integer.valueOf(txtSoLuong.getText()) < 1) {
                JOptionPane.showMessageDialog(this, "Số lượng chỗ ngồi không được dưới 1");
                return;
            }
            if ("".equals(txtTenKhuVuc.getText())) {
                JOptionPane.showMessageDialog(this, "Tên khu vực không được trống");
                return;
            }
            int check = JOptionPane.showConfirmDialog(this, "Bạn có sửa không?");
            if (JOptionPane.NO_OPTION == check) {
                return;
            } else if (check == JOptionPane.CLOSED_OPTION) {
                return;
            } else if (check == JOptionPane.CANCEL_OPTION) {
                return;
            } else {
                Ban ban = new Ban();
                KhuVuc khuVuc = khuVucService.getOne(cbbMaKhuVuc.getSelectedItem().toString());
                ban.setKv(khuVuc);
                ban.setMaBan(Integer.valueOf(txtMa.getText()));
                ban.setSoLuongChoNgoi(Integer.valueOf(txtSoLuong.getText()));
                if (radioCoNguoi.isSelected()) {
                    ban.setTrangThai(1);
                } else {
                    ban.setTrangThai(0);
                }
                JOptionPane.showMessageDialog(this, banService.update(ban, txtMa.getText()));
                listBan = banService.getAll();
                showData(listBan);
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        int index = tbBan.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Chọn data cần xoá!");
        } else {
            if ("".equals(txtMa.getText())) {
                JOptionPane.showMessageDialog(this, "Mã không được trống");
                return;
            }
            if (!txtMa.getText().matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Mã phải là số");
                return;
            }
            int check = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá không?");
            if (JOptionPane.NO_OPTION == check) {
                return;
            } else if (check == JOptionPane.CLOSED_OPTION) {
                return;
            } else if (check == JOptionPane.CANCEL_OPTION) {
                return;
            } else {
                Ban ban = new Ban();
                KhuVuc khuVuc = khuVucService.getOne(cbbMaKhuVuc.getSelectedItem().toString());
                ban.setKv(khuVuc);
                ban.setMaBan(Integer.valueOf(txtMa.getText()));
                ban.setSoLuongChoNgoi(Integer.valueOf(txtSoLuong.getText()));
                if (radioCoNguoi.isSelected()) {
                    ban.setTrangThai(1);
                } else {
                    ban.setTrangThai(0);
                }
                JOptionPane.showMessageDialog(this, banService.remove(txtMa.getText()));
                listBan = banService.getAll();
                showData(listBan);
            }
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void txtSearchCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchCaretUpdate
        // TODO add your handling code here:
        String search = txtSearch.getText();
        if ("".equals(search)) {
            listBan = banService.getFull();
            showData(listBan);
            return;
        }
        if (!search.matches("\\d+")) {
            listBan = banService.searchByString(search);
            showData(listBan);
            for (Ban ban : listBan) {
                System.out.println(ban.getMaBan());
            }
        } else {
            listBan = banService.searchByInteger(Integer.valueOf(search));
            showData(listBan);
            for (Ban ban : listBan) {
                System.out.println(ban.getMaBan());
            }
        }
        showData(listBan);
    }//GEN-LAST:event_txtSearchCaretUpdate


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddKhuVuc;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnLamMoiKhuVuc;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
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
    private javax.swing.JRadioButton radioCoNguoi;
    private javax.swing.JRadioButton radioControng;
    private javax.swing.JTable tbBan;
    private javax.swing.JTextField txtMa;
    private com.raven.swing.SearchText txtSearch;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenKhuVuc;
    // End of variables declaration//GEN-END:variables
}
