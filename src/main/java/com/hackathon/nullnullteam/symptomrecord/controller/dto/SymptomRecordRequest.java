package com.hackathon.nullnullteam.symptomrecord.controller.dto;

import com.hackathon.nullnullteam.symptomrecord.service.dto.SymptomRecordCommand;
import lombok.Builder;

import java.time.LocalDate;

public class SymptomRecordRequest {

    @Builder
    public record Add(
            String description,
            String symptomName,
            LocalDate startDate
    ) {
        public SymptomRecordCommand.Add toCommand() {
            return SymptomRecordCommand.Add.builder()
                    .description(description)
                    .symptomName(symptomName)
                    .startDate(startDate)
                    .build();
        }


    }

}
