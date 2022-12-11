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
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Duongntt
 */
@Entity
@Table(name = "Rank_Khach_Hang")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RankKhachHang {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "IdRank", columnDefinition = "uniqueidentifier", nullable = false)
    private String id;

    @Column(name = "maRank", nullable = false)
    private String maRank;

    @Column(name = "TenRank", nullable = false)
    private String tenRank;

    @Column(name = "soTienBatDau", nullable = false)
    private BigDecimal SoTienBatDau;

    @Column(name = "soTienKetThuc", nullable = false)
    private BigDecimal soTienKetThuc;

    @Column(name = "KhuyenMaiRank", nullable = false)
    private int khuyenMaiRank;

    @Column(name = "TrangThai", nullable = false)
    private int trangThai;

}
