package com.obinna.scorecardapi.service;

import com.obinna.scorecardapi.dto.DecadevDto;
import com.obinna.scorecardapi.dto.responsedto.APIResponse;
import com.obinna.scorecardapi.model.Decadev;
import com.obinna.scorecardapi.model.User;
import com.obinna.scorecardapi.response.AdminResponse;

import java.util.List;


public interface AdminService {
    List<AdminResponse> getAllAdmin();

   User createDecadev(DecadevDto dev, Long podId, Long stackId, Long squadId);

   void deleteDecadev(Long decadevId);
    APIResponse<Decadev> updateDecadev(DecadevDto decadevDto, Long decadevId, Long podId, Long stackId, Long squadId);
}

