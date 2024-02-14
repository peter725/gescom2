package es.consumo.gescom.modules.productServices.model.converter;

import es.consumo.gescom.commons.converter.SimpleDataConverter;
import es.consumo.gescom.modules.productServices.model.dto.ProductServiceDTO;
import es.consumo.gescom.modules.productServices.model.entity.ProductServiceEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceConverter extends SimpleDataConverter<ProductServiceEntity, ProductServiceDTO> {
    public ProductServiceConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
