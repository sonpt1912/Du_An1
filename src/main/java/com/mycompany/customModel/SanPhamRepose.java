/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.customModel;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author son45
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SanPhamRepose {

    private String ma;
    private String ten;
    private int soLuong;

    public Object[] toShowData(int stt) {
        return new Object[]{stt, ma, ten, soLuong};
    }

}
