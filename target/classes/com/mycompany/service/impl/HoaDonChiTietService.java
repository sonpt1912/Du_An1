/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.impl;

import com.mycompany.domainModel.ComBo;
import com.mycompany.domainModel.HoaDon;
import com.mycompany.domainModel.HoaDonChiTiet;
import com.mycompany.domainModel.MonAn;
import com.mycompany.repository.impl.HoaDonChiTietRepository;
import java.util.List;
import com.mycompany.service.ICommonService;
import java.math.BigDecimal;
import com.mycompany.service.IHoaDonChiTietService;

/**
 *
 * @author Admin
 */
public class HoaDonChiTietService implements IHoaDonChiTietService<HoaDonChiTiet, String, HoaDon> {

    private final com.mycompany.repository.IHoaDonChiTietRepository hdctr = new HoaDonChiTietRepository();
    private HoaDonChiTietRepository hdctRep = new HoaDonChiTietRepository();

    @Override
    public List<HoaDonChiTiet> getAll() {
        return hdctr.getAll();
    }

    @Override
    public HoaDonChiTiet getOne(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String add(HoaDonChiTiet kh) {
        if ((Boolean) hdctr.add(kh)) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String update(HoaDonChiTiet kh, HoaDon ma) {
        if ((Boolean) hdctr.update(kh, ma)) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public String remove(HoaDon ma) {
        if ((Boolean) hdctr.remove(ma)) {
            return "Xoá thành công";
        } else {
            return "Xoá thất bại";
        }
    }

    public BigDecimal tongTienHD(List<HoaDonChiTiet> listHDCT) {
        return hdctRep.tinhTongTien(listHDCT);
    }

    @Override
    public List<HoaDonChiTiet> getHDCTByHD(HoaDon hoaDon) {
        return hdctRep.getHDCTByHD(hoaDon);
    }

    @Override
    public HoaDonChiTiet getOneHDCTByMAHD(HoaDon hd, MonAn monAn) {
        return hdctRep.getOneHDCTByMAHD(hd, monAn);
    }

    @Override
    public HoaDonChiTiet getOneHDCTByCombo(HoaDon hd, ComBo combo) {
        return hdctRep.getOneCombo(hd, combo);
    }

    @Override
    public String updateHDCTById(HoaDonChiTiet kh, String idHDCT) {
        if ((Boolean) hdctr.updateHDCTById(kh, idHDCT)) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    public static void main(String[] args) {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setId("602EA574-B3FC-4991-8243-F5BCD5FEF480");
        MonAn monAn = new MonAn();
        monAn.setId("735E2EAE-92A5-458F-8CB7-8211402B3361");
        HoaDonChiTiet hdct = new HoaDonChiTietService().getOneHDCTByMAHD(hoaDon, monAn);
        System.out.println(hdct.toString());
    }

    @Override
    public String updateSoLuongCombo(HoaDonChiTiet HDCT, HoaDon hd, ComBo combo) {
        if ((Boolean) hdctr.updateSoLuongCombo(HDCT, hd, combo)) {
            return "Update thành công";
        } else {
            return "Update thất bại";
        }
    }

    @Override
    public String updateSoLuongMonAn(HoaDonChiTiet HDCT, HoaDon hd, MonAn MonAn) {
        if ((Boolean) hdctr.updateSoLuongMonAn(HDCT, hd, MonAn)) {
            return "Update thành công";
        } else {
            return "Update thất bại";
        }
    }

    @Override
    public String removeMonAn(HoaDon hd, MonAn MonAn) {
        if ((Boolean) hdctr.removeMonAn(hd, MonAn)) {
            return "Xoá thành công";
        } else {
            return "Xoá thất bại";
        }
    }

    @Override
    public String removeCombo(HoaDon hd, ComBo Combo) {
        if ((Boolean) hdctr.removeCombo(hd, Combo)) {
            return "Xoá thành công";
        } else {
            return "Xoá thất bại";
        }
    }
}
