package com.hackathon.nullnullteam.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private ProblemDetail setCustomProblemDetail(CustomException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(e.getStatus());
        problemDetail.setTitle(e.getTitle());
        problemDetail.setDetail(e.getMessage());
        return problemDetail;
    }
}
