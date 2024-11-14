package com.hackathon.nullnullteam.lifestyle.service;

import com.hackathon.nullnullteam.global.constants.DateConstants;
import com.hackathon.nullnullteam.lifestyle.LifeStyle;
import com.hackathon.nullnullteam.lifestyle.service.dto.LifeStyleCommand;
import com.hackathon.nullnullteam.lifestyle.service.dto.LifeStyleModel;
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
public class LifeStyleService {

    private final MemberReaderService memberReaderService;
    private final LifeStyleWriterService lifeStyleWriterService;
    private final LifeStyleReaderService lifeStyleReaderService;

    @Transactional
    public void addLifeStyle(Long memberId, LifeStyleCommand.Add command) {
        Member member = memberReaderService.getMemberById(memberId);

        LifeStyle lifeStyle = command.toEntity();

        lifeStyleWriterService.save(lifeStyle);
    }

    @Transactional(readOnly = true)
    public LifeStyleModel.Info getLifeStyle(Long memberId, Long lifeStyleId) {
        Member member = memberReaderService.getMemberById(memberId);

        LifeStyle lifeStyle = lifeStyleReaderService.getLifeStyle(lifeStyleId);

        return LifeStyleModel.Info.from(lifeStyle);
    }

    @Transactional(readOnly = true)
    public Page<LifeStyleModel.Info> getAllLifeStyles(Long memberId, Pageable pageable) {
        Member member = memberReaderService.getMemberById(memberId);

        Page<LifeStyle> allLifeStyles = lifeStyleReaderService.getAllLifeStyles(member, pageable);

        return allLifeStyles.map(LifeStyleModel.Info::from);
    }

    @Transactional(readOnly = true)
    public Page<LifeStyleModel.Info> getMonthlyLifeStyles(Long memberId, Pageable pageable, LocalDate date) {
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

        Page<LifeStyle> monthlyLifeStyles = lifeStyleReaderService.getMonthlyLifeStyles(member, pageable, startDate, endDate);

        return monthlyLifeStyles.map(LifeStyleModel.Info::from);
    }

}
