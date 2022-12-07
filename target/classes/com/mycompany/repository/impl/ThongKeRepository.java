/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.domainModel.HoaDon;
import com.mycompany.domainModel.MonAn;
import com.mycompany.hibernateUtil.HibernateUtil;
import com.mycompany.repository.IThongKeRepository;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author PTS
 */
public class ThongKeRepository implements IThongKeRepository {

    private static final Session SESSION = HibernateUtil.getFactory().openSession();

    @Override
    public List<HoaDon> getAllHoaDon(int trangThai) {
        String hql = "FROM HoaDon WHERE trangThai <> :TrangThai";
        Query query = SESSION.createQuery(hql);
        query.setParameter("TrangThai", trangThai);
        List<HoaDon> hoaDons = query.getResultList();
        return hoaDons;
    }

    @Override
    public List<MonAn> getAllSanPham() {
        String hql = "FROM MonAn";
        Query query = SESSION.createQuery(hql);
        List<MonAn> monAns = query.getResultList();
        return monAns;
    }

    public static void main(String[] args) {
        String ngayThanhToan = "2022-11-25";
//        List<HoaDon> listHoaDon = new ThongKeRepository().getAllDay();
//        for (HoaDon hoaDon : listHoaDon) {
//            System.out.println(hoaDon.toString());
//        }
        long hoaDon = new ThongKeRepository().soLuongKhachHangDAY();
        System.out.println(hoaDon);
    }

    @Override
    public List<HoaDon> getAllHoaDonTrangThai(int trangThai) {
        String hql = "FROM HoaDon WHERE trangThai = :TrangThai";
        Query query = SESSION.createQuery(hql);
        query.setParameter("TrangThai", trangThai);
        List<HoaDon> hoaDons = query.getResultList();
        return hoaDons;
    }

    @Override
    public List<MonAn> getAllSanPhamTrangThai(int trangThai) {
        String hql = "FROM MonAn WHERE trangThai = :TrangThai";
        Query query = SESSION.createQuery(hql);
        query.setParameter("TrangThai", trangThai);
        List<MonAn> monAns = query.getResultList();
        return monAns;
    }

    @Override
    public long getCountAllDay() {
        String hql = "select COUNT(*) FROM HoaDon hd WHERE day(hd.ngayThanhToan) = day(sysdatetime())";
        Query query = SESSION.createQuery(hql);
        long hoaDons = (long) query.getSingleResult();
        return hoaDons;
    }

    @Override
    public long getCountAllMonth() {
        String hql = "select COUNT(*) FROM HoaDon hd WHERE month(hd.ngayThanhToan) = month(sysdatetime())";
        Query query = SESSION.createQuery(hql);
        long hoaDons = (long) query.getSingleResult();
        return hoaDons;
    }

    @Override
    public long getCountAllWeek() {
        String hql = "select COUNT(*) FROM HoaDon hd WHERE (day(datediff(d,0,hd.ngayThanhToan)/7*7)-1)/7+1 = (day(datediff(d,0,sysdatetime())/7*7)-1)/7+1";
        Query query = SESSION.createQuery(hql);
        long hoaDons = (long) query.getSingleResult();
        return hoaDons;
    }

    @Override
    public long getCountAllYear() {
        String hql = "select COUNT(*) FROM HoaDon hd WHERE Year(hd.ngayThanhToan) = Year(sysdatetime())";
        Query query = SESSION.createQuery(hql);
        long hoaDons = (long) query.getSingleResult();
        return hoaDons;
    }

    @Override
    public long getHoaDonDaTTDAY() {
        String hql = "select COUNT(*) FROM HoaDon hd WHERE day(hd.ngayThanhToan) = day(sysdatetime()) AND hd.trangThai = 1";
        Query query = SESSION.createQuery(hql);
        long hoaDons = (long) query.getSingleResult();
        return hoaDons;
    }

    @Override
    public long getHoaDonDaTTWEEK() {
        String hql = "select COUNT(*) FROM HoaDon hd WHERE (day(datediff(d,0,hd.ngayThanhToan)/7*7)-1)/7+1 = (day(datediff(d,0,sysdatetime())/7*7)-1)/7+1 AND hd.trangThai = 1";
        Query query = SESSION.createQuery(hql);
        long hoaDons = (long) query.getSingleResult();
        return hoaDons;
    }

    @Override
    public long getHoaDonDaTTMONTH() {
        String hql = "select COUNT(*) FROM HoaDon hd WHERE month(hd.ngayThanhToan) = month(sysdatetime()) AND hd.trangThai = 1";
        Query query = SESSION.createQuery(hql);
        long hoaDons = (long) query.getSingleResult();
        return hoaDons;
    }

    @Override
    public long getHoaDonDaTTYEAR() {
        String hql = "select COUNT(*) FROM HoaDon hd WHERE year(hd.ngayThanhToan) = year(sysdatetime()) AND hd.trangThai = 1";
        Query query = SESSION.createQuery(hql);
        long hoaDons = (long) query.getSingleResult();
        return hoaDons;
    }

    @Override
    public long getHoaDonHuyDAY() {
        String hql = "select COUNT(*) FROM HoaDon hd WHERE day(hd.ngayThanhToan) = day(sysdatetime()) AND hd.trangThai = 2";
        Query query = SESSION.createQuery(hql);
        long hoaDons = (long) query.getSingleResult();
        return hoaDons;
    }

    @Override
    public long getHoaDonHuyWEEK() {
        String hql = "select COUNT(*) FROM HoaDon hd WHERE (day(datediff(d,0,hd.ngayThanhToan)/7*7)-1)/7+1 = (day(datediff(d,0,sysdatetime())/7*7)-1)/7+1 AND hd.trangThai = 2";
        Query query = SESSION.createQuery(hql);
        long hoaDons = (long) query.getSingleResult();
        return hoaDons;
    }

    @Override
    public long getHoaDonHuyMONTH() {
        String hql = "select COUNT(*) FROM HoaDon hd WHERE month(hd.ngayThanhToan) = month(sysdatetime()) AND hd.trangThai = 2";
        Query query = SESSION.createQuery(hql);
        long hoaDons = (long) query.getSingleResult();
        return hoaDons;
    }

    @Override
    public long getHoaDonHuyYEAR() {
        String hql = "select COUNT(*) FROM HoaDon hd WHERE year(hd.ngayThanhToan) = year(sysdatetime()) AND hd.trangThai = 2";
        Query query = SESSION.createQuery(hql);
        long hoaDons = (long) query.getSingleResult();
        return hoaDons;
    }

    @Override
    public BigDecimal getDoanhThuDAY() {
        try {
            String hql = "SELECT SUM(hd.tongTien) FROM HoaDon hd WHERE DAY(hd.ngayThanhToan) = DAY(sysdatetime()) AND hd.trangThai = 1";
            Query query = SESSION.createQuery(hql);
            BigDecimal hoaDons = (BigDecimal) query.getSingleResult();
            return hoaDons;
        } catch (Exception e) {
            return new BigDecimal(0);
        }
    }

    @Override
    public BigDecimal getDoanhThuWEEK() {
        String hql = "SELECT SUM(hd.tongTien) FROM HoaDon hd WHERE (day(datediff(d,0,hd.ngayThanhToan)/7*7)-1)/7+1 = (day(datediff(d,0,sysdatetime())/7*7)-1)/7+1 AND hd.trangThai = 1";
        Query query = SESSION.createQuery(hql);
        BigDecimal hoaDons = (BigDecimal) query.getSingleResult();
        return hoaDons;
    }

    @Override
    public BigDecimal getDoanhThuMONTH() {
        String hql = "SELECT SUM(hd.tongTien) FROM HoaDon hd WHERE MONTH(hd.ngayThanhToan) = MONTH(sysdatetime()) AND hd.trangThai = 1";
        Query query = SESSION.createQuery(hql);
        BigDecimal hoaDons = (BigDecimal) query.getSingleResult();
        return hoaDons;
    }

    @Override
    public BigDecimal getDoanhThuYEAR() {
        String hql = "SELECT SUM(hd.tongTien) FROM HoaDon hd WHERE YEAR(hd.ngayThanhToan) = YEAR(sysdatetime()) AND hd.trangThai = 1";
        Query query = SESSION.createQuery(hql);
        BigDecimal hoaDons = (BigDecimal) query.getSingleResult();
        return hoaDons;
    }

    @Override
    public BigDecimal getDoanhThuThang1() {
        String hql = "SELECT SUM(hd.tongTien) FROM HoaDon hd WHERE MONTH(hd.ngayThanhToan) = 1 AND YEAR(hd.ngayThanhToan) = YEAR(SYSDATETIME())";
        Query query = SESSION.createQuery(hql);
        BigDecimal hoaDons = (BigDecimal) query.getSingleResult();
        return hoaDons;
    }

    @Override
    public BigDecimal getDoanhThuThang2() {
        String hql = "SELECT SUM(hd.tongTien) FROM HoaDon hd WHERE MONTH(hd.ngayThanhToan) = 2 AND YEAR(hd.ngayThanhToan) = YEAR(SYSDATETIME())";
        Query query = SESSION.createQuery(hql);
        BigDecimal hoaDons = (BigDecimal) query.getSingleResult();
        return hoaDons;
    }

    @Override
    public BigDecimal getDoanhThuThang3() {
        String hql = "SELECT SUM(hd.tongTien) FROM HoaDon hd WHERE MONTH(hd.ngayThanhToan) = 3 AND YEAR(hd.ngayThanhToan) = YEAR(SYSDATETIME())";
        Query query = SESSION.createQuery(hql);
        BigDecimal hoaDons = (BigDecimal) query.getSingleResult();
        return hoaDons;
    }

    @Override
    public BigDecimal getDoanhThuThang4() {
        String hql = "SELECT SUM(hd.tongTien) FROM HoaDon hd WHERE MONTH(hd.ngayThanhToan) = 4 AND YEAR(hd.ngayThanhToan) = YEAR(SYSDATETIME())";
        Query query = SESSION.createQuery(hql);
        BigDecimal hoaDons = (BigDecimal) query.getSingleResult();
        return hoaDons;
    }

    @Override
    public BigDecimal getDoanhThuThang5() {
        String hql = "SELECT SUM(hd.tongTien) FROM HoaDon hd WHERE MONTH(hd.ngayThanhToan) = 5 AND YEAR(hd.ngayThanhToan) = YEAR(SYSDATETIME())";
        Query query = SESSION.createQuery(hql);
        BigDecimal hoaDons = (BigDecimal) query.getSingleResult();
        return hoaDons;
    }

    @Override
    public BigDecimal getDoanhThuThang6() {
        String hql = "SELECT SUM(hd.tongTien) FROM HoaDon hd WHERE MONTH(hd.ngayThanhToan) = 6 AND YEAR(hd.ngayThanhToan) = YEAR(SYSDATETIME())";
        Query query = SESSION.createQuery(hql);
        BigDecimal hoaDons = (BigDecimal) query.getSingleResult();
        return hoaDons;
    }

    @Override
    public BigDecimal getDoanhThuThang7() {
        String hql = "SELECT SUM(hd.tongTien) FROM HoaDon hd WHERE MONTH(hd.ngayThanhToan) = 7 AND YEAR(hd.ngayThanhToan) = YEAR(SYSDATETIME())";
        Query query = SESSION.createQuery(hql);
        BigDecimal hoaDons = (BigDecimal) query.getSingleResult();
        return hoaDons;
    }

    @Override
    public BigDecimal getDoanhThuThang8() {
        String hql = "SELECT SUM(hd.tongTien) FROM HoaDon hd WHERE MONTH(hd.ngayThanhToan) = 8 AND YEAR(hd.ngayThanhToan) = YEAR(SYSDATETIME())";
        Query query = SESSION.createQuery(hql);
        BigDecimal hoaDons = (BigDecimal) query.getSingleResult();
        return hoaDons;
    }

    @Override
    public BigDecimal getDoanhThuThang9() {
        String hql = "SELECT SUM(hd.tongTien) FROM HoaDon hd WHERE MONTH(hd.ngayThanhToan) = 9 AND YEAR(hd.ngayThanhToan) = YEAR(SYSDATETIME())";
        Query query = SESSION.createQuery(hql);
        BigDecimal hoaDons = (BigDecimal) query.getSingleResult();
        return hoaDons;
    }

    @Override
    public BigDecimal getDoanhThuThang10() {
        String hql = "SELECT SUM(hd.tongTien) FROM HoaDon hd WHERE MONTH(hd.ngayThanhToan) = 10 AND YEAR(hd.ngayThanhToan) = YEAR(SYSDATETIME())";
        Query query = SESSION.createQuery(hql);
        BigDecimal hoaDons = (BigDecimal) query.getSingleResult();
        return hoaDons;
    }

    @Override
    public BigDecimal getDoanhThuThang11() {
        String hql = "SELECT SUM(hd.tongTien) FROM HoaDon hd WHERE MONTH(hd.ngayThanhToan) = 11 AND YEAR(hd.ngayThanhToan) = YEAR(SYSDATETIME())";
        Query query = SESSION.createQuery(hql);
        BigDecimal hoaDons = (BigDecimal) query.getSingleResult();
        return hoaDons;
    }

    @Override
    public BigDecimal getDoanhThuThang12() {
        String hql = "SELECT SUM(hd.tongTien) FROM HoaDon hd WHERE MONTH(hd.ngayThanhToan) = 12 AND YEAR(hd.ngayThanhToan) = YEAR(SYSDATETIME())";
        Query query = SESSION.createQuery(hql);
        BigDecimal hoaDons = (BigDecimal) query.getSingleResult();
        return hoaDons;
    }

    @Override
    public long soLuongKhachHangDAY() {
        try {
            String hql = "select SUM(hd.soLuongKhach) FROM HoaDon hd WHERE hd.trangThai =1 AND day(hd.ngayThanhToan) = day(sysdatetime())";
            Query query = SESSION.createQuery(hql);
            long hoaDons = (long) query.getSingleResult();
            return hoaDons;
        } catch (Exception e) {
            return 0;
        }

    }

    @Override
    public long soLuongKhachHangWEEK() {
        try {
            String hql = "select SUM(hd.soLuongKhach) FROM HoaDon hd WHERE hd.trangThai =1 AND (day(datediff(d,0,hd.ngayThanhToan)/7*7)-1)/7+1 = (day(datediff(d,0,sysdatetime())/7*7)-1)/7+1";
            Query query = SESSION.createQuery(hql);
            long hoaDons = (long) query.getSingleResult();
            return hoaDons;
        } catch (Exception e) {
            return 0;
        }

    }

    @Override
    public long soLuongKhachHangMONTH() {
        try {
            String hql = "select SUM(hd.soLuongKhach) FROM HoaDon hd WHERE hd.trangThai =1 AND month(hd.ngayThanhToan) = month(sysdatetime())";
            Query query = SESSION.createQuery(hql);
            long hoaDons = (long) query.getSingleResult();
            return hoaDons;
        } catch (Exception e) {
            return 0;
        }

    }

    @Override
    public long soLuongKhachHangYEAR() {
        try {
            String hql = "select SUM(hd.soLuongKhach) FROM HoaDon hd WHERE hd.trangThai= 1 AND year(hd.ngayThanhToan) = year(sysdatetime())";
            Query query = SESSION.createQuery(hql);
            long hoaDons = (long) query.getSingleResult();
            return hoaDons;
        } catch (Exception e) {
            return 0;
        }

    }

}
