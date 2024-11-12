package com.hackathon.nullnullteam.anxietyresultrecord.controller.dto;

import com.hackathon.nullnullteam.anxietyresultrecord.service.dto.AnxietyResultRecordModel;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;

public class AnxietyResultRecordResponse {

    @Builder
    public record Info(
        String name,
        int anxietyScore,
        String anxietyLevel,
        LocalDate testDate
    ) {

        public static Info from(AnxietyResultRecordModel.Info model) {
            return Info.builder()
                .name(model.name())
                .anxietyScore(model.anxietyScore())
                .anxietyLevel(model.anxietyLevel())
                .testDate(model.testDate())
                .build();
        }
    }

    @Builder
    public record Infos(
        List<Info> infos
    ) {

        public static Infos from(List<Info> infos) {
            return Infos.builder()
                .infos(infos)
                .build();
        }
    }
}
