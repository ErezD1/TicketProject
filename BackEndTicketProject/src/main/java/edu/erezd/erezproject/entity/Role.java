package edu.erezd.erezproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UQ_ROLE_NAME", columnNames = {"name"})
})
public class Role {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp = "^ROLE_[A-Z]{1,20}$")
    private String name; //ROLE_ADMIN // ROLE_USER
}