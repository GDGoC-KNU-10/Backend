package com.hackathon.nullnullteam.symptomrecord.service;


import com.hackathon.nullnullteam.symptomrecord.SymptomRecord;
import com.hackathon.nullnullteam.symptomrecord.infrastructure.repository.SymptomRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SymptomRecordReaderService {

    private final SymptomRecordRepository symptomRecordRepository;

    @Transactional(readOnly = true)
    public Page<SymptomRecord> getAllByMemberId(Long memberId, Pageable pageable){
        return symptomRecordRepository.getAllByMemberId(memberId, pageable);
    }
}
