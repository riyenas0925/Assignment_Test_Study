package dev.riyenas.assignment.web.dto;

import lombok.Getter;

@Getter
public class ProcessRequestDto {

    private final String url;
    private final String exposureType;
    private final int unit;

    public ProcessRequestDto(String url, String exposureType, int unit) {
        this.url = url;
        this.exposureType = exposureType;
        this.unit = unit;
    }

}