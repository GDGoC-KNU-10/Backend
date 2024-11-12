package com.hackathon.nullnullteam.symptomrecord.service;

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

        Page<SymptomRecord> symptomRecordPage = symptomRecordReaderService.getAllByMemberId(memberId, pageable);

        return symptomRecordPage.map(SymptomRecordModel.Info::from);

    }


}
