/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.impl;

import com.mycompany.customModel.MonAnCoKM;
import com.mycompany.domainModel.DanhMuc;
import com.mycompany.domainModel.KhuyenMai;
import com.mycompany.domainModel.Loai;
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
        return monAnRepo.getAll();
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

    public List<MonAn> getMonAnTheoTen(String ten) {
        return monAnRepo.searchMonAnTheoTen(ten);
    }

//    public List<MonAn> getMonAnLoai(Loai loai) {
//        return monAnRepo.getMonAnByLoai(loai);
//    }
    public List<MonAn> getMonAnTheoTenLoai(String ten, DanhMuc danhMuc) {
        return monAnRepo.searchMonAnTheoTenLoai(ten, danhMuc);
    }

    public static void main(String[] args) {
        List<MonAnCoKM> list = new MonAnService().getMonAnCoKM();
        for (MonAnCoKM monAn : list) {
            System.out.println(list.toString());
        }
    }

    public String maTuDong() {
        List<MonAn> listMon = new MonAnService().getAllMonAn();
        int index = listMon.size();
        String maMA = "MA";
        if (index <= 0) {
            return "MA1";
        } else {
//            String viTri = "";
//            String maMon = "";
//            viTri += listMon.get(index - 1).getMaMonAn();
//            int soMa = Integer.valueOf(viTri.substring(2)) + 1;
//            maMon = "MA" + soMa;
//            return maMon;

            maMA = maMA + index;
            if (monAnRepo.getOne(maMA) != null) {
                maMA = "MA" + (index + 1);
            }
        }
        return maMA;
    }

    private List<MonAn> getAllMonAn() {
        return monAnRepo.getAllMonAn();
    }

    public List<MonAn> getMonAnByDanhMuc(DanhMuc danhMuc) {
        return monAnRepo.getMonAnByDanhMuc(danhMuc);
    }

    @Override
    public List<MonAnCoKM> getMonAnCoKM() {
        return monAnRepo.getMonAnCoKM();
    }

    @Override
    public List<MonAn> getMonAnLeftJoinKMCT() {
        return monAnRepo.getMonAnLeftJoinKMCT();
    }

}
