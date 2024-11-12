package com.hackathon.nullnullteam.anxietyresultrecord.infrastructure.repository;

import com.hackathon.nullnullteam.anxietyresultrecord.AnxietyResultRecord;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnxietyResultRecordRepository extends JpaRepository<AnxietyResultRecord, Long> {

    List<AnxietyResultRecord> findByMemberId(Long memberId);
}
