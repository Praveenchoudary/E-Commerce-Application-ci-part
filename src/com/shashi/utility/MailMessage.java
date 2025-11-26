package com.shashi.utility;

public class MailMessage {

    public static void registrationSuccess(String emailId, String name) {
        // Email disabled
        System.out.println("[MAIL DISABLED] Registration mail skipped for: " + emailId);
    }

    public static void transactionSuccess(String recipientEmail, String name, String transId, double transAmount) {
        // Email disabled
        System.out.println("[MAIL DISABLED] Transaction mail skipped for: " + recipientEmail);
    }

    public static void orderShipped(String recipientEmail, String name, String transId, double transAmount) {
        // Email disabled
        System.out.println("[MAIL DISABLED] Order shipped mail skipped for: " + recipientEmail);
    }

    public static void productAvailableNow(String recipientEmail, String name, String prodName, String prodId) {
        // Email disabled
        System.out.println("[MAIL DISABLED] Product available mail skipped for: " + recipientEmail);
    }

    public static String sendMessage(String toEmailId, String subject, String htmlTextMessage) {
        // Email disabled
        System.out.println("[MAIL DISABLED] Generic mail skipped for: " + toEmailId);
        return "SUCCESS";
    }
}

