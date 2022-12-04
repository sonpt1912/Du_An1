/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.domainModel;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
//import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Hoa_Don")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HoaDon {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "IdHD", columnDefinition = "uniqueidentifier", nullable = false)
    private String id;

    @Column(name = "MaHD", nullable = false)
    private String maHoaDon;

    @ManyToOne
    @JoinColumn(name = "IdNV", nullable = false)
    private NhanVien nhanVien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdKH", nullable = true)
    private KhachHang khachHang;

//    @ManyToOne
//    @JoinColumn(name = "IdBan", nullable = false)
//    private Ban ban;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "NgayTao", nullable = false)
    private java.util.Date ngayTao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "NgayThanhToan")
    private java.util.Date ngayThanhToan;

    @Column(name = "TongTien")
    private BigDecimal tongTien;

    @Column(name = "GiamGia")
    private BigDecimal tienDuocGiam;

    @Column(name = "GhiChu")
    private String ghiChu;

    @Column(name = "TrangThai")
    private Integer trangThai;

//    @OneToMany(mappedBy = "hoaDon", fetch = FetchType.LAZY)
//    private List<HoaDonChiTiet> listHDCT;
    public Object[] toDataRow() {
        return new Object[]{maHoaDon, nhanVien.getId(), khachHang.getId(), new SimpleDateFormat(), ngayThanhToan, tongTien, ghiChu};
    }
//    private String checkNgayTao(java.util.Date ngayTao){
//        if (ngayTao==null) {
//            
//        }
//    }

    public String fillTrangThai(String trangThai) {
        if (Integer.valueOf(trangThai) == 0) {
            return "Chưa thanh toán";
        } else if (Integer.valueOf(trangThai) == 1) {
            return "Đã thanh toán";
        } else if (Integer.valueOf(trangThai) == 3) {
            return "Đã huỷ";
        } else if (trangThai == null) {
            return "";
        } else {
            return "";
        }
    }

    public Object[] toDataRowViewHoaDon() {
        return new Object[]{maHoaDon, (nhanVien != null ? nhanVien.getMa() : " "), (khachHang != null ? khachHang.getMa() : " "),
            new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(ngayTao), ngayThanhToan==null?"":new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(ngayThanhToan), ghiChu, (trangThai != null ? fillTrangThai(String.valueOf(trangThai)) : "")};
    }

    public Object[] toDataRowThongKe(int stt) {
        return new Object[]{stt, maHoaDon, ngayTao, tongTien, fillTrangThai(String.valueOf(trangThai))};
    }
}
