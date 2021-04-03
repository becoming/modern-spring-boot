package tech.becoming.modernspringboot.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RobotsService {

    private final RobotsRepository repository;
    private final RobotsHelper helper;

    public List<Robot> findInRange(int page, int size) {
        helper.validatePage(page, size);

        return repository
                .findAll(PageRequest.of(page, size))
                .getContent();
    }

}
