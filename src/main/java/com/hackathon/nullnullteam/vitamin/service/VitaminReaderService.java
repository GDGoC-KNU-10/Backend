package com.hackathon.nullnullteam.vitamin.service;

import com.hackathon.nullnullteam.global.exception.EntityNotFoundException;
import com.hackathon.nullnullteam.member.Member;
import com.hackathon.nullnullteam.vitamin.Vitamin;
import com.hackathon.nullnullteam.vitamin.infrastructure.repository.VitaminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VitaminReaderService {
    private final VitaminRepository vitaminRepository;

    public Vitamin getVitaminById(Long vitaminId) {
        return vitaminRepository.findById(vitaminId)
                .orElseThrow(() -> new EntityNotFoundException("비타민 기록을 찾을 수 없습니다."));
    }

    public Page<Vitamin> getAllByMember(Member member, Pageable pageable) {
        return vitaminRepository.findAllByMember(member, pageable);
    }

    public Page<Vitamin> getMonthlyByMember(Member member, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        return vitaminRepository.findAllByMemberAndCreatedAtBetween(member, startDate, endDate, pageable);
    }
}