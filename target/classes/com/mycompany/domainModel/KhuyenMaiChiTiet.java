/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.domainModel;

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

/**
 *
 * @author Duongntt
 */
@Entity
@Table(name = "Khuyen_Mai_Chi_Tiet")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class KhuyenMaiChiTiet {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "Id", columnDefinition = "uniqueidentifier", nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "IdMonAn")
    private MonAn monAn;

    @ManyToOne
    @JoinColumn(name = "IdKM")
    private MonAn khuyenMai;

    @Column(name = "GhiChu", nullable = false)
    private Integer ghiChu;

}
