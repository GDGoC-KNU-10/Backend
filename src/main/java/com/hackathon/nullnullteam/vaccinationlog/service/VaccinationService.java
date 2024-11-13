package com.hackathon.nullnullteam.vaccinationlog.service;

import com.hackathon.nullnullteam.member.Member;
import com.hackathon.nullnullteam.member.service.MemberReaderService;
import com.hackathon.nullnullteam.vaccinationlog.VaccinationLog;
import com.hackathon.nullnullteam.vaccinationlog.infrastructure.repository.VaccinationRecommendRepository;
import com.hackathon.nullnullteam.vaccinationlog.infrastructure.repository.dto.VaccinationRecommendDto;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VaccinationService {

    private final VaccinationReaderService vaccinationReaderService;
    private final VaccinationRecommendRepository vaccinationRecommendRepository;
    private final MemberReaderService memberReaderService;

    public List<VaccinationRecommendDto> getVaccinationRecommends(Long memberId) {
        Member member = memberReaderService.getMemberById(memberId);

        // 1. 유저명으로 예방접종 로그에서 예방접종 이름 목록 조회
        List<VaccinationLog> vaccinationLogs = vaccinationReaderService.getVaccinationLogs(member.getName());

        // 2. 예방접종 이름으로 추천 데이터를 조회하여 리스트에 추가
        List<VaccinationRecommendDto> recommendations = new ArrayList<>();
        for (VaccinationLog log : vaccinationLogs) {
            vaccinationRecommendRepository.findByVaccineName(log.getVaccinationName()).ifPresent(recommendations::add);
        }

        return recommendations;
    }

    public VaccinationRecommendDto getVaccinationRecommend(Long id) {
        return vaccinationReaderService.getVaccinationRecommendById(id);
    }
}
