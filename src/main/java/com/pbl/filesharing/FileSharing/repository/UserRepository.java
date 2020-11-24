package com.pbl.filesharing.FileSharing.repository;

import com.pbl.filesharing.FileSharing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Beatrice V.
 * @created 24.11.2020 - 17:35
 * @project FileSharing
 */

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);
}
