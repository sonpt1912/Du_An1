/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.repository;

import java.util.List;

/**
 *
 * @author Admin
 */
public interface IChiTietBanHoaDonRepository<O, B, O2, O3> {

    List<O> getAll();

    B add(O chiTietBanHoaDon);

    B update(O chiTietBanHoaDon, O2 hoaDon, O3 ban);

    List<O> getByHoaDon(O2 hoaDon);
    
}
