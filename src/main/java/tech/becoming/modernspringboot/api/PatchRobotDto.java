package tech.becoming.modernspringboot.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PatchRobotDto {

    private String name;
    private String description;

}
