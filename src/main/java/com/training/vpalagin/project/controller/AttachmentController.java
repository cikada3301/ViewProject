package com.training.vpalagin.project.controller;

import com.training.vpalagin.project.dto.attachment.AttachmentViewDto;
import com.training.vpalagin.project.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attachments")
public class AttachmentController {
    private final AttachmentService attachmentService;

    @GetMapping("/{ticketId}")
    public ResponseEntity<AttachmentViewDto> getByTicketId(@PathVariable("ticketId") Long id) {
        AttachmentViewDto attachment = attachmentService.getByTicketId(id);
        return ResponseEntity.ok(attachment);
    }

    @DeleteMapping("/{ticketId")
    public ResponseEntity<AttachmentViewDto> deleteByTicketId(@PathVariable("ticketId") Long id) {
        attachmentService.deleteByTicketId(id);
        return ResponseEntity.noContent().build();
    }
}
