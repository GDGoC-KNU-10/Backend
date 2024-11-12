package com.hackathon.nullnullteam.lifestyle.service;

import com.hackathon.nullnullteam.lifestyle.LifeStyle;
import com.hackathon.nullnullteam.lifestyle.service.dto.LifeStyleCommand;
import com.hackathon.nullnullteam.lifestyle.service.dto.LifeStyleModel;
import com.hackathon.nullnullteam.member.Member;
import com.hackathon.nullnullteam.member.service.MemberReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LifeStyleService {

    private final MemberReaderService memberReaderService;
    private final LifeStyleWriterService lifeStyleWriterService;
    private final LifeStyleReaderService lifeStyleReaderService;

    @Transactional
    public void addLifeStyle(Long memberId, LifeStyleCommand.Add command) {
        Member member = memberReaderService.getMemberById(memberId);

        LifeStyle lifeStyle = command.toEntity();

        lifeStyleWriterService.save(lifeStyle);
    }

    @Transactional(readOnly = true)
    public LifeStyleModel.Info getLifeStyle(Long memberId, Long lifeStyleId) {
        Member member = memberReaderService.getMemberById(memberId);

        LifeStyle lifeStyle = lifeStyleReaderService.getLifeStyle(lifeStyleId);

        return LifeStyleModel.Info.from(lifeStyle);
    }
}
