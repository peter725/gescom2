package es.consumo.junta_arbitral.jwt.rest.auth.clave.service.impl;

import es.consumo.junta_arbitral.jwt.rest.auth.clave.constant.ClaveParams;
import es.consumo.junta_arbitral.jwt.rest.auth.clave.dto.ClaveAuthResponseData;
import es.consumo.junta_arbitral.jwt.rest.auth.clave.dto.ClaveRequestFormData;
import es.consumo.junta_arbitral.jwt.rest.auth.clave.service.ClaveService;
import es.mscbs.clave2client.LogoutPeticion;
import es.mscbs.clave2client.Peticion;
import es.mscbs.clave2client.Respuesta;
import eu.eidas.auth.commons.attribute.impl.LiteralStringAttributeValue;
import eu.eidas.auth.commons.attribute.impl.StringAttributeValue;
import eu.eidas.auth.engine.xml.opensaml.SecureRandomXmlIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Service
@Slf4j
public class ClaveServiceImpl implements ClaveService {

	@Override
	public ClaveRequestFormData requestAuthentication() {
		log.info("Requesting Clave authentication");
		Peticion request = new Peticion();
		request.defaultRequest();

		String relayId = SecureRandomXmlIdGenerator.INSTANCE.generateIdentifier(8);
		log.trace("Generated new clave relayId [{}]", relayId);
		Map<String, String> params = new HashMap<>();
		params.put(ClaveParams.SAML_LOGIN_REQUEST_PARAM, request.getSAMLRequest());
		params.put(ClaveParams.RELAY_STATE_PARAM, relayId);

		log.info("Created Clave authentication data");
		return new ClaveRequestFormData(request.getPepsUrl(), relayId, params);
	}

	@Override
	public ClaveRequestFormData requestLogout() {
		LogoutPeticion request = new LogoutPeticion();
		request.defaultRequest();

		String relayId = SecureRandomXmlIdGenerator.INSTANCE.generateIdentifier(8);
		Map<String, String> params = new HashMap<>();
		params.put(ClaveParams.SAML_LOGOUT_REQUEST_PARAM, request.getSamlRequestLogout());
		params.put(ClaveParams.RELAY_STATE_PARAM, relayId);

		return new ClaveRequestFormData(request.getPepsUrl(), relayId, params);
	}

	@Override
	public ClaveAuthResponseData processResponse(HttpServletRequest request) {
		try {
			log.trace("::processResponse Starting Cl@ve response processing");
			Respuesta response = new Respuesta();
			response.setSAMLResponse(request.getParameter(ClaveParams.SAML_RESPONSE_PARAM));
			// Esperamos 1 segundo antes de procesar porque gracias a los dioses de clave, si la respuesta
			// llega muy rápido no cumplimos la condición NotBefore impuesta.
			Thread.sleep(1000);
			response.procesarRespuesta(request.getRemoteHost());

			ClaveAuthResponseData data = new ClaveAuthResponseData();
			data.setRelayId(request.getParameter(ClaveParams.RELAY_STATE_PARAM));

			response.getAttrMap().getAttributeMap().keySet().forEach(attr -> {
				String key = "";
				String value;
				try {
					key = attr.getFriendlyName();
					value = ((StringAttributeValue) response.getAttrMap().getAttributeMap()
						.get(attr).asList().get(0)).getValue();
				} catch (NullPointerException | ClassCastException e) {
					value = ((LiteralStringAttributeValue) response.getAttrMap().getAttributeMap()
						.get(attr).asList().get(0)).getValue();
				}

				switch (key) {
					case ClaveParams.DOC_NUMBER_PARAM:
						data.setDocNum(value);
						break;
					case ClaveParams.USER_FIRST_NAME_PARAM:
						data.setFirstName(value);
						break;
					case ClaveParams.USER_SURNAMES_PARAM:
						data.setSurnames(value);
						break;
				}
			});

			log.trace("::processResponse Cl@ve response processing completed");
			return data;
		} catch (Exception ex) {
			final Throwable rootCause = ExceptionUtils.getRootCause(ex);
			log.error("::processResponse Cl@ve response processor has encountered an exception [{}]", rootCause.getMessage());
			log.error("::processResponse Root cause trace: ", rootCause);
			log.error("::processResponse Exception trace: ", ex);
			throw new RuntimeException(rootCause.getMessage(), ex);
		}
	}
}
