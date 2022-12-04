/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.raven.form;

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

/**
 *
 * @author Admin
 */
public class JDialogThemNhanhBan extends javax.swing.JDialog {

    private DefaultTableModel dtmBan = new DefaultTableModel();
    private List<Ban> listBan = new ArrayList<>();
    private BanService banService = new BanService();
    private List<KhuVuc> listKV = new ArrayList<>();
    private DefaultComboBoxModel dcbmKhuVuc = new DefaultComboBoxModel();
    private KhuVucService khuVucService = new KhuVucService();
    private NhanVien nv;

    public JDialogThemNhanhBan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //nv = nhanVien;
        tbBan.setModel(dtmBan);
        String header[] = {"Mã bàn", "Khu vực", "Số lượng chỗ ngồi", "Trạng thái"};
        dtmBan.setColumnIdentifiers(header);
        cbbMaKhuVuc4.setModel(dcbmKhuVuc);
        //trạng thái = 0 => còn trống, = 1 có người
        listBan = banService.getFull();
        //thêm toData row vào domain
        showData(listBan);
        listKV = khuVucService.getAll();
        loadCbb(listKV);
        rdoDaXoa4.setEnabled(false);
        radioControng4.setSelected(true);
    }

    private void showData(List<Ban> listBan) {
        dtmBan.setRowCount(0);
        for (Ban ban : listBan) {
            dtmBan.addRow(ban.toDataRow());
        }
    }

    private void loadCbb(List<KhuVuc> listKV) {
        cbbMaKhuVuc4.removeAllItems();
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
        txtMa4.setText(String.valueOf(ban.getMaBan()));
        txtMa4.setEnabled(false);
        txtSoLuong4.setText(String.valueOf(ban.getSoLuongChoNgoi()));
        if (ban.getTrangThai() == 0) {
            radioControng4.setSelected(true);
            cbbMaKhuVuc4.setSelectedItem(ban.getKv().getMaKV());
        }
        if (ban.getTrangThai() == 1) {
            radioCoNguoi4.setSelected(true);
            cbbMaKhuVuc4.setSelectedItem(ban.getKv().getMaKV());
        }
        if (ban.getTrangThai() == 2) {
            rdoDaXoa4.setSelected(true);
            cbbMaKhuVuc4.setSelectedItem(ban.getKv().getMaKV());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelBorder5 = new com.raven.swing.PanelBorder();
        txtSearch4 = new com.raven.swing.SearchText();
        jLabel25 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtMa4 = new javax.swing.JTextField();
        txtSoLuong4 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtTenKhuVuc4 = new javax.swing.JTextField();
        cbbMaKhuVuc4 = new javax.swing.JComboBox<>();
        btnAddKhuVuc4 = new javax.swing.JButton();
        btnLamMoiKhuVuc4 = new javax.swing.JButton();
        radioControng4 = new javax.swing.JRadioButton();
        radioCoNguoi4 = new javax.swing.JRadioButton();
        rdoDaXoa4 = new javax.swing.JRadioButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbBan = new javax.swing.JTable();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelBorder5.setBackground(new java.awt.Color(204, 204, 255));

        txtSearch4.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearch4CaretUpdate(evt);
            }
        });

        jButton13.setBackground(new java.awt.Color(204, 204, 204));
        jButton13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton13.setText("Search");

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel26.setText("Mã Bàn                  :");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel27.setText("Số lượng chỗ ngồi:");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel28.setText("Trạng Thái             :");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel29.setText("Mã Khu Vực :");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel30.setText("Tên Khu Vực:");

        cbbMaKhuVuc4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbMaKhuVuc4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMaKhuVuc4ActionPerformed(evt);
            }
        });

        btnAddKhuVuc4.setText("+");
        btnAddKhuVuc4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddKhuVuc4ActionPerformed(evt);
            }
        });

        btnLamMoiKhuVuc4.setText("@");
        btnLamMoiKhuVuc4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiKhuVuc4ActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioControng4);
        radioControng4.setText("Còn trống");

        buttonGroup1.add(radioCoNguoi4);
        radioCoNguoi4.setText("Có người");

        buttonGroup1.add(rdoDaXoa4);
        rdoDaXoa4.setText("Đã xoá");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addGap(21, 21, 21)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(radioControng4)
                                .addGap(56, 56, 56)
                                .addComponent(radioCoNguoi4))
                            .addComponent(txtSoLuong4)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMa4, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addGap(18, 18, 18)
                                .addComponent(txtTenKhuVuc4))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addGap(18, 18, 18)
                                .addComponent(cbbMaKhuVuc4, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addComponent(btnAddKhuVuc4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLamMoiKhuVuc4)
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(rdoDaXoa4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMa4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel29)
                    .addComponent(cbbMaKhuVuc4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddKhuVuc4)
                    .addComponent(btnLamMoiKhuVuc4))
                .addGap(48, 48, 48)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(txtSoLuong4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(txtTenKhuVuc4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(radioControng4)
                    .addComponent(radioCoNguoi4)
                    .addComponent(rdoDaXoa4))
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

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setText("Thiết lập thông tin bàn");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel32.setText("Danh sách bàn");

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

        javax.swing.GroupLayout panelBorder5Layout = new javax.swing.GroupLayout(panelBorder5);
        panelBorder5.setLayout(panelBorder5Layout);
        panelBorder5Layout.setHorizontalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder5Layout.createSequentialGroup()
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addGroup(panelBorder5Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearch4, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jButton13)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelBorder5Layout.createSequentialGroup()
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelBorder5Layout.createSequentialGroup()
                                .addComponent(jLabel32)
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
        panelBorder5Layout.setVerticalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearch4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton13)))
                .addGap(10, 10, 10)
                .addComponent(jLabel31)
                .addGap(8, 8, 8)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel32)
                    .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAdd)
                        .addComponent(btnUpdate)
                        .addComponent(btnRemove)
                        .addComponent(btnClear)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBorder5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearch4CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearch4CaretUpdate
        // TODO add your handling code here:
        String search = txtSearch4.getText();
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
    }//GEN-LAST:event_txtSearch4CaretUpdate

    private void cbbMaKhuVuc4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMaKhuVuc4ActionPerformed
        try {
            KhuVuc khuVuc = khuVucService.getOne(cbbMaKhuVuc4.getSelectedItem().toString());
            txtTenKhuVuc4.setText(khuVuc.getTenKV());
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cbbMaKhuVuc4ActionPerformed

    private void btnAddKhuVuc4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddKhuVuc4ActionPerformed
        JDialogKhuVuc viewKhuVuc = new JDialogKhuVuc(null, true);
        viewKhuVuc.setVisible(true);
    }//GEN-LAST:event_btnAddKhuVuc4ActionPerformed

    private void btnLamMoiKhuVuc4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiKhuVuc4ActionPerformed
        listKV = khuVucService.getAll();
        loadCbb(listKV);
    }//GEN-LAST:event_btnLamMoiKhuVuc4ActionPerformed

    private void tbBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBanMouseClicked
        int index = tbBan.getSelectedRow();
        fill(index, listBan);
    }//GEN-LAST:event_tbBanMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if ("".equals(txtMa4.getText())) {
            JOptionPane.showMessageDialog(this, "Mã không được trống");
            return;
        }
        if (!txtMa4.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Mã phải là số");
            return;
        }
        if (null != banService.getOne(txtMa4.getText())) {
            JOptionPane.showMessageDialog(this, "Mã trùng");
            return;
        }
        if ("".equals(txtSoLuong4.getText())) {
            JOptionPane.showMessageDialog(this, "Số lượng không được trống");
            return;
        }
        if (!txtSoLuong4.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số");
            return;
        }
        if (Integer.valueOf(txtSoLuong4.getText()) < 1) {
            JOptionPane.showMessageDialog(this, "Số lượng chỗ ngồi không được dưới 1");
            return;
        }
        if ("".equals(txtTenKhuVuc4.getText())) {
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
            KhuVuc khuVuc = khuVucService.getOne(cbbMaKhuVuc4.getSelectedItem().toString());
            ban.setKv(khuVuc);
            ban.setMaBan(Integer.valueOf(txtMa4.getText()));
            ban.setSoLuongChoNgoi(Integer.valueOf(txtSoLuong4.getText()));
            if (radioCoNguoi4.isSelected()) {
                ban.setTrangThai(1);
            } else if (radioControng4.isSelected()) {
                ban.setTrangThai(0);
            } else {
                ban.setTrangThai(2);
            }
            JOptionPane.showMessageDialog(this, banService.add(ban));
            listBan = banService.getFull();
            showData(listBan);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int index = tbBan.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Chọn data cần sửa!");
        } else {
            if ("".equals(txtSoLuong4.getText())) {
                JOptionPane.showMessageDialog(this, "Số lượng không được trống");
                return;
            }
            if (!txtSoLuong4.getText().matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Số lượng phải là số");
                return;
            }
            if (Integer.valueOf(txtSoLuong4.getText()) < 1) {
                JOptionPane.showMessageDialog(this, "Số lượng chỗ ngồi không được dưới 1");
                return;
            }
            if ("".equals(txtTenKhuVuc4.getText())) {
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
                KhuVuc khuVuc = khuVucService.getOne(cbbMaKhuVuc4.getSelectedItem().toString());
                ban.setKv(khuVuc);
                ban.setMaBan(Integer.valueOf(txtMa4.getText()));
                ban.setSoLuongChoNgoi(Integer.valueOf(txtSoLuong4.getText()));
                if (radioCoNguoi4.isSelected()) {
                    ban.setTrangThai(1);
                } else if (radioControng4.isSelected()) {
                    ban.setTrangThai(0);
                } else {
                    ban.setTrangThai(2);
                }
                JOptionPane.showMessageDialog(this, banService.update(ban, txtMa4.getText()));
                listBan = banService.getFull();
                showData(listBan);
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        int index = tbBan.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Chọn data cần xoá!");
        } else {
            if ("".equals(txtMa4.getText())) {
                JOptionPane.showMessageDialog(this, "Mã không được trống");
                return;
            }
            if (!txtMa4.getText().matches("\\d+")) {
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
//                Ban ban = new Ban();
//                KhuVuc khuVuc = khuVucService.getOne(cbbMaKhuVuc4.getSelectedItem().toString());
//                ban.setKv(khuVuc);
//                ban.setMaBan(Integer.valueOf(txtMa4.getText()));
//                ban.setSoLuongChoNgoi(Integer.valueOf(txtSoLuong4.getText()));
//                if (radioCoNguoi4.isSelected()) {
//                    ban.setTrangThai(1);
//                } else if (radioControng4.isSelected()) {
//                    ban.setTrangThai(0);
//                } else {
//                    ban.setTrangThai(2);
//                }
                JOptionPane.showMessageDialog(this, banService.remove(txtMa4.getText()));
                listBan = banService.getFull();
                showData(listBan);
            }
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        txtMa4.setText("");
        txtSoLuong4.setText("0");
        buttonGroup1.clearSelection();
        cbbMaKhuVuc4.setSelectedIndex(0);
        txtMa4.setEnabled(true);
    }//GEN-LAST:event_btnClearActionPerformed

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
            java.util.logging.Logger.getLogger(JDialogThemNhanhBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDialogThemNhanhBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDialogThemNhanhBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDialogThemNhanhBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDialogThemNhanhBan dialog = new JDialogThemNhanhBan(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddKhuVuc;
    private javax.swing.JButton btnAddKhuVuc1;
    private javax.swing.JButton btnAddKhuVuc2;
    private javax.swing.JButton btnAddKhuVuc3;
    private javax.swing.JButton btnAddKhuVuc4;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnLamMoiKhuVuc;
    private javax.swing.JButton btnLamMoiKhuVuc1;
    private javax.swing.JButton btnLamMoiKhuVuc2;
    private javax.swing.JButton btnLamMoiKhuVuc3;
    private javax.swing.JButton btnLamMoiKhuVuc4;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbMaKhuVuc;
    private javax.swing.JComboBox<String> cbbMaKhuVuc1;
    private javax.swing.JComboBox<String> cbbMaKhuVuc2;
    private javax.swing.JComboBox<String> cbbMaKhuVuc3;
    private javax.swing.JComboBox<String> cbbMaKhuVuc4;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane5;
    private com.raven.swing.PanelBorder panelBorder1;
    private com.raven.swing.PanelBorder panelBorder2;
    private com.raven.swing.PanelBorder panelBorder3;
    private com.raven.swing.PanelBorder panelBorder4;
    private com.raven.swing.PanelBorder panelBorder5;
    private javax.swing.JRadioButton radioCoNguoi;
    private javax.swing.JRadioButton radioCoNguoi1;
    private javax.swing.JRadioButton radioCoNguoi2;
    private javax.swing.JRadioButton radioCoNguoi3;
    private javax.swing.JRadioButton radioCoNguoi4;
    private javax.swing.JRadioButton radioControng;
    private javax.swing.JRadioButton radioControng1;
    private javax.swing.JRadioButton radioControng2;
    private javax.swing.JRadioButton radioControng3;
    private javax.swing.JRadioButton radioControng4;
    private javax.swing.JRadioButton rdoDaXoa;
    private javax.swing.JRadioButton rdoDaXoa1;
    private javax.swing.JRadioButton rdoDaXoa2;
    private javax.swing.JRadioButton rdoDaXoa3;
    private javax.swing.JRadioButton rdoDaXoa4;
    private javax.swing.JTable tbBan;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMa1;
    private javax.swing.JTextField txtMa2;
    private javax.swing.JTextField txtMa3;
    private javax.swing.JTextField txtMa4;
    private com.raven.swing.SearchText txtSearch;
    private com.raven.swing.SearchText txtSearch1;
    private com.raven.swing.SearchText txtSearch2;
    private com.raven.swing.SearchText txtSearch3;
    private com.raven.swing.SearchText txtSearch4;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtSoLuong1;
    private javax.swing.JTextField txtSoLuong2;
    private javax.swing.JTextField txtSoLuong3;
    private javax.swing.JTextField txtSoLuong4;
    private javax.swing.JTextField txtTenKhuVuc;
    private javax.swing.JTextField txtTenKhuVuc1;
    private javax.swing.JTextField txtTenKhuVuc2;
    private javax.swing.JTextField txtTenKhuVuc3;
    private javax.swing.JTextField txtTenKhuVuc4;
    // End of variables declaration//GEN-END:variables
}
