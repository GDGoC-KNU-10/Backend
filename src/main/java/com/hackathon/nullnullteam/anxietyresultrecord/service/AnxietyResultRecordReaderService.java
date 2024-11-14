package com.hackathon.nullnullteam.anxietyresultrecord.service;

import com.hackathon.nullnullteam.anxietyresultrecord.AnxietyResultRecord;
import com.hackathon.nullnullteam.anxietyresultrecord.infrastructure.repository.AnxietyResultRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnxietyResultRecordReaderService {

    private final AnxietyResultRecordRepository anxietyResultRecordRepository;

    public List<AnxietyResultRecord> findByMemberId(Long memberId) {
        return anxietyResultRecordRepository.findByMemberId(memberId);
    }
}
