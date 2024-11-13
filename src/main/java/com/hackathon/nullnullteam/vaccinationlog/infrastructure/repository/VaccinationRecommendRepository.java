package com.hackathon.nullnullteam.vaccinationlog.infrastructure.repository;

import com.hackathon.nullnullteam.vaccinationlog.infrastructure.repository.dto.VaccinationRecommendDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class VaccinationRecommendRepository {

    private final JdbcTemplate jdbcTemplate;

    public Optional<VaccinationRecommendDto> findByVaccineName(String vaccineName) {
        String sql = "SELECT id, vaccine_name, start_age, end_age, disease_name " +
            "FROM vaccination_recommend " +
            "WHERE disease_name = ? LIMIT 1";

        Optional<VaccinationRecommendDto> result = jdbcTemplate.query(sql, new Object[]{vaccineName}, rs -> {
            if (rs.next()) {
                return Optional.of(new VaccinationRecommendDto(
                    rs.getLong("id"),
                    rs.getString("disease_name"),
                    rs.getString("vaccine_name"),
                    rs.getInt("start_age"),
                    rs.getInt("end_age")
                ));
            } else {
                return Optional.empty();
            }
        });

        // 디버깅 로그 추가
        result.ifPresentOrElse(
            r -> System.out.println("Found recommendation: " + r),
            () -> System.out.println("No recommendation found for vaccine: " + vaccineName)
        );

        return result;
    }
}
