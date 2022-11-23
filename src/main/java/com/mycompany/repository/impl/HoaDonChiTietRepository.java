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

    public static void main(String[] args) {
//        Ban ban = new Ban();
//        ban.setId("E2379CDD-2827-4CB6-9A34-2C2103268F70");
//        MonAn monAn = new MonAn();
//        monAn.setId("F140701F-1D94-49E6-97EC-46CDAB6D9EC6");
//        HoaDon hd = new HoaDon();
//        hd.setId("ED4B2826-6CD7-4D7B-B6D5-3961C5DCD85D");
//        ComBo comBo = new ComBo();
//        comBo.setId(null);
//        HoaDonChiTiet hdct = new HoaDonChiTiet(null, ban, monAn, null, null, 10, BigDecimal.ONE);
//        boolean test = new HoaDonChiTietRepository().update(hdct,hd);
//        System.out.println(test);
//        List<HoaDonChiTiet> getAll = new HoaDonChiTietRepository().getAll();
//        for (HoaDonChiTiet hoaDonChiTiet : getAll) {
//            System.out.println(hoaDonChiTiet.toString());
//        }
        HoaDon hoaDon = new HoaDon();
        hoaDon.setId("602EA574-B3FC-4991-8243-F5BCD5FEF480");
        MonAn monAn = new MonAn();
        monAn.setId("735E2EAE-92A5-458F-8CB7-8211402B3361");
        HoaDonChiTiet hdct = new HoaDonChiTietRepository().getOneHDCTByMAHD(hoaDon, monAn);
        System.out.println(hdct.toString());
    }

    @Override
    public HoaDonChiTiet getOneCombo(HoaDon hd, ComBo combo) {
        String hql = fromTable + "WHERE hoaDon = :hd AND comBo = :comBo";
        Query query = session.createQuery(hql);
        query.setParameter("hd", hd);
        query.setParameter("comBo", combo);
        HoaDonChiTiet kh = (HoaDonChiTiet) query.getSingleResult();
        return kh;
    }

    @Override
    public HoaDonChiTiet getOneHDCTByMAHD(HoaDon hd, MonAn monAn) {
        String hql = fromTable + "WHERE hoaDon = :hd AND monAn = :MA";
        Query query = session.createQuery(hql);
        query.setParameter("hd", hd);
        query.setParameter("MA", monAn);
        HoaDonChiTiet kh = (HoaDonChiTiet) query.getSingleResult();
        return kh;
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

}
