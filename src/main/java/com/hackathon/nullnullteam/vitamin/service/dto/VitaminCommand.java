package com.hackathon.nullnullteam.vitamin.service.dto;

import com.hackathon.nullnullteam.vitamin.IntakeFrequency;
import com.hackathon.nullnullteam.vitamin.Vitamin;
import lombok.Builder;

public class VitaminCommand {

    @Builder
    public record Add(
            String vitaminName,
            IntakeFrequency intakeFrequency,
            String oneTakeAmount
    ){
      public Vitamin toEntity(){
          return Vitamin.builder()
                  .vitaminName(vitaminName)
                  .intakeFrequency(intakeFrequency)
                  .onetakeAmount(oneTakeAmount)
                  .build();
      }
    }
}
