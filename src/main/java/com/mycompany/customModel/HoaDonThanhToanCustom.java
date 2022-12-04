/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.customModel;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Duongntt
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HoaDonThanhToanCustom {

    private String maHD;
    private String maNV;
    private String maKH;
    private Date ngayTao;
    private Date ngayThanhToan;
    private BigDecimal tongTien;
    private BigDecimal tienDuocGiam;
    private BigDecimal tienThua;
    private String ghiChu;
    private String listBan;
    private BigDecimal tienMat;
    private BigDecimal tienChuyenKhoan;
}
