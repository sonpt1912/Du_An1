/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.util;

import com.mycompany.domainModel.KhuyenMai;
import com.mycompany.domainModel.MonAn;
import java.math.BigDecimal;

/**
 *
 * @author Duongntt
 */
public class KhuyenMaiUtil {

    public BigDecimal tinhTienMonAnSauKM(KhuyenMai khuyenMai, MonAn monAn) {
        double donGiaSauKm = 0;
        if (khuyenMai.getLoaiKhuyenMai().equals("Phần trăm")) {
            double tienGiam = 0;
            double phanTram = Double.valueOf(String.valueOf(khuyenMai.getGiaTriKM())) / 100;
            tienGiam = Double.valueOf(String.valueOf(monAn.getDonGia())) * phanTram;
            donGiaSauKm = Double.valueOf(String.valueOf(monAn.getDonGia())) - tienGiam;
        } else {
            donGiaSauKm = Double.valueOf(String.valueOf(monAn.getDonGia())) - Double.valueOf(String.valueOf(khuyenMai.getGiaTriKM()));
        }
        return BigDecimal.valueOf(donGiaSauKm);
    }
}
