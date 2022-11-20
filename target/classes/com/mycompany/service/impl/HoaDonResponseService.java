/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.impl;

import com.mycompany.customModel.HoaDonResponse;
import com.mycompany.repository.ICommonResponseRepository;
import com.mycompany.repository.IcommonHoaDonResponseRepository;
import com.mycompany.repository.impl.HoaDonResponseRepository;
import com.mycompany.service.ICommonResponseService;
import com.mycompany.service.IcommonHoaDonResponseService;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HoaDonResponseService implements IcommonHoaDonResponseService<HoaDonResponse, Integer> {

    private final IcommonHoaDonResponseRepository hdrr = new HoaDonResponseRepository();

    @Override
    public List<HoaDonResponse> getAll() {
        return hdrr.getAll();
    }

    @Override
    public List<HoaDonResponse> getByTrangThai(Integer trangThai) {
        return hdrr.getByTrangThai(trangThai);
    }

}
