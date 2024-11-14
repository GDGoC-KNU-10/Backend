package com.hackathon.nullnullteam.member.infrastructure.repository;

import com.hackathon.nullnullteam.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
}
