package com.hackathon.nullnullteam.lifestyle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.nullnullteam.lifestyle.controller.dto.LifeStyleRequest;
import com.hackathon.nullnullteam.lifestyle.infrastructure.repository.LifeStyleRepository;
import com.hackathon.nullnullteam.member.Gender;
import com.hackathon.nullnullteam.member.Member;
import com.hackathon.nullnullteam.member.infrastructure.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class LifeStyleIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private LifeStyleRepository lifeStyleRepository;

    private Member testMember;

    @BeforeEach
    void setUp() {
        // 테스트 멤버 생성
        testMember = memberRepository.save(Member.builder()
                .name("테스트유저")
                .gender(Gender.MALE)
                .build());

        // 테스트용 생활습관 기록 15개 생성
        IntStream.range(0, 15).forEach(i -> {
            lifeStyleRepository.save(LifeStyle.builder()
                    .member(testMember)
                    .category(LifeStyleCategory.SLEEP)
                    .todayStatus("오늘의 상태" + i)
                    .goodThing("잘한 점" + i)
                    .worstThing("아쉬운 점" + i)
                    .tomorrowGoal("내일 목표" + i)
                    .moodScore(MoodScore.GOOD)
                    .build());
        });
    }


    @Test
    @DisplayName("생활습관 기록 추가")
    void addLifeStyleTest() throws Exception {
        // given
        LifeStyleRequest.Add request = new LifeStyleRequest.Add(
                LifeStyleCategory.EXERCISE,
                "운동한 날",
                "1시간 운동 완료",
                "스트레칭 부족",
                "스트레칭 추가하기",
                MoodScore.VERY_GOOD
        );

        // when & then
        mockMvc.perform(post("/api/lifestyle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("memberId", testMember.getId().toString())
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("생활습관이 추가되었습니다."))
                .andDo(print());
    }

    @Test
    @DisplayName("특정 생활습관 기록 조회")
    void getLifeStyleTest() throws Exception {
        // given
        LifeStyle lifeStyle = lifeStyleRepository.findAll().get(0);

        // when & then
        mockMvc.perform(get("/api/lifestyle/{lifestyle-id}", lifeStyle.getId())
                        .param("memberId", testMember.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lifeStyleId").value(lifeStyle.getId()))
                .andExpect(jsonPath("$.category").value(lifeStyle.getCategory().toString()))
                .andExpect(jsonPath("$.todayStatus").value(lifeStyle.getTodayStatus()))
                .andDo(print());
    }

    @Test
    @DisplayName("전체 생활습관 기록 목록 조회")
    void getAllLifeStylesTest() throws Exception {
        // when & then
        mockMvc.perform(get("/api/lifestyle/record")
                        .param("memberId", testMember.getId().toString())
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(10))
                .andExpect(jsonPath("$.totalElements").value(15))
                .andExpect(jsonPath("$.totalPages").value(2))
                .andDo(print());
    }

    @Test
    @DisplayName("월별 생활습관 기록 목록 조회")
    void getMonthlyLifeStylesTest() throws Exception {
        // given
        LocalDate targetDate = LocalDate.now();

        // when & then
        mockMvc.perform(get("/api/lifestyle/record/monthly")
                        .param("memberId", testMember.getId().toString())
                        .param("date", targetDate.toString())
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andDo(result -> {
                    String response = result.getResponse().getContentAsString();
                    System.out.println("응답 내용: " + response);
                })
                .andDo(print());
    }

    @Test
    @DisplayName("존재하지 않는 회원의 생활습관 조회 시 예외 발생")
    void getLifeStyleWithInvalidMemberTest() throws Exception {
        mockMvc.perform(get("/api/lifestyle/record")
                        .param("memberId", "999999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

}