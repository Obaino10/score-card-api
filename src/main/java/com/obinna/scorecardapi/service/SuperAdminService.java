package com.obinna.scorecardapi.service;

import com.obinna.scorecardapi.dto.requestdto.AdminDto;
import com.obinna.scorecardapi.dto.requestdto.StackDto;
import com.obinna.scorecardapi.dto.responsedto.APIResponse;
import com.obinna.scorecardapi.dto.responsedto.SquadDto;
import com.obinna.scorecardapi.dto.responsedto.StackResponseDto;
import com.obinna.scorecardapi.model.*;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.Optional;

public interface SuperAdminService {

    List<Pod> listOfPods();

    String removeAdminById(Long id);

    User CreateAdmin(AdminDto adminDto, Long podId, Long stackId, Long squadId);

    String createSquad(SquadDto squadDto);

    APIResponse getAdmin(Long id);

    Page<Squad> getAllSquads(int offset, int pageSize);

    List<StackResponseDto> getDetailsOfAllStacks(Long squadId);

    Stack getStackUsingId(Long id);

    APIResponse<String> updateStack(StackDto stackDto, Long id);
    APIResponse<Admin> updateAdmin(AdminDto adminDto, Long adminId);

    APIResponse<User>activateAdmin(Long adminId);
    APIResponse<User> deactivateAdmin(Long adminId);

    Pod getPod(Long id);

}

