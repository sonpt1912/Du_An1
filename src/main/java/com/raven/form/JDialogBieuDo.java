/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.form;

import com.mycompany.customModel.SanPhamRepose;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author son45
 */
public class JDialogBieuDo extends JFrame {

    public JDialogBieuDo(List<SanPhamRepose> list) {
        initUI(list);

    }

    private void initUI(List<SanPhamRepose> list) {

        CategoryDataset dataset = createDataset(list);

        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Bar chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private CategoryDataset createDataset(List<SanPhamRepose> list) {

        var dataset = new DefaultCategoryDataset();
//        dataset.setValue(46, "Gold medals", "USA");
//        dataset.setValue(38, "Gold medals", "China");
//        dataset.setValue(29, "Gold medals", "UK");
//        dataset.setValue(22, "Gold medals", "Russia");
//        dataset.setValue(13, "Gold medals", "South Korea");
//        dataset.setValue(11, "Gold medals", "Germany");
//       
        for (SanPhamRepose x : list) {
            System.out.println(x.getMa());
            dataset.setValue(x.getSoLuong(), x.getTen(), x.getTen());
        }

        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) {

        JFreeChart barChart = ChartFactory.createBarChart(
                "BIỂU ĐỒ THỐNG KÊ SẢN PHẨM",
                "",
                "Số Lượng",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        return barChart;
    }

//    public static void main(String[] args) {
//
//        EventQueue.invokeLater(() -> {
//
//            var ex = new JDialogBieuDo();
//            ex.setVisible(true);
//        });
//    }
}
