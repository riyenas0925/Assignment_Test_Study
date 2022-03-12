package dev.riyenas.assignment.service;

import dev.riyenas.assignment.domain.CharacterExtractor;
import dev.riyenas.assignment.domain.Processor;
import dev.riyenas.assignment.domain.Result;
import dev.riyenas.assignment.domain.SplitCalculator;
import org.springframework.stereotype.Service;

@Service
public class DataProcessService {

    public Result dataProcess(String data, int unit) {
        CharacterExtractor extractor = new CharacterExtractor(data);

        Processor dataProcessor = Processor.builder()
                .alphabets(extractor.getAlphabets())
                .numbers(extractor.getNumbers())
                .build();

        String processedData = dataProcessor.process();

        SplitCalculator splitCalculator = SplitCalculator.of(processedData, unit);

        return splitCalculator.calculate();
    }

}
