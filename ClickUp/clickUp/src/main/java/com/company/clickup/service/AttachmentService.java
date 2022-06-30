package com.company.clickup.service;

import com.company.clickup.dto.AttachmentDto;
import com.company.clickup.dto.Response;
import com.company.clickup.entity.Attachment;
import com.company.clickup.entity.User;

import java.util.UUID;

public interface AttachmentService {

    Response addFolder(User user, AttachmentDto attachmentDto);

    Response editFolder(User user, UUID attachmentId,AttachmentDto attachmentDto);

    Response deleteFolder(User user, UUID attachmentId);

    Attachment getById(User user, UUID attachmentId);

}
