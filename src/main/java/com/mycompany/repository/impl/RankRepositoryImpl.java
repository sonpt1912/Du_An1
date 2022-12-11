/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.domainModel.RankKhachHang;
import com.mycompany.hibernateUtil.HibernateUtil;
import com.mycompany.repository.IRankKhachHang;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author PTS
 */
public class RankRepositoryImpl implements IRankKhachHang {

    @Override
    public List<RankKhachHang> getAll() {
        List<RankKhachHang> listLoai = new ArrayList<>();
        try (Session session = HibernateUtil.getFactory().openSession()) {
            Query query = session.createQuery("FROM RankKhachHang");
            listLoai = query.getResultList();
        } finally {
            return listLoai;
        }
    }

    @Override
    public RankKhachHang getOne(String ma) {
        RankKhachHang loai = new RankKhachHang();
        try (Session session = HibernateUtil.getFactory().openSession()) {
            Query query = session.createQuery("FROM RankKhachHang WHERE tenRank = :maRank");
            query.setParameter("maRank", ma);
            loai = (RankKhachHang) query.getSingleResult();
        } finally {
            return loai;
        }
    }

    @Override
    public RankKhachHang getOneById(String id) {
        RankKhachHang loai = new RankKhachHang();
        try (Session session = HibernateUtil.getFactory().openSession()) {
            Query query = session.createQuery("FROM RankKhachHang WHERE idRank = :maRank");
            query.setParameter("maRank", id);
            loai = (RankKhachHang) query.getSingleResult();
        } finally {
            return loai;
        }
    }

    @Override
    public Boolean add(RankKhachHang kh) {
        boolean isAdd = false;
        try (Session session = HibernateUtil.getFactory().openSession()) {
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
    public Boolean update(RankKhachHang loai, int ma) {
        int check = 0;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            try {
                Query query = session.createQuery("UPDATE RankKhachHang SET tenRank = :tenRank , soTienBatDau = :soTienBatDau,"
                        + "soTienKetThuc =:soTienKetThuc,khuyenMaiRank = :khuyenMaiRank,trangThai =:trangThai"
                        + " WHERE maRank = :maRank");
                query.setParameter("tenRank", loai.getTenRank());
                query.setParameter("soTienBatDau", loai.getSoTienBatDau());
                query.setParameter("soTienKetThuc", loai.getSoTienKetThuc());
                query.setParameter("khuyenMaiRank", loai.getKhuyenMaiRank());
                query.setParameter("trangThai", loai.getTrangThai());
                query.setParameter("maRank", ma);
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
    public Boolean remove(int ma) {
        int check = 0;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            try {
                Query query = session.createQuery("UPDATE RankKhachHang SET trangThai = :trangThai "
                        + " WHERE maRank = :maRank");
                query.setParameter("trangThai", 1);
                query.setParameter("maRank", ma);
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
    public String Rank(String idKH) {
        try (Session session = HibernateUtil.getFactory().openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            try {
                Query query = session.createSQLQuery("Select dbo.Rank (:id_KH)");
                query.setParameter("id_KH", idKH);
                String rank = (String) query.getSingleResult();
                return rank;
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }

        }
    }

    @Override
    public Boolean updateRank(int ma) {
        int check = 0;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            try {
                Query query = session.createQuery("UPDATE RankKhachHang SET trangThai = :trangThai "
                        + " WHERE maRank = :maRank");
                query.setParameter("trangThai", 1);
                query.setParameter("maRank", ma);
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
        List<RankKhachHang> list = new RankRepositoryImpl().getAll();
        for (RankKhachHang rankKhachHang : list) {
            System.out.println(rankKhachHang.toString());
        }
    }
}
