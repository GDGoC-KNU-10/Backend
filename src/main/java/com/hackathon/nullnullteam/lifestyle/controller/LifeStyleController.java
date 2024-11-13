package com.hackathon.nullnullteam.lifestyle.controller;

import com.hackathon.nullnullteam.global.annotation.Authenticate;
import com.hackathon.nullnullteam.global.dto.GlobalResponse;
import com.hackathon.nullnullteam.global.dto.PagingResponse;
import com.hackathon.nullnullteam.lifestyle.controller.dto.LifeStyleRequest;
import com.hackathon.nullnullteam.lifestyle.controller.dto.LifeStyleResponse;
import com.hackathon.nullnullteam.lifestyle.service.LifeStyleService;
import com.hackathon.nullnullteam.lifestyle.service.dto.LifeStyleModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lifestyle")
public class LifeStyleController {

    private final LifeStyleService lifeStyleService;

    @PostMapping("")
    public GlobalResponse addLifeStyle(
            @Authenticate Long memberId,
            @RequestBody LifeStyleRequest.Add request
    ) {
        lifeStyleService.addLifeStyle(memberId, request.toCommand());

        return GlobalResponse.builder().message("생활습관이 추가되었습니다.").build();
    }

    @GetMapping("/{lifestyle-id}")
    public LifeStyleResponse.Info getLifeStyle(
            @Authenticate Long memberId,
            @PathVariable("lifestyle-id") Long lifestyleId
    ) {
        LifeStyleModel.Info lifeStyle = lifeStyleService.getLifeStyle(memberId, lifestyleId);
        return LifeStyleResponse.Info.from(lifeStyle);
    }

    @GetMapping("/record")
    public PagingResponse<LifeStyleResponse.Info> getAllLifeStyles(
            @Authenticate Long memberId,
            @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.ASC)
            Pageable pageable) {
        Page<LifeStyleModel.Info> allLifeStyles = lifeStyleService.getAllLifeStyles(memberId, pageable);

        LifeStyleResponse.Infos infoList = LifeStyleResponse.Infos.from(allLifeStyles);

        return PagingResponse.from(infoList.infos());
    }

    @GetMapping("/record/monthly")
    public PagingResponse<LifeStyleResponse.Info> getMonthlyLifeStyles(
            @Authenticate Long memberId,
            @PageableDefault(page = 0, size = 10) Pageable pageable,
            @RequestParam(name = "date", required = false) LocalDate date
    ) {
        Page<LifeStyleModel.Info> monthlyLifeStyles = lifeStyleService.getMonthlyLifeStyles(memberId, pageable, date);

        LifeStyleResponse.Infos infoList = LifeStyleResponse.Infos.from(monthlyLifeStyles);
        return PagingResponse.from(infoList.infos());
    }

}
