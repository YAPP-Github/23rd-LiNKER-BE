package com.imlinker.storage.user;

import com.imlinker.domain.user.model.UserInterest;
import com.imlinker.storage.tag.TagEntity;
import com.imlinker.storage.user.mapper.UserInterestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class UserInterestJdbcQueryRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    public List<TagEntity> findAllByUserId(Long userId) {
        String sql = """
            SELECT 
                t.* 
            FROM 
                user_interest ui 
            JOIN 
                tags t 
            ON 
                ui.ref_tag_id=t.id 
            WHERE 1=1 
            AND ui.ref_user_id=:userId
        """;
        MapSqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("userId",userId);
      return jdbcTemplate.query(sql,namedParameters,TagEntity.getRowMapper());
    }
}
