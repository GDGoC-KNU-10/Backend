package com.hackathon.nullnullteam.vitamin.service;

import com.hackathon.nullnullteam.vitamin.Vitamin;
import com.hackathon.nullnullteam.vitamin.infrastructure.repository.VitaminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VitaminWriterService {

    private final VitaminRepository vitaminRepository;

    @Transactional
    public void save(Vitamin vitamin) {
        vitaminRepository.save(vitamin);
    }

}
