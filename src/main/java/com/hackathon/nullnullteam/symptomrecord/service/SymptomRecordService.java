package com.hackathon.nullnullteam.symptomrecord.service;

import com.hackathon.nullnullteam.symptomrecord.service.dto.SymptomRecordCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SymptomRecordService {

    @Transactional
    public void addSymptomRecord(Long userId, SymptomRecordCommand.Add command){

    }


}
