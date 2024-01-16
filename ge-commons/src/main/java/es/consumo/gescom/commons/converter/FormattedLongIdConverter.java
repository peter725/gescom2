package es.consumo.gescom.commons.converter;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class FormattedLongIdConverter {
    public static final String DEFAULT_SEPARATOR = ",";
    public static final int DEFAULT_EXPECTED_ID_COUNT = 2;

    private FormattedLongIdConverter() {
    }

    public static String encode(List<Long> elements) {
        return encode(elements, DEFAULT_SEPARATOR);
    }

    public static String encode(List<Long> elements, String separator) {
        return elements.stream().map(Objects::toString).collect(Collectors.joining(separator));
    }

    public static List<Long> decode(String source) {
        return decode(source, DEFAULT_EXPECTED_ID_COUNT);
    }

    public static List<Long> decode(String source, int expectedIdsCount) {
        return decode(source, expectedIdsCount, DEFAULT_SEPARATOR);
    }

    public static List<Long> decode(String source, int expectedIdsCount, String separator) {
        List<Long> list = Arrays.stream(source.split(separator))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        if (list.size() != expectedIdsCount) {
            final String message = "Invalid number of tokens provided extract IDs. "
                    + "Expected [" + expectedIdsCount + "] tokens, got [" + list.size() + "]";
            log.error(message);
            throw new IllegalArgumentException(message);
        }
        return list;
    }
}
