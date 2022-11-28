/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.util;

import com.mycompany.domainModel.KhuyenMai;
import com.mycompany.domainModel.MonAn;
import com.mycompany.service.impl.KhuyenMaiService;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Duongntt
 */
public class KhuyenMaiUtil {

    private KhuyenMaiService khuyenMaiService = new KhuyenMaiService();
    private java.util.Date today = new java.util.Date();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public BigDecimal tinhTienMonAnSauKM(KhuyenMai khuyenMai, MonAn monAn) {
        BigDecimal donGiaSauKm = null;
        if (khuyenMai.getLoaiKhuyenMai().equalsIgnoreCase("Phần trăm")) {
            BigDecimal tienGiam = (monAn.getDonGia()).multiply((khuyenMai.getGiaTriKM().divide(BigDecimal.valueOf(100))));
            donGiaSauKm = (monAn.getDonGia()).subtract(tienGiam);
        } else {
            donGiaSauKm = (monAn.getDonGia()).subtract(khuyenMai.getGiaTriKM());
        }
        return donGiaSauKm;
    }

    public String zenMaKM(List<KhuyenMai> listKM) {
        String maKM = "KM";
        int sz = listKM.size();
        return maKM + (sz + 1);
    }

    public boolean checkValidateKM(KhuyenMai khuyenMai) {
        boolean check = false;
        String ngayBDString = String.valueOf(khuyenMai.getThoiGianBD());
        String ngayKTString = String.valueOf(khuyenMai.getThoiGianKT());
        String todayString = String.valueOf(dateFormat.format(today));
        if (ngayBDString.compareTo(todayString) < 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng không chọn thời gian bắt đầu là quá khứ!");
            check = false;
        } else if (ngayKTString.compareTo(ngayBDString) < 0) {
            JOptionPane.showMessageDialog(null, "Thời gian kết thúc KM không được sớm hơn thời gian bắt đầu!");
            check = false;
        } else if (Double.valueOf(String.valueOf(khuyenMai.getGiaTriKM())) <= Double.valueOf(0)) {
            JOptionPane.showMessageDialog(null, "Giá trị KM ko hợp lệ!");
            check = false;
        } else if (khuyenMai.getLoaiKhuyenMai().equalsIgnoreCase("Phần trăm") && Double.valueOf(String.valueOf(khuyenMai.getGiaTriKM())) > Double.valueOf(100)) {
            JOptionPane.showMessageDialog(null, "Giá trị KM ko hợp lệ!");
            check = false;
        } else if (khuyenMai.getTenKhuyenMai().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "tên KM không được để trống");
            check = false;
        } else {
            check = true;
        }
        return check;
    }

    public int trangThaiKM(Date tgianBD, Date tgianKT) {
        int trangThai = 0;
        String ngayBDString = String.valueOf(tgianBD);
        String ngayKTString = String.valueOf(tgianKT);
        String todayString = String.valueOf(dateFormat.format(today));
        if (ngayBDString.compareTo(todayString) == 0 && ngayKTString.compareTo(todayString) >= 0) {
            //trạng thái = 0: áp dụng
            trangThai = 0;
        } else if (ngayBDString.compareTo(todayString) > 0 && ngayKTString.compareTo(todayString) > 0) {
            //trạng thái == 2: chờ áp dụng
            trangThai = 2;
        } else {
            //trạng thái = 1: ngừg áp dụng
            trangThai = 1;
        }
        return trangThai;
    }

//    public static void main(String[] args) {
//        Date date = Date.valueOf("2022-11-27");
//        String dateString = "2022-11-29";
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        java.util.Date today = new java.util.Date();
//        String todayString = String.valueOf(dateFormat.format(today));
//        System.out.println(dateString.compareTo(todayString));
//    }
}
