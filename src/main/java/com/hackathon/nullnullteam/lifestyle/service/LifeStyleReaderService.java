package com.hackathon.nullnullteam.lifestyle.service;

import com.hackathon.nullnullteam.global.exception.EntityNotFoundException;
import com.hackathon.nullnullteam.lifestyle.LifeStyle;
import com.hackathon.nullnullteam.lifestyle.infrastructure.repository.LifeStyleRepository;
import com.hackathon.nullnullteam.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LifeStyleReaderService {

    private final LifeStyleRepository lifeStyleRepository;

    @Transactional(readOnly = true)
    public LifeStyle getLifeStyle(Long lifeStyleId) {
        return lifeStyleRepository.findById(lifeStyleId)
                .orElseThrow(
                        () -> new EntityNotFoundException("생활습관을 찾을 수 없습니다.")
                );
    }

    @Transactional(readOnly = true)
    public Page<LifeStyle> getAllLifeStyles(Member member, Pageable pageable) {
        return lifeStyleRepository.getAllByMember(member, pageable);
    }

    @Transactional(readOnly = true)
    public Page<LifeStyle> getMonthlyLifeStyles(Member member, Pageable pageable, LocalDateTime startDate, LocalDateTime endDate) {
        return lifeStyleRepository.getAllByMemberAndCreatedAtBetween(member, startDate, endDate, pageable);
    }
}
