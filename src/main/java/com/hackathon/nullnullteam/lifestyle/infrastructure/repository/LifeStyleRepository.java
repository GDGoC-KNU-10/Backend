package com.hackathon.nullnullteam.lifestyle.infrastructure.repository;

import com.hackathon.nullnullteam.lifestyle.LifeStyle;
import com.hackathon.nullnullteam.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface LifeStyleRepository extends JpaRepository<LifeStyle, Long> {

    Page<LifeStyle> getAllByMember(Member member, Pageable pageable);

    @Query("SELECT s FROM LifeStyle s WHERE s.member = :member AND s.createdAt BETWEEN :startDate AND :endDate ORDER BY s.createdAt DESC")
    Page<LifeStyle> getAllByMemberAndCreatedAtBetween(@Param("member") Member member, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, Pageable pageable);

}
