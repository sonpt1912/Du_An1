/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.domainModel.DanhMuc;
import com.mycompany.domainModel.Loai;
import com.mycompany.hibernateUtil.HibernateUtil;
import com.mycompany.repository.ICommonRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Duongntt
 */
public class LoaiRepository implements ICommonRepository<Loai, Boolean, String> {

    @Override
    public List<Loai> getAll() {
        List<Loai> listLoai = new ArrayList<>();
        try ( Session session = HibernateUtil.getFactory().openSession()) {
            Query query = session.createQuery("FROM Loai ORDER BY maLoai");
            listLoai = query.getResultList();
        } finally {
            return listLoai;
        }
    }

    @Override
    public Loai getOne(String ma) {
        Loai loai = new Loai();
        try ( Session session = HibernateUtil.getFactory().openSession()) {
            Query query = session.createQuery("FROM Loai WHERE maLoai = :ma");
            query.setParameter("ma", ma);
            loai = (Loai) query.getSingleResult();
        } finally {
            return loai;
        }
    }

    @Override
    public Boolean add(Loai kh) {
        boolean isAdd = false;
        try ( Session session = HibernateUtil.getFactory().openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            try {
                session.save(kh);
                transaction.commit();
                isAdd = true;
            } catch (Exception e) {
                e.printStackTrace();
                transaction.rollback();
            }
        } finally {
            return isAdd;
        }
    }

    @Override
    public Boolean update(Loai loai, String ma) {
        int check = 0;
        try ( Session session = HibernateUtil.getFactory().openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            try {
                Query query = session.createQuery("UPDATE Loai SET tenLoai = :tenLoai, trangThai = :trangThai, "
                        + "danhMuc = :danhMuc WHERE maLoai = :ma");
                query.setParameter("tenLoai", loai.getTenLoai());
                query.setParameter("trangThai", loai.getTrangThai());
                query.setParameter("danhMuc", loai.getDanhMuc());
                query.setParameter("ma", ma);
                check = query.executeUpdate();
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                transaction.rollback();
            }
        } finally {
            return check > 0;
        }
    }

    @Override
    public Boolean remove(String ma) {
        //chuyển trạng tháis
        int check = 0;
        try ( Session session = HibernateUtil.getFactory().openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            try {
                Query query = session.createQuery("UPDATE Loai SET trangThai = :trangThai "
                        + " WHERE maLoai = :ma");
                query.setParameter("trangThai", 1);
                query.setParameter("ma", ma);
                check = query.executeUpdate();
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                transaction.rollback();
            }
        } finally {
            return check > 0;
        }
    }

    public Loai getOneByDanhMuc(DanhMuc danhMuc) {
        Loai loai = new Loai();
        try ( Session session = HibernateUtil.getFactory().openSession()) {
            Query query = session.createQuery("FROM Loai WHERE danhMuc = :DanhMuc");
            query.setParameter("DanhMuc", danhMuc);
            loai = (Loai) query.getSingleResult();
        } finally {
            return loai;
        }
    }

    public static void main(String[] args) {
        Loai loai = new Loai();
        DanhMuc danhMuc = new DanhMuc();
        danhMuc.setIdDanhMuc("BA987442-27D9-4076-B86A-6DD10E377CCE");
        loai.setDanhMuc(danhMuc);
        loai.setMaLoai("L5");
        loai.setTenLoai("Loại 55555");
        loai.setTrangThai(0);
        System.out.println(new LoaiRepository().remove("L5"));
        List<Loai> list = new LoaiRepository().getAll();
        for (Loai loai1 : list) {
            System.out.println(loai1.toString());
        }
//        Loai loai = new LoaiRepository().getOne("L1");
//        System.out.println(loai.toString());
    }

}
