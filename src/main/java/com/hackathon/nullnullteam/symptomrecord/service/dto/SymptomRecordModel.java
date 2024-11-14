package com.hackathon.nullnullteam.symptomrecord.service.dto;

import com.hackathon.nullnullteam.symptomrecord.SymptomRecord;
import lombok.Builder;

import java.time.LocalDate;

public class SymptomRecordModel {

    @Builder
    public record Info(
            Long userId,
            LocalDate startDate,
            String description,
            String symptomName
    ) {
        public static SymptomRecordModel.Info from(SymptomRecord symptomRecord) {
            return Info.builder()
                    .userId(symptomRecord.getMember().getId())
                    .startDate(symptomRecord.getStartDate())
                    .description(symptomRecord.getDescription())
                    .symptomName(symptomRecord.getSymptomName())
                    .build();
        }

    }
}
