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
public interface IBanResponseRepository<O, I> {

    List<O> getAll();

    List<O> getByTrangThai(I trangThai);

    List<O> getByTrangThaiAndSoLuongChoNgoi(I trangThai, I soLuongKhach);

}
