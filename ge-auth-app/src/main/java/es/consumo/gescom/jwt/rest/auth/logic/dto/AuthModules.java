package es.consumo.gescom.jwt.rest.auth.logic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthModules {
    private String code;
    private Set<String> scopes;
}
