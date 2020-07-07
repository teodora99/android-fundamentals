package ro.utcn.sd.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import ro.utcn.sd.entity.Application;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class EmailUtils {

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    private ApplicationUtils applicationUtils;

    /**
     * This method will send the random code
     * @param email the email to which the mail will be send
     * @param message the email message
     */
    public void sendMailDelete(String email, String message){

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("Internship Deleted");
        msg.setText(message);
        emailSender.send(msg);
    }

    /**
     * This method will send the random code
     * @param email the email to which the mail will be send
     * @param code the random code generated
     */
    public void resetPassword(String email, String code){

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("RESET PASSWORD");
        msg.setText("Your reset code is: " + code);
        emailSender.send(msg);
    }

    public void applicationConfirmation(String email, Application application){
        try {
                MimeMessage message = emailSender.createMimeMessage();

                MimeMessageHelper msg = new MimeMessageHelper(message, true);
                msg.setTo(email);
                msg.setSubject("APPLICATION CONFIRMATION");
                msg.setText("Good day! We have attached the application confirmation! ");

                createPDFApplication(application);

                FileSystemResource file
                        = new FileSystemResource(new File("application.pdf"));
                msg.addAttachment("Application",file);
                emailSender.send(message);
         } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    public Document createPDFApplication(Application application) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("application.pdf"));
            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Chunk chunk = new Chunk(applicationUtils.templateAppliedInternship(application), font);
            document.add(new Phrase(chunk));
            document.close();
            return document;
        }catch (IOException | DocumentException e){
            return null;
        }
    }
}
