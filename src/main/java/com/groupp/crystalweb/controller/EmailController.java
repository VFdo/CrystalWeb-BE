package com.groupp.crystalweb.controller;

import com.groupp.crystalweb.common.Tuple;
import com.groupp.crystalweb.dto.request.EmailRequest;
import com.groupp.crystalweb.dto.request.EmployeeRequest;
import com.groupp.crystalweb.dto.response.ApiResponse;
import com.groupp.crystalweb.dto.response.PageInfo;
import com.groupp.crystalweb.entity.EmailMessage;

import com.groupp.crystalweb.entity.Employee;
import com.groupp.crystalweb.service.EmailSenderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;

@RestController
@RequestMapping("/mail")
public class EmailController {
    private  final EmailSenderService emailSenderService;

    public EmailController(EmailSenderService emailSenderService) {

        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/send")
    public String sendEmail(@Nullable @RequestParam(value = "file",required = false)MultipartFile file, String reciever, @Nullable String cc, String subject, String body){
        System.out.println("");
        return emailSenderService.sendEmail(file, reciever,cc, subject, body);

    }

//    @PostMapping("/send")
//    public ResponseEntity<ApiResponse> saveEmail(@Valid @RequestBody EmailRequest emailRequest){
//        EmailMessage savedEmailMessage =  emailSenderService.saveEmailMessage(emailRequest);
//        ApiResponse response = new ApiResponse(
//                200,
//                "Success",
//                savedEmailMessage
//        );
//        return ResponseEntity.ok(response);
//    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllEmails(@RequestParam(defaultValue = "0") int pageNumber,
                                                    @RequestParam(defaultValue = "10") int pageSize){
        Tuple<Object, Object> allEmails = emailSenderService.getAllEmails(pageNumber, pageSize);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                allEmails.first(),
                (PageInfo) allEmails.second()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/email/{id}")
    public ResponseEntity<ApiResponse> findEmail(@PathVariable String id){
        EmailMessage existingEmailMessage = emailSenderService.getEmail(id);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                existingEmailMessage
        );
        return ResponseEntity.ok(response);
    }
}
