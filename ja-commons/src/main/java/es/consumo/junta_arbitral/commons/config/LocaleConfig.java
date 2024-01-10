package es.consumo.junta_arbitral.commons.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Configuration
public class LocaleConfig {

    private static final String MESSAGES_SOURCE_PATH = "classpath:18n";
    public static final Locale DEFAULT_LOCALE = Locale.forLanguageTag("es");


   @Autowired
    public void addFormatters(FormatterRegistry registry) {


        DateTimeFormatterRegistrar dateTimeRegistrar = new DateTimeFormatterRegistrar();
        dateTimeRegistrar.setDateFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        dateTimeRegistrar.setDateTimeFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        dateTimeRegistrar.registerFormatters(registry);

        DateFormatterRegistrar dateRegistrar = new DateFormatterRegistrar();
        dateRegistrar.setFormatter(new DateFormatter("yyyy-MM-dd"));
        dateRegistrar.registerFormatters(registry);

        registry.addFormatter(new DateTimeToMillisFormatter());
    }

    @Bean
    public AcceptHeaderLocaleResolver localeResolver() {
        final AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
        resolver.setDefaultLocale(DEFAULT_LOCALE);
        return resolver;
    }

    public static class DateTimeToMillisFormatter implements Formatter<Timestamp> {
        @Override
        public Timestamp parse(String text, Locale locale) throws ParseException {
            long millis = Long.parseLong(text);
            return Timestamp.from(Instant.ofEpochMilli(millis));
        }

        @Override
        public String print(Timestamp object, Locale locale) {
            return "" + object.toInstant().toEpochMilli();
        }
    }

//    @Bean
//    public ResourceBundleMessageSource messageSource() {
//        final ResourceBundleMessageSource source = new ResourceBundleMessageSource();
//        source.setBasename(MESSAGES_SOURCE_PATH);
//        return source;
//    }
}
