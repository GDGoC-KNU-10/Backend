package com.hackathon.nullnullteam.chattingroom.service.dto;

import com.hackathon.nullnullteam.chattingroom.ChattingRoom;
import com.hackathon.nullnullteam.chattingroom.controller.dto.ChattingRoomResponse;
import lombok.Builder;

import java.util.List;

public class ChattingRoomModel {
    @Builder
    public record Info(
            String name
    ){
        public static ChattingRoomModel.Info from(ChattingRoom chattingRoom){
            return Info.builder()
                    .name(chattingRoom.getName())
                    .build();
        }

    }
}
