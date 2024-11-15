package com.hackathon.nullnullteam.hospitalstatistics;

import lombok.Getter;

@Getter
public enum ResultType {
    HIGH("심각한 상태에요"),
    MEDIUM("초기 상태예요"),
    LOW("문제가 없어요");
    private final String description;

    ResultType(String description) {
        this.description = description;
    }
}
