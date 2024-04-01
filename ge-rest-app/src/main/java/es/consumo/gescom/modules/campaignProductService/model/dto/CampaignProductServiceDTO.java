package es.consumo.gescom.modules.campaignProductService.model.dto;

import java.io.Serializable;
import java.util.List;

import es.consumo.gescom.commons.dto.LongIdModel;
import es.consumo.gescom.modules.productServices.model.dto.ProductServiceDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CampaignProductServiceDTO implements Serializable, LongIdModel{

    private Long id;
    private String productName;
    private Long campaignId;
    private String codeProductService;
    private Long productServiceId;


    
}
