package com.hackathon.nullnullteam.symptomrecord.infrastructure.repository;


import com.hackathon.nullnullteam.symptomrecord.SymptomRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SymptomRecordRepository extends JpaRepository<SymptomRecord, Long> {

    Page<SymptomRecord> getAllByMemberId(Long memberId, Pageable pageable);
}
