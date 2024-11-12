package com.hackathon.nullnullteam.hospitalstatistics.service;

import com.hackathon.nullnullteam.hospitalstatistics.HospitalStatistics;
import com.hackathon.nullnullteam.hospitalstatistics.infrastructure.repository.HospitalStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HospitalStatisticsWriterService {
    private final HospitalStatisticsRepository hospitalStatisticsRepository;

    @Transactional
    public void save(HospitalStatistics hospitalStatistics) {
        hospitalStatisticsRepository.save(hospitalStatistics);
    }
}