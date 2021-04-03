package tech.becoming.modernspringboot.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "ROBOTS", schema = "MODERN_SPRING_BOOT")
@NoArgsConstructor
@Getter
@Setter
public class Robot {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Instant created;

    private Instant updated;
}
