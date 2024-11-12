package com.hackathon.nullnullteam.anxietyresultrecord.infrastructure.repository;

import com.hackathon.nullnullteam.anxietyresultrecord.AnxietyResultRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnxietyResultRecordRepository extends JpaRepository<AnxietyResultRecord, Long> {
}
