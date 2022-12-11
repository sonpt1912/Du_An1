/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.repository;

import com.mycompany.domainModel.RankKhachHang;
import java.util.List;

/**
 *
 * @author PTS
 */
public interface IRankKhachHang {

    List<RankKhachHang> getAll();

    RankKhachHang getOne(String ma);

    RankKhachHang getOneById(String id);

    Boolean add(RankKhachHang kh);

    Boolean update(RankKhachHang kh, int ma);

    Boolean remove(int ma);

    String Rank(String idKH);

    Boolean updateRank(int ma);
    
}
