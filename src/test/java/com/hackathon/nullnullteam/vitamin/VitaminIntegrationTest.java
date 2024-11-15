package com.hackathon.nullnullteam.vitamin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.nullnullteam.member.Gender;
import com.hackathon.nullnullteam.member.Member;
import com.hackathon.nullnullteam.member.infrastructure.repository.MemberRepository;
import com.hackathon.nullnullteam.vitamin.controller.dto.VitaminRequest;
import com.hackathon.nullnullteam.vitamin.infrastructure.repository.VitaminRepository;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class VitaminIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private VitaminRepository vitaminRepository;

    private Member testMember;

    @BeforeEach
    void setUp() {
        // 테스트 멤버 생성
        testMember = memberRepository.save(Member.builder()
                .name("테스트유저")
                .gender(Gender.MALE)
                .build());

        // 테스트용 비타민 복용 기록 15개 생성
        IntStream.range(0, 15).forEach(i -> {
            vitaminRepository.save(Vitamin.builder()
                    .member(testMember)
                    .vitaminName("비타민" + i)
                    .intakeFrequency(IntakeFrequency.DAILY)
                    .onetakeAmount("1정")
                    .build());
        });
    }



    @Test
    @DisplayName("비타민 복용 기록 추가")
    void addVitaminTest() throws Exception {
        // given
        VitaminRequest.Add request = new VitaminRequest.Add(
                "비타민C",
                IntakeFrequency.DAILY,
                "1정"
        );

        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setAttribute("userId", "93");

        // when & then
        mockMvc.perform(post("/api/vitamin")
                        .requestAttr("userId", "93")
                        .contentType(MediaType.APPLICATION_JSON)

                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("비타민 복용 현황을 추가하였습니다."))
                .andDo(print());
    }

    @Test
    @DisplayName("전체 비타민 복용 기록 조회")
    void getAllVitaminsTest() throws Exception {

        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setAttribute("userId", "93");

        // when & then
        mockMvc.perform(get("/api/vitamin/record")
                        .requestAttr("userId", "93")
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
    @DisplayName("월별 비타민 복용 기록 조회")
    void getMonthlyVitaminsTest() throws Exception {
        // given
        LocalDate targetDate = LocalDate.now();

        // when & then
        mockMvc.perform(get("/api/vitamin/record/monthly")
                        .param("memberId", testMember.getId().toString())
                        .param("date", targetDate.toString())
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andDo(print());
    }

    @Test
    @DisplayName("존재하지 않는 회원의 비타민 기록 조회 시 예외 발생")
    void getVitaminWithInvalidMemberTest() throws Exception {
        mockMvc.perform(get("/api/vitamin/record")
                        .param("memberId", "999999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

}