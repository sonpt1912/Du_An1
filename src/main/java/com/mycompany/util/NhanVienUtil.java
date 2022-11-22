/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.util;

import com.mycompany.domainModel.NhanVien;
import java.util.List;

/**
 *
 * @author son45
 */
public class NhanVienUtil {

    public String maTuDong(List<NhanVien> list) {
        String maNV = "KH";
        int sz = list.size();
        return maNV + (sz + 1);
    }
}
