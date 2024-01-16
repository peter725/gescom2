package es.consumo.gescom.commons.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import es.consumo.gescom.commons.constants.EntityState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Pageable;

/**
 * Application filter query base definition class with pagination support.
 * <p>
 * Possible operators to implement using variable_operator format
 * - var: 		Like/Equal to with wildcards. LIKE for string, = for numeric
 * - varNE: 	Exclude value. NOT LIKE for strings, != for numbers and dates
 * - varGT: 	Greater than. > for numbers and dates
 * - varGTE: 	Greater than or equal. >= for numbers and dates
 * - varLT: 	Less than. < for numbers and dates
 * - varLTE: 	Less than or equal. < for numbers and dates
 * <p>
 * e.g. name, nameEQ, nameNE
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Slf4j
public class FilterCriteria {
    // Generic search
    private String search;

    /**
     * Entity states filter, values are defined in {@link EntityState}.
     * <p>
     * Type bay be changed to EntityState in the future.
     */
    private Integer[] state = {EntityState.ON.getValue()};

    /**
     * Required scope filter
     */
    private String scope;

    // Paging and sorting

    /**
     * Specify if the current query should not be paged. Default false.
     */
    private boolean unpaged;

    /**
     * Request page number, first page is zero.
     * Value may be null.
     */
    private Integer page;

    /**
     * Number of items for the page.
     * Value may be null.
     */
    private Integer size;

    /**
     * Sort configuration, default is unsorted. Defined using default sort format (sort=property;direction)
     * <p>
     * e.g. id;desc
     */
    private String[] sort;

    public static FilterCriteria unpaged() {
        FilterCriteria criteria = new FilterCriteria();
        criteria.unpaged = true;
        return criteria;
    }

    public Pageable toPageable() {
        try {
            return unpaged ? PageRequestImpl.of(sort) : PageRequestImpl.of(page, size, sort);
        } catch (Exception e) {
            return Pageable.unpaged();
        }
    }

    /**
     * Returns the current language code
     */
    public String getLanguageIsoCode() {
        return LocaleContextHolder.getLocale().getLanguage();
    }

    /**
     * Returns user current scope, first configured scope or none
     */
    public String getScope() {
        final String NO_SCOPE = "no_scope";
        final String WILDCARD = "%";
        try {
//			UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String code = null;
//			if (this.scope != null && principal.getScopes().contains(scope)) {
//				code = this.scope;
//			} else {
//				Optional<String> first = principal.getScopes().stream().sorted().findFirst();
//				if (first.isPresent()) {
//					code = first.get();
//				}
//			}

            if (code == null) {
                return NO_SCOPE;
            }
            return code + WILDCARD;
        } catch (Exception e) {
            // In case of exception, hide values.
            log.error("Failed to obtain user primary scope");
            return NO_SCOPE;
        }
    }

}
