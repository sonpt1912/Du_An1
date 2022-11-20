/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.domainModel.DanhMuc;
import com.mycompany.domainModel.KhuyenMai;
import com.mycompany.domainModel.Loai;
import com.mycompany.domainModel.MonAn;
import com.mycompany.hibernateUtil.HibernateUtil;
import com.mycompany.repository.ICommonRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Admin
 */
public class MonAnRepository implements ICommonRepository<MonAn, Boolean, String> {

    private static final Session session = HibernateUtil.getFactory().openSession();
    private String fromTable = "FROM MonAn ";

    @Override
    public List<MonAn> getAll() {
        String hql = fromTable + "WHERE trangThai = 0";
        Query query = session.createQuery(hql);
        List<MonAn> monAns = query.getResultList();
        return monAns;
    }

    public List<MonAn> getAllMonAn() {
        String hql = fromTable + "ORDER BY maMonAn";
        Query query = session.createQuery(hql);
        List<MonAn> monAns = query.getResultList();
        return monAns;
    }

    @Override
    public MonAn getOne(String ma) {
        String hql = fromTable + "WHERE maMonAn = :ma";
        Query query = session.createQuery(hql);
        query.setParameter("ma", ma);
        MonAn monAn = (MonAn) query.getSingleResult();
        return monAn;
    }

    @Override
    public Boolean add(MonAn kh) {
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
    public Boolean update(MonAn kh, String ma) {
        String hql = "UPDATE " + fromTable + "SET tenMonAn = :ten, hinhAnh = :hinhAnh, donGia = :donGia, donViTinh = :donViTinh,"
                + "loai = :loaiMA  "
                + "WHERE maMonAn =:ma";
        Transaction transaction = null;
        int check = 0;
        try {
            transaction = session.beginTransaction();
            session.clear();
            Query query = session.createQuery(hql);
            query.setParameter("ten", kh.getTenMonAn());
            query.setParameter("hinhAnh", kh.getHinhAnh());
            query.setParameter("donGia", kh.getDonGia());
            query.setParameter("donViTinh", kh.getDonViTinh());
            query.setParameter("loaiMA", kh.getLoai());
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
        String hql = "UPDATE " + fromTable + "SET trangThai = 1 "
                + "WHERE maMonAn =:ma";
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

    public List<MonAn> getMonAnByDanhMuc(DanhMuc danhMuc) {
        List<MonAn> listMA = new ArrayList<>();
        try ( Session session = HibernateUtil.getFactory().openSession()) {
            Query query = session.createQuery("FROM MonAn WHERE loai.danhMuc = :danhMuc");
            query.setParameter("danhMuc", danhMuc);
            listMA = query.getResultList();
        } finally {
            return listMA;
        }
    }
////thêm hàm getMoNAn theo khuyến mãi:

    public List<MonAn> getMonAnByKhuyenMai(KhuyenMai khuyenMai) {
        List<MonAn> listMA = new ArrayList<>();
        try ( Session session = HibernateUtil.getFactory().openSession()) {
            Query query = session.createQuery("FROM MonAn WHERE khuyenMai = :KM");
            query.setParameter("KM", khuyenMai);
            listMA = query.getResultList();
        } finally {
            return listMA;
        }
    }
    ////update khuyến mãi cho món ăn

    public boolean themKMChoMonAn(KhuyenMai khuyenMai, String maMA) {
        String hql = "UPDATE " + fromTable + "SET khuyenMai = :KM "
                + "WHERE maMonAn =:ma";
        Transaction transaction = null;
        int check = 0;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("KM", khuyenMai);
            query.setParameter("ma", maMA);
            check = query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return check > 0;
    }

    public List<MonAn> getAllMonAnByTrangThai(int trangThai) {
        String hql = fromTable + "WHERE trangThai = :TrangThai ORDER BY maMonAn";
        Query query = session.createQuery(hql);
        query.setParameter("TrangThai", trangThai);
        List<MonAn> monAns = query.getResultList();
        return monAns;
    }

    public static void main(String[] args) {
//        DanhMuc loaiMA = new DanhMuc();
//        loaiMA.setIdDanhMuc("57109CB2-7DA5-4245-A24C-5EF2BAD02EA3");
//        MonAn monAn = new MonAn();
//        monAn.setDonGia(BigDecimal.valueOf(200));
//        monAn.setDonViTinh("suất");
//        monAn.setMaMonAn("MA3");
//        monAn.setTenMonAn("xyzzzzzz");
////        monAn.setTrangThai(0);
//        boolean add = new MonAnRepository().remove("MA3");
//        System.out.println(add);
        DanhMuc danhMuc = new DanhMuc();
        danhMuc.setIdDanhMuc("E90C9CF5-5D6A-4DE0-8609-F190956442A7");
        List<MonAn> list = new MonAnRepository().getMonAnByDanhMuc(danhMuc);
        for (MonAn monAn : list) {
            System.out.println(monAn.toString());
        }
    }
}
