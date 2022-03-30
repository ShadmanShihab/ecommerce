package com.emart.ecommerce.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "first name can not be empty")
    @Column(nullable = false)
    private String firstName;

    @NotEmpty(message = "last name can not be empty")
    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "email can not be empty")
    @Email(message = "{errors.invalid_email")
    private String email;

    @NotEmpty
    private String password;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumn = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")}
            }
    )
    private List<Role> roles;

    public User(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.roles = user.getRoles();
    }
}
