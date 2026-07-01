package com.example.estoque.security;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private static final Base64.Encoder ENCODER = Base64.getUrlEncoder().withoutPadding();
    private static final Base64.Decoder DECODER = Base64.getUrlDecoder();

    private final ObjectMapper objectMapper;
    private final String secret;
    private final long expirationSeconds;

    public JwtService(
            ObjectMapper objectMapper,
            @Value("${app.jwt.secret}") String secret,
            @Value("${app.jwt.expiration-seconds}") long expirationSeconds
    ) {
        this.objectMapper = objectMapper;
        this.secret = secret;
        this.expirationSeconds = expirationSeconds;
    }

    public String gerarToken(String usuario) {
        try {
            String header = encodeJson(Map.of("alg", "HS256", "typ", "JWT"));
            long expiresAt = Instant.now().plusSeconds(expirationSeconds).getEpochSecond();
            String payload = encodeJson(Map.of("sub", usuario, "exp", expiresAt));
            String unsignedToken = header + "." + payload;
            return unsignedToken + "." + assinar(unsignedToken);
        } catch (Exception exception) {
            throw new IllegalStateException("Erro ao gerar token JWT", exception);
        }
    }

    public String extrairUsuario(String token) {
        try {
            if (!isValido(token)) {
                return null;
            }
            String payload = new String(DECODER.decode(token.split("\\.")[1]), StandardCharsets.UTF_8);
            Map<String, Object> claims = objectMapper.readValue(payload, new TypeReference<>() {
            });
            return (String) claims.get("sub");
        } catch (Exception exception) {
            return null;
        }
    }

    public boolean isValido(String token) {
        try {
            String[] partes = token.split("\\.");
            if (partes.length != 3) {
                return false;
            }
            String unsignedToken = partes[0] + "." + partes[1];
            if (!assinar(unsignedToken).equals(partes[2])) {
                return false;
            }
            String payload = new String(DECODER.decode(partes[1]), StandardCharsets.UTF_8);
            Map<String, Object> claims = objectMapper.readValue(payload, new TypeReference<>() {
            });
            Number expiration = (Number) claims.get("exp");
            return expiration != null && expiration.longValue() > Instant.now().getEpochSecond();
        } catch (Exception exception) {
            return false;
        }
    }

    private String encodeJson(Map<String, Object> data) throws Exception {
        return ENCODER.encodeToString(objectMapper.writeValueAsBytes(data));
    }

    private String assinar(String value) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        return ENCODER.encodeToString(mac.doFinal(value.getBytes(StandardCharsets.UTF_8)));
    }
}
