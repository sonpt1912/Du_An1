/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.domainModel.Ban;
import com.mycompany.domainModel.ChiTietBanHoaDon;
import com.mycompany.domainModel.HoaDon;
import com.mycompany.hibernateUtil.HibernateUtil;
import com.mycompany.repository.IChiTietBanHoaDonRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Admin
 */
public class ChiTietBanHoaDonRepository implements IChiTietBanHoaDonRepository<ChiTietBanHoaDon, Boolean, HoaDon, Ban> {

    private static Session session = HibernateUtil.getFactory().openSession();
    private String fromTable = " FROM ChiTietBanHoaDon CTBHD ";

    @Override
    public List<ChiTietBanHoaDon> getAll() {
        Query query = session.createQuery(fromTable);
        List<ChiTietBanHoaDon> chiTietBanHoaDons = query.getResultList();
        return chiTietBanHoaDons;
    }

    @Override
    public Boolean add(ChiTietBanHoaDon chiTietBanHoaDon) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(chiTietBanHoaDon);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    @Override
    public Boolean update(ChiTietBanHoaDon chiTietBanHoaDon, HoaDon hoaDon, Ban ban) {
        String hql = "UPDATE" + fromTable + "SET CTBHD.hd = :hd, CTBHD.ban = :ban WHERE CTBHD.hd = :hdOut AND CTBHD.ban = :banOut";
        Transaction transaction = null;
        int check = 0;
        try {
            transaction = session.beginTransaction();
            session.clear();
            Query query = session.createQuery(hql);
            query.setParameter("hd", chiTietBanHoaDon.getHd());
            query.setParameter("ban", chiTietBanHoaDon.getBan());
            query.setParameter("hdOut", hoaDon);
            query.setParameter("banOut", ban);
            check = query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return check > 0;
    }

    @Override
    public List<ChiTietBanHoaDon> getByHoaDon(HoaDon hoaDon) {
        String hql = fromTable + "WHERE CTBHD.hd = :hd";
        Query query = session.createQuery(hql);
        query.setParameter("hd", hoaDon);
        List<ChiTietBanHoaDon> chiTietBanHoaDons = query.getResultList();
        return chiTietBanHoaDons;
    }
    
    @Override
    public List<ChiTietBanHoaDon> getByBan(Ban ban) {
        String hql = fromTable + "WHERE CTBHD.hd.trangThai = 0 AND CTBHD.ban = :ban";
        Query query = session.createQuery(hql);
        query.setParameter("ban", ban);
        List<ChiTietBanHoaDon> chiTietBanHoaDons = query.getResultList();
        return chiTietBanHoaDons;
    }

    @Override
    public Boolean remove(HoaDon hoaDon) {
        String hql = "DELETE" + fromTable + "WHERE CTBHD.hd = :hd";
        Transaction transaction = null;
        int check = 0;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("hd", hoaDon);
            check = query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return check > 0;
    }

    public static void main(String[] args) {
        Ban b = new Ban();
        b.setId("7A1E2672-47AA-415E-84D5-6D0AB9C42B63");
        List<ChiTietBanHoaDon> chiTietBanHoaDons = new ChiTietBanHoaDonRepository().getByBan(b);
        for (ChiTietBanHoaDon chiTietBanHoaDon : chiTietBanHoaDons) {
            System.out.println(chiTietBanHoaDon.toString());
        }
    }

}
