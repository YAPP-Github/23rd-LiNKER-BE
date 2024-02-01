package com.imlinker.coreapi.core.tag.response;

import com.imlinker.domain.tag.model.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record GetTagsResponse(@Schema(description = "태그 리스트") List<Tag> tags) {}
