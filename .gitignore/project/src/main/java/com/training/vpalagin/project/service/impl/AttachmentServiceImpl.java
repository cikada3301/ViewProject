package com.training.vpalagin.project.service.impl;

import com.training.vpalagin.project.model.Attachment;
import com.training.vpalagin.project.repository.AttachmentRepository;
import com.training.vpalagin.project.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository attachmentRepository;

    @Override
    public List<Attachment> getAll() {
        return attachmentRepository.getAll();
    }

    @Override
    public void add(Attachment attachment) {
        attachmentRepository.add(attachment);
    }

    @Override
    public void update(Attachment attachment) {
        attachmentRepository.update(attachment);
    }
}
