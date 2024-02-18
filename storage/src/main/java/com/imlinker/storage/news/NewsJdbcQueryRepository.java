package com.imlinker.storage.news;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NewsJdbcQueryRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<NewsEntity> findAllByTagIdWithCursor(Long cursorId, int size, List<Long> tagIds){
        String sql =
                """
                    SELECT
                        *
                    FROM
                        news
                    WHERE
                        1=1
                        AND ref_tag_id IN (:tagIds)
                        AND id <=:cursorId
                    ORDER BY
                        id DESC
                    LIMIT 
                        :limit
                """;


        MapSqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("tagIds", tagIds)
                .addValue("cursorId", cursorId == null? 9223372036854775807L : cursorId)
                .addValue("limit", size);

        return jdbcTemplate.query(sql, namedParameters, NewsEntity.getRowMapper());
    }
}
