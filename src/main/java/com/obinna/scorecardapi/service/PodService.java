

package com.obinna.scorecardapi.service;

import com.obinna.scorecardapi.dto.PodRequestDto;
import com.obinna.scorecardapi.dto.PodResponseDto;

public interface PodService {
    PodResponseDto createPod(Long id, PodRequestDto requestDto);

    PodResponseDto updatePod(Long id, PodRequestDto requestDto);
}