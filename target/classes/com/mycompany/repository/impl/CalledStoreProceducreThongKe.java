/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.customModel.SanPhamRepose;
import com.mycompany.hibernateUtil.HibernateUtil;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import org.hibernate.Session;

/**
 *
 * @author son45
 */
public class CalledStoreProceducreThongKe {

    public List<SanPhamRepose> calledStore(Date ngayBatDau, Date ngayKetThuc) {
        try ( Session session = HibernateUtil.getFactory().openSession()) {
            List<SanPhamRepose> lists = new ArrayList<>();
            List<SanPhamRepose> list = new ArrayList<>();
            Query query = session.createSQLQuery("EXEC dbo.ThongKe :@NgayBatDau, :@NgayKetThuc");
            query.setParameter("@NgayBatDau", ngayBatDau);
            query.setParameter("@NgayKetThuc", ngayKetThuc);
            List<SanPhamRepose> allFoos = query.getResultList();
            Iterator itr = allFoos.iterator();
            // convert từ object sang sanPhamRepose
            while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                String ma = String.valueOf(obj[0]);
                String ten = String.valueOf(obj[1]);
                Integer soLuong = Integer.valueOf(String.valueOf(obj[2]));
                SanPhamRepose sp = new SanPhamRepose(ma, ten, soLuong);
                lists.add(sp);
            }
            // check trùng mã
            for (int i = 0; i < lists.size(); i++) {
                for (int j = i + 1; j < lists.size(); j++) {
                    if (lists.get(i).getMa().equals(lists.get(j).getMa())) {
                        lists.get(i).setSoLuong(lists.get(i).getSoLuong() + lists.get(j).getSoLuong());
                        lists.remove(j);
                    }
                }
                list.add(lists.get(i));
            }
            return list;
        }
    }

    public static void main(String[] args) {
        List<SanPhamRepose> lists = new CalledStoreProceducreThongKe().calledStore(Date.valueOf("2022-11-12"), Date.valueOf("2022-11-30"));
//        List<SanPhamRepose> list = new ArrayList<>();
//
//        lists.forEach(s -> System.out.println(s.toString()));
//        System.out.println(" ");
//        for (int i = 0; i < lists.size(); i++) {
//            for (int j = i + 1; j < lists.size(); j++) {
//                if (lists.get(i).getMa().equals(lists.get(j).getMa())) {
//                    lists.get(i).setSoLuong(lists.get(i).getSoLuong() + lists.get(j).getSoLuong());
//                    lists.remove(j);
//                }
//            }
//            list.add(lists.get(i));
//        }
//
        lists.forEach(s -> System.out.println(s.toString()));

    }
}
