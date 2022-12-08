/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.impl;

import com.mycompany.customModel.BanResponse;
import com.mycompany.repository.IBanResponseRepository;
import com.mycompany.repository.ICommonResponseRepository;
import com.mycompany.repository.impl.BanResponseRepository;
import com.mycompany.service.IBanResponseService;
import com.mycompany.service.ICommonResponseService;
import java.util.List;

/**
 *
 * @author Admin
 */
public class BanResponseService implements IBanResponseService<BanResponse, Integer> {

    private final IBanResponseRepository brr = new BanResponseRepository();

    @Override
    public List<BanResponse> getAll() {
        return brr.getAll();
    }

    @Override
    public List<BanResponse> getByTrangThai(Integer trangThai) {
        return brr.getByTrangThai(trangThai);
    }

    @Override
    public List<BanResponse> getByTrangThaiAndSoLuongChoNgoi(Integer trangThai, Integer soLuongKhach) {
        return brr.getByTrangThaiAndSoLuongChoNgoi(trangThai, soLuongKhach);
    }

    @Override
    public List<BanResponse> getByTrangThaiAndKhuVuc(String tenKV) {
        return brr.getByTrangThaiAndKhuVuc(tenKV);
    }

}
