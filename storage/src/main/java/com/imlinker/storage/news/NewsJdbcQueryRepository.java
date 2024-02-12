package com.imlinker.storage.news;

import com.imlinker.domain.news.model.query.NewsPaginationQueryCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NewsJdbcQueryRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<NewsEntity> findAllByTagIdWithCursor(NewsPaginationQueryCondition condition){
        String sql =
                """
                        SELECT
                        *
                        FROM
                        news
                        WHERE
                        ref_tag_id=:tagId
                        AND
                        id < :cursorId
                        ORDER BY
                        created_at DESC
                        LIMIT :limit
                """;

        MapSqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("tagId", condition.tagId())
                .addValue("cursorId", condition.cursorId() == null? 9223372036854775807L : condition.cursorId())
                .addValue("limit", condition.size());

        return jdbcTemplate.query(sql, namedParameters, NewsEntity.getRowMapper());

    }
}
