package com.hackathon.nullnullteam.chattingroom.service;


import com.hackathon.nullnullteam.chattingroom.ChattingRoom;
import com.hackathon.nullnullteam.chattingroom.infrastructure.repository.ChattingRoomRepository;
import com.hackathon.nullnullteam.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChattingRoomReaderService {

    private final ChattingRoomRepository chattingRoomRepository;

    @Transactional(readOnly = true)
    public List<ChattingRoom> getAllByMemberId(Member member){
        return chattingRoomRepository.findAllByMember(member);
    }
}
