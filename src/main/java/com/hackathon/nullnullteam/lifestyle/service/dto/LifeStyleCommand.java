package com.hackathon.nullnullteam.lifestyle.service.dto;

import com.hackathon.nullnullteam.lifestyle.LifeStyle;
import com.hackathon.nullnullteam.lifestyle.LifeStyleCategory;
import com.hackathon.nullnullteam.lifestyle.MoodScore;
import lombok.Builder;

public class LifeStyleCommand {

    @Builder
    public record Add(
            LifeStyleCategory category,
            String todayStatus,
            String goodThing,
            String worstThing,
            String tomorrowGoal,
            MoodScore moodScore
    ){
        public LifeStyle toEntity(){
            return LifeStyle.builder()
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
