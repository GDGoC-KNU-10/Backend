package com.hackathon.nullnullteam.symptomrecord.service.dto;

import com.hackathon.nullnullteam.member.Member;
import com.hackathon.nullnullteam.symptomrecord.SymptomRecord;
import lombok.Builder;

import java.time.LocalDate;

public class SymptomRecordCommand {

    @Builder
    public record Add(
            String description,
            String symptomName,
            LocalDate startDate
    ){
        public SymptomRecord toEntity(Member member){
            return  SymptomRecord.builder()
                    .symptomName(symptomName)
                    .description(description)
                    .startDate(startDate)
                    .member(member)
                    .build();
        }
    }
}
