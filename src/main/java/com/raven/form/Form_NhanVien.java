/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.form;

import com.mycompany.domainModel.ChucVu;
import com.mycompany.domainModel.NhanVien;
import com.mycompany.service.impl.ChucVuService;
import com.mycompany.service.impl.NhanVienService;
import com.mycompany.util.NhanVienUtil;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 *
 * @author RAVEN
 */
public class Form_NhanVien extends javax.swing.JPanel implements Runnable, ThreadFactory {

    // gọi service
    private NhanVienService nhanVienService = new NhanVienService();
    private ChucVuService chucVuService = new ChucVuService();
    // tạo list thực thể
    private List<NhanVien> listNhanVien = new ArrayList<>();
    private List<ChucVu> listChucVu = new ArrayList<>();
    // tạo model view table
    private DefaultTableModel dtmNhanVien = new DefaultTableModel();
    // tạo model view combobox
    private DefaultComboBoxModel dcbmMaCV = new DefaultComboBoxModel();
    // tạo thực thể
    private NhanVien nv;
    private ChucVu chucVu;
    // util
    private NhanVienUtil nhanVienUtil = new NhanVienUtil();
    // format ngày
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private java.util.Date today = new java.util.Date();
    // camera
    private Webcam webcam = null;
    private WebcamPanel panel = null;
    private Executor executor = Executors.newSingleThreadExecutor();

    public Form_NhanVien(NhanVien nv) {
        initComponents();
        //        lbNhanVien.setText("WELCOME " + nhanVien.getTen());
        tbNhanVien.setModel(dtmNhanVien);
        cbbChucVu.setModel(dcbmMaCV);
        String header[] = {"MÃ", "HỌ VÀ Tên", "GIỚI TÍNH", "SDT", "NGÀY SINH", "TRẠNG THÁI"};
        dtmNhanVien.setColumnIdentifiers(header);
        showData(listNhanVien = nhanVienService.getAllByTrangThai(0));
        loadCBB(listChucVu = chucVuService.getChucVuActive());
        txtNgaySinh.setDate(today);
        txtMa.setEnabled(false);
        txtNgaySinh.getJCalendar().setMaxSelectableDate(today);
        JTextFieldDateEditor ngaySinh = (JTextFieldDateEditor) txtNgaySinh.getDateEditor();
        ngaySinh.setEnabled(false);
    }

    public void showData(List<NhanVien> list) {
        dtmNhanVien.setRowCount(0);
        list.forEach(s -> dtmNhanVien.addRow(s.toDataRow()));
    }

    private void loadCBB(List<ChucVu> list) {
        if (dcbmMaCV != null) {
            dcbmMaCV.removeAllElements();
        }
        for (ChucVu cv : list) {
            dcbmMaCV.addElement(cv.getMa());
        }
    }

    private void webCamCapture() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0);
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);
        panel.setFPSLimited(true);

        jPanel2.add(panel, new AbsoluteConstraints(0, 0, 270, 200));
        executor.execute(this);
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {

            }

            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            }
            if (image != null) {
                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                try {
                    result = new MultiFormatReader().decode(bitmap);
                } catch (NotFoundException ex) {

                }
            } else {
            }

            if (result != null) {

                String[] cccd = result.getText().split("[|]");
                if (cccd.length == 7) {
                    if (txtMa.getText().isEmpty()) {
                        String[] ten = cccd[2].split("\\s");
                        if (ten.length < 4) {
                            txtTen.setText(ten[2]);
                            txtTenDem.setText(ten[1]);
                            txtHo.setText(ten[0]);
                            txtDiaChi.setText(cccd[5]);

                            String ngay = cccd[3].substring(4) + "-" + cccd[3].substring(2, 4) + "-" + cccd[3].substring(0, 2);
                            txtNgaySinh.setDate(Date.valueOf(ngay));
                            if (cccd[4].equalsIgnoreCase("Nam")) {
                                rdoNam.setSelected(true);
                            } else if (cccd[4].equalsIgnoreCase("Nữ")) {
                                rdoNu.setSelected(true);
                            } else {
                                rdoKoXacDinh.setSelected(true);
                            }
                        } else {
                            txtTen.setText(ten[3]);
                            txtTenDem.setText(ten[1] + " " + ten[2]);
                            txtHo.setText(ten[0]);
                            txtDiaChi.setText(cccd[5]);

                            String ngay = cccd[3].substring(4) + "-" + cccd[3].substring(2, 4) + "-" + cccd[3].substring(0, 2);
                            txtNgaySinh.setDate(Date.valueOf(ngay));
                            if (cccd[4].equalsIgnoreCase("Nam")) {
                                rdoNam.setSelected(true);
                            } else if (cccd[4].equalsIgnoreCase("Nữ")) {
                                rdoNu.setSelected(true);
                            } else {
                                rdoKoXacDinh.setSelected(true);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "clear trước khi thêm mới");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "không phải cccd");
                }

            }

        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "my thread");
        t.setDaemon(true);
        return t;
    }

    private void fillData(int index) {
        nv = listNhanVien.get(index);
        txtDiaChi.setText(nv.getDiaChi());
        txtEmail.setText(nv.getEmail());
        txtHo.setText(nv.getHo());
        txtMa.setText(nv.getMa());
//        txtMa.setEnabled(false);
        txtSdt.setText(nv.getSoDienThoai());
        txtTen.setText(nv.getTen());
        txtTenDem.setText(nv.getTenDem());
        if (nv.getTrangThai() == 0) {
            radioActive.setSelected(true);
        } else {
            radioUnactive.setSelected(true);
        }
        if (nv.getGioiTinh().equals("Nam")) {
            rdoNam.setSelected(true);
        } else if (nv.getGioiTinh().equals("Nữ")) {
            rdoNu.setSelected(true);
        } else {
            rdoKoXacDinh.setSelected(true);
        }
        cbbChucVu.setSelectedItem(nv.getChucVu().getMa());
        txtNgaySinh.setDate(nv.getNgaySinh());
        txtMatKhau.setText(nv.getMatKhau());
        txtTenCv.setText(nv.getChucVu().getTen());
    }

    private NhanVien newNV() {
        NhanVien nhanVien = new NhanVien();
        ChucVu chucVu = chucVuService.getOne(dcbmMaCV.getSelectedItem().toString());
        nhanVien.setChucVu(chucVu);
        nhanVien.setDiaChi(txtDiaChi.getText());
        nhanVien.setEmail(txtEmail.getText());
        if (rdoNam.isSelected()) {
            nhanVien.setGioiTinh("Nam");
        } else if (rdoNu.isSelected()) {
            nhanVien.setGioiTinh("Nữ");
        } else {
            nhanVien.setGioiTinh("Ko xác định");
        }
        nhanVien.setHo(txtHo.getText());
//        nhanVien.setMa(nhanVienUtil.maTuDong(listNhanVien = nhanVienService.getAll()));
        nhanVien.setMatKhau(txtMatKhau.getText());
        nhanVien.setNgaySinh(Date.valueOf(simpleDateFormat.format(txtNgaySinh.getDate())));
        nhanVien.setSoDienThoai(txtSdt.getText());
        nhanVien.setTen(txtTen.getText());
        nhanVien.setTenDem(txtTenDem.getText());
        if (radioActive.isSelected()) {
            nhanVien.setTrangThai(0);
        } else {
            nhanVien.setTrangThai(1);
        }
        return nhanVien;
    }

    private String checkAdd() {
        if (txtNgaySinh.getDate() == null) {
            return "không được để trống ngày";
        } else {
            String ngay = simpleDateFormat.format(txtNgaySinh.getDate());
            String[] birday = ngay.split("-");
            int day = 0;
            int monh = 0;
            int year = 0;
            for (int i = 0; i < 2; i++) {
                day = Integer.valueOf(birday[2]);
                monh = Integer.valueOf(birday[1]);
                year = Integer.valueOf(birday[0]);
            }
            LocalDate ngaySinh = LocalDate.of(year, monh, day);
            LocalDate ngayHienTai = LocalDate.now();
            JOptionPane.showConfirmDialog(this, Period.between(ngaySinh, ngayHienTai).getYears());
            if (txtDiaChi.getText().isEmpty()) {
                return "địa chỉ không được để trống";
            } else if (txtEmail.getText().isEmpty()) {
                return "email không được để trống";
            } else if (txtHo.getText().isEmpty()) {
                return "họ không được để trống";
            } else if (txtTen.getText().isEmpty()) {
                return "tên không được để trống";
            } else if (txtTenDem.getText().isEmpty()) {
                return "tên đệm không được để trống";
            } else if (txtMatKhau.getText().isEmpty()) {
                return "mật khẩu không được để trống";
            } else if (txtSdt.getText().isEmpty()) {
                return "sdt không được để trống";
            } else if (!txtHo.getText().matches("^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ ]+$")) {
                return "họ không đúng định dạng";
            } else if (!txtTenDem.getText().matches("^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ ]+$")) {
                return "tên đệm không đúng định dạng";
            } else if (!txtTen.getText().matches("^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ ]+$")) {
                return "tên không đúng định dạng";
            } else if (Period.between(ngaySinh, ngayHienTai).getYears() < 18 || Period.between(ngaySinh, ngayHienTai).getYears() > 64) {
                return "nhân viên có tuổi nhỏ hơn 18 hoặc lớn hơn 64";
            } else if (!txtSdt.getText().matches("(0)((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))[0-9]{7}")) {
                return "sdt không đúng định dạng";
            } else if (!txtEmail.getText().matches("[a-zA-Z0-9]+(@fpt|@gmail)((.com)|(.edu.vn))")) {
                return "email không đúng định dạng";
            } else if (!txtDiaChi.getText().matches("^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ ]+$")) {
                return "địa chỉ không đúng định dạng";
            } else if (!txtMatKhau.getText().matches("[a-zA-Z0-9]{8}")) {
                return "mật khẩu không đúng định dạng";
            } else {
                int result = JOptionPane.showConfirmDialog(this, "bạn có muốn thêm nhân viên này không", "xác nhận", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    NhanVien nhanVien = newNV();
                    nhanVien.setMa(nhanVienUtil.maTuDong(listNhanVien = nhanVienService.getAll()));
                    String add = nhanVienService.add(nhanVien);
                    return add;
                } else {
                    return "hủy thêm";
                }
            }
        }
    }

    private String Checkupdate() {
        if (txtNgaySinh.getDate() == null) {
            return "không được để trống ngày";
        } else {
            String ngay = simpleDateFormat.format(txtNgaySinh.getDate());
            String[] birday = ngay.split("-");
            int day = 0;
            int monh = 0;
            int year = 0;
            for (int i = 0; i < 2; i++) {
                day = Integer.valueOf(birday[2]);
                monh = Integer.valueOf(birday[1]);
                year = Integer.valueOf(birday[0]);
            }
            LocalDate ngaySinh = LocalDate.of(year, monh, day);
            LocalDate ngayHienTai = LocalDate.now();
            JOptionPane.showConfirmDialog(this, Period.between(ngaySinh, ngayHienTai).getYears());
            if (txtDiaChi.getText().isEmpty()) {
                return "địa chỉ không được để trống";
            } else if (txtEmail.getText().isEmpty()) {
                return "email không được để trống";
            } else if (txtHo.getText().isEmpty()) {
                return "họ không được để trống";
            } else if (txtTen.getText().isEmpty()) {
                return "tên không được để trống";
            } else if (txtTenDem.getText().isEmpty()) {
                return "tên đệm không được để trống";
            } else if (txtMatKhau.getText().isEmpty()) {
                return "mật khẩu không được để trống";
            } else if (txtSdt.getText().isEmpty()) {
                return "sdt không được để trống";
            } else if (!txtHo.getText().matches("^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ ]+$")) {
                return "họ không đúng định dạng";
            } else if (!txtTenDem.getText().matches("^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ ]+$")) {
                return "tên đệm không đúng định dạng";
            } else if (!txtTen.getText().matches("^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ ]+$")) {
                return "tên không đúng định dạng";
            } else if (Period.between(ngaySinh, ngayHienTai).getYears() < 18 || Period.between(ngaySinh, ngayHienTai).getYears() > 64) {
                return "nhân viên có tuổi nhỏ hơn 18 hoặc lớn hơn 64";
            } else if (!txtSdt.getText().matches("(0)((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))[0-9]{7}")) {
                return "sdt không đúng định dạng";
            } else if (!txtEmail.getText().matches("[a-zA-Z0-9]+(@fpt|@gmail)((.com)|(.edu.vn))")) {
                return "email không đúng định dạng";
            } else if (!txtDiaChi.getText().matches("^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ ]+$")) {
                return "địa chỉ không đúng định dạng";
            } else if (!txtMatKhau.getText().matches("[a-zA-Z0-9]{8}")) {
                return "mật khẩu không đúng định dạng";
            } else {
                int result = JOptionPane.showConfirmDialog(this, "bạn có muốn sửa nhân viên này không", "xác nhận", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    NhanVien nhanVien = newNV();
                    String ma = txtMa.getText();
                    nhanVien.setMa(ma);
                    String update = nhanVienService.update(nhanVien, ma);
                    return update;
                } else {
                    return "hủy sửa";
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        radioTrangThaiNV = new javax.swing.ButtonGroup();
        radioGioiTinh = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        panelBorder1 = new com.raven.swing.PanelBorder();
        txtSearch = new com.raven.swing.SearchText();
        jLabel1 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        txtMa = new javax.swing.JTextField();
        txtNgaySinh = new com.toedter.calendar.JDateChooser();
        txtHo = new javax.swing.JTextField();
        txtTenDem = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtSdt = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtMatKhau = new javax.swing.JTextField();
        cbbChucVu = new javax.swing.JComboBox<>();
        btnAddChucVu = new javax.swing.JButton();
        btnLoad = new javax.swing.JButton();
        radioActive = new javax.swing.JRadioButton();
        radioUnactive = new javax.swing.JRadioButton();
        jLabel16 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        rdoKoXacDinh = new javax.swing.JRadioButton();
        txtTenCv = new javax.swing.JLabel();
        plCamera = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnOpenCamera = new javax.swing.JButton();
        btnCloseCamera = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbNhanVien = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        radioListActive = new javax.swing.JRadioButton();
        radioListUnactive = new javax.swing.JRadioButton();

        panelBorder1.setBackground(new java.awt.Color(204, 204, 255));

        txtSearch.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchCaretUpdate(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(204, 204, 204));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton9.setText("Search");

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Mã NV                   :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Họ                          :");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Tên Đệm                :");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Tên                         :");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Giới Tính                :");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Ngày Sinh              :");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Sđt            :");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Email         :");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Địa chỉ       :");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Mật Khẩu   :");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Chức Vụ     :");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Trạng Thái  :");

        cbbChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbChucVuActionPerformed(evt);
            }
        });

        btnAddChucVu.setText("+");
        btnAddChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddChucVuActionPerformed(evt);
            }
        });

        btnLoad.setText("Load");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });

        radioTrangThaiNV.add(radioActive);
        radioActive.setSelected(true);
        radioActive.setText("Active");

        radioTrangThaiNV.add(radioUnactive);
        radioUnactive.setText("Unactive");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Tên CV:");

        radioGioiTinh.add(rdoNam);
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        radioGioiTinh.add(rdoNu);
        rdoNu.setText("Nữ");

        radioGioiTinh.add(rdoKoXacDinh);
        rdoKoXacDinh.setText("Ko xác định");

        txtTenCv.setText("jLabel17");

        plCamera.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        plCamera.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, 200, -1));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        plCamera.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 270, 200));

        btnOpenCamera.setText("Mở Camera");
        btnOpenCamera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenCameraActionPerformed(evt);
            }
        });
        plCamera.add(btnOpenCamera, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, -1));

        btnCloseCamera.setText("Đóng Camera");
        btnCloseCamera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseCameraActionPerformed(evt);
            }
        });
        plCamera.add(btnCloseCamera, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, -1, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(18, 18, 18)
                            .addComponent(txtTenDem, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtHo, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                            .addComponent(txtMa)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8)
                                .addComponent(jLabel9)
                                .addComponent(jLabel15))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(26, 26, 26)
                                    .addComponent(rdoNam)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(rdoNu)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(rdoKoXacDinh))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(radioActive)
                                            .addGap(27, 27, 27)
                                            .addComponent(radioUnactive))))))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtTenCv, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLoad))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cbbChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAddChucVu))))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtMatKhau))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel12)
                            .addGap(18, 18, 18)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addGap(18, 18, 18)
                            .addComponent(txtEmail)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(20, 20, 20)
                        .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(plCamera, javax.swing.GroupLayout.PREFERRED_SIZE, 290, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtHo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtTenDem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(rdoNam)
                            .addComponent(rdoNu)
                            .addComponent(rdoKoXacDinh))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9))
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(cbbChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddChucVu))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtTenCv)
                            .addComponent(btnLoad))))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(radioActive)
                    .addComponent(radioUnactive))
                .addGap(30, 30, 30))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(plCamera, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNhanVienMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbNhanVien);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Thiết lập thông tin nhân viên");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Danh sách nhân viên");

        btnAdd.setBackground(new java.awt.Color(51, 255, 0));
        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnRemove.setBackground(new java.awt.Color(255, 0, 51));
        btnRemove.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRemove.setText("Remove");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnClear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioListActive);
        radioListActive.setSelected(true);
        radioListActive.setText("Active");
        radioListActive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioListActiveActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioListUnactive);
        radioListUnactive.setText("Unactive");
        radioListUnactive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioListUnactiveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jButton9))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 946, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 10, Short.MAX_VALUE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(radioListActive)
                        .addGap(27, 27, 27)
                        .addComponent(radioListUnactive)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate)
                        .addGap(18, 18, 18)
                        .addComponent(btnRemove)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear)
                        .addGap(70, 70, 70))))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton9)))
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addGap(8, 8, 8)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAdd)
                        .addComponent(btnUpdate)
                        .addComponent(btnRemove)
                        .addComponent(btnClear))
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(radioListActive)
                        .addComponent(radioListUnactive))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
//        ChucVu chucVu = chucVuService.getOne((String) cbbChucVu.getSelectedItem());
        if (txtMa.getText().isEmpty()) {
            String add = checkAdd();
            JOptionPane.showMessageDialog(this, add);
            showData(listNhanVien = nhanVienService.getAll());
        } else {
            JOptionPane.showMessageDialog(this, "vui lòng clear");
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnAddChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddChucVuActionPerformed
        // TODO add your handling code here:
        JDialogChucVu viewChucVu = new JDialogChucVu(null, true);
        viewChucVu.setVisible(true);
    }//GEN-LAST:event_btnAddChucVuActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (!txtMa.getText().isEmpty()) {
            String update = Checkupdate();
            JOptionPane.showMessageDialog(this, update);
            btnClearActionPerformed(evt);
            showData(listNhanVien = nhanVienService.getAll());
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        int result = JOptionPane.showConfirmDialog(this, "bạn có muốn xóa nhân viên này không", "xác nhận", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            if (!txtMa.getText().isEmpty()) {
                String xoa = nhanVienService.remove(txtMa.getText());
                showData(listNhanVien = nhanVienService.getAllByTrangThai(0));
                radioListActiveActionPerformed(evt);
                btnClearActionPerformed(evt);
                JOptionPane.showMessageDialog(this, xoa);
            }
        } else {
            JOptionPane.showMessageDialog(this, "hủy xóa");
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void tbNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNhanVienMouseClicked
        int index = tbNhanVien.getSelectedRow();
        fillData(index);
    }//GEN-LAST:event_tbNhanVienMouseClicked

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        txtDiaChi.setText("");
        txtEmail.setText("");
        txtHo.setText("");
        txtMa.setText("");
        txtMa.setEnabled(false);
        txtSdt.setText("");
        txtTen.setText("");
        txtTenDem.setText("");
        radioActive.setSelected(true);
        rdoNam.setSelected(true);
        cbbChucVu.setSelectedIndex(0);
        txtNgaySinh.setDate(today);
        txtMatKhau.setText("");
        if (dcbmMaCV != null) {
            txtTenCv.setText(chucVuService.getOne(dcbmMaCV.getSelectedItem().toString()).getTen());
        }
    }//GEN-LAST:event_btnClearActionPerformed

    private void txtSearchCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchCaretUpdate

        if (txtSearch.getText().isEmpty()) {
            showData(listNhanVien = nhanVienService.getAll());
        } else {
            listNhanVien = nhanVienService.searchByNameAndMa(txtSearch.getText(), txtSearch.getText());
            showData(listNhanVien);
        }
    }//GEN-LAST:event_txtSearchCaretUpdate

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        listChucVu = chucVuService.getChucVuActive();
        loadCBB(listChucVu);
    }//GEN-LAST:event_btnLoadActionPerformed

    private void cbbChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbChucVuActionPerformed
        try {
            chucVu = chucVuService.getOne((String) dcbmMaCV.getSelectedItem());
            txtTenCv.setText(chucVu.getTen());
        } catch (Exception e) {
            txtTenCv.setText("");
        }
    }//GEN-LAST:event_cbbChucVuActionPerformed

    private void radioListActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioListActiveActionPerformed
        // TODO add your handling code here:
        showData(listNhanVien = nhanVienService.getAllByTrangThai(0));
    }//GEN-LAST:event_radioListActiveActionPerformed

    private void radioListUnactiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioListUnactiveActionPerformed
        // TODO add your handling code here:
        showData(listNhanVien = nhanVienService.getAllByTrangThai(1));
    }//GEN-LAST:event_radioListUnactiveActionPerformed

    private void btnOpenCameraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenCameraActionPerformed
        // TODO add your handling code here:
        webCamCapture();
    }//GEN-LAST:event_btnOpenCameraActionPerformed

    private void btnCloseCameraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseCameraActionPerformed
        // TODO add your handling code here:
        webcam.close();
    }//GEN-LAST:event_btnCloseCameraActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddChucVu;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnCloseCamera;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnOpenCamera;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbChucVu;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField1;
    private com.raven.swing.PanelBorder panelBorder1;
    private javax.swing.JPanel plCamera;
    private javax.swing.JRadioButton radioActive;
    private javax.swing.ButtonGroup radioGioiTinh;
    private javax.swing.JRadioButton radioListActive;
    private javax.swing.JRadioButton radioListUnactive;
    private javax.swing.ButtonGroup radioTrangThaiNV;
    private javax.swing.JRadioButton radioUnactive;
    private javax.swing.JRadioButton rdoKoXacDinh;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tbNhanVien;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHo;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMatKhau;
    private com.toedter.calendar.JDateChooser txtNgaySinh;
    private javax.swing.JTextField txtSdt;
    private com.raven.swing.SearchText txtSearch;
    private javax.swing.JTextField txtTen;
    private javax.swing.JLabel txtTenCv;
    private javax.swing.JTextField txtTenDem;
    // End of variables declaration//GEN-END:variables
}
