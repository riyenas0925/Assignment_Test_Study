package dev.riyenas.assignment.service;

import dev.riyenas.assignment.util.CharacterExtractor;
import dev.riyenas.assignment.util.Processor;
import dev.riyenas.assignment.util.Result;
import dev.riyenas.assignment.util.SplitCalculator;
import dev.riyenas.assignment.web.dto.ProcessResponseDto;
import org.springframework.stereotype.Service;

@Service
public class DataProcessService {

    public ProcessResponseDto dataProcess(String data, int unit) {

        CharacterExtractor extractor = new CharacterExtractor(data);

        Processor dataProcessor = Processor.builder()
                .alphabets(extractor.getAlphabets())
                .numbers(extractor.getNumbers())
                .build();

        String processedData = dataProcessor.process();

        SplitCalculator splitCalculator = SplitCalculator.of(processedData, unit);

        Result result = splitCalculator.calculate();

        return new ProcessResponseDto(result);
    }

}
