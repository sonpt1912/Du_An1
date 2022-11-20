/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.customModel.HoaDonResponse;
import com.mycompany.hibernateUtil.HibernateUtil;
import com.mycompany.repository.ICommonResponseRepository;
import com.mycompany.repository.IcommonHoaDonResponseRepository;
import java.sql.Date;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class HoaDonResponseRepository implements IcommonHoaDonResponseRepository<HoaDonResponse, Integer> {

    private static final Session session = HibernateUtil.getFactory().openSession();
    private String fromTable = " FROM HoaDon HD ";

    @Override
    public List<HoaDonResponse> getAll() {
        String hql = "SELECT new com.mycompany.customModel.HoaDonResponse(HD.maHoaDon,KH.ma,HD.ngayTao,HD.trangThai,HD.ghiChu)"
                + fromTable + "LEFT JOIN KhachHang KH ON KH.id = HD.khachHang";
        Query query = session.createQuery(hql);
        List<HoaDonResponse> hoaDonResponses = query.getResultList();
        return hoaDonResponses;
    }

    public static void main(String[] args) {
        List<HoaDonResponse> test = new HoaDonResponseRepository().getByTrangThai(1);
        for (HoaDonResponse monAnResponse : test) {
            System.out.println(test);
        }
    }

    @Override
    public List<HoaDonResponse> getByTrangThai(Integer trangThai) {
        String hql = "SELECT new com.mycompany.customModel.HoaDonResponse(HD.maHoaDon,KH.ma,HD.ngayTao,HD.trangThai,HD.ghiChu)"
                + fromTable + "LEFT JOIN KhachHang KH ON KH.id = HD.khachHang WHERE HD.trangThai = :trangThai";
        Query query = session.createQuery(hql);
        query.setParameter("trangThai", trangThai);
        List<HoaDonResponse> hoaDonResponses = query.getResultList();
        return hoaDonResponses;
    }
}
