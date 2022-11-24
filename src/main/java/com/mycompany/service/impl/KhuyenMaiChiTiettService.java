/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.impl;

import com.mycompany.domainModel.KhuyenMaiChiTiet;
import com.mycompany.repository.ICommonRepository;
import com.mycompany.repository.impl.KhuyenMaiChiTietRepository;
import com.mycompany.service.ICommonService;
import com.mycompany.util.ThongBao;
import java.util.List;

/**
 *
 * @author Duongntt
 */
public class KhuyenMaiChiTiettService implements ICommonService<KhuyenMaiChiTiet, String> {

    private ICommonRepository kmctRepo = new KhuyenMaiChiTietRepository();
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
    public String update(KhuyenMaiChiTiet kh, String ma) {
        return thongBao.thongBaoUPDATE((boolean) kmctRepo.update(kh, ma));
    }

    @Override
    public String remove(String ma) {
        return thongBao.thongBaoDELETE((boolean) kmctRepo.remove(ma));
    }

}