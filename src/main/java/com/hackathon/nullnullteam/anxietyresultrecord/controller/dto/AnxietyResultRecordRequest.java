package com.hackathon.nullnullteam.anxietyresultrecord.controller.dto;

import com.hackathon.nullnullteam.anxietyresultrecord.service.dto.AnxietyResultRecordCommand;
import lombok.Builder;

public class AnxietyResultRecordRequest {

    @Builder
    public record Result(
        int score
    ) {

        public AnxietyResultRecordCommand.Result toCommand() {
            return AnxietyResultRecordCommand.Result.builder()
                .score(score)
                .build();
        }
    }
}
