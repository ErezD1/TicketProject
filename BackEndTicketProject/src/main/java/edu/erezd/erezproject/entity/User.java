package edu.erezd.erezproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UQ_USER_NAME", columnNames = {"username"}),
        @UniqueConstraint(name = "UQ_USER_EMAIL", columnNames = {"email"})
})
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;


    @NotNull
    @Size(min = 2)
    private String username;

    @NotNull
    @Pattern(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;

    @NotNull
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            )},
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id"
            )
    )
    private Set<Role> roles;

    //one user - many comment (oneToMany)
    @OneToMany(mappedBy = "user")
    private Set<Comment> comments = Set.of();
}