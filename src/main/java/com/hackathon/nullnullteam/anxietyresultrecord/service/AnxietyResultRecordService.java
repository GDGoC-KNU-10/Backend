package com.hackathon.nullnullteam.anxietyresultrecord.service;

import com.hackathon.nullnullteam.anxietyresultrecord.AnxietyResultRecord;
import com.hackathon.nullnullteam.anxietyresultrecord.service.dto.AnxietyResultRecordCommand;
import com.hackathon.nullnullteam.anxietyresultrecord.service.dto.AnxietyResultRecordModel;
import com.hackathon.nullnullteam.anxietyresultrecord.service.dto.AnxietyResultRecordModel.Info;
import com.hackathon.nullnullteam.member.Member;
import com.hackathon.nullnullteam.member.service.MemberReaderService;
import com.hackathon.nullnullteam.vaccinationlog.infrastructure.repository.dto.VaccinationRecommendDto;
import com.hackathon.nullnullteam.vaccinationlog.service.VaccinationWriterService;
import com.hackathon.nullnullteam.vaccinationrecommend.VaccinationRecommend;
import com.hackathon.nullnullteam.vaccinationrecommend.infrastructure.repository.VaccinationRecommendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnxietyResultRecordService {

    private final AnxietyResultRecordReaderService anxietyResultRecordReaderService;
    private final MemberReaderService memberReaderService;
    private final AnxietyResultRecordWriterService anxietyResultRecordWriterService;
    private final VaccinationRecommendRepository vaccinationRecommendRepository;
    private final VaccinationWriterService vaccinationWriterService;

    public List<AnxietyResultRecordModel.Info> getAnxietyResult(Long memberId) {
        List<AnxietyResultRecord> anxietyResultRecords = anxietyResultRecordReaderService.findByMemberId(memberId);
        return anxietyResultRecords.stream().map(Info::from).toList();
    }

    @Transactional
    public void AnxietyTestResult(Long memberId, AnxietyResultRecordCommand.Result command) {
        Member member = memberReaderService.getMemberById(memberId);
        AnxietyResultRecord anxietyResultRecord = command.toEntity(member);
        anxietyResultRecordWriterService.save(anxietyResultRecord);
        member.setAnxietyLevel(command.score());

        // 백신 추천 정보를 조회
        List<VaccinationRecommend> vaccinationRecommendDtos = vaccinationRecommendRepository.findAllByStartAgeLessThanEqualAndEndAgeGreaterThanEqual(member.getAge());

        // 추천 백신 정보를 VaccinationLog 엔티티로 변환하여 저장
        vaccinationRecommendDtos.forEach(vaccinationRecommendDto -> {
            vaccinationWriterService.save(member.getName(), vaccinationRecommendDto);
        });
    }
}
