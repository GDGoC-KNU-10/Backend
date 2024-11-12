package com.hackathon.nullnullteam.member.service;

import com.hackathon.nullnullteam.global.constants.MessageConstants;
import com.hackathon.nullnullteam.global.exception.EntityNotFoundException;
import com.hackathon.nullnullteam.member.Member;
import com.hackathon.nullnullteam.member.infrastructure.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberReaderService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Member getMemberById(Long memberId){
        return memberRepository.findById(memberId)
                .orElseThrow(
                        () -> new EntityNotFoundException(MessageConstants.USER_NOT_FOUND_MESSAGE)
                );
    }

    @Transactional(readOnly = true)
    public Optional<Member> findByEmail(String email){
        return memberRepository.findByEmail(email);
    }
}
