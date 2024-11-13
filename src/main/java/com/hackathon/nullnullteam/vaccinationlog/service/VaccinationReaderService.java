package com.hackathon.nullnullteam.vaccinationlog.service;

import com.hackathon.nullnullteam.vaccinationlog.VaccinationLog;
import com.hackathon.nullnullteam.vaccinationlog.infrastructure.repository.VaccinationLogRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VaccinationReaderService {

    private final VaccinationLogRepository vaccinationLogRepository;

    public List<VaccinationLog> getVaccinationLogs(String memberName) {
        return vaccinationLogRepository.findByUsername(memberName);
    }
}
