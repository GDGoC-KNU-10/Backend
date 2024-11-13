package com.hackathon.nullnullteam.symptomrecord.service;


import com.hackathon.nullnullteam.member.Member;
import com.hackathon.nullnullteam.symptomrecord.SymptomRecord;
import com.hackathon.nullnullteam.symptomrecord.infrastructure.repository.SymptomRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SymptomRecordReaderService {

    private final SymptomRecordRepository symptomRecordRepository;

    @Transactional(readOnly = true)
    public Page<SymptomRecord> getSymptomListByMember(Member member, Pageable pageable) {
        return symptomRecordRepository.getAllByMember(member, pageable);
    }

    @Transactional(readOnly = true)
    public Page<SymptomRecord> getMonthlySymptomListByMember(Member member, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        return symptomRecordRepository.getAllByMemberAndCreatedAtBetween(member, startDate, endDate, pageable);
    }
}
