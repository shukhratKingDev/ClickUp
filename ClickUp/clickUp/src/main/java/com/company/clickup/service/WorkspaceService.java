package com.company.clickup.service;

import com.company.clickup.dto.MemberDto;
import com.company.clickup.dto.Response;
import com.company.clickup.dto.WorkspaceDto;
import com.company.clickup.entity.User;


public interface WorkspaceService {
    Response addWorkspace(WorkspaceDto workspaceDto, User user);
    Response editWorkspace(WorkspaceDto workspaceDto,Long id);
    Response deleteWorkspace(Long id);
    Response addOrEditOrRemoveWorkspaceOwner(MemberDto memberDto, Long id);
    Response joinViaEmail(Long memberId, User user);
}
