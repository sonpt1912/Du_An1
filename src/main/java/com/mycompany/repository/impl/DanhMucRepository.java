/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.domainModel.DanhMuc;
import com.mycompany.hibernateUtil.HibernateUtil;
import com.mycompany.repository.ICommonRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Admin
 */
public class DanhMucRepository implements ICommonRepository<DanhMuc, Boolean, String> {
    
    private static final Session session = HibernateUtil.getFactory().openSession();
    private String fromTable = "FROM DanhMuc ";
    
    @Override
    public List<DanhMuc> getAll() {
        String hql = fromTable;
        Query query = session.createQuery(hql);
        List<DanhMuc> danhMucs = query.getResultList();
        return danhMucs;
    }
    
    @Override
    public DanhMuc getOne(String ma) {
        String hql = fromTable + "WHERE trangThai = 0 AND maDanhMuc = :ma";
        Query query = session.createQuery(hql);
        query.setParameter("ma", ma);
        DanhMuc dm = (DanhMuc) query.getSingleResult();
        return dm;
    }
    
    @Override
    public Boolean add(DanhMuc kh) {
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
    public Boolean update(DanhMuc kh, String ma) {
        String hql = "UPDATE " + fromTable + "SET tenDanhMuc = :tenDanhMuc, trangThai = :trangThaiDM WHERE maDanhMuc = :ma ";
        Transaction transaction = null;
        int check = 0;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("tenDanhMuc", kh.getTenDanhMuc());
            query.setParameter("trangThaiDM", kh.getTrangThai());
            query.setParameter("ma", ma);
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
        String hql = "UPDATE " + fromTable + "SET trangThai = 1 WHERE maDanhMuc = :ma ";
        Transaction transaction = null;
        int check = 0;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("ma", ma);
            check = query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return check > 0;
    }
    
    public static void main(String[] args) {
        //DanhMuc danhMuc = new DanhMucRepository().getOne("DM1");
        //System.out.println(danhMuc.toString());
//        DanhMuc danhMuc = new DanhMuc();
//        //  danhMuc.setMaDanhMuc("DM3");
//        danhMuc.setTenDanhMuc("Đồ ăn vặt");
//        danhMuc.setTrangThai(0);
//        System.out.println(new DanhMucRepository().update(danhMuc, "DM3"));
//        List<DanhMuc> listDM = new DanhMucRepository().getAll();
//        for (DanhMuc danhMuc1 : listDM) {
//            System.out.println(danhMuc1.toString());
//        }
        System.out.println(new DanhMucRepository().getOne("DM1"));
    }
}
