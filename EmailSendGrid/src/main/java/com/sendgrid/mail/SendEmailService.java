package com.sendgrid.mail;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;

public class SendEmailService {

    private final String SENDGRID_API_KEY = System.getenv("SENDGRID_API_KEY");

    public void sendEmail(String to, String subject, String body) throws IOException {
        Email from = new Email("deepakkumarupadhyay63@gmail.com");
        Email toEmail = new Email(to);
        Content content = new Content("text/plain", body);
        Mail mail = new Mail(from,subject,toEmail,content);

        //configure Sendgrid client
        SendGrid sg=new SendGrid(SENDGRID_API_KEY);
        Request request = new Request();

        try{
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);

            System.out.println("Status code: " + response.getStatusCode());
            System.out.println("Status message: " + response.getBody());

        } catch (IOException e) {
            throw new IOException("Failed to send email: "+ e.getMessage());
        }
    }
}
