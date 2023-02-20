package com.starer.stock;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.number.CurrencyStyleFormatter;
import org.springframework.format.number.NumberFormatAnnotationFormatterFactory;
import org.springframework.format.number.NumberStyleFormatter;
import org.springframework.format.number.PercentStyleFormatter;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Configuration
public class AppConfig {

    @Bean
    public WebClient webClient(){
        String BASE_URL = "http://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService/getStockPriceInfo";
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(BASE_URL);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        return WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(BASE_URL)
                .build();
    }

    @Bean
    public DocumentBuilder documentBuilder() throws Exception{

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        return dBuilder;
    }

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper;
    }

    @Bean
    public FormattingConversionService conversionService() {

        // Use the DefaultFormattingConversionService but do not register defaults
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(false);

        // Ensure @NumberFormat is still supported
//        conversionService.addFormatterForFieldAnnotation(new NumberFormatAnnotationFormatterFactory());
        conversionService.addFormatter(new NumberStyleFormatter());
        // Register JSR-310 date conversion with a specific global format
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setDateFormatter(DateTimeFormatter.ofPattern("yyyyMMdd"));
        registrar.registerFormatters(conversionService);

//        // Register date conversion with a specific global format
//        DateFormatterRegistrar registrar = new DateFormatterRegistrar();
//        registrar.setFormatter(new DateFormatter("yyyyMMdd"));
//        registrar.registerFormatters(conversionService);

        return conversionService;
    }
}

//public final class NumberFormatAnnotationFormatterFactory
//        implements AnnotationFormatterFactory<NumberFormat> {
//
//    public Set<Class<?>> getFieldTypes() {
//        return new HashSet<Class<?>>(Arrays.asList(new Class<?>[] {
//                Short.class, Integer.class, Long.class, Float.class,
//                Double.class, BigDecimal.class, BigInteger.class }));
//    }
//
//    public Printer<Number> getPrinter(NumberFormat annotation, Class<?> fieldType) {
//        return configureFormatterFrom(annotation, fieldType);
//    }
//
//    public Parser<Number> getParser(NumberFormat annotation, Class<?> fieldType) {
//        return configureFormatterFrom(annotation, fieldType);
//    }
//
//    private Formatter<Number> configureFormatterFrom(NumberFormat annotation, Class<?> fieldType) {
//        if (!annotation.pattern().isEmpty()) {
//            return new NumberStyleFormatter(annotation.pattern());
//        } else {
//            NumberFormat.Style style = annotation.style();
//            if (style == NumberFormat.Style.PERCENT) {
//                return new PercentStyleFormatter();
//            } else if (style == NumberFormat.Style.CURRENCY) {
//                return new CurrencyStyleFormatter();
//            } else {
//                return new NumberStyleFormatter();
//            }
//        }
//    }
//}
