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

    public static AnxietyLevel fromString(int count) {
        if (count <= 5) {
            return LOW;
        } else if (count <= 10) {
            return MEDIUM;
        } else if (count <= 15) {
            return HIGH;
        }
        return VERY_HIGH;
    }
}
