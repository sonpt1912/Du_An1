/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.domainModel.HoaDon;
import java.util.List;

/**
 *
 * @author Duongntt
 */
public interface IHoaDonService {

    List<HoaDon> getHDByTrangThai(int trangThai);

    List<HoaDon> getHDChoByMaBan(int maBan);
}
