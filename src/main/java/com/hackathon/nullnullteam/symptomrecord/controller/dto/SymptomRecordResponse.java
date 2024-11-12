package com.hackathon.nullnullteam.symptomrecord.controller.dto;

import com.hackathon.nullnullteam.symptomrecord.service.dto.SymptomRecordModel;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public class SymptomRecordResponse {

    @Builder
    public record Info(
            Long userId,
            LocalDate startDate,
            String description,
            String symptomName
    ){
        public static SymptomRecordResponse.Info from(SymptomRecordModel.Info model) {
            return Info.builder()
                    .userId(model.userId())
                    .startDate(model.startDate())
                    .description(model.description())
                    .symptomName(model.symptomName())
                    .build();
        }

    }

    @Builder
    public record Infos(
            Page<SymptomRecordResponse.Info> infos
    ){
        public static SymptomRecordResponse.Infos from(Page<SymptomRecordModel.Info> infoList){
            return Infos.builder()
                    .infos(infoList.map(SymptomRecordResponse.Info::from))
                    .build();
        }

    }
}
