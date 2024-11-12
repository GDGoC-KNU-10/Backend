package com.hackathon.nullnullteam.vitamin.controller;

import com.hackathon.nullnullteam.global.dto.GlobalResponse;
import com.hackathon.nullnullteam.vitamin.controller.dto.VitaminRequest;
import com.hackathon.nullnullteam.vitamin.service.VitaminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vitamin")
public class VitaminController {

    private final VitaminService vitaminService;

    @PostMapping("")
    public GlobalResponse addVitamin(
            Long memberId,
            @RequestBody VitaminRequest.Add request
    ){
        vitaminService.addVitamin(memberId, request.toCommand());
        return GlobalResponse.builder().message("비타민 복용 현황을 추가하였습니다.").build();
    }
}
