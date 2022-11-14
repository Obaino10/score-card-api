package com.obinna.scorecardapi.serviceImpl;

import com.obinna.scorecardapi.dto.PodRequestDto;
import com.obinna.scorecardapi.dto.PodResponseDto;
import com.obinna.scorecardapi.exception.ResourceNotFoundException;
import com.obinna.scorecardapi.model.Admin;
import com.obinna.scorecardapi.model.Pod;
import com.obinna.scorecardapi.model.Stack;
import com.obinna.scorecardapi.repository.AdminRepository;
import com.obinna.scorecardapi.repository.PodRepository;
import com.obinna.scorecardapi.repository.StackRepository;
import com.obinna.scorecardapi.service.PodService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PodServiceImpl implements PodService {
    private final PodRepository podRepository;
    private final StackRepository stackRepository;
    private final ModelMapper modelMapper;
    private final AdminRepository adminRepository;


    @Override
    public PodResponseDto createPod(Long id, PodRequestDto requestDto) {
        List<Admin> adminList = new ArrayList();
        Stack stack = stackRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("stackname", "id", id));
        Admin stackAssociate = adminRepository.findAdminByEmail(requestDto.getStackAssociateByEmail()).orElseThrow(()->new ResourceNotFoundException("stack Associate", "id", id));

        Admin programAssociate = adminRepository.findAdminByEmail(requestDto.getProgramAssociateByEmail()).orElseThrow(()->new ResourceNotFoundException("stack Associate", "id", id));
        adminList.add(stackAssociate);
        adminList.add(programAssociate);

        Pod pod = new Pod();
        pod.setStack(stack);
        pod.setPodName(requestDto.getPodName());
        pod.setAdmin(adminList);
        return modelMapper.map(podRepository.save(pod), PodResponseDto.class);

    }

    @Override
    public PodResponseDto updatePod(Long id, PodRequestDto requestDto) {

        return podRepository.findById(id).map(podUpdate-> {
            modelMapper.map(requestDto, podUpdate);
            Pod updated = podRepository.save(podUpdate);
            return modelMapper.map(updated, PodResponseDto.class);
        }).orElseThrow(()-> new ResourceNotFoundException("pod not found", "id", id));
    }
}