package com.hackathon.nullnullteam.lifestyle.controller;

import com.hackathon.nullnullteam.global.dto.GlobalResponse;
import com.hackathon.nullnullteam.lifestyle.controller.dto.LifeStyleRequest;
import com.hackathon.nullnullteam.lifestyle.controller.dto.LifeStyleResponse;
import com.hackathon.nullnullteam.lifestyle.service.LifeStyleService;
import com.hackathon.nullnullteam.lifestyle.service.dto.LifeStyleModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lifestyle")
public class LifeStyleController {

    private final LifeStyleService lifeStyleService;

    @PostMapping("")
    public GlobalResponse addLifeStyle(
            Long memberId,
            @RequestBody LifeStyleRequest.Add request
            ){
        lifeStyleService.addLifeStyle(memberId, request.toCommand());

        return GlobalResponse.builder().message("생활습관이 추가되었습니다.").build();
    }

    @GetMapping("/{lifestyle-id}")
    public LifeStyleResponse.Info getLifeStyle(
            Long memberId,
            @PathVariable("lifestyle-id") Long lifestyleId
    ){
        LifeStyleModel.Info lifeStyle = lifeStyleService.getLifeStyle(memberId, lifestyleId);
        return LifeStyleResponse.Info.from(lifeStyle);
    }
}
