package com.imlinker.storage.news;

import com.imlinker.storage.common.model.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NewsEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ref_tag_id")
    private Long tagId;

    private String title;

    @Column(name = "thumbnail_img_url")
    private String thumbnailImgUrl;

    private String company;
}
