package com.imlinker.linker.news.domain;

import com.imlinker.linker.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class News extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String thumbnailUrl;
    @Column(nullable = false)
    private String publisher;

    @Builder
    public News(String title, String thumbnailUrl, String publisher){
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.publisher = publisher;
    }
}
