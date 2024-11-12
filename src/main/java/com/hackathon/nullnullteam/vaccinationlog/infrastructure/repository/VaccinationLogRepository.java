package com.hackathon.nullnullteam.vaccinationlog.infrastructure.repository;

import com.hackathon.nullnullteam.vaccinationlog.VaccinationLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinationLogRepository extends JpaRepository<VaccinationLog, Long> {
}
