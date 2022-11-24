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
public interface IMonAnResponseRepository<O, S> {

    List<O> getAll();

    List<O> getByDanhMuc(S tenDanhMuc);

    List<O> getByDanhMucAndTenMonAn(S tenMonAn, S tenDanhMuc);

}
