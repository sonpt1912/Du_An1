/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.repository;

import com.mycompany.customModel.MonAnCoKM;
import com.mycompany.domainModel.ComBo;
import com.mycompany.domainModel.MonAn;
import java.util.List;

/**
 *
 * @author Duongntt
 */
public interface IMonAnRepository {

    List<MonAnCoKM> getMonAnCoKM();

    List<MonAn> getMonAnByComBo(MonAn id);

    List<MonAn> getMonAnLeftJoinKMCT();

    List<MonAn> searchMonAnFormSP(String tenTimKiem);
}
