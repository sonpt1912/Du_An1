/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.raven.form;

import com.mycompany.customModel.HoaDonChiTietResponse;
import com.mycompany.customModel.HoaDonThanhToanCustom;
import com.mycompany.domainModel.Ban;
import com.mycompany.domainModel.ChiTietBanHoaDon;
import com.mycompany.domainModel.GiaoDich;
import com.mycompany.domainModel.HoaDon;
import com.mycompany.domainModel.HoaDonChiTiet;
import com.mycompany.domainModel.KhachHang;
import com.mycompany.domainModel.NhanVien;
import com.mycompany.domainModel.RankKhachHang;
import com.mycompany.service.ICommonService;
import com.mycompany.service.IHoaDonChiTietResponseService;
import com.mycompany.service.impl.BanService;
import com.mycompany.service.impl.ChiTietBanHoaDonService;
import com.mycompany.service.impl.GiaoDichService;
import com.mycompany.service.impl.HoaDonChiTietResponseService;
import com.mycompany.service.impl.HoaDonService;
import com.mycompany.service.impl.KhachHangService;
import com.mycompany.service.impl.NhanVienService;
import com.mycompany.service.impl.RankServiceImpl;
import com.mycompany.util.ThanhToanUtil;
import java.awt.print.PrinterException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class JDialogThanhToan extends javax.swing.JDialog {

    private List<HoaDonChiTiet> listHdCt = new ArrayList<>();
    private HoaDonThanhToanCustom hdCustom;
    private DefaultTableModel dtmHoaDonCT = new DefaultTableModel();
    private ICommonService hds = new HoaDonService();
    private List<HoaDonChiTietResponse> lstHDCTResponses = new ArrayList<>();
    private IHoaDonChiTietResponseService hdctResponseService = new HoaDonChiTietResponseService();
    private HoaDonService hoaDonService = new HoaDonService();
    private KhachHangService khachHangService = new KhachHangService();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private ThanhToanUtil thanhToanUtil = new ThanhToanUtil();
    private GiaoDichService giaoDichService = new GiaoDichService();
    private BanService banService = new BanService();
    private List<ChiTietBanHoaDon> listCTBan_HD = new ArrayList<>();
    private ChiTietBanHoaDonService chiTietBanHoaDonService = new ChiTietBanHoaDonService();
    private java.util.Date today = new java.util.Date();
    private HoaDon hoaDon;
    private RankServiceImpl rankServiceImpl = new RankServiceImpl();
//    private String tenMon;
//    private String giaMonAn;
//    private String soLuong;
//    private String tongTien;
    private DecimalFormat df = new DecimalFormat("0.00");
    private KhachHang khachHang = new KhachHang();
    private BigDecimal tongTienTT = new BigDecimal(0);
    private BigDecimal tienTraLai = new BigDecimal(0);
    private BigDecimal tienMat = new BigDecimal(0);
    private BigDecimal tienCK = new BigDecimal(0);
    private BigDecimal tienGiamGia = new BigDecimal(0);
    private double thue = 0;

    /**
     * Creates new form JDialogThanhToan
     */
    public JDialogThanhToan(java.awt.Frame parent, boolean modal, HoaDonThanhToanCustom hoaDonCustom) {
        super(parent, modal);
        initComponents();

        hdCustom = hoaDonCustom;
        hoaDon = hoaDonService.getOne(hdCustom.getMaHD());
        tbHDCT.setModel(dtmHoaDonCT);
        String headerHoaDonCT[] = {"STT", "Tên món ăn", "Giá món ăn", "Số lượng món ăn", "Tên combo", "Giá combo", "Số lượng combo", "Tổng tiền", "Ghi chú"};
        dtmHoaDonCT.setColumnIdentifiers(headerHoaDonCT);
        txtMaNV.setText(hdCustom.getMaNV());
        txtMaHD.setText(hdCustom.getMaHD());
        if (hdCustom.getMaKH().trim().isEmpty()) {
            txtTenKhachHang.setText("");
        } else {
            KhachHang khachHang = khachHangService.getOne(hdCustom.getMaKH());
            txtTenKhachHang.setText(khachHang.getTen());
        }
        txtNgayTao.setText(dateFormat.format(hoaDon.getNgayTao()));
        txtTongTien.setText(String.valueOf(hdCustom.getTongTien()));
        if (hdCustom.getTienDuocGiam() != null) {
            txtTienDuocGiam.setText(String.valueOf(hdCustom.getTienDuocGiam()));
        } else {
            txtTienDuocGiam.setText(hdCustom.getTienDuocGiam().toString());
        }
        tienMat = hdCustom.getTienMat();
        txtTiennMat.setText(tienMat.toString());
        tienCK = hdCustom.getTienChuyenKhoan();
        txtChuyenKhoan.setText(tienCK.toString());
        tienTraLai = hdCustom.getTienThua();
        txtTienThua.setText(tienTraLai.toString());
        txtGhiChu.setText(hdCustom.getGhiChu());
        txtBan.setText(hdCustom.getListBan());
        lstHDCTResponses = hdctResponseService.getAll(hoaDon);
        txtBan.setEditable(false);
        txtChuyenKhoan.setEditable(false);
        txtMaHD.setEditable(false);
        txtNgayTao.setEditable(false);
        txtTienThua.setEditable(false);
        txtTiennMat.setEditable(false);
        txtChuyenKhoan.setEditable(false);
        showDataHDCT(lstHDCTResponses);
        txtTongTien.setEditable(false);
        txtMaNV.setEditable(false);
        txtTenKhachHang.setEditable(false);
        // BigDecimal thue = BigDecimal.valueOf(0);
        //đổi double 
        thue = Double.valueOf(String.valueOf(hdCustom.getTongTien())) * 0.1;
        BigDecimal tongTienHang = new BigDecimal(txtTongTien.getText());
        //thue = tongTienHang.multiply(new BigDecimal(0.1));
        BigDecimal tongTienThanhToan = new BigDecimal(0);
        // tongTienThanhToan = hdCustom.getTongTien().add(thue);
        tongTienThanhToan = hdCustom.getTongTien().add(new BigDecimal(thue));
        tongTienTT = tongTienThanhToan;
        txtTienThanhToan.setText(String.valueOf(df.format(tongTienThanhToan)));
        //txtTienThanhToan.setText(tongTienThanhToan.toString());
    }

    private void showDataHDCT(List<HoaDonChiTietResponse> hoaDonChiTietResponses) {
        dtmHoaDonCT.setRowCount(0);
        int stt = 0;
        for (HoaDonChiTietResponse hoaDonChiTietResponse : hoaDonChiTietResponses) {
            stt++;
            dtmHoaDonCT.addRow(hoaDonChiTietResponse.toDataRow(stt));
        }
    }

    private void thanhToan() {
        HoaDon hd = hoaDonService.getOne(hoaDon.getMaHoaDon());
        //Th1: thanh toán bằng tiền mặt:
        if (cbTienMat.isSelected() && !(cbChuyenKhoan.isSelected())) {
            //check tiền mặt:
            try {
                //1.thêm giao dịch:
                GiaoDich giaoDich = new GiaoDich();
                giaoDich.setHinhThucThanhToan("Tiền mặt");
                giaoDich.setHoaDon(hd);
                giaoDich.setSoTienThanhToan(new BigDecimal(txtTiennMat.getText()));
                String addGD = giaoDichService.add(giaoDich);
                //chuyển trạng thái hoá đơn:
                if (hoaDon.getKhachHang() == null) {
                    hd.setKhachHang(null);
                } else {
                    hd.setKhachHang(hoaDon.getKhachHang());
                }
                hd.setGhiChu(txtGhiChu.getText());
                NhanVien nhanVien = new NhanVienService().getOne(hoaDon.getNhanVien().getMa());
                hd.setNhanVien(nhanVien);
                //System.out.println("====" + txtTienThanhToan.getText());
                //hd.setTongTien(new BigDecimal(txtTienThanhToan.getText()));
                hd.setTongTien(tongTienTT);
                if (hoaDon.getTienDuocGiam() == null || hoaDon.getTienDuocGiam().compareTo(new BigDecimal(0)) < 0) {
                    String tienGiam = txtTienDuocGiam.getText();
                    hd.setTienDuocGiam(new BigDecimal(tienGiam));
                }
                String tienGiam = txtTienDuocGiam.getText();
                hd.setTienDuocGiam(new BigDecimal(tienGiam));
                hd.setNgayThanhToan(today);
                hd.setTrangThai(1);
                //set KH cho HD:
                khachHang = khachHangService.getOneBySdt(txtSdtKH.getText());;
                if (khachHang != null) {
                    hd.setKhachHang(khachHang);
                }
                String update = hoaDonService.update(hd, hoaDon.getMaHoaDon());
                //get list bàn:
                listCTBan_HD = chiTietBanHoaDonService.getByHoaDon(hoaDon);
                for (ChiTietBanHoaDon chiTietBanHoaDon : listCTBan_HD) {
                    Ban ban = chiTietBanHoaDon.getBan();
                    ban.setTrangThai(0);
                    String updateBan = banService.update(ban, ban.getMaBan().toString());
                }
                JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
                if (khachHang != null) {
                    String rank = rankServiceImpl.rank(khachHang.getId());
                    RankKhachHang r = rankServiceImpl.getOneById(rank);
                    String updateRank = khachHangService.updateIdRank(khachHang.getId(), r);
                }
                this.dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Thanh toán không thành công");
                e.printStackTrace();
            }
            //TH2: thanh toán chuyển khoản:
        } else if (!(cbTienMat.isSelected()) && cbChuyenKhoan.isSelected()) {
            //check tiền chuyển khoản:
            try {
                //1.thêm giao dịch:
                GiaoDich giaoDich = new GiaoDich();
                giaoDich.setHinhThucThanhToan("Chuyển khoản");
                giaoDich.setHoaDon(hd);
                giaoDich.setSoTienThanhToan(new BigDecimal(txtChuyenKhoan.getText()));
                String addGD = giaoDichService.add(giaoDich);
                //chuyển trạng thái hoá đơn:
                if (hoaDon.getKhachHang() == null) {
                    hd.setKhachHang(null);
                } else {
                    hd.setKhachHang(hoaDon.getKhachHang());
                }
                hd.setGhiChu(txtGhiChu.getText());
                NhanVien nhanVien = new NhanVienService().getOne(hoaDon.getNhanVien().getMa());
                hd.setNhanVien(nhanVien);
                // hd.setTongTien(new BigDecimal(txtTienThanhToan.getText()));
                hd.setTongTien(tongTienTT);
                if (hoaDon.getTienDuocGiam() == null || hoaDon.getTienDuocGiam().compareTo(new BigDecimal(0)) < 0) {
                    String tienGiam = txtTienDuocGiam.getText();
                    hd.setTienDuocGiam(new BigDecimal(tienGiam));
                }
                String tienGiam = txtTienDuocGiam.getText();
                hd.setTienDuocGiam(new BigDecimal(tienGiam));
                hd.setNgayThanhToan(today);
                hd.setTrangThai(1);
                //set KH cho HD:
                khachHang = khachHangService.getOneBySdt(txtSdtKH.getText());;
                if (khachHang != null) {
                    hd.setKhachHang(khachHang);
                }
                String update = hoaDonService.update(hd, hoaDon.getMaHoaDon());
                //get list bàn:
                listCTBan_HD = chiTietBanHoaDonService.getByHoaDon(hoaDon);
                for (ChiTietBanHoaDon chiTietBanHoaDon : listCTBan_HD) {
                    Ban ban = chiTietBanHoaDon.getBan();
                    ban.setTrangThai(0);
                    String updateBan = banService.update(ban, ban.getMaBan().toString());
                }
                JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
                if (khachHang != null) {
                    String rank = rankServiceImpl.rank(khachHang.getId());
                    RankKhachHang r = rankServiceImpl.getOneById(rank);
                    String updateRank = khachHangService.updateIdRank(khachHang.getId(), r);
                }
                this.dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Thanh toán không thành công");
                e.printStackTrace();
            }
            //TH 3: hai hình thức:
        } else if (cbChuyenKhoan.isSelected() && cbTienMat.isSelected()) {
            //check tiền chuyển khoản và tiền mặt:
            try {
                //1.thêm giao dịch:
                //giao dịch 1: chuyển khoản
                GiaoDich giaoDich = new GiaoDich();
                giaoDich.setHinhThucThanhToan("Chuyển khoản");
                giaoDich.setHoaDon(hd);
                giaoDich.setSoTienThanhToan(new BigDecimal(txtChuyenKhoan.getText()));
                String addGD = giaoDichService.add(giaoDich);
                //giao dịch 2: tiền mặt
                GiaoDich giaoDich2 = new GiaoDich();
                giaoDich2.setHinhThucThanhToan("Tiền mặt");
                giaoDich2.setHoaDon(hd);
                giaoDich2.setSoTienThanhToan(new BigDecimal(txtTiennMat.getText()));
                String addGD2 = giaoDichService.add(giaoDich2);
                //chuyển trạng thái hoá đơn:
                if (hoaDon.getKhachHang() == null) {
                    hd.setKhachHang(null);
                } else {
                    hd.setKhachHang(hoaDon.getKhachHang());
                }
                hd.setGhiChu(txtGhiChu.getText());
                NhanVien nhanVien = new NhanVienService().getOne(hoaDon.getNhanVien().getMa());
                hd.setNhanVien(nhanVien);
                // hd.setTongTien(new BigDecimal(txtTienThanhToan.getText()));
                hd.setTongTien(tongTienTT);
                if (hoaDon.getTienDuocGiam() == null || hoaDon.getTienDuocGiam().compareTo(new BigDecimal(0)) < 0) {
                    String tienGiam = txtTienDuocGiam.getText();
                    hd.setTienDuocGiam(new BigDecimal(tienGiam));
                }
                String tienGiam = txtTienDuocGiam.getText();
                hd.setTienDuocGiam(new BigDecimal(tienGiam));
                hd.setTrangThai(1);
                //set KH cho HD:
                khachHang = khachHangService.getOneBySdt(txtSdtKH.getText());;
                if (khachHang != null) {
                    hd.setKhachHang(khachHang);
                }
                String update = hoaDonService.update(hd, hoaDon.getMaHoaDon());
                //get list bàn:
                listCTBan_HD = chiTietBanHoaDonService.getByHoaDon(hoaDon);
                for (ChiTietBanHoaDon chiTietBanHoaDon : listCTBan_HD) {
                    Ban ban = chiTietBanHoaDon.getBan();
                    ban.setTrangThai(0);
                    String updateBan = banService.update(ban, ban.getMaBan().toString());
                }
                JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
                if (khachHang != null) {
                    String rank = rankServiceImpl.rank(khachHang.getId());
                    RankKhachHang r = rankServiceImpl.getOneById(rank);
                    String updateRank = khachHangService.updateIdRank(khachHang.getId(), r);
                }
                this.dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Thanh toán không thành công");
                e.printStackTrace();
            }
        }
    }
// CheckValidateTienInHoaDonMau;

    private void checkValidateTien() {
        try {
            if (cbTienMat.isSelected() && !(cbChuyenKhoan.isSelected())) {
                if (thanhToanUtil.checkBigDecimal(txtTiennMat.getText())) {
                    txtPrint.setText("");
                    thanhToanIn();
                }
            } else if (!(cbTienMat.isSelected()) && cbChuyenKhoan.isSelected()) {
                if (thanhToanUtil.checkBigDecimal(txtChuyenKhoan.getText())) {
                    txtPrint.setText("");
                    thanhToanIn();
                }
            } else if (cbChuyenKhoan.isSelected() && cbTienMat.isSelected()) {
                if (thanhToanUtil.checkBigDecimal(txtTiennMat.getText()) && thanhToanUtil.checkBigDecimal(txtChuyenKhoan.getText())) {
                    txtPrint.setText("");
                    thanhToanIn();
                }
            } else {
                thanhToanIn();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
// InHoaDonMau

    private void thanhToanIn() {
        BigDecimal tienMat = new BigDecimal(txtTiennMat.getText());
        BigDecimal chuyenKhoan = new BigDecimal(txtChuyenKhoan.getText());
        BigDecimal tong = tienMat.add(chuyenKhoan);
        String ten = "";
        String soLuong = "";
        String donGia = "";
        BigDecimal tongTien = BigDecimal.valueOf(0);
        String trangThai = "";
        if (hoaDon.getTrangThai() == 0) {
            trangThai = "Chờ Thanh Toán";
        } else if (hoaDon.getTrangThai() == 1) {
            trangThai = "Đã Thanh Toán";
        }
        lstHDCTResponses = hdctResponseService.getAll(hoaDon);
        txtPrint.setText(txtPrint.getText() + "     ====================CỬA HÀNG.....===============================\n\n");
        txtPrint.setText(txtPrint.getText() + "     ======================HÓA ĐƠN THANH TOÁN=====================\n\n\n");
        txtPrint.setText(txtPrint.getText() + "     Mã NV                :    " + txtMaNV.getText() + "                          " + "Ngày Tạo              : " + txtNgayTao.getText() + "\t\n\n");
        txtPrint.setText(txtPrint.getText() + "     Mã HĐ                :    " + txtMaHD.getText() + "                         " + "Ngày Thanh Toán: " + "\t\n\n");
        txtPrint.setText(txtPrint.getText() + "     Thu Ngân           :    " + txtMaNV.getText() + "\t\n\n");
        txtPrint.setText(txtPrint.getText() + "     Tên Khách Hàng:    " + txtTenKhachHang.getText() + "                                       " + "Bàn:    " + txtBan.getText() + "\t\n\n");
        txtPrint.setText(txtPrint.getText() + "     Ghi Chú              :    " + txtGhiChu.getText() + "\t\n\n");
        txtPrint.setText(txtPrint.getText() + "     Trạng Thái          :    " + trangThai + "\t\n\n\n");
        txtPrint.setText(txtPrint.getText() + "     Tên Món" + "\t" + "Số Lượng" + "\t" + "Đơn Giá" + "\t" + "Tổng Tiền" + "\n");
        txtPrint.setText(txtPrint.getText() + "     --------------------------------------------------------------------------------------------------------------\n");
        for (int i = 0; i < lstHDCTResponses.size(); i++) {
            if (lstHDCTResponses.get(i).getTenCombo() == null) {
                ten = lstHDCTResponses.get(i).getTenMonAn();
                soLuong = String.valueOf(lstHDCTResponses.get(i).getSoLuongMonAn());
                donGia = String.valueOf(lstHDCTResponses.get(i).getDonGiaMonAn());
                tongTien = lstHDCTResponses.get(i).getDonGiaMonAn().multiply(new BigDecimal(lstHDCTResponses.get(i).getSoLuongMonAn()));
                txtPrint.setText(txtPrint.getText() + "     " + ten + "\t" + soLuong + "\t" + donGia + "\t" + String.valueOf(tongTien) + "\n");
            } else if (lstHDCTResponses.get(i).getTenMonAn() == null) {
                ten = lstHDCTResponses.get(i).getTenCombo();
                soLuong = String.valueOf(lstHDCTResponses.get(i).getSoLuongCombo());
                donGia = String.valueOf(lstHDCTResponses.get(i).getDonGiaCombo());
                tongTien = lstHDCTResponses.get(i).getDonGiaCombo().multiply(new BigDecimal(lstHDCTResponses.get(i).getSoLuongCombo()));
                txtPrint.setText(txtPrint.getText() + "     " + ten + "\t" + soLuong + "\t" + donGia + "\t" + String.valueOf(tongTien) + "\n");
            }
        }
        txtPrint.setText(txtPrint.getText() + "     --------------------------------------------------------------------------------------------------------------\n");
        txtPrint.setText(txtPrint.getText() + "     \t\t\t            TIỀN HÀNG     :    " + txtTongTien.getText() + "\t\n\n");
        txtPrint.setText(txtPrint.getText() + "     \t\t\t            THUẾ VAT      :    " + "10%" + "\t\n\n");
        txtPrint.setText(txtPrint.getText() + "     \t\t\t            TIỀN ĐƯỢC GIẢM:    " + txtTienDuocGiam.getText() + "\t\n");
        txtPrint.setText(txtPrint.getText() + "    __________________________________________________________________\n");
        txtPrint.setText(txtPrint.getText() + "     \t\t\t            TỔNG TIỀN     :    " + txtTienThanhToan.getText() + "\t\n\n");
        txtPrint.setText(txtPrint.getText() + "     \t\t\t            TIỀN KHÁCH ĐƯA:    " + String.valueOf(tong) + "\t\n\n");
        txtPrint.setText(txtPrint.getText() + "     \t\t\t            TIỀN THỪA     :    " + txtTienThua.getText() + "\t\n\n");
        txtPrint.setText(txtPrint.getText() + "     -----------------------Cảm Ơn Quý Khách_Hẹn Gặp Lại Quý Khách--------------------------\n");
        int thanhToan = JOptionPane.showConfirmDialog(null, "Bạn có muốn in hay không");
        if (thanhToan == JOptionPane.NO_OPTION) {
            return;
        } else if (thanhToan == JOptionPane.CLOSED_OPTION) {
            return;
        } else if (thanhToan == JOptionPane.CANCEL_OPTION) {
            return;
        } else {
            try {
                // TODO add your handling code here:
                txtPrint.print();
            } catch (PrinterException ex) {
                Logger.getLogger(JDialogThanhToan.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
//checkInHoaDon

    private void checkInHoaDon() {
        txtPrint.setText("");
        BigDecimal tienMat = new BigDecimal(txtTiennMat.getText());
        BigDecimal chuyenKhoan = new BigDecimal(txtChuyenKhoan.getText());
        BigDecimal tong = tienMat.add(chuyenKhoan);
        String ten = "";
        String soLuong = "";
        String donGia = "";
        BigDecimal tongTien = BigDecimal.valueOf(0);
        String trangThai = "";
        BigDecimal thue = BigDecimal.valueOf(0);
        BigDecimal tongTienHang = new BigDecimal(txtTongTien.getText());
        thue = tongTienHang.multiply(new BigDecimal(0.1));
//        if (hoaDon.getTrangThai() == 0) {
        //            trangThai = "Chờ Thanh Toán";
        //        } else if (hoaDon.getTrangThai() == 1) {
        //            trangThai = "Đã Thanh Toán";
        //        }

        lstHDCTResponses = hdctResponseService.getAll(hoaDon);
        txtPrint.setText(txtPrint.getText() + "     ====================CỬA HÀNG.....===============================\n\n");
        txtPrint.setText(txtPrint.getText() + "     ======================HÓA ĐƠN THANH TOÁN=====================\n\n\n");
        txtPrint.setText(txtPrint.getText() + "     Mã NV                :    " + txtMaNV.getText() + "                          " + "Ngày Tạo              : " + txtNgayTao.getText() + "\t\n\n");
        txtPrint.setText(txtPrint.getText() + "     Mã HĐ                :    " + txtMaHD.getText() + "                         " + "Ngày Thanh Toán: " + "\t\n\n");
        txtPrint.setText(txtPrint.getText() + "     Thu Ngân           :    " + txtMaNV.getText() + "\t\n\n");
        txtPrint.setText(txtPrint.getText() + "     Tên Khách Hàng:    " + txtTenKhachHang.getText() + "                                      " + "Bàn:    " + txtBan.getText() + "\t\n\n");
        txtPrint.setText(txtPrint.getText() + "     Ghi Chú              :    " + txtGhiChu.getText() + "\t\n\n");
        txtPrint.setText(txtPrint.getText() + "     Trạng Thái         :    " + "Đã Thanh Toán" + "\t\n\n\n");
        txtPrint.setText(txtPrint.getText() + "     Tên Món" + "\t" + "Số Lượng" + "\t" + "Đơn Giá" + "\t" + "Tổng Tiền" + "\n");
        txtPrint.setText(txtPrint.getText() + "     --------------------------------------------------------------------------------------------------------------\n");
        for (int i = 0; i < lstHDCTResponses.size(); i++) {
            if (lstHDCTResponses.get(i).getTenCombo() == null) {
                ten = lstHDCTResponses.get(i).getTenMonAn();
                soLuong = String.valueOf(lstHDCTResponses.get(i).getSoLuongMonAn());
                donGia = String.valueOf(lstHDCTResponses.get(i).getDonGiaMonAn());
                tongTien = lstHDCTResponses.get(i).getDonGiaMonAn().multiply(new BigDecimal(lstHDCTResponses.get(i).getSoLuongMonAn()));
                txtPrint.setText(txtPrint.getText() + "     " + ten + "\t" + soLuong + "\t" + donGia + "\t" + String.valueOf(tongTien) + "\n");
            } else if (lstHDCTResponses.get(i).getTenMonAn() == null) {
                ten = lstHDCTResponses.get(i).getTenCombo();
                soLuong = String.valueOf(lstHDCTResponses.get(i).getSoLuongCombo());
                donGia = String.valueOf(lstHDCTResponses.get(i).getDonGiaCombo());
                tongTien = lstHDCTResponses.get(i).getDonGiaCombo().multiply(new BigDecimal(lstHDCTResponses.get(i).getSoLuongCombo()));
                txtPrint.setText(txtPrint.getText() + "     " + ten + "\t" + soLuong + "\t" + donGia + "\t" + String.valueOf(tongTien) + "\n");
            }
        }
        txtPrint.setText(txtPrint.getText() + "     --------------------------------------------------------------------------------------------------------------\n");
        txtPrint.setText(txtPrint.getText() + "     \t\t\t            TIỀN HÀNG     :    " + txtTongTien.getText() + "\t\n\n");
        txtPrint.setText(txtPrint.getText() + "     \t\t\t            THUẾ VAT      :    " + "10%" + "\t\n\n");
        txtPrint.setText(txtPrint.getText() + "     \t\t\t            TIỀN ĐƯỢC GIẢM:    " + txtTienDuocGiam.getText() + "\t\n");
        txtPrint.setText(txtPrint.getText() + "    __________________________________________________________________\n");
        txtPrint.setText(txtPrint.getText() + "     \t\t\t            TỔNG TIỀN     :    " + txtTienThanhToan.getText() + "\t\n\n");
        txtPrint.setText(txtPrint.getText() + "     \t\t\t            TIỀN KHÁCH ĐƯA:    " + String.valueOf(tong) + "\t\n\n");
        txtPrint.setText(txtPrint.getText() + "     \t\t\t            TIỀN THỪA     :    " + txtTienThua.getText() + "\t\n\n");
        txtPrint.setText(txtPrint.getText() + "     -------------------Cảm Ơn Quý Khách_Hẹn Gặp Lại Quý Khách--------------------\n");
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
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHDCT = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtNgayTao = new javax.swing.JTextField();
        txtMaHD = new javax.swing.JTextField();
        txtMaNV = new javax.swing.JTextField();
        txtTongTien = new javax.swing.JTextField();
        txtTienDuocGiam = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTiennMat = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtBan = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtGhiChu = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtChuyenKhoan = new javax.swing.JTextField();
        cbTienMat = new javax.swing.JCheckBox();
        cbChuyenKhoan = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        txtTienThanhToan = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtTenKhachHang = new javax.swing.JTextField();
        txtSdtKH = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnThemKH = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        lbRank = new javax.swing.JLabel();
        txtPhanTramTheoRank = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtPrint = new javax.swing.JTextArea();
        btnInHoaDonMau = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 255));
        jLabel1.setText("NHÀ HÀNG 'HÀU'");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(340, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(334, 334, 334))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 3));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("MÃ HĐ                 :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("MÃ NV:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("NGÀY TẠO:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("TỔNG TIỀN:");

        tbHDCT.setBackground(new java.awt.Color(255, 204, 204));
        tbHDCT.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbHDCT);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("TIỀN ĐƯỢC GIẢM:");

        txtTienDuocGiam.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienDuocGiamCaretUpdate(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("TIỀN MẶT:");

        txtTiennMat.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTiennMatCaretUpdate(evt);
            }
        });
        txtTiennMat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTiennMatActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("TIỀN THỪA           :");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("BÀN                       :");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("GHI CHÚ               :");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("CHUYỂN KHOẢN:");

        txtChuyenKhoan.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtChuyenKhoanCaretUpdate(evt);
            }
        });
        txtChuyenKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtChuyenKhoanMouseClicked(evt);
            }
        });
        txtChuyenKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChuyenKhoanActionPerformed(evt);
            }
        });

        cbTienMat.setText("Tiền mặt");
        cbTienMat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTienMatActionPerformed(evt);
            }
        });

        cbChuyenKhoan.setText("Chuyển khoản");
        cbChuyenKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbChuyenKhoanActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("TIỀN THANH TOÁN:");

        txtTienThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienThanhToanActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("TÊN KHÁCH HÀNG:");

        txtSdtKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSdtKHActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("SDT KH:");

        btnThemKH.setText("Thêm");
        btnThemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKHActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("RANK :");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Phần trăm:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtSdtKH, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addGap(20, 20, 20))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbRank, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(96, 96, 96))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(txtPhanTramTheoRank, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lbRank)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSdtKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPhanTramTheoRank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNgayTao, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(txtTongTien)
                            .addComponent(txtTienDuocGiam))
                        .addGap(385, 385, 385))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel2))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTienThua, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                    .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBan, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTienThanhToan)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(cbTienMat)
                                .addGap(133, 133, 133)
                                .addComponent(cbChuyenKhoan))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(txtTiennMat, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(txtChuyenKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(23, 23, 23)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbChuyenKhoan)
                            .addComponent(cbTienMat)))
                    .addComponent(txtTienDuocGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTiennMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtChuyenKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTienThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));

        txtPrint.setColumns(20);
        txtPrint.setRows(5);
        jScrollPane3.setViewportView(txtPrint);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnInHoaDonMau.setBackground(new java.awt.Color(51, 255, 51));
        btnInHoaDonMau.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnInHoaDonMau.setText("IN Hoá Đơn Mẫu");
        btnInHoaDonMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHoaDonMauActionPerformed(evt);
            }
        });

        btnExit.setBackground(new java.awt.Color(204, 204, 204));
        btnExit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnExit.setText("EXIT");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnThanhToan.setBackground(new java.awt.Color(255, 255, 0));
        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThanhToan.setText("THANH TOÁN");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnInHoaDonMau, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnInHoaDonMau, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExit)
                            .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnInHoaDonMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHoaDonMauActionPerformed
        checkValidateTien();
    }//GEN-LAST:event_btnInHoaDonMauActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        //kiểm tra  thanh toán theo hình thức nào
        HoaDon hd = hoaDonService.getOne(hoaDon.getMaHoaDon());
        if (!(cbChuyenKhoan.isSelected()) && !(cbTienMat.isSelected())) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hình thức thanh toán!");
            //Th1: thanh toán bằng tiền mặt:
        } else if (cbTienMat.isSelected() && !(cbChuyenKhoan.isSelected())) {
            //check tiền mặt:
            if (thanhToanUtil.checkBigDecimal(txtTiennMat.getText())) {
                //check tìeen đủ chưa:
                if (tienTraLai.compareTo(new BigDecimal(0)) < 0) {
                    JOptionPane.showMessageDialog(this, "Chưa đủ tiền!");
                } else {
                    checkInHoaDon();
                    int thanhToan = JOptionPane.showConfirmDialog(this, "Bạn có muốn in hóa đơn hay không!");
                    if (thanhToan == JOptionPane.NO_OPTION) {
                        thanhToan();
                        return;
                    } else if (thanhToan == JOptionPane.CLOSED_OPTION) {
                        JOptionPane.showMessageDialog(this, "Bạn chưa thanh toán hóa đơn");
                        return;
                    } else if (thanhToan == JOptionPane.CANCEL_OPTION) {
                        JOptionPane.showMessageDialog(this, "Bạn chưa thanh toán hóa đơn");
                        return;
                    } else {
                        try {
                            // TODO add your handling code here:
                            txtPrint.print();
                        } catch (PrinterException ex) {
                            Logger.getLogger(JDialogThanhToan.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        thanhToan();
                    }
                }
            }
        } else if (!(cbTienMat.isSelected()) && cbChuyenKhoan.isSelected()) {
            //check tiền chuyển khoản:
            if (thanhToanUtil.checkBigDecimal(txtChuyenKhoan.getText())) {
                //check tìeen đủ chưa:
                if (tienTraLai.compareTo(new BigDecimal(0)) < 0) {
                    JOptionPane.showMessageDialog(this, "Chưa đủ tiền!");
                } else {
                    checkInHoaDon();
                    int thanhToan = JOptionPane.showConfirmDialog(this, "Bạn có muốn in hóa đơn hay không!");
                    if (thanhToan == JOptionPane.NO_OPTION) {
                        thanhToan();
                        return;
                    } else if (thanhToan == JOptionPane.CLOSED_OPTION) {
                        JOptionPane.showMessageDialog(this, "Bạn chưa thanh toán hóa đơn");
                        return;
                    } else if (thanhToan == JOptionPane.CANCEL_OPTION) {
                        JOptionPane.showMessageDialog(this, "Bạn chưa thanh toán hóa đơn");
                        return;
                    } else {
                        try {
                            // TODO add your handling code here:
                            txtPrint.print();
                        } catch (PrinterException ex) {
                            Logger.getLogger(JDialogThanhToan.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        thanhToan();
                    }
                }
            }
        } else if (cbChuyenKhoan.isSelected() && cbTienMat.isSelected()) {
            //check tiền chuyển khoản và tiền mặt:
            if (thanhToanUtil.checkBigDecimal(txtChuyenKhoan.getText()) && thanhToanUtil.checkBigDecimal(txtTiennMat.getText())) {
                //check tìeen đủ chưa:
                if (tienTraLai.compareTo(new BigDecimal(0)) < 0) {
                    JOptionPane.showMessageDialog(this, "Chưa đủ tiền!");
                } else {
                    checkInHoaDon();
                    int thanhToan = JOptionPane.showConfirmDialog(this, "Bạn có muốn in hóa đơn hay không!");
                    if (thanhToan == JOptionPane.NO_OPTION) {
                        thanhToan();
                        return;
                    } else if (thanhToan == JOptionPane.CLOSED_OPTION) {
                        JOptionPane.showMessageDialog(this, "Bạn chưa thanh toán hóa đơn");
                        return;
                    } else if (thanhToan == JOptionPane.CANCEL_OPTION) {
                        JOptionPane.showMessageDialog(this, "Bạn chưa thanh toán hóa đơn");
                        return;
                    } else {
                        try {
                            // TODO add your handling code here:
                            txtPrint.print();
                        } catch (PrinterException ex) {
                            Logger.getLogger(JDialogThanhToan.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        thanhToan();
                    }
                }
            }
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void cbChuyenKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChuyenKhoanActionPerformed
        if (tongTienTT.compareTo(new BigDecimal(0)) < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng kiểm tra lại tiền được giảm!");
        } else {
            if (!cbTienMat.isSelected()) {
                txtTiennMat.setText("0");
                txtTiennMat.setEditable(false);
            }
            txtChuyenKhoan.setEditable(true);
        }
    }//GEN-LAST:event_cbChuyenKhoanActionPerformed

    private void cbTienMatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTienMatActionPerformed
        if (tongTienTT.compareTo(new BigDecimal(0)) < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng kiểm tra lại tiền được giảm!");
        } else {
            txtTiennMat.setEditable(true);
            if (!cbChuyenKhoan.isSelected()) {
                txtChuyenKhoan.setText("0");
                txtChuyenKhoan.setEditable(false);
            }
        }
    }//GEN-LAST:event_cbTienMatActionPerformed

    private void txtTiennMatCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTiennMatCaretUpdate
        try {
            tienMat = new BigDecimal(txtTiennMat.getText());
            tienCK = new BigDecimal(txtChuyenKhoan.getText());
            tienTraLai = thanhToanUtil.fillTienThua(tienMat, tienCK, tongTienTT);
            txtTienThua.setText(df.format(tienTraLai));
        } catch (Exception e) {
            //  e.printStackTrace();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtTiennMatCaretUpdate

    private void txtChuyenKhoanCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtChuyenKhoanCaretUpdate
        try {
            tienMat = new BigDecimal(txtTiennMat.getText());
            tienCK = new BigDecimal(txtChuyenKhoan.getText());
            tienTraLai = thanhToanUtil.fillTienThua(tienMat, tienCK, tongTienTT);
            txtTienThua.setText(df.format(tienTraLai));
        } catch (Exception e) {
            //e.printStackTrace();
        }      // TODO add your handling code here:
    }//GEN-LAST:event_txtChuyenKhoanCaretUpdate

    private void txtChuyenKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtChuyenKhoanMouseClicked
        if (!cbChuyenKhoan.isSelected()) {
            txtChuyenKhoan.setEditable(false);
        }
    }//GEN-LAST:event_txtChuyenKhoanMouseClicked

    private void txtTienThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienThanhToanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienThanhToanActionPerformed

    private void txtSdtKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSdtKHActionPerformed
        if (checkValidatesdt(txtSdtKH.getText())) {
            if (khachHangService.getOneBySdt(txtSdtKH.getText()) != null) {
                khachHang = khachHangService.getOneBySdt(txtSdtKH.getText());
                txtTenKhachHang.setText(khachHang.getTen());

                txtPhanTramTheoRank.setText(String.valueOf(khachHang.getRankKH().getKhuyenMaiRank()));
                lbRank.setText(khachHang.getRankKH().getTenRank());
            } else {
                txtTenKhachHang.setText("");
                int check = JOptionPane.showConfirmDialog(this, "Thêm Khách hàng vào hệ thống");
                if (check == 0) {
                    KhachHang khachHang = new KhachHang();
                    khachHang.setSdt(txtSdtKH.getText());
                    khachHang.setTen("");
                    NhanVien nhanVien = new NhanVienService().getOne(hoaDon.getNhanVien().getMa());
                    JDialogThemKH jDialogThemKH = new JDialogThemKH(null, rootPaneCheckingEnabled, khachHang);
                    jDialogThemKH.setVisible(true);
                }
            }
        }
    }//GEN-LAST:event_txtSdtKHActionPerformed

    private void btnThemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKHActionPerformed
        KhachHang khachHang = new KhachHang();
        khachHang.setSdt(txtSdtKH.getText());
        khachHang.setTen("");
        NhanVien nhanVien = new NhanVienService().getOne(hoaDon.getNhanVien().getMa());
        JDialogThemKH jDialogThemKH = new JDialogThemKH(null, rootPaneCheckingEnabled, khachHang);
        jDialogThemKH.setVisible(true);
    }//GEN-LAST:event_btnThemKHActionPerformed

    private void txtTiennMatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTiennMatActionPerformed
        if (!cbTienMat.isSelected()) {
            txtTiennMat.setEditable(false);
            txtTiennMat.setText("0");
        }
    }//GEN-LAST:event_txtTiennMatActionPerformed

    private void txtChuyenKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChuyenKhoanActionPerformed
        if (!cbChuyenKhoan.isSelected()) {
            txtChuyenKhoan.setEditable(false);
            txtChuyenKhoan.setText("0");
        }
    }//GEN-LAST:event_txtChuyenKhoanActionPerformed

    private void txtTienDuocGiamCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienDuocGiamCaretUpdate
        try {
            if (txtTienDuocGiam.getText().isEmpty()) {
                tienGiamGia = new BigDecimal(0);
                tongTienTT = hdCustom.getTongTien().add(new BigDecimal(thue));
                txtTienThanhToan.setText(df.format(tongTienTT));
            } else {
                if (thanhToanUtil.checkBigDecimal(txtTienDuocGiam.getText())) {
                    tienGiamGia = new BigDecimal(txtTienDuocGiam.getText());
                    tongTienTT = hdCustom.getTongTien().add(new BigDecimal(thue)).subtract(tienGiamGia);
                    txtTienThanhToan.setText(df.format(tongTienTT));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtTienDuocGiamCaretUpdate
    private boolean checkValidatesdt(String sdt) {
        boolean isCheckSdt = false;
        if (!sdt.matches("(0)((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))[0-9]{7}")) {
            isCheckSdt = false;
            JOptionPane.showMessageDialog(null, "sdt không đúng định dạng");
        } else {
            isCheckSdt = true;
        }
        return isCheckSdt;
    }
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnInHoaDonMau;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThemKH;
    private javax.swing.JCheckBox cbChuyenKhoan;
    private javax.swing.JCheckBox cbTienMat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbRank;
    private javax.swing.JTable tbHDCT;
    private javax.swing.JTextField txtBan;
    private javax.swing.JTextField txtChuyenKhoan;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtPhanTramTheoRank;
    private javax.swing.JTextArea txtPrint;
    private javax.swing.JTextField txtSdtKH;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTienDuocGiam;
    private javax.swing.JTextField txtTienThanhToan;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTiennMat;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
