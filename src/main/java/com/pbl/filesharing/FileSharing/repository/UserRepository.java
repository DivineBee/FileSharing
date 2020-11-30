package com.pbl.filesharing.FileSharing.repository;

import com.pbl.filesharing.FileSharing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Beatrice V.
 * @created 24.11.2020 - 17:35
 * @project FileSharing
 */

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.firstName = :first_name")
    User findByFirstName(@Param("first_name") String firstName);

    @Query("SELECT u FROM User u WHERE u.lastName = :last_name")
    User findByLastName(@Param("last_name") String lastName);
}
