package com.hackathon.nullnullteam.chattingroom.service;

import com.hackathon.nullnullteam.chattingroom.ChattingRoom;
import com.hackathon.nullnullteam.chattingroom.service.dto.ChattingRoomCommand;
import com.hackathon.nullnullteam.chattingroom.service.dto.ChattingRoomModel;
import com.hackathon.nullnullteam.member.Member;
import com.hackathon.nullnullteam.member.service.MemberReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChattingRoomService {

    private final MemberReaderService memberReaderService;
    private final ChattingRoomWriterService chattingRoomWriterService;
    private final ChattingRoomReaderService chattingRoomReaderService;

    //TODO 채팅방 정보에서 받아온 content로 채팅로그 생성해야함
    @Transactional
    public void addChattingRoom(Long memberId, ChattingRoomCommand.Add command){
        Member member = memberReaderService.getMemberById(memberId);

        chattingRoomWriterService.save(command.toEntity());
    }

    @Transactional
    public List<ChattingRoomModel.Info> getChattingRoomList(Long memberId){
        Member member = memberReaderService.getMemberById(memberId);

        List<ChattingRoom> roomList = chattingRoomReaderService.getAllByMemberId(member);

        List<ChattingRoomModel.Info> modelList = roomList.stream().map(ChattingRoomModel.Info::from).toList();

        return modelList;
    }
}
