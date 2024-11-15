package com.hackathon.nullnullteam.chattinglog.repository;

import com.hackathon.nullnullteam.chattinglog.ChattingLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChattingLogRepository extends JpaRepository<ChattingLog, Long> {
}
