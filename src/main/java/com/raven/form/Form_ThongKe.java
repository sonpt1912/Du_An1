/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.form;

import com.mycompany.customModel.SanPhamRepose;
import com.mycompany.domainModel.MonAn;
import com.mycompany.domainModel.NhanVien;
import com.mycompany.service.impl.ThongKeService;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Form_ThongKe extends javax.swing.JPanel {

    private DefaultTableModel dtmSP = new DefaultTableModel();
    private ThongKeService thongKeService = new ThongKeService();
    private List<SanPhamRepose> listSanPham = new ArrayList<>();
    private DefaultComboBoxModel dcbmNgayThang = new DefaultComboBoxModel();
    private List<String> listTenNgayThang = new ArrayList<>();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yyyy");
    private java.util.Date today = new java.util.Date();

    public Form_ThongKe(NhanVien nv) {
        initComponents();
        String headerSP[] = {"STT", "MaSP", "Tên SP", "Số Lượng"};
        tbSanPham.setModel(dtmSP);
        txtNgayBatDau.setDate(Date.valueOf("2022-11-01"));
        txtNgayKetThuc.setDate(today);
        dtmSP.setColumnIdentifiers(headerSP);
        cbbNgayThang.setModel(dcbmNgayThang);
        cbbDate();
        String ngayBatDau = dateFormat.format(txtNgayBatDau.getDate());
        String ngayKetThuc = dateFormat.format(txtNgayKetThuc.getDate());
        listSanPham = thongKeService.getAllSanPham(Date.valueOf(ngayBatDau), Date.valueOf(ngayKetThuc));
        showDataSanPham(listSanPham, 1);
        showSoLuongDon();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        scrollBar1 = new com.raven.swing.ScrollBar();
        panelBorder1 = new com.raven.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        txtNgayBatDau = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        txtNgayKetThuc = new com.toedter.calendar.JDateChooser();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lbDonHangHomNay1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        lbDonHangHomNay2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnUpdate1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbDoanhThuWEEK = new javax.swing.JLabel();
        lbHoaDonTongWEEK = new javax.swing.JLabel();
        lbHoaDonDaThanhToanWEEK = new javax.swing.JLabel();
        lbHoaDonHuyWEEK = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lbDoanhThuMONTH = new javax.swing.JLabel();
        lbHoaDonTongMONTH = new javax.swing.JLabel();
        lbHoaDonDaThanhToanMONTH = new javax.swing.JLabel();
        lbHoaDonHuyMONTH = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lbDoanhThuYEAR = new javax.swing.JLabel();
        lbHoaDonTongYEAR = new javax.swing.JLabel();
        lbHoaDonDaThanhToanYEAR = new javax.swing.JLabel();
        lbHoaDonHuyYEAR = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lbDoangThuDAY = new javax.swing.JLabel();
        lbHoaDonTongDAY = new javax.swing.JLabel();
        lbHoaDonDaThanhToanDAY = new javax.swing.JLabel();
        lbHoaDonHuyDAY = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        panelBorder2 = new com.raven.swing.PanelBorder();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable();
        rdTatCaHoaDon = new javax.swing.JRadioButton();
        rdDaThanhToanHoaDon = new javax.swing.JRadioButton();
        rdDaHuyHoaDon = new javax.swing.JRadioButton();
        cbbNgayThang = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        btnBieuDo = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 255));
        setPreferredSize(new java.awt.Dimension(953, 595));

        jLabel1.setText("From :  ");

        jLabel6.setText("To :");

        jPanel5.setBackground(new java.awt.Color(186, 123, 247));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setPreferredSize(new java.awt.Dimension(203, 198));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Đơn hàng bán chạy nhất  ");

        lbDonHangHomNay1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbDonHangHomNay1.setForeground(new java.awt.Color(255, 0, 0));
        lbDonHangHomNay1.setText("1");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 2, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tên Sản Phẩm");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(58, 58, 58)
                        .addComponent(lbDonHangHomNay1)
                        .addContainerGap(140, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(82, 82, 82))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDonHangHomNay1)
                    .addComponent(jLabel8))
                .addGap(13, 13, 13)
                .addComponent(jLabel7)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(186, 123, 247));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setPreferredSize(new java.awt.Dimension(203, 198));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Đơn hàng bán ít nhất nhất  ");

        lbDonHangHomNay2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbDonHangHomNay2.setForeground(new java.awt.Color(255, 0, 0));
        lbDonHangHomNay2.setText("1");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 2, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Tên Sản phẩm");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap(101, Short.MAX_VALUE)
                        .addComponent(jLabel10))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel12)
                        .addGap(58, 58, 58)
                        .addComponent(lbDonHangHomNay2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(68, 68, 68))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDonHangHomNay2)
                    .addComponent(jLabel12))
                .addGap(13, 13, 13)
                .addComponent(jLabel10)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        btnUpdate1.setText("Search");
        btnUpdate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdate1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(btnUpdate1))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setToolTipText("");
        jPanel1.setPreferredSize(new java.awt.Dimension(203, 198));

        jLabel2.setBackground(new java.awt.Color(153, 255, 153));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tuần");

        lbDoanhThuWEEK.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lbDoanhThuWEEK.setText("100.000 đ");

        lbHoaDonTongWEEK.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbHoaDonTongWEEK.setText("Tổng số hóa đơn : 8");

        lbHoaDonDaThanhToanWEEK.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbHoaDonDaThanhToanWEEK.setText("Hóa đơn đã thanh toán : 8");

        lbHoaDonHuyWEEK.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbHoaDonHuyWEEK.setText("Hóa đơn đã hủy : 8");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDoanhThuWEEK)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lbHoaDonTongWEEK))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(lbHoaDonDaThanhToanWEEK))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(lbHoaDonHuyWEEK)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDoanhThuWEEK)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbHoaDonTongWEEK)
                .addGap(13, 13, 13)
                .addComponent(lbHoaDonDaThanhToanWEEK)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbHoaDonHuyWEEK)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setPreferredSize(new java.awt.Dimension(203, 198));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tháng");

        lbDoanhThuMONTH.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lbDoanhThuMONTH.setText("100.000 đ");

        lbHoaDonTongMONTH.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbHoaDonTongMONTH.setText("Tổng số hóa đơn : 8");

        lbHoaDonDaThanhToanMONTH.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbHoaDonDaThanhToanMONTH.setText("Hóa đơn đã thanh toán : 8");

        lbHoaDonHuyMONTH.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbHoaDonHuyMONTH.setText("Hóa đơn đã hủy : 8");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(lbDoanhThuMONTH))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lbHoaDonTongMONTH)
                                .addGap(15, 15, 15))
                            .addComponent(lbHoaDonDaThanhToanMONTH)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(lbHoaDonHuyMONTH)))
                .addGap(39, 39, 39))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDoanhThuMONTH)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbHoaDonTongMONTH)
                .addGap(10, 10, 10)
                .addComponent(lbHoaDonDaThanhToanMONTH)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbHoaDonHuyMONTH)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setPreferredSize(new java.awt.Dimension(203, 198));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Năm");

        lbDoanhThuYEAR.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lbDoanhThuYEAR.setText("100.000 đ");

        lbHoaDonTongYEAR.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbHoaDonTongYEAR.setText("Tổng số hóa đơn : 8");

        lbHoaDonDaThanhToanYEAR.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbHoaDonDaThanhToanYEAR.setText("Hóa đơn đã thanh toán : 8");

        lbHoaDonHuyYEAR.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbHoaDonHuyYEAR.setText("Hóa đơn đã hủy : 8");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(76, 76, 76))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lbHoaDonTongYEAR)
                                .addGap(23, 23, 23))
                            .addComponent(lbHoaDonDaThanhToanYEAR))
                        .addGap(23, 23, 23))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(lbDoanhThuYEAR))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(lbHoaDonHuyYEAR)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDoanhThuYEAR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbHoaDonTongYEAR)
                .addGap(10, 10, 10)
                .addComponent(lbHoaDonDaThanhToanYEAR)
                .addGap(10, 10, 10)
                .addComponent(lbHoaDonHuyYEAR)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(153, 153, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setPreferredSize(new java.awt.Dimension(203, 198));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Hôm nay");

        lbDoangThuDAY.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lbDoangThuDAY.setText("100.000 đ");

        lbHoaDonTongDAY.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbHoaDonTongDAY.setText("Tổng số hóa đơn : 8");

        lbHoaDonDaThanhToanDAY.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbHoaDonDaThanhToanDAY.setText("Hóa đơn đã thanh toán : 8");

        lbHoaDonHuyDAY.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbHoaDonHuyDAY.setText("Hóa đơn đã hủy : 8");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lbHoaDonHuyDAY)
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lbHoaDonDaThanhToanDAY)
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbHoaDonTongDAY)
                            .addComponent(lbDoangThuDAY))
                        .addGap(30, 30, 30))))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel4)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDoangThuDAY)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbHoaDonTongDAY)
                .addGap(10, 10, 10)
                .addComponent(lbHoaDonDaThanhToanDAY)
                .addGap(10, 10, 10)
                .addComponent(lbHoaDonHuyDAY)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        tbSanPham.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tbSanPham);

        buttonGroup2.add(rdTatCaHoaDon);
        rdTatCaHoaDon.setSelected(true);
        rdTatCaHoaDon.setText("Tất cả ");

        buttonGroup2.add(rdDaThanhToanHoaDon);
        rdDaThanhToanHoaDon.setText("Đã thanh toán");

        buttonGroup2.add(rdDaHuyHoaDon);
        rdDaHuyHoaDon.setText("Đã hủy");

        cbbNgayThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbNgayThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNgayThangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addComponent(cbbNgayThang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdTatCaHoaDon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdDaThanhToanHoaDon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdDaHuyHoaDon)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdTatCaHoaDon)
                    .addComponent(rdDaThanhToanHoaDon)
                    .addComponent(rdDaHuyHoaDon)
                    .addComponent(cbbNgayThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Thống kê sản phẩm ");

        btnBieuDo.setText("Biểu Đồ");
        btnBieuDo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBieuDoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel11))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
                            .addComponent(panelBorder1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnBieuDo)
                                .addGap(31, 31, 31)
                                .addComponent(btnUpdate)))))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(scrollBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrollBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(527, 527, 527))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnUpdate)
                        .addComponent(btnBieuDo))
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    public void cbbDate() {
        listTenNgayThang.add("Hóa đơn theo ngày");
        listTenNgayThang.add("Hóa đơn theo tháng");
        for (String string : listTenNgayThang) {
            dcbmNgayThang.addElement(string);
        }
    }
    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        String ngayBatDau = dateFormat.format(txtNgayBatDau.getDate());
        String ngayKetThuc = dateFormat.format(txtNgayKetThuc.getDate());
        listSanPham = thongKeService.getAllSanPham(Date.valueOf(ngayBatDau), Date.valueOf(ngayKetThuc));
        showDataSanPham(listSanPham, 1);
        showSoLuongDon();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void cbbNgayThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNgayThangActionPerformed
        // TODO add your handling code here:
//        if (cbbNgayThang.getSelectedItem().toString().equals("Hóa đơn theo ngày")) {
//            listHoaDon = thongKeService.getAllDay();
//            showDataHoaDon(listHoaDon, 1);
//        }
//        if (cbbNgayThang.getSelectedItem().toString().equals("Hóa đơn theo tháng")) {
//            listHoaDon = thongKeService.getAllMonth();
//            showDataHoaDon(listHoaDon, 1);
//        }
    }//GEN-LAST:event_cbbNgayThangActionPerformed

    private void btnUpdate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate1ActionPerformed
        // TODO add your handling code here:
        if (txtNgayBatDau.getDate() == null) {
            JOptionPane.showMessageDialog(this, "không được để trống ngày bắt đầu");
        } else if (txtNgayKetThuc.getDate() == null) {
            JOptionPane.showMessageDialog(this, "không được để trống ngày kêt thúc");
        } else if (txtNgayBatDau.getDate().compareTo(today) > 0) {
            JOptionPane.showMessageDialog(this, "ngày bắt đầu phải nhỏ hơn ngày hiện tại");
        } else if (txtNgayKetThuc.getDate().compareTo(today) > 0) {
            JOptionPane.showMessageDialog(this, "ngày kêt thúc phải nhỏ hơn ngày hiện tại");
        } else if (txtNgayBatDau.getDate().compareTo(txtNgayKetThuc.getDate()) > 0) {
            JOptionPane.showMessageDialog(this, "ngày bắt đầu phải nhỏ hoặc bằng ngày kết thúc");
        } else {
            String ngayBatDau = dateFormat.format(txtNgayBatDau.getDate());
            String ngayKetThuc = dateFormat.format(txtNgayKetThuc.getDate());
            listSanPham = thongKeService.getAllSanPham(Date.valueOf(ngayBatDau), Date.valueOf(ngayKetThuc));
            showDataSanPham(listSanPham, 1);
        }
    }//GEN-LAST:event_btnUpdate1ActionPerformed

    private void btnBieuDoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBieuDoActionPerformed
        // TODO add your handling code here:
        JDialogBieuDo bieuDo = new JDialogBieuDo(listSanPham);
        bieuDo.setVisible(true);
    }//GEN-LAST:event_btnBieuDoActionPerformed

    private void showDataSanPham(List<SanPhamRepose> listSanPham, int stt) {
        dtmSP.setRowCount(0);
        for (SanPhamRepose monAn : listSanPham) {
            dtmSP.addRow(monAn.toShowData(stt));
            stt++;
        }
        if (listSanPham.size() > 0) {
            lbDonHangHomNay1.setText(listSanPham.get(0).getTen());
            if (listSanPham.size() > 1) {
                lbDonHangHomNay2.setText(listSanPham.get(listSanPham.size() - 1).getTen());
            }
        }
    }

    private void showSoLuongDon() {
//        Tổng số hóa đơn
        lbHoaDonTongDAY.setText("Tổng số hóa đơn: " + (String.valueOf(thongKeService.getCountAllDay())));
        lbHoaDonTongWEEK.setText("Tổng số hóa đơn: " + (String.valueOf(thongKeService.getCountAllWeek())));
        lbHoaDonTongMONTH.setText("Tổng số hóa đơn: " + String.valueOf(thongKeService.getCountAllMonth()));
        lbHoaDonTongYEAR.setText("Tổng số hóa đơn: " + (String.valueOf(thongKeService.getCountAllYear())));
//        Đã thanh toán
        lbHoaDonDaThanhToanDAY.setText("Hóa đơn đã thanh toán: " + (String.valueOf(thongKeService.getHoaDonDaTTDAY())));
        lbHoaDonDaThanhToanWEEK.setText("Hóa đơn đã thanh toán: " + (String.valueOf(thongKeService.getHoaDonDaTTWEEK())));
        lbHoaDonDaThanhToanMONTH.setText("Hóa đơn đã thanh toán: " + (String.valueOf(thongKeService.getHoaDonDaTTMONTH())));
        lbHoaDonDaThanhToanYEAR.setText("Hóa đơn đã thanh toán: " + (String.valueOf(thongKeService.getHoaDonDaTTYEAR())));
//        Đã hủy
        lbHoaDonHuyDAY.setText("Hóa đơn đã húy: " + (String.valueOf(thongKeService.getHoaDonHuyDAY())));
        lbHoaDonHuyWEEK.setText("Hóa đơn đã húy: " + (String.valueOf(thongKeService.getHoaDonHuyWEEK())));
        lbHoaDonHuyMONTH.setText("Hóa đơn đã húy: " + (String.valueOf(thongKeService.getHoaDonHuyMONTH())));
        lbHoaDonHuyYEAR.setText("Hóa đơn đã húy: " + (String.valueOf(thongKeService.getHoaDonHuyYEAR())));
//        Doanh Thu
        if (thongKeService.getDoanhThuDAY() == null) {
            lbDoangThuDAY.setText(String.valueOf("      " + 0 + " đ"));
        } else {
            lbDoangThuDAY.setText(String.valueOf(thongKeService.getDoanhThuDAY() + " đ"));
        }
        if (thongKeService.getDoanhThuWEEK() == null) {
            lbDoanhThuWEEK.setText(String.valueOf("      " + 0 + " đ"));
        } else {
            lbDoanhThuWEEK.setText(String.valueOf(thongKeService.getDoanhThuWEEK() + " đ"));
        }
        if (thongKeService.getDoanhThuMONTH() == null) {
            lbDoanhThuMONTH.setText(String.valueOf("      " + 0 + " đ"));
        } else {
            lbDoanhThuMONTH.setText(String.valueOf(thongKeService.getDoanhThuMONTH() + " đ"));
        }
        if (thongKeService.getDoanhThuYEAR() == null) {
            lbDoanhThuYEAR.setText(String.valueOf("      " + 0 + " đ"));
        } else {
            lbDoanhThuYEAR.setText(String.valueOf(thongKeService.getDoanhThuYEAR() + " đ"));
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBieuDo;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdate1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbbNgayThang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbDoangThuDAY;
    private javax.swing.JLabel lbDoanhThuMONTH;
    private javax.swing.JLabel lbDoanhThuWEEK;
    private javax.swing.JLabel lbDoanhThuYEAR;
    private javax.swing.JLabel lbDonHangHomNay1;
    private javax.swing.JLabel lbDonHangHomNay2;
    private javax.swing.JLabel lbHoaDonDaThanhToanDAY;
    private javax.swing.JLabel lbHoaDonDaThanhToanMONTH;
    private javax.swing.JLabel lbHoaDonDaThanhToanWEEK;
    private javax.swing.JLabel lbHoaDonDaThanhToanYEAR;
    private javax.swing.JLabel lbHoaDonHuyDAY;
    private javax.swing.JLabel lbHoaDonHuyMONTH;
    private javax.swing.JLabel lbHoaDonHuyWEEK;
    private javax.swing.JLabel lbHoaDonHuyYEAR;
    private javax.swing.JLabel lbHoaDonTongDAY;
    private javax.swing.JLabel lbHoaDonTongMONTH;
    private javax.swing.JLabel lbHoaDonTongWEEK;
    private javax.swing.JLabel lbHoaDonTongYEAR;
    private com.raven.swing.PanelBorder panelBorder1;
    private com.raven.swing.PanelBorder panelBorder2;
    private javax.swing.JRadioButton rdDaHuyHoaDon;
    private javax.swing.JRadioButton rdDaThanhToanHoaDon;
    private javax.swing.JRadioButton rdTatCaHoaDon;
    private com.raven.swing.ScrollBar scrollBar1;
    private javax.swing.JTable tbSanPham;
    private com.toedter.calendar.JDateChooser txtNgayBatDau;
    private com.toedter.calendar.JDateChooser txtNgayKetThuc;
    // End of variables declaration//GEN-END:variables
}
