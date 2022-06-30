package com.company.clickup.service.impl;

import com.company.clickup.dto.AttachmentDto;
import com.company.clickup.dto.Response;
import com.company.clickup.entity.Attachment;
import com.company.clickup.entity.User;
import com.company.clickup.repository.AttachmentRepository;
import com.company.clickup.service.AttachmentService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@Service
public class AttachmentServiceImpl implements AttachmentService {

    private AttachmentRepository attachmentRepository;

    public AttachmentServiceImpl(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    public Response addFolder(User user, AttachmentDto attachmentDto) {
        Attachment attachment=new Attachment();
        attachment.setName(attachmentDto.getName());
        attachment.setContentType(attachmentDto.getContentType());
        attachment.setOriginalName(attachmentDto.getOriginalName());
        attachment.setSize(attachmentDto.getSize());
        attachmentRepository.save(attachment);
        return new Response("Folder saved successfully",true);
    }

    @Override
    public Response editFolder(User user, UUID attachmentId,AttachmentDto attachmentDto) {
        Optional<Attachment> optionalAttachment=attachmentRepository.findById(attachmentId);
        if (!optionalAttachment.isPresent()) {
            return new Response("Attachment with this id not found",false);
        }
        Attachment attachment=optionalAttachment.get();
        attachment.setName(attachmentDto.getName());
        attachment.setContentType(attachmentDto.getContentType());
        attachment.setOriginalName(attachmentDto.getOriginalName());
        attachment.setSize(attachmentDto.getSize());
        attachmentRepository.save(attachment);
        return new Response("Attachment successfully updated",true);
    }

    @Override
    public Response deleteFolder(User user, UUID attachmentId) {
        Optional<Attachment> optionalAttachment=attachmentRepository.findById(attachmentId);
        if (!optionalAttachment.isPresent()) {
            return new Response("Attachment with this id not found",false);
        }
        attachmentRepository.deleteById(attachmentId);
        return new Response("Attachment successfully deleted",true);
    }

    @Override
    public Attachment getById(User user, UUID attachmentId) {
        Optional<Attachment> optionalAttachment=attachmentRepository.findById(attachmentId);
        if (!optionalAttachment.isPresent()) {
            return null;
        }
        return attachmentRepository.findById(attachmentId).get();
    }
}
