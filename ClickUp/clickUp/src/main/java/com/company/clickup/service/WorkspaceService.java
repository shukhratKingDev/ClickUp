package com.company.clickup.service;

import com.company.clickup.dto.MemberDto;
import com.company.clickup.dto.Response;
import com.company.clickup.dto.WorkspaceDto;
import com.company.clickup.entity.User;
import com.company.clickup.entity.Workspace;
import com.company.clickup.entity.WorkspaceUser;
import com.company.clickup.entity.enums.ActionType;
import com.company.clickup.entity.enums.WorkspaceRoleName;
import com.company.clickup.security.annotation.CurrentUser;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;


public interface WorkspaceService {
    Response addWorkspace(WorkspaceDto workspaceDto, User user);
    Response editWorkspace(WorkspaceDto workspaceDto,Long id);
    Response deleteWorkspace(Long id);
    Response addOrEditOrRemoveWorkspaceOwner(MemberDto memberDto, Long id);
    Response joinViaEmail(Long memberId, User user);
    List<WorkspaceUser> getAllMembers(User user);
    List<WorkspaceUser> getAllGuests(User user);
    List<Workspace> getAllWorkspaces(User user);
Response addOrDeleteWorkspacePermission( User user,Long workspaceId, String roleName, WorkspaceRoleName workspaceRoleName, UUID workspacePermissionId,ActionType actionType);

}
