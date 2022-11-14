package com.obinna.scorecardapi.model;

import com.obinna.scorecardapi.enums.Gender;
import com.obinna.scorecardapi.enums.Role;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorColumn(name = "user_type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@Table(name = "users")
public class User extends BaseClass implements Serializable {
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String password;
    private Boolean isAccountActive;


    public void deactivateUser(){
        this.setIsAccountActive(false);
    }
    public void activateUser(){
        this.setIsAccountActive(true);
    }
}
