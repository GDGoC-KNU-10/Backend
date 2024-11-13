package com.hackathon.nullnullteam.vaccinationlog.service;

import com.hackathon.nullnullteam.global.constants.MessageConstants;
import com.hackathon.nullnullteam.global.exception.EntityNotFoundException;
import com.hackathon.nullnullteam.vaccinationlog.VaccinationLog;
import com.hackathon.nullnullteam.vaccinationlog.infrastructure.repository.VaccinationLogRepository;
import com.hackathon.nullnullteam.vaccinationlog.infrastructure.repository.VaccinationRecommendRepository;
import com.hackathon.nullnullteam.vaccinationlog.infrastructure.repository.dto.VaccinationRecommendDto;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VaccinationReaderService {

    private final VaccinationLogRepository vaccinationLogRepository;
    private final VaccinationRecommendRepository vaccinationRecommendRepository;

    public List<VaccinationLog> getVaccinationLogs(String memberName) {
        return vaccinationLogRepository.findByUsername(memberName);
    }

    public VaccinationRecommendDto getVaccinationRecommendById(Long id) {
        return vaccinationRecommendRepository.findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException(MessageConstants.VACCINE_NOT_FOUND_MESSAGE)
            );
    }
}
