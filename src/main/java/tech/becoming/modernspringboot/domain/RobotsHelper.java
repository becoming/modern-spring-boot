package tech.becoming.modernspringboot.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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
class RobotsHelper {

    private final MainProperties properties;

    public PageRequest validatePage(PageRequest pageRequest) {
        List<ExceptionDetail> details = new ArrayList<>();

        if (pageRequest.getPageNumber() < 0) {
            var i = ExceptionDetail.ofNameAndMessage(
                    "page",
                    "Page value must be a positive number.");

            details.add(i);
        }

        if (pageRequest.getPageSize() < 0) {
            var i = ExceptionDetail.ofNameAndMessage(
                    "size",
                    "Size value must be a positive number.");

            details.add(i);
        }

        if (pageRequest.getPageSize() > properties.getMaxPageSize()) {
            var i = ExceptionDetail.ofNameAndMessage(
                    "size",
                    "Size value must be lower than " + properties.getMaxPageSize() + ".");

            details.add(i);
        }

        BadRequestException.throwIfHasDetails(details);

        return pageRequest;
    }

    public void validateId(Long id) {
        List<ExceptionDetail> details = new ArrayList<>();

        if (id < 0) {
            var i = ExceptionDetail.ofNameAndMessage(
                    "id",
                    "ID value must be a positive number.");

            details.add(i);
        }

        BadRequestException.throwIfHasDetails(details);
    }

    public void validate(NewRobotRequest dto) {
        List<ExceptionDetail> details = new ArrayList<>();
        if (isEmpty(dto.getName())) {
            var i = ExceptionDetail.ofNameAndMessage(
                    "name",
                    "Name value must be a string with at least one character.");

            details.add(i);
        }

        if (dto.getName().length() > properties.getMaxNameChars()) {
            var i = ExceptionDetail.ofNameAndMessage(
                    "name",
                    "Name length must be shorter than " + properties.getMaxNameChars() + " characters.");

            details.add(i);
        }

        BadRequestException.throwIfHasDetails(details);
    }

    // suppressed for the sake of demo
    @SuppressWarnings("DuplicatedCode")
    public void validate(PatchRobotRequest dto) {
        List<ExceptionDetail> details = new ArrayList<>();
        if (isEmpty(dto.getName())) {
            var i = ExceptionDetail.ofNameAndMessage(
                    "name",
                    "Name value must be a string with at least one character.");

            details.add(i);
        }

        if (dto.getName().length() > properties.getMaxNameChars()) {
            var i = ExceptionDetail.ofNameAndMessage(
                    "name",
                    "Name length must be shorter than " + properties.getMaxNameChars() + " characters.");

            details.add(i);
        }

        BadRequestException.throwIfHasDetails(details);
    }

    private boolean isEmpty(String s) {
        return null == s || s.replace(" ", "").length() < 1;
    }
}
