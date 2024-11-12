package com.hackathon.nullnullteam.hospitalstatistics.infrastructure.repository;

import com.hackathon.nullnullteam.hospitalstatistics.HospitalStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalStatisticsRepository extends JpaRepository<HospitalStatistics, Long> {
}
