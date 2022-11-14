package com.obinna.scorecardapi.dto.responsedto;

import lombok.*;

import java.util.List;

@Data
public class SquadDto {
    private String squadName;
    private List<String> stackNames;
}

