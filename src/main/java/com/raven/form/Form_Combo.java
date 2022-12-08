/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.form;

import com.mycompany.customModel.BanResponse;
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
import java.awt.Component;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.event.InputEvent;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

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
    //đường dẫn
    private String selectedImagePath = "";

    public Form_Combo(NhanVien nv) {
        initComponents();
        tbComBo.setModel(dtComBo);
        tbCTCombo.setModel(dtCTComBo);
        tbChonMon.setModel(dtSanPham);
        String headerComBo[] = {"STT", "MÃ", "TÊN", "ĐƠN GIÁ", "HÌNH ẢNH"};
        String headerCTComBo[] = {"STT", "TÊN COMBO", "TÊN MÓN ĂN", "SỐ LƯỢNG MÓN ĂN"};
        String headerSanPham[] = {"STT", "MÃ SẢN PHẨM", "TÊN SẢN PHẨM", "ĐƠN VỊ TÍNH", "ĐƠN GIÁ", "ẢNH"};
        //
        dtCTComBo.setColumnIdentifiers(headerCTComBo);
        dtComBo.setColumnIdentifiers(headerComBo);
        dtSanPham.setColumnIdentifiers(headerSanPham);
        //
        cbbMaNhanVien.setModel(dcbNhanVien);
        cbbLoaiMonAn.setModel(dcbLoai);
        // check
//        for (ComBo c : listComBo = comBoService.getAllByTrangThai(0)) {
//            comBoService.checkTrangThaiMonAn(c);
//        }
// tb combo
        tbComBo.setFillsViewportHeight(true);
        tbComBo.getColumn("HÌNH ẢNH").setCellRenderer(new CellRenderers());
        //tb san pham
        tbChonMon.setFillsViewportHeight(true);
        tbChonMon.getColumn("ẢNH").setCellRenderer(new CellRenderer());
        //
        listComBo = comBoService.getAllByTrangThai(0);
        showDataComBo(listComBo);
//        showDataSanPham(listMonAn = monAnService.getAll());
        txtMa.setEnabled(false);
        setCbb();

    }

    class CellRenderer implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {
            //tb sanPham
            TableColumn tb = tbChonMon.getColumn("ẢNH");
            tb.setMaxWidth(100);
            tb.setMinWidth(100);
            tbChonMon.setRowHeight(60);

            return (Component) value;
        }

    }

    //
    class CellRenderers implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {
            TableColumn tbComBos = tbComBo.getColumn("HÌNH ẢNH");
            tbComBos.setMaxWidth(100);
            tbComBos.setMinWidth(100);
            tbComBo.setRowHeight(60);

            return (Component) value;
        }

    }

    private void showDataComBo(List<ComBo> listComBo) {
        dtComBo.setRowCount(0);
        for (int i = 0; i < listComBo.size(); i++) {
            if (listComBo.get(i).getHinhAnh() != null) {
                JLabel imageLabel1 = new JLabel();
                ImageIcon imageicon = new ImageIcon(listComBo.get(i).getHinhAnh());
                Image img = imageicon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                imageLabel1.setIcon(new ImageIcon(img));
                imageLabel1.setIcon(imageicon);
                dtComBo.addRow(new Object[]{i + 1, listComBo.get(i).getMaCB(), listComBo.get(i).getTenCB(), listComBo.get(i).getDonGia() + "K", imageLabel1});
            } else {
                dtComBo.addRow(new Object[]{i + 1, listComBo.get(i).getMaCB(), listComBo.get(i).getTenCB(), listComBo.get(i).getDonGia() + "K", null});
            }
        }
    }

    private void showDataCTComBo(List<ChiTietComBo> list) {
        dtCTComBo.setRowCount(0);
        int i = 1;
        for (ChiTietComBo cb : list) {
            dtCTComBo.addRow(cb.toShowData(i++));
        }
    }

    private void showDataSanPham(List<MonAn> listMonAn) {
        dtSanPham.setRowCount(0);
        for (int i = 0; i < listMonAn.size(); i++) {
            if (listMonAn.get(i).getHinhAnh() != null) {
                JLabel imageLabel = new JLabel();
                ImageIcon imageicon = new ImageIcon(listMonAn.get(i).getHinhAnh());
                Image img = imageicon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(img));
                imageLabel.setIcon(imageicon);
                dtSanPham.addRow(new Object[]{i + 1, listMonAn.get(i).getMaMonAn(), listMonAn.get(i).getTenMonAn(), listMonAn.get(i).getDonGia() + "K", listMonAn.get(i).getDonViTinh(), imageLabel});
            } else {
                dtSanPham.addRow(new Object[]{i + 1, listMonAn.get(i).getMaMonAn(), listMonAn.get(i).getTenMonAn(), listMonAn.get(i).getDonGia() + "K", listMonAn.get(i).getDonViTinh(), null});
            }
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
        jLabelImage1.setIcon(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        xoa = new javax.swing.JMenuItem();
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
        rdoApDung = new javax.swing.JRadioButton();
        rdoChoApDung = new javax.swing.JRadioButton();
        rdoNgungApDung = new javax.swing.JRadioButton();
        btnChonAnh = new javax.swing.JButton();
        jLabelImage1 = new javax.swing.JLabel();
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
        rdoListChoApDung = new javax.swing.JRadioButton();
        rdoListApDung = new javax.swing.JRadioButton();
        rdoListNgungApDung = new javax.swing.JRadioButton();
        lbLoaiMon = new javax.swing.JLabel();
        txtSearch1 = new com.raven.swing.SearchText();

        xoa.setText("Xóa");
        xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(xoa);

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
        cbbMaNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMaNhanVienActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoApDung);
        rdoApDung.setText("Áp dụng");

        buttonGroup1.add(rdoChoApDung);
        rdoChoApDung.setSelected(true);
        rdoChoApDung.setText("Chờ Áp dụng");

        buttonGroup1.add(rdoNgungApDung);
        rdoNgungApDung.setText("Ngừng áp dụng");

        btnChonAnh.setBackground(new java.awt.Color(204, 204, 204));
        btnChonAnh.setText("Chọn Ảnh:");
        btnChonAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonAnhActionPerformed(evt);
            }
        });

        jLabelImage1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(60, 60, 60)
                        .addComponent(btnUpdate)
                        .addGap(81, 81, 81)
                        .addComponent(btnRemove))
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoChoApDung)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnChonAnh)
                                        .addGap(17, 17, 17)))))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(rdoNgungApDung)
                                .addComponent(btnClear)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(30, 30, 30))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(btnChonAnh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(14, 14, 14))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbComBoMouseReleased(evt);
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
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbCTComboMouseReleased(evt);
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

        buttonGroup2.add(rdoListChoApDung);
        rdoListChoApDung.setText("Chờ Áp dụng");
        rdoListChoApDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoListChoApDungActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoListApDung);
        rdoListApDung.setSelected(true);
        rdoListApDung.setText("Áp dụng");
        rdoListApDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoListApDungActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoListNgungApDung);
        rdoListNgungApDung.setText("Ngừng áp dụng");
        rdoListNgungApDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoListNgungApDungActionPerformed(evt);
            }
        });

        lbLoaiMon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbLoaiMon.setText("loaiMon");

        txtSearch1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearch1CaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(24, 24, 24)
                        .addComponent(cbbLoaiMonAn, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbLoaiMon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder1Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdoListApDung)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdoListChoApDung)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdoListNgungApDung))
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(63, 63, 63))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnApDung, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdoListApDung)
                        .addComponent(rdoListChoApDung)
                        .addComponent(rdoListNgungApDung)
                        .addComponent(jLabel9))
                    .addComponent(jLabel2))
                .addGap(4, 4, 4)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel3)
                    .addComponent(cbbLoaiMonAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbLoaiMon)
                    .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(19, Short.MAX_VALUE))
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
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (txtMa.getText().isEmpty()) {
            if (txtTen.getText().isEmpty() || txtTen.getText().matches("\\s+")) {
                JOptionPane.showMessageDialog(this, "không được để trống tên món ăn");
            } else if (!txtTen.getText().matches("^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ0-9 ]+$")) {
                JOptionPane.showMessageDialog(this, "tên phải là số hoặc chữ");
            } else if (txtDonGia.getText().isEmpty() || txtDonGia.getText().matches("\\s+")) {
                JOptionPane.showMessageDialog(this, "đơn giá được để trống tên món ăn");
            } else if (!txtDonGia.getText().matches("[0-9]+")) {
                JOptionPane.showMessageDialog(this, "đơn giá phải là số");
            } else {
                if (rdoChoApDung.isSelected()) {
                    String hinhAnh = selectedImagePath;
                    NhanVien nhanVien = nhanVienService.getOne(dcbNhanVien.getSelectedItem().toString());
                    ComBo comB = new ComBo(null, nhanVien, comBoService.randomMaHoaDon(listComBo = comBoService.getAlls()), txtTen.getText(), hinhAnh, new BigDecimal(txtDonGia.getText()), 2);
                    int checkConfirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm!");
                    if (checkConfirm == 0) {
                        String addComBo = comBoService.add(comB);
                        JOptionPane.showMessageDialog(this, addComBo);
                        rdoListChoApDung.setSelected(true);
                        showDataComBo(listComBo = comBoService.getAllByTrangThai(2));
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "chỉ được thêm combo chờ áp dụng");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "vui lòng clear trước khi add");
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        if (!txtMa.getText().isEmpty()) {
            int checkConfirm = JOptionPane.showConfirmDialog(this, "Xác nhận xóa!");
            if (checkConfirm == 0) {
                String delete = comBoService.remove(txtMa.getText());
                JOptionPane.showMessageDialog(this, delete);
                clear();
                showDataComBo(listComBo = comBoService.getAll());
                showDataCTComBo(listCTComBo = chiTietComBoService.getAll());
            }
        } else {
            JOptionPane.showMessageDialog(this, "chọn combo cần xóa");
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clear();
    }//GEN-LAST:event_btnClearActionPerformed
    private void fill() {
        txtMa.setText(comBo.getMaCB());
        txtTen.setText(comBo.getTenCB());
        txtDonGia.setText(comBo.getDonGia().toString());
        cbbMaNhanVien.setSelectedItem(comBo.getNhanVien().getMa());
        if (comBo.getHinhAnh() != null) {
            ImageIcon imageicon = new ImageIcon(comBo.getHinhAnh());
            Image img = imageicon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            jLabelImage1.setIcon(new ImageIcon(img));
            jLabelImage1.setIcon(imageicon);
        } else {
            jLabelImage1.setIcon(null);
        }
    }

    private void tbComBoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbComBoMouseClicked
        // TODO add your handling code here:
        // lấy mã com bo
        int index = tbComBo.getSelectedRow();
        comBo = comBoService.getOne((String) dtComBo.getValueAt(index, 1));
        // lấy danh sách ct combo
        listCTComBo = chiTietComBoService.getAllByComBo(comBo);
        showDataCTComBo(listCTComBo);
        fill();
        // set combo ở ô text field
//        txtMa.setText(comBo.getMaCB());
//        txtTen.setText(comBo.getTenCB());
//        txtDonGia.setText(comBo.getDonGia().toString());
//        cbbMaNhanVien.setSelectedItem(comBo.getNhanVien().getMa());
//        ImageIcon imageicon = new ImageIcon(comBo.getHinhAnh());
//        Image img = imageicon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
//        jLabelImage.setIcon(new ImageIcon(img));
//        jLabelImage.setIcon(imageicon);
    }//GEN-LAST:event_tbComBoMouseClicked

    private void tbChonMonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbChonMonMouseClicked
        // TODO add your handling code here:
        if (comBo != null) {
            if (rdoListChoApDung.isSelected()) {
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
        // TODO add your handling code here
        int index = tbComBo.getSelectedRow();
        String hinhAnh;
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn comBo cần sửa");
        } else {
            ComBo comBoo = listComBo.get(index);
            if (txtTen.getText().isEmpty() || txtTen.getText().matches("\\s+")) {
                JOptionPane.showMessageDialog(this, "không được để trống tên món ăn");
            } else if (!txtTen.getText().matches("[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ0-9 ]+$")) {
                JOptionPane.showMessageDialog(this, "tên phải là số hoặc chữ");
            } else if (txtDonGia.getText().isEmpty() || txtDonGia.getText().matches("\\s+")) {
                JOptionPane.showMessageDialog(this, "đơn giá được để trống tên món ăn");
            } else if (!txtDonGia.getText().matches("[0-9]+")) {
                JOptionPane.showMessageDialog(this, "đơn giá phải là số");
            } else {
                int apDung;
                if (rdoApDung.isSelected()) {
                    apDung = 0;
                } else if (rdoChoApDung.isSelected()) {
                    apDung = 2;
                } else {
                    apDung = 1;
                }
                if (selectedImagePath != null) {
                    hinhAnh = selectedImagePath;
                } else {
                    hinhAnh = comBoo.getHinhAnh();
                }
                NhanVien nhanVien = nhanVienService.getOne(dcbNhanVien.getSelectedItem().toString());
                ComBo comB = new ComBo(this.comBo.getId(), nhanVien, this.comBo.getMaCB(), txtTen.getText(), hinhAnh, BigDecimal.valueOf(Double.valueOf(txtDonGia.getText())), apDung);
                int checkConfirm = JOptionPane.showConfirmDialog(this, "Xác nhận update!");
                if (checkConfirm == 0) {
                    String update = comBoService.update(comB, txtMa.getText());
                    JOptionPane.showMessageDialog(this, update);
                    rdoListApDungActionPerformed(evt);
                    showDataComBo(listComBo = comBoService.getAllByTrangThai(0));
                }
            }
        }

        // chưa show lại list
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void rdoListApDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoListApDungActionPerformed
        // TODO add your handling code here:
        comBo = null;
        clear();
        showDataCTComBo(listCTComBo = chiTietComBoService.getAllByComBo(comBo));
        rdoApDung.setSelected(true);
        showDataComBo(listComBo = comBoService.getAllByTrangThai(0));
    }//GEN-LAST:event_rdoListApDungActionPerformed

    private void rdoListChoApDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoListChoApDungActionPerformed
        // TODO add your handling code here:
        comBo = null;
        clear();
        rdoChoApDung.setSelected(true);
        showDataCTComBo(listCTComBo = chiTietComBoService.getAllByComBo(comBo));
        showDataComBo(listComBo = comBoService.getAllByTrangThai(2));
    }//GEN-LAST:event_rdoListChoApDungActionPerformed

    private void rdoListNgungApDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoListNgungApDungActionPerformed
        // TODO add your handling code here:
        comBo = null;
        clear();
        rdoNgungApDung.setSelected(true);
        showDataCTComBo(listCTComBo = chiTietComBoService.getAllByComBo(comBo));
        showDataComBo(listComBo = comBoService.getAllByTrangThai(1));
    }//GEN-LAST:event_rdoListNgungApDungActionPerformed

    private void btnApDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApDungActionPerformed
        // TODO add your handling code here:
        if (rdoChoApDung.isSelected()) {
            if (txtMa.getText() != null) {
                String ma = comBo.getMaCB();
                ComBo cb = new ComBo(comBo.getId(), nhanVien, comBo.getMaCB(), comBo.getTenCB(), comBo.getHinhAnh(), comBo.getDonGia(), 0);
                String update = comBoService.update(cb, ma);
                JOptionPane.showMessageDialog(this, update);
                showDataComBo(listComBo = comBoService.getAllByTrangThai(2));
                showDataCTComBo(listCTComBo = chiTietComBoService.getAllByComBo(null));
                clear();
            } else {
                JOptionPane.showMessageDialog(this, "vui lòng chọn combo");
            }
        } else {
            JOptionPane.showMessageDialog(this, "vui lòng chọn combo chờ áp dụng");
        }
    }//GEN-LAST:event_btnApDungActionPerformed

    private void tbCTComboMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCTComboMouseClicked
        // TODO add your handling code here:
        // lấy chi tiết combo
        if (rdoChoApDung.isSelected()) {
            int index = tbCTCombo.getSelectedRow();
            chiTietComBo = listCTComBo.get(index);

            //tạo chi tiết combo để update
            String soLuongGiam = JOptionPane.showInputDialog("chọn số lượng muốn giảm");
            if (soLuongGiam != null) {
                if (soLuongGiam.matches("[0-9]+")) {
                    int soLuongMonAn = chiTietComBo.getSoLuongMonAn() - Integer.valueOf(soLuongGiam);
                    if (soLuongMonAn <= 0) {
                        String xoa = chiTietComBoService.deleteCTCombo(chiTietComBo.getId());
                        JOptionPane.showMessageDialog(this, xoa);
                    } else {
                        String update = chiTietComBoService.updateSoLuong(chiTietComBo, comBo, soLuongMonAn);
                        JOptionPane.showMessageDialog(this, update);
                    }
                    showDataCTComBo(listCTComBo = chiTietComBoService.getAllByComBo(comBo));
                } else {
                    JOptionPane.showMessageDialog(this, "vui lòng nhập số");
                }
            } else {
                JOptionPane.showMessageDialog(this, "không thể sửa hoặc xóa");
            }
        }
    }//GEN-LAST:event_tbCTComboMouseClicked

    private void txtSearchCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchCaretUpdate
        // TODO add your handling code here:
        String ten = txtSearch.getText();
        listComBo = comBoService.getAllTen(ten);
        showDataComBo(listComBo);
    }//GEN-LAST:event_txtSearchCaretUpdate

    private void cbbMaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMaNhanVienActionPerformed
        // TODO add your handling code here:
        nhanVien = nhanVienService.getOne((String) cbbMaNhanVien.getSelectedItem());
    }//GEN-LAST:event_cbbMaNhanVienActionPerformed

    private void btnChonAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonAnhActionPerformed
        // TODO add your handling code here:
        JFileChooser browseImageFile = new JFileChooser("C:\\Users\\Public\\Pictures\\Sample Pictures");
        //Filter image extensions
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("IMAGES", "png", "jpg", "jpeg");
        browseImageFile.addChoosableFileFilter(fnef);
        int showOpenDialogue = browseImageFile.showOpenDialog(null);

        if (showOpenDialogue == JFileChooser.APPROVE_OPTION) {
            File selectedImageFile = browseImageFile.getSelectedFile();
            selectedImagePath = selectedImageFile.getAbsolutePath();
            JOptionPane.showMessageDialog(null, "Bạn đã chọn ảnh: " + selectedImagePath);
            //Display image on jlable
            ImageIcon ii = new ImageIcon(selectedImagePath);
//            Resize image to fit jlabel
            Image image = ii.getImage().getScaledInstance(jLabelImage1.getWidth(), jLabelImage1.getHeight(), Image.SCALE_SMOOTH);
//
            jLabelImage1.setIcon(new ImageIcon(image));
        }
    }//GEN-LAST:event_btnChonAnhActionPerformed

    private void txtSearch1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearch1CaretUpdate
        // TODO add your handling code here:
        String loaiDanhMuc = (String) cbbLoaiMonAn.getSelectedItem();
        DanhMuc danhMuc = danhMucService.getOne(loaiDanhMuc);
        lbLoaiMon.setText(danhMuc.getTenDanhMuc());
        String ten = txtSearch1.getText();
        listMonAn = monAnService.getMonAnTheoTenLoai(ten, danhMuc);
        showDataSanPham(listMonAn);
    }//GEN-LAST:event_txtSearch1CaretUpdate

    private void tbComBoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbComBoMouseReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_tbComBoMouseReleased

    private void xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaActionPerformed
        // TODO add your handling code here:
        int index = tbCTCombo.getSelectedRow();
        chiTietComBo = listCTComBo.get(index);
        String xoa = chiTietComBoService.deleteCTCombo(chiTietComBo.getId());
        JOptionPane.showMessageDialog(this, xoa);
        showDataCTComBo(listCTComBo = chiTietComBoService.getAllByComBo(comBo));
    }//GEN-LAST:event_xoaActionPerformed

    private void tbCTComboMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCTComboMouseReleased
        // TODO add your handling code here:
        if (evt.getModifiers() == InputEvent.BUTTON3_MASK) {
            if (evt.isPopupTrigger() && tbCTCombo.getSelectedRow() != 0) {
                jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }
    }//GEN-LAST:event_tbCTComboMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnApDung;
    private javax.swing.JButton btnChonAnh;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbbLoaiMonAn;
    private javax.swing.JComboBox<String> cbbMaNhanVien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelImage1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lbLoaiMon;
    private com.raven.swing.PanelBorder panelBorder1;
    private javax.swing.JRadioButton rdoApDung;
    private javax.swing.JRadioButton rdoChoApDung;
    private javax.swing.JRadioButton rdoListApDung;
    private javax.swing.JRadioButton rdoListChoApDung;
    private javax.swing.JRadioButton rdoListNgungApDung;
    private javax.swing.JRadioButton rdoNgungApDung;
    private javax.swing.JTable tbCTCombo;
    private javax.swing.JTable tbChonMon;
    private javax.swing.JTable tbComBo;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtMa;
    private com.raven.swing.SearchText txtSearch;
    private com.raven.swing.SearchText txtSearch1;
    private javax.swing.JTextField txtTen;
    private javax.swing.JMenuItem xoa;
    // End of variables declaration//GEN-END:variables
}
