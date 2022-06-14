package com.company.clickup.service.impl;

import com.company.clickup.dto.Response;
import com.company.clickup.dto.WorkspaceDto;
import com.company.clickup.entity.Attachment;
import com.company.clickup.entity.User;
import com.company.clickup.entity.Workspace;
import com.company.clickup.repository.AttachmentRepository;
import com.company.clickup.repository.WorkspaceRepository;
import com.company.clickup.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {

    private WorkspaceRepository workspaceRepository;
    private AttachmentRepository attachmentRepository;
@Autowired
    public WorkspaceServiceImpl(WorkspaceRepository workspaceRepository, AttachmentRepository attachmentRepository) {
        this.workspaceRepository = workspaceRepository;
    this.attachmentRepository = attachmentRepository;
}

    @Override
    public Response addWorkspace(WorkspaceDto workspaceDto, User user) {
       if (workspaceRepository.existsByOwner_IdAndName(user.getId(),workspaceDto.getName())){
           return new Response("You have already created workspace with this name",false);
        }
        Workspace workspace=new Workspace();
       workspace.setName(workspaceDto.getName());
       workspace.setOwner(user);
       workspace.setColor(workspaceDto.getColor());
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(workspaceDto.getAvatarId());
        if (optionalAttachment.isEmpty()) {
            workspace.setAvatarId(null);
        }
        workspace.setAvatarId(optionalAttachment.get());
        workspaceRepository.save(workspace);
        return new Response("New workspace successfully added !!!",true);
    }



    @Override
    public Response editWorkspace(WorkspaceDto workspaceDto, Long id) {
        return null;
    }



    @Override
    public Response deleteWorkspace(Long id) {
        return null;
    }

    @Override
    public Response changeWorkspaceOwner(UUID ownerId, Long id) {
        return null;
    }
}
