package es.consumo.gescom.commons.controller;

import es.consumo.gescom.commons.config.AppInfo;
import es.consumo.gescom.commons.constants.ApiEndpoints;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(ApiEndpoints.V1_API + "/health")
@Tag(name = "API Health", description = "Check API status")
public class HealthController {
    private final AppInfo appInfo;

    @Autowired
    HealthController(AppInfo appInfo) {
        this.appInfo = appInfo;
    }

    @GetMapping
    public ResponseEntity<Object> status() {
        Map<String, String> status = new HashMap<>();
        status.put("status", "Application is running");
        status.put("name", appInfo.getName());
        status.put("version", appInfo.getVersion());
        status.put("buildTime", appInfo.getTimestamp());
        status.put("env", appInfo.getProfile());
        status.put("executionNode", appInfo.getExecutionNode());
        return ResponseEntity.ok(status);
    }

}
