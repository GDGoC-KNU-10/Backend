package com.hackathon.nullnullteam.member;

import lombok.Getter;

@Getter
public enum AnxietyLevel {
    LOW("낮음"),
    MEDIUM("중간"),
    HIGH("높음"),
    VERY_HIGH("매우 높음");

    private final String description;

    AnxietyLevel(String description) {
        this.description = description;
    }
}
