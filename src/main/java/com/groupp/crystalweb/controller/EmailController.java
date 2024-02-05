package com.groupp.crystalweb.controller;

import com.groupp.crystalweb.entity.EmailMessage;
import com.groupp.crystalweb.service.EmailSenderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/mail")
public class EmailController {
    private  EmailSenderService emailSenderService;

    public EmailController(EmailSenderService emailSenderService) {

        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/send")
    public String sendEmail(@RequestParam(value = "file",required = false)MultipartFile[] file,String to,String[] cc, String subject, String body){
        System.out.println("");
        return emailSenderService.sendEmail(file, to, cc, subject, body);
    }
}
