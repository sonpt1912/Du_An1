/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.impl;

import com.mycompany.customModel.SanPhamRepose;
import com.mycompany.domainModel.HoaDon;
import com.mycompany.domainModel.MonAn;
import com.mycompany.repository.IThongKeRepository;
import com.mycompany.repository.impl.CalledStoreProceducreThongKe;
import com.mycompany.repository.impl.ThongKeRepository;
import com.mycompany.service.IThongKeService;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author PTS
 */
public class ThongKeService implements IThongKeService {

    private CalledStoreProceducreThongKe calledStoreProceducre = new CalledStoreProceducreThongKe();

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

    @Override
    public List<MonAn> getAllSanPhamTrangThai(int trangThai) {
        return thongKeRepository.getAllSanPhamTrangThai(trangThai);
    }

    @Override
    public long getCountAllDay() {
        return thongKeRepository.getCountAllDay();
    }

    @Override
    public long getCountAllMonth() {
        return thongKeRepository.getCountAllMonth();
    }

    @Override
    public long getCountAllWeek() {
        return thongKeRepository.getCountAllWeek();
    }

    @Override
    public long getCountAllYear() {
        return thongKeRepository.getCountAllYear();
    }

    @Override
    public long getHoaDonDaTTDAY() {
        return thongKeRepository.getHoaDonDaTTDAY();
    }

    @Override
    public long getHoaDonDaTTWEEK() {
        return thongKeRepository.getHoaDonDaTTWEEK();
    }

    @Override
    public long getHoaDonDaTTMONTH() {
        return thongKeRepository.getHoaDonDaTTMONTH();
    }

    @Override
    public long getHoaDonDaTTYEAR() {
        return thongKeRepository.getHoaDonDaTTYEAR();
    }

    @Override
    public long getHoaDonHuyDAY() {
        return thongKeRepository.getHoaDonHuyDAY();
    }

    @Override
    public long getHoaDonHuyWEEK() {
        return thongKeRepository.getHoaDonHuyWEEK();
    }

    @Override
    public long getHoaDonHuyMONTH() {
        return thongKeRepository.getHoaDonHuyMONTH();
    }

    @Override
    public long getHoaDonHuyYEAR() {
        return thongKeRepository.getHoaDonHuyYEAR();
    }

    @Override
    public BigDecimal getDoanhThuDAY() {
        return thongKeRepository.getDoanhThuDAY();
    }

    @Override
    public BigDecimal getDoanhThuWEEK() {
        return thongKeRepository.getDoanhThuWEEK();
    }

    @Override
    public BigDecimal getDoanhThuMONTH() {
        return thongKeRepository.getDoanhThuMONTH();
    }

    @Override
    public BigDecimal getDoanhThuYEAR() {
        return thongKeRepository.getDoanhThuYEAR();
    }

    public List<SanPhamRepose> getAllSanPham(Date ngaBatDau, Date ngayKetThuc) {
        List<SanPhamRepose> list = calledStoreProceducre.calledStore(ngaBatDau, ngayKetThuc);
        list.sort((o1, o2) -> {
            if (o1.getMa().equals(o2.getMa())) {
                return o1.getMa().compareTo(o2.getMa());
            } else {
                return o2.getSoLuong() - o1.getSoLuong();
            }
        });

        return list;
    }

    @Override
    public BigDecimal getDoanhThuThang1() {
        return thongKeRepository.getDoanhThuThang1();
    }

    @Override
    public BigDecimal getDoanhThuThang2() {
        return thongKeRepository.getDoanhThuThang2();
    }

    @Override
    public BigDecimal getDoanhThuThang3() {
        return thongKeRepository.getDoanhThuThang3();
    }

    @Override
    public BigDecimal getDoanhThuThang4() {
        return thongKeRepository.getDoanhThuThang4();
    }

    @Override
    public BigDecimal getDoanhThuThang5() {
        return thongKeRepository.getDoanhThuThang5();
    }

    @Override
    public BigDecimal getDoanhThuThang6() {
        return thongKeRepository.getDoanhThuThang6();
    }

    @Override
    public BigDecimal getDoanhThuThang7() {
        return thongKeRepository.getDoanhThuThang7();
    }

    @Override
    public BigDecimal getDoanhThuThang8() {
        return thongKeRepository.getDoanhThuThang8();
    }

    @Override
    public BigDecimal getDoanhThuThang9() {
        return thongKeRepository.getDoanhThuThang9();
    }

    @Override
    public BigDecimal getDoanhThuThang10() {
        return thongKeRepository.getDoanhThuThang10();
    }

    @Override
    public BigDecimal getDoanhThuThang11() {
        return thongKeRepository.getDoanhThuThang11();
    }

    @Override
    public BigDecimal getDoanhThuThang12() {
        return thongKeRepository.getDoanhThuThang12();
    }

}
