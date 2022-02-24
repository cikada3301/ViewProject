package com.training.vpalagin.project.service.impl;

import com.training.vpalagin.project.converter.AttachmentConverter;
import com.training.vpalagin.project.converter.HistoryConverter;
import com.training.vpalagin.project.dto.attachment.AttachmentViewDto;
import com.training.vpalagin.project.model.Attachment;
import com.training.vpalagin.project.repository.AttachmentRepository;
import com.training.vpalagin.project.service.AttachmentService;
import com.training.vpalagin.project.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository attachmentRepository;

    private final HistoryConverter historyConverter;

    private final HistoryService historyService;

    private final AttachmentConverter attachmentConverter;

    @Transactional(readOnly = true)
    public AttachmentViewDto getByTicketId(Long id) {
        return attachmentConverter.convertToViewDto(attachmentRepository.getByTicketId(id));
    }

    @Transactional
    @Override
    public void add(Attachment attachment) {
        attachmentRepository.add(attachment);
    }

    @Transactional
    @Override
    public void deleteByTicketId(Long id) {
        Attachment attachment = attachmentRepository.getByTicketId(id);
        historyService.add(historyConverter.convertToHistoryWithAttachmentDelete(attachment.getTicket(), attachment.getTicket().getOwner()));
        attachmentRepository.update(attachmentRepository.getByTicketId(id));
    }
}
