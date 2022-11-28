/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.domainModel.KhuyenMai;
import java.util.List;

/**
 *
 * @author Duongntt
 */
public interface IKhuyenMaiService {

    List<KhuyenMai> searchKM(String keyWord);
    
}
