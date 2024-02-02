package com.imlinker.storage.news;

import com.imlinker.storage.common.model.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "news")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NewsEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ref_tag_id")
    private Long tagId;

    @Column(name = "title")
    private String title;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "news_provider")
    private String newsProvider;
}
