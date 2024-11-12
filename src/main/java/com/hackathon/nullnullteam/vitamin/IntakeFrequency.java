package com.hackathon.nullnullteam.vitamin;

import lombok.Getter;

@Getter
public enum IntakeFrequency {
    DAILY("매일"),
    EVERY_OTHER_DAY("이틀에 한번"),
    TWICE_A_WEEK("주 2회"),
    THREE_TIMES_A_WEEK("주 3회"),
    WEEKLY("주 1회");

    private final String description;

    IntakeFrequency(String description) {
        this.description = description;
    }
}