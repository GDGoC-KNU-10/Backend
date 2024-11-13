package com.hackathon.nullnullteam.anxietyresultrecord.service.dto;

import com.hackathon.nullnullteam.anxietyresultrecord.AnxietyResultRecord;
import lombok.Builder;

import java.time.LocalDate;

public class AnxietyResultRecordModel {

    @Builder
    public record Info(
            String name,
            int anxietyScore,
            String anxietyLevel,
            LocalDate testDate
    ) {

        public static AnxietyResultRecordModel.Info from(AnxietyResultRecord anxietyResultRecord) {
            return Info.builder()
                    .name(anxietyResultRecord.getMember().getName())
                    .anxietyScore(anxietyResultRecord.getAnxietyScore())
                    .anxietyLevel(anxietyResultRecord.getAnxietyLevel().getDescription())
                    .testDate(anxietyResultRecord.getCreatedAt().toLocalDate())
                    .build();
        }
    }
}
