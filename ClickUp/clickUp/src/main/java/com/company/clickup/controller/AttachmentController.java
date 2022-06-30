package com.company.clickup.controller;

import com.company.clickup.dto.AttachmentDto;
import com.company.clickup.dto.Response;
import com.company.clickup.entity.Attachment;
import com.company.clickup.entity.User;
import com.company.clickup.security.annotation.CurrentUser;
import com.company.clickup.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/folder")
public class AttachmentController {
    private AttachmentService attachmentService;
@Autowired
    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addFolder(@CurrentUser User user, @RequestBody AttachmentDto attachmentDto){
        Response response=attachmentService.addFolder(user,attachmentDto);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> editFolder(@CurrentUser User user, @PathVariable UUID attachmentId,@RequestBody AttachmentDto attachmentDto){
        Response response=attachmentService.editFolder(user,attachmentId,attachmentDto);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteFolder(@CurrentUser User user, @PathVariable UUID attachmentId){
        Response response=attachmentService.deleteFolder(user,attachmentId);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attachment> getById(@CurrentUser User user, @PathVariable UUID attachmentId){
        return ResponseEntity.ok(attachmentService.getById(user,attachmentId));
    }
}
