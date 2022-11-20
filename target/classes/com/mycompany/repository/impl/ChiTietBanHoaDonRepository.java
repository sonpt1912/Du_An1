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
    private String fromTable = " FROM ChiTietBanHoaDon ";

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
        String hql = "UPDATE" + fromTable + "SET hd = :hd, ban = :ban WHERE hd = :hdOut AND ban = :banOut";
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
        String hql = fromTable + "WHERE hd = :hd";
        Query query = session.createQuery(hql);
        query.setParameter("hd", hoaDon);
        List<ChiTietBanHoaDon> chiTietBanHoaDons = query.getResultList();
        return chiTietBanHoaDons;
    }

    public static void main(String[] args) {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setId("75E38A4A-D7E4-4B1F-84D3-A6677696BB83");
//        Ban b = new Ban();
//        b.setId("BCD9EBC1-B3B5-480D-9A7B-1B6C2FEAD603");
//        HoaDon hd = new HoaDon();
//        hd.setId("75E38A4A-D7E4-4B1F-84D3-A6677696BB83");
//        Ban ban = new Ban();
//        ban.setId("00BE29FE-3C01-4C16-B6AE-097DE507C78E");
//        ChiTietBanHoaDon chiTietBanHoaDon = new ChiTietBanHoaDon(null, hd, ban);
//        Boolean add = new ChiTietBanHoaDonRepository().update(chiTietBanHoaDon,hoaDon,b);
//        System.out.println(add);
        List<ChiTietBanHoaDon> chiTietBanHoaDons = new ChiTietBanHoaDonRepository().getByHoaDon(hoaDon);
        for (ChiTietBanHoaDon chiTietBanHoaDon : chiTietBanHoaDons) {
            System.out.println(chiTietBanHoaDon);
        }
    }

}
