/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.util;

import java.math.BigDecimal;

/**
 *
 * @author Duongntt
 */
public class TinhTienKhuyenMai {

    public BigDecimal tinhTienKM(double donGia, double gtriKM) {
        return BigDecimal.valueOf(donGia - gtriKM);
    }
}
