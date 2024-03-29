/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.form;

//import java.awt.Color;
//import java.awt.GradientPaint;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.RenderingHints;
import com.mycompany.domainModel.Ban;
import com.mycompany.domainModel.ChiTietBanHoaDon;
import com.mycompany.domainModel.GiaoDich;
import com.mycompany.domainModel.HoaDon;
import com.mycompany.domainModel.HoaDonChiTiet;
import com.mycompany.domainModel.NhanVien;
import com.mycompany.service.impl.BanService;
import com.mycompany.service.impl.ChiTietBanHoaDonService;
import com.mycompany.service.impl.GiaoDichService;
import com.mycompany.service.impl.HoaDonChiTietService;
import com.mycompany.service.impl.HoaDonService;
import com.mycompany.service.impl.NhanVienService;
import com.mycompany.util.HoaDonUtil;
import java.awt.Color;
import java.awt.Font;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author RAVEN
 */
public class FormHoaDon extends javax.swing.JPanel {

    private DefaultTableModel dtmHoaDon = new DefaultTableModel();
    private List<HoaDon> listHD = new ArrayList<>();
    private HoaDonService hoaDonService = new HoaDonService();
    private HoaDonChiTietService hoaDonChiTietService = new HoaDonChiTietService();
    private List<HoaDonChiTiet> listHDCT = new ArrayList<>();
    private List<Ban> listBan = new ArrayList<>();
    private BanService banService = new BanService();
    private HoaDonUtil hoaDonUtil = new HoaDonUtil();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private NhanVienService nhanVienService = new NhanVienService();
    private NhanVien nhanV;
    private java.util.Date today = new java.util.Date();
    private DefaultComboBoxModel dcbmTrangThaiHD = new DefaultComboBoxModel();
    private List<String> listTrangThaiHD = new ArrayList<>();
    private ChiTietBanHoaDonService chiTietBanHoaDonService = new ChiTietBanHoaDonService();
    private List<ChiTietBanHoaDon> listCTBan_HD = new ArrayList<>();
    private DefaultTableModel dtmHDCT = new DefaultTableModel();

    public FormHoaDon(NhanVien nv) {
        initComponents();
        this.nhanV = nv;
        dateNgay1.setDate(today);
        dateNgay2.setDate(today);
        radioChoTT.setSelected(true);
        tbHoaDon.setModel(dtmHoaDon);
        String header[] = {"Mã HĐ", "Người tạo", "Khách hàng", "Ngày Tạo", "Ngày TT", "Ghi chú", "Trạng thái"};
        dtmHoaDon.setColumnIdentifiers(header);
        //thêm hàm tính tổng tiền ở class HoaDonChiTietRepository và hdctService
        //getAll hoá đơn sao lại chỉ lấy trạng thái = 0?
        //thêm hàm getAll hdct theo 1 hoá đơn domain ở class hdct repo và service 
//        listHD = hoaDonService.getAll();
//        showData(listHD);
        txtNgayTao.setDate(today);
        // hàm getAll bàn where trạng thái =  còn trống = 0
//        lbNgayGio.setText(String.valueOf(today));
        cbbTrangThaiHoaDon.setModel(dcbmTrangThaiHD);
        loadCbbTrangThaiHD();
        String headersHDCT[] = {"Mã HD", "Mã MA", "Sl món", "Đơn giá", "Combo", "SL combo", "Đơn giá", "Ghi chú"};
        tbHoaDonChiTiet.setModel(dtmHDCT);
        dtmHDCT.setColumnIdentifiers(headersHDCT);
        txtTongTien.setEditable(false);
        txtBan.setEditable(false);
        rdoHomNay.setSelected(true);
        btnLoc.setEnabled(false);
        dateNgay1.setEnabled(false);
        dateNgay2.setEnabled(false);
        listHD = hoaDonService.getHoaDonsHomNay(today);
        showData(listHD);
        dateNgay1.getJCalendar().setMaxSelectableDate(today);
        txtNgayThanhToan.setEnabled(false);
        JTableHeader hd = tbHoaDon.getTableHeader();
        hd.setBackground(Color.red);
        hd.setForeground(Color.red);
        hd.setFont(new Font("Segoe", Font.BOLD, 13));
        //
        JTableHeader hd1 = tbHoaDonChiTiet.getTableHeader();
        hd1.setBackground(Color.red);
        hd1.setForeground(Color.red);
        hd1.setFont(new Font("Segoe", Font.BOLD, 13));
        txtTienDuocGiam.setEditable(false);
        txtTienMat.setEditable(false);
        txtChuyenKhoan.setEditable(false);
    }

    private void showData(List<HoaDon> listHD) {
        if (listHD.size() > 0) {
            dtmHoaDon.setRowCount(0);
            for (HoaDon hoaDon : listHD) {
                dtmHoaDon.addRow(hoaDon.toDataRowViewHoaDon());
            }
        }
    }

    private void showDataHDCT(List<HoaDonChiTiet> listHDCT) {
        dtmHDCT.setRowCount(0);
        if (listHDCT.size() > 0) {
            for (HoaDonChiTiet hoaDonChiTiet : listHDCT) {
                dtmHDCT.addRow(hoaDonChiTiet.toDataRow());
            }
        }
    }

    private void loadCbbTrangThaiHD() {
        listTrangThaiHD.add("Tất cả");
        listTrangThaiHD.add("Chờ thanh toán");
        listTrangThaiHD.add("Đã thanh toán");
        listTrangThaiHD.add("Đã huỷ");
        for (String string : listTrangThaiHD) {
            dcbmTrangThaiHD.addElement(string);
        }
    }

    private void fillHD(HoaDon hoaDon) {
        lbMaHD.setText(hoaDon.getMaHoaDon());
        listHDCT = hoaDonChiTietService.getHDCTByHD(hoaDon);
        //txtTongTien.setText(String.valueOf(hoaDonChiTietService.tongTienHD(listHDCT)));
        txtTongTien.setText(String.valueOf(hoaDonUtil.tinhTongTienHD(listHDCT)));
        txtTongTien.setEditable(false);
        txtGhiChu.setText(hoaDon.getGhiChu());
        int trangThaiHD = hoaDon.getTrangThai();
        if (trangThaiHD == 0) {
            radioChoTT.setSelected(true);
            radioChoTT.setEnabled(false);
            radioDaHuy.setEnabled(true);
            radioDaTT.setEnabled(false);
            btnAdd.setEnabled(true);
            btnRemove.setEnabled(true);
            btnUpdate.setEnabled(true);
        } else if (trangThaiHD == 1) {
            btnAdd.setEnabled(false);
            btnRemove.setEnabled(false);
            btnUpdate.setEnabled(false);
            radioDaTT.setSelected(true);
            radioChoTT.setEnabled(false);
            radioDaHuy.setEnabled(false);
            radioDaTT.setEnabled(false);
            try {
                if (hoaDon.getTienDuocGiam() == null || hoaDon.getTienDuocGiam().compareTo(new BigDecimal(0)) < 0) {
                    txtTienDuocGiam.setText("0");
                } else {
                    txtTienDuocGiam.setText(hoaDon.getTienDuocGiam().toString());
                }
                List<GiaoDich> listGD = new GiaoDichService().getTheoHoaDon(hoaDon);
                if (listGD.size() == 2) {
                    for (GiaoDich giaoDich : listGD) {
                        if (giaoDich.getHinhThucThanhToan().equalsIgnoreCase("Tiền mặt")) {
                            txtTienMat.setText(giaoDich.getSoTienThanhToan().toString());
                        } else {
                            txtChuyenKhoan.setText(giaoDich.getSoTienThanhToan().toString());
                        }
                    }
                } else {
                    for (GiaoDich giaoDich : listGD) {
                        if (giaoDich.getHinhThucThanhToan().equalsIgnoreCase("Tiền mặt")) {
                            txtTienMat.setText(giaoDich.getSoTienThanhToan().toString());
                            txtChuyenKhoan.setText("0");
                        } else {
                            txtChuyenKhoan.setText(giaoDich.getSoTienThanhToan().toString());
                            txtTienMat.setText("0");
                        }
                    }
                }
            } catch (Exception e) {
            }
        } else if (trangThaiHD == 2) {
            btnAdd.setEnabled(false);
            btnRemove.setEnabled(false);
            btnUpdate.setEnabled(false);
            radioDaHuy.setSelected(true);
            radioChoTT.setEnabled(false);
            radioDaHuy.setEnabled(false);
            radioDaTT.setEnabled(false);
        } else {
            radioTrangThaiHD.clearSelection();
        }
        listCTBan_HD = chiTietBanHoaDonService.getByHoaDon(hoaDon);
        String maBan = listCTBan_HD.get(0).getBan().getMaBan().toString();
        if (listCTBan_HD.size() > 1) {
            for (int i = 1; i < listCTBan_HD.size(); i++) {
                maBan += ", " + listCTBan_HD.get(i).getBan().getMaBan();
            }
        }
        txtBan.setText(maBan);
        txtBan.setEditable(false);
        java.util.Date ngayTao = hoaDon.getNgayTao();
        txtNgayTao.setDate(ngayTao);
        txtNgayThanhToan.setDate(hoaDon.getNgayThanhToan());
//        lbKhuVuc.setText(hoaDon.getBan().getKv().getTenKV());
//        txtBan.setText(String.valueOf(hoaDon.getBan().getMaBan()));
        txtNgayTao.setEnabled(false);
        lbKhuVuc.setText(banService.getOne(listCTBan_HD.get(0).getBan().getMaBan().toString()).getKv().getTenKV());
        txtSoLuongKhach.setText(hoaDon.getSoLuongKhach().toString());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        radioTrangThaiHD = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        panelBorder1 = new com.raven.swing.PanelBorder();
        searchText1 = new com.raven.swing.SearchText();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbMaHD = new javax.swing.JLabel();
        txtNgayTao = new com.toedter.calendar.JDateChooser();
        txtNgayThanhToan = new com.toedter.calendar.JDateChooser();
        txtTongTien = new javax.swing.JTextField();
        txtGhiChu = new javax.swing.JTextField();
        txtBan = new javax.swing.JTextField();
        lbKhuVuc = new javax.swing.JLabel();
        radioChoTT = new javax.swing.JRadioButton();
        radioDaTT = new javax.swing.JRadioButton();
        radioDaHuy = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        txtSoLuongKhach = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtTienDuocGiam = new javax.swing.JTextField();
        txtChuyenKhoan = new javax.swing.JTextField();
        txtTienMat = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbHoaDon = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbHoaDonChiTiet = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbbTrangThaiHoaDon = new javax.swing.JComboBox<>();
        rdoHomNay = new javax.swing.JRadioButton();
        dateNgay2 = new com.toedter.calendar.JDateChooser();
        dateNgay1 = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnLoc = new javax.swing.JButton();
        rdoKhoangNgay = new javax.swing.JRadioButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();

        panelBorder1.setBackground(new java.awt.Color(204, 204, 255));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Mã HĐ                   :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Ngày Tạo               :");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Ngày Thanh Toán :");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Tổng Tiền              :");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Ghi Chú                  :");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Trạng Thái             :");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Bàn                         :");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Khu vực:");

        lbKhuVuc.setText("Khu vực");

        radioTrangThaiHD.add(radioChoTT);
        radioChoTT.setText("Chờ thanh toán");

        radioTrangThaiHD.add(radioDaTT);
        radioDaTT.setText("Đã thanh toán");

        radioTrangThaiHD.add(radioDaHuy);
        radioDaHuy.setText("Đã huỷ");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Số lượng khách:");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Tiền được giảm:");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Tiền mặt:");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("Chuyển khoản:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtNgayThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(radioChoTT, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(radioDaTT)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(radioDaHuy))
                            .addComponent(txtGhiChu, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(lbMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(34, 34, 34)
                        .addComponent(txtSoLuongKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(txtBan, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jLabel11)
                        .addGap(34, 34, 34)
                        .addComponent(lbKhuVuc, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtChuyenKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTienMat, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTienDuocGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(49, 49, 49))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(lbMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtNgayThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtTienDuocGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtTienMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtChuyenKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(radioChoTT)
                    .addComponent(radioDaTT)
                    .addComponent(radioDaHuy))
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(lbKhuVuc, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSoLuongKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));

        tbHoaDon.setBackground(new java.awt.Color(178, 205, 255));
        tbHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tbHoaDon.setModel(new javax.swing.table.DefaultTableModel(
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
        tbHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHoaDonMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbHoaDon);

        tbHoaDonChiTiet.setBackground(new java.awt.Color(178, 205, 255));
        tbHoaDonChiTiet.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tbHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tbHoaDonChiTiet);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Danh sách hóa đơn:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Danh sách hóa đơn chi tiết:");

        cbbTrangThaiHoaDon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbTrangThaiHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTrangThaiHoaDonActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoHomNay);
        rdoHomNay.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoHomNay.setText("Hôm nay");
        rdoHomNay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoHomNayActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("From:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("To:");

        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoKhoangNgay);
        rdoKhoangNgay.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoKhoangNgay.setText("Khoảng ngày");
        rdoKhoangNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoKhoangNgayActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jRadioButton1.setText("Hoá đơn được giảm giá");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
                            .addComponent(jScrollPane5)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(rdoHomNay)
                                .addGap(70, 70, 70)
                                .addComponent(rdoKhoangNgay)
                                .addGap(63, 63, 63)
                                .addComponent(jRadioButton1))
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dateNgay1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13)
                                .addGap(26, 26, 26)
                                .addComponent(dateNgay2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(btnLoc)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbbTrangThaiHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdoKhoangNgay)
                        .addComponent(jRadioButton1))
                    .addComponent(rdoHomNay))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel12)
                            .addGap(6, 6, 6))
                        .addComponent(dateNgay1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnLoc)
                    .addComponent(dateNgay2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(cbbTrangThaiHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel3)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        jPanel3.setBackground(new java.awt.Color(153, 204, 255));

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

        btnRefresh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRemove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnAdd)
                    .addComponent(btnClear)
                    .addComponent(btnRefresh)
                    .addComponent(btnRemove))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchText1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(searchText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 703, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, 748, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        dateNgay1.setDate(today);
        dateNgay2.setDate(today);
        lbMaHD.setText("");
//         listHDCT = hoaDonChiTietService.getHDCTByHD(hoaDon);
        txtTongTien.setText("");
        txtGhiChu.setText("");
        radioChoTT.setSelected(true);
        java.util.Date date = new java.util.Date();
        txtNgayTao.setDate(date);
        txtNgayThanhToan.setDate(null);
        lbKhuVuc.setText("");
        txtBan.setText("");
        radioChoTT.setEnabled(true);
        radioDaHuy.setEnabled(true);
        radioDaTT.setEnabled(true);
        txtNgayTao.setEnabled(true);
        dtmHDCT.setRowCount(0);
        txtTongTien.setEditable(false);
        txtBan.setEditable(false);
        rdoHomNayActionPerformed(evt);
        listHD = hoaDonService.getHoaDonsHomNay(today);
        rdoHomNay.setSelected(true);
        txtSoLuongKhach.setText("");
        //showData(listHD);
        btnAdd.setEnabled(true);
        btnRemove.setEnabled(true);
        btnUpdate.setEnabled(true);
    }//GEN-LAST:event_btnClearActionPerformed

    private void tbHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDonMouseClicked
        int index = tbHoaDon.getSelectedRow();
        HoaDon hoaDon = hoaDonService.getOne((String) tbHoaDon.getValueAt(index, 0));
        fillHD(hoaDon);
        listHDCT = hoaDonChiTietService.getHDCTByHD(hoaDon);
        showDataHDCT(listHDCT);
    }//GEN-LAST:event_tbHoaDonMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        NhanVien nv = nhanV;
        JDialogBan_ThemHD ban_ThemHD = new JDialogBan_ThemHD(null, true, nv);
        ban_ThemHD.setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int index = tbHoaDon.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Chọn data để sửa!");
        } else {
            HoaDon hoaDon = hoaDonService.getOne((String) tbHoaDon.getValueAt(index, 0));
            int tongSoGhe = 0;
            if (radioChoTT.isSelected()) {
                List<ChiTietBanHoaDon> listCTB_HD = chiTietBanHoaDonService.getByHoaDon(hoaDon);
                for (ChiTietBanHoaDon chiTietBanHoaDon : listCTB_HD) {
                    tongSoGhe += chiTietBanHoaDon.getBan().getSoLuongChoNgoi();
                }
                hoaDon.setGhiChu(txtGhiChu.getText());
                String soLuongKH = txtSoLuongKhach.getText();
                if (hoaDonUtil.checkSoLuongKhach(soLuongKH, tongSoGhe)) {
                    hoaDon.setSoLuongKhach(Integer.valueOf(soLuongKH));
                    hoaDon.setTrangThai(0);
                    String update = hoaDonService.update(hoaDon, hoaDon.getMaHoaDon());
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                    btnClearActionPerformed(evt);
                }
            } else if (radioDaTT.isSelected()) {
                hoaDon.setTrangThai(1);
            } else if (radioDaHuy.isSelected()) {
                hoaDon.setTrangThai(2);
                hoaDon.setGhiChu(txtGhiChu.getText());
                if (hoaDon.getGhiChu().isEmpty() || hoaDon.getGhiChu().equals("")) {
                    JOptionPane.showMessageDialog(this, "Không được để trống ghi chú");
                } else {
                    int checkConfirm = JOptionPane.showConfirmDialog(this, "Xác nhận chuyển trạng thái!");
                    if (checkConfirm == 0) {
                        List<ChiTietBanHoaDon> listCTBan_HD = chiTietBanHoaDonService.getByHoaDon(hoaDon);
                        for (ChiTietBanHoaDon chiTietBanHoaDon : listCTBan_HD) {
                            Ban ban = chiTietBanHoaDon.getBan();
                            ban.setTrangThai(0);
                            String updateBan = new BanService().update(ban, ban.getMaBan().toString());
                        }
                        JOptionPane.showMessageDialog(this, hoaDonService.update(hoaDon, hoaDon.getMaHoaDon()));
                        dcbmTrangThaiHD.setSelectedItem("Tất cả");
                        btnClearActionPerformed(evt);
                    }
                }
            } else {
                hoaDon.setTrangThai(hoaDon.getTrangThai());
                JOptionPane.showMessageDialog(this, hoaDonService.update(hoaDon, hoaDon.getMaHoaDon()));
                dcbmTrangThaiHD.setSelectedItem("Tất cả");
//                listHD = hoaDonService.getAll();
//                showData(listHD);
                btnClearActionPerformed(evt);
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed

//hoá đơn .remove = update trạng thái = 3 => đã huỷ
        int index = tbHoaDon.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "?");
        } else {
            HoaDon hoaDon = hoaDonService.getOne((String) tbHoaDon.getValueAt(index, 0));
            if (txtGhiChu.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ghi chú ko được trống!");
            } else {
                List<ChiTietBanHoaDon> listCTBan_HD = chiTietBanHoaDonService.getByHoaDon(hoaDon);
                for (ChiTietBanHoaDon chiTietBanHoaDon : listCTBan_HD) {
                    Ban ban = chiTietBanHoaDon.getBan();
                    ban.setTrangThai(0);
                    String updateBan = new BanService().update(ban, ban.getMaBan().toString());
                }
                hoaDon.setGhiChu(txtGhiChu.getText());
                hoaDon.setTrangThai(2);
                // Ban ban = hoaDon.getBan();
                // ban.setTrangThai(0);
                //String updateBan = banService.update(ban, String.valueOf(ban.getMaBan()));
                if (JOptionPane.showConfirmDialog(this, "Xac nhận remove!") == 0) {

                    java.util.Date date = today;
                    System.out.println(dateFormat.format(date));
                    hoaDon.setNgayHuy(date);
                    JOptionPane.showMessageDialog(this, hoaDonService.update(hoaDon, hoaDon.getMaHoaDon()));
                    listHD = hoaDonService.getAll();
                    showData(listHD);
                }
            }
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void cbbTrangThaiHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTrangThaiHoaDonActionPerformed
        if (rdoHomNay.isSelected()) {
            rdoHomNayActionPerformed(evt);
        } else {
            btnLocActionPerformed(evt);
        }
    }//GEN-LAST:event_cbbTrangThaiHoaDonActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        btnClearActionPerformed(evt);
        listHD = hoaDonService.getHoaDonsHomNay(today);
        List<HoaDon> listHDShow = new ArrayList<>();
        for (HoaDon hoaDon : listHD) {
            if (hoaDon.getTrangThai() == 0) {
                listHDShow.add(hoaDon);
            }
        }
        dcbmTrangThaiHD.setSelectedItem("Chờ thanh toán");
        showData(listHDShow);
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void rdoHomNayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoHomNayActionPerformed
        btnLoc.setEnabled(false);
        List<HoaDon> listHDShow = new ArrayList<>();
//if rdoHnay được chọn: 
        //lấy 1 list HNay để lọc trạng thái = for=)))))
        listHD = hoaDonService.getHoaDonsHomNay(today);
        if (listHD.size() <= 0) {
            dtmHoaDon.setRowCount(0);
        } else {
            //ko cho chọn ngày
            dateNgay1.setEnabled(false);
            dateNgay2.setEnabled(false);
            //lọc hoá đơn theo trjang thái: DÙNG FOR =))))
            if (dcbmTrangThaiHD.getSelectedItem().toString().equalsIgnoreCase("Tất cả")) {
                listHDShow = hoaDonService.getHoaDonsHomNay(today);
                showData(listHDShow);
            } else if (dcbmTrangThaiHD.getSelectedItem().toString().equalsIgnoreCase("Chờ thanh toán")) {
                for (HoaDon hoaDon : listHD) {
                    if (hoaDon.getTrangThai() == 0) {
                        listHDShow.add(hoaDon);
                    }
                }
                if (listHDShow.size() <= 0) {
                    dtmHoaDon.setRowCount(0);
                } else {
                    showData(listHDShow);
                }
            } else if (dcbmTrangThaiHD.getSelectedItem().toString().equalsIgnoreCase("Đã thanh toán")) {
                for (HoaDon hoaDon : listHD) {
                    if (hoaDon.getTrangThai() == 1) {
                        listHDShow.add(hoaDon);
                    }
                }
                if (listHDShow.size() <= 0) {
                    dtmHoaDon.setRowCount(0);
                } else {
                    showData(listHDShow);
                }
            } else if (dcbmTrangThaiHD.getSelectedItem().toString().equalsIgnoreCase("Đã huỷ")) {
                for (HoaDon hoaDon : listHD) {
                    if (hoaDon.getTrangThai() == 2) {
                        listHDShow.add(hoaDon);
                    }
                }
                if (listHDShow.size() <= 0) {
                    dtmHoaDon.setRowCount(0);
                } else {
                    showData(listHDShow);
                }
            } else {
                //
            }
        }
        // showData(listHDShow);
    }//GEN-LAST:event_rdoHomNayActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        String date1 = dateFormat.format(dateNgay1.getDate());
        String date2 = dateFormat.format(dateNgay2.getDate());
        List<HoaDon> listHDShow = new ArrayList<>();
        if (date1.compareTo(date2) > 0) {
            JOptionPane.showMessageDialog(this, "thời gian không xác định");
            dtmHoaDon.setRowCount(0);
        } else if (date1.compareTo(dateFormat.format(today)) > 0) {
            JOptionPane.showMessageDialog(this, "thời gian không xác định");
            dtmHoaDon.setRowCount(0);
        } else {
            dateNgay1.setEnabled(true);
            dateNgay2.setEnabled(true);
            listHDShow.clear();
            if (dateFormat.format(dateNgay1.getDate()).compareTo(dateFormat.format(today)) > 0) {
                JOptionPane.showMessageDialog(this, "Thời gian không xác định");
                dtmHoaDon.setRowCount(0);
            } else if (dateFormat.format(dateNgay1.getDate()).compareTo(dateFormat.format(dateNgay2.getDate())) > 0) {
                JOptionPane.showMessageDialog(this, "Thời gian không xác định!");
                dtmHoaDon.setRowCount(0);
            } else {
                listHD = hoaDonService.getHoaDonsKhoangNgay(dateNgay1.getDate(), dateNgay2.getDate());
                if (dcbmTrangThaiHD.getSelectedItem().toString().equalsIgnoreCase("Tất cả")) {
                    for (HoaDon hoaDon : listHD) {
                        listHDShow.add(hoaDon);
                    }
                    if (listHDShow.size() <= 0) {
                        dtmHoaDon.setRowCount(0);
                    } else {
                        showData(listHDShow);
                    }
                } else if (dcbmTrangThaiHD.getSelectedItem().toString().equalsIgnoreCase("Chờ thanh toán")) {
                    for (HoaDon hoaDon : listHD) {
                        if (hoaDon.getTrangThai() == 0) {
                            listHDShow.add(hoaDon);
                        }
                    }
                    if (listHDShow.size() <= 0) {
                        dtmHoaDon.setRowCount(0);
                    } else {
                        showData(listHDShow);
                    }
                } else if (dcbmTrangThaiHD.getSelectedItem().toString().equalsIgnoreCase("Đã thanh toán")) {
                    for (HoaDon hoaDon : listHD) {
                        if (hoaDon.getTrangThai() == 1) {
                            listHDShow.add(hoaDon);
                        }
                    }
                    if (listHDShow.size() <= 0) {
                        dtmHoaDon.setRowCount(0);
                    } else {
                        showData(listHDShow);
                    }
                } else {
                    for (HoaDon hoaDon : listHD) {
                        if (hoaDon.getTrangThai() == 2) {
                            listHDShow.add(hoaDon);
                        }
                    }
                    if (listHDShow.size() <= 0) {
                        dtmHoaDon.setRowCount(0);
                    } else {
                        showData(listHDShow);
                    }
                }
            }
            //showData(listHDShow);
        }
    }//GEN-LAST:event_btnLocActionPerformed

    private void rdoKhoangNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoKhoangNgayActionPerformed
        dateNgay1.setEnabled(true);
        dateNgay2.setEnabled(true);
        btnLoc.setEnabled(true);
    }//GEN-LAST:event_rdoKhoangNgayActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        dtmHoaDon.setRowCount(0);
        List<HoaDon> listHDCoGiamGia = hoaDonService.getHoaDonsCoGiamGia();
        if (listHDCoGiamGia.size() > 0) {
            showData(listHDCoGiamGia);
        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbTrangThaiHoaDon;
    private com.toedter.calendar.JDateChooser dateNgay1;
    private com.toedter.calendar.JDateChooser dateNgay2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lbKhuVuc;
    private javax.swing.JLabel lbMaHD;
    private com.raven.swing.PanelBorder panelBorder1;
    private javax.swing.JRadioButton radioChoTT;
    private javax.swing.JRadioButton radioDaHuy;
    private javax.swing.JRadioButton radioDaTT;
    private javax.swing.ButtonGroup radioTrangThaiHD;
    private javax.swing.JRadioButton rdoHomNay;
    private javax.swing.JRadioButton rdoKhoangNgay;
    private com.raven.swing.SearchText searchText1;
    private javax.swing.JTable tbHoaDon;
    private javax.swing.JTable tbHoaDonChiTiet;
    private javax.swing.JTextField txtBan;
    private javax.swing.JTextField txtChuyenKhoan;
    private javax.swing.JTextField txtGhiChu;
    private com.toedter.calendar.JDateChooser txtNgayTao;
    private com.toedter.calendar.JDateChooser txtNgayThanhToan;
    private javax.swing.JTextField txtSoLuongKhach;
    private javax.swing.JTextField txtTienDuocGiam;
    private javax.swing.JTextField txtTienMat;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
