package es.consumo.gescom.modules.arbitration.model.dto;

import es.consumo.gescom.modules.general.model.entity.ProvinceEntity;
import es.consumo.gescom.modules.general.model.entity.RoadTypeEntity;
import es.consumo.gescom.modules.general.model.entity.TownEntity;
import es.consumo.gescom.commons.dto.LongIdModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class LocationDTO implements Serializable, LongIdModel {
    private Long id;
    private String address;
    private String block;
    private String door;
    private String floor;
    private String ladder;
    private Integer number;
    private String portal;
    private RoadTypeEntity roadType;
    private String postalCode;
    private TownEntity town;
    private ProvinceEntity province;
}
