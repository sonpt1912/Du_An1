/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.domainModel.HoaDon;
import com.mycompany.hibernateUtil.HibernateUtil;
import com.mycompany.repository.ICommonRepository;
import com.mycompany.repository.IHoaDonRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Admin
 */
public class HoaDonRepository implements ICommonRepository<HoaDon, Boolean, String>, IHoaDonRepository {

    private static final Session session = HibernateUtil.getFactory().openSession();
    private String fromTable = "FROM HoaDon ";

    @Override
    public List<HoaDon> getAll() {
        String hql = fromTable;
        Query query = session.createQuery(hql);
        List<HoaDon> hoaDons = query.getResultList();
        return hoaDons;
    }

    @Override
    public HoaDon getOne(String ma) {
        String hql = fromTable + "WHERE maHoaDon = :ma";
        Query query = session.createQuery(hql);
        query.setParameter("ma", ma);
        HoaDon hd = (HoaDon) query.getSingleResult();
        return hd;
    }

    @Override
    public Boolean add(HoaDon kh) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(kh);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();;
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean update(HoaDon kh, String ma) {
        String hql = "UPDATE " + fromTable + "SET nhanVien = :nhanVien, khachHang = :khachHang, ngayTao = :ngayTao, ngayThanhToan = :ngayThanhToan,ngayHuy = :ngayHuy, "
                + "tongTien = :tongTien, ghiChu = :ghiTru,soLuongKhach = :soLuongKH, trangThai = :trangThai, tienDuocGiam = :tienDuocGiam,"
                + " phanTramGiamGia = :phanTram, thueVAT = :VAT, tongTienCanTT = :tienCanTT  "
                + "WHERE maHoaDon = :maHoaDon";
        Transaction transaction = null;
        int check = 0;
        try {
            transaction = session.beginTransaction();
            session.clear();
            Query query = session.createQuery(hql);
            query.setParameter("nhanVien", kh.getNhanVien());
            query.setParameter("khachHang", kh.getKhachHang());
            query.setParameter("ngayTao", kh.getNgayTao());
            query.setParameter("ngayThanhToan", kh.getNgayThanhToan());
            query.setParameter("ngayHuy", kh.getNgayHuy());
            query.setParameter("tongTien", kh.getTongTien());
            query.setParameter("ghiTru", kh.getGhiChu());
            query.setParameter("trangThai", kh.getTrangThai());
            query.setParameter("soLuongKH", kh.getSoLuongKhach());
            query.setParameter("tienDuocGiam", kh.getTienDuocGiam());
            query.setParameter("phanTram", kh.getPhanTramGiamGia());
            query.setParameter("VAT", kh.getThueVAT());
            query.setParameter("tienCanTT", kh.getTongTienCanTT());
            //query.setParameter("ban", kh.getBan());
            query.setParameter("maHoaDon", ma);
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
        String hql = "UPDATE " + fromTable + "SET trangThai = 3"
                + "WHERE maHoaDon = :maHoaDon";
        Transaction transaction = null;
        int check = 0;
        try {
            transaction = session.beginTransaction();
            session.clear();
            Query query = session.createQuery(hql);
            query.setParameter("maHoaDon", ma);
            check = query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return check > 0;
    }

//    public BigDecimal getTongTien1HD(HoaDon hoaDon) {
//        BigDecimal tongHD = BigDecimal.valueOf(0);
//        try ( Session session = HibernateUtil.getFactory().openSession()) {
//            String hql = "SELECT ((SUM(hdct.SoLuongMonAn * hdct.DonGiaMonAn)) + (SUM(hdct.SoLuongCombo * hdct.DonGiaCombo))) \n"
//                    + "FROM Hoa_Don_Chi_Tiet hdct WHERE hdct.IdHD = :idHoaDon";
//            Query query = session.createQuery(hql);
//            query.setParameter("idHoaDon", hoaDon.getId());
//          
//        } finally {
//            return tongHD;
//        }
//    }
    // hàm để check bàn đã có hoá đơn chờ hay chưa
    @Override
    public List<HoaDon> getHDByTrangThai(int trangThaiHD) {
        String hql = fromTable + " WHERE trangThai = :trangThaiHD ORDER BY ngayTao DESC";
        Query query = session.createQuery(hql);
        query.setParameter("trangThaiHD", trangThaiHD);
        List<HoaDon> hoaDons = query.getResultList();
        return hoaDons;
    }

    @Override
    public List<HoaDon> getHDChoByMaBan(int maBan) {
        List<HoaDon> listHD = new ArrayList<>();
        String hql = "FROM HoaDon HD JOIN ChiTietBanHoaDon ctBan_HD ON HD.id = ctBan_HD.hd.id "
                + " JOIN Ban b ON ctBan_HD.ban.id = b.id "
                + "WHERE HD.trangThai = 0 AND b.maBan = :maBan";
        Query query = session.createQuery(hql);
        query.setParameter("maBan", maBan);
        listHD = query.getResultList();
        return listHD;
    }

    @Override
    public List<HoaDon> getHoaDonsHomNay(java.util.Date today) {
        String hql = fromTable + " WHERE DAY(NgayTao) = DAY(:ngayTaoHD) "
                + "and month(ngayTao) = MONTH(:ngayTaoHD) and YEAR(NgayTao) = YEAR(:ngayTaoHD) ORDER By ngayTao DESC";
        Query query = session.createQuery(hql);
        query.setParameter("ngayTaoHD", today);
        List<HoaDon> hoaDons = query.getResultList();
        return hoaDons;
    }

    @Override
    public List<HoaDon> getHoaDonsKhoangNgay(java.util.Date today1, java.util.Date today2) {
        String hql = fromTable + " WHERE DAY(ngayTao) >= DAY(:ngayTao1) AND MONTH(ngayTao)>= MONTH(:ngayTao1) "
                + "AND YEAR(ngayTao)>= YEAR(:ngayTao1) AND DAY(ngayTao) <= DAY(:ngayTao2) "
                + "AND MONTH(ngayTao) <= MONTH(:ngayTao2) AND YEAR(ngayTao) <= YEAR(:ngayTao2) "
                + "ORDER By ngayTao DESC";
        Query query = session.createQuery(hql);
        query.setParameter("ngayTao1", today1);
        query.setParameter("ngayTao2", today2);
        List<HoaDon> hoaDons = query.getResultList();
        return hoaDons;
    }

//    public static void main(String[] args) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        java.util.Date today = new java.util.Date();
//        List<HoaDon> list = new HoaDonRepository().getHoaDonsHomNay("2022-12-01");
//        for (HoaDon hoaDon : list) {
//            System.out.println(hoaDon.toString());
//        }
//    }
    @Override
    public List<HoaDon> getHoaDonsCoGiamGia() {
        String hql = fromTable + " WHERE tienDuocGiam > 0 ORDER BY ngayThanhToan";
        Query query = session.createQuery(hql);
        List<HoaDon> hoaDons = query.getResultList();
        return hoaDons;
    }
}
