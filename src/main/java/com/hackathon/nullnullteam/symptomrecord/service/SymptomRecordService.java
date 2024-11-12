package com.hackathon.nullnullteam.symptomrecord.service;

import com.hackathon.nullnullteam.global.constants.DateConstants;
import com.hackathon.nullnullteam.member.Member;
import com.hackathon.nullnullteam.member.service.MemberReaderService;
import com.hackathon.nullnullteam.symptomrecord.SymptomRecord;
import com.hackathon.nullnullteam.symptomrecord.service.dto.SymptomRecordCommand;
import com.hackathon.nullnullteam.symptomrecord.service.dto.SymptomRecordModel;
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
public class SymptomRecordService {

    private final MemberReaderService memberReaderService;
    private final SymptomRecordReaderService symptomRecordReaderService;
    private final SymptomRecordWriterService symptomRecordWriterService;

    @Transactional
    public void addSymptomRecord(Long memberId, SymptomRecordCommand.Add command){
        Member member = memberReaderService.getMemberById(memberId);

        SymptomRecord symptomRecord = command.toEntity(member);

        symptomRecordWriterService.save(symptomRecord);
    }

    @Transactional(readOnly = true)
    public Page<SymptomRecordModel.Info> getAllSymptomRecord(Long memberId, Pageable pageable){
        Member member = memberReaderService.getMemberById(memberId);

        Page<SymptomRecord> symptomRecordPage = symptomRecordReaderService.getSymptomListByMember(member, pageable);

        return symptomRecordPage.map(SymptomRecordModel.Info::from);

    }

    @Transactional(readOnly = true)
    public Page<SymptomRecordModel.Info> getMontlySymptomRecord(Long memberId, LocalDate date, Pageable pageable){
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
        Page<SymptomRecord> symptomRecordList = symptomRecordReaderService.getMonthlySymptomListByMember(member, startDate, endDate, pageable);
        return symptomRecordList.map(SymptomRecordModel.Info::from);

    }


}
