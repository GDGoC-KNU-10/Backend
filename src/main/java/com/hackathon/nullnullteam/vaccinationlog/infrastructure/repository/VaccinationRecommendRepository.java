package com.hackathon.nullnullteam.vaccinationlog.infrastructure.repository;

import com.hackathon.nullnullteam.vaccinationlog.infrastructure.repository.dto.VaccinationRecommendDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

        return result;
    }

    public Optional<VaccinationRecommendDto> findById(Long id) {
        String sql = "SELECT id, vaccine_name, start_age, end_age, disease_name " +
            "FROM vaccination_recommend " +
            "WHERE id = ? LIMIT 1";

        return jdbcTemplate.query(sql, new Object[]{id}, rs -> {
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
    }

    public List<VaccinationRecommendDto> findByAgeRange(int age) {
        String sql = "SELECT id, vaccine_name, start_age, end_age, disease_name " +
            "FROM vaccination_recommend " +
            "WHERE start_age <= ? AND end_age >= ?";

        return jdbcTemplate.query(sql, new Object[]{age, age}, (rs, rowNum) ->
            new VaccinationRecommendDto(
                rs.getLong("id"),
                rs.getString("disease_name"),
                rs.getString("vaccine_name"),
                rs.getInt("start_age"),
                rs.getInt("end_age")
            )
        );
    }

    public Page<VaccinationRecommendDto> findByVaccineNamesWithPaging(List<String> vaccinationNames, Pageable pageable) {
        // SQL 쿼리 생성
        String sql = "SELECT id, vaccine_name, start_age, end_age, disease_name " +
            "FROM vaccination_recommend " +
            "WHERE vaccine_name IN (" + String.join(",", Collections.nCopies(vaccinationNames.size(), "?")) + ") " +
            "LIMIT ? OFFSET ?";

        // 값 배열 생성 (예방접종 이름 목록과 페이징 정보 포함)
        List<Object> params = new ArrayList<>(vaccinationNames);
        params.add(pageable.getPageSize());
        params.add(pageable.getOffset());

        // 데이터 조회
        List<VaccinationRecommendDto> recommendations = jdbcTemplate.query(
            sql,
            params.toArray(),
            (rs, rowNum) -> new VaccinationRecommendDto(
                rs.getLong("id"),
                rs.getString("disease_name"),
                rs.getString("vaccine_name"),
                rs.getInt("start_age"),
                rs.getInt("end_age")
            )
        );

        // 전체 데이터 개수 조회 쿼리
        String countSql = "SELECT COUNT(*) FROM vaccination_recommend WHERE vaccine_name IN (" +
            String.join(",", Collections.nCopies(vaccinationNames.size(), "?")) + ")";
        Integer total = jdbcTemplate.queryForObject(countSql, vaccinationNames.toArray(), Integer.class);

        return new PageImpl<>(recommendations, pageable, total);
    }
}
