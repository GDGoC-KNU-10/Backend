package com.hackathon.nullnullteam.HealthCheck.infrastructure;


import com.hackathon.nullnullteam.HealthCheck.HealthCheck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthCheckRepository extends JpaRepository<HealthCheck, Long> {
}
