package com.sendgrid.mail;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        var emailService = new SendEmailService();
        var body = """
                Hello, from SendGrid!!!
                
                I am testing the Java SDK for SendGrid and this is a multiline string in Java.
                
                Thanks and Regards,
                Deepak Upadhyay
    """
                ;

        try{
            emailService.sendEmail("usanket15@gmail.com",
                    "SendGrid mail sending learning and Implementation",
                    body);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
