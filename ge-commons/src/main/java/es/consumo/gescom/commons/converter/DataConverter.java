package es.consumo.gescom.commons.converter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface DataConverter<E, M> {

    E convertToEntity(M model);

    void mergeToEntity(E entity, M model);

    default List<E> convertToEntity(List<M> list) {
        return this.convertToStreamEntity(list).collect(Collectors.toList());
    }

    default Set<E> convertToEntity(Set<M> list) {
        return this.convertToStreamEntity(list).collect(Collectors.toSet());
    }

    default Page<E> convertToEntity(Page<M> page) {
        if (page == null) {
            return Page.empty();
        }
        return new PageImpl<>(this.convertToEntity(page.getContent()), page.getPageable(), page.getTotalElements());
    }

    M convertToModel(E entity);

    default List<M> convertToModel(List<E> list) {
        return this.convertToStreamModel(list).collect(Collectors.toList());
    }

    default Set<M> convertToModel(Set<E> list) {
        return this.convertToStreamModel(list).collect(Collectors.toSet());
    }

    default Page<M> convertToModel(Page<E> page) {
        if (page == null) {
            return Page.empty();
        }
        return new PageImpl<>(this.convertToModel(page.getContent()), page.getPageable(), page.getTotalElements());
    }

    default Stream<E> convertToStreamEntity(Collection<M> collection) {
        if (collection == null) {
            return Stream.empty();
        }
        return collection.stream().map(this::convertToEntity);
    }

    default Stream<M> convertToStreamModel(Collection<E> collection) {
        if (collection == null) {
            return Stream.empty();
        }
        return collection.stream().map(this::convertToModel);
    }

}
