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
 * @author PTS
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ExcelReponse {

    private String thang;
    private BigDecimal doanhthu;
    private String thang2;
    private BigDecimal doanhthu2;
//
//    public ExcelReponse(String thang, BigDecimal doanhthu) {
//        this.thang = thang;
//        this.doanhthu = doanhthu;
//    }
    
}
