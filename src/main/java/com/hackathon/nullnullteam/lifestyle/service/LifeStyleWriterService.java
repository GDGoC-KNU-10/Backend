package com.hackathon.nullnullteam.lifestyle.service;

import com.hackathon.nullnullteam.lifestyle.LifeStyle;
import com.hackathon.nullnullteam.lifestyle.infrastructure.repository.LifeStyleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LifeStyleWriterService {

    private final LifeStyleRepository lifeStyleRepository;

    @Transactional
    public void save(LifeStyle lifeStyle) {
        lifeStyleRepository.save(lifeStyle);
    }
}
