/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.domainModel.Ban;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IBanService {

    List<Ban> searchByString(String search);

    List<Ban> searchByInteger(Integer search);
    
}
