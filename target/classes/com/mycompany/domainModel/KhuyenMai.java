/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.domainModel;

import java.math.BigDecimal;
//import java.sql.Date;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
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

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "Khuyen_Mai")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class KhuyenMai {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "IdKM", columnDefinition = "uniqueidentifier", nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "IdNV", nullable = false)
    private NhanVien nhanVien;

    @Column(name = "MaKM", nullable = false)
    private String maKhuyenMai;

    @Column(name = "TenKM", nullable = false)
    private String tenKhuyenMai;

//    @Column(name = "ThoiGianBD", nullable = false)
//    private Date thoiGianBD;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ThoiGianBD", nullable = false)
    private Date thoiGianBD;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ThoiGianKT", nullable = false)
    private Date thoiGianKT;

    @Column(name = "LoaiKM", nullable = false)
    private String loaiKhuyenMai;

    @Column(name = "GtriKM", nullable = false)
    private BigDecimal giaTriKM;

    @Column(name = "GhiChu")
    private String ghiChu;

    @Column(name = "TrangThai")
    private Integer trangThai;

    private String trangThaiKM(int trangThai) {
        if (trangThai == 0) {
            return "Áp dụng";
        } else if (trangThai == 1) {
            return "Ngừng áp dụng";
        } else {
            return "Không trong thời gian áp dụng thời gian áp dụng";
        }
    }

    public Object[] toDataRowViewKM(int stt) {
        return new Object[]{stt, maKhuyenMai, tenKhuyenMai, trangThaiKM(this.trangThai), (ghiChu.isEmpty() ? "" : ghiChu)};
    }

}
