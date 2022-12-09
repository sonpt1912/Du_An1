/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.raven.form;

import com.mycompany.customModel.HoaDonChiTietResponse;
import com.mycompany.domainModel.ChiTietBanHoaDon;
import com.mycompany.domainModel.ComBo;
import com.mycompany.domainModel.HoaDon;
import com.mycompany.domainModel.HoaDonChiTiet;
import com.mycompany.domainModel.MonAn;
import com.mycompany.service.IHoaDonChiTietResponseService;
import com.mycompany.service.impl.BanService;
import com.mycompany.service.impl.ChiTietBanHoaDonService;
import com.mycompany.service.impl.ComBoService;
import com.mycompany.service.impl.HoaDonChiTietResponseService;
import com.mycompany.service.impl.HoaDonChiTietService;
import com.mycompany.service.impl.HoaDonService;
import com.mycompany.service.impl.MonAnService;
import com.mycompany.util.HoaDonUtil;
import java.awt.Color;
import java.awt.Font;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Duongntt
 */
public class JDialogTachHoaDon extends javax.swing.JDialog {

    private List<HoaDonChiTietResponse> lstDonChiTietResponsesCu = new ArrayList<>();
    private List<HoaDonChiTietResponse> lstDonChiTietResponsesMoi = new ArrayList<>();
    private IHoaDonChiTietResponseService hdctrs = new HoaDonChiTietResponseService();
    private DefaultTableModel dtmHDCu = new DefaultTableModel();
    private DefaultTableModel dtmHDMoi = new DefaultTableModel();
    private List<HoaDon> listHD = new ArrayList<>();
    private HoaDonService hoaDonService = new HoaDonService();
    private HoaDonUtil hoaDonUtil = new HoaDonUtil();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private java.util.Date today = new java.util.Date();
    private HoaDonChiTietService hoaDonChiTietService = new HoaDonChiTietService();
    private MonAnService monAnService = new MonAnService();
    private ComBoService comBoService = new ComBoService();
    private List<ChiTietBanHoaDon> listCTBan_HD = new ArrayList<>();
    private ChiTietBanHoaDonService chiTietBanHoaDonService = new ChiTietBanHoaDonService();
    private BanService banService = new BanService();
    // thực thể cả class
    private HoaDon hoaDon;

    /**
     * Creates new form JDailogTachHD
     */
    public JDialogTachHoaDon(java.awt.Frame parent, boolean modal, HoaDon hd) {
        super(parent, modal);
        initComponents();
        hoaDon = hd;
        String headerHoaDonCT[] = {"STT", "Tên món ăn", "Giá món ăn", "Số lượng món ăn", "Tên combo", "Giá combo", "Số lượng combo", "Ghi chú"};
        tbHDCTCu.setModel(dtmHDCu);
        tbHDCTMoi.setModel(dtmHDMoi);
        dtmHDCu.setColumnIdentifiers(headerHoaDonCT);
        dtmHDMoi.setColumnIdentifiers(headerHoaDonCT);
        lstDonChiTietResponsesCu = hdctrs.getAll(hoaDon);
        showDataHDCu(lstDonChiTietResponsesCu);
        //
        JTableHeader hd2 = tbHDCTCu.getTableHeader();
        hd2.setBackground(Color.red);
        hd2.setForeground(Color.red);
        hd2.setFont(new Font("Segoe", Font.BOLD, 13));
        //
        JTableHeader hd1 = tbHDCTMoi.getTableHeader();
        hd1.setBackground(Color.red);
        hd1.setForeground(Color.red);
        hd1.setFont(new Font("Segoe", Font.BOLD, 13));
    }

    private void showDataHDCu(List<HoaDonChiTietResponse> hoaDonChiTietResponses) {
        dtmHDCu.setRowCount(0);
        int stt = 0;
//        for (HoaDonChiTietResponse hoaDonChiTietResponse : hoaDonChiTietResponses) {
//            stt++;
//            dtmHDCu.addRow(hoaDonChiTietResponse.toDataRow(stt));
//            if (hoaDonChiTietResponse.getSoLuongCombo() == 0 && hoaDonChiTietResponse.getSoLuongMonAn() == 0) {
//                continue;
//            }
//        }
        for (int i = 0; i < hoaDonChiTietResponses.size(); i++) {
            stt++;
            dtmHDCu.addRow(hoaDonChiTietResponses.get(i).toDataRow(stt));
//            if (hoaDonChiTietResponses.get(i).getSoLuongCombo() == 0 && hoaDonChiTietResponses.get(i).getSoLuongMonAn() == 0) {
//                dtmHDCu.removeRow(i);
//            }
        }
    }

    private void showDataHDMoi(List<HoaDonChiTietResponse> hoaDonChiTietResponses) {
        dtmHDMoi.setRowCount(0);
        int stt = 0;
        for (HoaDonChiTietResponse hoaDonChiTietResponse : hoaDonChiTietResponses) {
            stt++;
            dtmHDMoi.addRow(hoaDonChiTietResponse.toDataRow(stt));
        }
    }

    private boolean checkSoLuongTach(String soLuongTach) {
        boolean isCheck = false;
        if (soLuongTach.isEmpty() || soLuongTach.isBlank()) {
            JOptionPane.showMessageDialog(null, "Bạn đã không nhập số lượng khách!");
            isCheck = false;
        } else if (!soLuongTach.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(null, "Bạn đã không nhập số cho số lượng khách hàng");
            isCheck = false;;
        } else if (Integer.valueOf(soLuongTach) <= 0) {
            JOptionPane.showMessageDialog(null, "Số lượng khách phải là số nguyên dương");
            isCheck = false;
        } else {
            isCheck = true;
        }
        return isCheck;
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
        btnXacNhan = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHDCTCu = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbHDCTMoi = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("TÁCH HOÁ ĐƠN");

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        btnXacNhan.setBackground(new java.awt.Color(0, 255, 51));
        btnXacNhan.setText("Confirm");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Hoá đơn cũ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Hoá đơn mới");

        btnExit.setBackground(new java.awt.Color(255, 255, 255));
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

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
        jScrollPane1.setViewportView(tbHDCTCu);

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
        jScrollPane2.setViewportView(tbHDCTMoi);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(203, 203, 203))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXacNhan)
                    .addComponent(btnExit))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        listHD = hoaDonService.getAll();
        String maHD = hoaDonUtil.zenMaThuyDuong(listHD);
        //tạo hoá đơn mới
        HoaDon hoaDonNew = new HoaDon();
        hoaDonNew.setGhiChu("Tach tu HD " + hoaDon.getMaHoaDon());
        hoaDonNew.setMaHoaDon(maHD);
        hoaDonNew.setNgayTao(today);
        hoaDonNew.setNhanVien(hoaDon.getNhanVien());
        hoaDonNew.setTrangThai(0);
        hoaDonNew.setSoLuongKhach(0);
        String themHD = hoaDonService.add(hoaDonNew);

        //get list chi tiết bàn hoá đơn của hoá đơn cũ
        listCTBan_HD = chiTietBanHoaDonService.getByHoaDon(hoaDon);
        for (ChiTietBanHoaDon chiTietBanHoaDon : listCTBan_HD) {
            System.out.println(chiTietBanHoaDon.toString());
        }
        //in sz bàn:
        int sz = listCTBan_HD.size();
        //duyệt for để cập nhật bàn đó cho hoá đơn mới => chung bàn
        //getOne hoaDonNew:
        HoaDon HDNew = hoaDonService.getOne(hoaDonNew.getMaHoaDon());
        for (ChiTietBanHoaDon chiTietBanHoaDon : listCTBan_HD) {
            ChiTietBanHoaDon chiTietBanHoaDonNew = new ChiTietBanHoaDon();
            chiTietBanHoaDonNew.setBan(chiTietBanHoaDon.getBan());
            chiTietBanHoaDonNew.setHd(HDNew);
            ///add vào db
            String themCTBan_HD = chiTietBanHoaDonService.add(chiTietBanHoaDonNew);
        }
        //        list ctbanf mới thêm:
        List<ChiTietBanHoaDon> listMoi = chiTietBanHoaDonService.getByHoaDon(HDNew);
        for (ChiTietBanHoaDon chiTietBanHoaDon : listMoi) {
            System.out.println(chiTietBanHoaDon.toString());
        }
        //duyệt list hdct mới tách để insert vào hoá đơn mới tạo
        for (HoaDonChiTietResponse hoaDonChiTietResponse : lstDonChiTietResponsesMoi) {
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setHoaDon(hoaDonNew);
            if (hoaDonChiTietResponse.getMaMonAn() != null) {
                MonAn monAn = monAnService.getOne(hoaDonChiTietResponse.getMaMonAn());
                hoaDonChiTiet.setMonAn(monAn);
                hoaDonChiTiet.setDonGiaMonAn(hoaDonChiTietResponse.getDonGiaMonAn());
                hoaDonChiTiet.setSoLuongMonAn(hoaDonChiTietResponse.getSoLuongMonAn());
                hoaDonChiTiet.setDonGiaCombo(BigDecimal.valueOf(0));
                hoaDonChiTiet.setSoLuongCombo(0);
                hoaDonChiTiet.setGhiChu("Tach tu " + hoaDon.getMaHoaDon());
                String themHDCT = hoaDonChiTietService.add(hoaDonChiTiet);
            } else {
                ComBo comBo = comBoService.getOne(hoaDonChiTietResponse.getMaCombo());
                hoaDonChiTiet.setComBo(comBo);
                hoaDonChiTiet.setDonGiaCombo(hoaDonChiTietResponse.getDonGiaCombo());
                hoaDonChiTiet.setSoLuongCombo(hoaDonChiTietResponse.getSoLuongCombo());
                hoaDonChiTiet.setDonGiaMonAn(BigDecimal.valueOf(0));
                hoaDonChiTiet.setSoLuongMonAn(0);
                String themHDCT = hoaDonChiTietService.add(hoaDonChiTiet);
            }

        }
        //theem xong, clear lisst moiw
        lstDonChiTietResponsesMoi.clear();
        //update hdct cu
        for (HoaDonChiTietResponse hoaDonChiTietResponse : lstDonChiTietResponsesCu) {
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            if (hoaDonChiTietResponse.getMaMonAn() != null) {
                MonAn monAn = monAnService.getOne(hoaDonChiTietResponse.getMaMonAn());
                //System.out.println("HELLOOOOOOOOO  " + HDNew.getId() + " - " + monAn.getId());
                HoaDonChiTiet hdct = hoaDonChiTietService.getOneHDCTByMAHD(hoaDon, monAn);
                hoaDonChiTiet.setMonAn(monAn);
                hoaDonChiTiet.setDonGiaMonAn(hoaDonChiTietResponse.getDonGiaMonAn());
                hoaDonChiTiet.setSoLuongMonAn(hoaDonChiTietResponse.getSoLuongMonAn());
                hoaDonChiTiet.setDonGiaCombo(BigDecimal.valueOf(0));
                hoaDonChiTiet.setSoLuongCombo(0);
                String updatHDCT = hoaDonChiTietService.updateHDCTById(hoaDonChiTiet, hdct.getId());
            } else {
                ComBo comBo = comBoService.getOne(hoaDonChiTietResponse.getMaCombo());
                // System.out.println("HELLOOOOOOOOO  " + HDNew.getId() + " - " + comBo.getId());
                HoaDonChiTiet hdct = hoaDonChiTietService.getOneHDCTByCombo(hoaDon, comBo);
                hoaDonChiTiet.setComBo(comBo);
                hoaDonChiTiet.setDonGiaCombo(hoaDonChiTietResponse.getDonGiaCombo());
                hoaDonChiTiet.setSoLuongCombo(hoaDonChiTietResponse.getSoLuongCombo());
                hoaDonChiTiet.setDonGiaMonAn(BigDecimal.valueOf(0));
                hoaDonChiTiet.setSoLuongMonAn(0);
                String updatHDCT = hoaDonChiTietService.updateHDCTById(hoaDonChiTiet, hdct.getId());
            }
        }
        JOptionPane.showMessageDialog(this, "Thanh cong!");
        this.dispose();
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void tbHDCTCuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHDCTCuMouseClicked

        // lấy thằng cần tách
        int index = tbHDCTCu.getSelectedRow();
        HoaDonChiTietResponse hdctrCu = lstDonChiTietResponsesCu.get(index);
        //        // con vớt thằng cần tách vào thằng mới
        //
        //        HoaDonChiTietResponse hdctrMoi = hdctrCu;
        // lấy số lượng của thằng cũ
        Integer soLuongMonAnCu = hdctrCu.getSoLuongMonAn();
        Integer soLuongComboCu = hdctrCu.getSoLuongCombo();

        String soLuong = JOptionPane.showInputDialog("Mời nhập số lượng");

        if (soLuong == null) {
            return;
        } else if (soLuong.equals("")) {
            return;
        } else if (!soLuong.matches("[0-9]+") || Integer.valueOf(soLuong) <= 0) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên dương");
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
                // set  số lượng cho thằng mới
                hdctrMoi.setDonGiaCombo(hdctrCu.getDonGiaCombo());
                hdctrMoi.setGhiChu(hdctrCu.getGhiChu());
                hdctrMoi.setMaCombo(hdctrCu.getMaCombo());
                hdctrMoi.setSoLuongCombo(Integer.valueOf(soLuong));
                hdctrMoi.setTenCombo(hdctrCu.getTenCombo());
                System.out.println(hdctrMoi);
                hdctrCu.setGhiChu("Da tach " + soLuong + " sp sang HD moi");

                lstDonChiTietResponsesMoi.add(hdctrMoi);
                showDataHDMoi(lstDonChiTietResponsesMoi);
                //sét số lượng còn lại cho thằng cũ
                hdctrCu.setSoLuongCombo(soLuongConLai);
                showDataHDCu(lstDonChiTietResponsesCu);
                //                if (soLuongConLai == 0) {
                //                    dtmHDCu.removeRow(index);
                //                }
                //                for (int i = 0; i < lstDonChiTietResponsesCu.size(); i++) {
                //                    if (lstDonChiTietResponsesCu.get(i).getSoLuongCombo() == 0 && lstDonChiTietResponsesCu.get(i).getSoLuongMonAn() > 0) {
                //                        dtmHDCu.removeRow(i);
                //                    }
                //                }
                for (HoaDonChiTietResponse hoaDonChiTietResponse : lstDonChiTietResponsesMoi) {
                    System.out.println(hoaDonChiTietResponse);
                }
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
                hdctrCu.setGhiChu("Da tach " + soLuong + " sp sang HD moi");
                //add vào list tạm thời
                lstDonChiTietResponsesMoi.add(hdctrMoi);
                showDataHDMoi(lstDonChiTietResponsesMoi);
                //sét số lượng còn lại cho thằng cũ
                hdctrCu.setSoLuongMonAn(soLuongConLai);
                showDataHDCu(lstDonChiTietResponsesCu);
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

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbHDCTCu;
    private javax.swing.JTable tbHDCTMoi;
    // End of variables declaration//GEN-END:variables
}
