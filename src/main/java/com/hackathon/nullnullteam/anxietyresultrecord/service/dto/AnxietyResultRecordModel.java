package com.hackathon.nullnullteam.anxietyresultrecord.service.dto;

import com.hackathon.nullnullteam.anxietyresultrecord.AnxietyResultRecord;
import com.hackathon.nullnullteam.anxietyresultrecord.controller.dto.AnxietyResultRecordResponse;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;

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
