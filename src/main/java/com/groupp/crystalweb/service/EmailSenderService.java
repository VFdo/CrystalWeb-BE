package com.groupp.crystalweb.service;

import com.groupp.crystalweb.common.Tuple;
import com.groupp.crystalweb.dto.request.EmailRequest;
import com.groupp.crystalweb.dto.request.EmployeeRequest;
import com.groupp.crystalweb.dto.response.PageInfo;
import com.groupp.crystalweb.entity.EmailMessage;
import com.groupp.crystalweb.entity.Employee;
import com.groupp.crystalweb.repository.EmailRepository;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional

public class EmailSenderService {
    @Autowired
    private final JavaMailSender mailSender;
    private final EmailRepository emailRepository;

    @Value("${spring.mail.username")
    private String fromEmail;

    public EmailSenderService(JavaMailSender mailSender,EmailRepository emailRepository){
        this.mailSender = mailSender;
        this.emailRepository = emailRepository;
    }


    public String sendEmail(@Nullable MultipartFile file, String reciever, @Nullable String cc , String subject, String body){
       try{
           MimeMessage mimeMessage = mailSender.createMimeMessage();
           MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
           mimeMessageHelper.setFrom(fromEmail);
           mimeMessageHelper.setTo(reciever);
           mimeMessageHelper.setCc(cc);
           mimeMessageHelper.setSubject(subject);
           mimeMessageHelper.setText(body);

//           for(int i=0;i<file.length;i++){
//               mimeMessageHelper.addAttachment(
//                       file[i].getOriginalFilename(),
//                       new ByteArrayResource(file[i].getBytes())
//               );
//           }
           mailSender.send(mimeMessage);
           return "mail-send";
       }catch (Exception e){
           throw new RuntimeException(e);
       }

    }

    public EmailMessage saveEmailMessage(EmailRequest emailRequest) {
        log.info("email save request received");
        try {
            EmailMessage newEmailMessage = new EmailMessage();
            newEmailMessage.setReceiver(emailRequest.reciever());
            newEmailMessage.setReceiver(emailRequest.subject());
            newEmailMessage.setReceiver(emailRequest.body());
            newEmailMessage.setReceiver(emailRequest.file());


            return emailRepository.save(newEmailMessage);
        } catch (Exception e) {
            log.info("email saving failed: {}", e.getMessage());
            throw new RuntimeException("Something went wrong!");
        }
    }

    public EmailMessage getEmail(String id) {
        Optional<EmailMessage> emailMessage = emailRepository.findByRefId(id);
        if(emailMessage.isPresent()){
            return emailMessage.get();
        }
//        TODO: handle response
        return null;
    }

    public Tuple<Object, Object> getAllEmails(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<EmailMessage> emailMessagePage = emailRepository.findAll(pageable);
        List<EmailMessage> emailMessages = emailMessagePage.getContent();
        PageInfo pageInfo = new PageInfo(
                emailMessagePage.getNumber(),
                emailMessagePage.getSize(),
                emailMessagePage.getTotalElements(),
                emailMessagePage.getTotalPages());
        return new Tuple<>(emailMessages, pageInfo);
    }



}
