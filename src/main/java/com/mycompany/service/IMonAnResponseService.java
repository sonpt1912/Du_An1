/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.service;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IMonAnResponseService<O, S> {

    List<O> getAll();

    List<O> getByDanhMuc(S tenDanhMuc);

    List<O> getByDanhMucAndTenMonAn(S tenMonAn, S tenDanhMuc);
    List<O> getByDanhMucAndDonGia(BigDecimal donGia, S tenDanhMuc);

    List<O> getMonAnJoinKMCT(String tenDanhMuc);
}
