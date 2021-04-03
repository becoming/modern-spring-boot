package tech.becoming.modernspringboot.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Robot {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private Instant created;

    private Instant updated;
}
