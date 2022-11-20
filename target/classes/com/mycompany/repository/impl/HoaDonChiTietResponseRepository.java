/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.customModel.HoaDonChiTietResponse;
import com.mycompany.domainModel.HoaDon;
import com.mycompany.hibernateUtil.HibernateUtil;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import com.mycompany.repository.IHoaDonChiTietResponseRepository;

/**
 *
 * @author Admin
 */
public class HoaDonChiTietResponseRepository implements IHoaDonChiTietResponseRepository<HoaDonChiTietResponse, HoaDon> {

    private static final Session session = HibernateUtil.getFactory().openSession();
    private String fromTable = " FROM HoaDonChiTiet HDCT ";

    @Override
    public List<HoaDonChiTietResponse> getAll(HoaDon hd) {
        String hql = "SELECT new com.mycompany.customModel.HoaDonChiTietResponse(MA.maMonAn,MA.tenMonAn,"
                + "HDCT.donGiaMonAn,HDCT.soLuongMonAn,CB.maCB,CB.tenCB,HDCT.donGiaCombo,HDCT.soLuongCombo)"
                + fromTable + "LEFT JOIN ComBo CB ON CB.id = HDCT.comBo LEFT JOIN MonAn MA ON HDCT.monAn = MA.id "
                + "WHERE HDCT.hoaDon = :hd";
        Query query = session.createQuery(hql);
        query.setParameter("hd", hd);
        List<HoaDonChiTietResponse> hoaDonChiTietResponses = query.getResultList();
        return hoaDonChiTietResponses;
    }

    public static void main(String[] args) {
        HoaDon hd = new HoaDon();
        hd.setId("88210A1C-1074-46CD-960A-5489EEEA8274");
        List<HoaDonChiTietResponse> chiTietResponses = new HoaDonChiTietResponseRepository().getAll(hd);
        for (HoaDonChiTietResponse chiTietResponse : chiTietResponses) {
            System.out.println(chiTietResponse.toString());
        }
    }

}
