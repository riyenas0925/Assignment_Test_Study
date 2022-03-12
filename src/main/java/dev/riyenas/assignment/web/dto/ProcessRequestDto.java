package dev.riyenas.assignment.web.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProcessRequestDto {

    @ApiModelProperty(example = "https://google.com", required = true)
    private String url;

    @ApiModelProperty(example = "HTML", required = true)
    private String exposureType;

    @ApiModelProperty(example = "5", required = true)
    private int unit;

}