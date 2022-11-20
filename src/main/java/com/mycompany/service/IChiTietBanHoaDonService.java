/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.service;

import java.util.List;

/**
 *
 * @author Admin
 */
public interface IChiTietBanHoaDonService<O, S, O2, O3> {

    List<O> getAll();

    S add(O chiTietBanHoaDon);

    S update(O chiTietBanHoaDon, O2 hoaDon, O3 ban);

    List<O> getByHoaDon(O2 hoaDon);
    
}
