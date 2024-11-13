package com.hackathon.nullnullteam.vaccinationlog.controller;

import com.hackathon.nullnullteam.global.annotation.Authenticate;
import com.hackathon.nullnullteam.vaccinationlog.infrastructure.repository.dto.VaccinationRecommendDto;
import com.hackathon.nullnullteam.vaccinationlog.service.VaccinationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vaccination")
public class VaccinationController {

    private final VaccinationService vaccinationService;

    @GetMapping("")
    public List<VaccinationRecommendDto> getVaccinationRecommends(@Authenticate Long memberId) {
        List<VaccinationRecommendDto> vaccinationRecommendDtos = vaccinationService.getVaccinationRecommends(memberId);
        return vaccinationRecommendDtos;
    }

    @GetMapping("/{vaccination-id}")
    public VaccinationRecommendDto getVaccinationRecommend(
        @Authenticate Long memberId,
        @PathVariable("vaccination-id") Long vaccineId
        ) {
        VaccinationRecommendDto vaccinationRecommend = vaccinationService.getVaccinationRecommend(
            vaccineId);
        return vaccinationRecommend;
    }
}
