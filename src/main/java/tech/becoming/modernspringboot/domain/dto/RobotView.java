package tech.becoming.modernspringboot.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class RobotView {

    private Long id;
    private String name;
    private String description;
    private String created;
    private String updated;

}
