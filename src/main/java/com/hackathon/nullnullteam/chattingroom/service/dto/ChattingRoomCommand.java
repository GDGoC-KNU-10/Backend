package com.hackathon.nullnullteam.chattingroom.service.dto;

import com.hackathon.nullnullteam.chattingroom.ChattingRoom;
import lombok.Builder;

public class ChattingRoomCommand {

    @Builder
    public record Add(
            String name,
            String content
    ){
        public ChattingRoom toEntity(){
            return ChattingRoom.builder()
                    .name(name)
                    .build();
        }
    }
}
