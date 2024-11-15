package com.hackathon.nullnullteam.chattingroom.controller.dto;

import com.hackathon.nullnullteam.chattingroom.service.dto.ChattingRoomModel;
import lombok.Builder;

import java.util.List;

public class ChattingRoomResponse {

    @Builder
    public record Info(
            String name
    ){
        public static ChattingRoomResponse.Info from(ChattingRoomModel.Info info){
            return Info.builder()
                    .name(info.name())
                    .build();
        }

    }

    @Builder
    public record Infos(
            List<ChattingRoomResponse.Info> infos
    ){
        public static ChattingRoomResponse.Infos from(List<ChattingRoomModel.Info> infoList){
            return Infos.builder()
                    .infos(
                            infoList.stream().map(ChattingRoomResponse.Info::from)
                                    .toList()
                    )
                    .build();
        }
    }
}
