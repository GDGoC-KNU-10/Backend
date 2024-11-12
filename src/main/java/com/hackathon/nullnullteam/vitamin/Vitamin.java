package com.hackathon.nullnullteam.vitamin;

import com.hackathon.nullnullteam.global.entity.BaseTimeEntity;
import com.hackathon.nullnullteam.member.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Vitamin extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vitaminName;

    @Enumerated(EnumType.STRING)
    private IntakeFrequency intakeFrequency;

    private String onetakeAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private Member member;

}