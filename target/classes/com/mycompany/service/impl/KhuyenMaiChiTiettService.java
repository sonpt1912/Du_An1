/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.impl;

import com.mycompany.domainModel.KhuyenMai;
import com.mycompany.domainModel.KhuyenMaiChiTiet;
import com.mycompany.domainModel.MonAn;
import com.mycompany.repository.impl.KhuyenMaiChiTietRepository;
import com.mycompany.service.ICommonService;
import com.mycompany.service.IKMCTService;
import com.mycompany.util.ThongBao;
import java.util.List;

/**
 *
 * @author Duongntt
 */
public class KhuyenMaiChiTiettService implements ICommonService<KhuyenMaiChiTiet, String>, IKMCTService {

    private KhuyenMaiChiTietRepository kmctRepo = new KhuyenMaiChiTietRepository();
    private ThongBao thongBao = new ThongBao();

    @Override
    public List<KhuyenMaiChiTiet> getAll() {
        return kmctRepo.getAll();
    }

    @Override
    public KhuyenMaiChiTiet getOne(String ma) {
        return (KhuyenMaiChiTiet) kmctRepo.getOne(ma);
    }

    @Override
    public String add(KhuyenMaiChiTiet kh) {
        return thongBao.thongBaoADD((boolean) kmctRepo.add(kh));
    }

    @Override
    public String update(KhuyenMaiChiTiet kh, String id) {
        return thongBao.thongBaoUPDATE((boolean) kmctRepo.update(kh, id));
    }

    @Override
    public String remove(String ma) {
        return thongBao.thongBaoDELETE((boolean) kmctRepo.remove(ma));
    }

    @Override
    public List<KhuyenMaiChiTiet> getKMCTByMaAndKM(MonAn monAn, KhuyenMai khuyenMai) {
        return kmctRepo.getKMCTByMaAndKM(monAn, khuyenMai);
    }

    @Override
    public List<KhuyenMaiChiTiet> getKMCTsByKM(KhuyenMai khuyenMai) {
        return kmctRepo.getKMCTsByKM(khuyenMai);
    }

    @Override
    public List<KhuyenMaiChiTiet> getKMCTsByMA(MonAn monAn) {
        return kmctRepo.getKMCTsByMA(monAn);
    }

}
