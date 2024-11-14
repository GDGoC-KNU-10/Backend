package com.hackathon.nullnullteam.anxietyresultrecord.infrastructure.repository;

import com.hackathon.nullnullteam.anxietyresultrecord.AnxietyResultRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnxietyResultRecordRepository extends JpaRepository<AnxietyResultRecord, Long> {

    List<AnxietyResultRecord> findByMemberId(Long memberId);
}
