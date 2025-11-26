package com.shashi.utility;

import java.util.Properties;
import java.util.ResourceBundle;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class JavaMailUtil {

    /**
     * Create a Session object using Gmail SMTP with TLS.
     */
    private static Session getMailSession() {

        ResourceBundle rb = ResourceBundle.getBundle("application");

        String emailId = rb.getString("mailer.email");
        String passWord = rb.getString("mailer.password");

        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // 🔥 MUST HAVE – This fixes FolderClosedIOException issue
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        return Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailId, passWord);
            }
        });
    }

    /**
     * Send simple text email (Used for registration success)
     */
    public static void sendMail(String recipientMailId) throws MessagingException {

        ResourceBundle rb = ResourceBundle.getBundle("application");
        String emailId = rb.getString("mailer.email");

        Message message = new MimeMessage(getMailSession());
        message.setFrom(new InternetAddress(emailId));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientMailId));
        message.setSubject("Welcome to Shopping Cart");
        message.setText("Hello " + recipientMailId + ",\n\nThank you for registering with us!");

        Transport.send(message);
        System.out.println("Simple Email Sent Successfully!");
    }

    /**
     * Send email with HTML content (Used for order success)
     */
    public static void sendMail(String recipient, String subject, String htmlText)
            throws MessagingException {

        ResourceBundle rb = ResourceBundle.getBundle("application");
        String emailId = rb.getString("mailer.email");

        Message message = new MimeMessage(getMailSession());
        message.setFrom(new InternetAddress(emailId));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        message.setSubject(subject);
        message.setContent(htmlText, "text/html; charset=utf-8");

        Transport.send(message);
        System.out.println("HTML Email Sent Successfully!");
    }
}

