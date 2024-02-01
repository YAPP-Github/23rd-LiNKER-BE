package com.imlinker.domain.tag;

import com.imlinker.domain.tag.model.Tag;
import com.imlinker.enums.OperationResult;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TagService {
    private final TagReader tagReader;
    private final TagUpdater tagUpdater;

    public List<Tag> getTags() {
        return tagReader.findAll();
    }

    @Transactional
    public OperationResult create(CreateTagParam createTagParam) {
        String section = createTagParam.getSection();
        String name = createTagParam.getName();
        log.info("[태그 생성] section: {}, name: {}", section, name);
        tagUpdater.create(section, name);
        return OperationResult.SUCCESS;
    }
}
