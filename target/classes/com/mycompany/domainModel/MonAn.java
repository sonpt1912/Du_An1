/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.domainModel;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Mon_An")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MonAn {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "IdMonAn", columnDefinition = "uniqueidentifier", nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "IdLoai", nullable = false)
    private Loai loai;

//    @ManyToOne
//    @JoinColumn(name = "IdKM", nullable = true)
//    private KhuyenMai khuyenMai;
    @Column(name = "MaMonAn", nullable = false)
    private String maMonAn;

    @Column(name = "TenMonAn", nullable = false)
    private String tenMonAn;

    @Column(name = "HinhAnh")
    private String hinhAnh;

    @Column(name = "DonGia", nullable = false)
    private BigDecimal donGia;

    @Column(name = "DonViTinh", nullable = false)
    private String donViTinh;

    @Column(name = "TrangThai")
    private Integer trangThai;

//    @OneToMany(mappedBy = "monAn", fetch = FetchType.LAZY)
//    private List<ChiTietComBo> listCTCB;
//    
//    @OneToMany(mappedBy = "monAn", fetch = FetchType.LAZY)
//    private List<HoaDonChiTiet> listHDCT;
    public Object[] toDataRow(int stt) {
        return new Object[]{stt, maMonAn, tenMonAn, donGia + "K", donViTinh};
    }

//    public Object[] toDataRowViewKM(int stt) {
//        return new Object[]{stt, (khuyenMai == null ? "ko xác định" : khuyenMai.getMaKhuyenMai()), maMonAn, tenMonAn};
//    }
    public Object[] toDataRowViewKM(int stt) {
        return new Object[]{stt, "cái này đang bỏ để ánh xạ lại", maMonAn, tenMonAn};
    }

    public String fillTrangThai(String trangThai) {
        if (Integer.valueOf(trangThai) == 0) {
            return "Kinh doanh";
        } else if (Integer.valueOf(trangThai) == 1) {
            return "Ngừng kinh doanh";
        } else if (trangThai == null) {
            return "";
        } else {
            return "";
        }
    }

    public Object[] toDataRowThongKe(int stt) {
        return new Object[]{stt, maMonAn, tenMonAn, donGia, fillTrangThai(String.valueOf(trangThai))};
    }
}
