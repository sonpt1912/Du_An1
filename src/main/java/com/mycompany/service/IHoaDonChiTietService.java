/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.domainModel.ComBo;
import com.mycompany.domainModel.HoaDon;
import com.mycompany.domainModel.MonAn;
import java.util.List;

//O = Object
//B = boolean
//S = String
//O2 = Object2
public interface IHoaDonChiTietService<O, S, O2> {

    List<O> getAll();

    O getOne(S ma);

    S add(O kh);

    S update(O kh, O2 ma);

    S remove(O2 ma);

    List<O> getHDCTByHD(O2 hoaDon);

    O getOneHDCTByMAHD(HoaDon hd, MonAn monAn);

    O getOneHDCTByCombo(HoaDon hd, ComBo combo);

    S updateHDCTById(O kh, S idHDCT);

    S updateSoLuongCombo(O HDCT, O2 hd, ComBo combo);

    S updateSoLuongMonAn(O HDCT, O2 hd, MonAn MonAn);
}
