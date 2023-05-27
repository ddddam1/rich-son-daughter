package com.starer.stock;

import com.starer.stock.model.RequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.number.NumberFormatAnnotationFormatterFactory;
import org.springframework.format.number.NumberStyleFormatter;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.ui.Model;

import javax.swing.text.NumberFormatter;
import java.util.Date;

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
