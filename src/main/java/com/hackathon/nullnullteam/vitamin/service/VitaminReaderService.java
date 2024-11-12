package com.hackathon.nullnullteam.vitamin.service;

import com.hackathon.nullnullteam.vitamin.infrastructure.repository.VitaminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VitaminReaderService {

    private final VitaminRepository vitaminRepository;
}
