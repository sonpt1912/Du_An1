/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.repository;

import com.mycompany.domainModel.HoaDon;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Duongntt
 */
public interface IHoaDonRepository {

    List<HoaDon> getHDByTrangThai(int trangThaiHD);

    List<HoaDon> getHDChoByMaBan(int maBan);

    List<HoaDon> getHoaDonsHomNay(java.util.Date today);

    List<HoaDon> getHoaDonsKhoangNgay(java.util.Date today1, java.util.Date today2);
}
