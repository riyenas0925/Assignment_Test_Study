package dev.riyenas.assignment.web;

import dev.riyenas.assignment.crawler.ExposureType;
import dev.riyenas.assignment.service.CrawlerService;
import dev.riyenas.assignment.service.DataProcessService;
import dev.riyenas.assignment.web.dto.ProcessRequestDto;
import dev.riyenas.assignment.web.dto.ProcessResponseDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@Api(tags = {"크롤링과 데이터 가공을 제공하는 Controller"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class DataProcessController {

    private final DataProcessService dataProcessService;
    private final CrawlerService crawlerService;

    @PostMapping("/process")
    public ResponseEntity<ProcessResponseDto> dataProcess(
            @Valid @RequestBody ProcessRequestDto requestDto
    ) throws IOException {

        String data = crawlerService.crawl(requestDto.getUrl(), ExposureType.valueOf(requestDto.getExposureType()));
        ProcessResponseDto responseDto = dataProcessService.dataProcess(data, requestDto.getUnit());

        return ResponseEntity.ok(responseDto);
    }

}
