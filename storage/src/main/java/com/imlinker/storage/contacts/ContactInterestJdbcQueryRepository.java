package com.imlinker.storage.contacts;

import com.imlinker.domain.contacts.model.ContactInterest;
import com.imlinker.storage.contacts.mapper.ContactsInterestMapper;
import com.imlinker.storage.tag.TagEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ContactInterestJdbcQueryRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<TagEntity> findAllByContactId(Long contactId) {
        String sql = """
            SELECT
                t.*
            FROM
                contact_interest ci
            JOIN
                tags t
            ON
                ci.ref_tag_id=t.id
            WHERE
                1=1
                AND ci.ref_contact_id=:contactId  
        """;
        MapSqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("contactId",contactId);

        return jdbcTemplate.query(sql,namedParameters,TagEntity.getRowMapper());
    }
}
