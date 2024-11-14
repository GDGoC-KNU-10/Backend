package com.hackathon.nullnullteam.lifestyle.controller.dto;

import com.hackathon.nullnullteam.lifestyle.LifeStyleCategory;
import com.hackathon.nullnullteam.lifestyle.MoodScore;
import com.hackathon.nullnullteam.lifestyle.service.dto.LifeStyleModel;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public class LifeStyleResponse {
    @Builder
    public record Info(
            Long lifeStyleId,
            LifeStyleCategory category,
            String todayStatus,
            String goodThing,
            String worstThing,
            String tomorrowGoal,
            MoodScore moodScore,
            LocalDate createdAt
    ) {
        public static LifeStyleResponse.Info from(LifeStyleModel.Info model) {
            return Info.builder()
                    .lifeStyleId(model.lifeStyleId())
                    .category(model.category())
                    .todayStatus(model.todayStatus())
                    .goodThing(model.goodThing())
                    .worstThing(model.worstThing())
                    .tomorrowGoal(model.tomorrowGoal())
                    .moodScore(model.moodScore())
                    .createdAt(model.createdAt())
                    .build();
        }
    }

    @Builder
    public record Infos(
            Page<LifeStyleResponse.Info> infos
    ) {
        public static LifeStyleResponse.Infos from(Page<LifeStyleModel.Info> infoList) {
            return Infos.builder()
                    .infos(infoList.map(LifeStyleResponse.Info::from))
                    .build();
        }
    }
}
