package com.hackathon.nullnullteam.hospitalstatistics.infrastructure.repository;

import com.hackathon.nullnullteam.hospitalstatistics.HospitalStatistics;
import com.hackathon.nullnullteam.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface HospitalStatisticsRepository extends JpaRepository<HospitalStatistics, Long> {
    Page<HospitalStatistics> findAllByMember(Member member, Pageable pageable);

    @Query("SELECT h FROM HospitalStatistics h WHERE h.member = :member AND h.createdAt BETWEEN :startDate AND :endDate")
    Page<HospitalStatistics> findAllByMemberAndCreatedAtBetween(
            @Param("member") Member member,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable
    );
}