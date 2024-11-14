package com.hackathon.nullnullteam.vaccinationlog.service;

import com.hackathon.nullnullteam.vaccinationlog.VaccinationLog;
import com.hackathon.nullnullteam.vaccinationlog.infrastructure.repository.VaccinationLogRepository;
import com.hackathon.nullnullteam.vaccinationlog.infrastructure.repository.dto.VaccinationRecommendDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VaccinationWriterService {

    private final VaccinationLogRepository vaccinationLogRepository;

    @Transactional
    public void save(String memberName, VaccinationRecommendDto vaccinationRecommendDto) {
        VaccinationLog vaccinationLog = VaccinationLog.builder()
            .username(memberName)
            .vaccinationName(vaccinationRecommendDto.vaccineName())
            .build();

        vaccinationLogRepository.save(vaccinationLog);
    }
}
