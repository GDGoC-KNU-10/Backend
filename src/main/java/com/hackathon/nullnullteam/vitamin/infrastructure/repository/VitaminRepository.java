package com.hackathon.nullnullteam.vitamin.infrastructure.repository;

import com.hackathon.nullnullteam.vitamin.Vitamin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VitaminRepository extends JpaRepository<Vitamin, Long> {
}