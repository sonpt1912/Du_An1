/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.customModel;

import java.math.BigDecimal;
import java.math.BigInteger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HoaDonChiTietResponse {

    private String maMonAn;

    private String tenMonAn;

    private BigDecimal donGiaMonAn;

    private Integer soLuongMonAn;

    private String maCombo;

    private String tenCombo;

    private BigDecimal donGiaCombo;

    private Integer soLuongCombo;

    private String ghiChu;

    public Object[] toDataRow(int stt) {
//        return new Object[]{stt, maMonAn, tenMonAn, donGiaMonAn,
//            maCombo != null ? maCombo : "", tenCombo != null ? tenCombo : "", donGiaCombo != null ? donGiaCombo : ""};
        return new Object[]{stt, tenMonAn, donGiaMonAn, soLuongMonAn, tenCombo == null ? "" : tenCombo, donGiaCombo == null ? "" : donGiaCombo, soLuongCombo,tongTien(),ghiChu};
    }
    private BigDecimal tongTien(){
        if (null==tenCombo) {
            BigDecimal soLuong = new BigDecimal(soLuongMonAn);
            return donGiaMonAn.multiply(soLuong);
        }else{
            BigDecimal soLuong = new BigDecimal(soLuongCombo);
            return donGiaCombo.multiply(soLuong);
        }
    }
}
