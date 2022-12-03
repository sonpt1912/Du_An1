/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.form;

import com.mycompany.customModel.SanPhamRepose;
import com.mycompany.domainModel.HoaDon;
import com.mycompany.domainModel.NhanVien;
import com.mycompany.service.impl.ThongKeService;
import java.awt.Color;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

public class Form_ThongKe extends javax.swing.JPanel {

    private ChartPanel charPanel = null;

    private DefaultTableModel dtmSP = new DefaultTableModel();
    private ThongKeService thongKeService = new ThongKeService();
    private List<SanPhamRepose> listSanPham = new ArrayList<>();
    private List<HoaDon> listHoaDon = new ArrayList<>();
    private DefaultComboBoxModel dcbmNgayThang = new DefaultComboBoxModel();
    private List<String> listTenNgayThang = new ArrayList<>();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private java.util.Date today = new java.util.Date();

    public Form_ThongKe(NhanVien nv) {
        initComponents();
        String headerSP[] = {"STT", "MaSP", "Tên SP", "Số Lượng"};
        txtNgayBatDau.setDate(Date.valueOf("2022-11-01"));
        txtNgayKetThuc.setDate(today);
        dtmSP.setColumnIdentifiers(headerSP);
        cbbNgayThang.setModel(dcbmNgayThang);
        cbbDate();
        // set enble
//        txtNgayBatDau.getJCalendar().setMaxSelectableDate(today);
//        JTextFieldDateEditor batDau = (JTextFieldDateEditor) txtNgayBatDau.getDateEditor();
//        txtNgayKetThuc.getJCalendar().setMaxSelectableDate(today);
//        JTextFieldDateEditor ketThuc = (JTextFieldDateEditor) txtNgayKetThuc.getDateEditor();

        //
        String ngayBatDau = dateFormat.format(txtNgayBatDau.getDate());
        String ngayKetThuc = dateFormat.format(txtNgayKetThuc.getDate());
        listSanPham = thongKeService.getAllSanPham(Date.valueOf(ngayBatDau), Date.valueOf(ngayKetThuc));
        listHoaDon = thongKeService.getAllHoaDon(0);

        initUIHoaDon6ThangDau(listHoaDon);
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
        btnUpdate1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        nameofmax = new javax.swing.JLabel();
        soLuongofmax = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
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
        cbbNgayThang = new javax.swing.JComboBox<>();
        chartHoaDon = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 255));
        setPreferredSize(new java.awt.Dimension(953, 595));

        jLabel1.setText("From :  ");

        jLabel6.setText("To :");

        btnUpdate1.setText("Search");
        btnUpdate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate1ActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        nameofmax.setText("jLabel7");

        soLuongofmax.setText("jLabel8");

        jLabel9.setText("Sản phẩm bán chạy nhất");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(nameofmax)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(soLuongofmax)
                .addGap(40, 40, 40))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(55, 55, 55))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameofmax)
                    .addComponent(soLuongofmax))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNgayBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                            .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate1)))
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(btnUpdate1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setToolTipText("");
        jPanel1.setPreferredSize(new java.awt.Dimension(203, 198));

        jLabel2.setBackground(new java.awt.Color(153, 255, 153));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tuần");

        lbDoanhThuWEEK.setFont(new java.awt.Font("Segoe UI", 1, 27)); // NOI18N
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
                        .addGap(22, 22, 22)
                        .addComponent(lbHoaDonDaThanhToanWEEK))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(lbHoaDonHuyWEEK))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDoanhThuWEEK)
                            .addComponent(lbHoaDonTongWEEK))))
                .addContainerGap(25, Short.MAX_VALUE))
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
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setPreferredSize(new java.awt.Dimension(203, 198));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tháng");

        lbDoanhThuMONTH.setFont(new java.awt.Font("Segoe UI", 1, 27)); // NOI18N
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
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setPreferredSize(new java.awt.Dimension(203, 198));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Năm");

        lbDoanhThuYEAR.setFont(new java.awt.Font("Segoe UI", 1, 27)); // NOI18N
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

        lbDoangThuDAY.setFont(new java.awt.Font("Segoe UI", 1, 27)); // NOI18N
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
                .addGap(54, 54, 54)
                .addComponent(jLabel4)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lbHoaDonHuyDAY)
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbHoaDonTongDAY)
                            .addComponent(lbDoangThuDAY))
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lbHoaDonDaThanhToanDAY)
                        .addGap(21, 21, 21))))
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
                .addGap(13, 13, 13)
                .addComponent(lbHoaDonDaThanhToanDAY)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbHoaDonHuyDAY)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        panelBorder2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbbNgayThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbNgayThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNgayThangActionPerformed(evt);
            }
        });
        panelBorder2.add(cbbNgayThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 10, 250, -1));

        chartHoaDon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        chartHoaDon.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel14.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        chartHoaDon.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        panelBorder2.add(chartHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 580, 280));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Thống kê doanh thu theo từng tháng ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnUpdate))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19)
                .addComponent(scrollBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
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
                    .addComponent(btnUpdate)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    public void cbbDate() {
         listTenNgayThang.add("Thống kê cả năm");
        listTenNgayThang.add("Thống kê 6 tháng đầu năm");
        listTenNgayThang.add("Thống kê 6 tháng cuối năm");
        for (String string : listTenNgayThang) {
            dcbmNgayThang.addElement(string);
        }
    }
    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        String ngayBatDau = dateFormat.format(txtNgayBatDau.getDate());
        String ngayKetThuc = dateFormat.format(txtNgayKetThuc.getDate());
        listSanPham = thongKeService.getAllSanPham(Date.valueOf(ngayBatDau), Date.valueOf(ngayKetThuc));
        initUIHoaDonCaNam(listHoaDon);
        showSoLuongDon();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void cbbNgayThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNgayThangActionPerformed
        // TODO add your handling code here:
         if (cbbNgayThang.getSelectedItem().toString().equals("Thống kê cả năm")) {
             initUIHoaDonCaNam(listHoaDon);
        }
        if (cbbNgayThang.getSelectedItem().toString().equals("Thống kê 6 tháng đầu năm")) {
            initUIHoaDon6ThangDau(listHoaDon);
        }
        if (cbbNgayThang.getSelectedItem().toString().equals("Thống kê 6 tháng cuối năm")) {
            initUIHoaDon6ThangCuoi();
        }
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
//            showDataSanPham(listSanPham, 1);

        }
    }//GEN-LAST:event_btnUpdate1ActionPerformed

//    private void showDataSanPham(List<SanPhamRepose> listSanPham, int stt) {
//        dtmSP.setRowCount(0);
//        for (SanPhamRepose monAn : listSanPham) {
//            dtmSP.addRow(monAn.toShowData(stt));
//            stt++;
//        }
//        lbDonHangHomNay1.setText(listSanPham.get(0).getTen());
//        lbDonHangHomNay2.setText(listSanPham.get(listSanPham.size()-1).getTen());
//    }
      private void initUIHoaDonCaNam(List<HoaDon> list) {
        CategoryDataset dataset = createDatasetHoaDon();

        JFreeChart chart = createChartHoaDon(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 20, 20));
        chartPanel.setBackground(Color.white);
        
        chartHoaDon.add(chartPanel, new AbsoluteConstraints(0, 0, 590, 300));
    }
    private void initUIHoaDon6ThangDau(List<HoaDon> list) {
        CategoryDataset dataset = createDatasetHoaDon6ThangDau();

        JFreeChart chart = createChartHoaDon(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 20, 20));
        chartPanel.setBackground(Color.white);
        chartHoaDon.add(chartPanel, new AbsoluteConstraints(0, 0, 590, 300));
    }

    private void initUIHoaDon6ThangCuoi() {
        CategoryDataset dataset = createDatasetHoaDon6ThangCuoi();

        JFreeChart chart = createChartHoaDon(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 20, 20));
        chartPanel.setBackground(Color.white);
        chartHoaDon.add(chartPanel, new AbsoluteConstraints(0, 0, 590, 300));
    }

    private CategoryDataset createDatasetHoaDon6ThangDau() {
        var dataset = new DefaultCategoryDataset();

        dataset.setValue(thongKeService.getDoanhThuThang1(), "Doanh Thu", "Tháng 1");
        dataset.setValue(thongKeService.getDoanhThuThang2(), "Doanh Thu", "Tháng 2");
        dataset.setValue(thongKeService.getDoanhThuThang3(), "Doanh Thu", "Tháng 3");
        dataset.setValue(thongKeService.getDoanhThuThang4(), "Doanh Thu", "Tháng 4");
        dataset.setValue(thongKeService.getDoanhThuThang5(), "Doanh Thu", "Tháng 5");
        dataset.setValue(thongKeService.getDoanhThuThang6(), "Doanh Thu", "Tháng 6");

        return dataset;
    }

    private CategoryDataset createDatasetHoaDon6ThangCuoi() {
        var dataset = new DefaultCategoryDataset();

        dataset.setValue(thongKeService.getDoanhThuThang7(), "Doanh Thu", "Tháng 7");
        dataset.setValue(thongKeService.getDoanhThuThang8(), "Doanh Thu", "Tháng 8");
        dataset.setValue(thongKeService.getDoanhThuThang9(), "Doanh Thu", "Tháng 9");
        dataset.setValue(thongKeService.getDoanhThuThang10(), "Doanh Thu", "Tháng 10");
        dataset.setValue(thongKeService.getDoanhThuThang11(), "Doanh Thu", "Tháng 11");
        dataset.setValue(thongKeService.getDoanhThuThang12(), "Doanh Thu", "Tháng 12");

        return dataset;
    }

    private CategoryDataset createDatasetHoaDon() {
        var dataset = new DefaultCategoryDataset();
        dataset.setValue(thongKeService.getDoanhThuThang1(), "Doanh Thu", " 1");
        dataset.setValue(thongKeService.getDoanhThuThang2(), "Doanh Thu", " 2");
        dataset.setValue(thongKeService.getDoanhThuThang3(), "Doanh Thu", " 3");
        dataset.setValue(thongKeService.getDoanhThuThang4(), "Doanh Thu", " 4");
        dataset.setValue(thongKeService.getDoanhThuThang5(), "Doanh Thu", " 5");
        dataset.setValue(thongKeService.getDoanhThuThang6(), "Doanh Thu", " 6");
        dataset.setValue(thongKeService.getDoanhThuThang7(), "Doanh Thu", " 7");
        dataset.setValue(thongKeService.getDoanhThuThang8(), "Doanh Thu", " 8");
        dataset.setValue(thongKeService.getDoanhThuThang9(), "Doanh Thu", " 9");
        dataset.setValue(thongKeService.getDoanhThuThang10(), "Doanh Thu", " 10");
        dataset.setValue(thongKeService.getDoanhThuThang11(), "Doanh Thu", " 11");
        dataset.setValue(thongKeService.getDoanhThuThang12(), "Doanh Thu", " 12");

        return dataset;
    }

    private JFreeChart createChartHoaDon(CategoryDataset dataset) {

        JFreeChart barChart = ChartFactory.createBarChart(
                "BIỂU ĐỒ THỐNG KÊ HÓA ĐƠN",
                "",
                "Doanh thu",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);
        return barChart;
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
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdate1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbbNgayThang;
    private javax.swing.JPanel chartHoaDon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lbDoangThuDAY;
    private javax.swing.JLabel lbDoanhThuMONTH;
    private javax.swing.JLabel lbDoanhThuWEEK;
    private javax.swing.JLabel lbDoanhThuYEAR;
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
    private javax.swing.JLabel nameofmax;
    private com.raven.swing.PanelBorder panelBorder1;
    private com.raven.swing.PanelBorder panelBorder2;
    private com.raven.swing.ScrollBar scrollBar1;
    private javax.swing.JLabel soLuongofmax;
    private com.toedter.calendar.JDateChooser txtNgayBatDau;
    private com.toedter.calendar.JDateChooser txtNgayKetThuc;
    // End of variables declaration//GEN-END:variables
}
