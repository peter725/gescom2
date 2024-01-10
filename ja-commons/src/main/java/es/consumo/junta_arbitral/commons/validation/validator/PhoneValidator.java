package es.consumo.junta_arbitral.commons.validation.validator;

import es.consumo.junta_arbitral.commons.validation.constraints.Phone;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<Phone, String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PhoneValidator.class);
    private static final String PHONE_REGEX = "\\d{5,12}";
    private static final Pattern PHONE = Pattern.compile(PHONE_REGEX);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        LOGGER.trace("Attempting PHONE validation");
        if (StringUtils.isBlank(value)) {
            LOGGER.trace("PHONE number is blank, skipping validation");
            return true;
        }
        String phone = extractPhoneNumber(value);
        boolean isValid = PHONE.matcher(phone).matches();
        LOGGER.trace("Completed PHONE validation");
        return isValid;
    }

    private String extractPhoneNumber(String value) {
        String[] numbers = value.trim().split(" ");
        return String.join("", numbers);
    }
}
