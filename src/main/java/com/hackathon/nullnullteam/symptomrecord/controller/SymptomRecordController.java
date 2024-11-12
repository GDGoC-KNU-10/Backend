package com.hackathon.nullnullteam.symptomrecord.controller;

import com.hackathon.nullnullteam.global.dto.GlobalResponse;
import com.hackathon.nullnullteam.global.dto.PagingResponse;
import com.hackathon.nullnullteam.symptomrecord.controller.dto.SymptomRecordRequest;
import com.hackathon.nullnullteam.symptomrecord.controller.dto.SymptomRecordResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/symptom")
public class SymptomRecordController {

    @PostMapping("")
    public GlobalResponse addSymptomRecord(
            @RequestBody SymptomRecordRequest.Add request
            ){

        return GlobalResponse.builder().message("증상 기록 추가가 완료되었습니다.").build();

    }

    @GetMapping("/record")
    public PagingResponse<SymptomRecordResponse.Info> getAllSymptomRecord(
            @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable
    ){

        return null;

    }

    @GetMapping("/record/monthly")
    public PagingResponse<SymptomRecordResponse.Info> getMonthlySymptomRecord(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "date", required = false) LocalDate date
    ) {
        return null;

    }


}
