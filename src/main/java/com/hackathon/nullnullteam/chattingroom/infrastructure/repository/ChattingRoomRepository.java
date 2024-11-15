package com.hackathon.nullnullteam.chattingroom.infrastructure.repository;

import com.hackathon.nullnullteam.chattingroom.ChattingRoom;
import com.hackathon.nullnullteam.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, Long> {

    List<ChattingRoom> findAllByMember(Member member);
}
