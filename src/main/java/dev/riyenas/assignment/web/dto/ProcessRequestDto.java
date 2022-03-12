package dev.riyenas.assignment.web.dto;

import dev.riyenas.assignment.validator.Exposure;
import dev.riyenas.assignment.validator.Protocol;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ProcessRequestDto {

    @ApiModelProperty(example = "https://google.com", required = true)
    @NotBlank(message = "url은 필수 값입니다.")
    @Protocol(message = "url은 https또는 http가 포함되어야 합니다.")
    private String url;

    @ApiModelProperty(example = "HTML", required = true)
    @NotBlank(message = "노출 유형은 필수값입니다.")
    @Exposure(message = "노출 유형은 TEXT와 HTML만 허용됩니다.")
    private String exposureType;

    @ApiModelProperty(example = "5", required = true)
    @NotNull(message = "묶음 단위는 필수값입니다.")
    @Min(value = 1, message = "묶음 단위는 0일 수 없습니다.")
    private int unit;

}