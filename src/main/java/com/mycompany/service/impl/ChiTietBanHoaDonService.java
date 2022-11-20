/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.impl;

import com.mycompany.domainModel.Ban;
import com.mycompany.domainModel.ChiTietBanHoaDon;
import com.mycompany.domainModel.HoaDon;
import com.mycompany.repository.IChiTietBanHoaDonRepository;
import com.mycompany.repository.impl.ChiTietBanHoaDonRepository;
import com.mycompany.service.IChiTietBanHoaDonService;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ChiTietBanHoaDonService implements IChiTietBanHoaDonService<ChiTietBanHoaDon, String, HoaDon, Ban> {

    private IChiTietBanHoaDonRepository ctbhdr = new ChiTietBanHoaDonRepository();

    @Override
    public List<ChiTietBanHoaDon> getAll() {
        return ctbhdr.getAll();
    }

    @Override
    public String add(ChiTietBanHoaDon chiTietBanHoaDon) {
        if ((Boolean) ctbhdr.add(chiTietBanHoaDon)) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String update(ChiTietBanHoaDon chiTietBanHoaDon, HoaDon hoaDon, Ban ban) {
        if ((Boolean) ctbhdr.update(chiTietBanHoaDon, hoaDon, ban)) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public List<ChiTietBanHoaDon> getByHoaDon(HoaDon hoaDon) {
        return ctbhdr.getByHoaDon(hoaDon);
    }

    public static void main(String[] args) {
        HoaDon hd = new HoaDon();
        hd.setId("EE38F876-447A-41E3-8CCA-B0E37A508E23");
        Ban ban = new Ban();
        ban.setId("BCD9EBC1-B3B5-480D-9A7B-1B6C2FEAD603");
        ChiTietBanHoaDon chiTietBanHoaDon = new ChiTietBanHoaDon(null, hd, ban);
        String add = new ChiTietBanHoaDonService().add(chiTietBanHoaDon);
        System.out.println(add);
    }

}
