package com.hackathon.nullnullteam.member;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("남성"),
    FEMALE("여성");

    private final String description;

    Gender(String description) {
        this.description = description;
    }

    public static Gender fromString(String gender) {
        if (gender.equals("FEMALE")) {
            return MALE;
        }
        return FEMALE;
    }
}