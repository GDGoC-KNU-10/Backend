package com.hackathon.nullnullteam.symptomrecord.infrastructure.repository;


import com.hackathon.nullnullteam.symptomrecord.SymptomRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SymptomRecordRepository extends JpaRepository<SymptomRecord, Long> {
}
