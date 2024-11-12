package com.hackathon.nullnullteam.symptomrecord.controller.dto;

import lombok.Builder;

import java.time.LocalDate;

public class SymptomRecordResponse {

    @Builder
    public record Info(
            Long userId,
            LocalDate startDate,
            String description,
            String symptomName
    ){

    }
}
