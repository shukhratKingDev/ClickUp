package com.company.clickup.repository;

import com.company.clickup.entity.User;
import com.company.clickup.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface WorkspaceRepository  extends JpaRepository<Workspace, Long> {
   boolean existsByOwner_IdAndName(UUID owner_id, String name);
   Optional<List<Workspace>> findByUser_Id(UUID user_id);
   Optional<List<Workspace>>findAllByOwner_Id(UUID owner_id);
   Optional<Workspace> findByIdAndOwner_Id(Long id, UUID owner_id);

}
