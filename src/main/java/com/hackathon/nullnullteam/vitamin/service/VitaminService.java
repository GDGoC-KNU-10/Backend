package com.hackathon.nullnullteam.vitamin.service;

import com.hackathon.nullnullteam.global.constants.DateConstants;
import com.hackathon.nullnullteam.member.Member;
import com.hackathon.nullnullteam.member.service.MemberReaderService;
import com.hackathon.nullnullteam.vitamin.Vitamin;
import com.hackathon.nullnullteam.vitamin.service.dto.VitaminCommand;
import com.hackathon.nullnullteam.vitamin.service.dto.VitaminModel;
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
public class VitaminService {

    private final MemberReaderService memberReaderService;
    private final VitaminReaderService vitaminReaderService;
    private final VitaminWriterService vitaminWriterService;

    @Transactional
    public void addVitamin(Long memberId, VitaminCommand.Add command){
        Member member = memberReaderService.getMemberById(memberId);

        Vitamin vitamin = command.toEntity(member);

        vitaminWriterService.save(vitamin);
    }

    @Transactional(readOnly = true)
    public VitaminModel.Info getVitamin(Long memberId, Long vitaminId) {
        Member member = memberReaderService.getMemberById(memberId);
        Vitamin vitamin = vitaminReaderService.getVitaminById(vitaminId);
        return VitaminModel.Info.from(vitamin);
    }

    @Transactional(readOnly = true)
    public Page<VitaminModel.Info> getAllVitamins(Long memberId, Pageable pageable) {
        Member member = memberReaderService.getMemberById(memberId);
        Page<Vitamin> vitamins = vitaminReaderService.getAllByMember(member, pageable);
        return vitamins.map(VitaminModel.Info::from);
    }

    @Transactional(readOnly = true)
    public Page<VitaminModel.Info> getMonthlyVitamins(Long memberId, LocalDate date, Pageable pageable) {
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

        Page<Vitamin> vitamins = vitaminReaderService.getMonthlyByMember(member, startDate, endDate, pageable);
        return vitamins.map(VitaminModel.Info::from);
    }


}
