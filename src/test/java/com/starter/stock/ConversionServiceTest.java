package com.starter.stock;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.number.NumberStyleFormatter;
import org.springframework.format.support.DefaultFormattingConversionService;

public class ConversionServiceTest {
    @Test
    void conversionServiceTest() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(false);

        conversionService.addFormatter(new NumberStyleFormatter());
//        conversionService.addFormatterForFieldAnnotation(new NumberFormatAnnotationFormatterFactory());

        conversionService.addFormatter(new DateFormatter());
//        System.out.println(conversionService.parse("20200101", Date.class));
//        Assertions.assertThat(conversionService.convert("10000", Integer.class)).isEqualTo(10000);
        System.out.println(conversionService.convert(10000, String.class));
        Assertions.assertThat(conversionService.convert(10000, String.class)).isEqualTo("10,000");

    }
}
