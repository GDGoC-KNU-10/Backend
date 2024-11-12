package com.hackathon.nullnullteam.symptomrecord.service;

import com.hackathon.nullnullteam.member.Member;
import com.hackathon.nullnullteam.member.service.MemberReaderService;
import com.hackathon.nullnullteam.symptomrecord.SymptomRecord;
import com.hackathon.nullnullteam.symptomrecord.service.dto.SymptomRecordCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SymptomRecordService {

    private final MemberReaderService memberReaderService;
    private final SymptomRecordWriterService symptomRecordWriterService;

    @Transactional
    public void addSymptomRecord(Long memberId, SymptomRecordCommand.Add command){
        Member member = memberReaderService.getMemberById(memberId);

        SymptomRecord symptomRecord = command.toEntity(member);

        symptomRecordWriterService.save(symptomRecord);
    }


}
