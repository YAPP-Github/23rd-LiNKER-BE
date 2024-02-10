package com.imlinker.storage.schedules;

import com.imlinker.domain.schedules.model.query.SearchContactIdAndDateRangeScheduleQueryCondition;
import com.imlinker.domain.schedules.model.query.SearchNearTermScheduleQueryCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScheduleJdbcQueryRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<ScheduleEntity> findAllNearTermSchedules(
            SearchNearTermScheduleQueryCondition condition
    ){
        StringBuffer sql = new StringBuffer().append(
        """
            SELECT 
                s.*
            FROM
                schedules s
            WHERE 1=1
            AND ref_user_id=:userId
        """);

        if(condition.isUpcoming()){
            sql
                    .append("AND s.start_date_time > :baseDateTime\n")
                    .append("ORDER BY s.start_date_time ASC\n");
        }else{
            sql
                    .append("AND s.start_date_time < :baseDateTime\n")
                    .append("ORDER BY s.start_date_time DESC\n");
        }
        sql.append("LIMIT :limit");


        MapSqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("limit",condition.size())
                .addValue("userId",condition.userId())
                .addValue("baseDateTime",condition.baseDateTime());

        return jdbcTemplate.query(sql.toString(),namedParameters,ScheduleEntity.getRowMapper());
    }

    public List<ScheduleEntity> findByContactIdAndDateRange(
            SearchContactIdAndDateRangeScheduleQueryCondition condition
    ){
        String sql =
                """
                    SELECT 
                        s.*
                    FROM
                        schedules s
                    JOIN
                        schedule_contacts_mapping scm
                    ON
                        s.id = scm.ref_schedule_id
                    WHERE 1=1
                    AND s.ref_user_id=:userId
                    AND scm.ref_contact_id=:contactId
                    AND (
                            s.start_date_time BETWEEN :from AND :to
                            OR s.end_date_time BETWEEN :from AND :to
                    )
                    ORDER BY s.start_date_time
                    LIMIT :limit
                """;
        MapSqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("userId",condition.userId())
                .addValue("contactId",condition.contactId())
                .addValue("from",condition.from())
                .addValue("to",condition.to())
                .addValue("limit",condition.size());

        return jdbcTemplate.query(sql,namedParameters,ScheduleEntity.getRowMapper());
    }
}
