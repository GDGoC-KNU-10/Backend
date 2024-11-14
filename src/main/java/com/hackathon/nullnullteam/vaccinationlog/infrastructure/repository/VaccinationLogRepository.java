package com.hackathon.nullnullteam.vaccinationlog.infrastructure.repository;

import com.hackathon.nullnullteam.vaccinationlog.VaccinationLog;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinationLogRepository extends JpaRepository<VaccinationLog, Long> {

    List<VaccinationLog> findByUsername(String username);
}
