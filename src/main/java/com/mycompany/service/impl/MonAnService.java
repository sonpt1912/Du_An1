/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.impl;

import com.mycompany.domainModel.DanhMuc;
import com.mycompany.domainModel.KhuyenMai;
import com.mycompany.domainModel.KhuyenMaiChiTiet;
import com.mycompany.domainModel.MonAn;
import com.mycompany.repository.ICommonRepository;
import com.mycompany.repository.impl.MonAnRepository;
import com.mycompany.service.IMonAnService;
import com.mycompany.util.ThongBao;
import java.util.List;

/**
 *
 * @author Admin
 */
public class MonAnService implements com.mycompany.service.ICommonService<MonAn, String>, IMonAnService {

    private final ICommonRepository mar = new MonAnRepository();
    private ThongBao thongBao = new ThongBao();
    private MonAnRepository monAnRepo = new MonAnRepository();

    @Override
    public List<MonAn> getAll() {
        return mar.getAll();
    }

    @Override
    public MonAn getOne(String ma) {
        return (MonAn) mar.getOne(ma);
    }

    @Override
    public String add(MonAn kh) {
        return thongBao.thongBaoADD((Boolean) mar.add(kh));
    }

    @Override
    public String update(MonAn kh, String ma) {
        return thongBao.thongBaoUPDATE((Boolean) mar.update(kh, ma));
    }

    @Override
    public String remove(String ma) {
        return thongBao.thongBaoDELETE((Boolean) mar.remove(ma));
    }

    public List<MonAn> getMonAnByKhuyenMai(KhuyenMai khuyenMai) {
        return monAnRepo.getMonAnByKhuyenMai(khuyenMai);
    }

    public List<MonAn> getMonAnByTrangThai(int trangThai) {
        return monAnRepo.getAllMonAnByTrangThai(trangThai);
    }

    public static void main(String[] args) {
    }

    public String maTuDong() {
        List<MonAn> listMon = new MonAnService().getAllMonAn();
        int index = listMon.size();
        if (index <= 0) {
            return "MA1";
        } else {
            String viTri = "";
            String maMon = "";
            viTri += listMon.get(index - 1).getMaMonAn();
            int soMa = Integer.valueOf(viTri.substring(2)) + 1;
            maMon = "MA" + soMa;
            return maMon;
        }
    }

    private List<MonAn> getAllMonAn() {
        return monAnRepo.getAllMonAn();
    }

    public List<MonAn> getMonAnByDanhMuc(DanhMuc danhMuc) {
        return monAnRepo.getMonAnByDanhMuc(danhMuc);
    }

    @Override
    public List<MonAn> getMonAnCoKM() {
        return monAnRepo.getMonAnCoKM();
    }

    @Override
    public List<MonAn> getMonAnLeftJoinKMCT() {
        return monAnRepo.getMonAnLeftJoinKMCT();
    }

}
