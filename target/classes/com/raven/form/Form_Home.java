package com.raven.form;

import com.mycompany.customModel.BanResponse;
import com.mycompany.customModel.ComboResponse;
import com.mycompany.customModel.HoaDonChiTietResponse;
import com.mycompany.customModel.HoaDonResponse;
import com.mycompany.customModel.MonAnResponse;
import com.mycompany.domainModel.Ban;
import com.mycompany.domainModel.ComBo;
import com.mycompany.domainModel.GiaoDich;
import com.mycompany.domainModel.HoaDon;
import com.mycompany.domainModel.HoaDonChiTiet;
import com.mycompany.domainModel.MonAn;
import com.mycompany.domainModel.NhanVien;
import com.mycompany.service.impl.HoaDonChiTietResponseService;
import com.mycompany.service.ICommonResponseService;
import com.mycompany.service.ICommonService;
import com.mycompany.service.IHoaDonChiTiet;
import com.mycompany.service.IHoaDonChiTietResponseService;
import com.mycompany.service.IcommonHoaDonResponseService;
import com.mycompany.service.impl.BanResponseService;
import com.mycompany.service.impl.BanService;
import com.mycompany.service.impl.ComBoService;
import com.mycompany.service.impl.ComboResponseService;
import com.mycompany.service.impl.GiaoDichService;
import com.mycompany.service.impl.HoaDonChiTietService;
import com.mycompany.service.impl.HoaDonResponseService;
import com.mycompany.service.impl.HoaDonService;
import com.mycompany.service.impl.MonAnResponseService;
import com.mycompany.service.impl.MonAnService;
import com.mycompany.service.impl.NhanVienService;
import com.mycompany.util.HoaDonUtil;
import java.awt.Color;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Form_Home extends javax.swing.JPanel {

    private DefaultTableModel dtmHoaDon = new DefaultTableModel();
    private DefaultTableModel dtmHoaDonCT = new DefaultTableModel();
    private DefaultTableModel dtmBan = new DefaultTableModel();
    private DefaultTableModel dtmMonAn = new DefaultTableModel();
    private DefaultTableModel dtmCombo = new DefaultTableModel();
    private DefaultTableModel dtmDoUong = new DefaultTableModel();
    private List<MonAnResponse> lstMonAnResponses = new ArrayList<>();
    private List<BanResponse> lstBanResponses = new ArrayList<>();
    private List<HoaDonResponse> lstHoaDonResponses = new ArrayList<>();
    private List<HoaDonChiTietResponse> lstHDCTResponses = new ArrayList<>();
    private List<ComboResponse> lstComboResponses = new ArrayList<>();
    private ICommonResponseService monAnResponseService = new MonAnResponseService();
    private ICommonResponseService banResponseService = new BanResponseService();
    private IcommonHoaDonResponseService hoaDonResponseService = new HoaDonResponseService();
    private ICommonResponseService comboResponseService = new ComboResponseService();
    private ICommonService hds = new HoaDonService();
    private ICommonService mas = new MonAnService();
    private ICommonService cbs = new ComBoService();
    private ICommonService gds = new GiaoDichService();
    private ICommonService nvs = new NhanVienService();
    private ICommonService monAnService = new MonAnService();
    private ICommonService banService = new BanService();
    private IHoaDonChiTiet hdctService = new HoaDonChiTietService();
    private GiaoDichService gds2 = new GiaoDichService();// khai báo như này mới có hàm fill tiên thừa
    private IHoaDonChiTietResponseService hdctResponseService = new HoaDonChiTietResponseService();
    // để khi hoá đơn có trạng thái đã thanh toán thì k thể thêm sản phẩm
    // = 0  thì là đang chờ thanh toán, 1 là đã thanh toán, 3 là đã huỷ
    private int checkTrangThaiHD;
    // để khi chưa chọn hoá đơn đã ấn vào món ăn 
    // = 1 thì k cho thêm , 0 thì cho thêm vào hdct
    private int checkMonAn = 1;
    // để khi click vào rdo nào thì hiện ra hoa đơn có trạng thái như thế
    //tất cả là 3 , chờ thanh toán là 0, đã thanh toán là 1, đã huỷ là 2
    private int checkRdo = 0;
    // khi click vào button đồ ăn thì hiện thị lên đồ ăn
    // 1 là đồ ăn, 2 là đồ uống
    private int checkBtnMonAn = 1;
    private List<HoaDon> listHD = new ArrayList<>();
    private HoaDonService hoaDonService = new HoaDonService();
    private HoaDonUtil hoaDonUtil = new HoaDonUtil();
    // thực thể
    private NhanVien nhanV;

    public Form_Home() {
        initComponents();
//        card1.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/raven/icon/stock.png")), "Stock Total", "$200000", "Increased by 60%"));
//        card2.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/raven/icon/profit.png")), "Total Profit", "$15000", "Increased by 25%"));
//        card3.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/raven/icon/flag.png")), "Unique Visitors", "$300000", "Increased by 70%"));
//        //  add row table
//        spTable.setVerticalScrollBar(new ScrollBar());
//        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
//        spTable.getViewport().setBackground(Color.WHITE);
//        JPanel p = new JPanel();
//        p.setBackground(Color.WHITE);
//        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
//        table.addRow(new Object[]{"Mike Bhand", "mikebhand@gmail.com", "Admin", "25 Apr,2018", StatusType.PENDING});
//        table.addRow(new Object[]{"Andrew Strauss", "andrewstrauss@gmail.com", "Editor", "25 Apr,2018", StatusType.APPROVED});
//        table.addRow(new Object[]{"Ross Kopelman", "rosskopelman@gmail.com", "Subscriber", "25 Apr,2018", StatusType.APPROVED});
//        table.addRow(new Object[]{"Mike Hussy", "mikehussy@gmail.com", "Admin", "25 Apr,2018", StatusType.REJECT});
//        table.addRow(new Object[]{"Kevin Pietersen", "kevinpietersen@gmail.com", "Admin", "25 Apr,2018", StatusType.PENDING});
//        table.addRow(new Object[]{"Andrew Strauss", "andrewstrauss@gmail.com", "Editor", "25 Apr,2018", StatusType.APPROVED});
//        table.addRow(new Object[]{"Ross Kopelman", "rosskopelman@gmail.com", "Subscriber", "25 Apr,2018", StatusType.APPROVED});
//        table.addRow(new Object[]{"Mike Hussy", "mikehussy@gmail.com", "Admin", "25 Apr,2018", StatusType.REJECT});
//        table.addRow(new Object[]{"Kevin Pietersen", "kevinpietersen@gmail.com", "Admin", "25 Apr,2018", StatusType.PENDING});
//        table.addRow(new Object[]{"Kevin Pietersen", "kevinpietersen@gmail.com", "Admin", "25 Apr,2018", StatusType.PENDING});
//        table.addRow(new Object[]{"Andrew Strauss", "andrewstrauss@gmail.com", "Editor", "25 Apr,2018", StatusType.APPROVED});
//        table.addRow(new Object[]{"Ross Kopelman", "rosskopelman@gmail.com", "Subscriber", "25 Apr,2018", StatusType.APPROVED});
//        table.addRow(new Object[]{"Mike Hussy", "mikehussy@gmail.com", "Admin", "25 Apr,2018", StatusType.REJECT});
//        table.addRow(new Object[]{"Kevin Pietersen", "kevinpietersen@gmail.com", "Admin", "25 Apr,2018", StatusType.PENDING});
        tbHoaDon.setModel(dtmHoaDon);
        tbHoaDonCT.setModel(dtmHoaDonCT);
        tbBan.setModel(dtmBan);
        String headerHoaDon[] = {"STT", "MÃ HĐ", "MÃ KH", "Ngày Tạo", "Bàn", "Trạng Thái", "Ghi Chú"};
        String headerHoaDonCT[] = {"STT", "Mã món ăn", "Tên món ăn", "Giá món ăn", "Số lượng món ăn", "Mã combo", "Tên combo", "Giá combo", "Số lượng combo"};
        String headerBan[] = {"STT", "Mã Bàn", "Số lượng chỗ ngồi", "Khu vực", "Trạng thái"};
        loadTableMonAn();
        dtmHoaDon.setColumnIdentifiers(headerHoaDon);
        dtmHoaDonCT.setColumnIdentifiers(headerHoaDonCT);
        dtmBan.setColumnIdentifiers(headerBan);
        lstBanResponses = banResponseService.getAll();
        lstHoaDonResponses = hoaDonResponseService.getAll();
        lstMonAnResponses = monAnResponseService.getAll();
        radioTatCa.setSelected(true);
        showDataMonAn(lstMonAnResponses);
        showDataBan(lstBanResponses);
        showDataHoaDon(lstHoaDonResponses);
        showDataHDCT(lstHDCTResponses);
        // btnDoAn.setBackground(Color.GRAY);
        txtTienMat.setEnabled(false);
        txtChuyenKhoan.setEnabled(false);
        txtTienThua.setText("0");
        // lbNhanVien.setText(nv.getMa());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panel = new javax.swing.JLayeredPane();
        panelBorder1 = new com.raven.swing.PanelBorder();
        searchText1 = new com.raven.swing.SearchText();
        jLabel1 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbMonAn = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbHoaDonCT = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        btnTaoHoaDon = new javax.swing.JButton();
        radioTatCa = new javax.swing.JRadioButton();
        rdoChoThanhToan = new javax.swing.JRadioButton();
        rdoDaHuy = new javax.swing.JRadioButton();
        rdoDaThanhToan = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbHoaDon = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        lbMaHD = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lbMaHDThanhToan = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbSoBan = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtTienMat = new javax.swing.JTextField();
        txtChuyenKhoan = new javax.swing.JTextField();
        txtTongTien = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cbTienMat = new javax.swing.JCheckBox();
        cbChuyenKhoan = new javax.swing.JCheckBox();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbBan = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        btnThanhToan = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        panelBorder1.setBackground(new java.awt.Color(204, 204, 255));

        jButton9.setBackground(new java.awt.Color(204, 204, 204));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton9.setText("Search");

        jComboBox1.setBackground(new java.awt.Color(255, 255, 0));
        jComboBox1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đồ Ăn", "Đồ Uống", "ComBo" }));

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
        jScrollPane4.setViewportView(tbMonAn);

        tbHoaDonCT.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tbHoaDonCT);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("HÓA ĐƠN");

        btnTaoHoaDon.setBackground(new java.awt.Color(51, 255, 0));
        btnTaoHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTaoHoaDon.setText("TẠO HĐ");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioTatCa);
        radioTatCa.setText("Tất Cả");
        radioTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioTatCaActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoChoThanhToan);
        rdoChoThanhToan.setText("Chờ Thanh Toán");
        rdoChoThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoChoThanhToanActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoDaHuy);
        rdoDaHuy.setText("Đã Hủy");
        rdoDaHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDaHuyActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoDaThanhToan);
        rdoDaThanhToan.setText("Đã Thanh Toán");
        rdoDaThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDaThanhToanActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("HÓA ĐƠN CT");

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
        jScrollPane6.setViewportView(tbHoaDon);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Mã HĐ");

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 0));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Mã HĐ       :");

        lbMaHDThanhToan.setBackground(new java.awt.Color(204, 204, 255));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Bàn                :");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Tổng Tiền  :");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Tiền Thừa  :");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Tiền Mặt         :");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Chuyển khoản:");

        txtTienMat.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienMatCaretUpdate(evt);
            }
        });

        txtChuyenKhoan.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtChuyenKhoanCaretUpdate(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Loại Thanh Toán:");

        cbTienMat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbTienMat.setText("Tiền mặt");
        cbTienMat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTienMatActionPerformed(evt);
            }
        });

        cbChuyenKhoan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbChuyenKhoan.setText("Chuyển Khoản");
        cbChuyenKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbChuyenKhoanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(lbMaHDThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(lbSoBan, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(txtTienMat))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(txtChuyenKhoan)))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbTienMat)
                    .addComponent(jLabel15)
                    .addComponent(cbChuyenKhoan))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbMaHDThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel9)
                    .addComponent(lbSoBan, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(txtTienMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTienMat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel14)
                    .addComponent(txtChuyenKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbChuyenKhoan)))
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
        jScrollPane7.setViewportView(tbBan);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Bàn");

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("Tách Bàn");

        jComboBox2.setBackground(new java.awt.Color(255, 255, 0));
        jComboBox2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tầng 1", "Tầng 2" }));

        btnThanhToan.setBackground(new java.awt.Color(51, 255, 51));
        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 51, 0));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setText("Hủy");

        jButton4.setBackground(new java.awt.Color(204, 204, 204));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setText("In");

        jButton5.setBackground(new java.awt.Color(153, 255, 0));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5.setText("TT & In");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("SĐT:");

        jButton6.setBackground(new java.awt.Color(204, 204, 204));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton6.setText("Search");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("Tên:");

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton7.setText("+");

        jButton8.setBackground(new java.awt.Color(204, 204, 204));
        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton8.setText("Tách HĐ");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchText1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jButton9))
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addGap(16, 16, 16)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelBorder1Layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(radioTatCa)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoChoThanhToan)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoDaHuy)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoDaThanhToan)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(panelBorder1Layout.createSequentialGroup()
                                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lbMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(jScrollPane3)
                                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addContainerGap())
                                    .addGroup(panelBorder1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel17))
                                        .addGap(18, 18, 18)
                                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton7))
                                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton6)))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnThanhToan)
                                .addContainerGap())))))
            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                    .addContainerGap(406, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                        .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radioTatCa)
                            .addComponent(rdoChoThanhToan)
                            .addComponent(rdoDaHuy)
                            .addComponent(jLabel3)
                            .addComponent(rdoDaThanhToan))
                        .addGap(9, 9, 9))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(searchText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton9)
                                .addComponent(jButton8)))
                        .addGap(10, 10, 10)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(lbMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(jButton6))
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton3)
                                    .addComponent(jButton4)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                    .addComponent(btnThanhToan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jButton1)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17)
                                    .addComponent(jButton7))))))
                .addGap(3, 3, 3))
            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelBorder1Layout.createSequentialGroup()
                    .addGap(77, 77, 77)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(363, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 861, Short.MAX_VALUE)
                .addGap(76, 76, 76))
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbMonAnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMonAnMouseClicked
// TODO add your handling code here:
        // check nếu bấm vào btn món ăn thì thêm món ăn
        if (checkBtnMonAn == 1) {
            if (checkTrangThaiHD == 1) {
                JOptionPane.showMessageDialog(this, "Không thể thêm sản phẩm");
                return;
            } else if (checkMonAn == 1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn hoá đơn");
                return;
            } else {
                int soLuong = Integer.valueOf(JOptionPane.showInputDialog("Mời bạn nhập số lượng"));
                int index = tbMonAn.getSelectedRow();
                MonAnResponse mar = lstMonAnResponses.get(index);// lấy ra món ăn đang chọn
                MonAn ma = (MonAn) mas.getOne(mar.getMaMonAn());// chuyển đổi về món ăn để add vào hdct
                HoaDon hd = (HoaDon) hds.getOne(lbMaHDThanhToan.getText());
                // khai báo hdct để add
                HoaDonChiTiet hdct = new HoaDonChiTiet(null, ma, hd, null, soLuong, ma.getDonGia(), 0, BigDecimal.valueOf(0));
                //add hdct
                String addHDCT = (String) hdctService.add(hdct);
                lstHDCTResponses = hdctResponseService.getAll(hd);
                showDataHDCT(lstHDCTResponses);
                //gọi lại fill tổng tiền để cập nhập lại tổng tiền mỗi khi thêm món ăn vào hdct
                fillTongTien();
                return;
            }
        }
        //check khi click vào btn đồ uống thì thêm đồ uống vào hdct
        if (checkBtnMonAn == 2) {
            //thêm sản phẩm nước uống vào hdct
        } else {
            if (checkTrangThaiHD == 1) {
                JOptionPane.showMessageDialog(this, "Không thể thêm sản phẩm");
            } else if (checkMonAn == 1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn hoá đơn");
            } else {
                int soLuong = Integer.valueOf(JOptionPane.showInputDialog("Mời bạn nhập số lượng"));
                int index = tbMonAn.getSelectedRow();
                ComboResponse cbr = lstComboResponses.get(index);
                ComBo cb = (ComBo) cbs.getOne(cbr.getMaCB());
                HoaDon hd = (HoaDon) hds.getOne(lbMaHDThanhToan.getText());
                HoaDonChiTiet hdct = new HoaDonChiTiet(null, null, hd, cb, 0, BigDecimal.valueOf(0), soLuong, cb.getDonGia());
                String addHDCT = (String) hdctService.add(hdct);
                lstHDCTResponses = hdctResponseService.getAll(hd);
                showDataHDCT(lstHDCTResponses);
                fillTongTien();
            }
        }    }//GEN-LAST:event_tbMonAnMouseClicked

    private void tbHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDonMouseClicked
        // TODO add your handling code here:
        // set lại tiền thừa với tổng tiền  = 0.0 để cho dữ liệu được chính xác hơn
        txtTienThua.setText("0.0");
        txtTongTien.setText("0.0");
        // gọi lại hàm để dữ liệu được cập nhập
        fillTienThuaChuyenKhoan();
        fillTienThuaTienMat();
        // lấy ra hoá đơn response đang chọn
        int index = tbHoaDon.getSelectedRow();
        HoaDonResponse hdr = lstHoaDonResponses.get(index);
        lbMaHDThanhToan.setText(hdr.getMaHoaDon());
        // con vớt về hoá đơn
        HoaDon hd = (HoaDon) hds.getOne(lbMaHDThanhToan.getText());
        // lấy ra những giao dịch có trong hoá đơn đã được chọn
        List<GiaoDich> giaoDichs = gds2.getTheoHoaDon(hd);
//        lbSoBan.setText(hd.getBan().getMaBan().toString());
        // nếu hoá đơn đang chọn có trạng thái là dang chờ thanh toán thì set check trangthaiHD = 0
        //, và check món ăn = 0 và fill mã HD lên label
        // ngược lại nếu hd đã thanh toán hoặc đã huỷ thì check TrangTHaiHD = 1 và fill rỗng lên ô tếch phiu mã HD
        if (hd.getTrangThai() == 0) {
            checkTrangThaiHD = 0;
            checkMonAn = 0;
            lbMaHDThanhToan.setText(hdr.getMaHoaDon());
        } else {
            checkTrangThaiHD = 1;
            lbMaHDThanhToan.setText("");
        }
//        txtTienMat.setText("");
//        txtChuyenKhoan.setText("");
        // để fill hình thức thanh toán và số tiền
        for (GiaoDich giaoDich : giaoDichs) {
            if (giaoDich.getHinhThucThanhToan().equals("Chuyển khoản")) {
                txtChuyenKhoan.setText(giaoDich.getSoTienThanhToan().toString());
            }
            if (giaoDich.getHinhThucThanhToan().equals("Tiền mặt")) {
                txtTienMat.setText(giaoDich.getSoTienThanhToan().toString());
            }
        }
        // show data hdct theo hd đang chọn
        lstHDCTResponses = hdctResponseService.getAll(hd);
        showDataHDCT(lstHDCTResponses);
        fillTongTien();
    }//GEN-LAST:event_tbHoaDonMouseClicked

    private void tbBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBanMouseClicked
        // TODO add your handling code here:
        // lấy ra bàn đang chọn và fill mã bàn lên label
        int index = tbBan.getSelectedRow();
        BanResponse banResponse = lstBanResponses.get(index);
        lbSoBan.setText(banResponse.getMaBan().toString());
    }//GEN-LAST:event_tbBanMouseClicked

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        // lấy dữ liệu từ ô tếch phiu tiền mặt và chuyển khoản
        // lếu ô tếch phiuu rỗng thì set dữ liệu được lấy về = 0
        String tienMat = txtTienMat.getText();
        String chuyenKhoan = txtChuyenKhoan.getText();
        if ("".equals(tienMat)) {
            tienMat = "0";
        }
        if ("".equals(chuyenKhoan)) {
            chuyenKhoan = "0";
        }
        //check trường hợp
        if ("".equals(lbMaHDThanhToan.getText())) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hoá đơn chưa thanh toán");
        } else if (cbChuyenKhoan.isSelected() == false && cbTienMat.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hình thức thanh toán");
        } else if (Double.valueOf(txtTongTien.getText()) > (Double.valueOf(tienMat) + Double.valueOf(chuyenKhoan))) {
            JOptionPane.showMessageDialog(this, "Chưa đủ tiền");
        } else {
            String hinhThucThanhToan = "";
            // lấy tổng tiền từ ô tếch phiu và ép kiểu
            Double tongTien = Double.valueOf(txtTongTien.getText());
            // gọi hàm lấy ngày từ máy tính
            String ngayThanhToan = new HoaDonUtil().layNgay();
            // get ont bàn lấy mã từ label mã bàn
            Ban ban = (Ban) banService.getOne(lbSoBan.getText());
            // set trạng thái = 0 cho bàn đã getone về
            ban.setTrangThai(0);
            //fix cuwnsg nv
            // NhanVien nv = (NhanVien) nvs.getOne(lbNhanVien.getText());
            NhanVien nv = (NhanVien) nvs.getOne("NV1");
            // get one hoá đơn theo lable mã HD
            HoaDon hd = (HoaDon) hds.getOne(lbMaHDThanhToan.getText());
            // set trạng thái của hoá đơn get one được 
            hd.setTrangThai(1);
//            hd.setBan(ban);
            hd.setNhanVien(nv);
            hd.setTongTien(BigDecimal.valueOf(tongTien));
            hd.setNgayThanhToan(Date.valueOf(ngayThanhToan));
            // check trường hợp ô checkBox
            if (cbTienMat.isSelected() && cbChuyenKhoan.isSelected()) {
                // nếu cả 2 check box cùng chọn thì add 2 dữ liệu vào bảng giao dịch
                GiaoDich gd = new GiaoDich(null, hd, "Tiền mặt", BigDecimal.valueOf(Double.valueOf(tienMat)));
                // add giao dịch với ô check box tiền mặt
                String addGD = (String) gds.add(gd);
                GiaoDich gd1 = new GiaoDich(null, hd, "Chuyển khoản", BigDecimal.valueOf(Double.valueOf(chuyenKhoan)));
                // add giao dịch với ô check box chuyển khoản
                String addGD1 = (String) gds.add(gd1);
                // update lại hoá đơn
                String addHD = (String) hds.update(hd, lbMaHDThanhToan.getText());
                // up date lại bàn
                String setTrangThaiBan = (String) banService.update(ban, ban.getMaBan().toString());
                JOptionPane.showMessageDialog(this, "Thanh toán thành công");
                // check xem đang chọn radio nào thì hiện thị đúng dữ liệu của radio đấy
                if (checkRdo == 0) {
                    lstHoaDonResponses = hoaDonResponseService.getAll();
                    showDataHoaDon(lstHoaDonResponses);
                } else if (checkRdo == 1) {
                    lstHoaDonResponses = hoaDonResponseService.getByTrangThai(0);
                    showDataHoaDon(lstHoaDonResponses);
                } else if (checkRdo == 2) {
                    lstHoaDonResponses = hoaDonResponseService.getByTrangThai(1);
                    showDataHoaDon(lstHoaDonResponses);
                } else {
                    lstHoaDonResponses = hoaDonResponseService.getByTrangThai(2);
                    showDataHoaDon(lstHoaDonResponses);
                }
                // show lại data bàn
                lstBanResponses = banResponseService.getAll();
                showDataBan(lstBanResponses);
                return;
            } else if (cbChuyenKhoan.isSelected()) {
                hinhThucThanhToan = "Chuyển khoản";
                GiaoDich gd = new GiaoDich(null, hd, hinhThucThanhToan, BigDecimal.valueOf(Double.valueOf(chuyenKhoan)));
                // add giao dịch chuyển khoản
                String addGD = (String) gds.add(gd);
                // update lại hd
                String addHD = (String) hds.update(hd, lbMaHDThanhToan.getText());
                //update lại bàn
                String setTrangThaiBan = (String) banService.update(ban, ban.getMaBan().toString());
                JOptionPane.showMessageDialog(this, "Thanh toán thành công");
                // check xem đang chọn radio nào thì hiện thị đúng dữ liệu của radio đấy
                if (checkRdo == 0) {
                    lstHoaDonResponses = hoaDonResponseService.getAll();
                    showDataHoaDon(lstHoaDonResponses);
                } else if (checkRdo == 1) {
                    lstHoaDonResponses = hoaDonResponseService.getByTrangThai(0);
                    showDataHoaDon(lstHoaDonResponses);
                } else if (checkRdo == 2) {
                    lstHoaDonResponses = hoaDonResponseService.getByTrangThai(1);
                    showDataHoaDon(lstHoaDonResponses);
                } else {
                    lstHoaDonResponses = hoaDonResponseService.getByTrangThai(2);
                    showDataHoaDon(lstHoaDonResponses);
                }
                // show lại data bàn
                lstBanResponses = banResponseService.getAll();
                showDataBan(lstBanResponses);
                return;
            } else {
                hinhThucThanhToan = "Tiền mặt";
                GiaoDich gd = new GiaoDich(null, hd, hinhThucThanhToan, BigDecimal.valueOf(Double.valueOf(tienMat)));
                // add giao dịch tiền mặt
                String addGD = (String) gds.add(gd);
                // update HD
                String addHD = (String) hds.update(hd, lbMaHDThanhToan.getText());
                //UPdate bàn
                String setTrangThaiBan = (String) banService.update(ban, ban.getMaBan().toString());
                JOptionPane.showMessageDialog(this, "Thanh toán thành công");
                // check xem đang chọn radio nào thì hiện thị đúng dữ liệu của radio đấy
                if (checkRdo == 0) {
                    lstHoaDonResponses = hoaDonResponseService.getAll();
                    showDataHoaDon(lstHoaDonResponses);
                } else if (checkRdo == 1) {
                    lstHoaDonResponses = hoaDonResponseService.getByTrangThai(0);
                    showDataHoaDon(lstHoaDonResponses);
                } else if (checkRdo == 2) {
                    lstHoaDonResponses = hoaDonResponseService.getByTrangThai(1);
                    showDataHoaDon(lstHoaDonResponses);
                } else {
                    lstHoaDonResponses = hoaDonResponseService.getByTrangThai(2);
                    showDataHoaDon(lstHoaDonResponses);
                }
                //show lại data bàn
                lstBanResponses = banResponseService.getAll();
                showDataBan(lstBanResponses);
            }

        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        // TODO add your handling code here:
        // check lb mã bàn rỗng thì k cho tạo
        if ("".equals(lbSoBan.getText())) {
            JOptionPane.showMessageDialog(this, "Chọn bàn trước");
        } else {
            listHD = hoaDonService.getAll();
            String maHD = hoaDonUtil.zenMaThuyDuong(listHD);
            String ngayTao = new HoaDonUtil().layNgay();
            String ngayThanhToan = new HoaDonUtil().layNgay();

            // fixx cứng nv
            NhanVien nhanVien = (NhanVien) nvs.getOne("NV1");
            // lấy bàn theo mã bàn lấy từ label mã bàn
            Ban ban = (Ban) banService.getOne(lbSoBan.getText());
            if (ban.getTrangThai() == 1) {
                JOptionPane.showMessageDialog(this, "Bàn đang có khách");
            } else {
                // set bàn đang có khách
                ban.setTrangThai(1);
                HoaDon hd = new HoaDon(null, maHD, nhanVien, null, ngayTao, Date.valueOf(ngayThanhToan), null, null, 0);
                //update lại trạng thái bàn
                String setTrangThaiBan = (String) banService.update(ban, ban.getMaBan().toString());
                // tạo hd
                JOptionPane.showMessageDialog(this, hds.add(hd));
                // check xem đang ở radio nào thì show dữ liệu của radio đấy
                if (checkRdo == 0) {
                    lstHoaDonResponses = hoaDonResponseService.getAll();
                    showDataHoaDon(lstHoaDonResponses);
                } else if (checkRdo == 1) {
                    lstHoaDonResponses = hoaDonResponseService.getByTrangThai(0);
                    showDataHoaDon(lstHoaDonResponses);
                } else if (checkRdo == 2) {
                    lstHoaDonResponses = hoaDonResponseService.getByTrangThai(1);
                    showDataHoaDon(lstHoaDonResponses);
                } else {
                    lstHoaDonResponses = hoaDonResponseService.getByTrangThai(2);
                    showDataHoaDon(lstHoaDonResponses);
                }
                lstBanResponses = banResponseService.getAll();
                showDataBan(lstBanResponses);
            }
        }
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void cbTienMatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTienMatActionPerformed
        if (cbTienMat.isSelected()) {
            txtTienMat.setEnabled(true);
        } else {
            txtTienMat.setText("0");
            txtTienMat.setEnabled(false);
        }
    }//GEN-LAST:event_cbTienMatActionPerformed

    private void cbChuyenKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChuyenKhoanActionPerformed
        // TODO add your handling code here:
        if (cbChuyenKhoan.isSelected()) {
            txtChuyenKhoan.setEnabled(true);
        } else {
            txtChuyenKhoan.setText("0");
            txtChuyenKhoan.setEnabled(false);
        }
    }//GEN-LAST:event_cbChuyenKhoanActionPerformed

    private void txtTienMatCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienMatCaretUpdate
        // TODO add your handling code here:
        fillTienThuaTienMat();
    }//GEN-LAST:event_txtTienMatCaretUpdate

    private void txtChuyenKhoanCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtChuyenKhoanCaretUpdate
        // TODO add your handling code here:
        fillTienThuaChuyenKhoan();
    }//GEN-LAST:event_txtChuyenKhoanCaretUpdate

    private void radioTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioTatCaActionPerformed
        // TODO add your handling code here:
        checkRdo = 3;
        lstHoaDonResponses = hoaDonResponseService.getAll();
        showDataHoaDon(lstHoaDonResponses);
    }//GEN-LAST:event_radioTatCaActionPerformed

    private void rdoChoThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoChoThanhToanActionPerformed
        // TODO add your handling code here:
        checkRdo = 0;
        lstHoaDonResponses = hoaDonResponseService.getByTrangThai(0);
        showDataHoaDon(lstHoaDonResponses);
    }//GEN-LAST:event_rdoChoThanhToanActionPerformed

    private void rdoDaHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDaHuyActionPerformed
        // TODO add your handling code here:
        checkRdo = 2;
        lstHoaDonResponses = hoaDonResponseService.getByTrangThai(2);
        showDataHoaDon(lstHoaDonResponses);
    }//GEN-LAST:event_rdoDaHuyActionPerformed

    private void rdoDaThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDaThanhToanActionPerformed
        // TODO add your handling code here:
        checkRdo = 1;
        lstHoaDonResponses = hoaDonResponseService.getByTrangThai(1);
        showDataHoaDon(lstHoaDonResponses);
    }//GEN-LAST:event_rdoDaThanhToanActionPerformed

    private void fillTienThuaChuyenKhoan() {
//        txtTienMat.setText("0");
        // lấy dữ liệut ừ ô tếch phiu
        String tienMat = txtTienMat.getText();
        String chuyenKhoan = txtChuyenKhoan.getText();
        // nếu dữ liệu rỗng thì set ngầm dữ liệu lấy về bằng = 0
        if ("".equals(tienMat)) {
            tienMat = "0";
        }
        if ("".equals(chuyenKhoan)) {
            chuyenKhoan = "0";
        }
        Double tienThua = Double.valueOf(txtTienThua.getText());
//        Double chuyenKhoan = 0.0;
        try {
//            chuyenKhoan = Double.valueOf(txtChuyenKhoan.getText());
            tienThua = (Double.valueOf(tienMat) + (Double.valueOf(chuyenKhoan))) - Double.valueOf(txtTongTien.getText());
        } catch (java.lang.NumberFormatException e) {
        }
        txtTienThua.setText(tienThua.toString());
    }

    private void fillTienThuaTienMat() {
//        txtChuyenKhoan.setText("0");
        String chuyenKhoan = txtChuyenKhoan.getText();
        String tienMat = txtTienMat.getText();
        if ("".equals(chuyenKhoan)) {
            chuyenKhoan = "0";
        }
        if ("".equals(tienMat)) {
            tienMat = "0";
        }
        Double tienThua = Double.valueOf(txtTienThua.getText());
//        Double tienMat = 0.0;
        try {
//            tienMat = Double.valueOf(txtTienMat.getText());
            tienThua = (Double.valueOf(tienMat) + Double.valueOf(chuyenKhoan)) - Double.valueOf(txtTongTien.getText());
        } catch (java.lang.NumberFormatException e) {
        }
        txtTienThua.setText(tienThua.toString());
    }

    private void fillTongTien() {
        Double tongTien = Double.valueOf(0);
        for (HoaDonChiTietResponse lstHDCTResponse : lstHDCTResponses) {
            String giaCB = lstHDCTResponse.getDonGiaCombo().toString();
            String giaMA = lstHDCTResponse.getDonGiaMonAn().toString();
            tongTien += (Double.valueOf(giaCB) * 1) + (Double.valueOf(giaMA) * 1);
        }
        txtTongTien.setText(tongTien.toString());
    }

    private void loadTableCombo() {
        String header[] = {"STT", "Mã Combo", "Tên Combo", "Đơn giá"};
        tbMonAn.setModel(dtmCombo);
        dtmCombo.setColumnIdentifiers(header);
    }

    private void loadTableMonAn() {
        String headerMonAn[] = {"STT", "Loại món ăn", "Mã món ăn", "Tên món ăn", "Đơn giá", "Đơn vị tính"};
        tbMonAn.setModel(dtmMonAn);
        dtmMonAn.setColumnIdentifiers(headerMonAn);
    }

    private void loadTableDoUong() {
        String headersDoUong[] = {"STT", "Loại đồ uống", "Mã đồ uống", "Tên đồ uống", "Đơn giá", "Đơn vị tính"};
        tbMonAn.setModel(dtmDoUong);
        dtmDoUong.setColumnIdentifiers(headersDoUong);
    }

    private void showDataMonAn(List<MonAnResponse> monAnResponses) {
        dtmMonAn.setRowCount(0);
        int stt = 0;
        for (MonAnResponse monAnResponse : monAnResponses) {
            stt++;
            dtmMonAn.addRow(monAnResponse.toDataRow(stt));
        }
    }

    private void showDataHDCT(List<HoaDonChiTietResponse> hoaDonChiTietResponses) {
        dtmHoaDonCT.setRowCount(0);
        int stt = 0;
        for (HoaDonChiTietResponse hoaDonChiTietResponse : hoaDonChiTietResponses) {
            stt++;
            dtmHoaDonCT.addRow(hoaDonChiTietResponse.toDataRow(stt));
        }
    }

    private void showDataHoaDon(List<HoaDonResponse> hoaDonResponses) {
        dtmHoaDon.setRowCount(0);
        int stt = 0;
        for (HoaDonResponse hoaDonResponse : hoaDonResponses) {
            stt++;
            dtmHoaDon.addRow(hoaDonResponse.toDataRow(stt));
        }
    }

    private void showDataBan(List<BanResponse> banResponses) {
        dtmBan.setRowCount(0);
        int stt = 0;
        for (BanResponse banResponse : banResponses) {
            stt++;
            dtmBan.addRow(banResponse.toDataRow(stt));
        }
    }

    private void loadDataCombo(List<ComboResponse> comboResponses) {
        dtmCombo.setRowCount(0);
        int stt = 0;
        for (ComboResponse comboResponse : comboResponses) {
            stt++;
            dtmCombo.addRow(comboResponse.toDataRow(stt));
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cbChuyenKhoan;
    private javax.swing.JCheckBox cbTienMat;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JLabel lbMaHD;
    private javax.swing.JLabel lbMaHDThanhToan;
    private javax.swing.JLabel lbSoBan;
    private javax.swing.JLayeredPane panel;
    private com.raven.swing.PanelBorder panelBorder1;
    private javax.swing.JRadioButton radioTatCa;
    private javax.swing.JRadioButton rdoChoThanhToan;
    private javax.swing.JRadioButton rdoDaHuy;
    private javax.swing.JRadioButton rdoDaThanhToan;
    private com.raven.swing.SearchText searchText1;
    private javax.swing.JTable tbBan;
    private javax.swing.JTable tbHoaDon;
    private javax.swing.JTable tbHoaDonCT;
    private javax.swing.JTable tbMonAn;
    private javax.swing.JTextField txtChuyenKhoan;
    private javax.swing.JTextField txtTienMat;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
