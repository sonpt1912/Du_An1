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
    public List<HoaDon> getAllHoaDon() {
        String hql = "FROM HoaDon ";
        Query query = SESSION.createQuery(hql);
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

    @Override
    public int getCountHoaDon(int trangThai) {
        String hql = "SELECT COUNT(*) FROM dbo.HoaDon WHERE TrangThai = :trangThai";
        Query query = SESSION.createQuery(hql);
        query.setParameter("trangThai", trangThai);
        int hoaDon = (int) query.getSingleResult();
        return hoaDon;
    }

    public static void main(String[] args) {
//        List<MonAn> listHoaDon = new ThongKeRepository().getAllSanPham();
//        for (MonAn hoaDon : listHoaDon) {
//            System.out.println(hoaDon.toString());
//        }
        int trangThai = 1;
        int hoaDon = new ThongKeRepository().getCountHoaDon(trangThai);
        System.out.println(hoaDon);
    }
}
