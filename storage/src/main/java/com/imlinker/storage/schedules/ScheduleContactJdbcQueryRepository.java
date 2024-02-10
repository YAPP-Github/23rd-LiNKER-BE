package com.imlinker.storage.schedules;

import com.imlinker.storage.contacts.ContactsEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScheduleContactJdbcQueryRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<ContactsEntity> findAllByScheduleId(Long scheduleId) {
        String sql = """
            SELECT
                c.*
            FROM
                schedules s
            JOIN
                schedule_contacts_mapping scm
            ON
                s.id=scm.ref_schedule_id
            JOIN
                contacts c
            ON
                s.id=scm.ref_schedule_id
            WHERE
                s.id=:scheduleId
        """;

        MapSqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("scheduleId",scheduleId);

        return jdbcTemplate.query(sql,namedParameters,ContactsEntity.getRowMapper());
    }

    public List<ContactsEntity> findAllByContactId(Long contactId) {
        String sql = """
            SELECT
                c.*
            FROM
                schedules s
            JOIN
                schedule_contacts_mapping scm
            ON
                s.id=scm.ref_schedule_id
            JOIN
                contacts c
            ON
                c.id=scm.ref_contacts_id
            WHERE
                c.id=:contactId
        """;

        MapSqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("contactId",contactId);

        return jdbcTemplate.query(sql,namedParameters,ContactsEntity.getRowMapper());
    }
}
