/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.util;

import com.mycompany.domainModel.KhachHang;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Duongntt
 */
public class KhachHangUtil {

    private java.util.Date today = new java.util.Date();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public String zenMaKH(List<KhachHang> listKH) {
        String maKH = "KH";
        int sz = listKH.size();
        maKH = maKH + (sz + 1);
        for (KhachHang khachHang : listKH) {
            if (khachHang.getMa().equals(maKH)) {
                maKH = "KH" + (sz + 1 + 1);
            }
        }
        return maKH;
    }

    public boolean checkValidate(KhachHang khachHang) {
        boolean isCheck = false;
        String todayString = dateFormat.format(today);
        String ngaySinh = String.valueOf(khachHang.getNgaySinh());
        if (khachHang.getTen().isBlank()) {
            isCheck = false;
            JOptionPane.showMessageDialog(null, "Tên khách hàng không được trống!");
        } else if (khachHang.getTen().isEmpty()) {
            isCheck = false;
            JOptionPane.showMessageDialog(null, "Tên khách hàng không được trống!");
        } else if (khachHang.getSdt().isEmpty()) {
            isCheck = false;
            JOptionPane.showMessageDialog(null, "SDT khách hàng không được trống!");
        } else if (!khachHang.getSdt().matches("(0)((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))[0-9]{7}")) {
            isCheck = false;
            JOptionPane.showMessageDialog(null, "sdt không đúng định dạng");
        } else if (!khachHang.getHo().isEmpty() && (!khachHang.getHo().matches("^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ ]+$"))) {
            isCheck = false;
            JOptionPane.showMessageDialog(null, "Họ không được chứa kí tự đặc biệt!");
        } else if (!khachHang.getTen().isEmpty() && (!khachHang.getTen().matches("^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ ]+$"))) {
            isCheck = false;
            JOptionPane.showMessageDialog(null, "Tên không được chứa kí tự đặc biệt!");
        } else if (!khachHang.getTenDem().isEmpty() && (!khachHang.getTenDem().matches("^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ ]+$"))) {
            isCheck = false;
            JOptionPane.showMessageDialog(null, "Tên đệm không được chứa kí tự đặc biệt!");
        } else if (ngaySinh.compareTo(todayString) > 0) {
            isCheck = false;
            JOptionPane.showMessageDialog(null, "Ngày sinh không hợp lệ!");
        } else {
            isCheck = true;
        }
        return isCheck;
    }
}
