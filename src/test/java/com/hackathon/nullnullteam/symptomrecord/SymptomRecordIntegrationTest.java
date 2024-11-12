package com.hackathon.nullnullteam.symptomrecord;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.nullnullteam.member.Gender;
import com.hackathon.nullnullteam.member.Member;
import com.hackathon.nullnullteam.member.infrastructure.repository.MemberRepository;
import com.hackathon.nullnullteam.symptomrecord.controller.dto.SymptomRecordRequest;
import com.hackathon.nullnullteam.symptomrecord.infrastructure.repository.SymptomRecordRepository;
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
class SymptomRecordIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private SymptomRecordRepository symptomRecordRepository;

    private Member testMember;

    @BeforeEach
    void setUp() {
        // 테스트용 회원 생성
        testMember = memberRepository.save(Member.builder()
                .name("테스트유저")
                .gender(Gender.MALE)
                .build());

        // 테스트용 증상 기록들 생성
        IntStream.range(0, 15).forEach(i -> {
            symptomRecordRepository.save(SymptomRecord.builder()
                    .member(testMember)
                    .symptomName("증상" + i)
                    .description("설명" + i)
                    .startDate(LocalDate.now().minusDays(i))
                    .build());
        });
    }


    @Test
    @DisplayName("증상 기록 추가 테스트")
    void addSymptomRecordTest() throws Exception {
        // given
        SymptomRecordRequest.Add request = new SymptomRecordRequest.Add(
                "두통",
                "심한 두통이 있음",
                LocalDate.now()
        );

        // when & then
        mockMvc.perform(post("/api/symptom")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("memberId", testMember.getId().toString())
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("증상 기록 추가가 완료되었습니다."))
                .andDo(print());
    }

    @Test
    @DisplayName("전체 증상 기록 조회 테스트")
    void getAllSymptomRecordTest() throws Exception {
        // when & then
        mockMvc.perform(get("/api/symptom/record")
                        .param("memberId", testMember.getId().toString())
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(10))
                .andExpect(jsonPath("$.totalElements").value(15))
                .andExpect(jsonPath("$.totalPages").value(2))
                .andDo(result -> {
                    String responseContent = result.getResponse().getContentAsString();
                    System.out.println("응답 내용: " + responseContent);
                })
                .andDo(print());
    }

    @Test
    @DisplayName("월별 증상 기록 조회 테스트")
    void getMonthlySymptomRecordTest() throws Exception {
        // given
        LocalDate targetDate = LocalDate.now();

        // when & then
        mockMvc.perform(get("/api/symptom/record/monthly")
                        .param("memberId", testMember.getId().toString())
                        .param("date", targetDate.toString())
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].startDate").exists())
                .andDo(print());
    }

    @Test
    @DisplayName("존재하지 않는 회원의 증상 기록 조회 시 예외 발생")
    void getSymptomRecordWithInvalidMemberTest() throws Exception {
        // when & then
        mockMvc.perform(get("/api/symptom/record")
                        .param("memberId", "999999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }


}