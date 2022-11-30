/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.domainModel.DanhMuc;
import com.mycompany.domainModel.KhuyenMai;
import com.mycompany.domainModel.MonAn;
import com.mycompany.hibernateUtil.HibernateUtil;
import com.mycompany.repository.ICommonRepository;
import com.mycompany.repository.IMonAnRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Admin
 */
public class MonAnRepository implements ICommonRepository<MonAn, Boolean, String>, IMonAnRepository {

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
                + "loai = :loaiMA, trangThai = :TrangThai WHERE maMonAn =:maMon";
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
            query.setParameter("TrangThai", kh.getTrangThai());
            //bỏ KM
            // query.setParameter("KM", kh.getKhuyenMai());
            query.setParameter("maMon", ma);
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
            Query query = session.createQuery("FROM MonAn WHERE loai.danhMuc = :danhMuc AND trangThai = 0");
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
            Query query = session.createQuery("FROM MonAn MA JOIN KhuyenMaiChiTiet kmct"
                    + " ON MA.id = kmct.monAn.id "
                    + "JOIN KhuyenMai km ON kmct.khuyenMai.id = km.id  WHERE khuyenMai = :KM");
            query.setParameter("KM", khuyenMai);
            listMA = query.getResultList();
        } finally {
            return listMA;
        }
    }

    // searchMonAnTheoTen
    public List<MonAn> searchMonAnTheoTen(String ten) {
        String hql = fromTable + "WHERE tenMonAn like :tenMonAnMoi";
        Query query = session.createQuery(hql);
        query.setParameter("tenMonAnMoi", "%" + ten + "%");
        List<MonAn> monAns = query.getResultList();
        return monAns;
    }

    public List<MonAn> getAllMonAnByTrangThai(int trangThai) {
        String hql = fromTable + "WHERE trangThai = :TrangThai ORDER BY maMonAn";
        Query query = session.createQuery(hql);
        query.setParameter("TrangThai", trangThai);
        List<MonAn> monAns = query.getResultList();
        return monAns;
    }

    @Override
    public List<MonAn> getMonAnCoKM() {
        List<MonAn> monAns = new ArrayList<>();
//        String hql = "FROM MonAn MA RIGHT OUTER JOIN KhuyenMai KM ON KM.id = MA.khuyenMai.id";
//        Query query = session.createQuery(hql);
//        monAns = query.getResultList();
        return monAns;
    }

    @Override
    public List<MonAn> getMonAnByComBo(MonAn id) {
        String hql = "FROM MonAn WHERE id = : ID";
        Query query = session.createQuery(hql);
        query.setParameter("ID", id.getId());
        // join ạ lấy điều kiện đúng oi
        List<MonAn> monAns = query.getResultList();
        return monAns;

    }

    @Override
    public List<MonAn> getMonAnLeftJoinKMCT() {
        List<MonAn> listMA = new ArrayList<>();
        try ( Session session = HibernateUtil.getFactory().openSession()) {
            Query query = session.createQuery("SELECT FROM MonAn MA LEFT JOIN KhuyenMaiChiTiet KMCT "
                    + "ON MA.id = KMCT.monAn.id");
            listMA = query.getResultList();
        } finally {
            return listMA;
        }
    }
//    public static void main(String[] args) {
//        new MonAnRepository().searchMonAnTheoTen("C").forEach(s -> System.out.println(s.toString()));
//    }
}
