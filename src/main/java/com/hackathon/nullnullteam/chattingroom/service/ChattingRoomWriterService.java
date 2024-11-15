package com.hackathon.nullnullteam.chattingroom.service;

import com.hackathon.nullnullteam.chattingroom.ChattingRoom;
import com.hackathon.nullnullteam.chattingroom.infrastructure.repository.ChattingRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChattingRoomWriterService {

    private final ChattingRoomRepository chattingRoomRepository;

    @Transactional
    public void save(ChattingRoom chattingRoom){
        chattingRoomRepository.save(chattingRoom);
    }
}
