package com.hackathon.nullnullteam.chattingroom.controller.dto;

import com.hackathon.nullnullteam.chattingroom.service.dto.ChattingRoomCommand;
import lombok.Builder;

public class ChattingRoomRequest {

    @Builder
    public record Add(
            String name,
            String content
    ){
        public ChattingRoomCommand.Add toCommand(){
            return ChattingRoomCommand.Add.builder()
                    .name(name)
                    .content(content)
                    .build();
        }
    }
}
