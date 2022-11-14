package com.obinna.scorecardapi.dto.requestdto;

import com.obinna.scorecardapi.enums.AssignRole;
import com.obinna.scorecardapi.enums.Gender;
import com.obinna.scorecardapi.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdminDto {
    private String firstName;
    private String lastName;
    private Gender gender;
    private String email;
    private AssignRole assignRole;
    private Role role;
}