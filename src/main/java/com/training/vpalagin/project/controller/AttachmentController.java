package com.training.vpalagin.project.controller;

import com.training.vpalagin.project.dto.attachment.AttachmentViewDto;
import com.training.vpalagin.project.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attachment")
public class AttachmentController {
    private final AttachmentService attachmentService;

    @GetMapping("/{id}")
    public ResponseEntity<AttachmentViewDto> getById(@PathVariable("id") Long id) {
        AttachmentViewDto attachment = attachmentService.getById(id);
        return ResponseEntity.ok(attachment);
    }
}
