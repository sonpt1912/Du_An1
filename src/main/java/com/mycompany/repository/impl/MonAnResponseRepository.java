/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.customModel.MonAnResponse;
import com.mycompany.hibernateUtil.HibernateUtil;
import com.mycompany.repository.ICommonResponseRepository;
import com.mycompany.repository.IMonAnResponseRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class MonAnResponseRepository implements IMonAnResponseRepository<MonAnResponse, String> {

    private static final Session session = HibernateUtil.getFactory().openSession();
    private String fromTable = " FROM MonAn MA WHERE trangThai = 0";

    @Override
    public List<MonAnResponse> getAll() {
        String hql = "SELECT new com.mycompany.customModel.MonAnResponse(MA.maMonAn,MA.tenMonAn,MA.donGia,MA.donViTinh,MA.loai.tenLoai)" + fromTable;
        Query query = session.createQuery(hql);
        List<MonAnResponse> monAnResponses = query.getResultList();
        return monAnResponses;
    }

    public static void main(String[] args) {
        List<MonAnResponse> test = new MonAnResponseRepository().getByDanhMucAndTenMonAn("Đồ uống",);
        for (MonAnResponse monAnResponse : test) {
            System.out.println(test);
        }
    }

    @Override
    public List<MonAnResponse> getByDanhMuc(String tenDanhMuc) {
        String hql = "SELECT new com.mycompany.customModel.MonAnResponse(MA.maMonAn,MA.tenMonAn,MA.donGia,MA.donViTinh,MA.loai.tenLoai)" + fromTable
                + " AND MA.loai.danhMuc.tenDanhMuc = :tenDanhMuc";
        Query query = session.createQuery(hql);
        query.setParameter("tenDanhMuc", tenDanhMuc);
        List<MonAnResponse> monAnResponses = query.getResultList();
        return monAnResponses;
    }

    @Override
    public List<MonAnResponse> getByDanhMucAndTenMonAn(String tenMonAn, String tenDanhMuc) {
        String hql = "SELECT new com.mycompany.customModel.MonAnResponse(MA.maMonAn,MA.tenMonAn,MA.donGia,MA.donViTinh,MA.loai.tenLoai)" + fromTable
                + " AND MA.loai.danhMuc.tenDanhMuc = :tenDanhMuc AND MA.tenMonAn like :tenMonAn";
        Query query = session.createQuery(hql);
        query.setParameter("tenDanhMuc", tenDanhMuc);
        query.setParameter("tenMonAn", "%" + tenMonAn + "%");
        List<MonAnResponse> monAnResponses = query.getResultList();
        return monAnResponses;
    }

}
