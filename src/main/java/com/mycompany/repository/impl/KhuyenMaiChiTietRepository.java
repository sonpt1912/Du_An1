/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.domainModel.KhuyenMai;
import com.mycompany.domainModel.KhuyenMaiChiTiet;
import com.mycompany.domainModel.MonAn;
import com.mycompany.hibernateUtil.HibernateUtil;
import com.mycompany.repository.ICommonRepository;
import com.mycompany.repository.IKMCTRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Duongntt
 */
public class KhuyenMaiChiTietRepository implements ICommonRepository<KhuyenMaiChiTiet, Boolean, String>, IKMCTRepository {

    @Override
    public List<KhuyenMaiChiTiet> getAll() {
        List<KhuyenMaiChiTiet> listKMCT = new ArrayList<>();
        Session session = HibernateUtil.getFactory().openSession();
        Query query = session.createQuery("FROM KhuyenMaiChiTiet");
        listKMCT = query.getResultList();
        return listKMCT;
    }

    //get One by Id
    @Override
    public KhuyenMaiChiTiet getOne(String idDCMT) {
        KhuyenMaiChiTiet khuyenMaiChiTiet = new KhuyenMaiChiTiet();
        Session session = HibernateUtil.getFactory().openSession();
        Query query = session.createQuery("FROM KhuyenMaiChiTiet WHERE id = :idKMCT");
        query.setParameter("idKMCT", idDCMT);
        khuyenMaiChiTiet = (KhuyenMaiChiTiet) query.getSingleResult();
        return khuyenMaiChiTiet;
    }

    @Override
    public Boolean add(KhuyenMaiChiTiet kmct) {
        int check = 0;
        try ( Session session = HibernateUtil.getFactory().openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            try {
                session.save(kmct);
                transaction.commit();
                check = 1;
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        } finally {
            return check > 0;
        }
    }

//update where Id:
    @Override
    public Boolean update(KhuyenMaiChiTiet kmct, String id) {
        int check = 0;
        try ( Session session = HibernateUtil.getFactory().openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            try {
                String hql = "UPDATE KhuyenMaiChiTiet SET monAn = :MA, khuyenMai = :KM, donGiaSauKM = :donGia "
                        + " trangThai = :trangThai WHERE id = :idKMCT";
                Query query = session.createQuery(hql);
                query.setParameter("MA", kmct.getMonAn());
                query.setParameter("KM", kmct.getKhuyenMai());
                query.setParameter("trangThai", kmct.getTrangThai());
                query.setParameter("donGia", kmct.getDonGiaSauKM());
                query.setParameter("idKMCT", id);
                check = query.executeUpdate();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        } finally {
            return check > 0;
        }
    }

//remove by id_ code cho vui cũng chả để làm gì nhể=))
    @Override
    public Boolean remove(String id) {
        int check = 0;
        try ( Session session = HibernateUtil.getFactory().openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            try {
                String hql = "DELETE FROM KhuyenMaiChiTiet WHERE id = :idKMCT";
                Query query = session.createQuery(hql);
                query.setParameter("idKMCT", id);
                check = query.executeUpdate();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        } finally {
            return check > 0;
        }
    }

    @Override
    public List<KhuyenMaiChiTiet> getKMCTByMaAndKM(MonAn monAn, KhuyenMai khuyenMai) {
        List<KhuyenMaiChiTiet> listKMCT = new ArrayList<>();
        Session session = HibernateUtil.getFactory().openSession();
        Query query = session.createQuery("FROM KhuyenMaiChiTiet WHERE monAn = :MA AND khuyenMai = :KM");
        query.setParameter("MA", monAn);
        query.setParameter("KM", khuyenMai);
        listKMCT = query.getResultList();
        return listKMCT;
    }

    public static void main(String[] args) {
        MonAn monAn = new MonAn();
        monAn.setId("91855412-290D-470F-9CB9-C95C18C60CB7");
        KhuyenMai khuyenMai = new KhuyenMai();
        khuyenMai.setId("7BC6AC30-4199-44B4-9931-899EB6B78805");
        List<KhuyenMaiChiTiet> listKM = new KhuyenMaiChiTietRepository().getKMCTByMaAndKM(monAn, khuyenMai);
        for (KhuyenMaiChiTiet khuyenMaiChiTiet : listKM) {
            System.out.println(khuyenMaiChiTiet);
        }
    }

    @Override
    public List<KhuyenMaiChiTiet> getKMCTsByKM(KhuyenMai khuyenMai) {
        List<KhuyenMaiChiTiet> listKMCT = new ArrayList<>();
        Session session = HibernateUtil.getFactory().openSession();
        Query query = session.createQuery("FROM KhuyenMaiChiTiet WHERE khuyenMai = :KM");
        query.setParameter("KM", khuyenMai);
        listKMCT = query.getResultList();
        return listKMCT;
    }
}
