package com.company.clickup.repository;

import com.company.clickup.entity.WorkspaceRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface WorkspaceRoleRepository extends JpaRepository<WorkspaceRole, UUID> {

}
