package com.pbl.filesharing.FileSharing;

import com.pbl.filesharing.FileSharing.entity.User;
import com.pbl.filesharing.FileSharing.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Beatrice V.
 * @created 24.11.2020 - 17:38
 * @project FileSharing
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser(){
        User user = new User();
        user.setEmail("samwoods888@gmail.com");
        user.setPassword("woods8sam");
        user.setFirstName("Samuel");
        user.setLastName("Woods");

        User savedUser = repository.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());

    }

    @Test
    public void testFindUserByEmail(){
        String email = "samwoods888@gmail.com";

        User user = repository.findByEmail(email);

        assertThat(user).isNotNull();
    }
}
