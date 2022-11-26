/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.domainModel.HoaDon;
import com.mycompany.domainModel.MonAn;
import java.util.List;

/**
 *
 * @author PTS
 */
public interface IThongKeService {

    List<HoaDon> getAllHoaDon(int t);

    List<MonAn> getAllSanPham();

    List<HoaDon> getAllHoaDonTrangThai(int trangThai);

    List<MonAn> getAllSanPhamTrangThai(int trangThai);

    List<HoaDon> getAllDay();

    List<HoaDon> getAllMonth();

    long getCountAllDay();

    long getCountAllMonth();
}
