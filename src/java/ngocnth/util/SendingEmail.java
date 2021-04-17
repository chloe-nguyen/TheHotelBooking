package ngocnth.util;

import java.io.Serializable;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendingEmail implements Serializable {

    private String userEmail;

    public SendingEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void sendEmail() throws MessagingException {
        String myEmail = "cloudie1206@gmail.com";
        String myPassword = "0976874387";
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail, myPassword);
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(myEmail));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
        message.setSubject("Booking.vn - Email Recovery Password Link");
        message.setText("Your Recovery Link: " + "http://localhost:8084/Booking.vn/change-password-page?txtEmail=" + userEmail);
        Transport.send(message);
    }
}
