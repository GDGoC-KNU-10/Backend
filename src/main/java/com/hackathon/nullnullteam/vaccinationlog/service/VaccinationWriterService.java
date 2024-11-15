package com.hackathon.nullnullteam.vaccinationlog.service;

import com.hackathon.nullnullteam.vaccinationlog.VaccinationLog;
import com.hackathon.nullnullteam.vaccinationlog.infrastructure.repository.VaccinationLogRepository;
import com.hackathon.nullnullteam.vaccinationlog.infrastructure.repository.dto.VaccinationRecommendDto;
import com.hackathon.nullnullteam.vaccinationrecommend.VaccinationRecommend;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VaccinationWriterService {

    private final VaccinationLogRepository vaccinationLogRepository;

    @Transactional
    public void save(String memberName, VaccinationRecommend vaccinationRecommend) {
        VaccinationLog vaccinationLog = VaccinationLog.builder()
            .username(memberName)
            .vaccinationName(vaccinationRecommend.getName())
            .build();

        vaccinationLogRepository.save(vaccinationLog);
    }
}
