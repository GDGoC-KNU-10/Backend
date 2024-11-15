package com.hackathon.nullnullteam.vaccinationlog.service;

import com.hackathon.nullnullteam.member.service.MemberReaderService;
import com.hackathon.nullnullteam.vaccinationlog.infrastructure.repository.dto.VaccinationRecommendDto;
import com.hackathon.nullnullteam.vaccinationrecommend.infrastructure.repository.VaccinationRecommendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VaccinationService {

    private final VaccinationReaderService vaccinationReaderService;
    private final VaccinationRecommendRepository vaccinationRecommendRepository;
    private final MemberReaderService memberReaderService;

//    public Page<VaccinationRecommendDto> getVaccinationRecommends(Long memberId, Pageable pageable) {
//        Member member = memberReaderService.getMemberById(memberId);
//
//        // 1. 유저명으로 예방접종 로그에서 예방접종 이름 목록 조회
//        List<VaccinationLog> vaccinationLogs = vaccinationReaderService.getVaccinationLogs(member.getName());
//
//        // 2. 예방접종 로그에서 예방접종 이름 목록 추출
//        List<String> vaccinationNames = vaccinationLogs.stream()
//            .map(VaccinationLog::getVaccinationName)
//            .collect(Collectors.toList());
//
//        // 3. 예방접종 이름 목록으로 추천 데이터를 페이징 처리하여 조회
//        return vaccinationRecommendRepository.findByVaccineNamesWithPaging(vaccinationNames, pageable);
//    }
//
//    public VaccinationRecommendDto getVaccinationRecommend(Long id) {
//        return vaccinationReaderService.getVaccinationRecommendById(id);
//    }
}
