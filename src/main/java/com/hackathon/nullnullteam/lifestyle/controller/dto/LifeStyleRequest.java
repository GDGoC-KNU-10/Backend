package com.hackathon.nullnullteam.lifestyle.controller.dto;

import com.hackathon.nullnullteam.lifestyle.LifeStyleCategory;
import com.hackathon.nullnullteam.lifestyle.MoodScore;
import com.hackathon.nullnullteam.lifestyle.service.dto.LifeStyleCommand;
import lombok.Builder;

public class LifeStyleRequest {

    @Builder
    public record Add(
            LifeStyleCategory category,
            String todayStatus,
            String goodThing,
            String worstThing,
            String tomorrowGoal,
            MoodScore moodScore
    ) {
        public LifeStyleCommand.Add toCommand() {
            return LifeStyleCommand.Add.builder()
                    .category(category)
                    .todayStatus(todayStatus)
                    .goodThing(goodThing)
                    .worstThing(worstThing)
                    .tomorrowGoal(tomorrowGoal)
                    .moodScore(moodScore)
                    .build();
        }

    }
}
