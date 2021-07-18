package com.bollore.stockbolloreback.repository;


import com.bollore.stockbolloreback.models.Role;
import com.bollore.stockbolloreback.enumeration.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * The interface Role repository.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    /**
     * Find by name optional.
     *
     * @param name the name
     * @return the optional
     */
    Optional<Role> findByName(UserRoles name);
}

