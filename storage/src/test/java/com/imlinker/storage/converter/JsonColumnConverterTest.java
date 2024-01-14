package com.imlinker.storage.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.imlinker.domain.common.Tag;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JsonColumnConverterTest {

    @Test
    public void JSON_COLUMN을_역직렬화_할_수_있다() {
        // given
        List<Tag> tags = List.of(new Tag(1L, "스포츠"), new Tag(2L, "비즈니스"));
        JsonColumnConverter<List<Tag>> sut = new JsonColumnConverter<>();

        // when
        String actual = assertDoesNotThrow(() -> sut.convertToDatabaseColumn(new JsonColumn<>(tags)));

        System.out.println(actual);
    }

    @Test
    public void JSON_COLUMN을_직렬화_할_수_있다() {
        // given
        String json = "[{\"id\": 1, \"name\": \"스포츠\"}, {\"id\": 2, \"name\": \"비즈니스\"}]";
        JsonColumnConverter<List<Tag>> sut = new JsonColumnConverter<>();

        // when
        JsonColumn<List<Tag>> actual = assertDoesNotThrow(() -> sut.convertToEntityAttribute(json));

        System.out.println(actual.getData());
        // then
        assertThat(actual.getData()).isNotEmpty();
        assertThat(actual.getData().size()).isEqualTo(2);
        assertThat(actual.getData()).isEqualTo(List.of(new Tag(1L, "스포츠"), new Tag(2L, "비즈니스")));
    }
}
