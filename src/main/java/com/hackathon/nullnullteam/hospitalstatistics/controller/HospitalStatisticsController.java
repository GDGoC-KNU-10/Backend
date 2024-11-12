package com.hackathon.nullnullteam.hospitalstatistics.controller;

import com.hackathon.nullnullteam.global.dto.GlobalResponse;
import com.hackathon.nullnullteam.global.dto.PagingResponse;
import com.hackathon.nullnullteam.hospitalstatistics.controller.dto.HospitalStatisticsRequest;
import com.hackathon.nullnullteam.hospitalstatistics.controller.dto.HospitalStatisticsResponse;
import com.hackathon.nullnullteam.hospitalstatistics.service.HospitalStatisticsService;
import com.hackathon.nullnullteam.hospitalstatistics.service.dto.HospitalStatisticsModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hospital")
public class HospitalStatisticsController {
    private final HospitalStatisticsService hospitalStatisticsService;

    @PostMapping("")
    public GlobalResponse addHospitalStatistics(
            Long memberId,
            @RequestBody HospitalStatisticsRequest.Add request
    ) {
        hospitalStatisticsService.addHospitalStatistics(memberId, request.toCommand());
        return GlobalResponse.builder().message("병원 기록이 추가되었습니다.").build();
    }

    @GetMapping("/{hospital-id}")
    public HospitalStatisticsResponse.Info getHospitalStatistics(
            Long memberId,
            @PathVariable("hospital-id") Long hospitalStatisticsId
    ) {
        HospitalStatisticsModel.Info hospitalStatistics =
                hospitalStatisticsService.getHospitalStatistics(memberId, hospitalStatisticsId);
        return HospitalStatisticsResponse.Info.from(hospitalStatistics);
    }

    @GetMapping("/record")
    public PagingResponse<HospitalStatisticsResponse.Info> getAllHospitalStatistics(
            Long memberId,
            @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.ASC)
            Pageable pageable
    ) {
        Page<HospitalStatisticsModel.Info> allHospitalStatistics =
                hospitalStatisticsService.getAllHospitalStatistics(memberId, pageable);
        HospitalStatisticsResponse.Infos infoList =
                HospitalStatisticsResponse.Infos.from(allHospitalStatistics);
        return PagingResponse.from(infoList.infos());
    }

    @GetMapping("/record/monthly")
    public PagingResponse<HospitalStatisticsResponse.Info> getMonthlyHospitalStatistics(
            Long memberId,
            @PageableDefault(page = 0, size = 10) Pageable pageable,
            @RequestParam(name = "date", required = false) LocalDate date
    ) {
        Page<HospitalStatisticsModel.Info> monthlyHospitalStatistics =
                hospitalStatisticsService.getMonthlyHospitalStatistics(memberId, date, pageable);
        HospitalStatisticsResponse.Infos infoList =
                HospitalStatisticsResponse.Infos.from(monthlyHospitalStatistics);
        return PagingResponse.from(infoList.infos());
    }
}