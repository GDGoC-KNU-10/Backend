package com.hackathon.nullnullteam.symptomrecord.infrastructure.repository;


import com.hackathon.nullnullteam.member.Member;
import com.hackathon.nullnullteam.symptomrecord.SymptomRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface SymptomRecordRepository extends JpaRepository<SymptomRecord, Long> {

    Page<SymptomRecord> getAllByMember(Member member, Pageable pageable);

    @Query("SELECT s FROM SymptomRecord s WHERE s.member = :member AND s.createdAt BETWEEN :startDate AND :endDate ORDER BY s.createdAt DESC")
    Page<SymptomRecord> getAllByMemberAndCreatedAtBetween(@Param("member") Member member, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, Pageable pageable);
}
