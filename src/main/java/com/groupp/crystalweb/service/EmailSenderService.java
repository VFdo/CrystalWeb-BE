package com.groupp.crystalweb.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;

@Service
public class EmailSenderService {
    @Autowired
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username")
    private String fromEmail;

    public EmailSenderService(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }


    public String sendEmail(@Nullable MultipartFile[] file, String to, @Nullable String cc , String subject, String body){
       try{
           MimeMessage mimeMessage = mailSender.createMimeMessage();
           MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
           mimeMessageHelper.setFrom(fromEmail);
           mimeMessageHelper.setTo(to);
           mimeMessageHelper.setCc(cc);
           mimeMessageHelper.setSubject(subject);
           mimeMessageHelper.setText(body);

           for(int i=0;i<file.length;i++){
               mimeMessageHelper.addAttachment(
                       file[i].getOriginalFilename(),
                       new ByteArrayResource(file[i].getBytes())
               );
           }
           mailSender.send(mimeMessage);
           return "mail-send";
       }catch (Exception e){
           throw new RuntimeException(e);
       }

    }

}
