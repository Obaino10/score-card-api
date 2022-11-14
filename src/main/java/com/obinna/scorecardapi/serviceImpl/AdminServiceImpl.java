package com.obinna.scorecardapi.serviceImpl;

import com.obinna.scorecardapi.dto.DecadevDto;
import com.obinna.scorecardapi.dto.responsedto.APIResponse;
import com.obinna.scorecardapi.enums.Role;
import com.obinna.scorecardapi.exception.CustomException;
import com.obinna.scorecardapi.exception.SquadNotFoundException;
import com.obinna.scorecardapi.exception.StackNotFoundException;
import com.obinna.scorecardapi.model.*;
import com.obinna.scorecardapi.repository.*;
import com.obinna.scorecardapi.response.AdminResponse;
import com.obinna.scorecardapi.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final PodRepository podRepository;
    private final StackRepository stackRepository;
    private final SquadRepository squadRepository;

    @Override
    public List<AdminResponse> getAllAdmin() {
        List<Admin> admins = adminRepository.findAll();
        List<AdminResponse> adminResponses = new ArrayList<>();
        if (admins.size() != 0){
            for (Admin admin : admins){
                adminResponses.add(new AdminResponse(admin));
            }
        }
        return adminResponses;
    }

    @Override
    public User createDecadev(DecadevDto decadev, Long podId, Long stackId, Long squadId) {
        if(userRepository.findByEmail(decadev.getEmail()).isPresent()){
            throw new CustomException("User email already exist");
        }
        Pod pod = podRepository.findById(podId).orElseThrow(()-> new CustomException("Not found"));
        Stack stack = stackRepository.findById(stackId).orElseThrow(()-> new StackNotFoundException("Not found"));
        Squad squad = squadRepository.findById(squadId).orElseThrow(()-> new SquadNotFoundException("Not found"));
        Decadev dev = new Decadev();
        dev.setFirstName(decadev.getFirstName());
        dev.setLastName(decadev.getLastName());
        dev.setGender(decadev.getGender());
        dev.setEmail(decadev.getEmail());
        dev.setDecadevId(decadev.getDecadevId());
        dev.setRole(decadev.getRole());
        dev.setSquad(squad);
        dev.setStack(stack);
        dev.setPod(pod);

        return userRepository.save(dev);

    }
    @Override
    public void deleteDecadev(Long decadevId) {
        User decadev =  userRepository.findUserByIdAndRole(decadevId, Role.DEV).orElseThrow(
                () -> new CustomException("User not found"));
        userRepository.delete(decadev);
    }

    @Override
    public APIResponse<Decadev> updateDecadev(DecadevDto decadevDto, Long decadevId, Long podId, Long stackId, Long squadId ) {
        Decadev decadev = (Decadev) userRepository.findById(decadevId).orElseThrow(() -> new CustomException("Decadev not found"));
        Pod pod = podRepository.findById(podId).orElseThrow(()-> new CustomException("Not found"));
        Stack stack = stackRepository.findById(stackId).orElseThrow(()-> new StackNotFoundException("Not found"));
        Squad squad = squadRepository.findById(squadId).orElseThrow(()-> new SquadNotFoundException("Not found"));
        decadev.setFirstName(decadevDto.getFirstName());
        decadev.setLastName(decadevDto.getLastName());
        decadev.setEmail(decadevDto.getEmail());
        decadev.setSquad(squad);
        decadev.setStack(stack);
        decadev.setPod(pod);
        userRepository.save(decadev);
        return new APIResponse<>(true, "Decadev updated successfully", decadev);

    }

}
