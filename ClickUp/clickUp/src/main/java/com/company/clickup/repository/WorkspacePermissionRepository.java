package com.company.clickup.repository;

import com.company.clickup.entity.WorkspacePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface WorkspacePermissionRepository extends JpaRepository<WorkspacePermission, UUID> {
}
