package com.company.clickup.service.impl;

import com.company.clickup.dto.Response;
import com.company.clickup.dto.WorkspaceDto;
import com.company.clickup.entity.*;
import com.company.clickup.entity.enums.WorkspaceRoleName;
import com.company.clickup.repository.AttachmentRepository;
import com.company.clickup.repository.WorkspaceRepository;
import com.company.clickup.repository.WorkspaceRoleRepository;
import com.company.clickup.repository.WorkspaceUserRepository;
import com.company.clickup.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {

    private WorkspaceRepository workspaceRepository;
    private AttachmentRepository attachmentRepository;
    private WorkspaceUserRepository workspaceUserRepository;
    private WorkspaceRoleRepository workspaceRoleRepository;
@Autowired
    public WorkspaceServiceImpl(WorkspaceRepository workspaceRepository, AttachmentRepository attachmentRepository, WorkspaceUserRepository workspaceUserRepository, WorkspaceRoleRepository workspaceRoleRepository) {
        this.workspaceRepository = workspaceRepository;
    this.attachmentRepository = attachmentRepository;
    this.workspaceUserRepository = workspaceUserRepository;
    this.workspaceRoleRepository = workspaceRoleRepository;
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
        if (workspaceDto.getAvatarId() != null) {
            Optional<Attachment> optionalAttachment = attachmentRepository.findById(workspaceDto.getAvatarId());
            if (optionalAttachment.isEmpty()) {
                workspace.setAvatarId(null);
            }
            workspace.setAvatarId(optionalAttachment.get());
        }else{
        workspace.setAvatarId(null);}

        workspaceRepository.save(workspace);
        WorkspaceUser workspaceUser=new WorkspaceUser();
        workspaceUser.setWorkspace(workspace);
        workspaceUser.setUser(user);
        workspaceUser.setDateInvited(new Timestamp(System.currentTimeMillis()));
        workspaceUser.setDateJoined(new Timestamp(System.currentTimeMillis()));
        workspaceRoleRepository.save(new WorkspaceRole(
                workspace, WorkspaceRoleName.ROLE_OWNER.name(), null
        ));


        /// 16:22


        return new Response("New workspace successfully added !!!",true);
    }



    @Override
    public Response editWorkspace(WorkspaceDto workspaceDto, Long id) {
        if (!workspaceRepository.findById(id).isPresent()) {
            return new Response("Workspace with this id not found",false);
        }
        Workspace workspace =new Workspace();
        workspace.setName(workspaceDto.getName());
        workspace.setColor(workspace.getColor());
        return null;
    }



    @Override
    public Response deleteWorkspace(Long id) {
    if (!workspaceRepository.findById(id).isPresent()){
        return new Response("Workspace with this id not found",false);
    }
    workspaceRepository.deleteById(id);
        return new Response("Workspace successfully deleted",true);
    }

    @Override
    public Response changeWorkspaceOwner(UUID ownerId, Long id) {
        return null;
    }
}
