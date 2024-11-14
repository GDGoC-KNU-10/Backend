package com.hackathon.nullnullteam.vaccinationlog.infrastructure.repository.dto;

public record VaccinationRecommendDto(
    Long id,
    String diseaseName,
    String vaccineName,
    int startAge,
    int endAge
) {

}
