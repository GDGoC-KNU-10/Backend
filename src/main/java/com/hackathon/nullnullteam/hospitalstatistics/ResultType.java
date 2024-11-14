package com.hackathon.nullnullteam.hospitalstatistics;

import lombok.Getter;

@Getter
public enum ResultType {
    HIGH("상"),
    MEDIUM("중"),
    LOW("하");
    private final String description;

    ResultType(String description) {
        this.description = description;
    }
}
