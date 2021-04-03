package tech.becoming.modernspringboot.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tech.becoming.common.exceptions.BadRequestException;
import tech.becoming.common.exceptions.ExceptionDetail;
import tech.becoming.modernspringboot.domain.dto.NewRobotRequest;
import tech.becoming.modernspringboot.domain.dto.PatchRobotRequest;
import tech.becoming.modernspringboot.properties.MainProperties;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RobotsHelper {

    private final MainProperties properties;

    public void validatePage(int page, int size) {
        List<ExceptionDetail> details = new ArrayList<>();
        if (page < 0) {
            final var i = ExceptionDetail
                    .builder()
                    .name("page")
                    .message("Page value must be a positive number.")
                    .build();

            details.add(i);
        }

        if (size < 0) {
            final var i = ExceptionDetail
                    .builder()
                    .name("size")
                    .message("Size value must be a positive number.")
                    .build();

            details.add(i);
        }

        if (size > properties.getMaxPageSize()) {
            final var i = ExceptionDetail
                    .builder()
                    .name("size")
                    .message("Size value must be lower than " + properties.getMaxPageSize() + ".")
                    .build();

            details.add(i);
        }

        if(details.size() > 0) {
            throw new BadRequestException(details);
        }
    }

    public void validateId(Long id) {
        List<ExceptionDetail> details = new ArrayList<>();

        if (id < 0) {
            final var i = ExceptionDetail
                    .builder()
                    .name("id")
                    .message("ID value must be a positive number.")
                    .build();

            details.add(i);
        }

        if(details.size() > 0) {
            throw new BadRequestException(details);
        }
    }

    public void validate(NewRobotRequest dto) {
        List<ExceptionDetail> details = new ArrayList<>();
        if (isEmpty(dto.getName())) {
            final var i = ExceptionDetail
                    .builder()
                    .name("name")
                    .message("Name value must be a string with at least one character.")
                    .build();

            details.add(i);
        }

        if (dto.getName().length() > properties.getMaxNameChars()) {
            final var i = ExceptionDetail
                    .builder()
                    .name("name")
                    .message("Name length must be shorter than " + properties.getMaxNameChars() + " characters.")
                    .build();

            details.add(i);
        }

        if(details.size() > 0) {
            throw new BadRequestException(details);
        }
    }

    // suppressed for the sake of demo
    @SuppressWarnings("DuplicatedCode")
    public void validate(PatchRobotRequest dto) {
        List<ExceptionDetail> details = new ArrayList<>();
        if (isEmpty(dto.getName())) {
            final var i = ExceptionDetail
                    .builder()
                    .name("name")
                    .message("Name value must be a string with at least one character.")
                    .build();

            details.add(i);
        }

        if (dto.getName().length() > properties.getMaxNameChars()) {
            final var i = ExceptionDetail
                    .builder()
                    .name("name")
                    .message("Name length must be shorter than " + properties.getMaxNameChars() + " characters.")
                    .build();

            details.add(i);
        }

        if(details.size() > 0) {
            throw new BadRequestException(details);
        }
    }

    private boolean isEmpty(String s) {
        return null == s || s.replace(" ", "").length() < 1;
    }
}
