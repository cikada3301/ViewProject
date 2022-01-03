package com.training.vpalagin.project.controller;

import com.training.vpalagin.project.model.Attachment;
import com.training.vpalagin.project.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ticket")
public class AttachmentController {
    private final AttachmentService attachmentService;

    @PostMapping("/attachment")
    public ResponseEntity<Void> addAttachment(Attachment attachment) {
        attachmentService.add(attachment);
        return ResponseEntity.ok().build();
    }
}
