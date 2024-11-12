package com.hackathon.nullnullteam.symptomrecord.service;


import com.hackathon.nullnullteam.symptomrecord.SymptomRecord;
import com.hackathon.nullnullteam.symptomrecord.infrastructure.repository.SymptomRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SymptomRecordWriterService {

    private final SymptomRecordRepository symptomRecordRepository;

    @Transactional
    public void save(SymptomRecord symptomRecord){
        symptomRecordRepository.save(symptomRecord);
    }
}
