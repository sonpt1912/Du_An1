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
 * @author son45
 */
@Entity
@Table(name = "Loai")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Loai {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "IdLoai", columnDefinition = "uniqueidentifier", nullable = false)
    private String idLoai;

    @ManyToOne()
    @JoinColumn(name = "IdDanhMuc", nullable = false)
    private DanhMuc danhMuc;

    @Column(name = "MaLoai", nullable = false)
    private String maLoai;

    @Column(name = "TenLoai", nullable = false)
    private String tenLoai;

    @Column(name = "TrangThai", nullable = true)
    private int trangThai;

//    @Override
//    public String toString() {
//        return "Loai{" + "idLoai=" + idLoai + ", maLoai=" + maLoai + ", tenLoai=" + tenLoai + ", trangThai=" + trangThai + '}';
//    }
}
