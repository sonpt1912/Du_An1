/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.impl;

import com.mycompany.domainModel.HoaDon;
import com.mycompany.domainModel.MonAn;
import com.mycompany.repository.IThongKeRepository;
import com.mycompany.repository.impl.ThongKeRepository;
import com.mycompany.service.IThongKeService;
import java.util.List;

/**
 *
 * @author PTS
 */
public class ThongKeService implements IThongKeService {

    private ThongKeRepository thongKeRepository = new ThongKeRepository();

    @Override
    public List<HoaDon> getAllHoaDon(int trangThai) {
        return thongKeRepository.getAllHoaDon(trangThai);
    }

    @Override
    public List<MonAn> getAllSanPham() {
        return thongKeRepository.getAllSanPham();
    }

    @Override
    public List<HoaDon> getAllHoaDonTrangThai(int trangThai) {
        return thongKeRepository.getAllHoaDonTrangThai(trangThai);
    }

}
