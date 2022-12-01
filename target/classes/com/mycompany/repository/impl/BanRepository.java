/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.domainModel.Ban;
import com.mycompany.domainModel.KhuVuc;
import com.mycompany.hibernateUtil.HibernateUtil;
import com.mycompany.repository.IBanRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.mycompany.repository.ICommonRepository;

/**
 *
 * @author Admin
 */
public class BanRepository implements IBanRepository, ICommonRepository<Ban, Boolean, String> {

    private static final Session session = HibernateUtil.getFactory().openSession();
    private String fromTable = "FROM Ban ";

    @Override
    public List<Ban> getAll() {
        String hql = fromTable + "WHERE trangThai = 0";
        Query query = session.createQuery(hql);
        List<Ban> bans = query.getResultList();
        return bans;
    }

    @Override
    public Ban getOne(String ma) {
        try {
            String hql = fromTable + "WHERE maBan = :ma";
            Query query = session.createQuery(hql);
            query.setParameter("ma", Integer.valueOf(ma));
            Ban b = (Ban) query.getSingleResult();
            return b;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean add(Ban kh) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(kh);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean update(Ban kh, String ma) {
        String hql = "UPDATE " + fromTable + "SET soLuongChoNgoi = :soLuongChoNgoi,kv = :kv ,trangThai = :trangThai "
                + "WHERE maBan = :ma";
        Transaction transaction = null;
        int check = 0;
        try {
            transaction = session.beginTransaction();
            session.clear();
            Query query = session.createQuery(hql);
            query.setParameter("soLuongChoNgoi", kh.getSoLuongChoNgoi());
            query.setParameter("kv", kh.getKv());
            query.setParameter("trangThai", kh.getTrangThai());
            query.setParameter("ma", Integer.valueOf(ma));
            check = query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public Boolean remove(String ma) {
        String hql = "UPDATE " + fromTable + "SET trangThai = 1"
                + "WHERE maBan = :ma";
        Transaction transaction = null;
        int check = 0;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("ma", Integer.valueOf(ma));
            check = query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return check > 0;
    }

    public List<Ban> getFull() {
        String hql = fromTable;
        Query query = session.createQuery(hql);
        List<Ban> bans = query.getResultList();
        return bans;
    }

    public Boolean delete(String ma) {
        String hql = "DELETE " + fromTable
                + "WHERE maBan = :ma";
        Transaction transaction = null;
        int check = 0;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("ma", Integer.valueOf(ma));
            check = query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return check > 0;
    }

    public static void main(String[] args) {
        Ban b = new BanRepository().getOne("5");
        System.out.println(b);
    }

    @Override
    public List<Ban> searchByString(String search) {
        String hql = "FROM Ban B WHERE B.kv.tenKV LIKE :tenKV ";
        Query query = session.createQuery(hql);
        query.setParameter("tenKV", "%" + search + "%");
        List<Ban> search2 = query.getResultList();
        return search2;
    }

    @Override
    public List<Ban> searchByInteger(Integer search) {
        String hql = "FROM Ban B WHERE B.maBan = :maBan "
                + "OR B.soLuongChoNgoi = :soLuongChoNgoi";
        Query query = session.createQuery(hql);
        query.setParameter("maBan", Integer.valueOf(search));
        query.setParameter("soLuongChoNgoi", Integer.valueOf(search));
        List<Ban> search2 = query.getResultList();
        return search2;
    }

}
