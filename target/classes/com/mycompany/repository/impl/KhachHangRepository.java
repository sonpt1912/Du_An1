/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.domainModel.KhachHang;
import com.mycompany.domainModel.RankKhachHang;
import com.mycompany.hibernateUtil.HibernateUtil;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.mycompany.repository.ICommonRepository;
import com.mycompany.repository.IKhachHangRepository;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class KhachHangRepository implements ICommonRepository<KhachHang, Boolean, String>, IKhachHangRepository {

    private static final Session session = HibernateUtil.getFactory().openSession();
    private String fromTable = "FROM KhachHang ";

    @Override
    public List<KhachHang> getAll() {
        String hql = fromTable;
        Query query = session.createQuery(hql);
        List<KhachHang> khachHangs = query.getResultList();
        return khachHangs;
    }

    @Override
    public KhachHang getOne(String ma) {
        try {

            String hql = fromTable + "WHERE ma = :ma and trangThai = 0";
            Query query = session.createQuery(hql);
            query.setParameter("ma", ma);
            KhachHang kh = (KhachHang) query.getSingleResult();
            return kh;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean add(KhachHang kh) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
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
    public Boolean update(KhachHang kh, String ma) {
        String hql = "UPDATE KhachHang SET ho = :ho, tenDem = :tenDem, ten = :ten, gioiTinh = :gioiTinh, ngaySinh = :ngaySinh, "
                + "sdt = :sdt, diaChi = :diaChi, thanhPho = :thanhPho, quocGia = :quocGia, trangThai = :trangThai "
                + "WHERE ma = :ma";
        int check = 0;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.clear();
            Query query = session.createQuery(hql);
            query.setParameter("ho", kh.getHo());
            query.setParameter("tenDem", kh.getTenDem());
            query.setParameter("ten", kh.getTen());
            query.setParameter("gioiTinh", kh.getGioiTinh());
            query.setParameter("ngaySinh", kh.getNgaySinh());
            query.setParameter("sdt", kh.getSdt());
            query.setParameter("diaChi", kh.getDiaChi());
            query.setParameter("thanhPho", kh.getThanhPho());
            query.setParameter("quocGia", kh.getQuocGia());
            query.setParameter("trangThai", kh.getTrangThai());
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
        String hql = "DELETE " + fromTable
                + "WHERE ma = :ma";
        int check = 0;
        Transaction transaction = null;
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

    @Override
    public List<KhachHang> searchBySDTOrTen(String txtTimKiem) {
        List<KhachHang> listKH = new ArrayList<>();
        String hql = fromTable + " WHERE ten LIKE :tenKH OR sdt LIKE :sdtKH";
        Query query = session.createQuery(hql);
        query.setParameter("tenKH", "%" + txtTimKiem + "%");
        query.setParameter("sdtKH", "%" + txtTimKiem + "%");
        listKH = query.getResultList();
        return listKH;
    }

    @Override
    public KhachHang getOneBySdt(String sdt) {
        try {
            String hql = fromTable + "WHERE sdt = :sdt";
            Query query = session.createQuery(hql);
            query.setParameter("sdt", sdt);
            KhachHang kh = (KhachHang) query.getSingleResult();
            return kh;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int xepHangKhachHang(String idKH) {
        try (Session session = HibernateUtil.getFactory().openSession()) {
            try {
                Query query = session.createSQLQuery("Select dbo.Rank(:@idKH)");
                query.setParameter("@idKH", idKH);
                return (int) query.getSingleResult();
            } catch (Exception e) {
                return 0;
            }
        }
    }

    @Override
    public Boolean updateIdRank(String idKH, RankKhachHang idRank) {
        int check = 0;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            try {
                Query query = session.createQuery("Update KhachHang KH SET KH.RankKH.id = :IDdRank WHERE KH.id =:IDKH");
                query.setParameter("IDdRank", idRank.getId());
                query.setParameter("IDKH", idKH);
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
    
    public static void main(String[] args) {
        String r = new RankRepositoryImpl().Rank("239ED5A6-21E0-4852-8A11-D6B472AC8178");
        System.out.println(r);
        RankKhachHang rank = new RankRepositoryImpl().getOneById(r);
        System.out.println(rank.getTenRank());
        boolean updateRank = new KhachHangRepository().updateIdRank("239ED5A6-21E0-4852-8A11-D6B472AC8178",  rank);
       
        System.out.println(updateRank);
    }

}
