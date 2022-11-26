/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.form;

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

    public Form_KhuyenMai(NhanVien nv) {
        initComponents();
        dtmMonAn = (DefaultTableModel) tbMonAn.getModel();
        dtmKhuyenMai = (DefaultTableModel) tbKhuyenMai.getModel();
        dtmKMCT = (DefaultTableModel) tbKMCT.getModel();
        this.nhanV = nv;
        // tbKhuyenMai.setModel(dtmKhuyenMai);
        String headers[] = {"STT", "Mã KM", "Tên KM", "TrangThai"};
        dtmKhuyenMai.setColumnIdentifiers(headers);
        listKM = khuyenMaiRepository.getAll();
        for (KhuyenMai khuyenMai : listKM) {
            khuyenMai.setTrangThai(khuyenMaiUtil.trangThaiKM(khuyenMai));
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
        txtDonVi.setText("0");
        txtThoiGianBatDau.setDate(today);
        txtThoiGianKetThuc.setDate(today);
        radioDangApDung.setSelected(true);
        txtMaKM.setEditable(false);

    }
//show mặc định tất cả check box ko được chọn

    private void showDataMonAn(List<MonAn> listMonAn) {
        dtmMonAn.setRowCount(0);
        for (int i = 0; i < listMonAn.size(); i++) {
            dtmMonAn.addRow(new Object[]{listMonAn.get(i).getMaMonAn(), listMonAn.get(i).getTenMonAn(),
                listMonAn.get(i).getDonGia(), false});
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

    //fill món ăn + kmct: cột áp dụng có được click hay ko
    private void fillKMCT() {
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
        listKM = khuyenMaiService.getAll();
        khuyenMai.setMaKhuyenMai(khuyenMaiUtil.zenMaKM(listKM));
        khuyenMai.setGhiChu(txtGhiChu.getText());
        khuyenMai.setLoaiKhuyenMai(dcbmLoaiKM.getSelectedItem().toString());
        khuyenMai.setNhanVien(nhanV);
        khuyenMai.setTenKhuyenMai(txtTenKhuyenMai.getText());
        Date ngayBD = Date.valueOf(dateFormat.format(txtThoiGianBatDau.getDate()));
        Date ngayKT = Date.valueOf(dateFormat.format(txtThoiGianKetThuc.getDate()));
        khuyenMai.setThoiGianBD(ngayBD);
        khuyenMai.setThoiGianKT(ngayKT);
        khuyenMai.setTrangThai(0);
        khuyenMai.setGiaTriKM(BigDecimal.valueOf(Double.valueOf(txtDonVi.getText())));
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
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
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
        radioNgungApDung.setText("Không trong thời gian áp dụng");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel14))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbbLoaiKM, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(radioDangApDung)
                                .addGap(18, 18, 18)
                                .addComponent(radioNgungApDung))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtThoiGianBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtThoiGianKetThuc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtTenKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtDonVi, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtGhiChu))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnRefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate)
                        .addGap(18, 18, 18)
                        .addComponent(btnRemove)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel12)
                            .addComponent(txtDonVi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
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
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTenKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioDangApDung)
                    .addComponent(radioNgungApDung)
                    .addComponent(jLabel14))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnRemove)
                    .addComponent(btnClear)
                    .addComponent(btnRefresh))
                .addContainerGap())
        );

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

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                                .addComponent(btnApDungKM, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(61, 61, 61))))
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
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)))
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
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
                .addGap(0, 0, 0)
                .addComponent(jLabel3)
                .addGap(2, 2, 2)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnApDungKM, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
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
        listCTKM = khuyenMaiChiTiettService.getKMCTsByKM(khuyenMai);
        //nếu list rỗng=> table món ăn cột áp dụng bỏ chọn check box
        if (listCTKM.size() <= 0) {
            listMonAn = monAnService.getAll();
            showDataMonAn(listMonAn);
            dtmKMCT.setRowCount(0);
        } else {
            showDataCTKM(listCTKM, 1);
            fillKMCT();
        }
    }//GEN-LAST:event_tbKhuyenMaiMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        KhuyenMai khuyenMai = newKM();
        if (khuyenMaiUtil.checkValidateKM(khuyenMai)) {
            JOptionPane.showMessageDialog(this, khuyenMaiService.add(khuyenMai));
            listKM = khuyenMaiRepository.getAll();
            for (KhuyenMai km : listKM) {
                km.setTrangThai(khuyenMaiUtil.trangThaiKM(km));
            }
            listKM = khuyenMaiService.getAll();
            showData(listKM, 1);
            tbMonAn.setEnabled(true);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int index = tbKhuyenMai.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Chọn data!");
        } else {
            KhuyenMai khuyenMai = newKM();
            JOptionPane.showMessageDialog(this, khuyenMaiService.update(khuyenMai, txtMaKM.getText()));
            listKM = khuyenMaiRepository.getAll();
            for (KhuyenMai km : listKM) {
                km.setTrangThai(khuyenMaiUtil.trangThaiKM(km));
            }
            listKM = khuyenMaiService.getAll();
            showData(listKM, 1);
            btnClearActionPerformed(evt);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        txtTenKhuyenMai.setText("");
        txtDonVi.setText("");
        dcbmLoaiKM.setSelectedItem("Phần trăm");
        txtThoiGianBatDau.setDate(today);
        txtThoiGianKetThuc.setDate(today);
        txtMaKM.setText("");
        txtMaKM.setEditable(false);
        listThemKM_MA.removeAll(listThemKM_MA);
        dtmKMCT.setRowCount(0);
        listMonAn = monAnService.getAll();
        showDataMonAn(listMonAn);
        tbMonAn.setEnabled(true);
        txtDonVi.setText("0");
        radioDangApDung.setSelected(true);
        listKM = khuyenMaiRepository.getAll();
        for (KhuyenMai khuyenMai : listKM) {
            khuyenMai.setTrangThai(khuyenMaiUtil.trangThaiKM(khuyenMai));
        }
        listKM = khuyenMaiService.getAll();
        showData(listKM, 1);
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        int index = tbKhuyenMai.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Chọn data!");
        } else {
            JOptionPane.showMessageDialog(this, khuyenMaiService.remove(txtMaKM.getText()));
            listKM = khuyenMaiRepository.getAll();
            for (KhuyenMai km : listKM) {
                km.setTrangThai(khuyenMaiUtil.trangThaiKM(km));
            }
            listKM = khuyenMaiService.getAll();
            showData(listKM, 1);
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void tbMonAnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMonAnMouseClicked
        int index = tbMonAn.getSelectedRow();
        String maMA = dtmMonAn.getValueAt(index, 0).toString();
        // System.out.println(maMA);
        //MonAn monAn = listMonAn.get(index);
        //getOne = getValue at => lấy mã:
        MonAn monAn = monAnService.getOne(maMA);
        String maKM = txtMaKM.getText();
    }//GEN-LAST:event_tbMonAnMouseClicked

    private void btnApDungKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApDungKMActionPerformed
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
                        List<KhuyenMaiChiTiet> kmct = khuyenMaiChiTiettService.getKMCTsByMA(monAn);
                        if (kmct.size() <= 0) {
                            KhuyenMaiChiTiet khuyenMaiChiTiet = new KhuyenMaiChiTiet();
                            khuyenMaiChiTiet.setKhuyenMai(khuyenMai);
                            khuyenMaiChiTiet.setMonAn(monAn);
                            BigDecimal donGiaSauKM = khuyenMaiUtil.tinhTienMonAnSauKM(khuyenMai, monAn);
                            khuyenMaiChiTiet.setDonGiaSauKM(donGiaSauKM);
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
    }//GEN-LAST:event_btnRefreshActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnApDungKM;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbLoaiKM;
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
