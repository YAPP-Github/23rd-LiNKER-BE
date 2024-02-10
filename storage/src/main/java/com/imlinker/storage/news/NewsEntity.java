package com.imlinker.storage.news;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "news")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class NewsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ref_tag_id")
    private Long tagId;

    @Column(name = "title")
    private String title;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "news_url")
    private String newsUrl;

    @Column(name = "news_provider")
    private String newsProvider;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
