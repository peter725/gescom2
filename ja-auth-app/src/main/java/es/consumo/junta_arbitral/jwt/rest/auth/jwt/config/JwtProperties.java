package es.consumo.junta_arbitral.jwt.rest.auth.jwt.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.time.Duration;
import java.time.temporal.TemporalAmount;

@Configuration
@PropertySource(value = "classpath:jwt.properties", encoding = "UTF-8")
@Data
public class JwtProperties {

	/**
	 * Token Issuer details
	 */
	@Value("${security.jwt.issuer}")
	private String issuer;

	/**
	 * Http header expected to contain the authorization token
	 */
	@Value("${security.jwt.authorizationHeader}")
	private String authorizationHeader;

	/**
	 * Secret value used to sign and verify the JWT
	 */
	@Value("${security.jwt.secret}")
	private String secret;

	/**
	 * Prefix assigned to token
	 */
	@Value("${security.jwt.tokenPrefix}")
	private String tokenPrefix;

	/**
	 * Token Time To Live expressed in minutes
	 */
	@Value("${security.jwt.tokenTTL}")
	private long tokenTTL;

	/**
	 * Token Time To Live expressed in minutes
	 */
	@Value("${security.jwt.tempTokenTTL}")
	private long tempTokenTTL;

	public TemporalAmount tokenTTLAsTemporalAmount() {
		return Duration.ofMinutes(tokenTTL);
	}

	public TemporalAmount tempTokenTTLAsTemporalAmount() {
		return Duration.ofMinutes(tempTokenTTL);
	}
}
