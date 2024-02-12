package com.imlinker.storage.news;

import static org.assertj.core.api.Assertions.assertThat;

import com.imlinker.domain.news.model.query.NewsPaginationQueryCondition;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class NewsJdbcQueryTest {

    @Autowired NewsJdbcQueryRepository newsJdbcQueryRepository;
    @Autowired NewsJpaRepository newsJpaRepository;

    @Test
    @DisplayName("태그에 맞는 뉴스를 가져온다.")
    public void findAllByTagIdWithCursor() {
        NewsEntity newsEntity = NewsEntity.builder().id(1L).tagId(1L).build();
        newsJpaRepository.save(newsEntity);

        NewsPaginationQueryCondition condition = new NewsPaginationQueryCondition(20, 1L, null);
        List<NewsEntity> newsEntityList = newsJdbcQueryRepository.findAllByTagIdWithCursor(condition);

        assertThat(newsEntityList.get(0).getId()).isEqualTo(newsEntity.getId());
        assertThat(newsEntityList.get(0).getTagId()).isEqualTo(newsEntity.getTagId());
    }
}
