package com.imlinker.storage.tag;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@AllArgsConstructor
@Table(name = "tags_crawling")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagCrawlingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ref_tag_id")
    private Long tagId;

    @Column(name = "platform")
    private String platform;

    @Column(name = "section")
    private String section;
}
