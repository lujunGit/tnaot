package com.mongcent.tnaot.repository;

import com.mongcent.tnaot.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
