/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.raven.form;

import com.mycompany.customModel.HoaDonChiTietResponse;
import com.mycompany.domainModel.Ban;
import com.mycompany.domainModel.ChiTietBanHoaDon;
import com.mycompany.domainModel.ComBo;
import com.mycompany.domainModel.HoaDon;
import com.mycompany.domainModel.HoaDonChiTiet;
import com.mycompany.domainModel.MonAn;
import com.mycompany.service.IChiTietBanHoaDonService;
import com.mycompany.service.ICommonService;
import com.mycompany.service.IHoaDonChiTietResponseService;
import com.mycompany.service.IHoaDonChiTietService;
import com.mycompany.service.IHoaDonService;
import com.mycompany.service.impl.BanService;
import com.mycompany.service.impl.ChiTietBanHoaDonService;
import com.mycompany.service.impl.ComBoService;
import com.mycompany.service.impl.HoaDonChiTietResponseService;
import com.mycompany.service.impl.HoaDonChiTietService;
import com.mycompany.service.impl.HoaDonService;
import com.mycompany.service.impl.MonAnService;
import java.awt.Color;
import java.awt.Font;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Duongntt
 */
public class JDialogGopHoaDon extends javax.swing.JDialog {

    private DefaultTableModel dtmHDCTCu = new DefaultTableModel();
    private DefaultTableModel dtmHDCTMoi = new DefaultTableModel();
    private DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
    private List<HoaDonChiTietResponse> lstDonChiTietResponsesCu;
    private List<HoaDonChiTietResponse> lstDonChiTietResponsesMoi;
    private HoaDonService hoaDonService = new HoaDonService();
    private ICommonService banService = new BanService();
    private IHoaDonChiTietResponseService hdctResponseService = new HoaDonChiTietResponseService();
    private IHoaDonChiTietService hdctService = new HoaDonChiTietService();
    private ICommonService monAnService = new MonAnService();
    private ICommonService comBoService = new ComBoService();
    private IChiTietBanHoaDonService chiTietBanHoaDonService = new ChiTietBanHoaDonService();
    private List<HoaDon> lstHoaDons;
    private IHoaDonService hds2 = new HoaDonService();
    private ICommonService hds1 = new HoaDonService();
    //thực thể
    private HoaDon hoaDon;

    /**
     * Creates new form JDialogGopHD
     */
    public JDialogGopHoaDon(java.awt.Frame parent, boolean modal, HoaDon hd) {
        super(parent, modal);
        initComponents();
        hoaDon = hd;
        String headerHoaDonCT[] = {"STT", "Tên món ăn", "Giá món ăn", "Số lượng món ăn", "Tên combo", "Giá combo", "Số lượng combo", "Ghi chú"};
        cbbMaHoaDonMoi.setModel(dcbm);
        tbHDCTCu.setModel(dtmHDCTCu);
        tbHDCTMoi.setModel(dtmHDCTMoi);
        dtmHDCTCu.setColumnIdentifiers(headerHoaDonCT);
        dtmHDCTMoi.setColumnIdentifiers(headerHoaDonCT);
        lbMaHDCu.setText(hd.getMaHoaDon());
        lstDonChiTietResponsesCu = hdctResponseService.getAll(hoaDon);
        showDataHDCTCu(lstDonChiTietResponsesCu);
        loadCBB();
        HoaDon hdMoi = (HoaDon) hds1.getOne(cbbMaHoaDonMoi.getSelectedItem().toString());
        lstDonChiTietResponsesMoi = hdctResponseService.getAll(hdMoi);
        showDataHDCTMoi(lstDonChiTietResponsesMoi);
        //
        JTableHeader hd1 = tbHDCTCu.getTableHeader();
        hd1.setBackground(Color.red);
        hd1.setForeground(Color.red);
        hd1.setFont(new Font("Segoe", Font.BOLD, 13));
        //
        JTableHeader hd2 = tbHDCTMoi.getTableHeader();
        hd2.setBackground(Color.red);
        hd2.setForeground(Color.red);
        hd2.setFont(new Font("Segoe", Font.BOLD, 13));
    }

    private void loadCBB() {
        lstHoaDons = hds2.getHDByTrangThai(0);
        for (int i = 0; i < lstHoaDons.size(); i++) {
            if (lstHoaDons.get(i).getMaHoaDon().equals(lbMaHDCu.getText())) {
                lstHoaDons.remove(i);
            }
        }
        for (HoaDon lstHoaDon : lstHoaDons) {
            dcbm.addElement(lstHoaDon.getMaHoaDon());
        }
    }

    private void showDataHDCTCu(List<HoaDonChiTietResponse> donChiTietResponses) {
        dtmHDCTCu.setRowCount(0);
        int stt = 0;
        for (HoaDonChiTietResponse donChiTietResponse : donChiTietResponses) {
            stt++;
            dtmHDCTCu.addRow(donChiTietResponse.toDataRow(stt));
        }
    }

    private void showDataHDCTMoi(List<HoaDonChiTietResponse> donChiTietResponses) {
        dtmHDCTMoi.setRowCount(0);
        int stt = 0;
        for (HoaDonChiTietResponse donChiTietResponse : donChiTietResponses) {
            stt++;
            dtmHDCTMoi.addRow(donChiTietResponse.toDataRow(stt));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHDCTMoi = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        btnXacNhan = new javax.swing.JButton();
        btnGopTatCa = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lbMaHDCu = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbHDCTCu = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        cbbMaHoaDonMoi = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GỘP HOÁ ĐƠN");

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        tbHDCTMoi.setBackground(new java.awt.Color(178, 205, 255));
        tbHDCTMoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tbHDCTMoi.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbHDCTMoi);

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Exit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnXacNhan.setBackground(new java.awt.Color(102, 255, 51));
        btnXacNhan.setText("Xác nhận");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        btnGopTatCa.setBackground(new java.awt.Color(255, 204, 204));
        btnGopTatCa.setText("Gộp tất cả");
        btnGopTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGopTatCaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Mã hoá đơn cũ:");

        lbMaHDCu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbMaHDCu.setText("Mã hd");

        tbHDCTCu.setBackground(new java.awt.Color(178, 205, 255));
        tbHDCTCu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tbHDCTCu.setModel(new javax.swing.table.DefaultTableModel(
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
        tbHDCTCu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHDCTCuMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbHDCTCu);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Mã hoá mới:");

        cbbMaHoaDonMoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbbMaHoaDonMoi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbMaHoaDonMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMaHoaDonMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnGopTatCa, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(lbMaHDCu, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 776, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(22, 22, 22)
                            .addComponent(cbbMaHoaDonMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 783, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbMaHDCu))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbMaHoaDonMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btnXacNhan)
                    .addComponent(btnGopTatCa))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        // TODO add your handling code here:
        String xoaHDCTCu = (String) hdctService.remove(hoaDon);
        HoaDon hdMoi = (HoaDon) hds1.getOne(cbbMaHoaDonMoi.getSelectedItem().toString());
        String xoaHDCTMoi = (String) hdctService.remove(hdMoi);
        for (HoaDonChiTietResponse hoaDonChiTietResponse : lstDonChiTietResponsesCu) {
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setHoaDon(hoaDon);
            if (hoaDonChiTietResponse.getMaMonAn() != null) {
                MonAn monAn = (MonAn) monAnService.getOne(hoaDonChiTietResponse.getMaMonAn());
                hoaDonChiTiet.setMonAn(monAn);
                hoaDonChiTiet.setDonGiaMonAn(hoaDonChiTietResponse.getDonGiaMonAn());
                hoaDonChiTiet.setSoLuongMonAn(hoaDonChiTietResponse.getSoLuongMonAn());
                hoaDonChiTiet.setDonGiaCombo(BigDecimal.valueOf(0));
                hoaDonChiTiet.setSoLuongCombo(0);
                String themHDCT = (String) hdctService.add(hoaDonChiTiet);
            } else {
                ComBo comBo = (ComBo) comBoService.getOne(hoaDonChiTietResponse.getMaCombo());
                hoaDonChiTiet.setComBo(comBo);
                hoaDonChiTiet.setDonGiaCombo(hoaDonChiTietResponse.getDonGiaCombo());
                hoaDonChiTiet.setSoLuongCombo(hoaDonChiTietResponse.getSoLuongCombo());
                hoaDonChiTiet.setDonGiaMonAn(BigDecimal.valueOf(0));
                hoaDonChiTiet.setSoLuongMonAn(0);
                String themHDCT = (String) hdctService.add(hoaDonChiTiet);
            }
        }
        for (HoaDonChiTietResponse hoaDonChiTietResponse : lstDonChiTietResponsesMoi) {
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setHoaDon(hdMoi);
            if (hoaDonChiTietResponse.getMaMonAn() != null) {
                MonAn monAn = (MonAn) monAnService.getOne(hoaDonChiTietResponse.getMaMonAn());
                hoaDonChiTiet.setMonAn(monAn);
                hoaDonChiTiet.setDonGiaMonAn(hoaDonChiTietResponse.getDonGiaMonAn());
                hoaDonChiTiet.setSoLuongMonAn(hoaDonChiTietResponse.getSoLuongMonAn());
                hoaDonChiTiet.setDonGiaCombo(BigDecimal.valueOf(0));
                hoaDonChiTiet.setSoLuongCombo(0);
                String themHDCT = (String) hdctService.add(hoaDonChiTiet);
            } else {
                ComBo comBo = (ComBo) comBoService.getOne(hoaDonChiTietResponse.getMaCombo());
                hoaDonChiTiet.setComBo(comBo);
                hoaDonChiTiet.setDonGiaCombo(hoaDonChiTietResponse.getDonGiaCombo());
                hoaDonChiTiet.setSoLuongCombo(hoaDonChiTietResponse.getSoLuongCombo());
                hoaDonChiTiet.setDonGiaMonAn(BigDecimal.valueOf(0));
                hoaDonChiTiet.setSoLuongMonAn(0);
                String themHDCT = (String) hdctService.add(hoaDonChiTiet);

            }
        }
        hoaDon.setTrangThai(0);
        String upDateTrangThaiHDCu = (String) hds1.update(hoaDon, hoaDon.getMaHoaDon());
        JOptionPane.showMessageDialog(this, "Gộp thành công");
        this.dispose();
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void btnGopTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGopTatCaActionPerformed
        // TODO add your handling code here:

        for (int i = 0; i < lstDonChiTietResponsesCu.size(); i++) {
            if (lstDonChiTietResponsesCu.get(i).getTenCombo() == null) {
                continue;
            } else {
                int indexDeUpDate = 0;
                int dem = 0;
                for (int j = 0; j < lstDonChiTietResponsesMoi.size(); j++) {
                    if (null == lstDonChiTietResponsesMoi.get(j).getTenCombo()) {
                        continue;
                    }
                    if (lstDonChiTietResponsesMoi.get(j).getTenCombo().contains(lstDonChiTietResponsesCu.get(i).getTenCombo())) {
                        indexDeUpDate = i;
                        dem++;
                        lstDonChiTietResponsesMoi.get(j).setSoLuongCombo(lstDonChiTietResponsesMoi.get(j).getSoLuongCombo() + lstDonChiTietResponsesCu.get(i).getSoLuongCombo());
                    }
                }
                if (dem == 0) {
                    lstDonChiTietResponsesMoi.add(lstDonChiTietResponsesCu.get(i));
                }
            }
        }
        for (int i = 0; i < lstDonChiTietResponsesCu.size(); i++) {
            if (lstDonChiTietResponsesCu.get(i).getTenMonAn() == null) {
                continue;
            } else {
                int indexDeUpDate = 0;
                int dem = 0;
                for (int j = 0; j < lstDonChiTietResponsesMoi.size(); j++) {
                    if (null == lstDonChiTietResponsesMoi.get(j).getTenMonAn()) {
                        continue;
                    }
                    if (lstDonChiTietResponsesMoi.get(j).getTenMonAn().contains(lstDonChiTietResponsesCu.get(i).getTenMonAn())) {
                        indexDeUpDate = i;
                        dem++;
                        lstDonChiTietResponsesMoi.get(j).setSoLuongMonAn(lstDonChiTietResponsesMoi.get(j).getSoLuongMonAn() + lstDonChiTietResponsesCu.get(i).getSoLuongMonAn());
                    }
                }
                if (dem == 0) {
                    lstDonChiTietResponsesMoi.add(lstDonChiTietResponsesCu.get(i));
                }
            }
        }
        lstDonChiTietResponsesCu.clear();
        showDataHDCTCu(lstDonChiTietResponsesCu);
        showDataHDCTMoi(lstDonChiTietResponsesMoi);
        String xoaHDCTCu = (String) hdctService.remove(hoaDon);
        HoaDon hd = (HoaDon) hds1.getOne(cbbMaHoaDonMoi.getSelectedItem().toString());
        String xoaHDCTMoi = (String) hdctService.remove(hd);
        HoaDon hdMoi = (HoaDon) hds1.getOne(cbbMaHoaDonMoi.getSelectedItem().toString());
        for (HoaDonChiTietResponse hoaDonChiTietResponse : lstDonChiTietResponsesMoi) {
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setHoaDon(hdMoi);
            if (hoaDonChiTietResponse.getMaMonAn() != null) {
                MonAn monAn = (MonAn) monAnService.getOne(hoaDonChiTietResponse.getMaMonAn());
                hoaDonChiTiet.setMonAn(monAn);
                hoaDonChiTiet.setDonGiaMonAn(hoaDonChiTietResponse.getDonGiaMonAn());
                hoaDonChiTiet.setSoLuongMonAn(hoaDonChiTietResponse.getSoLuongMonAn());
                hoaDonChiTiet.setDonGiaCombo(BigDecimal.valueOf(0));
                hoaDonChiTiet.setSoLuongCombo(0);
                String themHDCT = (String) hdctService.add(hoaDonChiTiet);
            } else {
                ComBo comBo = (ComBo) comBoService.getOne(hoaDonChiTietResponse.getMaCombo());
                hoaDonChiTiet.setComBo(comBo);
                hoaDonChiTiet.setDonGiaCombo(hoaDonChiTietResponse.getDonGiaCombo());
                hoaDonChiTiet.setSoLuongCombo(hoaDonChiTietResponse.getSoLuongCombo());
                hoaDonChiTiet.setDonGiaMonAn(BigDecimal.valueOf(0));
                hoaDonChiTiet.setSoLuongMonAn(0);
                String themHDCT = (String) hdctService.add(hoaDonChiTiet);

            }
        }
        hoaDon.setTrangThai(2);
        hoaDon.setGhiChu("Đã gộp vào hoá đơn " + cbbMaHoaDonMoi.getSelectedItem().toString());
        String upDateTrangThaiHDCu = (String) hds1.update(hoaDon, hoaDon.getMaHoaDon());
        List<ChiTietBanHoaDon> lstChiTietBanHoaDons = chiTietBanHoaDonService.getByHoaDon(hoaDon);
        //SET TRẠNG THÁI BÀN
        //for lấy mã bàn
        for (ChiTietBanHoaDon lstChiTietBanHoaDon : lstChiTietBanHoaDons) {
            Ban ban = (Ban) banService.getOne(lstChiTietBanHoaDon.getBan().getMaBan().toString());
            //khai báo list để getHD chờ where mã bàn:
            List<HoaDon> listHDChuaTT = hoaDonService.getHDChoByMaBan(ban.getMaBan());
            if (listHDChuaTT.size() <= 0) {
                // set trạng thái = 0 cho bàn đã getone về
                ban.setTrangThai(0);
                String setTrangThaiBan = (String) banService.update(ban, ban.getMaBan().toString());
            } else {
                ban.setTrangThai(1);
                String setTrangThaiBan = (String) banService.update(ban, ban.getMaBan().toString());
            }
        }
        JOptionPane.showMessageDialog(this, "Gộp thành công");
        this.dispose();
    }//GEN-LAST:event_btnGopTatCaActionPerformed

    private void tbHDCTCuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHDCTCuMouseClicked
        // TODO add your handling code here:
        int index = tbHDCTCu.getSelectedRow();
        HoaDonChiTietResponse hdctrCu = lstDonChiTietResponsesCu.get(index);
        //         con vớt thằng cần tách vào thằng mới

        //        HoaDonChiTietResponse hdctrMoi = hdctrCu;
        // lấy số lượng của thằng cũ
        Integer soLuongMonAnCu = hdctrCu.getSoLuongMonAn();
        Integer soLuongComboCu = hdctrCu.getSoLuongCombo();

        String soLuong = JOptionPane.showInputDialog("Mời nhập số lượng");
        if (soLuong == null) {
            return;
        } else if (!soLuong.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số");
        } else if (Integer.valueOf(soLuong) <= 0) {
            JOptionPane.showMessageDialog(this, "Số lượng không được < 1");
        } else if (soLuong.equals("")) {
            return;
        } else {
            if (hdctrCu.getMaMonAn() == null) {
                HoaDonChiTietResponse hdctrMoi = new HoaDonChiTietResponse();
                //tính số lượng còn lại của thằng cũ
                Integer soLuongConLai = soLuongComboCu - Integer.valueOf(soLuong);
                if (soLuongConLai < 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng còn lại không được âm");
                    return;
                }
                // sét  số lượng cho thằng mới
                hdctrMoi.setDonGiaCombo(hdctrCu.getDonGiaCombo());
                hdctrMoi.setGhiChu(hdctrCu.getGhiChu());
                hdctrMoi.setMaCombo(hdctrCu.getMaCombo());
                hdctrMoi.setSoLuongCombo(Integer.valueOf(soLuong));
                hdctrMoi.setTenCombo(hdctrCu.getTenCombo());
                System.out.println(hdctrMoi.getTenCombo());
                int indexDeUpDate = 0;
                int dem = 0;
                for (int i = 0; i < lstDonChiTietResponsesMoi.size(); i++) {
                    //                    System.out.println(lstDonChiTietResponsesMoi.get(i).getTenCombo());
                    if (null == lstDonChiTietResponsesMoi.get(i).getTenCombo()) {
                        continue;
                    }
                    if (lstDonChiTietResponsesMoi.get(i).getTenCombo().equals(hdctrCu.getTenCombo())) {
                        indexDeUpDate = i;
                        dem++;
                        lstDonChiTietResponsesMoi.get(indexDeUpDate).setSoLuongCombo(lstDonChiTietResponsesMoi.get(indexDeUpDate).getSoLuongCombo() + Integer.valueOf(soLuong));
                    }
                }
                if (dem == 0) {
                    lstDonChiTietResponsesMoi.add(hdctrMoi);
                }
                showDataHDCTMoi(lstDonChiTietResponsesMoi);
                //sét số lượng còn lại cho thằng cũ
                hdctrCu.setSoLuongCombo(soLuongConLai);
                showDataHDCTCu(lstDonChiTietResponsesCu);
                return;
            } else {
                //tạo hdct mới (tạm thời)
                HoaDonChiTietResponse hdctrMoi = new HoaDonChiTietResponse();
                hdctrMoi.setDonGiaMonAn(hdctrCu.getDonGiaMonAn());
                hdctrMoi.setGhiChu(hdctrCu.getGhiChu());
                hdctrMoi.setMaMonAn(hdctrCu.getMaMonAn());
                hdctrMoi.setTenMonAn(hdctrCu.getTenMonAn());
                Integer soLuongConLai = soLuongMonAnCu - Integer.valueOf(soLuong);
                if (soLuongConLai < 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng còn lại không được âm");
                    return;
                }
                // sét  số lượng cho thằng mới
                hdctrMoi.setSoLuongMonAn(Integer.valueOf(soLuong));
                //add vào list tạm thời
                int indexDeUpDate = 0;
                int dem = 0;
                for (int i = 0; i < lstDonChiTietResponsesMoi.size(); i++) {
                    if (null == lstDonChiTietResponsesMoi.get(i).getTenMonAn()) {
                        continue;
                    }
                    if (lstDonChiTietResponsesMoi.get(i).getTenMonAn().equals(hdctrCu.getTenMonAn())) {
                        indexDeUpDate = i;
                        dem++;
                        lstDonChiTietResponsesMoi.get(indexDeUpDate).setSoLuongMonAn(lstDonChiTietResponsesMoi.get(indexDeUpDate).getSoLuongMonAn() + Integer.valueOf(soLuong));
                    }
                }
                if (dem == 0) {
                    lstDonChiTietResponsesMoi.add(hdctrMoi);
                }
                showDataHDCTMoi(lstDonChiTietResponsesMoi);
                //sét số lượng còn lại cho thằng cũ
                hdctrCu.setSoLuongMonAn(soLuongConLai);
                showDataHDCTCu(lstDonChiTietResponsesCu);
                //                if (soLuongConLai == 0) {
                //                    dtmHDCu.removeRow(index);
                //                }
                //                for (int i = 0; i < lstDonChiTietResponsesCu.size(); i++) {
                //                    if (lstDonChiTietResponsesCu.get(i).getSoLuongMonAn() == 0 && lstDonChiTietResponsesCu.get(i).getSoLuongCombo() > 0) {
                //                        dtmHDCu.removeRow(i);
                //                    }
                //                }
                for (HoaDonChiTietResponse hoaDonChiTietResponse : lstDonChiTietResponsesMoi) {
                    System.out.println(hoaDonChiTietResponse);
                }
                return;
            }
        }
    }//GEN-LAST:event_tbHDCTCuMouseClicked

    private void cbbMaHoaDonMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMaHoaDonMoiActionPerformed
        // TODO add your handling code here:
        HoaDon hd = (HoaDon) hds1.getOne(cbbMaHoaDonMoi.getSelectedItem().toString());
        lstDonChiTietResponsesMoi = hdctResponseService.getAll(hd);
        showDataHDCTMoi(lstDonChiTietResponsesMoi);
    }//GEN-LAST:event_cbbMaHoaDonMoiActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGopTatCa;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JComboBox<String> cbbMaHoaDonMoi;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbMaHDCu;
    private javax.swing.JTable tbHDCTCu;
    private javax.swing.JTable tbHDCTMoi;
    // End of variables declaration//GEN-END:variables
}
