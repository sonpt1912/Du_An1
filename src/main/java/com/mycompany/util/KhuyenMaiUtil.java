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
        if (Date.valueOf(dateFormat.format(khuyenMai.getThoiGianBD())).compareTo(today) < 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng không chọn thời gian bắt đầu là quá khứ!");
        } else if (Date.valueOf(dateFormat.format(khuyenMai.getThoiGianKT())).compareTo(khuyenMai.getThoiGianKT()) < 0) {
            JOptionPane.showMessageDialog(null, "Thời gian áp dụng khuyến mãi không hợp lý!");
        } else if (Double.valueOf(String.valueOf(khuyenMai.getGiaTriKM())) <= Double.valueOf(0)) {
            JOptionPane.showMessageDialog(null, "Giá trị KM ko hợp lệ!");
        } else if (khuyenMai.getLoaiKhuyenMai().equalsIgnoreCase("Phần trăm") && Double.valueOf(String.valueOf(khuyenMai.getGiaTriKM())) > Double.valueOf(100)) {
            JOptionPane.showMessageDialog(null, "Giá trị KM ko hợp lệ!");
        } else if (khuyenMai.getTenKhuyenMai().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "tên KM không được để trốn");
        } else {
            check = true;
        }
        return check;

    }
}
