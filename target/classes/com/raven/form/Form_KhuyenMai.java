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
import com.mycompany.customModel.MonAnKhuyenMaiResponse;
import com.mycompany.domainModel.KhuyenMai;
import com.mycompany.domainModel.MonAn;
import com.mycompany.domainModel.NhanVien;
import com.mycompany.repository.impl.KhuyenMaiRepository;
//import com.mycompany.domainModel.KhuyenMaiChiTiet;
//import com.mycompany.service.impl.KhuyenMaiChiTietService;
import com.mycompany.service.impl.KhuyenMaiService;
import com.mycompany.service.impl.MonAnService;
import com.mycompany.service.impl.NhanVienService;
import java.math.BigDecimal;
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
public class Form_KhuyenMai extends javax.swing.JPanel {
    
    private DefaultTableModel dtmKhuyenMai = new DefaultTableModel();
    private DefaultTableModel dtmKMCT = new DefaultTableModel();
    private List<KhuyenMai> listKM = new ArrayList<>();
    private KhuyenMaiRepository khuyenMaiRepository = new KhuyenMaiRepository();
    private DefaultComboBoxModel dcbmLoaiKM = new DefaultComboBoxModel();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//    private List<KhuyenMaiChiTiet> listKMCT = new ArrayList<>();
    private KhuyenMaiService khuyenMaiService = new KhuyenMaiService();
//    private KhuyenMaiChiTietService khuyenMaiChiTietService = new KhuyenMaiChiTietService();
    // private DefaultComboBoxModel
    private NhanVien nhanV;
    private java.util.Date today = new java.util.Date();
    private List<MonAn> listMonAn = new ArrayList<>();
    private MonAnService monAnService = new MonAnService();
    private DefaultTableModel dtmMonAn = new DefaultTableModel();
    private List<MonAn> listCTKM = new ArrayList<>();
    private List<String> listHinhThucTT = new ArrayList<>();
    private List<MonAn> listThemKM_MA = new ArrayList<>();
    private NhanVienService nhanVienService = new NhanVienService();
    
    public Form_KhuyenMai() {
        initComponents();
        this.nhanV = nhanV;
        tbKhuyenMai.setModel(dtmKhuyenMai);
        String headers[] = {"STT", "Mã KM", "Tên KM", "TrangThai"};
        dtmKhuyenMai.setColumnIdentifiers(headers);
        listKM = khuyenMaiRepository.getAll();
        showData(listKM, 1);
        radioDangApDung.setSelected(true);
        cbbLoaiKM.setModel(dcbmLoaiKM);
        String headerMonAn[] = {"STT", "Mã món ăn", "Tên món ăn", "Đơn giá", "Đv tính"};
        dtmMonAn.setColumnIdentifiers(headerMonAn);
        tbMonAn.setModel(dtmMonAn);
        listMonAn = monAnService.getAll();
        showDataMonAn(listMonAn, 1);
        tbKMCT.setModel(dtmKMCT);
        String headersKMCT[] = {"STT", "Mã KM", "Mã món ăn", "Tên món ăn"};
        dtmKMCT.setColumnIdentifiers(headersKMCT);
        loadLoaiKM();
    }
    
    private void showDataCTKM(List<MonAn> listKMCT, int stt) {
        dtmKMCT.setRowCount(0);
        for (MonAn monAn : listKMCT) {
            dtmKMCT.addRow(monAn.toDataRowViewKM(stt));
        }
    }
    
    private void showData(List<KhuyenMai> listKM, int stt) {
        dtmKhuyenMai.setRowCount(0);
        for (KhuyenMai khuyenMai : listKM) {
            dtmKhuyenMai.addRow(khuyenMai.toDataRowViewKM(stt));
            stt++;
        }
    }
    
    private void showDataMonAn(List<MonAn> listMonAn, int stt) {
        dtmMonAn.setRowCount(0);
        for (MonAn monAn : listMonAn) {
            dtmMonAn.addRow(monAn.toDataRow(stt));
            stt++;
        }
    }
    
    private void loadLoaiKM() {
        dcbmLoaiKM.addElement("Phần trăm");
        dcbmLoaiKM.addElement("Tiền mặt");
    }
    
    private void fillKM(int index, List<KhuyenMai> listKM) {
        KhuyenMai khuyenMai = listKM.get(index);
        txtTenKhuyenMai.setText(khuyenMai.getMaKhuyenMai());
        txtDonVi.setText(String.valueOf(khuyenMai.getGiaTriKM()));
        dcbmLoaiKM.setSelectedItem(khuyenMai.getLoaiKhuyenMai());
        txtThoiGianBatDau.setDate(khuyenMai.getThoiGianBD());
        txtThoiGianKetThuc.setDate(khuyenMai.getThoiGianKT());
        txtMaKM.setText(khuyenMai.getMaKhuyenMai());
        txtMaKM.setEnabled(false);
        if (khuyenMai.getTrangThai() == 0) {
            radioDangApDung.setSelected(true);
        } else {
            radioNgungApDung.setSelected(true);
        }
    }
    
    private KhuyenMai newKM() {
        KhuyenMai khuyenMai = new KhuyenMai();
        khuyenMai.setMaKhuyenMai(txtMaKM.getText());
        khuyenMai.setGhiChu(txtGhiChu.getText());
        khuyenMai.setLoaiKhuyenMai(dcbmLoaiKM.getSelectedItem().toString());
        //khuyenMai.setNhanVien(nhanV);
        khuyenMai.setNhanVien(nhanVienService.getOne("NV1"));
        khuyenMai.setTenKhuyenMai(txtTenKhuyenMai.getText());
        //Date.valueOf(dateFormat.format(date))
        khuyenMai.setThoiGianBD(Date.valueOf(dateFormat.format(txtThoiGianBatDau.getDate())));
        khuyenMai.setThoiGianKT(Date.valueOf(dateFormat.format(txtThoiGianKetThuc.getDate())));
        khuyenMai.setGiaTriKM(BigDecimal.valueOf(Double.valueOf(txtDonVi.getText())));
        if (radioDangApDung.isSelected()) {
            khuyenMai.setTrangThai(0);
        } else {
            khuyenMai.setTrangThai(1);
        }
        return khuyenMai;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelBorder1 = new com.raven.swing.PanelBorder();
        searchText1 = new com.raven.swing.SearchText();
        jLabel1 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbbLoaiKM = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtMaKM = new javax.swing.JTextField();
        txtDonVi = new javax.swing.JTextField();
        txtGhiChu = new javax.swing.JTextField();
        txtThoiGianBatDau = new com.toedter.calendar.JDateChooser();
        txtThoiGianKetThuc = new com.toedter.calendar.JDateChooser();
        txtTenKhuyenMai = new javax.swing.JTextField();
        radioDangApDung = new javax.swing.JRadioButton();
        radioNgungApDung = new javax.swing.JRadioButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbMonAn = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbKhuyenMai = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbKMCT = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
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
        jLabel5.setText("Mã KM                  :");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Thời gian bắt đầu :");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Loại Khuyến Mãi:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Thời gian kết thúc:");

        cbbLoaiKM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Tên Khuyến Mãi   :");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Đơn Vị                :");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Ghi Chú              :");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Trạng Thái          :");

        buttonGroup1.add(radioDangApDung);
        radioDangApDung.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        radioDangApDung.setText("Đang áp dụng");

        buttonGroup1.add(radioNgungApDung);
        radioNgungApDung.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        radioNgungApDung.setText("Ngừng áp dụng");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbbLoaiKM, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtThoiGianBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtThoiGianKetThuc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtTenKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(radioDangApDung)
                                        .addGap(18, 18, 18)
                                        .addComponent(radioNgungApDung))
                                    .addComponent(jLabel14)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtDonVi, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtGhiChu)))))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(cbbLoaiKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel12)
                            .addComponent(txtDonVi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtThoiGianBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtThoiGianKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel14)
                    .addComponent(txtTenKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioDangApDung)
                    .addComponent(radioNgungApDung))
                .addGap(8, 8, 8))
        );

        tbMonAn.setModel(new javax.swing.table.DefaultTableModel(
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
        tbMonAn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMonAnMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbMonAn);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Thiết lập thông tin khuyến mãi");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Món Ăn:");

        tbKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
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
        tbKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbKhuyenMai);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Khuyến Mãi");

        tbKMCT.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tbKMCT);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Áp Dụng Khuyến Mãi");

        jButton1.setBackground(new java.awt.Color(51, 255, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("Áp Dụng");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchText1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jButton9))
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder1Layout.createSequentialGroup()
                            .addComponent(btnAdd)
                            .addGap(83, 83, 83)
                            .addComponent(btnUpdate)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRemove)
                            .addGap(81, 81, 81)
                            .addComponent(btnClear))
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel3))
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addGroup(panelBorder1Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel9))
                .addGap(8, 8, 8)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel3))
                .addGap(5, 5, 5)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd)
                            .addComponent(btnUpdate)
                            .addComponent(btnRemove)
                            .addComponent(btnClear))
                        .addGap(0, 26, Short.MAX_VALUE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))))
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

    private void tbKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKhuyenMaiMouseClicked
        int index = tbKhuyenMai.getSelectedRow();
        fillKM(index, listKM);
        KhuyenMai khuyenMai = khuyenMaiService.getOne(txtMaKM.getText());
        listCTKM = monAnService.getMonAnByKhuyenMai(khuyenMai);
        showDataCTKM(listCTKM, 1);
    }//GEN-LAST:event_tbKhuyenMaiMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        KhuyenMai khuyenMai = newKM();
        JOptionPane.showMessageDialog(this, khuyenMaiService.add(khuyenMai));
        listKM = khuyenMaiService.getAll();
        showData(listKM, 1);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int index = tbKhuyenMai.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Chọn data!");
        } else {
            KhuyenMai khuyenMai = newKM();
            JOptionPane.showMessageDialog(this, khuyenMaiService.update(khuyenMai, txtMaKM.getText()));
            listKM = khuyenMaiService.getAll();
            showData(listKM, WIDTH);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        txtTenKhuyenMai.setText("");
        txtDonVi.setText("");
        dcbmLoaiKM.setSelectedItem("Phần trăm");
        txtThoiGianBatDau.setDate(Date.valueOf(dateFormat.format(today)));
        txtThoiGianKetThuc.setDate(Date.valueOf(dateFormat.format(today)));
        txtMaKM.setText("");
        txtMaKM.setEnabled(true);
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        int index = tbKhuyenMai.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Chọn data!");
        } else {
            JOptionPane.showMessageDialog(this, khuyenMaiService.remove(txtMaKM.getText()));
            listKM = khuyenMaiService.getAll();
            showData(listKM, 1);
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void tbMonAnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMonAnMouseClicked
        int index = tbMonAn.getSelectedRow();
        MonAn monAn = listMonAn.get(index);
        String maKM = txtMaKM.getText();
        if (maKM.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn KM!");
        } else if (monAn.getKhuyenMai() != null) {
            JOptionPane.showMessageDialog(this, "Sản phẩm này đã được áp dụng KM!");
        } else {
            listThemKM_MA.add(monAn);
            showDataCTKM(listThemKM_MA, 1);
        }
    }//GEN-LAST:event_tbMonAnMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String maKM = txtMaKM.getText();
        int szListThemKM_Ma = listThemKM_MA.size();
        if (maKM.isEmpty() || szListThemKM_Ma <= 0) {
            JOptionPane.showMessageDialog(this, "??");
        } else {
            KhuyenMai khuyenMai = khuyenMaiService.getOne(maKM);
            try {
                for (int i = 0; i < szListThemKM_Ma; i++) {
                    String maMA = listThemKM_MA.get(i).getMaMonAn();
                    String updateKM_MA = monAnService.themKMChoMonAn(khuyenMai, maMA);
                }
                listThemKM_MA.removeAll(listThemKM_MA);
                JOptionPane.showMessageDialog(this, "Áp dụng KM thành công!");
                showDataCTKM(listThemKM_MA, 1);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Áp dụng KM ko thành công!");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbLoaiKM;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private com.raven.swing.PanelBorder panelBorder1;
    private javax.swing.JRadioButton radioDangApDung;
    private javax.swing.JRadioButton radioNgungApDung;
    private com.raven.swing.SearchText searchText1;
    private javax.swing.JTable tbKMCT;
    private javax.swing.JTable tbKhuyenMai;
    private javax.swing.JTable tbMonAn;
    private javax.swing.JTextField txtDonVi;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtMaKM;
    private javax.swing.JTextField txtTenKhuyenMai;
    private com.toedter.calendar.JDateChooser txtThoiGianBatDau;
    private com.toedter.calendar.JDateChooser txtThoiGianKetThuc;
    // End of variables declaration//GEN-END:variables
}
