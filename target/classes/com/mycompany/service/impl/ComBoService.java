/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.impl;

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
import java.util.Random;

/**
 *
 * @author Admin
 */
public class ComBoService implements ICommonService<ComBo, String>, IComBoService {

    private final com.mycompany.repository.ICommonRepository cbr = new ComBoRepository();
    private ComBoRepository cb = new ComBoRepository();
    private MonAnRepository mar = new MonAnRepository();

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

    public String randomMaHoaDon() {
        Random rd = new Random();
        int ran = 0;
        String ma = "";
        while (true) {
            ran = rd.nextInt(99999) + 1;
            ma = "CB" + ran;
            if (new ComBoService().getOne(ma) == null) {
                break;
            }
        }
        return ma;
    }

    @Override
    public void checkTrangThaiMonAn(ComBo ComBo) {
        List<MonAn> list = mar.getMonAnByComBo(ComBo);
        List<MonAn> list1 = mar.getAllMonAnByTrangThai(1);
        ComBo.setTrangThai(1);
        for (MonAn m : list1) {
            for (MonAn s : list) {
                if (m.getId().equalsIgnoreCase(s.getId())) {
                    update(ComBo, ComBo.getMaCB());
                }
            }
        }
    }

}
