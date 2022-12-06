/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.customModel.MonAnResponse;
import com.mycompany.hibernateUtil.HibernateUtil;
import com.mycompany.repository.IMonAnResponseRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
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
        List<MonAnResponse> list = new MonAnResponseRepository().getByDanhMucAndGiaMon(new BigDecimal(10), "Đồ ăn");
        for (MonAnResponse monAnResponse : list) {
            System.out.println(monAnResponse.toString());
        }
    }
//

    @Override
    public List<MonAnResponse> getByDanhMuc(String tenDanhMuc) {
        String hql = "SELECT new com.mycompany.customModel.MonAnResponse(MA.maMonAn,MA.tenMonAn,MA.donGia,MA.donGia,MA.donViTinh,MA.loai.tenLoai)" + fromTable
                + " AND MA.loai.danhMuc.tenDanhMuc = :tenDanhMuc";
        Query query = session.createQuery(hql);
        query.setParameter("tenDanhMuc", tenDanhMuc);
        List<MonAnResponse> monAnResponses = query.getResultList();
        return monAnResponses;
    }

    @Override
    public List<MonAnResponse> getByDanhMucAndTenMonAn(String tenMonAn, String tenDanhMuc) {
        String hql = "SELECT new com.mycompany.customModel.MonAnResponse(MA.maMonAn,MA.tenMonAn,MA.donGia,MA.donViTinh,MA.loai.tenLoai)" + fromTable
                + " AND MA.loai.danhMuc.tenDanhMuc = :tenDanhMuc AND MA.tenMonAn like :tenMonAn OR MA.maMonAn LIKE :tenMonAn OR MA.loai.tenLoai LIKE :tenMonAn";
        Query query = session.createQuery(hql);
        query.setParameter("tenDanhMuc", tenDanhMuc);
        query.setParameter("tenMonAn", "%" + tenMonAn + "%");
        List<MonAnResponse> monAnResponses = query.getResultList();
        return monAnResponses;
    }

    public List<MonAnResponse> getAllTest() {
        String hql = "SELECT new com.mycompany.customModel.MonAnResponse(MA.maMonAn,MA.tenMonAn,MA.donGia,KMCT.donGiaSauKM,MA.donViTinh,MA.loai.tenLoai) "
                + "FROM MonAn MA LEFT JOIN KhuyenMaiChiTiet KMCT ON MA.id=KMCT.monAn.id LEFT JOIN KhuyenMai KM ON KM.id = KMCT.khuyenMai.id ";
        Query query = session.createQuery(hql);
        List<MonAnResponse> monAnResponses = query.getResultList();
        return monAnResponses;
    }

    @Override
    public List<MonAnResponse> getMonAnJoinKMCT(String tenDanhMuc) {
        List<MonAnResponse> list = new ArrayList<>();
        Query query = session.createQuery("SELECT new com.mycompany.customModel.MonAnResponse(MA.maMonAn,MA.tenMonAn, "
                + "MA.donGia,KMCT.donGiaSauKM,MA.donViTinh,MA.loai.tenLoai)"
                + " FROM MonAn MA INNER JOIN KhuyenMaiChiTiet KMCT ON MA.id = KMCT.monAn.id\n"
                + "INNER JOIN Loai L ON MA.loai.idLoai = L.idLoai "
                + "INNER JOIN DanhMuc DM ON DM.idDanhMuc = L.danhMuc.idDanhMuc "
                + " INNER JOIN KhuyenMai KM ON KMCT.khuyenMai.id = KM.id \n"
                + "WHERE MA.trangThai = 0 AND KMCT.trangThai = 0 AND KM.trangThai = 0 \n"
                + "AND DM.tenDanhMuc = :tenDM");
        query.setParameter("tenDM", tenDanhMuc);
        list = query.getResultList();
        return list;
    }

    @Override
    public List<MonAnResponse> getByDanhMucAndGiaMon(BigDecimal donGia, String tenDanhMuc) {
        String hql = "SELECT new com.mycompany.customModel.MonAnResponse(MA.maMonAn,MA.tenMonAn,MA.donGia,MA.donViTinh,MA.loai.tenLoai)" + fromTable
                + " AND MA.loai.danhMuc.tenDanhMuc = :tenDanhMuc AND MA.donGia = :donGia";
        Query query = session.createQuery(hql);
        query.setParameter("tenDanhMuc", tenDanhMuc);
        query.setParameter("donGia", donGia);
        List<MonAnResponse> monAnResponses = query.getResultList();
        return monAnResponses;
    }

}
