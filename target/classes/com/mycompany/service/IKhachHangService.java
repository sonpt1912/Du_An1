/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.domainModel.KhachHang;
import com.mycompany.domainModel.RankKhachHang;
import java.util.List;

/**
 *
 * @author Duongntt
 */
public interface IKhachHangService {

    List<KhachHang> searchBySDTOrTen(String txtTimKiem);

    KhachHang getOneBySdt(String sdt);
     
       int xepHangKhachHang(String idKH);

     String updateIdRank(String idKH, RankKhachHang idRank);
}
