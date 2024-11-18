package es.consumo.gescom.modules.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

@Data
public class VerifyToken implements Serializable {

    @JsonProperty("exp")
    private Timestamp expireAt;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("role")
    private ArrayList<String> roleName;
    private String error;
    @JsonProperty("error_description")
    private String errorDescription;
}
