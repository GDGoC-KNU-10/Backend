package com.hackathon.nullnullteam.anxietyresultrecord.service;

import com.hackathon.nullnullteam.anxietyresultrecord.AnxietyResultRecord;
import com.hackathon.nullnullteam.anxietyresultrecord.infrastructure.repository.AnxietyResultRecordRepository;
import com.hackathon.nullnullteam.global.constants.MessageConstants;
import com.hackathon.nullnullteam.global.exception.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnxietyResultRecordReaderService {

    private final AnxietyResultRecordRepository anxietyResultRecordRepository;

    public List<AnxietyResultRecord> findByMemberId(Long memberId) {
        return anxietyResultRecordRepository.findByMemberId(memberId);
    }
}
