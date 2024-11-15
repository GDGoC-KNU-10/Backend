package com.hackathon.nullnullteam.hospitalstatistics.service;

import com.hackathon.nullnullteam.global.exception.EntityNotFoundException;
import com.hackathon.nullnullteam.hospitalstatistics.HospitalStatistics;
import com.hackathon.nullnullteam.hospitalstatistics.ResultType;
import com.hackathon.nullnullteam.hospitalstatistics.controller.dto.HospitalStatisticsResponse;
import com.hackathon.nullnullteam.hospitalstatistics.infrastructure.repository.HospitalStatisticsRepository;
import com.hackathon.nullnullteam.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    public HospitalStatistics getByMemberAndDate(Member member, LocalDate date) {
        return hospitalStatisticsRepository.findByMemberAndDate(member, date)
                .orElseThrow(() -> new EntityNotFoundException("해당 날짜의 병원 기록이 없습니다."));
    }

    public HospitalStatisticsResponse.Monthly getMonthlyStatistics(
            Member member, LocalDateTime startDate, LocalDateTime endDate) {

        List<HospitalStatistics> statistics = hospitalStatisticsRepository
                .findAllByMemberAndCreatedAtBetween(member, startDate, endDate);

        Long total = (long) statistics.size();
        Long highCount = statistics.stream()
                .filter(s -> s.getResult() == ResultType.HIGH)
                .count();
        Long mediumCount = statistics.stream()
                .filter(s -> s.getResult() == ResultType.MEDIUM)
                .count();
        Long lowCount = statistics.stream()
                .filter(s -> s.getResult() == ResultType.LOW)
                .count();

        return HospitalStatisticsResponse.Monthly.from(total, highCount, mediumCount, lowCount);
    }
}