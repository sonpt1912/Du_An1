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
import com.mycompany.domainModel.ChucVu;
import com.mycompany.domainModel.NhanVien;
import com.mycompany.service.impl.ChucVuService;
import com.mycompany.service.impl.NhanVienService;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAVEN
 */
public class Form_NhanVien extends javax.swing.JPanel {

    // gọi service
    private NhanVienService nhanVienService = new NhanVienService();
    private ChucVuService chucVuService = new ChucVuService();
    // tạo list thực thể
    private List<NhanVien> listNhanVien = new ArrayList<>();
    private List<ChucVu> listChucVu = new ArrayList<>();
    // tạo model view table
    private DefaultTableModel dtmNhanVien = new DefaultTableModel();
    // tạo model view combobox
    private DefaultComboBoxModel dcbmMaCV = new DefaultComboBoxModel();
    // tạo thực thể
    private NhanVien nv;
    // format ngày
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private java.util.Date today = new java.util.Date();

    public Form_NhanVien() {
        initComponents();
        //        lbNhanVien.setText("WELCOME " + nhanVien.getTen());
        tbNhanVien.setModel(dtmNhanVien);
        cbbChucVu.setModel(dcbmMaCV);
        String header[] = {"MÃ", "HỌ VÀ Tên", "GIỚI TÍNH", "SDT", "NGÀY SINH", "TRẠNG THÁI"};
        dtmNhanVien.setColumnIdentifiers(header);
        showData(listNhanVien = nhanVienService.getAll());
        listChucVu = chucVuService.getChucVuActive();
        loadCBB(listChucVu);
        txtNgaySinh.setDate(today);
    }

    public void showData(List<NhanVien> list) {
        dtmNhanVien.setRowCount(0);
        list.forEach(s -> dtmNhanVien.addRow(s.toDataRow()));
    }

    private void loadCBB(List<ChucVu> list) {
        if (dcbmMaCV != null) {
            dcbmMaCV.removeAllElements();
        }
        for (ChucVu cv : list) {
            dcbmMaCV.addElement(cv.getMa());
        }
    }

    private void fillData(int index) {
        nv = listNhanVien.get(index);
        txtDiaChi.setText(nv.getDiaChi());
        txtEmail.setText(nv.getEmail());
        txtHo.setText(nv.getHo());
        txtMa.setText(nv.getMa());
        txtMa.setEnabled(false);
        txtSdt.setText(nv.getSoDienThoai());
        txtTen.setText(nv.getTen());
        txtTenDem.setText(nv.getTenDem());
        if (nv.getTrangThai() == 0) {
            radioActive.setSelected(true);
        } else {
            radioUnactive.setSelected(true);
        }
        if (nv.getGioiTinh().equals("Nam")) {
            radioNam.setSelected(true);
        } else if (nv.getGioiTinh().equals("Nữ")) {
            radioNu.setSelected(true);
        } else {
            radioKoXD.setSelected(true);
        }
        cbbChucVu.setSelectedItem(nv.getChucVu().getMa());
        txtNgaySinh.setDate(nv.getNgaySinh());
        txtMatKhau.setText(nv.getMatKhau());
        txtTenCV.setText(nv.getChucVu().getTen());
    }

    private NhanVien newNV() {
        NhanVien nhanVien = new NhanVien();
        ChucVu chucVu = chucVuService.getOne(dcbmMaCV.getSelectedItem().toString());
        nhanVien.setChucVu(chucVu);
        nhanVien.setDiaChi(txtDiaChi.getText());
        nhanVien.setEmail(txtEmail.getText());
        if (radioNam.isSelected()) {
            nhanVien.setGioiTinh("Nam");
        } else if (radioNu.isSelected()) {
            nhanVien.setGioiTinh("Nữ");
        } else {
            nhanVien.setGioiTinh("Ko xác định");
        }
        nhanVien.setHo(txtHo.getText());
        nhanVien.setMa(txtMa.getText());
        nhanVien.setMatKhau(txtMatKhau.getText());
        nhanVien.setNgaySinh(Date.valueOf(simpleDateFormat.format(txtNgaySinh.getDate())));
        nhanVien.setSoDienThoai(txtSdt.getText());
        nhanVien.setTen(txtTen.getText());
        nhanVien.setTenDem(txtTenDem.getText());
        if (radioActive.isSelected()) {
            nhanVien.setTrangThai(0);
        } else {
            nhanVien.setTrangThai(1);
        }
        return nhanVien;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        radioTrangThaiNV = new javax.swing.ButtonGroup();
        radioGioiTinh = new javax.swing.ButtonGroup();
        panelBorder1 = new com.raven.swing.PanelBorder();
        txtSearch = new com.raven.swing.SearchText();
        jLabel1 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        txtMa = new javax.swing.JTextField();
        txtNgaySinh = new com.toedter.calendar.JDateChooser();
        txtHo = new javax.swing.JTextField();
        txtTenDem = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtSdt = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtMatKhau = new javax.swing.JTextField();
        cbbChucVu = new javax.swing.JComboBox<>();
        btnAddChucVu = new javax.swing.JButton();
        btnLoad = new javax.swing.JButton();
        radioActive = new javax.swing.JRadioButton();
        radioUnactive = new javax.swing.JRadioButton();
        jLabel16 = new javax.swing.JLabel();
        txtTenCV = new javax.swing.JTextField();
        radioNam = new javax.swing.JRadioButton();
        radioNu = new javax.swing.JRadioButton();
        radioKoXD = new javax.swing.JRadioButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbNhanVien = new javax.swing.JTable();
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

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Mã NV                   :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Họ                          :");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Tên Đệm                :");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Tên                         :");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Giới Tính                :");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Ngày Sinh              :");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Sđt            :");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Email         :");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Địa chỉ       :");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Mật Khẩu   :");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Chức Vụ     :");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Trạng Thái  :");

        cbbChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbChucVuActionPerformed(evt);
            }
        });

        btnAddChucVu.setText("+");
        btnAddChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddChucVuActionPerformed(evt);
            }
        });

        btnLoad.setText("Load");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });

        radioTrangThaiNV.add(radioActive);
        radioActive.setText("Active");

        radioTrangThaiNV.add(radioUnactive);
        radioUnactive.setText("Unactive");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Tên CV:");

        radioGioiTinh.add(radioNam);
        radioNam.setText("Nam");

        radioGioiTinh.add(radioNu);
        radioNu.setText("Nữ");

        radioGioiTinh.add(radioKoXD);
        radioKoXD.setText("Ko xác định");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtTenDem, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel15))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(radioActive)
                                                .addGap(27, 27, 27)
                                                .addComponent(radioUnactive))
                                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(radioNam)
                                        .addGap(18, 18, 18)
                                        .addComponent(radioNu)
                                        .addGap(18, 18, 18)
                                        .addComponent(radioKoXD)))))
                        .addGap(217, 217, 217))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMa)
                            .addComponent(txtHo, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
                        .addGap(215, 215, 215)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addGap(18, 18, 18)
                            .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addGap(18, 18, 18)
                            .addComponent(txtEmail))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel12)
                            .addGap(18, 18, 18)
                            .addComponent(txtDiaChi))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addGap(18, 18, 18)
                            .addComponent(txtMatKhau)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbbChucVu, 0, 162, Short.MAX_VALUE)
                            .addComponent(txtTenCV))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAddChucVu)
                            .addComponent(btnLoad))))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10)
                                .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtHo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtTenDem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13)
                                .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(radioNam)
                                .addComponent(radioNu)
                                .addComponent(radioKoXD))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14)
                                .addComponent(cbbChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAddChucVu)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtTenCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLoad)
                            .addComponent(jLabel9)))
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(radioActive)
                    .addComponent(radioUnactive))
                .addGap(30, 30, 30))
        );

        tbNhanVien.setModel(new javax.swing.table.DefaultTableModel(
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
        tbNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNhanVienMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbNhanVien);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Thiết lập thông tin nhân viên");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Danh sách nhân viên");

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
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAdd)
                                .addGap(18, 18, 18)
                                .addComponent(btnUpdate)
                                .addGap(18, 18, 18)
                                .addComponent(btnRemove)
                                .addGap(18, 18, 18)
                                .addComponent(btnClear))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
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
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
//        ChucVu chucVu = chucVuService.getOne((String) cbbChucVu.getSelectedItem());
//        NhanVien nhanVien = new NhanVien(null, chucVu, txtMa.getText(), txtHo.getText(), txtTenDem.getText(), txtTen.getText(), txtGioiTinh.getText(), txtSdt.getText(), txtEmail.getText(), Date.valueOf(simpleDateFormat.format(txtNgaySinh.getDate())), txtDiaChi.getText(), txtMatKhau.getText(), Integer.valueOf(txtTrangThai.getText()));
        NhanVien nhanVien = newNV();
        String add = nhanVienService.add(nhanVien);
        JOptionPane.showMessageDialog(this, add);
        showData(listNhanVien = nhanVienService.getAll());
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnAddChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddChucVuActionPerformed
        // TODO add your handling code here:
        JDialogChucVu viewChucVu = new JDialogChucVu(null, true);
        viewChucVu.setVisible(true);
    }//GEN-LAST:event_btnAddChucVuActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
//        ChucVu chucVu = chucVuService.getOne((String) cbbChucVu.getSelectedItem());
//        String ma = txtMa.getText();
//        NhanVien nhanVien = new NhanVien(null, chucVu, txtMa.getText(), txtHo.getText(), txtTenDem.getText(), txtTen.getText(), txtGioiTinh.getText(), txtSdt.getText(), txtEmail.getText(), Date.valueOf(simpleDateFormat.format(txtNgaySinh.getDate())), txtDiaChi.getText(), txtMatKhau.getText(), Integer.valueOf(txtTrangThai.getText()));
        String ma = txtMa.getText();
        NhanVien nhanVien = newNV();
        String add = nhanVienService.update(nhanVien, ma);
        JOptionPane.showMessageDialog(this, add);
        showData(listNhanVien = nhanVienService.getAll());
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        String xoa = nhanVienService.remove(txtMa.getText());
        JOptionPane.showMessageDialog(this, xoa);
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void tbNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNhanVienMouseClicked
        int index = tbNhanVien.getSelectedRow();
        fillData(index);
    }//GEN-LAST:event_tbNhanVienMouseClicked

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        txtDiaChi.setText("");
        txtEmail.setText("");
        txtHo.setText("");
        txtMa.setText("");
        txtMa.setEnabled(true);
        txtSdt.setText("");
        txtTen.setText("");
        txtTenDem.setText("");
        radioActive.setSelected(true);
        radioNam.setSelected(true);
        cbbChucVu.setSelectedIndex(0);
        txtNgaySinh.setDate(today);
        txtMatKhau.setText("");
        if (dcbmMaCV != null) {
            txtTenCV.setText(chucVuService.getOne(dcbmMaCV.getSelectedItem().toString()).getTen());
        }
    }//GEN-LAST:event_btnClearActionPerformed

    private void txtSearchCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchCaretUpdate
        listNhanVien = nhanVienService.searchByNameAndMa(txtSearch.getText(), txtSearch.getText());
        if (txtSearch.getText().isEmpty()) {
            showData(listNhanVien = nhanVienService.getAll());
        } else {
            showData(listNhanVien);
        }
    }//GEN-LAST:event_txtSearchCaretUpdate

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        listChucVu = chucVuService.getChucVuActive();
        loadCBB(listChucVu);
    }//GEN-LAST:event_btnLoadActionPerformed

    private void cbbChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbChucVuActionPerformed
        try {
            txtTenCV.setText(chucVuService.getOne(dcbmMaCV.getSelectedItem().toString()).getTen());
        } catch (Exception e) {
            txtTenCV.setText("");
        }
    }//GEN-LAST:event_cbbChucVuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddChucVu;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbbChucVu;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane5;
    private com.raven.swing.PanelBorder panelBorder1;
    private javax.swing.JRadioButton radioActive;
    private javax.swing.ButtonGroup radioGioiTinh;
    private javax.swing.JRadioButton radioKoXD;
    private javax.swing.JRadioButton radioNam;
    private javax.swing.JRadioButton radioNu;
    private javax.swing.ButtonGroup radioTrangThaiNV;
    private javax.swing.JRadioButton radioUnactive;
    private javax.swing.JTable tbNhanVien;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHo;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMatKhau;
    private com.toedter.calendar.JDateChooser txtNgaySinh;
    private javax.swing.JTextField txtSdt;
    private com.raven.swing.SearchText txtSearch;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTenCV;
    private javax.swing.JTextField txtTenDem;
    // End of variables declaration//GEN-END:variables
}
