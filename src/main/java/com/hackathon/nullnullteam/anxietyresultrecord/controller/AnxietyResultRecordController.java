package com.hackathon.nullnullteam.anxietyresultrecord.controller;

import com.hackathon.nullnullteam.anxietyresultrecord.controller.dto.AnxietyResultRecordResponse;
import com.hackathon.nullnullteam.anxietyresultrecord.controller.dto.AnxietyResultRecordResponse.Info;
import com.hackathon.nullnullteam.anxietyresultrecord.controller.dto.AnxietyResultRecordResponse.Infos;
import com.hackathon.nullnullteam.anxietyresultrecord.service.AnxietyResultRecordService;
import com.hackathon.nullnullteam.anxietyresultrecord.service.dto.AnxietyResultRecordModel;
import com.hackathon.nullnullteam.global.annotation.Authenticate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/anxietyResult")
public class AnxietyResultRecordController {

    private final AnxietyResultRecordService anxietyResultRecordService;

    @GetMapping("")
    public Infos getTestResult(@Authenticate Long memberId) {
        List<AnxietyResultRecordModel.Info> anxietyResult = anxietyResultRecordService.getAnxietyResult(memberId);
        List<Info> infos = anxietyResult.stream().map(Info::from).toList();
        return AnxietyResultRecordResponse.Infos.from(infos);
    }
}
