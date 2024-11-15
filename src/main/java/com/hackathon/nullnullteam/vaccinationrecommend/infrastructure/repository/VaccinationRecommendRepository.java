package com.hackathon.nullnullteam.vaccinationrecommend.infrastructure.repository;

import com.hackathon.nullnullteam.vaccinationrecommend.VaccinationRecommend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VaccinationRecommendRepository extends JpaRepository<VaccinationRecommend, Long> {

    List<VaccinationRecommend> findAllByStartAgeLessThanEqualAndEndAgeGreaterThanEqual(int age);
}
