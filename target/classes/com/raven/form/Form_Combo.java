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
import com.mycompany.domainModel.ChiTietComBo;
import com.mycompany.domainModel.ComBo;
import com.mycompany.domainModel.DanhMuc;
import com.mycompany.domainModel.HoaDon;
import com.mycompany.domainModel.MonAn;
import com.mycompany.domainModel.NhanVien;
import com.mycompany.service.impl.ChiTietComBoService;
import com.mycompany.service.impl.ComBoService;
import com.mycompany.service.impl.DanhMucService;
import com.mycompany.service.impl.MonAnService;
import com.mycompany.service.impl.NhanVienService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAVEN
 */
public class Form_Combo extends javax.swing.JPanel {
    // gọi service 

    private ComBoService comBoService = new ComBoService();
    private NhanVienService nhanVienService = new NhanVienService();
    private ChiTietComBoService chiTietComBoService = new ChiTietComBoService();
    private MonAnService monAnService = new MonAnService();
    private DanhMucService danhMucService = new DanhMucService();
    // tạo list
    private List<ComBo> listComBo = new ArrayList<>();
    private List<ChiTietComBo> listCTComBo = new ArrayList<>();
    private List<MonAn> listMonAn = new ArrayList<>();
    // tạo model table
    private DefaultTableModel dtComBo = new DefaultTableModel();
    private DefaultTableModel dtSanPham = new DefaultTableModel();
    private DefaultTableModel dtCTComBo = new DefaultTableModel();
    // tạo model cho combobox
    private DefaultComboBoxModel dcbNhanVien = new DefaultComboBoxModel();
    private DefaultComboBoxModel dcbLoai = new DefaultComboBoxModel();
    // tạo model 
    private NhanVien nhanVien;
    private ComBo comBo;
    private MonAn monAn;
    private ChiTietComBo chiTietComBo;
    
    public Form_Combo() {
        initComponents();
        tbComBo.setModel(dtComBo);
        tbCTCombo.setModel(dtCTComBo);
        tbChonMon.setModel(dtSanPham);
        String headerComBo[] = {"STT", "MÃ", "TÊN", "ĐƠN GIÁ"};
        String headerCTComBo[] = {"STT", "TÊN COMBO", "TÊN MÓN ĂN", "SỐ LƯỢNG MÓN ĂN"};
        String headerSanPham[] = {"STT", "MÃ SẢN PHẨM", "TÊN SẢN PHẨM", "ĐƠN VỊ TÍNH", "ĐƠN GIÁ"};
        //
        dtCTComBo.setColumnIdentifiers(headerCTComBo);
        dtComBo.setColumnIdentifiers(headerComBo);
        dtSanPham.setColumnIdentifiers(headerSanPham);
        //
        cbbMaNhanVien.setModel(dcbNhanVien);
        cbbLoaiMonAn.setModel(dcbLoai);
        //
        showDataComBo(listComBo = comBoService.getAllByTrangThai(0));
//        showDataSanPham(listMonAn = monAnService.getAll());
        txtMa.setEnabled(false);
        setCbb();
    }
    
    private void showDataComBo(List<ComBo> list) {
        dtComBo.setRowCount(0);
        int i = 1;
        for (ComBo cb : list) {
            dtComBo.addRow(cb.toDataRow(i++));
        }
    }
    
    private void showDataCTComBo(List<ChiTietComBo> list) {
        dtCTComBo.setRowCount(0);
        int i = 1;
        for (ChiTietComBo cb : list) {
            dtCTComBo.addRow(cb.toShowData(i++));
        }
    }
    
    private void showDataSanPham(List<MonAn> list) {
        dtSanPham.setRowCount(0);
        int i = 1;
        for (MonAn cb : list) {
            dtSanPham.addRow(cb.toDataRow(i++));
        }
    }
    
    private void setCbb() {
        List<NhanVien> listNhanVien = nhanVienService.getAll();
        for (NhanVien nv : listNhanVien) {
            dcbNhanVien.addElement(nv.getMa());
        }
        
        List<DanhMuc> listDanhMuc = danhMucService.getAll();
        for (DanhMuc dv : listDanhMuc) {
            dcbLoai.addElement(dv.getMaDanhMuc());
        }
    }
    
    private void clear() {
        txtDonGia.setText("");
        txtMa.setText("");
        txtTen.setText("");
        cbbMaNhanVien.setSelectedIndex(0);
        cbbLoaiMonAn.setSelectedIndex(0);
        rdoApDung.setSelected(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        panelBorder1 = new com.raven.swing.PanelBorder();
        txtSearch = new com.raven.swing.SearchText();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        txtMa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        txtTen = new javax.swing.JTextField();
        cbbMaNhanVien = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        rdoApDung = new javax.swing.JRadioButton();
        rdoChoApDung = new javax.swing.JRadioButton();
        rdoNgungApDung = new javax.swing.JRadioButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbChonMon = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbbLoaiMonAn = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbComBo = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbCTCombo = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        btnApDung = new javax.swing.JButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jRadioButton9 = new javax.swing.JRadioButton();
        jRadioButton10 = new javax.swing.JRadioButton();
        lbLoaiMon = new javax.swing.JLabel();

        panelBorder1.setBackground(new java.awt.Color(204, 204, 255));

        txtSearch.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchCaretUpdate(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Mã ComBo           :");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Tên ComBo          :");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Nhân Viên     :");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Đơn Giá                :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Trạng thái            :");

        btnAdd.setBackground(new java.awt.Color(0, 255, 0));
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

        btnRemove.setBackground(new java.awt.Color(255, 0, 0));
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

        cbbMaNhanVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Hình Ảnh       :");

        buttonGroup1.add(rdoApDung);
        rdoApDung.setSelected(true);
        rdoApDung.setText("Áp dụng");

        buttonGroup1.add(rdoChoApDung);
        rdoChoApDung.setText("Chờ Áp dụng");

        buttonGroup1.add(rdoNgungApDung);
        rdoNgungApDung.setText("Ngừng áp dụng");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(rdoApDung))))
                                .addGap(0, 0, 0)
                                .addComponent(rdoChoApDung))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(btnAdd)
                        .addGap(62, 62, 62)
                        .addComponent(btnUpdate)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnRemove)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(btnClear))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbMaNhanVien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(rdoNgungApDung)
                    .addComponent(jLabel11))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cbbMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel11))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(rdoApDung)
                    .addComponent(rdoChoApDung)
                    .addComponent(rdoNgungApDung))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnRemove)
                    .addComponent(btnClear))
                .addGap(15, 15, 15))
        );

        tbChonMon.setModel(new javax.swing.table.DefaultTableModel(
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
        tbChonMon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbChonMonMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbChonMon);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Thiết lập thông tin combo");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Chọn Món :");

        cbbLoaiMonAn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbLoaiMonAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLoaiMonAnActionPerformed(evt);
            }
        });

        tbComBo.setModel(new javax.swing.table.DefaultTableModel(
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
        tbComBo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbComBoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbComBo);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("ComBo");

        tbCTCombo.setModel(new javax.swing.table.DefaultTableModel(
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
        tbCTCombo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCTComboMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbCTCombo);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Chi Tiết ComBo");

        btnApDung.setBackground(new java.awt.Color(51, 255, 0));
        btnApDung.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnApDung.setText("Xác Nhận");
        btnApDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApDungActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton8);
        jRadioButton8.setText("Chờ Áp dụng");
        jRadioButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton8ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton9);
        jRadioButton9.setSelected(true);
        jRadioButton9.setText("Áp dụng");
        jRadioButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton9ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton10);
        jRadioButton10.setText("Ngừng áp dụng");
        jRadioButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton10ActionPerformed(evt);
            }
        });

        lbLoaiMon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbLoaiMon.setText("Hình Ảnh       :");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(24, 24, 24)
                        .addComponent(cbbLoaiMonAn, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbLoaiMon))
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel2)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton9)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton8)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton10)
                                .addGap(0, 40, Short.MAX_VALUE))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnApDung, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButton9)
                        .addComponent(jRadioButton8)
                        .addComponent(jRadioButton10))
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel9)))
                .addGap(4, 4, 4)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel3)
                    .addComponent(cbbLoaiMonAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbLoaiMon))
                .addGap(3, 3, 3)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(26, Short.MAX_VALUE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnApDung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (txtMa.getText().isEmpty()) {
            if (txtTen.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "không được để trống tên món ăn");
            } else if (!txtTen.getText().matches("[a-z A-Z0-9]+")) {
                JOptionPane.showMessageDialog(this, "tên phải là số hoặc chữ");
            } else if (txtDonGia.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "đơn giá được để trống tên món ăn");
            } else if (!txtDonGia.getText().matches("[0-9]+")) {
                JOptionPane.showMessageDialog(this, "đơn giá phải là số");
            } else {
                NhanVien nhanVien = nhanVienService.getOne(dcbNhanVien.getSelectedItem().toString());
                ComBo comBo = new ComBo(null, nhanVien, comBoService.randomMaHoaDon(), txtTen.getText(), null, new BigDecimal(txtDonGia.getText()), 2);
                String addComBo = comBoService.add(comBo);
                JOptionPane.showMessageDialog(this, addComBo);
                showDataComBo(listComBo = comBoService.getAll());
            }
        } else {
            JOptionPane.showMessageDialog(this, "vui lòng clear trước khi add");
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        String delete = comBoService.remove(txtMa.getText());
        JOptionPane.showMessageDialog(this, delete);
        clear();
        showDataComBo(listComBo = comBoService.getAll());
        showDataCTComBo(listCTComBo = chiTietComBoService.getAll());
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void tbComBoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbComBoMouseClicked
        // TODO add your handling code here:
        // lấy mã com bo
        int index = tbComBo.getSelectedRow();
        comBo = listComBo.get(index);
        // lấy danh sách ct hóa đơn
        listCTComBo = chiTietComBoService.getAllByComBo(comBo);
        showDataCTComBo(listCTComBo);

        // set combo ở ô text field
        txtMa.setText(comBo.getMaCB());
        txtTen.setText(comBo.getTenCB());
        txtDonGia.setText(comBo.getDonGia().toString());
        cbbMaNhanVien.setSelectedItem(comBo.getNhanVien().getMa());
        if (comBo.getTrangThai() == 0) {
            rdoApDung.setSelected(true);
        } else if (comBo.getTrangThai() == 2) {
            rdoChoApDung.setSelected(true);
        } else {
            rdoNgungApDung.setSelected(true);
        }
    }//GEN-LAST:event_tbComBoMouseClicked

    private void tbChonMonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbChonMonMouseClicked
        // TODO add your handling code here:
        if (comBo != null) {
            if (comBo.getTrangThai() == 2) {
                // lấy món ăn
                int index = tbChonMon.getSelectedRow();
                monAn = listMonAn.get(index);
                // lấy số lượng món ăn
                String soLuongNhap = JOptionPane.showInputDialog("xin mời nhập số lượng");
                if (soLuongNhap != null) {
                    if (soLuongNhap.matches("[0-9]+")) {
                        // tạo mới 1 chi tiết combo sau đó add vòa combo
                        ChiTietComBo chiTietComBo = new ChiTietComBo(null, comBo, monAn, Integer.valueOf(soLuongNhap)); // chứa số lượng vừa nhập

                        // 
//        String add = chiTietComBoService.add(chiTietComBo);
                        String add = chiTietComBoService.saveOrUpdate(comBo, monAn, chiTietComBo);
                        JOptionPane.showMessageDialog(this, add);

                        // show lại chi tiết combo
                        showDataCTComBo(listCTComBo = chiTietComBoService.getAllByComBo(comBo));
                    } else {
                        JOptionPane.showMessageDialog(this, "vui lòng nhập số");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "vui lòng chọn combo chờ áp dụng");
            }
        } else {
            JOptionPane.showMessageDialog(this, "vui lòng chọn combo");
        }
    }//GEN-LAST:event_tbChonMonMouseClicked

    private void cbbLoaiMonAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLoaiMonAnActionPerformed
        // TODO add your handling code here:
        String loaiDanhMuc = (String) cbbLoaiMonAn.getSelectedItem();
        DanhMuc danhMuc = danhMucService.getOne(loaiDanhMuc);
        lbLoaiMon.setText(danhMuc.getTenDanhMuc());
        listMonAn = monAnService.getMonAnByDanhMuc(danhMuc);
        showDataSanPham(listMonAn);
    }//GEN-LAST:event_cbbLoaiMonAnActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        int apDung;
        if (rdoApDung.isSelected()) {
            apDung = 0;
        } else if (rdoChoApDung.isSelected()) {
            apDung = 2;
        } else {
            apDung = 1;
        }
        NhanVien nhanVien = nhanVienService.getOne(dcbNhanVien.getSelectedItem().toString());
        ComBo comBo = new ComBo(null, nhanVien, txtMa.getText(), txtTen.getText(), null, new BigDecimal(txtDonGia.getText()), apDung);
        String update = comBoService.update(comBo, txtMa.getText());
        JOptionPane.showMessageDialog(this, update);
        // chưa show lại list
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void jRadioButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton9ActionPerformed
        // TODO add your handling code here:
        comBo = null;
        clear();
        showDataCTComBo(listCTComBo = chiTietComBoService.getAllByComBo(comBo));
        showDataComBo(listComBo = comBoService.getAllByTrangThai(0));
    }//GEN-LAST:event_jRadioButton9ActionPerformed

    private void jRadioButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton8ActionPerformed
        // TODO add your handling code here:
        comBo = null;
        clear();
        showDataCTComBo(listCTComBo = chiTietComBoService.getAllByComBo(comBo));
        showDataComBo(listComBo = comBoService.getAllByTrangThai(2));
    }//GEN-LAST:event_jRadioButton8ActionPerformed

    private void jRadioButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton10ActionPerformed
        // TODO add your handling code here:
        comBo = null;
        clear();
        showDataCTComBo(listCTComBo = chiTietComBoService.getAllByComBo(comBo));
        showDataComBo(listComBo = comBoService.getAllByTrangThai(1));
    }//GEN-LAST:event_jRadioButton10ActionPerformed

    private void btnApDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApDungActionPerformed
        // TODO add your handling code here:
        if (txtMa.getText() != null) {
            String ma = comBo.getMaCB();
            ComBo cb = new ComBo(comBo.getId(), nhanVien, comBo.getMaCB(), comBo.getTenCB(), comBo.getHinhAnh(), comBo.getDonGia(), 0);
            comBoService.update(cb, ma);
        } else {
            JOptionPane.showMessageDialog(this, "vui lòng chọn combo");
        }
    }//GEN-LAST:event_btnApDungActionPerformed

    private void tbCTComboMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCTComboMouseClicked
        // TODO add your handling code here:
        // lấy chi tiết combo
        int index = tbCTCombo.getSelectedRow();
        chiTietComBo = listCTComBo.get(index);

        //tạo chi tiết combo để update
        String soLuongGiam = JOptionPane.showInputDialog("chọn số lượng muốn giảm");
        if (soLuongGiam.matches("[0-9]+")) {
            int soLuongMonAn = chiTietComBo.getSoLuongMonAn() - Integer.valueOf(soLuongGiam);
            String update = chiTietComBoService.updateSoLuong(chiTietComBo, comBo, soLuongMonAn);
            JOptionPane.showMessageDialog(this, update);
            showDataCTComBo(listCTComBo = chiTietComBoService.getAllByComBo(comBo));
        } else {
            JOptionPane.showMessageDialog(this, "vui lòng nhập số");
        }
    }//GEN-LAST:event_tbCTComboMouseClicked

    private void txtSearchCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchCaretUpdate


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnApDung;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbbLoaiMonAn;
    private javax.swing.JComboBox<String> cbbMaNhanVien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton10;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lbLoaiMon;
    private com.raven.swing.PanelBorder panelBorder1;
    private javax.swing.JRadioButton rdoApDung;
    private javax.swing.JRadioButton rdoChoApDung;
    private javax.swing.JRadioButton rdoNgungApDung;
    private javax.swing.JTable tbCTCombo;
    private javax.swing.JTable tbChonMon;
    private javax.swing.JTable tbComBo;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtMa;
    private com.raven.swing.SearchText txtSearch;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
