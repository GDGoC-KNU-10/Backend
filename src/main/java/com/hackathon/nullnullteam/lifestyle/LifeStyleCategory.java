package com.hackathon.nullnullteam.lifestyle;

import lombok.Getter;

@Getter
public enum LifeStyleCategory {
    SLEEP("수면"),
    EXERCISE("운동"),
    DIET("식습관"),
    MEDICATION("복용약");

    private final String description;

    LifeStyleCategory(String description) {
        this.description = description;
    }
}
