/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.util;

import java.math.BigDecimal;
import javax.swing.JOptionPane;

/**
 *
 * @author Duongntt
 */
public class ThanhToanUtil {

    public boolean checkBigDecimal(String tienCheck) {
        boolean isCheck = false;
        if (tienCheck.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không được để trống tiền thanh toán");
            return isCheck;
        } else if (!tienCheck.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(null, "Tiền thanh toán phải là số");
            return isCheck;
        } else if (Double.valueOf(tienCheck) < 0) {
            JOptionPane.showMessageDialog(null, "Tiền thanh toán phải là số dương");
            return isCheck;
        } else {
            isCheck = true;
            return isCheck;
        }
    }

    public BigDecimal fillTienThua(BigDecimal tienMat, BigDecimal chuyenKhoan, BigDecimal tongTien) {
        BigDecimal tienKhachTra = tienMat.add(chuyenKhoan);
        return tienKhachTra.subtract(tongTien);
    }
}
