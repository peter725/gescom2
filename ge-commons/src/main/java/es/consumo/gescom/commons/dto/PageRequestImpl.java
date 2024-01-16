package es.consumo.gescom.commons.dto;

import lombok.EqualsAndHashCode;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@EqualsAndHashCode(callSuper = false)
public class PageRequestImpl extends PageRequest {

    /**
     *
     */
    private static final long serialVersionUID = -4603604810238274397L;
    private final boolean paged;

    protected PageRequestImpl(int page, int size, Sort sort, boolean paged) {
        super(page, size, sort);
        this.paged = paged;
    }

    public static PageRequestImpl of(Integer page, Integer size, String[] sort) {
        final int tempPage = (page != null && page > -1) ? page : 0;
        final int tempSize = (size != null && size > 0) ? size : 0;
        return new PageRequestImpl(tempPage, tempSize, buildSort(sort), true);
    }

    public static PageRequestImpl of(String[] sort) {
        return new PageRequestImpl(0, 1, buildSort(sort), false);
    }

    public static Sort buildSort(String[] sort) {
        if (sort == null) {
            return Sort.unsorted();
        }
        List<Sort.Order> orders = Arrays.stream(sort)
                .filter(operation -> operation != null && operation.contains(";"))
                .map(operation -> {
                    String[] splitOperation = operation.split(";");
                    String property = splitOperation[0].trim();
                    String direction = splitOperation[1].trim();
                    return new Sort.Order(Sort.Direction.fromString(direction), property);
                }).collect(Collectors.toList());
        return Sort.by(orders);
    }

    @Override
    public boolean isPaged() {
        return this.paged;
    }

}
