package com.hackathon.nullnullteam.anxietyresultrecord.service;

import com.hackathon.nullnullteam.anxietyresultrecord.AnxietyResultRecord;
import com.hackathon.nullnullteam.anxietyresultrecord.infrastructure.repository.AnxietyResultRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AnxietyResultRecordWriterService {

    private final AnxietyResultRecordRepository anxietyResultRecordRepository;

    @Transactional
    public void save(AnxietyResultRecord anxietyResultRecord) {
        anxietyResultRecordRepository.save(anxietyResultRecord);
    }
}
