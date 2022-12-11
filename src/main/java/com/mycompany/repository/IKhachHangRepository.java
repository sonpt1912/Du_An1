/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.repository;

import com.mycompany.domainModel.KhachHang;
import com.mycompany.domainModel.RankKhachHang;
import java.util.List;

/**
 *
 * @author Duongntt
 */
public interface IKhachHangRepository {

    List<KhachHang> searchBySDTOrTen(String txtTimKiem);

    KhachHang getOneBySdt(String sdt);

    int xepHangKhachHang(String idKH);

     Boolean updateIdRank(String idKH, RankKhachHang idRank);
}
