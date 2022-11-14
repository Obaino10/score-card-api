package com.obinna.scorecardapi.service;

import com.obinna.scorecardapi.dto.requestdto.StackDto;
import com.obinna.scorecardapi.dto.responsedto.APIResponse;
import com.obinna.scorecardapi.model.StackTemplate;

public interface StackService {

      APIResponse<StackTemplate> createStack(StackDto stackDto);

}
