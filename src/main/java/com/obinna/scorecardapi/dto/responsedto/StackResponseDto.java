package com.obinna.scorecardapi.dto.responsedto;

import lombok.Data;

@Data
public class StackResponseDto {
    private String stackName;
    private Integer podCount;
    private String podsHealth;
}
