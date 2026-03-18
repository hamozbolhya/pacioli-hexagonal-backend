package com.pacioli.cabinet.infrastructure.config;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.crypto.Ed25519Verifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.OctetKeyPair;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.time.Instant;
import java.util.Map;

@Configuration
public class JwtDecoderConfig {

    private static final String ISSUER = "http://localhost:3000";
    private static final String JWKS_URL = "http://localhost:3000/api/auth/jwks.json";

    @Bean
    public JwtDecoder jwtDecoder() {
        RestTemplate restTemplate = new RestTemplate();

        OAuth2TokenValidator<Jwt> validator =
                new DelegatingOAuth2TokenValidator<>(
                        JwtValidators.createDefaultWithIssuer(ISSUER)
                );

        return token -> {
            try {
                SignedJWT signedJWT = SignedJWT.parse(token);

                String kid = signedJWT.getHeader().getKeyID();
                String alg = signedJWT.getHeader().getAlgorithm() != null
                        ? signedJWT.getHeader().getAlgorithm().getName()
                        : null;

                if (!"EdDSA".equals(alg)) {
                    throw new JwtException("Unsupported JWT alg: " + alg);
                }

                String jwksJson = restTemplate.getForObject(JWKS_URL, String.class);
                if (jwksJson == null || jwksJson.isBlank()) {
                    throw new JwtException("JWKS response is empty");
                }

                JWKSet jwkSet = JWKSet.parse(jwksJson);
                JWK jwk = jwkSet.getKeyByKeyId(kid);

                if (jwk == null) {
                    throw new JwtException("No JWK found for kid=" + kid);
                }

                if (!(jwk instanceof OctetKeyPair okp)) {
                    throw new JwtException("JWK is not an OctetKeyPair");
                }

                OctetKeyPair publicJwk = okp.toPublicJWK();

                boolean valid = signedJWT.verify(new Ed25519Verifier(publicJwk));
                if (!valid) {
                    throw new JwtException("Invalid Ed25519 signature");
                }

                Map<String, Object> headers = signedJWT.getHeader().toJSONObject();
                Map<String, Object> claims = signedJWT.getJWTClaimsSet().getClaims();

                Instant issuedAt = signedJWT.getJWTClaimsSet().getIssueTime() != null
                        ? signedJWT.getJWTClaimsSet().getIssueTime().toInstant()
                        : null;

                Instant expiresAt = signedJWT.getJWTClaimsSet().getExpirationTime() != null
                        ? signedJWT.getJWTClaimsSet().getExpirationTime().toInstant()
                        : null;

                Jwt jwt = new Jwt(token, issuedAt, expiresAt, headers, claims);

                OAuth2TokenValidatorResult result = validator.validate(jwt);
                if (result.hasErrors()) {
                    OAuth2Error error = result.getErrors().iterator().next();
                    throw new JwtValidationException(error.getDescription(), result.getErrors());
                }

                return jwt;
            } catch (ParseException e) {
                throw new JwtException("Failed to parse JWT", e);
            } catch (JOSEException e) {
                throw new JwtException("Failed to verify Ed25519 signature", e);
            }
        };
    }
}