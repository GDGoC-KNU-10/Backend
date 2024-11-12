package com.hackathon.nullnullteam.symptomrecord.controller.dto;

import lombok.Builder;

import java.time.LocalDate;

public class SymptomRecordRequest {

    @Builder
    public record Add(
            String description,
            String symptomName,
            LocalDate startDate
    ){


    }

}
