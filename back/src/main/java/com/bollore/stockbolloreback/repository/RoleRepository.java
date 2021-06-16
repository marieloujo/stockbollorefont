package com.bollore.stockbolloreback.repository;


import com.bollore.stockbolloreback.models.Role;
import com.bollore.stockbolloreback.enumeration.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(UserRoles name);
}

