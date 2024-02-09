package com.imlinker.coreapi.core.tag.request;

import com.imlinker.domain.tag.CreateTagParam;
import io.swagger.v3.oas.annotations.media.Schema;

public record CreateTagRequest(
        @Schema(description = "platform ID") Long platformId,
        @Schema(description = "태그명") String name) {
    public CreateTagParam toParam() {
        return new CreateTagParam(platformId, name);
    }
}
