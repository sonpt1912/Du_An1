/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.hibernateUtil.HibernateUtil;
import com.mycompany.repository.IBanResponseRepository;
import com.mycompany.repository.ICommonResponseRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class BanResponseRepository implements IBanResponseRepository<BanResponseRepository, Integer> {

    private static final Session session = HibernateUtil.getFactory().openSession();
    private String fromTable = " FROM Ban B ";

    @Override
    public List<BanResponseRepository> getAll() {
        String hql = "SELECT new com.mycompany.customModel.BanResponse(B.maBan,B.soLuongChoNgoi,B.kv.tenKV,B.trangThai)"
                + fromTable + "WHERE B.trangThai <> 2";
        Query query = session.createQuery(hql);
        List<BanResponseRepository> banResponses = query.getResultList();
        return banResponses;
    }

    @Override
    public List<BanResponseRepository> getByTrangThai(Integer trangThai) {
        String hql = "SELECT new com.mycompany.customModel.BanResponse(B.maBan,B.soLuongChoNgoi,B.kv.tenKV,B.trangThai)" + fromTable
                + "WHERE trangThai = 0";
        Query query = session.createQuery(hql);
        List<BanResponseRepository> banResponses = query.getResultList();
        return banResponses;
    }

    @Override
    public List<BanResponseRepository> getByTrangThaiAndSoLuongChoNgoi(Integer trangThai, Integer soLuongKhach) {
        String hql = "SELECT new com.mycompany.customModel.BanResponse(B.maBan,B.soLuongChoNgoi,B.kv.tenKV,B.trangThai)" + fromTable
                + "WHERE B.trangThai = 0 AND B.soLuongChoNgoi >= :soLuongKhach";
        Query query = session.createQuery(hql);
        query.setParameter("soLuongKhach", soLuongKhach);
        List<BanResponseRepository> banResponses = query.getResultList();
        return banResponses;
    }

    @Override
    public List<BanResponseRepository> getByTrangThaiAndKhuVuc(String tenKV) {
        String hql = "SELECT new com.mycompany.customModel.BanResponse(B.maBan,B.soLuongChoNgoi,B.kv.tenKV,B.trangThai)" + fromTable
                + "WHERE B.kv.trangThai = 0 AND B.kv.tenKV = :tenKV";
        Query query = session.createQuery(hql);
        query.setParameter("tenKV", tenKV);
        List<BanResponseRepository> banResponses = query.getResultList();
        return banResponses;
    }

}
