/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.util;

import com.mycompany.domainModel.HoaDon;
import com.mycompany.domainModel.HoaDonChiTiet;
import com.mycompany.service.impl.HoaDonChiTietService;
import com.mycompany.service.impl.HoaDonService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Admin
 */
public class HoaDonUtil {
    
    public String zenMa() {
        String ma = "HD";
        Random rd = new Random();
        
        for (int i = 0; i < 6; i++) {
            ma += rd.nextInt(10);
        }
        //dsad
        return ma;
    }
    
    public String zenMaThuyDuong(List<HoaDon> listHD) {
        String ma = "HD";
        Random rd = new Random();
        return ma + String.valueOf(listHD.size() + 1);
    }
    
    public String layNgay() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String namThangNgay = year + "-" + month + "-" + day;
        return namThangNgay;
    }
    
    public BigDecimal tinhTongTienHD(List<HoaDonChiTiet> listHDCT) {
        BigDecimal tienMonAn = BigDecimal.valueOf(0);
        BigDecimal tienCombo = BigDecimal.valueOf(0);;
        for (HoaDonChiTiet hdct : listHDCT) {
//            if (hdct.getDonGiaMonAn() != null) {
//                tienMonAn.add(hdct.getDonGiaMonAn().multiply(BigDecimal.valueOf(Double.valueOf(String.valueOf(hdct.getSoLuongMonAn())))));
//            }
//            if (hdct.getDonGiaCombo() != null) {
//                tienCombo.add(hdct.getDonGiaCombo().multiply(BigDecimal.valueOf(Double.valueOf(String.valueOf(hdct.getSoLuongCombo())))));
//            }
//            tienMonAn.add(hdct.getDonGiaMonAn().multiply(BigDecimal.valueOf(Double.valueOf(String.valueOf(hdct.getSoLuongMonAn())))));
//            tienCombo.add(hdct.getDonGiaCombo().multiply(BigDecimal.valueOf(Double.valueOf(String.valueOf(hdct.getSoLuongCombo())))));
            //BigDecimal soLuongMA = new BigDecimal(BigInteger.ZERO, hdct.getSoLuongMonAn());
            BigDecimal soLuongMA = new BigDecimal(hdct.getSoLuongMonAn());
            BigDecimal soLuongCB = new BigDecimal(hdct.getSoLuongCombo());
            tienMonAn = tienMonAn.add(hdct.getDonGiaMonAn().multiply(soLuongMA));
            tienCombo = tienCombo.add(hdct.getDonGiaCombo().multiply(soLuongCB));
        }
        return tienCombo.add(tienMonAn);
    }
    
    public static void main(String[] args) {
        HoaDon hoaDon = new HoaDonService().getOne("HD2");
        List<HoaDonChiTiet> list = new HoaDonChiTietService().getHDCTByHD(hoaDon);
        System.out.println(new HoaDonUtil().tinhTongTienHD(list) + " fghjkl;");
    }
}
