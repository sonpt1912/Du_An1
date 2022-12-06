/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.impl;

import com.mycompany.domainModel.KhachHang;
import com.mycompany.repository.impl.KhachHangRepository;
import java.util.List;
import com.mycompany.repository.ICommonRepository;
import com.mycompany.service.IKhachHangService;

/**
 *
 * @author Admin
 */
public class KhachHangService implements com.mycompany.service.ICommonService<KhachHang, String>, IKhachHangService {

    private final KhachHangRepository khr = new KhachHangRepository();

    @Override
    public List<KhachHang> getAll() {
        return khr.getAll();
    }

    @Override
    public KhachHang getOne(String ma) {
        return (KhachHang) khr.getOne(ma);
    }

    @Override
    public String add(KhachHang kh) {
        if ((Boolean) khr.add(kh)) {
            return "Add thành công";
        } else {
            return "Add thất bại";
        }

    }

    @Override
    public String update(KhachHang kh, String ma) {
        if ((Boolean) khr.update(kh, ma)) {
            return "Update thành công";
        } else {
            return "Update thất bại";
        }
    }

    @Override
    public String remove(String ma) {
        if ((Boolean) khr.remove(ma)) {
            return "Xoá thành công";
        } else {
            return "Xoá thất bại";
        }
    }

    @Override
    public List<KhachHang> searchBySDTOrTen(String txtTimKiem) {
        return khr.searchBySDTOrTen(txtTimKiem);
    }

    @Override
    public KhachHang getOneBySdt(String sdt) {
        return khr.getOneBySdt(sdt);
    }
}
