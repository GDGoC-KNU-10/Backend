package com.hackathon.nullnullteam.vitamin.controller.dto;

import com.hackathon.nullnullteam.vitamin.IntakeFrequency;
import com.hackathon.nullnullteam.vitamin.service.dto.VitaminCommand;
import lombok.Builder;

public class VitaminRequest {

    @Builder
    public record Add(
            String vitaminName,
            IntakeFrequency intakeFrequency,
            String oneTakeAmount
    ){
        public VitaminCommand.Add toCommand(){
            return VitaminCommand.Add.builder()
                    .vitaminName(vitaminName)
                    .intakeFrequency(intakeFrequency)
                    .oneTakeAmount(oneTakeAmount)
                    .build();
        }
    }
}
