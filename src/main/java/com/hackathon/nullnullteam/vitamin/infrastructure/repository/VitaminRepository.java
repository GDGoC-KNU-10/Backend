package com.hackathon.nullnullteam.vitamin.infrastructure.repository;

import com.hackathon.nullnullteam.member.Member;
import com.hackathon.nullnullteam.vitamin.Vitamin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface VitaminRepository extends JpaRepository<Vitamin, Long> {
    Page<Vitamin> findAllByMember(Member member, Pageable pageable);

    @Query("SELECT v FROM Vitamin v WHERE v.member = :member AND v.createdAt BETWEEN :startDate AND :endDate")
    Page<Vitamin> findAllByMemberAndCreatedAtBetween(
            @Param("member") Member member,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable
    );
}