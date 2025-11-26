package com.shashi.utility;

import jakarta.mail.MessagingException;

public class TestMail {

    public static void main(String[] args) {

        try {
            String recipient = "ellison.alumni@gmail.com";
            String subject = "Test Email – Shopping Cart";

            String htmlTextMessage = "<html>" +
                    "<body>" +
                    "<h2 style='color:green;'>Email Service Test – Shopping Cart</h2>" +
                    "<p>Hello,<br><br>" +
                    "This is a test mail to confirm that your JavaMail (Jakarta Mail + Angus Mail) " +
                    "configuration is working successfully.<br><br>" +
                    "<b>If you received this email, then everything is correctly configured!</b>" +
                    "<br><br>Regards,<br>Shopping Cart Application" +
                    "</p>" +
                    "</body>" +
                    "</html>";

            JavaMailUtil.sendMail(recipient, subject, htmlTextMessage);

            System.out.println("Mail Sent Successfully!");

        } catch (MessagingException e) {
            System.out.println("Mail Sending Failed With Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

}

