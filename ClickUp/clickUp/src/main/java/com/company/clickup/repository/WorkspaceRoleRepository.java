package com.company.clickup.repository;

import com.company.clickup.entity.WorkspaceRole;
import com.company.clickup.entity.enums.WorkspaceRoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface WorkspaceRoleRepository extends JpaRepository<WorkspaceRole, UUID> {
    Optional<WorkspaceRole> findByWorkspace_idAndName(Long workspace_id, String name);

}
