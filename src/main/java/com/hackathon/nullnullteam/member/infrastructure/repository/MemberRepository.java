package com.hackathon.nullnullteam.member.infrastructure.repository;

import com.hackathon.nullnullteam.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
