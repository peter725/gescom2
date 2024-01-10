package es.consumo.junta_arbitral.commons.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;

@Service
public class SimpleDataConverter<E, M> implements DataConverter<E, M> {
    protected final ModelMapper modelMapper;

    @Autowired
    public SimpleDataConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public E convertToEntity(M model) {
        if (model == null) {
            return null;
        }
        return modelMapper.map(model, getClassE());
    }

    @Override
    public void mergeToEntity(E entity, M model) {
        if (model == null || entity == null) {
            return;
        }
        modelMapper.map(model, entity);
    }

    @Override
    public M convertToModel(E entity) {
        if (entity == null) {
            return null;
        }
        return modelMapper.map(entity, getClassM());
    }

    private <T> Class<T> getClassE() {
        return getType(0);
    }

    private <T> Class<T> getClassM() {
        return getType(1);
    }

    private <T> Class<T> getType(int argumentNumber) {
        Class<?> returnType;
        final ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        try {
            returnType = (Class<?>) type.getActualTypeArguments()[argumentNumber];
        } catch (ClassCastException ex) {
            final TypeVariable<?> typeVariable = (TypeVariable<?>) type.getActualTypeArguments()[argumentNumber];
            returnType = (Class<?>) typeVariable.getBounds()[0];
        }

        //noinspection unchecked
        return (Class<T>) returnType;
    }

}
