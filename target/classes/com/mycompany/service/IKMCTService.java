/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.domainModel.KhuyenMai;
import com.mycompany.domainModel.KhuyenMaiChiTiet;
import com.mycompany.domainModel.MonAn;
import java.util.List;

/**
 *
 * @author Duongntt
 */
public interface IKMCTService {

    List<KhuyenMaiChiTiet> getKMCTByMaAndKM(MonAn monAn, KhuyenMai khuyenMai);

    List<KhuyenMaiChiTiet> getKMCTsByKM(KhuyenMai khuyenMai);

    List<KhuyenMaiChiTiet> getKMCTsByMA(MonAn monAn);
}
