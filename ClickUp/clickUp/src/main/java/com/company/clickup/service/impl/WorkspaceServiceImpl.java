package com.company.clickup.service.impl;

import com.company.clickup.dto.MemberDto;
import com.company.clickup.dto.Response;
import com.company.clickup.dto.WorkspaceDto;
import com.company.clickup.entity.*;
import com.company.clickup.entity.enums.ActionType;
import com.company.clickup.entity.enums.WorkspacePermissionName;
import com.company.clickup.entity.enums.WorkspaceRoleName;
import com.company.clickup.repository.*;
import com.company.clickup.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {
    private WorkspaceRepository workspaceRepository;
    private AttachmentRepository attachmentRepository;
    private WorkspaceUserRepository workspaceUserRepository;
    private WorkspaceRoleRepository workspaceRoleRepository;
    private WorkspacePermissionRepository workspacePermissionRepository;
    private UserRepository userRepository;
@Autowired
    public WorkspaceServiceImpl(WorkspaceRepository workspaceRepository, AttachmentRepository attachmentRepository, WorkspaceUserRepository workspaceUserRepository, WorkspaceRoleRepository workspaceRoleRepository, WorkspacePermissionRepository workspacePermissionRepository, UserRepository userRepository) {
        this.workspaceRepository = workspaceRepository;
    this.attachmentRepository = attachmentRepository;
    this.workspaceUserRepository = workspaceUserRepository;
    this.workspaceRoleRepository = workspaceRoleRepository;
    this.workspacePermissionRepository = workspacePermissionRepository;

    this.userRepository = userRepository;
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

        WorkspaceRole ownerRole = new WorkspaceRole(
                workspace, WorkspaceRoleName.ROLE_OWNER.name(), null);
        workspaceUser.setWorkspaceRole(ownerRole);
        WorkspaceRole memberRole = workspaceRoleRepository.save(new WorkspaceRole(workspace, WorkspaceRoleName.ROLE_MEMBER.name(), null));
        WorkspaceRole adminRole = workspaceRoleRepository.save(new WorkspaceRole(workspace, WorkspaceRoleName.ROLE_ADMIN.name(), null));
        WorkspaceRole guestRole = workspaceRoleRepository.save(new WorkspaceRole(workspace, WorkspaceRoleName.ROLE_GUEST.name(), null));

        workspaceRoleRepository.save(ownerRole);
        workspaceUserRepository.save(workspaceUser);

        List<WorkspacePermission> workspacePermissions=new ArrayList<>();


        WorkspacePermissionName[] permissionNames = WorkspacePermissionName.values();
        for (WorkspacePermissionName permissionName : permissionNames) {
            WorkspacePermission workspacePermission=new WorkspacePermission(
                    ownerRole,permissionName
            );
            workspacePermissions.add(workspacePermission);
            if (permissionName.getRoleNames().contains(WorkspaceRoleName.ROLE_ADMIN)) {
               workspacePermissions.add( new WorkspacePermission(
                       adminRole,permissionName
               ));
            }
            if (permissionName.getRoleNames().contains(WorkspaceRoleName.ROLE_MEMBER)) {
                workspacePermissions.add( new WorkspacePermission(
                        memberRole,permissionName
                ));
            }
            if (permissionName.getRoleNames().contains(WorkspaceRoleName.ROLE_GUEST)) {
                workspacePermissions.add( new WorkspacePermission(
                        guestRole,permissionName
                ));
            }

        }
        workspacePermissionRepository.saveAll(workspacePermissions);

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
    public Response addOrEditOrRemoveWorkspaceOwner(MemberDto memberDto, Long id) {
       if (memberDto.getActionType().equals(ActionType.ADD)){
           WorkspaceUser workspaceUser=new WorkspaceUser();
           workspaceUser.setWorkspace(workspaceRepository.findById(id).orElseThrow(()->new BadCredentialsException("id")));
           workspaceUser.setUser(userRepository.findById(memberDto.getMemberId()).orElseThrow(()->new BadCredentialsException("id")));
           workspaceUser.setWorkspaceRole(workspaceRoleRepository.findById(memberDto.getRoleId()).orElseThrow(()->new BadCredentialsException("id")));
           workspaceUser.setDateInvited(new Timestamp(System.currentTimeMillis()));
           workspaceUser.setDateJoined(null);
           workspaceUserRepository.save(workspaceUser);

           //TODO send invitation  email

       }else if(memberDto.getActionType().equals(ActionType.EDIT)){
           WorkspaceUser workspaceUser=workspaceUserRepository.findByWorkspaceIdAndUserId(id,memberDto.getMemberId()).orElseGet(WorkspaceUser::new);
           workspaceUser.setWorkspaceRole(workspaceRoleRepository.findById(memberDto.getRoleId()).orElseThrow(()->new BadCredentialsException("id")));
           workspaceUserRepository.save(workspaceUser);
       }else{
           workspaceUserRepository.deleteByWorkspaceIdAndUserId(id,memberDto.getMemberId());
       }
       return new Response("Successfully modified",true);
    }

    @Override
    public Response joinViaEmail(Long memberId, User user) {
        Optional<WorkspaceUser> optionalWorkspaceUser = workspaceUserRepository.findByWorkspaceIdAndUserId(memberId, user.getId());
        if (optionalWorkspaceUser.isPresent()) {
            WorkspaceUser workspaceUser=optionalWorkspaceUser.get();
            workspaceUser.setDateJoined(new Timestamp(System.currentTimeMillis()));
            workspaceUserRepository.save(workspaceUser);
            return new Response("You successfully joined",true);

        }
        return new Response("Error",false);
    }
}
