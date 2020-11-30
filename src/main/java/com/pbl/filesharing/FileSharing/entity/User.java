package com.pbl.filesharing.FileSharing.entity;

import com.pbl.filesharing.FileSharing.security.validators.ValidEmail;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Beatrice V.
 * @created 24.11.2020 - 17:31
 * @project FileSharing
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ValidEmail
    @NotEmpty(message="Email must not be empty")
    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @NotEmpty(message="First name must not be empty")
    @Size(min=2, max=20)
    @Pattern(regexp="^$|[a-zA-Z ]+$", message="Name must not include special characters.")
    @Column(nullable = false, length = 20)
    private String firstName;

    @NotEmpty(message="Last name must not be empty")
    @Size(min=2, max=20)
    @Pattern(regexp="^$|[a-zA-Z ]+$", message="Name must not include special characters.")
    @Column(nullable = false, length = 20)
    private String lastName;

    @NotEmpty(message="Password must not be empty")
    @Column(nullable = false, length = 64)
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
