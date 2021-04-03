package tech.becoming.modernspringboot.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RobotsRepository extends PagingAndSortingRepository<Robot, Long> {
}
