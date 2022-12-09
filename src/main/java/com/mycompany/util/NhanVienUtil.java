/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.util;

import com.mycompany.domainModel.NhanVien;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author son45
 */
public class NhanVienUtil {

    public String maTuDong(List<NhanVien> list) {
        String maNV = "NV";
        int sz = list.size();
        return maNV + (sz + 1);
    }

    public String guiEmail(String email, String user, String pass) {
        final String username = "sonptph25875@fpt.edu.vn";
        final String password = "Son191203";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("sonptph25875@fpt.edu.vn"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email) // email người nhận
            );
            message.setSubject("Mat Khau Cua Hang Ban Hau La");
            message.setText("anh sơn đpẹ trai la: " + user + " " + pass);

            Transport.send(message);

            return "mật khẩu của bạn đã được gửi đến email";

        } catch (MessagingException e) {
            e.printStackTrace();
            return "lỗi không gửi được";
        }
    }
}
