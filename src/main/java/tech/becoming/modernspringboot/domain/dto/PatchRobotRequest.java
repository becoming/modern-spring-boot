package tech.becoming.modernspringboot.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PatchRobotRequest {

    private String name;
    private String description;

}
