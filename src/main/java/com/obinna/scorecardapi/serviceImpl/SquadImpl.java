package com.obinna.scorecardapi.serviceImpl;

import com.obinna.scorecardapi.exception.SquadNotFoundException;
import com.obinna.scorecardapi.model.Squad;
import com.obinna.scorecardapi.repository.SquadRepository;
import com.obinna.scorecardapi.service.SquadService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SquadImpl implements SquadService {
   private  final SquadRepository squadRepository;


    @Override
    public Squad getSquad(Long id) {
        Squad squad = squadRepository.findById(id).orElseThrow(()-> new SquadNotFoundException("Squad not found"));

        return squad;
    }
}
