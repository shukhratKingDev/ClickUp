package com.company.clickup.repository;

import com.company.clickup.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface WorkspaceUserRepository extends JpaRepository<User, UUID> {
}
