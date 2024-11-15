package com.hackathon.nullnullteam.vaccinationrecommend.controller;

import com.hackathon.nullnullteam.vaccinationrecommend.VaccinationRecommend;
import com.hackathon.nullnullteam.vaccinationrecommend.controller.dto.VaccinationRecommendResponse;
import com.hackathon.nullnullteam.vaccinationrecommend.service.VaccinationRecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vaccination")
public class VaccinationRecommendController {

    private final VaccinationRecommendService vaccinationRecommendService;

    @GetMapping("")
    public VaccinationRecommendResponse.Infos getRecommendedVaccinations(
            @PathVariable Long memberId) {
        List<VaccinationRecommend> recommendations = vaccinationRecommendService.getRecommendedVaccinations(memberId);
        return VaccinationRecommendResponse.Infos.from(recommendations);
    }
}
