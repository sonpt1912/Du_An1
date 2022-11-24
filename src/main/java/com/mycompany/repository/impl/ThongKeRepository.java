/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.domainModel.HoaDon;
import com.mycompany.domainModel.MonAn;
import com.mycompany.hibernateUtil.HibernateUtil;
import com.mycompany.repository.IThongKeRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author PTS
 */
public class ThongKeRepository implements IThongKeRepository {

    private static final Session SESSION = HibernateUtil.getFactory().openSession();

    @Override
    public List<HoaDon> getAllHoaDon(int trangThai) {
        String hql = "FROM HoaDon WHERE trangThai <> :TrangThai";
        Query query = SESSION.createQuery(hql);
        query.setParameter("TrangThai", trangThai);
        List<HoaDon> hoaDons = query.getResultList();
        return hoaDons;
    }

    @Override
    public List<MonAn> getAllSanPham() {
        String hql = "FROM MonAn";
        Query query = SESSION.createQuery(hql);
        List<MonAn> monAns = query.getResultList();
        return monAns;
    }

    public static void main(String[] args) {
        List<HoaDon> listHoaDon = new ThongKeRepository().getAllHoaDon(0);
        for (HoaDon hoaDon : listHoaDon) {
            System.out.println(hoaDon.toString());
        }
    }

    @Override
    public List<HoaDon> getAllHoaDonTrangThai(int trangThai) {
        String hql = "FROM HoaDon WHERE trangThai = :TrangThai";
        Query query = SESSION.createQuery(hql);
        query.setParameter("TrangThai", trangThai);
        List<HoaDon> hoaDons = query.getResultList();
        return hoaDons;
    }

    @Override
    public List<MonAn> getAllSanPhamTrangThai(int trangThai) {
        String hql = "FROM MonAn WHERE trangThai = :TrangThai";
        Query query = SESSION.createQuery(hql);
        query.setParameter("TrangThai", trangThai);
        List<MonAn> monAns = query.getResultList();
        return monAns;
    }
}
