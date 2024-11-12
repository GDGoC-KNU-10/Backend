package com.hackathon.nullnullteam.symptomrecord.service.dto;

import lombok.Builder;

import java.time.LocalDate;

public class SymptomRecordCommand {

    @Builder
    public record Add(
            String description,
            String symptomName,
            LocalDate startDate
    ){

    }
}
