package com.imlinker.storage.contacts;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ContactsBookmarkJdbcQueryRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<ContactsEntity> findAllByUserId(Long userId){
        String sql = """
            SELECT 
                c.*
            FROM
                contacts c
            JOIN
                contacts_bookmark cb
            ON
                c.id=cb.ref_contact_id
            WHERE
                1=1
                AND cb.ref_user_id=:userId        
            ORDER BY
                c.name DESC
        """;

        MapSqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("userId",userId);

        return jdbcTemplate.query(sql,namedParameters,ContactsEntity.getRowMapper());
    }

}
