/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.domainModel.Ban;
import com.mycompany.domainModel.ComBo;
import com.mycompany.domainModel.HoaDon;
import com.mycompany.domainModel.HoaDonChiTiet;
import com.mycompany.domainModel.MonAn;
import com.mycompany.hibernateUtil.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.mycompany.repository.IHoaDonChiTietRepository;

/**
 *
 * @author Admin
 */
public class HoaDonChiTietRepository implements IHoaDonChiTietRepository<HoaDonChiTiet, Boolean, String, HoaDon, ComBo, MonAn> {

    private static final Session session = HibernateUtil.getFactory().openSession();
    private String fromTable = "FROM HoaDonChiTiet ";

    @Override
    public List<HoaDonChiTiet> getAll() {
        Query query = session.createQuery(fromTable);
        List<HoaDonChiTiet> hoaDonChiTiets = query.getResultList();
        return hoaDonChiTiets;
    }

    @Override
    public Boolean add(HoaDonChiTiet kh) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(kh);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean update(HoaDonChiTiet kh, HoaDon hd) {
        Transaction transaction = null;
        String hql = "UPDATE " + fromTable + "SET monAn = :monAn,soLuongMonAn = :soLuongMonAn,donGiaMonAn = :donGiaMonAn,"
                + " comBo = :comBo, soLuongCombo = :soLuongCombo, donGiaCombo = :donGiaCombo, ghiChu = :ghiChu "
                + "WHERE hoaDon = :hoaDon";
        int check = 0;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("monAn", kh.getMonAn());
            query.setParameter("soLuongMonAn", kh.getSoLuongMonAn());
            query.setParameter("donGiaMonAn", kh.getDonGiaMonAn());
            query.setParameter("comBo", kh.getComBo());
            query.setParameter("soLuongCombo", kh.getSoLuongCombo());
            query.setParameter("donGiaCombo", kh.getDonGiaCombo());
            query.setParameter("ghiChu", kh.getGhiChu());
            query.setParameter("hoaDon", hd);
            check = query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public Boolean remove(HoaDon hd) {
        String hql = "DELETE " + fromTable + "WHERE hoaDon = :hd";
        int check = 0;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("hd", hd);
            check = query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public BigDecimal tinhTongTien(List<HoaDonChiTiet> listHDCT) {
        double tongTien = 0;
        double tienMonAn = 0;
        double tienCombo = 0;
        for (HoaDonChiTiet hdct : listHDCT) {
            String dongiaMonAn = String.valueOf(hdct.getDonGiaMonAn());
            String donGiaCombo = String.valueOf(hdct.getDonGiaCombo());
            tienMonAn += (Double.valueOf(dongiaMonAn)) * hdct.getSoLuongMonAn();
            tienCombo += (Double.valueOf(donGiaCombo)) * hdct.getSoLuongCombo();
        }
        tongTien = tienMonAn + tienCombo;
        return BigDecimal.valueOf(tongTien);
    }

    @Override
    public HoaDonChiTiet getOneCombo(HoaDon hd, ComBo combo) {
//        String hql = fromTable + "WHERE hoaDon = :hd AND comBo = :comBo";
//        Query query = session.createQuery(hql);
//        query.setParameter("hd", hd);
//        query.setParameter("comBo", combo);
//        HoaDonChiTiet kh = (HoaDonChiTiet) query.getSingleResult();
//        return kh;
        try {
            String hql = fromTable + "WHERE hoaDon = :hd AND comBo = :comBo";
            Query query = session.createQuery(hql);
            query.setParameter("hd", hd);
            query.setParameter("comBo", combo);
            HoaDonChiTiet kh = (HoaDonChiTiet) query.getSingleResult();
            return kh;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public HoaDonChiTiet getOneHDCTByMAHD(HoaDon hd, MonAn monAn) {
        try {
            String hql = fromTable + "WHERE hoaDon = :hd AND monAn = :MA";
            Query query = session.createQuery(hql);
            query.setParameter("hd", hd);
            query.setParameter("MA", monAn);
            HoaDonChiTiet kh = (HoaDonChiTiet) query.getSingleResult();
            return kh;
        } catch (Exception e) {
            return null;
        }
    }

    public List<HoaDonChiTiet> getHDCTByHD(HoaDon hoaDon) {
        Query query = session.createQuery(fromTable + " WHERE hoaDon = :hoaDon");
        query.setParameter("hoaDon", hoaDon);
        List<HoaDonChiTiet> hoaDonChiTiets = query.getResultList();
        return hoaDonChiTiets;
    }

    @Override
    public Boolean updateHDCTById(HoaDonChiTiet kh, String idHDCT) {
        Transaction transaction = null;
        String hql = "UPDATE " + fromTable + "SET monAn = :monAn,soLuongMonAn = :soLuongMonAn,donGiaMonAn = :donGiaMonAn,"
                + " comBo = :comBo, soLuongCombo = :soLuongCombo, donGiaCombo = :donGiaCombo, ghiChu = :ghiChu "
                + "WHERE id = :idHDCT";
        int check = 0;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("monAn", kh.getMonAn());
            query.setParameter("soLuongMonAn", kh.getSoLuongMonAn());
            query.setParameter("donGiaMonAn", kh.getDonGiaMonAn());
            query.setParameter("comBo", kh.getComBo());
            query.setParameter("soLuongCombo", kh.getSoLuongCombo());
            query.setParameter("donGiaCombo", kh.getDonGiaCombo());
            query.setParameter("ghiChu", kh.getGhiChu());
            query.setParameter("idHDCT", idHDCT);
            check = query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public static void main(String[] args) {
        HoaDon hd = new HoaDon();
        hd.setId("CF2EB6DC-2CA6-415E-8C28-5ACEE378AEC0");
        ComBo cb = new ComBo();
        cb.setId("1AEBBB55-C1B7-43C1-A494-FBECCC087782");
        HoaDonChiTiet hdct = new HoaDonChiTietRepository().getOneCombo(hd, cb);
        System.out.println(hdct);
    }

    @Override
    public Boolean updateSoLuongCombo(HoaDonChiTiet HDCT, HoaDon hd, ComBo combo) {
        Transaction transaction = null;
        String hql = "UPDATE " + "FROM HoaDonChiTiet HDCT " + "SET HDCT.soLuongCombo = :soLuongCombo, HDCT.ghiChu = :ghiChu "
                + "WHERE HDCT.hoaDon = :hoaDon AND HDCT.comBo = :combo";
        int check = 0;
        try {
            transaction = session.beginTransaction();
            session.clear();
            Query query = session.createQuery(hql);
            query.setParameter("soLuongCombo", HDCT.getSoLuongCombo());
            query.setParameter("ghiChu", HDCT.getGhiChu());
            query.setParameter("hoaDon", hd);
            query.setParameter("combo", combo);
            check = query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public Boolean updateSoLuongMonAn(HoaDonChiTiet HDCT, HoaDon hd, MonAn MonAn) {
        Transaction transaction = null;
        String hql = "UPDATE " + "FROM HoaDonChiTiet HDCT " + "SET HDCT.soLuongMonAn = :soLuongMonAn, HDCT.ghiChu = :ghiChu "
                + "WHERE HDCT.hoaDon = :hoaDon AND HDCT.monAn = :monAn";
        int check = 0;
        try {
            transaction = session.beginTransaction();
            session.clear();
            Query query = session.createQuery(hql);
            query.setParameter("soLuongMonAn", HDCT.getSoLuongMonAn());
            query.setParameter("ghiChu", HDCT.getGhiChu());
            query.setParameter("hoaDon", hd);
            query.setParameter("monAn", MonAn);
            check = query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public Boolean removeMonAn(HoaDon hd, MonAn MonAn) {
        String hql = "DELETE " + fromTable + "WHERE hoaDon = :hd AND monAn = :monAn";
        int check = 0;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("hd", hd);
            query.setParameter("monAn", MonAn);
            check = query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public Boolean removeCombo(HoaDon hd, ComBo Combo) {
        String hql = "DELETE " + fromTable + "WHERE hoaDon = :hd AND comBo = :comBo";
        int check = 0;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("hd", hd);
            query.setParameter("comBo", Combo);
            check = query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

}
