package com.hackathon.nullnullteam.anxietyresultrecord.service;

import com.hackathon.nullnullteam.anxietyresultrecord.AnxietyResultRecord;
import com.hackathon.nullnullteam.anxietyresultrecord.infrastructure.repository.AnxietyResultRecordRepository;
import com.hackathon.nullnullteam.anxietyresultrecord.service.dto.AnxietyResultRecordModel;
import com.hackathon.nullnullteam.anxietyresultrecord.service.dto.AnxietyResultRecordModel.Info;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnxietyResultRecordService {

    private final AnxietyResultRecordReaderService anxietyResultRecordReaderService;

    public List<AnxietyResultRecordModel.Info> getAnxietyResult(Long memberId) {
        List<AnxietyResultRecord> anxietyResultRecords = anxietyResultRecordReaderService.findByMemberId(memberId);
        System.out.println("aa" + anxietyResultRecords);
        return anxietyResultRecords.stream().map(Info::from).toList();
    }
}
