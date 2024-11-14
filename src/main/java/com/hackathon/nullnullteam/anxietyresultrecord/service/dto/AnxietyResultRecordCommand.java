package com.hackathon.nullnullteam.anxietyresultrecord.service.dto;

import com.hackathon.nullnullteam.anxietyresultrecord.AnxietyResultRecord;
import com.hackathon.nullnullteam.member.AnxietyLevel;
import com.hackathon.nullnullteam.member.Member;
import lombok.Builder;

public class AnxietyResultRecordCommand {

    @Builder
    public record Result(
        int count
    ) {

        public AnxietyResultRecord toEntity(Member member) {
            return AnxietyResultRecord.builder()
                .member(member)
                .anxietyLevel(AnxietyLevel.fromString(count))
                .anxietyScore(count)
                .build();
        }
    }
}
