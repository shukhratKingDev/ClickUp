package com.company.clickup.repository;

import com.company.clickup.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkspaceRepository  extends JpaRepository<Workspace, Long> {
   boolean existsByOwner_IdAndName(UUID owner_id, String name);
}
