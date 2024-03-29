/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.form;

import com.mycompany.customModel.MonAnCoKM;
import com.mycompany.customModel.MonAnKMResponse;
import com.mycompany.domainModel.KhuyenMai;
import com.mycompany.domainModel.KhuyenMaiChiTiet;
import com.mycompany.domainModel.MonAn;
import com.mycompany.domainModel.NhanVien;
import com.mycompany.repository.impl.KhuyenMaiRepository;
import com.mycompany.service.impl.KhuyenMaiChiTiettService;
import com.mycompany.service.impl.KhuyenMaiService;
import com.mycompany.service.impl.MonAnService;
import com.mycompany.service.impl.NhanVienService;
import com.mycompany.util.KhuyenMaiUtil;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Color;
import java.awt.Font;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author RAVEN
 */
public class FormKhuyenMai extends javax.swing.JPanel {

    private DefaultTableModel dtmKhuyenMai = new DefaultTableModel();
    private DefaultTableModel dtmKMCT = new DefaultTableModel();
    private List<KhuyenMai> listKM = new ArrayList<>();
    private KhuyenMaiRepository khuyenMaiRepository = new KhuyenMaiRepository();
    private DefaultComboBoxModel dcbmLoaiKM = new DefaultComboBoxModel();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private KhuyenMaiService khuyenMaiService = new KhuyenMaiService();
    private NhanVien nhanV;
    private java.util.Date today = new java.util.Date();
    private List<MonAn> listMonAn = new ArrayList<>();
    private MonAnService monAnService = new MonAnService();
    private DefaultTableModel dtmMonAn = new DefaultTableModel();
    private List<KhuyenMaiChiTiet> listCTKM = new ArrayList<>();
    private List<MonAn> listThemKM_MA = new ArrayList<>();
    private NhanVienService nhanVienService = new NhanVienService();
    private DefaultComboBoxModel dcbmMonAn = new DefaultComboBoxModel();
    private KhuyenMaiChiTiettService khuyenMaiChiTiettService = new KhuyenMaiChiTiettService();
    private KhuyenMaiUtil khuyenMaiUtil = new KhuyenMaiUtil();

    public FormKhuyenMai(NhanVien nv) {
        initComponents();
        dtmMonAn = (DefaultTableModel) tbMonAn.getModel();
        dtmKhuyenMai = (DefaultTableModel) tbKhuyenMai.getModel();
        dtmKMCT = (DefaultTableModel) tbKMCT.getModel();
        this.nhanV = nv;
        // tbKhuyenMai.setModel(dtmKhuyenMai);
        String headers[] = {"STT", "Mã KM", "Tên KM", "TrangThai", "Ghi chú"};
        dtmKhuyenMai.setColumnIdentifiers(headers);
        listKM = khuyenMaiRepository.getAll();
        for (KhuyenMai khuyenMai : listKM) {
            if (khuyenMai.getTrangThai() != 1) {
                khuyenMai.setTrangThai(khuyenMaiUtil.trangThaiKM(khuyenMai.getThoiGianBD(), khuyenMai.getThoiGianKT(), today));
                String updateTT = khuyenMaiService.update(khuyenMai, khuyenMai.getMaKhuyenMai());
            }

        }
        listKM = khuyenMaiService.getAll();
        showData(listKM, 1);
        //  radioDangApDung.setSelected(true);
        cbbLoaiKM.setModel(dcbmLoaiKM);
        String headerMonAn[] = {"Mã món ăn", "Tên món ăn", "Đơn giá", "Áp dụng"};
        dtmMonAn.setColumnIdentifiers(headerMonAn);
        // tbMonAn.setModel(dtmMonAn);
        listMonAn = monAnService.getAll();
        showDataMonAn(listMonAn);
        //tbKMCT.setModel(dtmKMCT);
        String headersKMCT[] = {"STT", "Mã KM", "Mã món ăn", "Tên món ăn", "Giá sau KM", "TT"};
        dtmKMCT.setColumnIdentifiers(headersKMCT);
        loadLoaiKM();
        txtGtriKM.setText("0");
        txtThoiGianBatDau.setDate(today);
        txtThoiGianKetThuc.setDate(today);
        radioDangApDung.setSelected(true);
        txtMaKM.setEditable(false);
        radioDangApDung.setEnabled(false);
        radioKoTrongTgApDung.setEnabled(false);
        radioNgungApDung.setEnabled(false);
        txtThoiGianBatDau.getJCalendar().setMinSelectableDate(today);
        txtThoiGianKetThuc.getJCalendar().setMinSelectableDate(today);
        JTextFieldDateEditor tgianBD = (JTextFieldDateEditor) txtThoiGianBatDau.getDateEditor();
        tgianBD.setEnabled(false);
        JTextFieldDateEditor tgianKT = (JTextFieldDateEditor) txtThoiGianKetThuc.getDateEditor();
        tgianKT.setEnabled(false);
        radioTatcaMonAn.setSelected(true);
        cbChonTatCa.setEnabled(false);
        JTextFieldDateEditor ngayBD = (JTextFieldDateEditor) txtThoiGianBatDau.getDateEditor();
        ngayBD.setEnabled(false);
        JTextFieldDateEditor ngayKT = (JTextFieldDateEditor) txtThoiGianKetThuc.getDateEditor();
        ngayKT.setEnabled(false);
        //
        JTableHeader hd = tbKMCT.getTableHeader();
        hd.setBackground(Color.red);
        hd.setForeground(Color.red);
        hd.setFont(new Font("Segoe", Font.BOLD, 13));
        //
        JTableHeader hd1 = tbKhuyenMai.getTableHeader();
        hd1.setBackground(Color.red);
        hd1.setForeground(Color.red);
        hd1.setFont(new Font("Segoe", Font.BOLD, 13));
        //
        JTableHeader hd2 = tbMonAn.getTableHeader();
        hd2.setBackground(Color.red);
        hd2.setForeground(Color.red);
        hd2.setFont(new Font("Segoe", Font.BOLD, 13));
    }
//show mặc định tất cả check box ko được chọn

    private void showDataMonAn(List<MonAn> listMonAn) {
        dtmMonAn.setRowCount(0);
        for (int i = 0; i < listMonAn.size(); i++) {
            dtmMonAn.addRow(new Object[]{listMonAn.get(i).getMaMonAn(), listMonAn.get(i).getTenMonAn(),
                listMonAn.get(i).getDonGia(), false});
        }
    }

    //truyền mặc định fulll true
    private void showDataMonAnTrue(List<MonAn> listMonAn) {
        dtmMonAn.setRowCount(0);
        for (int i = 0; i < listMonAn.size(); i++) {
            dtmMonAn.addRow(new Object[]{listMonAn.get(i).getMaMonAn(), listMonAn.get(i).getTenMonAn(),
                listMonAn.get(i).getDonGia(), true});
        }
    }

    //show khi fill km
    private void showDataMonAnCustom(List<MonAnKMResponse> listMaKM) {
        dtmMonAn.setRowCount(0);
        for (MonAnKMResponse monAnKMResponse : listMaKM) {
            dtmMonAn.addRow(new Object[]{monAnKMResponse.getMaMA(), monAnKMResponse.getTenMonAn(), monAnKMResponse.getGiaCu(), monAnKMResponse.isApDungKM()});
        }
    }

    private void showDataCTKM(List<KhuyenMaiChiTiet> listKMCT, int stt) {
        dtmKMCT.setRowCount(0);
        for (KhuyenMaiChiTiet kmct : listKMCT) {
            dtmKMCT.addRow(new Object[]{stt, kmct.getKhuyenMai().getMaKhuyenMai(), kmct.getMonAn().getMaMonAn(),
                kmct.getMonAn().getTenMonAn(), kmct.getDonGiaSauKM(), (kmct.getTrangThai() == 0 ? "Áp dụng" : "Ngừng")});
            stt++;
        }
    }

    private void showData(List<KhuyenMai> listKM, int stt) {
        dtmKhuyenMai.setRowCount(0);
        for (KhuyenMai khuyenMai : listKM) {
            if (khuyenMai.getTrangThai() != 1) {
//                String ngayBDString = dateFormat.format(khuyenMai.getThoiGianBD());
//                String ngayKTString = dateFormat.format(khuyenMai.getThoiGianKT());
//                String todayString = dateFormat.format(today);
                khuyenMai.setTrangThai(khuyenMaiUtil.trangThaiKM(khuyenMai.getThoiGianBD(), khuyenMai.getThoiGianKT(), today));
                String updateTT = khuyenMaiService.update(khuyenMai, khuyenMai.getMaKhuyenMai());
            }
        }
        for (KhuyenMai khuyenMai : listKM) {
            dtmKhuyenMai.addRow(khuyenMai.toDataRowViewKM(stt));
            stt++;
        }
    }

    private void loadLoaiKM() {
        dcbmLoaiKM.addElement("Phần trăm");
        dcbmLoaiKM.addElement("Tiền mặt");
    }

    private void fillKM(int index, List<KhuyenMai> listKM) {
        KhuyenMai khuyenMai = listKM.get(index);
        //nếu là ngunewfg áp dụng các btn vô hiệu hoá
        if (khuyenMai.getTrangThai() == 0 || khuyenMai.getTrangThai() >= 2) {
            radioNgungApDung.setEnabled(true);
            btnUpdate.setEnabled(true);
            txtTenKhuyenMai.setText(khuyenMai.getTenKhuyenMai());
            txtTenKhuyenMai.setEditable(true);
            txtGtriKM.setText(String.valueOf(khuyenMai.getGiaTriKM()));
            txtGtriKM.setEditable(true);
            dcbmLoaiKM.setSelectedItem(khuyenMai.getLoaiKhuyenMai());
            cbbLoaiKM.setEditable(true);
            txtThoiGianBatDau.setDate(khuyenMai.getThoiGianBD());
            txtThoiGianBatDau.setEnabled(true);
            txtThoiGianKetThuc.setDate(khuyenMai.getThoiGianKT());
            txtThoiGianKetThuc.setEnabled(true);
            txtMaKM.setText(khuyenMai.getMaKhuyenMai());
            txtMaKM.setEnabled(true);
            if (khuyenMai.getTrangThai() == 0) {
                radioDangApDung.setSelected(true);
            } else {
                radioKoTrongTgApDung.setSelected(true);
            }
            tbMonAn.setEnabled(true);
            radioNgungApDung.setEnabled(true);
            if (!khuyenMai.getGhiChu().isEmpty()) {
                txtGhiChu.setText(khuyenMai.getGhiChu());
            }
            cbBoChonTatCa.setEnabled(true);
            btnApDungKM.setEnabled(true);
        } else {
            btnUpdate.setEnabled(false);
            txtTenKhuyenMai.setText(khuyenMai.getTenKhuyenMai());
            txtTenKhuyenMai.setEditable(false);
            txtGtriKM.setText(String.valueOf(khuyenMai.getGiaTriKM()));
            txtGtriKM.setEditable(false);
            dcbmLoaiKM.setSelectedItem(khuyenMai.getLoaiKhuyenMai());
            cbbLoaiKM.setEditable(false);
            txtThoiGianBatDau.setDate(khuyenMai.getThoiGianBD());
            txtThoiGianBatDau.setEnabled(false);
            txtThoiGianKetThuc.setDate(khuyenMai.getThoiGianKT());
            txtThoiGianKetThuc.setEnabled(false);
            txtMaKM.setText(khuyenMai.getMaKhuyenMai());
            txtMaKM.setEnabled(false);
            radioNgungApDung.setEnabled(false);
            tbMonAn.setEnabled(false);
            cbChonTatCa.setEnabled(false);
            cbBoChonTatCa.setEnabled(false);
            btnApDungKM.setEnabled(false);
        }
    }

    //fill món ăn + kmct: cột áp dụng có được click hay ko
    private void fillKMCT() {
        radioNgungApDung.setEnabled(true);
        //lấy số lượng món ăn  trên table đang có
        int szTable = dtmMonAn.getRowCount();
        //list custom model: ko select = db mà chỉ tt view thêm thuộc tính áp dụng hay ko
        List<MonAnKMResponse> listMaKm = new ArrayList<>();
        for (int i = 0; i < szTable; i++) {
            MonAnKMResponse monAnKMResponse = new MonAnKMResponse();
            monAnKMResponse.setApDungKM((boolean) dtmMonAn.getValueAt(i, 3));
            monAnKMResponse.setGiaCu((BigDecimal) dtmMonAn.getValueAt(i, 2));
            monAnKMResponse.setTenMonAn((String) dtmMonAn.getValueAt(i, 1));
            monAnKMResponse.setMaMA((String) dtmMonAn.getValueAt(i, 0));
            listMaKm.add(monAnKMResponse);
        }
        //laasy max KM đang được chọn:
        String maKM = txtMaKM.getText();
        if (maKM.isEmpty() || maKM == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn KM cần áp dụng!");
        } else {
            //chạy for list món ăn trên table:
            KhuyenMai khuyenMai = khuyenMaiService.getOne(maKM);
            for (MonAnKMResponse monAnKMResponse : listMaKm) {
                MonAn monAn = monAnService.getOne(monAnKMResponse.getMaMA());
                //getOne kmct theo món ăn và km truyền vào
                //nếu != null => món ăn này đã được áp dụng km này => cột áp dụng = true
                //ngược lại
                //dùng hàm showDataMon ăn khác
                List<KhuyenMaiChiTiet> kmct = khuyenMaiChiTiettService.getKMCTByMaAndKM(monAn, khuyenMai);
                if (kmct.size() <= 0) {
                    monAnKMResponse.setApDungKM(false);
                } else if (kmct.size() > 0 && (kmct.get(0).getTrangThai() == 1)) {
                    monAnKMResponse.setApDungKM(false);
                } else {
                    monAnKMResponse.setApDungKM(true);
                }
            }
            showDataMonAnCustom(listMaKm);
        }
    }

    private KhuyenMai newKM() {
        KhuyenMai khuyenMai = new KhuyenMai();
        khuyenMai.setGhiChu(txtGhiChu.getText());
        khuyenMai.setLoaiKhuyenMai(dcbmLoaiKM.getSelectedItem().toString());
        khuyenMai.setNhanVien(nhanV);
        khuyenMai.setTenKhuyenMai(txtTenKhuyenMai.getText());
        Date ngayBD = Date.valueOf(dateFormat.format(txtThoiGianBatDau.getDate()));
        Date ngayKT = Date.valueOf(dateFormat.format(txtThoiGianKetThuc.getDate()));
        khuyenMai.setThoiGianBD(ngayBD);
        khuyenMai.setThoiGianKT(ngayKT);
        // khuyenMai.setGiaTriKM(BigDecimal.valueOf(Double.valueOf(txtDonVi.getText())));
        if (!txtGtriKM.getText().matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "Giá trị Km phải là số");
            return null;
        } else {
            khuyenMai.setGiaTriKM(new BigDecimal(txtGtriKM.getText()));
            return khuyenMai;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        radioMonAn = new javax.swing.ButtonGroup();
        panelBorder1 = new com.raven.swing.PanelBorder();
        txtSearch = new com.raven.swing.SearchText();
        jLabel1 = new javax.swing.JLabel();
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
        txtGtriKM = new javax.swing.JTextField();
        txtGhiChu = new javax.swing.JTextField();
        txtThoiGianBatDau = new com.toedter.calendar.JDateChooser();
        txtThoiGianKetThuc = new com.toedter.calendar.JDateChooser();
        txtTenKhuyenMai = new javax.swing.JTextField();
        radioDangApDung = new javax.swing.JRadioButton();
        radioKoTrongTgApDung = new javax.swing.JRadioButton();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
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
        btnApDungKM = new javax.swing.JButton();
        radioTatcaMonAn = new javax.swing.JRadioButton();
        radioChuaApDungKM = new javax.swing.JRadioButton();
        cbChonTatCa = new javax.swing.JCheckBox();
        cbBoChonTatCa = new javax.swing.JCheckBox();

        panelBorder1.setBackground(new java.awt.Color(204, 204, 255));

        txtSearch.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchCaretUpdate(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));

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
        jLabel12.setText("Giá trị KM:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Ghi Chú              :");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Trạng Thái          :");

        buttonGroup1.add(radioDangApDung);
        radioDangApDung.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        radioDangApDung.setText("Đang áp dụng");

        buttonGroup1.add(radioKoTrongTgApDung);
        radioKoTrongTgApDung.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        radioKoTrongTgApDung.setText("Không trong thời gian áp dụng");

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

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioNgungApDung);
        radioNgungApDung.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        radioNgungApDung.setText("Ngừng áp dụng");
        radioNgungApDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioNgungApDungActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(34, 34, 34)
                        .addComponent(radioDangApDung)
                        .addGap(18, 18, 18)
                        .addComponent(radioKoTrongTgApDung))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(txtTenKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(12, 12, 12)
                        .addComponent(txtThoiGianKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtThoiGianBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(12, 12, 12)
                                    .addComponent(jLabel12))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(radioNgungApDung))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtGtriKM, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbbLoaiKM, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(btnRefresh)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnAdd)
                            .addGap(18, 18, 18)
                            .addComponent(btnUpdate)
                            .addGap(18, 18, 18)
                            .addComponent(btnRemove)
                            .addGap(18, 18, 18)
                            .addComponent(btnClear))))
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
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel12)
                        .addComponent(txtGtriKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtThoiGianBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(txtThoiGianKetThuc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTenKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioDangApDung)
                    .addComponent(radioKoTrongTgApDung)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioNgungApDung)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnRemove)
                    .addComponent(btnClear)
                    .addComponent(btnRefresh))
                .addContainerGap())
        );

        tbMonAn.setBackground(new java.awt.Color(178, 205, 255));
        tbMonAn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tbMonAn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
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

        tbKhuyenMai.setBackground(new java.awt.Color(178, 205, 255));
        tbKhuyenMai.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
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

        tbKMCT.setBackground(new java.awt.Color(178, 205, 255));
        tbKMCT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
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
        tbKMCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKMCTMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbKMCT);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Chi tiết KM:");

        btnApDungKM.setBackground(new java.awt.Color(51, 255, 0));
        btnApDungKM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnApDungKM.setText("Áp Dụng");
        btnApDungKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApDungKMActionPerformed(evt);
            }
        });

        radioMonAn.add(radioTatcaMonAn);
        radioTatcaMonAn.setText("Tất cả");
        radioTatcaMonAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioTatcaMonAnActionPerformed(evt);
            }
        });

        radioMonAn.add(radioChuaApDungKM);
        radioChuaApDungKM.setText("Món chưa áp dụng KM");
        radioChuaApDungKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioChuaApDungKMActionPerformed(evt);
            }
        });

        cbChonTatCa.setText("Chọn tất cả");
        cbChonTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbChonTatCaActionPerformed(evt);
            }
        });

        cbBoChonTatCa.setText("Bỏ chọn tất cả");
        cbBoChonTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBoChonTatCaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(radioTatcaMonAn))
                        .addGap(30, 30, 30)
                        .addComponent(radioChuaApDungKM)
                        .addGap(93, 93, 93)
                        .addComponent(cbChonTatCa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbBoChonTatCa))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(btnApDungKM, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel9))
                .addGap(8, 8, 8)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(radioTatcaMonAn)
                    .addComponent(radioChuaApDungKM)
                    .addComponent(cbChonTatCa)
                    .addComponent(cbBoChonTatCa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnApDungKM, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKhuyenMaiMouseClicked
        radioNgungApDung.setEnabled(true);
        int index = tbKhuyenMai.getSelectedRow();
        fillKM(index, listKM);
        KhuyenMai khuyenMai = khuyenMaiService.getOne(txtMaKM.getText());
        listCTKM = khuyenMaiChiTiettService.getKMCTsByKM(khuyenMai);
        //nếu list rỗng=> table món ăn cột áp dụng bỏ chọn check box
        if (listCTKM.size() <= 0) {
            listMonAn = monAnService.getAll();
            showDataMonAn(listMonAn);
            dtmKMCT.setRowCount(0);
        } else {
            radioNgungApDung.setEnabled(true);
            showDataCTKM(listCTKM, 1);
            fillKMCT();
        }
    }//GEN-LAST:event_tbKhuyenMaiMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (!txtMaKM.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng bấm clear trước khi Add");
        } else {
            KhuyenMai khuyenMai = newKM();
            if (khuyenMai != null) {
                listKM = khuyenMaiService.getAll();
                khuyenMai.setMaKhuyenMai(khuyenMaiUtil.zenMaKM(listKM));
                khuyenMai.setTrangThai(khuyenMaiUtil.trangThaiKM(khuyenMai.getThoiGianBD(), khuyenMai.getThoiGianKT(), today));
                boolean isCheckValidate = khuyenMaiUtil.checkValidateKM(khuyenMai);
                if (isCheckValidate) {
                    //check validate = true => thêm
                    //get lại list all, set trạng thái theo tgian
                    int check = JOptionPane.showConfirmDialog(null, "Xác nhận thêm KM");
                    if (check == 0) {
                        JOptionPane.showMessageDialog(this, khuyenMaiService.add(khuyenMai));
                        listKM = khuyenMaiService.getAll();
                        showData(listKM, 1);
                        tbMonAn.setEnabled(true);
                    }
                }
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int index = tbKhuyenMai.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Chọn data!");
        } else {
            //KhuyenMai khuyenMai = newKM();
//            String ngayBDString = dateFormat.format(khuyenMai.getThoiGianBD());
//            String ngayKTString = dateFormat.format(khuyenMai.getThoiGianKT());

            KhuyenMai khuyenMai = khuyenMaiService.getOne(txtMaKM.getText());
            khuyenMai.setGhiChu(txtGhiChu.getText());
            String updtae = khuyenMaiService.update(khuyenMai, khuyenMai.getMaKhuyenMai());
            if (radioNgungApDung.isSelected()) {
//                khuyenMai.setTrangThai(1);
//                String updateTTKm = khuyenMaiService.update(khuyenMai, khuyenMai.getMaKhuyenMai());
                if (khuyenMai.getGhiChu().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập ghi chú!");
                } else {
                    khuyenMai.setGhiChu(txtGhiChu.getText());
                    khuyenMai.setTrangThai(1);
                    List<KhuyenMaiChiTiet> listKMCT = khuyenMaiChiTiettService.getKMCTsByKM(khuyenMai);
                    if (listKMCT.size() > 0) {
                        for (KhuyenMaiChiTiet khuyenMaiChiTiet : listKMCT) {
                            khuyenMaiChiTiet.setTrangThai(1);
                            String updtaeKMCT = khuyenMaiChiTiettService.update(khuyenMaiChiTiet, khuyenMaiChiTiet.getId());
                        }
                    }
                    if (JOptionPane.showConfirmDialog(this, "Xác nhận update!") == 0) {
                        JOptionPane.showMessageDialog(this, khuyenMaiService.update(khuyenMai, txtMaKM.getText()));
                        listKM = khuyenMaiRepository.getAll();
                        showData(listKM, 1);
                        btnClearActionPerformed(evt);
                    }
                }
            } else {
                KhuyenMai khuyenMai1 = newKM();
                if (khuyenMaiUtil.checkValidateKM(khuyenMai1)) {
                    khuyenMai.setTrangThai(khuyenMaiUtil.trangThaiKM(khuyenMai.getThoiGianBD(), khuyenMai.getThoiGianKT(), today));
                    if (JOptionPane.showConfirmDialog(this, "Xác nhận update!") == 0) {
                        JOptionPane.showMessageDialog(this, khuyenMaiService.update(khuyenMai1, txtMaKM.getText()));
                        listKM = khuyenMaiRepository.getAll();
                        showData(listKM, 1);
                        btnClearActionPerformed(evt);
                    }
                }
            }

        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        txtGhiChu.setText("");
        txtTenKhuyenMai.setText("");
        txtTenKhuyenMai.setEditable(true);
        txtGtriKM.setText("");
        txtGtriKM.setEditable(true);
        dcbmLoaiKM.setSelectedItem("Phần trăm");
        cbbLoaiKM.setEditable(true);
        txtThoiGianBatDau.setDate(today);
        txtThoiGianBatDau.setEnabled(true);
        txtThoiGianKetThuc.setDate(today);
        txtThoiGianKetThuc.setEnabled(true);
        txtMaKM.setText("");
        txtMaKM.setEditable(false);
        listThemKM_MA.removeAll(listThemKM_MA);
        dtmKMCT.setRowCount(0);
        listMonAn = monAnService.getAll();
        showDataMonAn(listMonAn);
        tbMonAn.setEnabled(true);
        txtGtriKM.setText("0");
        radioDangApDung.setSelected(true);
        listKM = khuyenMaiRepository.getAll();
        for (KhuyenMai khuyenMai : listKM) {
            if (khuyenMai.getTrangThai() != 1) {
                khuyenMai.setTrangThai(khuyenMaiUtil.trangThaiKM(khuyenMai.getThoiGianBD(), khuyenMai.getThoiGianKT(), today));
                String updateTT = khuyenMaiService.update(khuyenMai, khuyenMai.getMaKhuyenMai());
            }
        }
        listKM = khuyenMaiService.getAll();
        //khi khuyến mãi ngừng áp dụng=> CTKM cũng ngừng áp dụng
        for (KhuyenMai khuyenMai : listKM) {
            listCTKM = khuyenMaiChiTiettService.getKMCTsByKM(khuyenMai);
            if (khuyenMai.getTrangThai() == 1) {
                for (KhuyenMaiChiTiet khuyenMaiChiTiet : listCTKM) {
                    khuyenMaiChiTiet.setTrangThai(1);
                    String updtae = khuyenMaiChiTiettService.update(khuyenMaiChiTiet, khuyenMaiChiTiet.getId());
                }
            }
        }
        listKM = khuyenMaiService.getAll();
        showData(listKM, 1);
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        int index = tbKhuyenMai.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Chọn data!");
        } else {
            KhuyenMai khuyenMai = khuyenMaiService.getOne(txtMaKM.getText());
            if (khuyenMai.getGhiChu().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng không để trống ghi chú!");
                JOptionPane.showMessageDialog(this, "Không  thành công");
            } else {
                int checkConfirm = JOptionPane.showConfirmDialog(this, "Xác nhận thay đổi");
                if (checkConfirm == 0) {
                    JOptionPane.showMessageDialog(this, khuyenMaiService.remove(txtMaKM.getText()));
                    listKM = khuyenMaiService.getAll();
                    showData(listKM, 1);
                    btnClearActionPerformed(evt);
                }
            }
        }

    }//GEN-LAST:event_btnRemoveActionPerformed

    private void tbMonAnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMonAnMouseClicked
        try {
            int index = tbMonAn.getSelectedRow();
            String maMA = dtmMonAn.getValueAt(index, 0).toString();
            // System.out.println(maMA);
            //MonAn monAn = listMonAn.get(index);
            //getOne = getValue at => lấy mã:
            MonAn monAn = monAnService.getOne(maMA);
            String maKM = txtMaKM.getText();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tbMonAnMouseClicked

    private void btnApDungKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApDungKMActionPerformed
        //lấy số lượng món ăn  trên table đang có
        int szTable = dtmMonAn.getRowCount();
        //list custom model: ko select = db mà chỉ tt view thêm thuộc tính áp dụng hay ko\
        //chạy for lấy nhưunxg món ăn hiển thị ytreen tbl món ăn add vào list này
        List<MonAnKMResponse> listMaKm = new ArrayList<>();
        for (int i = 0; i < szTable; i++) {
            MonAnKMResponse monAnKMResponse = new MonAnKMResponse();
            monAnKMResponse.setApDungKM((boolean) dtmMonAn.getValueAt(i, 3));
            monAnKMResponse.setGiaCu((BigDecimal) dtmMonAn.getValueAt(i, 2));
            monAnKMResponse.setTenMonAn((String) dtmMonAn.getValueAt(i, 1));
            monAnKMResponse.setMaMA((String) dtmMonAn.getValueAt(i, 0));
            listMaKm.add(monAnKMResponse);
        }
        //laasy max KM đang được chọn:
        String maKM = txtMaKM.getText();
        if (maKM.isEmpty() || maKM == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn KM cần áp dụng!");
        } else {
            int checkConfirm = JOptionPane.showConfirmDialog(null, "Xác nhận thay đổi");
            if (checkConfirm == 0) {
                //chạy for list món ăn trên table:
                KhuyenMai khuyenMai = khuyenMaiService.getOne(maKM);
                for (MonAnKMResponse monAnKMResponse : listMaKm) {
                    MonAn monAn = monAnService.getOne(monAnKMResponse.getMaMA());
                    //nếu check box được chọn thì ktra xem trong db món đó đã được áp dụng từ trước hay chưa
                    //nếu rồi bỏ qua, nếu chưa insert kmct
                    if (monAnKMResponse.isApDungKM()) {
                        //--lấy listKMct banegf món ăn đnag duyệt for, nếu chưa kmct nào có món này thì add luôn
                        //nếu đang ko áp dụng KM nào thì add
                        List<KhuyenMaiChiTiet> kmct = khuyenMaiChiTiettService.getKMCTsByMA(monAn);
                        if (kmct.size() <= 0) {
                            KhuyenMaiChiTiet khuyenMaiChiTiet = new KhuyenMaiChiTiet();
                            khuyenMaiChiTiet.setKhuyenMai(khuyenMai);
                            khuyenMaiChiTiet.setMonAn(monAn);
                            BigDecimal donGiaSauKM = khuyenMaiUtil.tinhTienMonAnSauKM(khuyenMai, monAn);
                            //set đơn giá: nếu gtri KM > giá của món ăn => đơn giá = 0
                            if (donGiaSauKM.compareTo(new BigDecimal(0)) < 0) {
                                khuyenMaiChiTiet.setDonGiaSauKM(new BigDecimal(0));
                            } else {
                                khuyenMaiChiTiet.setDonGiaSauKM(donGiaSauKM);
                            }
                            String addKMCT = khuyenMaiChiTiettService.add(khuyenMaiChiTiet);
                            //nếu kmct lấy theo món ăn khác null nhưng trạng thái đều đã ngừng áp dụng cho món ăn đó
                            //thì cũng add
                        } else {
                            List<KhuyenMaiChiTiet> khuyenMaiChiTiets = khuyenMaiChiTiettService.getKMCTByMaAndKM(monAn, khuyenMai);
                            if (khuyenMaiChiTiets.size() > 0) {
                                if (khuyenMaiChiTiets.get(0).getTrangThai() == 1) {
                                    for (KhuyenMaiChiTiet khuyenMaiChiTiet : khuyenMaiChiTiets) {
                                        khuyenMaiChiTiet.setTrangThai(0);
                                        String update = khuyenMaiChiTiettService.update(khuyenMaiChiTiet, khuyenMaiChiTiet.getId());
                                    }
                                }
                            } else {
                                int checkTrangThaiKMCT = 0;
                                for (KhuyenMaiChiTiet khuyenMaiChiTiet : kmct) {
                                    if (khuyenMaiChiTiet.getTrangThai() == 0) {
                                        checkTrangThaiKMCT += 1;
                                    }
                                }
                                //nếu checkTrangThaiKMCT == 0 => món ăn này đang ko có ctkm nào đưuojc áp dụng => add kmct
                                //ngược lại nếu trạng thái những KMCT của món ăn đó có ít nhất 1 trạng thái = 0=> đang áp dụng
                                if (checkTrangThaiKMCT == 0) {
                                    KhuyenMaiChiTiet khuyenMaiChiTiet = new KhuyenMaiChiTiet();
                                    khuyenMaiChiTiet.setKhuyenMai(khuyenMai);
                                    khuyenMaiChiTiet.setMonAn(monAn);
                                    BigDecimal donGiaSauKM = khuyenMaiUtil.tinhTienMonAnSauKM(khuyenMai, monAn);
                                    khuyenMaiChiTiet.setDonGiaSauKM(donGiaSauKM);
                                    String addKMCT = khuyenMaiChiTiettService.add(khuyenMaiChiTiet);
                                } else {
                                    JOptionPane.showMessageDialog(this, monAn.getMaMonAn() + " đang được áp dụng một khuyến mãi khác!");
                                }
                            }
                        }
                    } else {
                        //ngược lại nếu cột áp dụng đang ko được chọn
                        //ktra xem trước có áp dụng ko, nếu ko bỏ qua, nếu có update trạng thái cho KMCT: = 1: ngưng áp dụng
                        List<KhuyenMaiChiTiet> kmct = khuyenMaiChiTiettService.getKMCTByMaAndKM(monAn, khuyenMai);
                        if (kmct.size() > 0) {
                            for (KhuyenMaiChiTiet khuyenMaiChiTiet : kmct) {
                                khuyenMaiChiTiet.setTrangThai(1);
                                String updateKMCT = khuyenMaiChiTiettService.update(khuyenMaiChiTiet, khuyenMaiChiTiet.getId());
                            }
                        }
                    }
                }
                JOptionPane.showMessageDialog(this, "Vui lòng refresh để kiểm tra thay đổi!");
            }
        }

    }//GEN-LAST:event_btnApDungKMActionPerformed

    private void tbKMCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKMCTMouseClicked
//        int index = tbKMCT.getSelectedRow();
//        String maMA = dtmKMCT.getValueAt(index, 2).toString();
//        MonAn monAn = monAnService.getOne(maMA);
//        //ánh xạ lại
//        // if (monAn.getKhuyenMai() != null) {
//        // KhuyenMai khuyenMaiDangShow = monAn.getKhuyenMai();
//        int checkOut = JOptionPane.showConfirmDialog(null, "Ngừng áp dụng KM cho sản phẩm này?");
//        if (checkOut == 0) {
//            // monAn.setKhuyenMai(null);
//            String updateMA_KM = monAnService.update(monAn, maMA);
//            JOptionPane.showMessageDialog(this, "Thành công!");
//            // listCTKM = monAnService.getMonAnByKhuyenMai(khuyenMaiDangShow);
//            showDataCTKM(listCTKM, 1);
//        }
//        //}
    }//GEN-LAST:event_tbKMCTMouseClicked

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        btnClearActionPerformed(evt);
        listKM = khuyenMaiService.getAll();
        //khi khuyến mãi ngừng áp dụng=> CTKM cũng ngừng áp dụng
        for (KhuyenMai khuyenMai : listKM) {
            listCTKM = khuyenMaiChiTiettService.getKMCTsByKM(khuyenMai);
            if (khuyenMai.getTrangThai() == 1) {
                for (KhuyenMaiChiTiet khuyenMaiChiTiet : listCTKM) {
                    khuyenMaiChiTiet.setTrangThai(1);
                    String updtae = khuyenMaiChiTiettService.update(khuyenMaiChiTiet, khuyenMaiChiTiet.getId());
                }
            }
        }
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void txtSearchCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchCaretUpdate
        if (txtSearch.getText().equals("") || txtSearch.getText().isEmpty()) {
            listKM = khuyenMaiService.getAll();
            showData(listKM, 1);
        } else {
            List<KhuyenMai> listSearch = khuyenMaiService.searchKM(txtSearch.getText());
            if (listSearch.size() > 0) {
                showData(listSearch, 1);
            } else {
                dtmKhuyenMai.setRowCount(0);
            }
        }
    }//GEN-LAST:event_txtSearchCaretUpdate

    private void radioNgungApDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioNgungApDungActionPerformed
        int checkConfirm = JOptionPane.showConfirmDialog(this, "Xác nhận ngừng áp dụng Khuyến mãi được chọn?");
        if (checkConfirm == 0) {
            radioNgungApDung.setSelected(true);
        } else {
            radioNgungApDung.setSelected(false);
            JOptionPane.showMessageDialog(this, "Trạng thái của KM sẽ được xác định dựa trên thời gian bắt đầu và kết thúc!");
        }
    }//GEN-LAST:event_radioNgungApDungActionPerformed

    private void radioTatcaMonAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioTatcaMonAnActionPerformed
        //get All = trạng thái = 0
        listMonAn = monAnService.getAll();
        showDataMonAn(listMonAn);
        cbChonTatCa.setEnabled(false);
    }//GEN-LAST:event_radioTatcaMonAnActionPerformed

    private void radioChuaApDungKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioChuaApDungKMActionPerformed
        listMonAn = monAnService.getAll();
        List<MonAnCoKM> listCoKM = monAnService.getMonAnCoKM();
        List<MonAn> listKoKM = new ArrayList<>();
        listKoKM = khuyenMaiUtil.listKoKM(listMonAn, listCoKM);
        showDataMonAn(listKoKM);
        //showDataMonAn(listKoKM);
        cbChonTatCa.setEnabled(true);
    }//GEN-LAST:event_radioChuaApDungKMActionPerformed

    private void cbChonTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChonTatCaActionPerformed
        int sz = dtmMonAn.getRowCount();
        cbBoChonTatCa.setSelected(false);
        List<MonAn> listShowing = new ArrayList<>();
        for (int i = 0; i < sz; i++) {
            listShowing.add(monAnService.getOne(dtmMonAn.getValueAt(i, 0).toString()));
        }
        showDataMonAnTrue(listShowing);
    }//GEN-LAST:event_cbChonTatCaActionPerformed

    private void cbBoChonTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBoChonTatCaActionPerformed
        cbChonTatCa.setSelected(false);
        int sz = dtmMonAn.getRowCount();
        List<MonAn> listShowing = new ArrayList<>();
        for (int i = 0; i < sz; i++) {
            listShowing.add(monAnService.getOne(dtmMonAn.getValueAt(i, 0).toString()));
        }
        showDataMonAn(listShowing);
    }//GEN-LAST:event_cbBoChonTatCaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnApDungKM;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cbBoChonTatCa;
    private javax.swing.JCheckBox cbChonTatCa;
    private javax.swing.JComboBox<String> cbbLoaiKM;
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
    private javax.swing.JRadioButton radioChuaApDungKM;
    private javax.swing.JRadioButton radioDangApDung;
    private javax.swing.JRadioButton radioKoTrongTgApDung;
    private javax.swing.ButtonGroup radioMonAn;
    private javax.swing.JRadioButton radioNgungApDung;
    private javax.swing.JRadioButton radioTatcaMonAn;
    private javax.swing.JTable tbKMCT;
    private javax.swing.JTable tbKhuyenMai;
    private javax.swing.JTable tbMonAn;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtGtriKM;
    private javax.swing.JTextField txtMaKM;
    private com.raven.swing.SearchText txtSearch;
    private javax.swing.JTextField txtTenKhuyenMai;
    private com.toedter.calendar.JDateChooser txtThoiGianBatDau;
    private com.toedter.calendar.JDateChooser txtThoiGianKetThuc;
    // End of variables declaration//GEN-END:variables
}
