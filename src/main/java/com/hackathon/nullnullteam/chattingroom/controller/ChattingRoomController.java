package com.hackathon.nullnullteam.chattingroom.controller;


import com.hackathon.nullnullteam.chattingroom.controller.dto.ChattingRoomRequest;
import com.hackathon.nullnullteam.chattingroom.controller.dto.ChattingRoomResponse;
import com.hackathon.nullnullteam.chattingroom.service.ChattingRoomService;
import com.hackathon.nullnullteam.chattingroom.service.dto.ChattingRoomModel;
import com.hackathon.nullnullteam.global.annotation.Authenticate;
import com.hackathon.nullnullteam.global.dto.GlobalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chatroom")
public class ChattingRoomController {

    private final ChattingRoomService chattingRoomService;

    @PostMapping("")
    public GlobalResponse addChattingRoom(
            @Authenticate Long memberId,
            @RequestBody ChattingRoomRequest.Add request
            ){
        chattingRoomService.addChattingRoom(memberId, request.toCommand());

        return GlobalResponse.builder().message("채팅방 추가가 완료되었습니다.").build();

    }

    @GetMapping("")
    public ChattingRoomResponse.Infos getChattingRoomList(
            @Authenticate Long memberId
    ){
        List<ChattingRoomModel.Info> chattingRoomList = chattingRoomService.getChattingRoomList(memberId);

        return ChattingRoomResponse.Infos.from(chattingRoomList);
    }

}
