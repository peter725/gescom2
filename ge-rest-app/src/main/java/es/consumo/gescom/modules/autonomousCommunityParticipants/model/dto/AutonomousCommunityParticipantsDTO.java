package es.consumo.gescom.modules.autonomousCommunityParticipants.model.dto;

import es.consumo.gescom.commons.dto.LongIdModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AutonomousCommunityParticipantsDTO implements Serializable {

    private Long id;
    private String name;

}
