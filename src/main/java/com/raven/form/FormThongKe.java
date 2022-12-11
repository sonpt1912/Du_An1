/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.form;

import com.mycompany.customModel.ExcelDayWeekMonthYear;
import com.mycompany.customModel.ExcelReponse;
import com.mycompany.customModel.SanPhamRepose;
import com.mycompany.domainModel.HoaDon;
import com.mycompany.domainModel.NhanVien;
import com.mycompany.service.impl.ThongKeService;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import static org.apache.poi.ss.usermodel.CellStyle.SOLID_FOREGROUND;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

public class FormThongKe extends javax.swing.JPanel {

    private ChartPanel charPanel = null;

    private DefaultTableModel dtmSP = new DefaultTableModel();
    private ThongKeService thongKeService = new ThongKeService();
    private List<SanPhamRepose> listSanPham = new ArrayList<>();
    private List<HoaDon> listHoaDon = new ArrayList<>();
    private DefaultComboBoxModel dcbmNgayThang = new DefaultComboBoxModel();
    private List<String> listTenNgayThang = new ArrayList<>();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private java.util.Date today = new java.util.Date();

    public FormThongKe(NhanVien nv) {
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

        String batDau = dateFormat.format(today);
        String ketThu = dateFormat.format(today);
        listSanPham = thongKeService.getAllSanPham(Date.valueOf(batDau), Date.valueOf(ketThu));
//            soLuongofmax.setText(listSanPham.get(0).getTen());
        setTKSP(listSanPham);
        jLabel7.setText(thongKeService.soLuongTheoKhoangNgay(Date.valueOf(ngayBatDau), Date.valueOf(ngayKetThuc)) + "đ");

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
        btnSearch = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        nameofmax = new javax.swing.JLabel();
        soLuongofmax = new javax.swing.JLabel();
        nameofmax1 = new javax.swing.JLabel();
        soLuongMin = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        nameofmax2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbDoanhThuWEEK = new javax.swing.JLabel();
        lbHoaDonDaThanhToanWEEK = new javax.swing.JLabel();
        lbHoaDonHuyWEEK = new javax.swing.JLabel();
        lbSoLuongKhachHangWEEK = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lbDoanhThuMONTH = new javax.swing.JLabel();
        lbHoaDonDaThanhToanMONTH = new javax.swing.JLabel();
        lbHoaDonHuyMONTH = new javax.swing.JLabel();
        lbSoLuongKhachHangMONTH = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lbDoanhThuYEAR = new javax.swing.JLabel();
        lbHoaDonDaThanhToanYEAR = new javax.swing.JLabel();
        lbHoaDonHuyYEAR = new javax.swing.JLabel();
        lbSoLuongKhachHangYEAR = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lbDoangThuDAY = new javax.swing.JLabel();
        lbHoaDonDaThanhToanDAY = new javax.swing.JLabel();
        lbHoaDonHuyDAY = new javax.swing.JLabel();
        lbSoLuongKhachDAY = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        panelBorder2 = new com.raven.swing.PanelBorder();
        cbbNgayThang = new javax.swing.JComboBox<>();
        chartHoaDon = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        ExportExcel = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 255));
        setPreferredSize(new java.awt.Dimension(953, 595));

        jLabel1.setText("From :  ");

        jLabel6.setText("To :");

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        nameofmax.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        nameofmax.setText("Sản phẩm bán chạy nhất: ");

        soLuongofmax.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        soLuongofmax.setText("jLabel8");

        nameofmax1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        nameofmax1.setText("sản phẩm bán ế nhất: ");

        soLuongMin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        soLuongMin.setText("jLabel7");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(nameofmax)
                        .addGap(18, 18, 18)
                        .addComponent(soLuongofmax))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(nameofmax1)
                        .addGap(18, 18, 18)
                        .addComponent(soLuongMin)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameofmax)
                    .addComponent(soLuongofmax))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameofmax1)
                    .addComponent(soLuongMin))
                .addGap(87, 87, 87))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        nameofmax2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        nameofmax2.setText("Tổng tiền của ngày là");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("jLabel7");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(nameofmax2))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(jLabel7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameofmax2)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
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
                        .addComponent(btnSearch)))
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
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("TUẦN ");

        lbDoanhThuWEEK.setFont(new java.awt.Font("Segoe UI", 1, 29)); // NOI18N
        lbDoanhThuWEEK.setText("100.000 đ");

        lbHoaDonDaThanhToanWEEK.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbHoaDonDaThanhToanWEEK.setText("Đã thanh toán : 8");

        lbHoaDonHuyWEEK.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbHoaDonHuyWEEK.setText("Đã hủy : 8");

        lbSoLuongKhachHangWEEK.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbSoLuongKhachHangWEEK.setText("Số lượng khách hàng : 8");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbHoaDonDaThanhToanWEEK)
                    .addComponent(lbSoLuongKhachHangWEEK)
                    .addComponent(lbHoaDonHuyWEEK)
                    .addComponent(jLabel2)
                    .addComponent(lbDoanhThuWEEK))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lbDoanhThuWEEK)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbSoLuongKhachHangWEEK)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbHoaDonDaThanhToanWEEK)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbHoaDonHuyWEEK)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setPreferredSize(new java.awt.Dimension(203, 198));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("THÁNG");

        lbDoanhThuMONTH.setFont(new java.awt.Font("Segoe UI", 1, 29)); // NOI18N
        lbDoanhThuMONTH.setText("100.000 đ");

        lbHoaDonDaThanhToanMONTH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbHoaDonDaThanhToanMONTH.setText("Đã thanh toán : 8");

        lbHoaDonHuyMONTH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbHoaDonHuyMONTH.setText("Đã hủy : 8");

        lbSoLuongKhachHangMONTH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbSoLuongKhachHangMONTH.setText("Số lượng khách hàng : 8");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbSoLuongKhachHangMONTH)
                            .addComponent(lbDoanhThuMONTH))
                        .addGap(24, 24, 24))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbHoaDonHuyMONTH)
                                    .addComponent(lbHoaDonDaThanhToanMONTH)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel3)))
                        .addGap(65, 65, 65)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(lbDoanhThuMONTH)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbSoLuongKhachHangMONTH)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbHoaDonDaThanhToanMONTH)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbHoaDonHuyMONTH)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setPreferredSize(new java.awt.Dimension(203, 198));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("NĂM");

        lbDoanhThuYEAR.setFont(new java.awt.Font("Segoe UI", 1, 29)); // NOI18N
        lbDoanhThuYEAR.setText("100.000 đ");

        lbHoaDonDaThanhToanYEAR.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbHoaDonDaThanhToanYEAR.setText("Đã thanh toán : 8");

        lbHoaDonHuyYEAR.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbHoaDonHuyYEAR.setText("Đã hủy : 8");

        lbSoLuongKhachHangYEAR.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbSoLuongKhachHangYEAR.setText("Số lượng khách hàng : 8");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbHoaDonDaThanhToanYEAR)
                            .addComponent(lbSoLuongKhachHangYEAR)
                            .addComponent(lbHoaDonHuyYEAR)
                            .addComponent(lbDoanhThuYEAR))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(lbDoanhThuYEAR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbSoLuongKhachHangYEAR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbHoaDonDaThanhToanYEAR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbHoaDonHuyYEAR)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(153, 153, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setPreferredSize(new java.awt.Dimension(203, 198));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("HÔM NAY");

        lbDoangThuDAY.setFont(new java.awt.Font("Segoe UI", 1, 29)); // NOI18N
        lbDoangThuDAY.setText("100.000 đ");

        lbHoaDonDaThanhToanDAY.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbHoaDonDaThanhToanDAY.setText("Đã thanh toán : 8");

        lbHoaDonHuyDAY.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbHoaDonHuyDAY.setText("Đã hủy : 8");

        lbSoLuongKhachDAY.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbSoLuongKhachDAY.setText("Số lượng khách hàng : 8");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(lbSoLuongKhachDAY)
                    .addComponent(lbDoangThuDAY)
                    .addComponent(lbHoaDonDaThanhToanDAY)
                    .addComponent(lbHoaDonHuyDAY))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(13, 13, 13)
                .addComponent(lbDoangThuDAY)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbSoLuongKhachDAY)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbHoaDonDaThanhToanDAY)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbHoaDonHuyDAY)
                .addContainerGap())
        );

        btnUpdate.setBackground(new java.awt.Color(255, 255, 153));
        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        panelBorder2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbbNgayThang.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbbNgayThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbNgayThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNgayThangActionPerformed(evt);
            }
        });
        panelBorder2.add(cbbNgayThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 250, 30));

        chartHoaDon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        chartHoaDon.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel14.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        chartHoaDon.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        panelBorder2.add(chartHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 780, 400));

        ExportExcel.setText("ExportExcel");
        ExportExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportExcelActionPerformed(evt);
            }
        });
        panelBorder2.add(ExportExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, 110, 30));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Thống kê doanh thu theo từng tháng ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(49, 49, 49)
                        .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(90, 90, 90)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17)))))
                .addGap(18, 18, 18)
                .addComponent(scrollBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(215, Short.MAX_VALUE)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(btnUpdate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
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

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
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
            setTKSP(listSanPham);
            System.out.println("phạm sơn");
            System.out.println(thongKeService.soLuongTheoKhoangNgay(Date.valueOf(ngayBatDau), Date.valueOf(ngayKetThuc)) + "đ");
            jLabel7.setText(thongKeService.soLuongTheoKhoangNgay(Date.valueOf(ngayBatDau), Date.valueOf(ngayKetThuc)) + "đ");
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void ExportExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportExcelActionPerformed
        // TODO add your handling code here:
        try {
            writeExcelbieuDo();
//            writeExcelDWMY();
            JOptionPane.showMessageDialog(this, "Export thành công rùi nhé heheee");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Âu nâu export thất bại rùi", "", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_ExportExcelActionPerformed

    private void setTKSP(List<SanPhamRepose> list) {
        if (list.size() > 0) {
            soLuongofmax.setText(listSanPham.get(0).getTen());
            if (list.size() > 1) {
                soLuongMin.setText(listSanPham.get(list.size() - 1).getTen());
            }
        }
    }
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

        chartHoaDon.add(chartPanel, new AbsoluteConstraints(0, 0, 790, 450));
    }

    private void initUIHoaDon6ThangDau(List<HoaDon> list) {
        CategoryDataset dataset = createDatasetHoaDon6ThangDau();

        JFreeChart chart = createChartHoaDon(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 20, 20));
        chartPanel.setBackground(Color.white);
        chartHoaDon.add(chartPanel, new AbsoluteConstraints(0, 0, 790, 450));
    }

    private void initUIHoaDon6ThangCuoi() {
        CategoryDataset dataset = createDatasetHoaDon6ThangCuoi();

        JFreeChart chart = createChartHoaDon(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 20, 20));
        chartPanel.setBackground(Color.white);
        chartHoaDon.add(chartPanel, new AbsoluteConstraints(0, 0, 790, 450));
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
                "BIỂU ĐỒ THỐNG KÊ DOANH THU",
                "",
                "Doanh thu",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);
        return barChart;
    }

    private void showSoLuongDon() {
//        Tổng số hóa đơn
//        lbHoaDonTongDAY.setText("Tổng số hóa đơn: " + (String.valueOf(thongKeService.getCountAllDay())));
//        lbHoaDonTongWEEK.setText("Tổng số hóa đơn: " + (String.valueOf(thongKeService.getCountAllWeek())));
//        lbHoaDonTongMONTH.setText("Tổng số hóa đơn: " + String.valueOf(thongKeService.getCountAllMonth()));
//        lbHoaDonTongYEAR.setText("Tổng số hóa đơn: " + (String.valueOf(thongKeService.getCountAllYear())));
//        Đã thanh toán
        lbHoaDonDaThanhToanDAY.setText("Đã thanh toán: " + (String.valueOf(thongKeService.getHoaDonDaTTDAY())));
        lbHoaDonDaThanhToanWEEK.setText("Đã thanh toán: " + (String.valueOf(thongKeService.getHoaDonDaTTWEEK())));
        lbHoaDonDaThanhToanMONTH.setText("Đã thanh toán: " + (String.valueOf(thongKeService.getHoaDonDaTTMONTH())));
        lbHoaDonDaThanhToanYEAR.setText("Đã thanh toán: " + (String.valueOf(thongKeService.getHoaDonDaTTYEAR())));
//        Đã hủy
        lbHoaDonHuyDAY.setText("Đã húy: " + (String.valueOf(thongKeService.getHoaDonHuyDAY())));
        lbHoaDonHuyWEEK.setText("Đã húy: " + (String.valueOf(thongKeService.getHoaDonHuyWEEK())));
        lbHoaDonHuyMONTH.setText("Đã húy: " + (String.valueOf(thongKeService.getHoaDonHuyMONTH())));
        lbHoaDonHuyYEAR.setText("Đã húy: " + (String.valueOf(thongKeService.getHoaDonHuyYEAR())));
//        Doanh Thu
        if (thongKeService.getDoanhThuDAY() == null) {
            lbDoangThuDAY.setText(String.valueOf(0 + " đ"));
        } else {
            lbDoangThuDAY.setText(String.valueOf(thongKeService.getDoanhThuDAY() + " đ"));
        }
        if (thongKeService.getDoanhThuWEEK() == null) {
            lbDoanhThuWEEK.setText(String.valueOf(0 + " đ"));
        } else {
            lbDoanhThuWEEK.setText(String.valueOf(thongKeService.getDoanhThuWEEK() + " đ"));
        }
        if (thongKeService.getDoanhThuMONTH() == null) {
            lbDoanhThuMONTH.setText(String.valueOf(0 + " đ"));
        } else {
            lbDoanhThuMONTH.setText(String.valueOf(thongKeService.getDoanhThuMONTH() + " đ"));
        }
        if (thongKeService.getDoanhThuYEAR() == null) {
            lbDoanhThuYEAR.setText(String.valueOf(0 + " đ"));
        } else {
            lbDoanhThuYEAR.setText(String.valueOf(thongKeService.getDoanhThuYEAR() + " đ"));
        }
// Số lượng khách
        lbSoLuongKhachDAY.setText("Số lượng khách hàng :" + thongKeService.soLuongKhachHangDAY());
        lbSoLuongKhachHangWEEK.setText("Số lượng khách hàng :" + thongKeService.soLuongKhachHangWEEK());
        lbSoLuongKhachHangMONTH.setText("Số lượng khách hàng :" + thongKeService.soLuongKhachHangMONTH());
        lbSoLuongKhachHangYEAR.setText("Số lượng khách hàng :" + thongKeService.soLuongKhachHangYEAR());
    }

    private void writeExcelbieuDo() {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("ThongKe");
            String columnHeader[] = {"Tháng", "Doanh thu", "Tháng", "Doanh thu"};
            String columnDayWeekMonthYear[] = {"Thời Gian", "Doanh thu", "Số lượng khách", "Hóa đơn đã thanh toán", "Hóa đơn đã hủy"};
            XSSFFont headerFont = (XSSFFont) workbook.createFont();
            headerFont.setFontName("Times New Roman");
            headerFont.setBoldweight((short) 14);
            headerFont.setColor(IndexedColors.BLACK.index);
            headerFont.setBold(true);

            CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
            cellStyle.setFont(headerFont);
            cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            cellStyle.setBorderTop(XSSFCellStyle.BORDER_MEDIUM);
            cellStyle.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
            cellStyle.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);
            cellStyle.setBorderRight(XSSFCellStyle.BORDER_MEDIUM);
//            cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
            CellStyle cellStyleRow = sheet.getWorkbook().createCellStyle();
            cellStyleRow.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            cellStyleRow.setFillPattern(CellStyle.ALIGN_CENTER);
            cellStyleRow.setBorderTop(XSSFCellStyle.BORDER_THIN);
            cellStyleRow.setBorderBottom(XSSFCellStyle.BORDER_THIN);
            cellStyleRow.setBorderLeft(XSSFCellStyle.BORDER_THIN);
            cellStyleRow.setBorderRight(XSSFCellStyle.BORDER_THIN);

//             ThongKe Theo tung tháng
            Row headerRow = sheet.createRow(9);
            for (int i = 0; i < columnHeader.length; i++) {
                Cell cell = headerRow.createCell(i + 2);
                cell.setCellValue(columnHeader[i]);
                cell.setCellStyle(cellStyle);
            }
//            ThongKe theo ngay tuan thang nam
            Row headerDayWeekMonthYear = sheet.createRow(2);
            for (int i = 0; i < columnDayWeekMonthYear.length; i++) {
                Cell cell = headerDayWeekMonthYear.createCell(i + 2);
                cell.setCellValue(columnDayWeekMonthYear[i]);
                cell.setCellStyle(cellStyle);
            }
//              Add thong ke tung thang
            int rownum = 3;
            int rownum1 = 10;
            List<ExcelReponse> a = createData();
            for (ExcelReponse i : a) {
                Row row = sheet.createRow(rownum1++);
                BigDecimal bd = new BigDecimal(0);
                BigDecimal bd2 = new BigDecimal(BigInteger.ZERO);
                row.createCell(2).setCellValue(i.getThang());
                row.createCell(3).setCellValue(bd.add(i.getDoanhthu()).toPlainString());
                row.createCell(4).setCellValue(i.getThang2());
                row.createCell(5).setCellValue(bd2.add(i.getDoanhthu2()).toPlainString());

                row.getCell(2).setCellStyle(cellStyleRow);
                row.getCell(3).setCellStyle(cellStyleRow);
                row.getCell(4).setCellStyle(cellStyleRow);
                row.getCell(5).setCellStyle(cellStyleRow);
            }
            //        Add thông kê ngay tuan thang nam

            List<ExcelDayWeekMonthYear> b = createDayWeekMonthYears();
            for (ExcelDayWeekMonthYear excelDayWeekMonthYear : b) {
                Row row = sheet.createRow(rownum++);
                BigDecimal bd = new BigDecimal(0);
                row.createCell(2).setCellValue(excelDayWeekMonthYear.getThoiGian());
                row.createCell(3).setCellValue(bd.add(excelDayWeekMonthYear.getDoanhThu()).toPlainString());
                row.createCell(4).setCellValue(excelDayWeekMonthYear.getSoLuongKhach());
                row.createCell(5).setCellValue(excelDayWeekMonthYear.getDaThanhToan());
                row.createCell(6).setCellValue(excelDayWeekMonthYear.getDaHuy());
                row.getCell(3).setCellStyle(cellStyleRow);
                row.getCell(2).setCellStyle(cellStyleRow);
                row.getCell(4).setCellStyle(cellStyleRow);
                row.getCell(5).setCellStyle(cellStyleRow);
                row.getCell(6).setCellStyle(cellStyleRow);

//                sheet.addMergedRegion(new CellRangeAddress(1, 3, 3, 2));
            }
            try (FileOutputStream fileOutputStream = new FileOutputStream("src\\ThongKeNhaHangERROR.xlsx")) {
                autoSizeColumns(workbook);
                workbook.write(fileOutputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void autoSizeColumns(Workbook workbook) {
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            Sheet sheet = workbook.getSheetAt(i);
            if (sheet.getPhysicalNumberOfRows() > 0) {
                Row row = sheet.getRow(sheet.getFirstRowNum());
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    int columnIndex = cell.getColumnIndex();
                    sheet.autoSizeColumn(columnIndex);
                    int currentColumnWidth = sheet.getColumnWidth(columnIndex);
                    sheet.setColumnWidth(columnIndex, (currentColumnWidth + 2500));
                }
            }
        }
    }

    private ArrayList<ExcelDayWeekMonthYear> createDayWeekMonthYears() {
        ArrayList<ExcelDayWeekMonthYear> b = new ArrayList<>();
        if (thongKeService.getDoanhThuDAY() == null) {
            b.add(new ExcelDayWeekMonthYear("HÔM NAY", new BigDecimal(0), (int) thongKeService.soLuongKhachHangDAY(), thongKeService.getHoaDonDaTTDAY(), thongKeService.getHoaDonHuyDAY()));
        } else {
            b.add(new ExcelDayWeekMonthYear("HÔM NAY", thongKeService.getDoanhThuDAY(), (int) thongKeService.soLuongKhachHangDAY(), thongKeService.getHoaDonDaTTDAY(), thongKeService.getHoaDonHuyDAY()));
        }
        if (thongKeService.getDoanhThuWEEK() == null) {
            b.add(new ExcelDayWeekMonthYear("TUẦN", new BigDecimal(0), (int) thongKeService.soLuongKhachHangWEEK(), thongKeService.getHoaDonDaTTWEEK(), thongKeService.getHoaDonHuyWEEK()));
        } else {
            b.add(new ExcelDayWeekMonthYear("TUẦN", thongKeService.getDoanhThuWEEK(), (int) thongKeService.soLuongKhachHangWEEK(), thongKeService.getHoaDonDaTTWEEK(), thongKeService.getHoaDonHuyWEEK()));
        }
        if (thongKeService.getDoanhThuMONTH() == null) {
            b.add(new ExcelDayWeekMonthYear("THÁNG", new BigDecimal(0), (int) thongKeService.soLuongKhachHangMONTH(), thongKeService.getHoaDonDaTTMONTH(), thongKeService.getHoaDonHuyMONTH()));
        } else {
            b.add(new ExcelDayWeekMonthYear("THÁNG", thongKeService.getDoanhThuMONTH(), (int) thongKeService.soLuongKhachHangMONTH(), thongKeService.getHoaDonDaTTMONTH(), thongKeService.getHoaDonHuyMONTH()));
        }
        if (thongKeService.getDoanhThuYEAR() == null) {
            b.add(new ExcelDayWeekMonthYear("NĂM", new BigDecimal(0), (int) thongKeService.soLuongKhachHangYEAR(), thongKeService.getHoaDonDaTTYEAR(), thongKeService.getHoaDonHuyYEAR()));
        } else {
            b.add(new ExcelDayWeekMonthYear("NĂM", thongKeService.getDoanhThuYEAR(), (int) thongKeService.soLuongKhachHangYEAR(), thongKeService.getHoaDonDaTTYEAR(), thongKeService.getHoaDonHuyYEAR()));
        }
        return b;
    }

    private ArrayList<ExcelReponse> createData() {
        ArrayList<ExcelReponse> a = new ArrayList<>();
//        Tháng 1 và Tháng 7
        if (thongKeService.getDoanhThuThang1() == null && thongKeService.getDoanhThuThang7() != null) {
            a.add(new ExcelReponse("Tháng 1", new BigDecimal(0), "Tháng 7", thongKeService.getDoanhThuThang7()));
        }
        if (thongKeService.getDoanhThuThang7() == null && thongKeService.getDoanhThuThang1() != null) {
            a.add(new ExcelReponse("Tháng 1", thongKeService.getDoanhThuThang1(), "Tháng 7", new BigDecimal(0)));
        }
        if (thongKeService.getDoanhThuThang7() == null && thongKeService.getDoanhThuThang1() == null) {
            a.add(new ExcelReponse("Tháng 1", new BigDecimal(0), "Tháng 7", new BigDecimal(0)));
        }
        if (thongKeService.getDoanhThuThang7() != null && thongKeService.getDoanhThuThang1() != null) {
            a.add(new ExcelReponse("Tháng 1", thongKeService.getDoanhThuThang1(), "Tháng 7", thongKeService.getDoanhThuThang7()));
        }
//        Tháng 2 và tháng 8
        if (thongKeService.getDoanhThuThang2() == null && thongKeService.getDoanhThuThang8() != null) {
            a.add(new ExcelReponse("Tháng 2", new BigDecimal(0), "Tháng 8", thongKeService.getDoanhThuThang8()));
        }
        if (thongKeService.getDoanhThuThang8() == null && thongKeService.getDoanhThuThang2() != null) {
            a.add(new ExcelReponse("Tháng 2", thongKeService.getDoanhThuThang2(), "Tháng 8", new BigDecimal(0)));
        }
        if (thongKeService.getDoanhThuThang8() == null && thongKeService.getDoanhThuThang2() == null) {
            a.add(new ExcelReponse("Tháng 2", new BigDecimal(0), "Tháng 8", new BigDecimal(0)));
        }
        if (thongKeService.getDoanhThuThang2() != null && thongKeService.getDoanhThuThang8() != null) {
            a.add(new ExcelReponse("Tháng 2", thongKeService.getDoanhThuThang2(), "Tháng 8", thongKeService.getDoanhThuThang8()));
        }
//        Tháng 3 và tháng 9
        if (thongKeService.getDoanhThuThang3() == null && thongKeService.getDoanhThuThang9() != null) {
            a.add(new ExcelReponse("Tháng 3", new BigDecimal(0), "Tháng 9", thongKeService.getDoanhThuThang9()));
        }
        if (thongKeService.getDoanhThuThang9() == null && thongKeService.getDoanhThuThang3() == null) {
            a.add(new ExcelReponse("Tháng 3", new BigDecimal(0), "Tháng 9", new BigDecimal(0)));
        }
        if (thongKeService.getDoanhThuThang9() == null && thongKeService.getDoanhThuThang3() != null) {
            a.add(new ExcelReponse("Tháng 3", thongKeService.getDoanhThuThang3(), "Tháng 9", new BigDecimal(0)));
        }
        if (thongKeService.getDoanhThuThang9() != null && thongKeService.getDoanhThuThang3() != null) {
            a.add(new ExcelReponse("Tháng 3", thongKeService.getDoanhThuThang3(), "Tháng 9", thongKeService.getDoanhThuThang9()));
        }
//        Tháng 4 và tháng 10
        if (thongKeService.getDoanhThuThang4() == null && thongKeService.getDoanhThuThang10() != null) {
            a.add(new ExcelReponse("Tháng 4", new BigDecimal(0), "Tháng 10", thongKeService.getDoanhThuThang10()));
        }
        if (thongKeService.getDoanhThuThang10() == null && thongKeService.getDoanhThuThang4() != null) {
            a.add(new ExcelReponse("Tháng 4", thongKeService.getDoanhThuThang4(), "Tháng 10", new BigDecimal(0)));
        }
        if (thongKeService.getDoanhThuThang10() == null && thongKeService.getDoanhThuThang4() == null) {
            a.add(new ExcelReponse("Tháng 4", new BigDecimal(0), "Tháng 10", new BigDecimal(0)));
        }
        if (thongKeService.getDoanhThuThang10() != null && thongKeService.getDoanhThuThang4() != null) {
            a.add(new ExcelReponse("Tháng 4", thongKeService.getDoanhThuThang4(), "Tháng 10", thongKeService.getDoanhThuThang10()));
        }
//        Tháng 5 và tháng 11
        if (thongKeService.getDoanhThuThang5() == null && thongKeService.getDoanhThuThang10() != null) {
            a.add(new ExcelReponse("Tháng 5", new BigDecimal(0), "Tháng 11", thongKeService.getDoanhThuThang11()));
        }
        if (thongKeService.getDoanhThuThang11() == null && thongKeService.getDoanhThuThang5() != null) {
            a.add(new ExcelReponse("Tháng 5", thongKeService.getDoanhThuThang5(), "Tháng 11", new BigDecimal(0)));
        }
        if (thongKeService.getDoanhThuThang11() == null && thongKeService.getDoanhThuThang5() == null) {
            a.add(new ExcelReponse("Tháng 5", new BigDecimal(0), "Tháng 11", new BigDecimal(0)));
        }
        if (thongKeService.getDoanhThuThang11() != null && thongKeService.getDoanhThuThang5() != null) {
            a.add(new ExcelReponse("Tháng 5", thongKeService.getDoanhThuThang5(), "Tháng 11", thongKeService.getDoanhThuThang11()));
        }
//        Tháng 6 và tháng 12
        if (thongKeService.getDoanhThuThang6() == null && thongKeService.getDoanhThuThang12() != null) {
            a.add(new ExcelReponse("Tháng 6", new BigDecimal(0), "Tháng 12", thongKeService.getDoanhThuThang12()));
        }
        if (thongKeService.getDoanhThuThang12() == null && thongKeService.getDoanhThuThang6() != null) {
            a.add(new ExcelReponse("Tháng 6", thongKeService.getDoanhThuThang6(), "Tháng 12", new BigDecimal(0)));
        }
        if (thongKeService.getDoanhThuThang12() == null && thongKeService.getDoanhThuThang6() == null) {
            a.add(new ExcelReponse("Tháng 6", new BigDecimal(0), "Tháng 12", new BigDecimal(0)));
        }
        if (thongKeService.getDoanhThuThang12() != null && thongKeService.getDoanhThuThang6() != null) {
            a.add(new ExcelReponse("Tháng 6", thongKeService.getDoanhThuThang6(), "Tháng 12", thongKeService.getDoanhThuThang12()));
        }
        return a;

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExportExcel;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
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
    private javax.swing.JLabel jLabel7;
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
    private javax.swing.JLabel lbSoLuongKhachDAY;
    private javax.swing.JLabel lbSoLuongKhachHangMONTH;
    private javax.swing.JLabel lbSoLuongKhachHangWEEK;
    private javax.swing.JLabel lbSoLuongKhachHangYEAR;
    private javax.swing.JLabel nameofmax;
    private javax.swing.JLabel nameofmax1;
    private javax.swing.JLabel nameofmax2;
    private com.raven.swing.PanelBorder panelBorder1;
    private com.raven.swing.PanelBorder panelBorder2;
    private com.raven.swing.ScrollBar scrollBar1;
    private javax.swing.JLabel soLuongMin;
    private javax.swing.JLabel soLuongofmax;
    private com.toedter.calendar.JDateChooser txtNgayBatDau;
    private com.toedter.calendar.JDateChooser txtNgayKetThuc;
    // End of variables declaration//GEN-END:variables
}
