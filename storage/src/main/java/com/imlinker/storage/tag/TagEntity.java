package com.imlinker.storage.tag;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

@Getter
@Entity
@AllArgsConstructor
@Table(name = "tags")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public static RowMapper<TagEntity> getRowMapper() {
        return (rs, rowNum) -> new TagEntity(rs.getLong("id"), rs.getString("name"));
    }
}
