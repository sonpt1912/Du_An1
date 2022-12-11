/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.impl;

import com.mycompany.domainModel.RankKhachHang;
import com.mycompany.repository.impl.RankRepositoryImpl;
import com.mycompany.util.ThongBao;
import java.util.List;

/**
 *
 * @author PTS
 */
public class RankServiceImpl implements IRankService {

    private final RankRepositoryImpl rankRepository = new RankRepositoryImpl();
    private ThongBao thongBao = new ThongBao();

    @Override
    public List<RankKhachHang> getAll() {
        return rankRepository.getAll();
    }

    @Override
    public RankKhachHang getOne(String ma) {
        return rankRepository.getOne(ma);
    }

    @Override
    public RankKhachHang getOneById(String id) {
        return rankRepository.getOneById(id);
    }

    @Override
    public String add(RankKhachHang kh) {
        return thongBao.thongBaoADD(rankRepository.add(kh));
    }

    @Override
    public String update(RankKhachHang kh, int ma) {
        return thongBao.thongBaoUPDATE(rankRepository.update(kh, ma));
    }

    @Override
    public String remove(int ma) {
        return thongBao.thongBaoDELETE(rankRepository.remove(ma));
    }

    @Override
    public String rank(String idKH) {
        return rankRepository.Rank(idKH);
    }

}
