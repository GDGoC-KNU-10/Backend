package com.hackathon.nullnullteam.hospitalstatistics;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.nullnullteam.hospitalstatistics.controller.dto.HospitalStatisticsRequest;
import com.hackathon.nullnullteam.hospitalstatistics.infrastructure.repository.HospitalStatisticsRepository;
import com.hackathon.nullnullteam.member.Gender;
import com.hackathon.nullnullteam.member.Member;
import com.hackathon.nullnullteam.member.infrastructure.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
class HospitalStatisticsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private HospitalStatisticsRepository hospitalStatisticsRepository;

    private Member testMember;

    @BeforeEach
    void setUp() {
        // 테스트 멤버 생성
        testMember = memberRepository.save(Member.builder()
                .name("테스트유저")
                .gender(Gender.MALE)
                .build());

        // 테스트용 병원 기록 15개 생성
        IntStream.range(0, 15).forEach(i -> {
            hospitalStatisticsRepository.save(HospitalStatistics.builder()
                    .member(testMember)
                    .name("병원" + i)
                    .amount(10000 * (i + 1))
                    .result(ResultType.HIGH)
                    .description("진료내용" + i)
                    .build());
        });
    }

    @Test
    @DisplayName("병원 기록 추가")
    void addHospitalStatisticsTest() throws Exception {
        // given
        HospitalStatisticsRequest.Add request = new HospitalStatisticsRequest.Add(
                "새로운병원",
                50000,
                ResultType.MEDIUM,
                "새로운 진료 내용"
        );

        // when & then
        mockMvc.perform(post("/api/hospital")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("memberId", testMember.getId().toString())
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("병원 기록이 추가되었습니다."))
                .andDo(print());

    }

    @Test
    @DisplayName("단건 병원 기록 조회")
    void getHospitalStatisticsTest() throws Exception {
        // given
        Page<HospitalStatistics> statisticsPage = hospitalStatisticsRepository
                .findAllByMember(testMember, PageRequest.of(0, 1));
        HospitalStatistics statistics = statisticsPage.getContent().get(0);

        // when & then
        mockMvc.perform(get("/api/hospital/{hospital-id}", statistics.getId())
                        .param("memberId", testMember.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hospitalStatisticsId").value(statistics.getId()))
                .andExpect(jsonPath("$.name").value(statistics.getName()))
                .andExpect(jsonPath("$.amount").value(statistics.getAmount()))
                .andExpect(jsonPath("$.result").value(statistics.getResult().toString()))
                .andDo(print());
    }

    @Test
    @DisplayName("전체 병원 기록 목록 조회")
    void getAllHospitalStatisticsTest() throws Exception {
        // when & then
        mockMvc.perform(get("/api/hospital/record")
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
    @DisplayName("월별 병원 기록 목록 조회")
    void getMonthlyHospitalStatisticsTest() throws Exception {
        // given
        LocalDate targetDate = LocalDate.now();

        // when & then
        mockMvc.perform(get("/api/hospital/record/monthly")
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
    @DisplayName("존재하지 않는 회원의 병원 기록 조회 시 예외 발생")
    void getHospitalStatisticsWithInvalidMemberTest() throws Exception {
        mockMvc.perform(get("/api/hospital/record")
                        .param("memberId", "999999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }


}