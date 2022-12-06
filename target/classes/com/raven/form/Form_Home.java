package com.raven.form;

import com.mycompany.customModel.BanResponse;
import com.mycompany.customModel.ComboResponse;
import com.mycompany.customModel.HoaDonChiTietResponse;
import com.mycompany.customModel.HoaDonResponse;
import com.mycompany.customModel.HoaDonThanhToanCustom;
import com.mycompany.customModel.MonAnResponse;
import com.mycompany.domainModel.Ban;
import com.mycompany.domainModel.ChiTietBanHoaDon;
import com.mycompany.domainModel.ComBo;
import com.mycompany.domainModel.DanhMuc;
import com.mycompany.domainModel.GiaoDich;
import com.mycompany.domainModel.HoaDon;
import com.mycompany.domainModel.HoaDonChiTiet;
import com.mycompany.domainModel.KhachHang;
import com.mycompany.domainModel.Loai;
import com.mycompany.domainModel.MonAn;
import com.mycompany.domainModel.NhanVien;
import com.mycompany.service.IBanResponseService;
import com.mycompany.service.IChiTietBanHoaDonService;
import com.mycompany.service.impl.HoaDonChiTietResponseService;
import com.mycompany.service.ICommonService;
import com.mycompany.service.IHoaDonChiTietResponseService;
import com.mycompany.service.IMonAnResponseService;
import com.mycompany.service.IcommonHoaDonResponseService;
import com.mycompany.service.impl.BanResponseService;
import com.mycompany.service.impl.BanService;
import com.mycompany.service.impl.ChiTietBanHoaDonService;
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
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.mycompany.service.IHoaDonChiTietService;
import com.mycompany.service.impl.DanhMucService;
import com.mycompany.service.impl.KhachHangService;
import com.mycompany.service.impl.LoaiService;
import com.mycompany.util.ThanhToanUtil;
import com.raven.main.Main;
import java.awt.event.InputEvent;
import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;
import net.bytebuddy.jar.asm.Opcodes;
//import java.awt.Color;
//import java.awt.event.MouseEvent;
//import javax.swing.JComponent;
//import javax.swing.JMenu;
//import javax.swing.JMenuItem;
//import javax.swing.JPopupMenu;
//import javax.swing.JTable;

public class Form_Home extends javax.swing.JPanel {

    private DefaultTableModel dtmHoaDon = new DefaultTableModel();
    private DefaultTableModel dtmHoaDonCT = new DefaultTableModel();
    private DefaultTableModel dtmBan = new DefaultTableModel();
    private DefaultTableModel dtmMonAn = new DefaultTableModel();
    private DefaultTableModel dtmCombo = new DefaultTableModel();
    private DefaultTableModel dtmDoUong = new DefaultTableModel();
    private List<MonAnResponse> lstMAReponByDM = new ArrayList<>();
    private List<MonAnResponse> listMAReponBYKMCT = new ArrayList<>();
    private List<MonAnResponse> listMAReponMerge = new ArrayList<>();
    private List<BanResponse> lstBanResponses = new ArrayList<>();
    private List<BanResponse> lstMaBan = new ArrayList<>();// list để lấy mã bàn
    private List<HoaDonResponse> lstHoaDonResponses = new ArrayList<>();
    private List<HoaDonChiTietResponse> lstHDCTResponses = new ArrayList<>();
    private List<ComboResponse> lstComboResponses = new ArrayList<>();
    private IMonAnResponseService monAnResponseService = new MonAnResponseService();
    private IBanResponseService banResponseService = new BanResponseService();
    private IcommonHoaDonResponseService hoaDonResponseService = new HoaDonResponseService();
    private ComboResponseService comboResponseService = new ComboResponseService();
    private ICommonService hds = new HoaDonService();
    private ICommonService mas = new MonAnService();
    private ICommonService cbs = new ComBoService();
    private ICommonService gds = new GiaoDichService();
    private ICommonService nvs = new NhanVienService();
    private ICommonService monAnService = new MonAnService();
    private ICommonService banService = new BanService();
    private IHoaDonChiTietService hdctService = new HoaDonChiTietService();
    private KhachHangService khs = new KhachHangService();
    private IChiTietBanHoaDonService chiTietBanHoaDonService = new ChiTietBanHoaDonService();
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
    private int checkRdo = 3;
    // khi click vào button đồ ăn thì hiện thị lên đồ ăn
    // 1 là đồ ăn, 2 là đồ uống
    private int checkBtnMonAn = 1;
    private List<HoaDon> listHD = new ArrayList<>();
    private HoaDonService hoaDonService = new HoaDonService();
    private HoaDonUtil hoaDonUtil = new HoaDonUtil();
    // thực thể
    private NhanVien nhanV;
    private HoaDon hdTong;
    private Ban ban;
    private List<Loai> listLoai = new ArrayList<>();
    private LoaiService loaiService = new LoaiService();
    private DefaultComboBoxModel dcbmLoaiSP = new DefaultComboBoxModel();
    private List<DanhMuc> listDanhMuc = new ArrayList<>();
    private DanhMucService danhMucService = new DanhMucService();
    private ThanhToanUtil thanhToanUtil = new ThanhToanUtil();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Main main;

    public Form_Home(NhanVien nv) {
        initComponents();
        nhanV = nv;
        tbHoaDon.setModel(dtmHoaDon);
        tbHoaDonCT.setModel(dtmHoaDonCT);
        tbBan.setModel(dtmBan);
        String headerHoaDon[] = {"STT", "MÃ HĐ", "MÃ KH", "Ngày Tạo", "Trạng Thái", "Ghi Chú"};
//        String headerHoaDonCT[] = {"STT", "Tên món ăn", "Giá món ăn", "Số lượng món ăn", "Tên combo", "Giá combo", "Số lượng combo", "Tổng tiền", "Ghi chú"};
        String headerHoaDonCT[] = {"STT", "Tên sản phẩm", "Giá sản phẩm", "Số lượng sản phẩm", "Tổng tiền", "Ghi chú"};
        String headerBan[] = {"STT", "Mã Bàn", "Số lượng chỗ ngồi", "Khu vực", "Trạng thái"};
        loadTableMonAn();
        loadCbbLoaiSP();
        dtmHoaDon.setColumnIdentifiers(headerHoaDon);
        dtmHoaDonCT.setColumnIdentifiers(headerHoaDonCT);
        dtmBan.setColumnIdentifiers(headerBan);
        cbbSanPham.setModel(dcbmLoaiSP);
        lstBanResponses = banResponseService.getAll();
        lstHoaDonResponses = hoaDonResponseService.getAll();
        lstMAReponByDM = monAnResponseService.getByDanhMuc(dcbmLoaiSP.getSelectedItem().toString());
        listMAReponBYKMCT = monAnResponseService.getMonAnJoinKMCT(dcbmLoaiSP.getSelectedItem().toString());
        listMAReponMerge = mergeMonAnRepon(listMAReponBYKMCT, lstMAReponByDM);
        radioTatCa.setSelected(true);
        showDataMonAn(listMAReponMerge);
        showDataBan(lstBanResponses);
        showDataHoaDon(lstHoaDonResponses);
        showDataHDCT(lstHDCTResponses);
        // btnDoAn.setBackground(Color.GRAY);
        txtTienMat.setEnabled(false);
        txtChuyenKhoan.setEnabled(false);
        txtTienThua.setText("0");
        txtTongTien.setEditable(false);
        txtTienThua.setEditable(false);
        txtTenKH.setEditable(false);
        // lbNhanVien.setText(nv.getMa());
    }
//đố biết =)))

    private List<MonAnResponse> mergeMonAnRepon(List<MonAnResponse> listMAKMCT, List<MonAnResponse> listMAByDM) {
        List<MonAnResponse> listMA = new ArrayList<>();
        for (MonAnResponse monAnByKMCT : listMAKMCT) {
            listMA.add(monAnByKMCT);
        }
        for (int i = 0; i < listMAByDM.size(); i++) {
            int checkMADuplicate = 0;
            for (int j = 0; j < listMA.size(); j++) {
                if (listMAByDM.get(i).getMaMonAn().equals(listMA.get(j).getMaMonAn())) {
                    checkMADuplicate++;
                }
            }
            if (checkMADuplicate == 0) {
                listMA.add(lstMAReponByDM.get(i));
            }
        }
        return listMA;
    }

    private void clearFrom() {
        txtTongTien.setText("0");
        txtTienMat.setText("0");
        txtTienThua.setText("0");
        txtChuyenKhoan.setText("0");
        lbMaHD.setText("");
        lbMaHDThanhToan.setText("");
        lbSoBan.setText("");
        cbChuyenKhoan.setSelected(false);
        cbTienMat.setSelected(false);
        txtChuyenKhoan.setEnabled(false);
        txtTienMat.setEnabled(false);
    }

    private void loadCbbLoaiSP() {
        listDanhMuc = danhMucService.getAll();
        for (DanhMuc danhMuc : listDanhMuc) {
            dcbmLoaiSP.addElement(danhMuc.getTenDanhMuc());
        }
        dcbmLoaiSP.addElement("Combo");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        popMenu = new javax.swing.JPopupMenu();
        gopHD = new javax.swing.JMenuItem();
        tachHD = new javax.swing.JMenuItem();
        chuyenBan = new javax.swing.JMenuItem();
        popMenu1 = new javax.swing.JPopupMenu();
        GopBan = new javax.swing.JMenuItem();
        TachBan = new javax.swing.JMenuItem();
        panel = new javax.swing.JLayeredPane();
        panelBorder1 = new com.raven.swing.PanelBorder();
        txtSearch = new com.raven.swing.SearchText();
        jLabel1 = new javax.swing.JLabel();
        cbbSanPham = new javax.swing.JComboBox<>();
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
        jScrollPane7 = new javax.swing.JScrollPane();
        tbBan = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btnThemBan = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        btnHuy = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        txtSdtKH = new javax.swing.JTextField();
        btnSearchKH = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        btnThemKH = new javax.swing.JButton();
        lbMaHDThanhToan = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JTextField();
        txtTongTien = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtChuyenKhoan = new javax.swing.JTextField();
        txtTienMat = new javax.swing.JTextField();
        lbSoBan = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cbTienMat = new javax.swing.JCheckBox();
        cbChuyenKhoan = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();

        gopHD.setText("Gộp hoá đơn");
        gopHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gopHDActionPerformed(evt);
            }
        });
        popMenu.add(gopHD);

        tachHD.setText("Tách hoá đơn");
        tachHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tachHDActionPerformed(evt);
            }
        });
        popMenu.add(tachHD);

        chuyenBan.setText("Chuyển bàn");
        chuyenBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chuyenBanActionPerformed(evt);
            }
        });
        popMenu.add(chuyenBan);

        GopBan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        GopBan.setText("Gộp Bàn");
        GopBan.setToolTipText("");
        GopBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GopBanActionPerformed(evt);
            }
        });
        popMenu1.add(GopBan);

        TachBan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        TachBan.setText("Tách Bàn");
        TachBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TachBanActionPerformed(evt);
            }
        });
        popMenu1.add(TachBan);

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        panelBorder1.setBackground(new java.awt.Color(204, 204, 255));
        panelBorder1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panelBorder1MouseReleased(evt);
            }
        });

        txtSearch.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchCaretUpdate(evt);
            }
        });

        cbbSanPham.setBackground(new java.awt.Color(255, 255, 0));
        cbbSanPham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbbSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đồ Ăn", "Đồ Uống", "ComBo" }));
        cbbSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSanPhamActionPerformed(evt);
            }
        });

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
        tbHoaDonCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHoaDonCTMouseClicked(evt);
            }
        });
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
        radioTatCa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        radioTatCa.setText("Tất Cả");
        radioTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioTatCaActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoChoThanhToan);
        rdoChoThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoChoThanhToan.setText("Chờ Thanh Toán");
        rdoChoThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoChoThanhToanActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoDaHuy);
        rdoDaHuy.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoDaHuy.setText("Đã Hủy");
        rdoDaHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDaHuyActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoDaThanhToan);
        rdoDaThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 39, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 68, Short.MAX_VALUE)
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

        btnThemBan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThemBan.setText("Thêm bàn");
        btnThemBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemBanActionPerformed(evt);
            }
        });

        jComboBox2.setBackground(new java.awt.Color(255, 255, 0));
        jComboBox2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tầng 1", "Tầng 2" }));

        btnHuy.setBackground(new java.awt.Color(255, 51, 0));
        btnHuy.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(204, 204, 204));
        btnClear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("SĐT:");

        btnSearchKH.setBackground(new java.awt.Color(204, 204, 204));
        btnSearchKH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSearchKH.setText("Search");
        btnSearchKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchKHActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("Tên:");

        btnThemKH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThemKH.setText("+");
        btnThemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKHActionPerformed(evt);
            }
        });

        lbMaHDThanhToan.setBackground(new java.awt.Color(204, 204, 255));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Tổng Tiền  :");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Tiền Thừa  :");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Bàn                :");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Tiền Mặt         :");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Chuyển khoản:");

        txtChuyenKhoan.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtChuyenKhoanCaretUpdate(evt);
            }
        });

        txtTienMat.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienMatCaretUpdate(evt);
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

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Mã HĐ       :");

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnThanhToan.setBackground(new java.awt.Color(51, 255, 51));
        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThanhToan.setText("THANH TOÁN");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

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
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnThemBan)
                                .addGap(32, 32, 32)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelBorder1Layout.createSequentialGroup()
                                        .addComponent(cbbSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelBorder1Layout.createSequentialGroup()
                                        .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(101, 101, 101)
                                        .addComponent(btnRefresh))
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 19, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelBorder1Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelBorder1Layout.createSequentialGroup()
                                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                                .addGap(15, 15, 15)
                                                .addComponent(lbMaHDThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(18, 18, 18)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelBorder1Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(18, 18, 18)
                                        .addComponent(lbSoBan, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelBorder1Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtTienMat))
                                    .addGroup(panelBorder1Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtChuyenKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(28, 28, 28)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbTienMat)
                                    .addComponent(jLabel15)
                                    .addComponent(cbChuyenKhoan))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelBorder1Layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(radioTatCa)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoChoThanhToan)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoDaHuy)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoDaThanhToan))
                                    .addGroup(panelBorder1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lbMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
                                    .addComponent(jScrollPane3))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel17))
                                .addGap(18, 18, 18)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelBorder1Layout.createSequentialGroup()
                                        .addComponent(txtSdtKH, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnSearchKH))
                                    .addGroup(panelBorder1Layout.createSequentialGroup()
                                        .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(14, 14, 14)
                                        .addComponent(btnThemKH)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnHuy)
                                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(112, 112, 112))))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radioTatCa)
                    .addComponent(rdoChoThanhToan)
                    .addComponent(rdoDaHuy)
                    .addComponent(jLabel3)
                    .addComponent(rdoDaThanhToan))
                .addGap(9, 9, 9)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemBan)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRefresh))
                        .addContainerGap(32, Short.MAX_VALUE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(lbMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbMaHDThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel9)
                                    .addComponent(lbSoBan, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13)
                                    .addComponent(txtTienMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbTienMat))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel14)
                                    .addComponent(txtChuyenKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbChuyenKhoan))))
                        .addGap(20, 20, 20)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSdtKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(btnSearchKH))
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnThemKH)
                                        .addComponent(btnClear)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnHuy))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(37, 37, 37))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 881, Short.MAX_VALUE)
                .addGap(76, 76, 76))
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDonMouseClicked
        // TODO add your handling code here:
        int index = tbHoaDon.getSelectedRow();
        HoaDonResponse hdr = lstHoaDonResponses.get(index);
        if (hdr.getTrangThai() != 0) {
            btnThanhToan.setEnabled(false);
        } else {
            btnThanhToan.setEnabled(true);
        }
        if (hdr.getTrangThai() != 0) {
            cbChuyenKhoan.setEnabled(false);
            cbTienMat.setEnabled(false);
        } else {
            cbChuyenKhoan.setEnabled(true);
            cbTienMat.setEnabled(true);
        }
        if (evt.getModifiers() == InputEvent.BUTTON3_MASK) {
            if ("".equals(lbMaHDThanhToan.getText())) {
                return;
            }
            if (hdr.getTrangThai() != 0) {
                tachHD.setEnabled(false);
                gopHD.setEnabled(false);
                chuyenBan.setEnabled(false);
            } else {
                tachHD.setEnabled(true);
                gopHD.setEnabled(true);
                chuyenBan.setEnabled(true);

            }
            popMenu.show(this, evt.getX() + 417, evt.getY() + 85);
        } else {
            lstMaBan.clear();// clear để lấy mã bàn mới
            // set lại tiền thừa với tổng tiền  = 0.0 để cho dữ liệu được chính xác hơn
            txtTienThua.setText("0");
            txtTongTien.setText("0");
            // lấy ra hoá đơn response đang chọn
            lbMaHDThanhToan.setText(hdr.getMaHoaDon());
            lbMaHD.setText(hdr.getMaHoaDon());
            // con vớt về hoá đơn
            HoaDon hd = (HoaDon) hds.getOne(lbMaHDThanhToan.getText());
            hdTong = hd;
            // lấy ra những giao dịch có trong hoá đơn đã được chọn
            List<GiaoDich> giaoDichs = gds2.getTheoHoaDon(hd);
//            for (GiaoDich giaoDich : giaoDichs) {
//            }
            //list để fill mã bàn, không khai bảo toàn cục nhé
            List<ChiTietBanHoaDon> lstChiTietBanHoaDons = chiTietBanHoaDonService.getByHoaDon(hd);
            String maBan = lstChiTietBanHoaDons.get(0).getBan().getMaBan().toString();
//        lbSoBan.setText(maBan);
            // fill label so ban
            for (int i = 0; i < lstChiTietBanHoaDons.size(); i++) {
                if (lstChiTietBanHoaDons.size() > 1) {
                    if (i == 0) {
                        continue;
                    }
                    maBan += ", " + lstChiTietBanHoaDons.get(i).getBan().getMaBan().toString();
//                lbSoBan.setText(maBan + ", " + lstChiTietBanHoaDon.getBan().getMaBan());
                    lbSoBan.setText(maBan);
                } else {
                    lbSoBan.setText(lstChiTietBanHoaDons.get(i).getBan().getMaBan().toString());
                }
            }

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
            txtTienMat.setText("0");
            txtChuyenKhoan.setText("");
            // show data hdct theo hd đang chọn
            lstHDCTResponses = hdctResponseService.getAll(hd);
            showDataHDCT(lstHDCTResponses);
//            fillTongTien();
            if (hdr.getTrangThai() == 1) {
                fillTongTien();
                fillTienThuaChuyenKhoan();
                fillTienThuaTienMat();
            }
            // gọi lại hàm để dữ liệu được cập nhập
            // để fill hình thức thanh toán và số tiền
            String tm = "";
            String ck = "";
            for (GiaoDich giaoDich : giaoDichs) {
                if (giaoDich.getHinhThucThanhToan().equals("Tiền mặt")) {
                    tm = giaoDich.getSoTienThanhToan().toString();
//                    txtTienMat.setText(giaoDich.getSoTienThanhToan().toString());
                    System.out.println(giaoDich.getHinhThucThanhToan() + " " + giaoDich.getSoTienThanhToan());
                    System.out.println("có vào tm r nha");
                }
                if (giaoDich.getHinhThucThanhToan().equals("Chuyển khoản")) {
                    ck = giaoDich.getSoTienThanhToan().toString();
//                    txtChuyenKhoan.setText(giaoDich.getSoTienThanhToan().toString());
                    System.out.println(giaoDich.getHinhThucThanhToan() + " " + giaoDich.getSoTienThanhToan());
                    System.out.println("có vào ck r nha");
                }
            }
            txtTienMat.setText(tm);
            txtChuyenKhoan.setText(ck);
        }
    }//GEN-LAST:event_tbHoaDonMouseClicked

    private void tbBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBanMouseClicked
        // TODO add your handling code here:
        // lấy ra bàn đang chọn và fill mã bàn lên label
        if (evt.getModifiers() == InputEvent.BUTTON3_MASK) {
            popMenu1.show(this, evt.getX() + 9, evt.getY() + 396);
            int index = tbBan.getSelectedRow();
            String maBan = lbSoBan.getText();
            BanResponse banResponse = lstBanResponses.get(index);
            if (banResponse.getTrangThai() == 1) {
                GopBan.setEnabled(false);
                TachBan.setEnabled(false);
            } else {
                GopBan.setEnabled(true);
                TachBan.setEnabled(true);
            }
        } else {
            int index = tbBan.getSelectedRow();
            String maBan = lbSoBan.getText();
            BanResponse banResponse = lstBanResponses.get(index);
            // check bàn được click đã có trong list chưa
//        List<ChiTietBanHoaDon> chiTietBanHoaDons = chiTietBanHoaDonService.getByBanAndHoaDon(banResponse.getMaBan().toString());
//        if (chiTietBanHoaDons.size()>0) {
//            JOptionPane.showMessageDialog(this, "Bàn đang có khách");
//        }
            if (banResponse.getTrangThai() == 1) {
                JOptionPane.showMessageDialog(this, "Bàn đang có khách");
                return;
            }
            for (BanResponse banResponse1 : lstMaBan) {
                if (banResponse1.getMaBan() == banResponse.getMaBan()) {
                    JOptionPane.showMessageDialog(this, "Đã có bàn rồi");
                    return;
                }
            }
            // add bàn click vào lstMaBan
            lstMaBan.add(banResponse);
            for (BanResponse banResponse1 : lstMaBan) {
                if (lstMaBan.size() > 1) {
                    lbSoBan.setText(maBan + ", " + banResponse1.getMaBan());
                } else {
                    lbSoBan.setText(banResponse1.getMaBan().toString());
                }
            }
        }
    }//GEN-LAST:event_tbBanMouseClicked

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        // TODO add your handling code here:
        // check lb mã bàn rỗng thì k cho tạo
        if ("".equals(lbSoBan.getText())) {
            JOptionPane.showMessageDialog(this, "Chọn bàn trước");
        } else {
            listHD = hoaDonService.getAll();
            String maHD = hoaDonUtil.zenMaThuyDuong(listHD);
            java.util.Date ngayTao = new java.util.Date();
            String ngayThanhToan = new HoaDonUtil().layNgay();

            // fixx cứng nv
            NhanVien nhanVien = (NhanVien) nvs.getOne(nhanV.getMa());
            HoaDon hd = new HoaDon();
            //  HoaDon hd = new HoaDon(null, maHD, nhanVien, null, ngayTao, Date.valueOf(ngayThanhToan), null, null, 0);
            hd.setMaHoaDon(maHD);
            hd.setNhanVien(nhanVien);
            hd.setNgayTao(ngayTao);
            hd.setTrangThai(0);
            int soLuongChoNgoi = 0;
            for (BanResponse banResponse : lstMaBan) {
                soLuongChoNgoi += banResponse.getSoLuongChoNgoi();
            }
            for (BanResponse banResponse : lstMaBan) {
                // lấy bàn theo mã bàn lấy từ banResponse
                Ban ban = (Ban) banService.getOne(banResponse.getMaBan().toString());
                if (ban.getTrangThai() == 1) {
                    JOptionPane.showMessageDialog(this, "Bàn đang có khách");
                    return;
                } else {
                    String soLuongKhach = "";
                    //check số lượng nhập vào

                    do {
                        soLuongKhach = JOptionPane.showInputDialog("Mời nhập số lượng khách");
                    } while (checkSoLuong(soLuongKhach) == false);
                    // tạo hd
                    if (Integer.valueOf(soLuongKhach) > soLuongChoNgoi) {
                        JOptionPane.showMessageDialog(this, "Số lượng chỗ ngồi không đủ");
                        return;
                    } else {
                        hd.setSoLuongKhach(Integer.valueOf(soLuongKhach));
                        JOptionPane.showMessageDialog(this, hds.add(hd));
                        lbMaHDThanhToan.setText(maHD);
                        // set bàn đang có khách
                        ban.setTrangThai(1);
                        //update lại trạng thái bàn
                        String setTrangThaiBan = (String) banService.update(ban, ban.getMaBan().toString());
                        HoaDon hd2 = (HoaDon) hds.getOne(maHD);// lấy hoá đơn vừa được tạo và add vào chiTietHoaDon
//                    ChiTietBanHoaDon chiTietBanHoaDon = new ChiTietBanHoaDon();
//                    chiTietBanHoaDon.setHd(hd2);
//                    chiTietBanHoaDon.setBan(ban);
                        ChiTietBanHoaDon chiTietBanHoaDon = new ChiTietBanHoaDon(null, hd2, ban);
//                    System.out.println(chiTietBanHoaDon.getHd().getId()+" "+chiTietBanHoaDon.getBan().getId());
                        String addChiTietBanHoaDon = (String) chiTietBanHoaDonService.add(chiTietBanHoaDon);
                    }
                }
            }

            // check xem đang ở radio nào thì show dữ liệu của radio đấy
            if (checkRdo == 3) {
                lstHoaDonResponses = hoaDonResponseService.getAll();
                showDataHoaDon(lstHoaDonResponses);
            } else if (checkRdo == 1) {
                lstHoaDonResponses = hoaDonResponseService.getByTrangThai(1);
                showDataHoaDon(lstHoaDonResponses);
            } else if (checkRdo == 2) {
                lstHoaDonResponses = hoaDonResponseService.getByTrangThai(2);
                showDataHoaDon(lstHoaDonResponses);
            } else {
                lstHoaDonResponses = hoaDonResponseService.getByTrangThai(0);
                showDataHoaDon(lstHoaDonResponses);
            }
            lstBanResponses = banResponseService.getAll();
            showDataBan(lstBanResponses);
            lstMaBan.clear();
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

    private void cbbSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSanPhamActionPerformed
        // TODO add your handling code here:
        String tenDanhMuc = dcbmLoaiSP.getSelectedItem().toString();
        if (tenDanhMuc.equalsIgnoreCase("Combo")) {
            lstComboResponses = comboResponseService.getAll();
            loadDataCombo(lstComboResponses);
        } else {
            lstMAReponByDM = monAnResponseService.getByDanhMuc(dcbmLoaiSP.getSelectedItem().toString());
            listMAReponBYKMCT = monAnResponseService.getMonAnJoinKMCT(dcbmLoaiSP.getSelectedItem().toString());
            listMAReponMerge = mergeMonAnRepon(listMAReponBYKMCT, lstMAReponByDM);
            loadTableMonAn();
            showDataMonAn(listMAReponMerge);
        }
//        if (cbbSanPham.getSelectedItem().equals("Đồ Ăn")) {
//            loadTableMonAn();
//            lstMonAnResponses = monAnResponseService.getByDanhMuc("Đồ ăn");
//            showDataMonAn(lstMonAnResponses);
//        } else if (cbbSanPham.getSelectedItem().equals("Đồ Uống")) {
//            loadTableMonAn();
//            lstMonAnResponses = monAnResponseService.getByDanhMuc("Đồ uống");
//            showDataMonAn(lstMonAnResponses);
//        } else {
//            lstComboResponses = comboResponseService.getAll();
//            loadDataCombo(lstComboResponses);
//        }
    }//GEN-LAST:event_cbbSanPhamActionPerformed

    private void tbMonAnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMonAnMouseClicked
        // TODO add your handling code here:
        String cbbSP = cbbSanPham.getSelectedItem().toString();
        if (cbbSP.equals("Combo")) {
            if (checkTrangThaiHD == 1) {
                JOptionPane.showMessageDialog(this, "Không thể thêm sản phẩm");
            } else if ("".equals(lbMaHDThanhToan.getText())) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn hoá đơn");
            } else {
                String soLuong2 = JOptionPane.showInputDialog("Mời bạn nhập số lượng");
                if (null == soLuong2) {
                    return;
                }
                if ("".equals(soLuong2)) {
                    JOptionPane.showMessageDialog(this, "Số lượng không được trống");
                    return;
                }
                if (!soLuong2.matches("\\d+")) {
                    JOptionPane.showMessageDialog(this, "Số lượng phải là số");
                    return;
                }
                int soLuong = Integer.valueOf(soLuong2);
                int index = tbMonAn.getSelectedRow();
                ComboResponse cbr = lstComboResponses.get(index);
                ComBo cb = (ComBo) cbs.getOne(cbr.getMaCB());
                HoaDon hd = (HoaDon) hds.getOne(lbMaHDThanhToan.getText());
                if (hdctService.getOneHDCTByCombo(hd, cb) != null) {
                    HoaDonChiTiet hdctLaySoLuongCu = (HoaDonChiTiet) hdctService.getOneHDCTByCombo(hd, cb);
                    HoaDonChiTiet hdct = new HoaDonChiTiet(null, null, hd, cb, 0, BigDecimal.valueOf(0), soLuong + hdctLaySoLuongCu.getSoLuongCombo(), cb.getDonGia(), null);
                    String updateHDCT = (String) hdctService.updateSoLuongCombo(hdct, hd, cb);
                    lstHDCTResponses = hdctResponseService.getAll(hd);
                    showDataHDCT(lstHDCTResponses);
                    fillTongTien();
                } else {
                    HoaDonChiTiet hdct = new HoaDonChiTiet(null, null, hd, cb, 0, BigDecimal.valueOf(0), soLuong, cb.getDonGia(), null);
                    String addHDCT = (String) hdctService.add(hdct);
                    lstHDCTResponses = hdctResponseService.getAll(hd);
                    showDataHDCT(lstHDCTResponses);
                    fillTongTien();
                }
            }
        } else {
            if (checkTrangThaiHD == 1) {
                JOptionPane.showMessageDialog(this, "Không thể thêm sản phẩm");
                return;
            } else if ("".equals(lbMaHDThanhToan.getText())) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn hoá đơn");
                return;
            } else {
                String soLuong2 = JOptionPane.showInputDialog("Mời bạn nhập số lượng");
                if (null == soLuong2) {
                    return;
                }
                if ("".equals(soLuong2)) {
                    JOptionPane.showMessageDialog(this, "Số lượng không được trống");
                    return;
                }
                if (!soLuong2.matches("\\d+")) {
                    JOptionPane.showMessageDialog(this, "Số lượng phải là số");
                    return;
                }
                int soLuong = Integer.valueOf(soLuong2);
                int index = tbMonAn.getSelectedRow();
                lstMAReponByDM = monAnResponseService.getByDanhMuc(dcbmLoaiSP.getSelectedItem().toString());
                listMAReponBYKMCT = monAnResponseService.getMonAnJoinKMCT(dcbmLoaiSP.getSelectedItem().toString());
                listMAReponMerge = mergeMonAnRepon(listMAReponBYKMCT, lstMAReponByDM);
                MonAnResponse mar = listMAReponMerge.get(index);// lấy ra món ăn đang chọn
                MonAn ma = (MonAn) mas.getOne(mar.getMaMonAn());// chuyển đổi về món ăn để add vào hdct
                //set lại giá cho món ăn là giá sau KM:
                ma.setDonGia(mar.getDonGiaSauKM());
                HoaDon hd = (HoaDon) hds.getOne(lbMaHDThanhToan.getText());
                // khai báo hdct để add
//                HoaDonChiTiet hdct = new HoaDonChiTiet(null, ma, hd, null, soLuong, mar.getDonGiaSauKM(), 0, BigDecimal.valueOf(0), null);
                //add hdct
                if (hdctService.getOneHDCTByMAHD(hd, ma) != null) {
                    HoaDonChiTiet hdctLaySoLuongCu = (HoaDonChiTiet) hdctService.getOneHDCTByMAHD(hd, ma);
                    HoaDonChiTiet hdct = new HoaDonChiTiet(null, ma, hd, null, soLuong + hdctLaySoLuongCu.getSoLuongMonAn(), mar.getDonGiaSauKM(), 0, BigDecimal.valueOf(0), null);
                    String updateHDCT = (String) hdctService.updateSoLuongMonAn(hdct, hd, ma);
                    lstHDCTResponses = hdctResponseService.getAll(hd);
                    showDataHDCT(lstHDCTResponses);
//                    fillTongTien();
                } else {
                    HoaDonChiTiet hdct = new HoaDonChiTiet(null, ma, hd, null, soLuong, mar.getDonGiaSauKM(), 0, BigDecimal.valueOf(0), null);
//                    String addHDCT = (String) hdctService.add(hdct);
//                    lstHDCTResponses = hdctResponseService.getAll(hd);
//                    showDataHDCT(lstHDCTResponses);
//                    fillTongTien();
                    String addHDCT = (String) hdctService.add(hdct);
                    lstHDCTResponses = hdctResponseService.getAll(hd);
                    showDataHDCT(lstHDCTResponses);
                    //gọi lại fill tổng tiền để cập nhập lại tổng tiền mỗi khi thêm món ăn vào hdct
//                    fillTongTien();
                    return;
                }
            }
        }
    }//GEN-LAST:event_tbMonAnMouseClicked

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        dtmHoaDonCT.setRowCount(0);
        lstMaBan.clear();
        lbMaHD.setText("");
        lbMaHDThanhToan.setText("");
        lbSoBan.setText("");
        txtChuyenKhoan.setText("0");
        txtTienMat.setText("0");
        txtTienThua.setText("0");
        txtTongTien.setText("0");
        txtSdtKH.setText("");
        txtTenKH.setText("");
        dcbmLoaiSP.setSelectedItem("Đồ ăn");
        lstMAReponByDM = monAnResponseService.getByDanhMuc("Đồ ăn");
        listMAReponBYKMCT = monAnResponseService.getMonAnJoinKMCT("Đồ ăn");
        listMAReponMerge = mergeMonAnRepon(listMAReponBYKMCT, lstMAReponByDM);
        loadTableMonAn();
        showDataMonAn(listMAReponMerge);
    }//GEN-LAST:event_btnClearActionPerformed

    private void txtSearchCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchCaretUpdate
        // TODO add your handling code here:
//        if ("".equals(txtSearch.getText())) {
//            return;
//        }
        if (cbbSanPham.getSelectedItem().equals("Combo")) {
            String search = txtSearch.getText();
            if ("".equals(search)) {
                lstComboResponses = comboResponseService.getAll();
                loadDataCombo(lstComboResponses);
            } else {
                if (!search.matches("\\d+")) {
                    lstComboResponses = comboResponseService.getByTenComBo(search);
                    loadDataCombo(lstComboResponses);
                } else {
                    lstComboResponses = comboResponseService.getByDonGia(new BigDecimal(search));
                    loadDataCombo(lstComboResponses);
                }
            }
        } else if (cbbSanPham.getSelectedItem().equals("Đồ ăn")) {
            String search = txtSearch.getText();
            if ("".equals(search)) {
                lstMAReponByDM = monAnResponseService.getByDanhMuc("Đồ ăn");
                showDataMonAn(lstMAReponByDM);
//                JOptionPane.showMessageDialog(this, "Có if nhé");
            } else {
                if (!search.matches("\\d+")) {
                    lstMAReponByDM = monAnResponseService.getByDanhMucAndTenMonAn(search, "Đồ ăn");
                    List<MonAnResponse> lstSearch = new ArrayList<>();
                    for (MonAnResponse monAnResponse : lstMAReponByDM) {
                        MonAn ma = (MonAn) mas.getOne(monAnResponse.getMaMonAn());
                        if (ma.getLoai().getDanhMuc().getTenDanhMuc().equals("Đồ ăn")) {
                            lstSearch.add(monAnResponse);
                        }
                    }
                    showDataMonAn(lstSearch);
                } else {
                    lstMAReponByDM = monAnResponseService.getByDanhMucAndDonGia(new BigDecimal(search), "Đồ ăn");
                    List<MonAnResponse> lstSearch = new ArrayList<>();
                    for (MonAnResponse monAnResponse : lstMAReponByDM) {
                        MonAn ma = (MonAn) mas.getOne(monAnResponse.getMaMonAn());
                        if (ma.getLoai().getDanhMuc().getTenDanhMuc().equals("Đồ ăn")) {
                            lstSearch.add(monAnResponse);
                        }
                    }
                    showDataMonAn(lstSearch);
                }
            }
        } else {
            String search = txtSearch.getText();
            if ("".equals(search)) {
                lstMAReponByDM = monAnResponseService.getByDanhMuc("Đồ uống");
                showDataMonAn(lstMAReponByDM);
//                JOptionPane.showMessageDialog(this, "Có if nhé");
            } else {
                if (!search.matches("\\d+")) {
                    lstMAReponByDM = monAnResponseService.getByDanhMucAndTenMonAn(search, "Đồ uống");
                    List<MonAnResponse> lstSearch = new ArrayList<>();
                    for (MonAnResponse monAnResponse : lstMAReponByDM) {
                        MonAn ma = (MonAn) mas.getOne(monAnResponse.getMaMonAn());
                        if (ma.getLoai().getDanhMuc().getTenDanhMuc().equals("Đồ uống")) {
                            lstSearch.add(monAnResponse);
                        }
                    }
                    showDataMonAn(lstSearch);
                } else {
                    lstMAReponByDM = monAnResponseService.getByDanhMucAndDonGia(new BigDecimal(search), "Đồ uống");
                    List<MonAnResponse> lstSearch = new ArrayList<>();
                    for (MonAnResponse monAnResponse : lstMAReponByDM) {
                        MonAn ma = (MonAn) mas.getOne(monAnResponse.getMaMonAn());
                        if (ma.getLoai().getDanhMuc().getTenDanhMuc().equals("Đồ uống")) {
                            lstSearch.add(monAnResponse);
                        }
                    }
                    showDataMonAn(lstSearch);

                }
            }
        }
    }//GEN-LAST:event_txtSearchCaretUpdate

    private void btnThemBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemBanActionPerformed
        JDialogThemNhanhBan viewThemNhanhBan = new JDialogThemNhanhBan(null, true);
        viewThemNhanhBan.setVisible(true);
    }//GEN-LAST:event_btnThemBanActionPerformed
// sự kiện của chuột phải vào hoá đơn
    private void gopHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gopHDActionPerformed
        // TODO add your handling code here:
        JDialogGopHoaDon dialogGopHoaDon = new JDialogGopHoaDon(null, true, hdTong);
        dialogGopHoaDon.setVisible(true);
    }//GEN-LAST:event_gopHDActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        //btnClearActionPerformed(evt);
        lstBanResponses = banResponseService.getAll();
        lstHoaDonResponses = hoaDonResponseService.getByTrangThai(0);
        lstMAReponByDM = monAnResponseService.getByDanhMuc(dcbmLoaiSP.getSelectedItem().toString());
        listMAReponBYKMCT = monAnResponseService.getMonAnJoinKMCT(dcbmLoaiSP.getSelectedItem().toString());
        listMAReponMerge = mergeMonAnRepon(listMAReponBYKMCT, lstMAReponByDM);
        dtmHoaDonCT.setRowCount(0);
        loadTableMonAn();
        rdoChoThanhToan.setSelected(true);
        showDataMonAn(listMAReponMerge);
        showDataHoaDon(lstHoaDonResponses);
        showDataBan(lstBanResponses);
        cbbSanPham.setSelectedIndex(0);
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void tachHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tachHDActionPerformed
        // TODO add your handling code here:
        HoaDon hd = hdTong;
        JDialogTachHoaDon jDialogTachHoaDon = new JDialogTachHoaDon(null, true, hd);
        jDialogTachHoaDon.setVisible(true);
    }//GEN-LAST:event_tachHDActionPerformed

    private void panelBorder1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorder1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_panelBorder1MouseReleased

    private void TachBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TachBanActionPerformed
        // TODO add your handling code here:
        int index = tbBan.getSelectedRow();
        String ma = dtmBan.getValueAt(index, 1).toString();
        Ban bans = (Ban) banService.getOne(ma);
        JDialogTachBan viewTachBan = new JDialogTachBan(null, true, bans);
        viewTachBan.setVisible(true);
    }//GEN-LAST:event_TachBanActionPerformed

    private void GopBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GopBanActionPerformed
        // TODO add your handling code here:
        int index = tbBan.getSelectedRow();
        String ma = dtmBan.getValueAt(index, 1).toString();
        Ban bans = (Ban) banService.getOne(ma);
        JDialogGopBan viewGopBan = new JDialogGopBan(null, true, bans);
        viewGopBan.setVisible(true);

    }//GEN-LAST:event_GopBanActionPerformed

    private void chuyenBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chuyenBanActionPerformed
        // TODO add your handling code here:
        JDialogChuyenBan chuyenBan = new JDialogChuyenBan(null, true, hdTong);
        chuyenBan.setVisible(true);
    }//GEN-LAST:event_chuyenBanActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        //check xem có đang chọn hoá đơn nào không
        if (lbMaHD.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hoá đơn!");
            return;
        } else {
            //có chọn hd=> getOne
            HoaDon hoaDon = hoaDonService.getOne(lbMaHD.getText());
            //kiểm tra trạng thái nếu là chờ thanh toán: 
            if (hoaDon.getTrangThai() == 0) {
                List<HoaDonChiTiet> listHDCT = hdctService.getHDCTByHD(hoaDon);
                //nếu list > 0
                if (listHDCT.size() > 0) {
                    //set thuộc tính cho hoá đơn thanh tonas custom:
                    HoaDonThanhToanCustom hoaDonThanhToanCustom = new HoaDonThanhToanCustom();
                    //hoaDonThanhToanCustom.setGhiChu(txt);
                    hoaDonThanhToanCustom.setListBan(lbSoBan.getText());
                    hoaDonThanhToanCustom.setMaHD(hoaDon.getMaHoaDon());
                    hoaDonThanhToanCustom.setMaNV(hoaDon.getNhanVien().getMa());
                    if (hoaDon.getKhachHang() != null) {
                        hoaDonThanhToanCustom.setMaKH(hoaDon.getKhachHang().getMa());
                    } else {
                        hoaDonThanhToanCustom.setMaKH("");
                    }
                    hoaDonThanhToanCustom.setNgayTao(hoaDon.getNgayTao());
                    hoaDonThanhToanCustom.setNgayThanhToan(null);
                    // hoaDonThanhToanCustom.setTienDuocGiam(new BigDecimal(txt));
                    //tiền được gianmr chưa có
                    hoaDonThanhToanCustom.setTienThua(new BigDecimal(0));
                    hoaDonThanhToanCustom.setTongTien(new BigDecimal(txtTongTien.getText()));
                    hoaDonThanhToanCustom.setTienChuyenKhoan(new BigDecimal(0));
                    hoaDonThanhToanCustom.setTienMat(new BigDecimal(0));
                    JDialogThanhToan viewThanhToan = new JDialogThanhToan(null, true, hoaDonThanhToanCustom);
                    viewThanhToan.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Chưa có món ăn nào!");
                    return;
                }
            }
        }

    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnThemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKHActionPerformed
        // TODO add your handling code here:
        JDialogThemNhanhKhachHang viewThemNhanhKH = new JDialogThemNhanhKhachHang(null, true);
        viewThemNhanhKH.setVisible(true);
    }//GEN-LAST:event_btnThemKHActionPerformed

    private void tbHoaDonCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDonCTMouseClicked
        // TODO add your handling code here:
        int index = tbHoaDonCT.getSelectedRow();
        HoaDonChiTietResponse hdctR = lstHDCTResponses.get(index);
        String ghiChu = "";
        String soLuong = "";
        do {
            soLuong = JOptionPane.showInputDialog("Mời nhập số lượng");
            if (null == soLuong) {
                return;
            }
        } while (nhapSoLuong(soLuong) == false);
        do {
            ghiChu = JOptionPane.showInputDialog("Bắt buộc phải nhập ghi chú");
        } while ("".equals(ghiChu));
        if (null == ghiChu) {
            return;
        }
//        if (Integer.valueOf(soLuong) <= 0) {
//            if (hdctR.getMaMonAn() == null) {
//                int checkXoa = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xoá chứ?");
//                if (checkXoa == JOptionPane.YES_OPTION) {
//                    ComBo cb = (ComBo) cbs.getOne(hdctR.getMaCombo());
//                    HoaDon hd = (HoaDon) hds.getOne(lbMaHDThanhToan.getText());
//                    JOptionPane.showMessageDialog(this, hdctService.removeCombo(hd, cb));
//                    lstHDCTResponses = hdctResponseService.getAll(hd);
//                    showDataHDCT(lstHDCTResponses);
//                } else {
//                    return;
//                }
//            } else {
//                int checkXoa = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xoá chứ?");
//                if (checkXoa == JOptionPane.YES_OPTION) {
//                    MonAn ma = (MonAn) mas.getOne(hdctR.getMaMonAn());
//                    HoaDon hd = (HoaDon) hds.getOne(lbMaHDThanhToan.getText());
//                    JOptionPane.showMessageDialog(this, hdctService.removeMonAn(hd, ma));
//                    lstHDCTResponses = hdctResponseService.getAll(hd);
//                    showDataHDCT(lstHDCTResponses);
//                } else {
//                    return;
//                }
//            }
//        } else {
        if (hdctR.getMaMonAn() == null) {
            ComBo cb = (ComBo) cbs.getOne(hdctR.getMaCombo());
            HoaDon hd = (HoaDon) hds.getOne(lbMaHDThanhToan.getText());
            HoaDonChiTiet hdct = new HoaDonChiTiet(null, null, hd, cb, 0, BigDecimal.valueOf(0), Integer.valueOf(soLuong), cb.getDonGia(), ghiChu);
            int checkUpdate = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa không?");
            if (checkUpdate == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, hdctService.updateSoLuongCombo(hdct, hd, cb));
                lstHDCTResponses = hdctResponseService.getAll(hd);
                showDataHDCT(lstHDCTResponses);
            } else {
                return;
            }
        } else {
            MonAn ma = (MonAn) mas.getOne(hdctR.getMaMonAn());
            HoaDon hd = (HoaDon) hds.getOne(lbMaHDThanhToan.getText());
            HoaDonChiTiet hdct = new HoaDonChiTiet(null, ma, hd, null, Integer.valueOf(soLuong), hdctR.getDonGiaMonAn(), 0, BigDecimal.valueOf(0), ghiChu);
            int checkUpdate = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa không?");
            if (checkUpdate == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, hdctService.updateSoLuongMonAn(hdct, hd, ma));
                lstHDCTResponses = hdctResponseService.getAll(hd);
                showDataHDCT(lstHDCTResponses);
            } else {
                return;
            }
        }
//        }
    }//GEN-LAST:event_tbHoaDonCTMouseClicked

    private void btnSearchKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchKHActionPerformed
        // TODO add your handling code here:
        String sdt = txtSdtKH.getText();
        KhachHang kh = khs.getOneBySdt(sdt);
        if (kh == null) {
            int checkThemKH = JOptionPane.showConfirmDialog(this, "Chưa có khách hàng, bạn có muốn thêm không?");
            if (checkThemKH == JOptionPane.YES_OPTION) {
                JDialogThemNhanhKhachHang viewThemNhanhKH = new JDialogThemNhanhKhachHang(null, true);
                viewThemNhanhKH.setVisible(true);
            }
        } else {
            txtTenKH.setText(kh.getTen());
        }
    }//GEN-LAST:event_btnSearchKHActionPerformed

    private void fillTienThuaChuyenKhoan() {
//        txtTienMat.setText("0");
//        String tienMat = txtTienMat.getText();
//        String chuyenKhoan = txtChuyenKhoan.getText();
//        // nếu dữ liệu rỗng thì set ngầm dữ liệu lấy về bằng = 0
//        if ("".equals(tienMat)) {
//            tienMat = "0";
//        }
//        if ("".equals(chuyenKhoan)) {
//            chuyenKhoan = "0";
//        }
//        try {
//            Double tienThua = Double.valueOf(txtTienThua.getText());
//            tienThua = (Double.valueOf(tienMat) + (Double.valueOf(chuyenKhoan))) - Double.valueOf(txtTongTien.getText());
//            txtTienThua.setText(tienThua.toString());
//        } catch (Exception e) {
//        }
//        txtTienMat.setText("0");
        String tienMat = txtTienMat.getText();
        String chuyenKhoan = txtChuyenKhoan.getText();
        // nếu dữ liệu rỗng thì set ngầm dữ liệu lấy về bằng = 0
        if ("".equals(tienMat)) {
            tienMat = "0";
        }
        if ("".equals(chuyenKhoan)) {
            chuyenKhoan = "0";
        }
        try {
            BigDecimal tienMatB = new BigDecimal(tienMat);
            BigDecimal tienCK = new BigDecimal(chuyenKhoan);
            BigDecimal tienKhachTra = tienMatB.add(tienCK);
            //BigDecimal tienThua = new BigDecimal(txtTienThua.getText());
            BigDecimal tienThua = null;
            BigDecimal tongTien = new BigDecimal(txtTongTien.getText());

            tienThua = tienKhachTra.subtract(tongTien);
            txtTienThua.setText(tienThua.toString());
        } catch (Exception e) {
        }

    }

    private void fillTienThuaTienMat() {
//        txtChuyenKhoan.setText("0");
//        String chuyenKhoan = txtChuyenKhoan.getText();
//        String tienMat = txtTienMat.getText();
//        if ("".equals(chuyenKhoan)) {
//            chuyenKhoan = "0";
//        }
//        if ("".equals(tienMat)) {
//            tienMat = "0";
//        }
//        try {
//            Double tienThua = Double.valueOf(txtTienThua.getText());
//            tienThua = (Double.valueOf(tienMat) + Double.valueOf(chuyenKhoan)) - Double.valueOf(txtTongTien.getText());
//            txtTienThua.setText(tienThua.toString());
//        } catch (Exception e) {
//        }
//        fillTongTien();
        txtChuyenKhoan.setText("0");
        String chuyenKhoan = txtChuyenKhoan.getText();
        String tienMat = txtTienMat.getText();//500
        if ("".equals(chuyenKhoan)) {
            chuyenKhoan = "0";
        }
        if ("".equals(tienMat)) {
            tienMat = "0";
        }
        try {
            BigDecimal tienMatB = new BigDecimal(tienMat);
            BigDecimal tienCK = new BigDecimal(chuyenKhoan);
            BigDecimal tienThua = new BigDecimal(txtTienThua.getText());
            BigDecimal tongTien = new BigDecimal(txtTongTien.getText());
            tienMatB.add(tienCK);
            tienThua = tienMatB.subtract(tongTien);
//            System.out.println("Tiền mặt-" + tienMatB);
//            System.out.println("Tổng tiền-" + tongTien);
            txtTienThua.setText(tienThua.toString());
        } catch (Exception e) {
        }
    }

    private void fillTongTien() {
        HoaDon hd = (HoaDon) hds.getOne(lbMaHD.getText());
        txtTongTien.setText(hd.getTongTien().toString());

//        BigDecimal tongTien = BigDecimal.valueOf(0);
//        BigDecimal tienMA = BigDecimal.valueOf(0);
//        BigDecimal tienCB = BigDecimal.valueOf(0);
//        for (HoaDonChiTietResponse lstHDCTResponse : lstHDCTResponses) {
//            BigDecimal soLuongMA = new BigDecimal(lstHDCTResponse.getSoLuongMonAn());
//            BigDecimal soLuongCB = new BigDecimal(lstHDCTResponse.getSoLuongCombo());
//            tienMA = tienMA.add(lstHDCTResponse.getDonGiaMonAn().multiply(soLuongMA));
//            tienCB = tienCB.add(lstHDCTResponse.getDonGiaCombo().multiply(soLuongCB));
//        }
//        txtTongTien.setText(String.valueOf(tienCB.add(tienMA)));
    }

    private void loadTableCombo() {
        String header[] = {"STT", "Mã Combo", "Tên Combo", "Đơn giá"};
        tbMonAn.setModel(dtmCombo);
        dtmCombo.setColumnIdentifiers(header);
    }

    private void loadTableMonAn() {
        String headerMonAn[] = {"STT", "Loại món ăn", "Mã món ăn", "Tên món ăn", "Đơn giá", "Sau KM", "Đơn vị tính"};
        tbMonAn.setModel(dtmMonAn);
        dtmMonAn.setColumnIdentifiers(headerMonAn);
    }

    private void loadTableDoUong() {
        String headersDoUong[] = {"STT", "Loại đồ uống", "Mã đồ uống", "Tên đồ uống", "Đơn giá", "Đơn giá sau KM", "Đơn vị tính"};
        tbMonAn.setModel(dtmDoUong);
        dtmDoUong.setColumnIdentifiers(headersDoUong);
    }

    private void showDataMonAn(List<MonAnResponse> monAnResponses) {

        dtmMonAn.setRowCount(0);
        int stt = 0;
        for (MonAnResponse monAnResponse : monAnResponses) {
            stt++;
            dtmMonAn.addRow(new Object[]{stt, monAnResponse.getLoaiMonAn(), monAnResponse.getMaMonAn(), monAnResponse.getTenMonAn(),
                monAnResponse.getDonGia(), monAnResponse.getDonGiaSauKM(), monAnResponse.getDonViTinh()});
            //dtmMonAn.addRow(monAnResponse.toDataRow(stt));
        }
    }

    private void showDataHDCT(List<HoaDonChiTietResponse> hoaDonChiTietResponses) {
        dtmHoaDonCT.setRowCount(0);
        int stt = 0;
        for (HoaDonChiTietResponse hoaDonChiTietResponse : hoaDonChiTietResponses) {
            stt++;
            dtmHoaDonCT.addRow(hoaDonChiTietResponse.toDataRow2(stt));
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
        String headerCombo[] = {"Stt", "Mã combo", "Tên combo", "Đơn giá combo"};
        tbMonAn.setModel(dtmCombo);
        dtmCombo.setColumnIdentifiers(headerCombo);
        dtmCombo.setRowCount(0);
        int stt = 0;
        for (ComboResponse comboResponse : comboResponses) {
            stt++;
            dtmCombo.addRow(comboResponse.toDataRow(stt));
        }
    }

    private boolean nhapSoLuong(String soLuong) {
        if (!soLuong.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số");
            return false;
        } else {
            return true;
        }
    }

    private boolean checkSoLuong(String soLuong) {
//        if (soLuong < 0) {
//            JOptionPane.showMessageDialog(this, "Số lượng phải > 0");
//            return false;
//        } else {
//            return true;
//        }
        if (!soLuong.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số");
            return false;
        } else if (Integer.valueOf(soLuong) < 0) {
            JOptionPane.showMessageDialog(this, "Số lượng phải > 0");
            return false;
        } else {
            return true;
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem GopBan;
    private javax.swing.JMenuItem TachBan;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearchKH;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThemBan;
    private javax.swing.JButton btnThemKH;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cbChuyenKhoan;
    private javax.swing.JCheckBox cbTienMat;
    private javax.swing.JComboBox<String> cbbSanPham;
    private javax.swing.JMenuItem chuyenBan;
    private javax.swing.JMenuItem gopHD;
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
    private javax.swing.JLabel lbMaHD;
    private javax.swing.JLabel lbMaHDThanhToan;
    private javax.swing.JLabel lbSoBan;
    private javax.swing.JLayeredPane panel;
    private com.raven.swing.PanelBorder panelBorder1;
    private javax.swing.JPopupMenu popMenu;
    private javax.swing.JPopupMenu popMenu1;
    private javax.swing.JRadioButton radioTatCa;
    private javax.swing.JRadioButton rdoChoThanhToan;
    private javax.swing.JRadioButton rdoDaHuy;
    private javax.swing.JRadioButton rdoDaThanhToan;
    private javax.swing.JMenuItem tachHD;
    private javax.swing.JTable tbBan;
    private javax.swing.JTable tbHoaDon;
    private javax.swing.JTable tbHoaDonCT;
    private javax.swing.JTable tbMonAn;
    private javax.swing.JTextField txtChuyenKhoan;
    private javax.swing.JTextField txtSdtKH;
    private com.raven.swing.SearchText txtSearch;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTienMat;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
