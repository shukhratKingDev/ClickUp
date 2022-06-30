package com.company.clickup.repository;

import com.company.clickup.entity.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpaceRepository  extends JpaRepository<Space, UUID> {

    boolean  existsByNameAndWorkspaceId(String name, Long workspaceId);
    Optional<Space> findByUser_IdAndId(UUID user_id, UUID id);
    Optional<List<Space>> findAllByUserId(UUID user_id);

}
