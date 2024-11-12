package com.hackathon.nullnullteam.lifestyle.service;

import com.hackathon.nullnullteam.global.exception.EntityNotFoundException;
import com.hackathon.nullnullteam.lifestyle.LifeStyle;
import com.hackathon.nullnullteam.lifestyle.infrastructure.repository.LifeStyleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
