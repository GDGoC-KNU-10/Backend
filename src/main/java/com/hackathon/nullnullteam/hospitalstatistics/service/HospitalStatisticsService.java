package com.hackathon.nullnullteam.hospitalstatistics.service;

import com.hackathon.nullnullteam.global.constants.DateConstants;
import com.hackathon.nullnullteam.hospitalstatistics.HospitalStatistics;
import com.hackathon.nullnullteam.hospitalstatistics.service.dto.HospitalStatisticsCommand;
import com.hackathon.nullnullteam.hospitalstatistics.service.dto.HospitalStatisticsModel;
import com.hackathon.nullnullteam.member.Member;
import com.hackathon.nullnullteam.member.service.MemberReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class HospitalStatisticsService {
    private final MemberReaderService memberReaderService;
    private final HospitalStatisticsReaderService hospitalStatisticsReaderService;
    private final HospitalStatisticsWriterService hospitalStatisticsWriterService;

    @Transactional
    public void addHospitalStatistics(Long memberId, HospitalStatisticsCommand.Add command) {
        Member member = memberReaderService.getMemberById(memberId);
        HospitalStatistics hospitalStatistics = command.toEntity(member);
        hospitalStatisticsWriterService.save(hospitalStatistics);
    }

    @Transactional(readOnly = true)
    public HospitalStatisticsModel.Info getHospitalStatistics(Long memberId, Long hospitalStatisticsId) {
        Member member = memberReaderService.getMemberById(memberId);
        HospitalStatistics hospitalStatistics = hospitalStatisticsReaderService.getHospitalStatisticsById(hospitalStatisticsId);
        return HospitalStatisticsModel.Info.from(hospitalStatistics);
    }

    @Transactional(readOnly = true)
    public Page<HospitalStatisticsModel.Info> getAllHospitalStatistics(Long memberId, Pageable pageable) {
        Member member = memberReaderService.getMemberById(memberId);
        Page<HospitalStatistics> hospitalStatistics = hospitalStatisticsReaderService.getAllByMember(member, pageable);
        return hospitalStatistics.map(HospitalStatisticsModel.Info::from);
    }

    @Transactional(readOnly = true)
    public Page<HospitalStatisticsModel.Info> getMonthlyHospitalStatistics(
            Long memberId, LocalDate date, Pageable pageable) {
        Member member = memberReaderService.getMemberById(memberId);

        LocalDateTime startDate;
        LocalDateTime endDate;

        if (date == null) {
            startDate = DateConstants.DEFAULT_START_DATE;
            endDate = LocalDateTime.now();
        } else {
            startDate = date.withDayOfMonth(1).atStartOfDay();
            endDate = date.withDayOfMonth(date.lengthOfMonth()).atTime(LocalTime.MAX);
        }

        Page<HospitalStatistics> hospitalStatistics =
                hospitalStatisticsReaderService.getMonthlyByMember(member, startDate, endDate, pageable);
        return hospitalStatistics.map(HospitalStatisticsModel.Info::from);
    }
}