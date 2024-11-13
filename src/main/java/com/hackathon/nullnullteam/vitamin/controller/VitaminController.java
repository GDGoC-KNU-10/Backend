package com.hackathon.nullnullteam.vitamin.controller;

import com.hackathon.nullnullteam.global.annotation.Authenticate;
import com.hackathon.nullnullteam.global.dto.GlobalResponse;
import com.hackathon.nullnullteam.global.dto.PagingResponse;
import com.hackathon.nullnullteam.vitamin.controller.dto.VitaminRequest;
import com.hackathon.nullnullteam.vitamin.controller.dto.VitaminResponse;
import com.hackathon.nullnullteam.vitamin.service.VitaminService;
import com.hackathon.nullnullteam.vitamin.service.dto.VitaminModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
@RequestMapping("/api/vitamin")
public class VitaminController {

    private final VitaminService vitaminService;

    @PostMapping("")
    public GlobalResponse addVitamin(
            @Authenticate Long memberId,
            @RequestBody VitaminRequest.Add request
    ) {
        vitaminService.addVitamin(memberId, request.toCommand());
        return GlobalResponse.builder().message("비타민 복용 현황을 추가하였습니다.").build();
    }

    @GetMapping("/record")
    public PagingResponse<VitaminResponse.Info> getAllVitamins(
            @Authenticate Long memberId,
            @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<VitaminModel.Info> allVitamins = vitaminService.getAllVitamins(memberId, pageable);
        VitaminResponse.Infos infoList = VitaminResponse.Infos.from(allVitamins);
        return PagingResponse.from(infoList.infos());
    }

    @GetMapping("/record/monthly")
    public PagingResponse<VitaminResponse.Info> getMonthlyVitamins(
            @Authenticate Long memberId,
            @PageableDefault(page = 0, size = 10) Pageable pageable,
            @RequestParam(name = "date", required = false) LocalDate date
    ) {
        Page<VitaminModel.Info> monthlyVitamins = vitaminService.getMonthlyVitamins(memberId, date, pageable);
        VitaminResponse.Infos infoList = VitaminResponse.Infos.from(monthlyVitamins);
        return PagingResponse.from(infoList.infos());
    }
}
