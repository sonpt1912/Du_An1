/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.impl;

import com.mycompany.domainModel.ChiTietComBo;
import com.mycompany.domainModel.ComBo;
import com.mycompany.domainModel.MonAn;
import com.mycompany.domainModel.NhanVien;
import com.mycompany.repository.impl.ChiTietComBoRepository;
import com.mycompany.repository.impl.ComBoRepository;
import com.mycompany.repository.impl.MonAnRepository;
import com.mycompany.service.IComBoService;
import java.math.BigDecimal;
import java.util.List;
import com.mycompany.service.ICommonService;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Admin
 */
public class ComBoService implements ICommonService<ComBo, String>, IComBoService {

    private final com.mycompany.repository.ICommonRepository cbr = new ComBoRepository();
    private ComBoRepository cb = new ComBoRepository();
    private MonAnRepository mar = new MonAnRepository();
    private ChiTietComBoRepository ctbr = new ChiTietComBoRepository();

    @Override
    public List<ComBo> getAll() {
        return cbr.getAll();
    }

    @Override
    public ComBo getOne(String ma) {
        return (ComBo) cbr.getOne(ma);
    }

    @Override
    public String add(ComBo kh) {
        if ((Boolean) cbr.add(kh)) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    //
    public List<ComBo> getAllTen(String ten) {
        return cb.getAllTheoTen(ten);
    }

    @Override
    public String update(ComBo kh, String ma) {
        if ((Boolean) cbr.update(kh, ma)) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public String remove(String ma) {
        if ((Boolean) cbr.remove(ma)) {
            return "Xoá thành công";
        } else {
            return "Xoá thất bại";
        }
    }

    public static void main(String[] args) {
        List<ComBo> test = new ComBoService().getAll();
        for (ComBo comBo : test) {
            System.out.println(comBo.toString());
        }
    }

    @Override
    public List<ComBo> getAllByTrangThai(int trangThai) {
        return cb.getAllByTrangThai(trangThai);
    }

    public String randomMaHoaDon(List<ComBo> listCB) {
        String maCB = "CB";
        int sz = listCB.size();
        maCB = maCB + (sz + 1);
        for (ComBo khachHang : listCB) {
            if (khachHang.getMaCB().equals(maCB)) {
                maCB = "CB" + (sz + 1 + 1);
            }
        }
        return maCB;
    }

    @Override
    public void checkTrangThaiMonAn(ComBo ComBo) {
        List<ChiTietComBo> listChiTiet = ctbr.getAllByComBo(ComBo);
        List<MonAn> listMonAn = new ArrayList<>();
        for (ChiTietComBo ct : listChiTiet) {
            listMonAn = mar.getMonAnByComBo(ct.getMonAn());
            for (MonAn ma : mar.getAllMonAnByTrangThai(1)) {
                for (MonAn a : listMonAn) {
                    if (ma.getId().equalsIgnoreCase(a.getId())) {
                        remove(ComBo.getMaCB());
                    }
                }
            }
        }
    }

    @Override
    public List<ComBo> getAlls() {
        return cb.getAlls();
    }

}
