package com.hackathon.nullnullteam.lifestyle;

import lombok.Getter;

@Getter
public enum MoodScore {
    VERY_BAD(1, "매우 나쁨"),
    BAD(2, "나쁨"),
    NEUTRAL(3, "보통"),
    GOOD(4, "좋음"),
    VERY_GOOD(5, "매우 좋음");

    private final int score;
    private final String description;

    MoodScore(int score, String description) {
        this.score = score;
        this.description = description;
    }
}