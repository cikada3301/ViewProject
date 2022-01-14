package com.training.vpalagin.project.converter;

import com.training.vpalagin.project.dto.AttachmentDto;
import com.training.vpalagin.project.model.Attachment;

public interface AttachmentConverter {
    Attachment convertFromDto(AttachmentDto commentDto);
    AttachmentDto convertToDto(Attachment comment);
}
