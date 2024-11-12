package com.hackathon.nullnullteam.hospitalstatistics.service;

import com.hackathon.nullnullteam.global.exception.EntityNotFoundException;
import com.hackathon.nullnullteam.hospitalstatistics.HospitalStatistics;
import com.hackathon.nullnullteam.hospitalstatistics.infrastructure.repository.HospitalStatisticsRepository;
import com.hackathon.nullnullteam.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HospitalStatisticsReaderService {
    private final HospitalStatisticsRepository hospitalStatisticsRepository;

    public HospitalStatistics getHospitalStatisticsById(Long hospitalStatisticsId) {
        return hospitalStatisticsRepository.findById(hospitalStatisticsId)
                .orElseThrow(() -> new EntityNotFoundException("병원 기록을 찾을 수 없습니다."));
    }

    public Page<HospitalStatistics> getAllByMember(Member member, Pageable pageable) {
        return hospitalStatisticsRepository.findAllByMember(member, pageable);
    }

    public Page<HospitalStatistics> getMonthlyByMember(
            Member member, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        return hospitalStatisticsRepository.findAllByMemberAndCreatedAtBetween(
                member, startDate, endDate, pageable);
    }
}