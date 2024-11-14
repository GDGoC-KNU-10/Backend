package com.hackathon.nullnullteam.lifestyle.service.dto;

import com.hackathon.nullnullteam.lifestyle.LifeStyle;
import com.hackathon.nullnullteam.lifestyle.LifeStyleCategory;
import com.hackathon.nullnullteam.lifestyle.MoodScore;
import lombok.Builder;

import java.time.LocalDate;

public class LifeStyleModel {

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
        public static LifeStyleModel.Info from(LifeStyle lifeStyle) {
            return Info.builder()
                    .lifeStyleId(lifeStyle.getId())
                    .category(lifeStyle.getCategory())
                    .todayStatus(lifeStyle.getTodayStatus())
                    .goodThing(lifeStyle.getGoodThing())
                    .worstThing(lifeStyle.getGoodThing())
                    .tomorrowGoal(lifeStyle.getTomorrowGoal())
                    .moodScore(lifeStyle.getMoodScore())
                    .createdAt(lifeStyle.getCreatedAt().toLocalDate())
                    .build();
        }
    }
}
