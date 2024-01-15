package es.consumo.gescom.jwt.rest.auth.logic.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.jwt.rest.auth.clave.dto.ClaveAuthResponseData;
import es.consumo.gescom.jwt.rest.auth.clave.service.ClaveService;
import es.consumo.gescom.jwt.rest.auth.logic.constant.AuthResultParams;
import es.consumo.gescom.jwt.rest.auth.logic.service.UserAuthService;
import es.consumo.gescom.jwt.rest.user.db.entity.LoginEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(ApiEndpoints.HOOKS + "/clave")
public class ClaveWebhookController {

    private final ClaveService claveService;
    private final UserAuthService userAuthService;
    private final Environment env;

    @Autowired
    public ClaveWebhookController(ClaveService claveService, UserAuthService userAuthService, Environment env) {
        this.claveService = claveService;
        this.userAuthService = userAuthService;
        this.env = env;
    }

    @RequestMapping(
            method = {RequestMethod.GET, RequestMethod.POST},
            path = "/auth/login_response"
    )
    public String processLoginResponse(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String returnUrl = env.getProperty("jjaa.front.app.url");
        try {

            // Descodificación y validación de la respuesta de clave y emisión del JWT
            ClaveAuthResponseData claveResponse = this.claveService.processResponse(request);
            LoginEntity login = this.userAuthService.createUserIfNotExits(claveResponse);
            String code = this.userAuthService.generateAuthorizationCode(login);
            // Redirección al front para completar la verificación
            redirectAttributes.addAttribute(AuthResultParams.AUTH_ACTION_PARAM, AuthResultParams.ACTION_SIGN_IN);
            redirectAttributes.addAttribute(AuthResultParams.AUTH_STATUS_PARAM, AuthResultParams.RESULT_OK);
            redirectAttributes.addAttribute(AuthResultParams.AUTH_CODE_PARAM, code);
            redirectAttributes.addAttribute(AuthResultParams.AUTH_MESSAGE_PARAM, "Authentication process completed");
            return "redirect:" + returnUrl;
        } catch (Exception e) {
            // Si la descodificación de clave falla, devuelve un error personalizado. Debemos gestionarlo con un mensaje nuestro.
            // Ver que otros posibles errores deberíamos contemplar.
            redirectAttributes.addAttribute(AuthResultParams.AUTH_ACTION_PARAM, AuthResultParams.ACTION_SIGN_IN);
            redirectAttributes.addAttribute(AuthResultParams.AUTH_STATUS_PARAM, AuthResultParams.RESULT_KO);
            redirectAttributes.addAttribute(AuthResultParams.AUTH_MESSAGE_PARAM, e.getMessage());
            return "redirect:" + returnUrl;
        }
    }

    @RequestMapping(
            method = {RequestMethod.GET, RequestMethod.POST},
            path = "/auth/logout_response"
    )
    public String processLogoutResponse(HttpServletRequest request, RedirectAttributes redirectAttributes, Environment env) {
        // Ignoramos cualquier respuesta de clave y redirigimos a la web para completar el proceso
        String returnUrl = env.getProperty("jjaa.front.app.url");
        try {
            redirectAttributes.addAttribute(AuthResultParams.AUTH_ACTION_PARAM, AuthResultParams.ACTION_SIGN_OUT);
            return "redirect:" + returnUrl;
        } catch (RuntimeException e) {
            redirectAttributes.addAttribute(AuthResultParams.AUTH_ACTION_PARAM, AuthResultParams.ACTION_SIGN_OUT);
            return "redirect:" + returnUrl;
        }
    }

}
