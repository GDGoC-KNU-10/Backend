package com.hackathon.nullnullteam.vaccinationrecommend.service;

import com.hackathon.nullnullteam.member.Member;
import com.hackathon.nullnullteam.member.infrastructure.repository.MemberRepository;
import com.hackathon.nullnullteam.member.service.MemberReaderService;
import com.hackathon.nullnullteam.vaccinationrecommend.VaccinationRecommend;
import com.hackathon.nullnullteam.vaccinationrecommend.infrastructure.repository.VaccinationRecommendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VaccinationRecommendService {

    private final VaccinationRecommendRepository vaccinationRecommendRepository;
    private final MemberReaderService memberReaderService;


    public List<VaccinationRecommend> getRecommendedVaccinations(Long memberId) {
        Member member = memberReaderService.getMemberById(memberId);

        List<VaccinationRecommend> recommendations = vaccinationRecommendRepository.findAllByStartAgeLessThanEqualAndEndAgeGreaterThanEqual(member.getAge());
        return recommendations;
    }
}