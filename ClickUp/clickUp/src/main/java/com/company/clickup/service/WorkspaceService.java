package com.company.clickup.service;

import com.company.clickup.dto.Response;
import com.company.clickup.dto.WorkspaceDto;
import com.company.clickup.entity.User;

import java.util.UUID;


public interface WorkspaceService {
    Response addWorkspace(WorkspaceDto workspaceDto, User user);
    Response editWorkspace(WorkspaceDto workspaceDto,Long id);
    Response deleteWorkspace(Long id);
    Response changeWorkspaceOwner(UUID ownerId, Long id);
}
