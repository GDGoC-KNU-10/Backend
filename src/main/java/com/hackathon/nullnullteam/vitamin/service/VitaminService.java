package com.hackathon.nullnullteam.vitamin.service;

import com.hackathon.nullnullteam.member.Member;
import com.hackathon.nullnullteam.member.service.MemberReaderService;
import com.hackathon.nullnullteam.vitamin.Vitamin;
import com.hackathon.nullnullteam.vitamin.service.dto.VitaminCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VitaminService {

    private final MemberReaderService memberReaderService;
    private final VitaminWriterService vitaminWriterService;

    @Transactional
    public void addVitamin(Long memberId, VitaminCommand.Add command){
        Member member = memberReaderService.getMemberById(memberId);

        Vitamin vitamin = command.toEntity();

        vitaminWriterService.save(vitamin);
    }


}
