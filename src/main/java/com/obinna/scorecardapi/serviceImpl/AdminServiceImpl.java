package com.obinna.scorecardapi.serviceImpl;

import com.obinna.scorecardapi.dto.DecadevDto;
import com.obinna.scorecardapi.dto.WeeklyScoreDto;
import com.obinna.scorecardapi.dto.responsedto.APIResponse;
import com.obinna.scorecardapi.enums.Role;
import com.obinna.scorecardapi.exception.CustomException;
import com.obinna.scorecardapi.exception.SquadNotFoundException;
import com.obinna.scorecardapi.exception.StackNotFoundException;
import com.obinna.scorecardapi.exception.UserNotFoundException;
import com.obinna.scorecardapi.model.*;
import com.obinna.scorecardapi.repository.*;
import com.obinna.scorecardapi.response.AdminResponse;
import com.obinna.scorecardapi.service.AdminService;
import com.obinna.scorecardapi.utility.CalculateScores;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final DecadevRepository decadevRepository;
    private final WeeklyScoreRepository scoreRepository;
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
    public WeeklyScore decadevWeeklyScore(WeeklyScoreDto score, Long id) {
        if((score.getAlgorithmScore() < 0|| score.getAlgorithmScore() > 100.0)
                || (score.getWeeklyTask() < 0 || score.getWeeklyTask() > 100.0)
                || (score.getWeeklyAssessment() < 0 || score.getWeeklyAssessment() > 100.0)
                || (score.getAgileTest() < 0 || score.getAgileTest() >100.0)
                || (score.getQaTest() < 0 || score.getQaTest() > 100.0 )){
            throw new CustomException("Decadev score shouldn't be less than zero(0) or greater than 100 ");
        }

        Decadev dev = decadevRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("No Decadev with the ID: " + id));
        if (scoreRepository.findWeeklyScoreByWeekAndDecadev(score.getWeek(), dev) != null) {
            throw new CustomException("Weekly score already populated");
        }
        WeeklyScore devWeeklyScore = new WeeklyScore();
        double result = CalculateScores.weeklyCumulative(score.getWeeklyTask(),
                score.getAlgorithmScore(), score.getQaTest(), score.getAgileTest(), score.getWeeklyAssessment());
        devWeeklyScore.setAlgorithmScore(score.getAlgorithmScore());
        devWeeklyScore.setWeeklyAssessment(score.getWeeklyAssessment());
        devWeeklyScore.setQaTest(score.getQaTest());
        devWeeklyScore.setAgileTest(score.getAgileTest());
        devWeeklyScore.setWeeklyTask(score.getWeeklyTask());
        devWeeklyScore.setWeek(score.getWeek());
        devWeeklyScore.setDecadev(dev);
        devWeeklyScore.setCumulativeScore(result);
        return scoreRepository.save(devWeeklyScore);
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
